package com.example.smartreminder.domain.model

/**
 * AI配置领域模型
 */
data class AiConfig(
    val id: Long = 0,
    val name: String = "",
    val provider: AiProvider,
    val apiKey: String,
    val modelName: String,
    val isEnabled: Boolean = true,
    val baseUrl: String? = null,
    val timeoutMs: Int = 30000,
    val maxRetries: Int = 3
)

/**
 * AI提供商枚举
 */
enum class AiProvider {
    OPENAI,
    DEEPSEEK,
    CLAUDE,
    GEMINI,
    CUSTOM
}

/**
 * AI解析结果
 */
data class AiParseResult(
    val title: String,
    val description: String?,
    val scheduledTime: Long?, // Unix timestamp in milliseconds
    val repeatType: RepeatType = RepeatType.NONE, // 添加重复类型字段
    val monthDays: Set<Int> = emptySet(), // 添加每月重复的具体日期字段（按日期重复）
    val weekDays: Set<Int> = emptySet(), // 添加每周重复的具体日期字段
    val monthlyWeek: Int? = null, // 添加每月按星期重复的周次字段（-1表示最后一个，1-4表示第几个）
    val monthlyWeekDays: Set<Int> = emptySet() // 添加每月按星期重复的星期几字段
)

/**
 * AI时间建议结果
 */
data class AiTimeSuggestion(
    val suggestedTime: Long, // Unix timestamp in milliseconds
    val reason: String
)