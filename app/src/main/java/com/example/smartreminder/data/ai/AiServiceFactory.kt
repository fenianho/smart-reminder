package com.example.smartreminder.data.ai

import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AI服务工厂类
 */
@Singleton
class AiServiceFactory @Inject constructor() {

    /**
     * 创建AI服务实例（带重试机制）
     */
    fun createAiService(config: AiConfig): AiService {
        val baseService = when (config.provider) {
            AiProvider.OPENAI -> OpenAiService(config)
            AiProvider.DEEPSEEK -> DeepSeekAiService(config)
            AiProvider.CLAUDE -> ClaudeAiService(config)
            AiProvider.GEMINI -> GeminiAiService(config)
            AiProvider.CUSTOM -> CustomAiService(config)
        }

        // 包装重试机制
        return AiServiceWithRetry(baseService, RetryPolicy(config.maxRetries))
    }

    /**
     * 创建AI服务实例（不带重试，用于测试）
     */
    fun createAiServiceWithoutRetry(config: AiConfig): AiService {
        return when (config.provider) {
            AiProvider.OPENAI -> OpenAiService(config)
            AiProvider.DEEPSEEK -> DeepSeekAiService(config)
            AiProvider.CLAUDE -> ClaudeAiService(config)
            AiProvider.GEMINI -> GeminiAiService(config)
            AiProvider.CUSTOM -> CustomAiService(config)
        }
    }
}