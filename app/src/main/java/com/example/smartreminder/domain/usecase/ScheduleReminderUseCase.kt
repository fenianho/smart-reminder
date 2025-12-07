package com.example.smartreminder.domain.usecase

import android.util.Log
import com.example.smartreminder.domain.model.MonthlyRepeatType
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import java.util.*
import javax.inject.Inject

class ScheduleReminderUseCase @Inject constructor() {
    private val TAG = "ScheduleReminderUseCase"

    operator fun invoke(reminder: Reminder): List<Long> {
        Log.d(TAG, "Scheduling reminder: ${reminder.title}, repeatType: ${reminder.repeatType}, repeatDays: ${reminder.repeatDays}")
        val scheduledTimes = mutableListOf<Long>()

        when (reminder.repeatType) {
            RepeatType.NONE -> {
                scheduledTimes.add(reminder.reminderTime)
                Log.d(TAG, "Added single reminder time: ${Date(reminder.reminderTime)}")
            }
            RepeatType.DAILY -> {
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = reminder.reminderTime
                }
                
                // 只计算下一次提醒时间
                // 如果提醒时间已过，则计算明天的同一时间
                if (calendar.timeInMillis <= System.currentTimeMillis()) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                }
                scheduledTimes.add(calendar.timeInMillis)
                Log.d(TAG, "Added next daily reminder time: ${Date(calendar.timeInMillis)}")
            }
            RepeatType.WEEKLY -> {
                if (!reminder.repeatDays.isNullOrEmpty()) {
                    val baseCalendar = Calendar.getInstance()
                    baseCalendar.timeInMillis = System.currentTimeMillis()
                    
                    Log.d(TAG, "Base calendar time: ${Date(baseCalendar.timeInMillis)}")
                    Log.d(TAG, "Current time: ${Date(System.currentTimeMillis())}")
                    
                    // 映射我们的1-7系统到Calendar常量 (1=Monday, ..., 7=Sunday)
                    val dayOfWeekMap = mapOf(
                        1 to Calendar.MONDAY,
                        2 to Calendar.TUESDAY,
                        3 to Calendar.WEDNESDAY,
                        4 to Calendar.THURSDAY,
                        5 to Calendar.FRIDAY,
                        6 to Calendar.SATURDAY,
                        7 to Calendar.SUNDAY
                    )
                    
                    // 映射Calendar常量到我们的1-7系统 (1=Monday, ..., 7=Sunday)
                    val calendarToDayMap = mapOf(
                        Calendar.MONDAY to 1,
                        Calendar.TUESDAY to 2,
                        Calendar.WEDNESDAY to 3,
                        Calendar.THURSDAY to 4,
                        Calendar.FRIDAY to 5,
                        Calendar.SATURDAY to 6,
                        Calendar.SUNDAY to 7
                    )
                    
                    val currentDay = calendarToDayMap[baseCalendar.get(Calendar.DAY_OF_WEEK)] ?: 1
                    Log.d(TAG, "Current day (1-7 system): $currentDay")
                    
                    // 查找下一个选定的星期几
                    var nextDay: Int? = null
                    var weeksToAdd = 0
                    
                    // 检查今天是否是选定的日期
                    if (currentDay in reminder.repeatDays) {
                        // 检查今天的提醒时间是否已经过了
                        val todayCalendar = baseCalendar.clone() as Calendar
                        val originalCalendar = Calendar.getInstance().apply {
                            timeInMillis = reminder.reminderTime
                        }
                        todayCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                        todayCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                        todayCalendar.set(Calendar.SECOND, 0)
                        todayCalendar.set(Calendar.MILLISECOND, 0)
                        
                        // 如果今天的提醒时间还没到，选择今天
                        if (todayCalendar.timeInMillis > System.currentTimeMillis()) {
                            nextDay = currentDay
                            weeksToAdd = 0
                            Log.d(TAG, "Using today as next day: $currentDay")
                        }
                    }
                    
                    // 如果今天不是下一个提醒日期，则查找之后的日期
                    if (nextDay == null) {
                        // 从明天开始查找一周内的日期
                        for (i in 1..7) {
                            val checkDay = ((currentDay - 1 + i) % 7) + 1
                            if (checkDay in reminder.repeatDays) {
                                nextDay = checkDay
                                // 如果找到的日期在本周内（i <= 7-currentDay），则不需要增加周数
                                // 否则需要增加一周
                                weeksToAdd = if (i <= (7 - currentDay)) 0 else 1
                                Log.d(TAG, "Found next day: $checkDay, weeks to add: $weeksToAdd")
                                break
                            }
                        }
                    }
                    
                    // 如果本周没有找到，则查找下周的第一天
                    if (nextDay == null) {
                        val sortedDays = reminder.repeatDays.sorted()
                        nextDay = sortedDays.firstOrNull()
                        weeksToAdd = 1
                        Log.d(TAG, "No day found this week, using next week's first day: $nextDay, weeks to add: $weeksToAdd")
                    }
                    
                    if (nextDay != null) {
                        val targetDayOfWeek = dayOfWeekMap[nextDay] ?: Calendar.MONDAY
                        
                        // 创建目标日历
                        val targetCalendar = baseCalendar.clone() as Calendar
                        
                        // 设置为目标星期几
                        targetCalendar.set(Calendar.DAY_OF_WEEK, targetDayOfWeek)
                        
                        // 调整周数
                        targetCalendar.add(Calendar.WEEK_OF_YEAR, weeksToAdd)
                        
                        // 确保时间部分与原始提醒时间一致
                        val originalCalendar = Calendar.getInstance().apply {
                            timeInMillis = reminder.reminderTime
                        }
                        targetCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                        targetCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                        targetCalendar.set(Calendar.SECOND, 0)
                        targetCalendar.set(Calendar.MILLISECOND, 0)
                        
                        Log.d(TAG, "Target calendar before adjustment: ${Date(targetCalendar.timeInMillis)}")
                        
                        // 再次检查时间是否已经过了
                        if (targetCalendar.timeInMillis <= System.currentTimeMillis()) {
                            // 这种情况不应该发生，但如果发生了，确保时间是未来的
                            do {
                                targetCalendar.add(Calendar.DAY_OF_YEAR, 1)
                            } while (targetCalendar.timeInMillis <= System.currentTimeMillis())
                            Log.d(TAG, "Adjusted to future time: ${Date(targetCalendar.timeInMillis)}")
                        }
                        
                        scheduledTimes.add(targetCalendar.timeInMillis)
                        Log.d(TAG, "Added next weekly reminder time: ${Date(targetCalendar.timeInMillis)}")
                    }
                } else {
                    // 如果没有指定重复天数，则默认使用原始提醒日期的星期几
                    val calendar = Calendar.getInstance().apply {
                        timeInMillis = reminder.reminderTime
                    }
                    
                    // 如果提醒时间已过，则计算下周的同一时间
                    if (calendar.timeInMillis <= System.currentTimeMillis()) {
                        calendar.add(Calendar.WEEK_OF_YEAR, 1)
                    }
                    scheduledTimes.add(calendar.timeInMillis)
                    Log.d(TAG, "Added next default weekly reminder time: ${Date(calendar.timeInMillis)}")
                }
            }
            RepeatType.MONTHLY -> {
                val baseCalendar = Calendar.getInstance()
                baseCalendar.timeInMillis = System.currentTimeMillis()
                
                Log.d(TAG, "Base calendar time: ${Date(baseCalendar.timeInMillis)}")
                Log.d(TAG, "Current time: ${Date(System.currentTimeMillis())}")
                
                when (reminder.monthlyRepeatType) {
                    MonthlyRepeatType.BY_DATE -> {
                        // 按日期重复 - 支持多选日期
                        val selectedDays = reminder.monthlyRepeatDays ?: emptySet()
                        
                        if (selectedDays.isNotEmpty()) {
                            // 解析选中的日期
                            Log.d(TAG, "Selected monthly repeat days: $selectedDays")
                            
                            // 获取当前月份的日期范围
                            val currentMonth = baseCalendar.get(Calendar.MONTH)
                            val currentYear = baseCalendar.get(Calendar.YEAR)
                            
                            // 创建当前月份的日历
                            val monthCalendar = Calendar.getInstance().apply {
                                set(Calendar.YEAR, currentYear)
                                set(Calendar.MONTH, currentMonth)
                                set(Calendar.DAY_OF_MONTH, 1)
                                set(Calendar.HOUR_OF_DAY, 0)
                                set(Calendar.MINUTE, 0)
                                set(Calendar.SECOND, 0)
                                set(Calendar.MILLISECOND, 0)
                            }
                            
                            // 获取当前月份的最大天数
                            val maxDaysInMonth = monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                            
                            // 查找当前月份中所有符合条件的日期（如果选择的日期不存在，则使用该月最后一天）
                            val validDatesInMonth = mutableListOf<Int>()
                            
                            for (day in selectedDays) {
                                if (day >= 1 && day <= maxDaysInMonth) {
                                    validDatesInMonth.add(day)
                                } else {
                                    // 如果选择的日期不存在，使用该月的最后一天
                                    validDatesInMonth.add(maxDaysInMonth)
                                    Log.d(TAG, "Selected day $day is not valid for month with $maxDaysInMonth days, using last day: $maxDaysInMonth")
                                }
                            }
                            
                            // 去重并排序
                            val uniqueDates = validDatesInMonth.distinct().sorted()
                            
                            // 查找下一个符合条件的日期
                            var nextDate: Int? = null
                            var monthsToAdd = 0
                            
                            // 检查当前月份
                            val currentDayOfMonth = baseCalendar.get(Calendar.DAY_OF_MONTH)
                            
                            // 检查今天是否是选定的日期，并且今天的提醒时间还未到
                            val originalCalendar = Calendar.getInstance().apply {
                                timeInMillis = reminder.reminderTime
                            }
                            
                            // 创建今天的日历，设置正确的时分
                            val todayCalendar = baseCalendar.clone() as Calendar
                            todayCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                            todayCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                            todayCalendar.set(Calendar.SECOND, 0)
                            todayCalendar.set(Calendar.MILLISECOND, 0)
                            
                            Log.d(TAG, "Current day of month: $currentDayOfMonth")
                            Log.d(TAG, "Today calendar time: ${Date(todayCalendar.timeInMillis)}")
                            Log.d(TAG, "System time: ${Date(System.currentTimeMillis())}")
                            
                            // 检查今天是否在选定的日期中，并且今天的提醒时间还未到
                            if (currentDayOfMonth in uniqueDates && todayCalendar.timeInMillis > System.currentTimeMillis()) {
                                nextDate = currentDayOfMonth
                                monthsToAdd = 0
                                Log.d(TAG, "Using today as next date: $currentDayOfMonth")
                            } else {
                                // 查找当前月份中下一个符合条件的日期
                                for (day in uniqueDates) {
                                    if (day > currentDayOfMonth) {
                                        nextDate = day
                                        monthsToAdd = 0
                                        Log.d(TAG, "Found next date in current month: $day")
                                        break
                                    }
                                }
                                
                                // 如果当前月份没有找到，查找下个月
                                if (nextDate == null) {
                                    nextDate = uniqueDates.firstOrNull()
                                    monthsToAdd = 1
                                    Log.d(TAG, "No date found in current month, using next month's first date: $nextDate")
                                }
                            }
                            
                            if (nextDate != null) {
                                // 创建目标日历
                                val targetCalendar = baseCalendar.clone() as Calendar
                                targetCalendar.add(Calendar.MONTH, monthsToAdd)
                                targetCalendar.set(Calendar.DAY_OF_MONTH, nextDate)
                                
                                // 确保时间部分与原始提醒时间一致
                                targetCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                                targetCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                                targetCalendar.set(Calendar.SECOND, 0)
                                targetCalendar.set(Calendar.MILLISECOND, 0)
                                
                                Log.d(TAG, "Target calendar: ${Date(targetCalendar.timeInMillis)}")
                                
                                scheduledTimes.add(targetCalendar.timeInMillis)
                                Log.d(TAG, "Added next monthly reminder time (by date): ${Date(targetCalendar.timeInMillis)}")
                            }
                        } else {
                            // 如果没有选择日期，使用默认行为
                            val calendar = Calendar.getInstance().apply {
                                timeInMillis = reminder.reminderTime
                            }
                            
                            // 如果提醒时间已过，则计算下个月的同一时间
                            if (calendar.timeInMillis <= System.currentTimeMillis()) {
                                calendar.add(Calendar.MONTH, 1)
                            }
                            scheduledTimes.add(calendar.timeInMillis)
                            Log.d(TAG, "Added next default monthly reminder time: ${Date(calendar.timeInMillis)}")
                        }
                    }
                    MonthlyRepeatType.BY_WEEKDAY -> {
                        // 按星期几重复
                        val weekNumbers = reminder.monthlyRepeatWeeks ?: setOf(1)
                        val daysOfWeek = reminder.monthlyRepeatDaysOfWeek ?: setOf(1)
                        
                        Log.d(TAG, "Monthly repeat by weekday - weekNumbers: $weekNumbers, daysOfWeek: $daysOfWeek")
                        
                        // 映射我们的1-7系统到Calendar常量
                        val dayOfWeekMap = mapOf(
                            1 to Calendar.MONDAY,
                            2 to Calendar.TUESDAY,
                            3 to Calendar.WEDNESDAY,
                            4 to Calendar.THURSDAY,
                            5 to Calendar.FRIDAY,
                            6 to Calendar.SATURDAY,
                            7 to Calendar.SUNDAY
                        )
                        
                        for (weekNumber in weekNumbers) {
                            for (dayOfWeek in daysOfWeek) {
                                val targetDayOfWeek = dayOfWeekMap[dayOfWeek] ?: Calendar.MONDAY
                                
                                val targetCalendar = baseCalendar.clone() as Calendar
                                
                                // 设置到正确的星期几
                                targetCalendar.set(Calendar.DAY_OF_WEEK, targetDayOfWeek)
                                
                                // 调整到正确的星期数
                                if (weekNumber > 0) {
                                    // 第1-4个星期
                                    targetCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekNumber)
                                } else {
                                    // 最后一个星期
                                    targetCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, -1)
                                }
                                
                                // 确保时间部分与原始提醒时间一致
                                val originalCalendar = Calendar.getInstance().apply {
                                    timeInMillis = reminder.reminderTime
                                }
                                targetCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                                targetCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                                targetCalendar.set(Calendar.SECOND, 0)
                                targetCalendar.set(Calendar.MILLISECOND, 0)
                                
                                Log.d(TAG, "Target calendar before month check: ${Date(targetCalendar.timeInMillis)}")
                                
                                // 检查计算出的时间是否已经过了
                                if (targetCalendar.timeInMillis <= System.currentTimeMillis()) {
                                    // 如果时间已经过了，移到下个月
                                    targetCalendar.add(Calendar.MONTH, 1)
                                    
                                    // 重新设置到正确的星期几和星期数
                                    targetCalendar.set(Calendar.DAY_OF_WEEK, targetDayOfWeek)
                                    if (weekNumber > 0) {
                                        targetCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekNumber)
                                    } else {
                                        targetCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, -1)
                                    }
                                    
                                    // 再次设置时间部分
                                    targetCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY))
                                    targetCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE))
                                    targetCalendar.set(Calendar.SECOND, 0)
                                    targetCalendar.set(Calendar.MILLISECOND, 0)
                                    
                                    Log.d(TAG, "Adjusted to next month: ${Date(targetCalendar.timeInMillis)}")
                                }
                                
                                scheduledTimes.add(targetCalendar.timeInMillis)
                                Log.d(TAG, "Added next monthly reminder time (by weekday): ${Date(targetCalendar.timeInMillis)}")
                            }
                        }
                    }
                    null -> {
                        // 默认按月重复（使用原始日期）
                        val calendar = Calendar.getInstance().apply {
                            timeInMillis = reminder.reminderTime
                        }
                        
                        // 如果提醒时间已过，则计算下个月的同一时间
                        if (calendar.timeInMillis <= System.currentTimeMillis()) {
                            calendar.add(Calendar.MONTH, 1)
                        }
                        scheduledTimes.add(calendar.timeInMillis)
                        Log.d(TAG, "Added next default monthly reminder time: ${Date(calendar.timeInMillis)}")
                    }
                }
            }
            RepeatType.YEARLY -> {
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = reminder.reminderTime
                }
                
                // 只计算下一次提醒时间
                // 如果提醒时间已过，则计算下一年的同一时间
                if (calendar.timeInMillis <= System.currentTimeMillis()) {
                    calendar.add(Calendar.YEAR, 1)
                }
                scheduledTimes.add(calendar.timeInMillis)
                Log.d(TAG, "Added next yearly reminder time: ${Date(calendar.timeInMillis)}")
            }
        }

        // 最后再过滤一次，确保所有时间都是未来的
        val filteredTimes = scheduledTimes.filter { it > System.currentTimeMillis() }.sorted()
        Log.d(TAG, "Final scheduled times count: ${filteredTimes.size}")
        filteredTimes.forEach { time ->
            Log.d(TAG, "Scheduled time: ${Date(time)}")
        }
        return filteredTimes
    }
}
