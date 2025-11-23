package com.example.smartreminder.data.ai

import android.util.Log
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.RepeatType
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * 本地NLP解析器
 * 用于解析简单的自然语言提醒文本
 */
class NlpParser @Inject constructor() {
    private val TAG = "NlpParser"

    fun parse(text: String): AiParseResult {
        Log.d(TAG, "Parsing text with local NLP: $text")
        
        // 基本信息提取
        val title = extractTitle(text)
        val description = if (title != text) text else null
        
        // 时间提取
        val time = extractTime(text) ?: LocalTime.of(9, 0)
        
        // 日期提取
        val date = extractDate(text)
        
        // 重复类型提取
        val repeatInfo = extractRepeatInfo(text)
        
        val result = AiParseResult(
            title = title,
            description = description,
            scheduledTime = if (date != null) {
                val dateTime = date.atTime(time)
                dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
            } else {
                val today = LocalDate.now()
                val dateTime = today.atTime(time)
                dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
            },
            repeatType = repeatInfo.repeatType,
            monthDays = repeatInfo.monthDays,
            weekDays = repeatInfo.weekDays,
            monthlyWeek = repeatInfo.monthlyWeek,
            monthlyWeekDays = repeatInfo.monthlyWeekDays
        )
        
        Log.d(TAG, "Parsed result: $result")
        return result
    }
    
    private fun extractTitle(text: String): String {
        // 简单的标题提取：去除时间和重复相关的词汇
        var title = text
        
        // 移除时间相关词汇
        title = title.replace(Regex("\\d{1,2}点\\d{0,2}分?"), "").trim()
        title = title.replace(Regex("\\d{1,2}:\\d{2}"), "").trim()
        title = title.replace(Regex("每天|每周|每月|每年|工作日|周末|周[一二三四五六日]"), "").trim()
        title = title.replace(Regex("上午|下午|晚上"), "").trim()
        
        // 如果处理后标题为空，则使用原文本
        return if (title.isBlank()) text else title
    }
    
    private fun extractTime(text: String): LocalTime? {
        // 匹配 "10点30分" 或 "10:30" 格式
        val timePattern1 = Pattern.compile("(\\d{1,2})点(\\d{0,2})分?")
        val matcher1 = timePattern1.matcher(text)
        if (matcher1.find()) {
            val hour = matcher1.group(1)?.toIntOrNull() ?: 9
            val minute = matcher1.group(2)?.toIntOrNull() ?: 0
            return LocalTime.of(hour, minute)
        }
        
        val timePattern2 = Pattern.compile("(\\d{1,2}):(\\d{2})")
        val matcher2 = timePattern2.matcher(text)
        if (matcher2.find()) {
            val hour = matcher2.group(1)?.toIntOrNull() ?: 9
            val minute = matcher2.group(2)?.toIntOrNull() ?: 0
            return LocalTime.of(hour, minute)
        }
        
        return null
    }
    
