package com.example.smartreminder.data.ai

import android.util.Log
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiTimeSuggestion
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 自然语言处理解析器
 * 用于处理基本的文本解析，作为AI服务的后备方案
 */
@Singleton
class NlpParser @Inject constructor() {

    private val TAG = "NlpParser"

    /**
     * 解析提醒文本
     */
    fun parseReminderText(text: String): AiParseResult {
        try {
            val cleanText = text.trim()

            // 提取时间信息
            val timeInfo = extractTimeInfo(cleanText)

            // 提取标题和描述
            val (title, description) = extractTitleAndDescription(cleanText, timeInfo)

            return AiParseResult(
                title = title,
                description = description,
                scheduledTime = timeInfo?.timestamp
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse reminder text: $text", e)
            // 返回基本的解析结果
            return AiParseResult(
                title = text.take(50), // 限制标题长度
                description = null,
                scheduledTime = null
            )
        }
    }

    /**
     * 提供时间建议
     */
    fun suggestReminderTime(title: String, description: String?): List<AiTimeSuggestion> {
        val suggestions = mutableListOf<AiTimeSuggestion>()
        val now = LocalDateTime.now()
        val currentTime = Instant.now().toEpochMilli()
        val zoneId = ZoneId.systemDefault()
        val zoneOffset = zoneId.rules.getOffset(Instant.now()) // Correct

        // 根据任务类型提供不同的建议
        val taskType = analyzeTaskType(title, description ?: "")

        when (taskType) {
            TaskType.MORNING_ROUTINE -> {
                // 早晨例行任务：建议早上时间
                val morningTime = now.toLocalDate().atTime(8, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(morningTime, "适合早晨例行任务的时间"))

                val alternativeTime = now.toLocalDate().atTime(7, 30).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(alternativeTime, "较早的早晨时间"))
            }
            TaskType.WORK_MEETING -> {
                // 工作会议：建议工作时间
                val meetingTime = now.toLocalDate().atTime(9, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(meetingTime, "标准工作开始时间"))

                val afternoonTime = now.toLocalDate().atTime(14, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(afternoonTime, "下午会议时间"))
            }
            TaskType.PERSONAL_APPOINTMENT -> {
                // 个人约会：建议合适的时间
                val eveningTime = now.toLocalDate().atTime(18, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(eveningTime, "傍晚约会时间"))

                val weekendTime = now.plusDays(if (now.dayOfWeek.value < 6) (6 - now.dayOfWeek.value).toLong() else 0)
                    .toLocalDate().atTime(10, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(weekendTime, "周末上午时间"))
            }
            TaskType.EXERCISE -> {
                // 运动：建议早晨或傍晚
                val exerciseMorning = now.toLocalDate().atTime(6, 30).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(exerciseMorning, "早晨运动时间"))

                val exerciseEvening = now.toLocalDate().atTime(19, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(exerciseEvening, "傍晚运动时间"))
            }
            TaskType.STUDY_LEARNING -> {
                // 学习：建议安静的时间
                val studyTime = now.toLocalDate().atTime(20, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(studyTime, "晚上学习时间"))

                val morningStudy = now.toLocalDate().atTime(8, 0).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(morningStudy, "早晨学习时间"))
            }
            else -> {
                // 默认建议：1小时后、明天同一时间、今天下午
                val oneHourLater = currentTime + (60 * 60 * 1000)
                suggestions.add(AiTimeSuggestion(oneHourLater, "1小时后"))

                val tomorrowSameTime = now.plusDays(1).toInstant(zoneOffset).toEpochMilli()
                suggestions.add(AiTimeSuggestion(tomorrowSameTime, "明天同一时间"))

                val thisAfternoon = now.toLocalDate().atTime(15, 0).toInstant(zoneOffset).toEpochMilli()
                if (thisAfternoon > currentTime) {
                    suggestions.add(AiTimeSuggestion(thisAfternoon, "今天下午"))
                }
            }
        }

        // 过滤掉过去的时间
        return suggestions.filter { it.suggestedTime > currentTime }
    }

    private data class TimeInfo(
        val timestamp: Long?,
        val timeText: String?
    )

    private fun extractTimeInfo(text: String): TimeInfo? {
        // 时间模式匹配
        val patterns = listOf(
            // 明天上午/下午 + 时间
            Pattern.compile("(明天)(上午|下午)?(\\d{1,2})[点时:](\\d{0,2})", Pattern.CASE_INSENSITIVE),
            // 后天上午/下午 + 时间
            Pattern.compile("(后天)(上午|下午)?(\\d{1,2})[点时:](\\d{0,2})", Pattern.CASE_INSENSITIVE),
            // 下周一到周日
            Pattern.compile("(下周|下个星期)(一|二|三|四|五|六|日|天)(上午|下午)?(\\d{1,2})[点时:](\\d{0,2})", Pattern.CASE_INSENSITIVE),
            // 数字 + 点/时/:
            Pattern.compile("(\\d{1,2})[点时:](\\d{0,2})", Pattern.CASE_INSENSITIVE),
            // 相对时间：半小时后、一小时后、两小时后等
            Pattern.compile("(\\d{1,2})(分钟|小时|天)后", Pattern.CASE_INSENSITIVE)
        )

        for (pattern in patterns) {
            val matcher = pattern.matcher(text)
            if (matcher.find()) {
                val timestamp = calculateTimestamp(matcher.group(0), matcher)
                if (timestamp != null) {
                    return TimeInfo(timestamp, matcher.group(0))
                }
            }
        }

        return null
    }

    private fun calculateTimestamp(timeText: String, matcher: java.util.regex.Matcher): Long? {
        val now = LocalDateTime.now()
        val zoneId = ZoneId.systemDefault()
        val zoneOffset = zoneId.rules.getOffset(Instant.now())
        return try {
            when {
                timeText.contains("明天") -> {
                    val tomorrow = now.plusDays(1)
                    val hour = matcher.group(3)?.toIntOrNull() ?: 9
                    val minute = matcher.group(4)?.toIntOrNull() ?: 0
                    val adjustedHour = adjustHourForPeriod(hour, matcher.group(2))
                    tomorrow.withHour(adjustedHour).withMinute(minute).toInstant(zoneOffset).toEpochMilli()
                }
                timeText.contains("后天") -> {
                    val dayAfterTomorrow = now.plusDays(2)
                    val hour = matcher.group(3)?.toIntOrNull() ?: 9
                    val minute = matcher.group(4)?.toIntOrNull() ?: 0
                    val adjustedHour = adjustHourForPeriod(hour, matcher.group(2))
                    dayAfterTomorrow.withHour(adjustedHour).withMinute(minute).toInstant(zoneOffset).toEpochMilli()
                }
                timeText.contains("下周") || timeText.contains("下个星期") -> {
                    val dayOfWeek = parseDayOfWeek(matcher.group(2))
                    val targetDay = now.plusWeeks(1).with(java.time.DayOfWeek.of(dayOfWeek))
                    val hour = matcher.group(4)?.toIntOrNull() ?: 9
                    val minute = matcher.group(5)?.toIntOrNull() ?: 0
                    val adjustedHour = adjustHourForPeriod(hour, matcher.group(3))
                    targetDay.withHour(adjustedHour).withMinute(minute).toInstant(zoneOffset).toEpochMilli()
                }
                timeText.contains("分钟后") || timeText.contains("小时后") || timeText.contains("天后") -> {
                    val amount = matcher.group(1)?.toIntOrNull() ?: 1
                    when {
                        timeText.contains("分钟") -> now.plusMinutes(amount.toLong())
                        timeText.contains("小时") -> now.plusHours(amount.toLong())
                        timeText.contains("天") -> now.plusDays(amount.toLong())
                        else -> now
                    }.toInstant(zoneOffset).toEpochMilli()
                }
                else -> {
                    // 今天的具体时间
                    val hour = matcher.group(1)?.toIntOrNull() ?: 9
                    val minute = matcher.group(2)?.toIntOrNull() ?: 0
                    val todayTime = now.toLocalDate().atTime(hour, minute)
                    val instant = todayTime.toInstant(zoneOffset)
                    // 如果时间已经过去，调整到明天
                    if (instant.toEpochMilli() < Instant.now().toEpochMilli()) {
                        todayTime.plusDays(1).toInstant(zoneOffset).toEpochMilli()
                    } else {
                        instant.toEpochMilli()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to calculate timestamp for: $timeText", e)
            null
        }
    }

    private fun adjustHourForPeriod(hour: Int, period: String?): Int {
        return when (period) {
            "下午", "pm", "PM" -> if (hour in 1..11) hour + 12 else hour
            "上午", "am", "AM" -> if (hour == 12) 0 else hour
            else -> hour
        }
    }

    private fun parseDayOfWeek(dayText: String?): Int {
        return when (dayText) {
            "一", "周一", "星期一" -> 1
            "二", "周二", "星期二" -> 2
            "三", "周三", "星期三" -> 3
            "四", "周四", "星期四" -> 4
            "五", "周五", "星期五" -> 5
            "六", "周六", "星期六" -> 6
            "日", "天", "周日", "星期日" -> 7
            else -> 1
        }
    }

    private fun extractTitleAndDescription(text: String, timeInfo: TimeInfo?): Pair<String, String?> {
        var cleanText = text

        // 移除时间信息
        timeInfo?.timeText?.let { timeText ->
            cleanText = cleanText.replace(timeText, "").trim()
        }

        // 移除常见的时间关键词
        val timeKeywords = listOf(
            "提醒我", "记住", "别忘了", "通知我", "告诉我",
            "明天", "后天", "下周", "下个星期", "上午", "下午",
            "早上", "晚上", "中午", "凌晨"
        )

        for (keyword in timeKeywords) {
            cleanText = cleanText.replace(keyword, "", ignoreCase = true).trim()
        }

        // 分割标题和描述
        val sentences = cleanText.split("[。！？.!?]".toRegex()).filter { it.isNotBlank() }

        val title = if (sentences.isNotEmpty()) {
            sentences.first().trim().take(50) // 限制标题长度
        } else {
            cleanText.take(50)
        }

        val description = if (sentences.size > 1) {
            sentences.drop(1).joinToString("。").trim().takeIf { it.isNotEmpty() }
        } else {
            null
        }

        return Pair(title, description)
    }

    private enum class TaskType {
        MORNING_ROUTINE,
        WORK_MEETING,
        PERSONAL_APPOINTMENT,
        EXERCISE,
        STUDY_LEARNING,
        GENERAL
    }

    private fun analyzeTaskType(title: String, description: String): TaskType {
        val text = "$title $description".lowercase()

        return when {
            text.contains("晨跑") || text.contains("早操") || text.contains("晨间") ||
                    text.contains("早餐") || text.contains("起床") || text.contains("刷牙") -> TaskType.MORNING_ROUTINE

            text.contains("会议") || text.contains("开会") || text.contains("讨论") ||
                    text.contains("汇报") || text.contains("培训") || text.contains("面试") -> TaskType.WORK_MEETING

            text.contains("约会") || text.contains("见面") || text.contains("聚会") ||
                    text.contains("晚餐") -> TaskType.PERSONAL_APPOINTMENT

            text.contains("运动") || text.contains("锻炼") || text.contains("健身") ||
                    text.contains("跑步") || text.contains("游泳") -> TaskType.EXERCISE

            text.contains("学习") || text.contains("看书") || text.contains("复习") ||
                    text.contains("课程") || text.contains("作业") -> TaskType.STUDY_LEARNING

            else -> TaskType.GENERAL
        }
    }
}
