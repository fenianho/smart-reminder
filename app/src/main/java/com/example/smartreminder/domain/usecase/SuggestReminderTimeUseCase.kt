package com.example.smartreminder.domain.usecase

import com.example.smartreminder.data.ai.AiServiceManager
import com.example.smartreminder.domain.model.AiTimeSuggestion
import javax.inject.Inject

/**
 * 建议提醒时间用例
 * 使用AI为提醒提供智能的时间建议
 */
class SuggestReminderTimeUseCase @Inject constructor(
    private val aiServiceManager: AiServiceManager
) {

    /**
     * 建议提醒时间
     *
     * @param title 提醒标题
     * @param description 提醒描述（可选）
     * @return 时间建议列表
     */
    suspend operator fun invoke(title: String, description: String?): Result<List<AiTimeSuggestion>> {
        return try {
            if (title.isBlank()) {
                return Result.failure(IllegalArgumentException("提醒标题不能为空"))
            }

            val result = aiServiceManager.suggestReminderTime(title.trim(), description?.trim())

            // 如果没有建议，提供默认建议
            if (result.isSuccess && result.getOrNull().isNullOrEmpty()) {
                // 返回一些基本的默认建议
                val defaultSuggestions = createDefaultSuggestions()
                Result.success(defaultSuggestions)
            } else {
                result
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 创建默认的时间建议
     */
    private fun createDefaultSuggestions(): List<AiTimeSuggestion> {
        val now = System.currentTimeMillis()
        return listOf(
            AiTimeSuggestion(now + 60 * 60 * 1000, "1小时后"), // 1小时后
            AiTimeSuggestion(now + 24 * 60 * 60 * 1000, "明天同一时间"), // 明天
            AiTimeSuggestion(now + 2 * 24 * 60 * 60 * 1000, "后天同一时间") // 后天
        )
    }
}