    private fun extractDate(text: String): LocalDate? {
        // 匹配 "明天"、"后天" 等相对日期
        if (text.contains("明天")) {
            return LocalDate.now().plusDays(1)
        }
        if (text.contains("后天")) {
            return LocalDate.now().plusDays(2)
        }
        
        // 匹配 "YYYY-MM-DD" 格式
        val datePattern = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})")
        val matcher = datePattern.matcher(text)
        if (matcher.find()) {
            val year = matcher.group(1)?.toIntOrNull() ?: return null
            val month = matcher.group(2)?.toIntOrNull() ?: return null
            val day = matcher.group(3)?.toIntOrNull() ?: return null
            return LocalDate.of(year, month, day)
        }
        
        return null
    }
    
    private fun extractRepeatInfo(text: String): RepeatInfo {
        val repeatInfo = RepeatInfo()
        
        when {
            text.contains("每天") -> {
                repeatInfo.repeatType = RepeatType.DAILY
            }
            text.contains("每周") -> {
                repeatInfo.repeatType = RepeatType.WEEKLY
                // 提取具体的星期几
                repeatInfo.weekDays = extractWeekDays(text)
            }
            text.contains("每月") -> {
                repeatInfo.repeatType = RepeatType.MONTHLY
                // 检查是否是按星期重复
                if (text.contains("第") && text.contains("个") && (text.contains("周末") || text.contains("周"))) {
                    // 处理"每月第二个周末"这样的表达
                    repeatInfo.monthlyWeek = extractMonthlyWeek(text)
                    repeatInfo.monthlyWeekDays = extractMonthlyWeekDays(text)
                } else if (text.contains("最后") && (text.contains("周末") || text.contains("工作日") || text.contains("一天"))) {
                    // 处理"每月最后一个周末"或"每月最后一天"这样的表达
                    repeatInfo.monthlyWeek = -1
                    repeatInfo.monthlyWeekDays = extractMonthlyWeekDays(text)
                } else {
                    // 按日期重复
                    repeatInfo.monthDays = extractMonthDays(text)
                }
            }
            text.contains("每年") -> {
                repeatInfo.repeatType = RepeatType.YEARLY
            }
            text.contains("工作日") -> {
                repeatInfo.repeatType = RepeatType.WEEKLY
                repeatInfo.weekDays = setOf(1, 2, 3, 4, 5) // 周一到周五
            }
            text.contains("周末") && !text.contains("每月") -> {
                repeatInfo.repeatType = RepeatType.WEEKLY
                repeatInfo.weekDays = setOf(6, 7) // 周六和周日
            }
        }
        
        return repeatInfo
    }
    
    private fun extractWeekDays(text: String): Set<Int> {
        val weekdays = mutableSetOf<Int>()
        if (text.contains("周一") || text.contains("星期一")) weekdays.add(1)
        if (text.contains("周二") || text.contains("星期二")) weekdays.add(2)
        if (text.contains("周三") || text.contains("星期三")) weekdays.add(3)
        if (text.contains("周四") || text.contains("星期四")) weekdays.add(4)
        if (text.contains("周五") || text.contains("星期五")) weekdays.add(5)
        if (text.contains("周六") || text.contains("星期六")) weekdays.add(6)
        if (text.contains("周日") || text.contains("星期日") || text.contains("周日")) weekdays.add(7)
        
        return weekdays.ifEmpty { setOf(1, 2, 3, 4, 5) } // 默认工作日
    }
    
    private fun extractMonthDays(text: String): Set<Int> {
        val monthdays = mutableSetOf<Int>()
        
        // 匹配"每月5号"或"每月31号"这样的表达
        val dayPattern = Pattern.compile("每月(\\d{1,2})号?")
        val matcher = dayPattern.matcher(text)
        if (matcher.find()) {
            val day = matcher.group(1)?.toIntOrNull()
            if (day != null && day in 1..31) {
                monthdays.add(day)
            }
        }
        
        // 匹配"月末"或"每月最后一天"
        if (text.contains("月末") || text.contains("最后一天")) {
            monthdays.add(31) // 用31表示月末
        }
        
        return monthdays.ifEmpty { setOf(1) } // 默认每月1号
    }
    
    private fun extractMonthlyWeek(text: String): Int? {
        // 匹配"每月第二个"或"每月第2个"这样的表达
        val weekPattern = Pattern.compile("每月第([一二三四]|\\d)个")
        val matcher = weekPattern.matcher(text)
        if (matcher.find()) {
            val weekStr = matcher.group(1)
            return when (weekStr) {
                "一" -> 1
                "二" -> 2
                "三" -> 3
                "四" -> 4
                else -> weekStr?.toIntOrNull()
            }
        }
        return null
    }
    
    private fun extractMonthlyWeekDays(text: String): Set<Int> {
        val weekdays = mutableSetOf<Int>()
        
        if (text.contains("周末")) {
            weekdays.addAll(setOf(6, 7)) // 周六和周日
        } else if (text.contains("工作日")) {
            weekdays.addAll(setOf(1, 2, 3, 4, 5)) // 周一到周五
        } else {
            // 尝试提取具体的星期几
            weekdays.addAll(extractWeekDays(text))
        }
        
        return weekdays.ifEmpty { setOf(1, 2, 3, 4, 5) } // 默认工作日
    }
    
    private data class RepeatInfo(
        var repeatType: RepeatType = RepeatType.NONE,
        var monthDays: Set<Int> = emptySet(),
        var weekDays: Set<Int> = emptySet(),
        var monthlyWeek: Int? = null,
        var monthlyWeekDays: Set<Int> = emptySet()
    )
}