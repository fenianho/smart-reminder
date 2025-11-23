package com.example.smartreminder.domain.usecase

import com.example.smartreminder.data.ai.AiServiceManager
import com.example.smartreminder.domain.model.AiParseResult
import javax.inject.Inject

/**
 * 解析提醒文本用例
 * 使用AI解析用户输入的自然语言文本，提取提醒信息
 */
class ParseReminderTextUseCase @Inject constructor(
    private val aiServiceManager: AiServiceManager
) {

    /**
     * 解析提醒文本
     *
     * @param text 用户输入的自然语言文本
     * @return 解析结果，包含标题、描述和时间信息
     */
    suspend operator fun invoke(text: String): Result<AiParseResult> {
        return try {
            if (text.isBlank()) {
                return Result.failure(IllegalArgumentException("提醒文本不能为空"))
            }

            aiServiceManager.parseReminderText(text.trim())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}