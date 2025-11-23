package com.example.smartreminder.data.ai

import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiTimeSuggestion
import com.example.smartreminder.domain.model.AiConfig

/**
 * AI服务接口
 */
interface AiService {

    /**
     * 解析提醒文本，提取标题、描述和时间
     */
    suspend fun parseReminderText(text: String): Result<AiParseResult>

    /**
     * 提供智能时间建议
     */
    suspend fun suggestReminderTime(title: String, description: String?): Result<List<AiTimeSuggestion>>

    /**
     * 检查AI服务是否可用
     */
    suspend fun isAvailable(): Boolean

    /**
     * 获取当前配置
     */
    fun getConfig(): AiConfig?
}