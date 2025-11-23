package com.example.smartreminder.data.ai

import android.util.Log
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiTimeSuggestion
import com.example.smartreminder.domain.repository.AiConfigRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AI服务管理器
 * 负责管理AI服务实例和提供统一的AI功能接口
 */
@Singleton
class AiServiceManager @Inject constructor(
    private val aiConfigRepository: AiConfigRepository,
    private val aiServiceFactory: AiServiceFactory,
    private val nlpParser: NlpParser
) {

    private val TAG = "AiServiceManager"
    private var currentAiService: AiService? = null

    /**
     * 初始化AI服务
     */
    suspend fun initialize(): Boolean {
        return try {
            Log.d(TAG, "Starting AI service initialization")
            val aiConfig = aiConfigRepository.getActiveAiConfig().firstOrNull()
            if (aiConfig != null) {
                Log.i(TAG, "Found active AI config - Provider: ${aiConfig.provider}, Model: ${aiConfig.modelName}")
                currentAiService = aiServiceFactory.createAiService(aiConfig)
                Log.i(TAG, "AI service initialized with provider: ${aiConfig.provider}, model: ${aiConfig.modelName}")
                true
            } else {
                Log.w(TAG, "No active AI config found, using NLP fallback")
                currentAiService = null
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize AI service", e)
            currentAiService = null
            false
        }
    }

    /**
     * 解析提醒文本
     * 优先使用AI服务，如果失败则使用NLP解析器
     */
    suspend fun parseReminderText(text: String): Result<AiParseResult> {
        Log.d(TAG, "Starting to parse reminder text: $text")
        val startTime = System.currentTimeMillis()
        
        return try {
            val aiService = currentAiService
            Log.d(TAG, "Current AI service is ${if (aiService != null) "available" else "null"}")
            
            if (aiService != null) {
                Log.d(TAG, "Checking if AI service is available")
                val isAvailable = aiService.isAvailable()
                Log.d(TAG, "AI service availability check result: $isAvailable")
                
                if (isAvailable) {
                    val config = aiService.getConfig()
                    Log.d(TAG, "Using AI service (${config?.provider}) for parsing")
                    val result = aiService.parseReminderText(text)
                    val duration = System.currentTimeMillis() - startTime
                    
                    if (result.isSuccess) {
                        Log.d(TAG, "AI parsing successful for text: $text. Duration: ${duration}ms")
                        Log.d(TAG, "Parsed result - Title: ${result.getOrNull()?.title}, " +
                                "Description: ${result.getOrNull()?.description}, " +
                                "Time: ${result.getOrNull()?.scheduledTime}")
                        return result
                    } else {
                        val exception = result.exceptionOrNull()
                        if (exception != null && exception is Exception) {
                            val aiException = exception.toAiException()
                            Log.w(TAG, "AI parsing failed with ${aiException::class.simpleName}: ${aiException.message}. Duration: ${duration}ms")
                            // 对于某些错误可以降级到NLP，比如API密钥错误
                            if (aiException is AiException.InvalidApiKeyException) {
                                Log.d(TAG, "API key invalid, falling back to NLP")
                            } else {
                                // 其他错误也降级到NLP
                                Log.d(TAG, "Falling back to NLP due to AI parsing failure")
                            }
                        } else {
                            Log.w(TAG, "AI parsing failed with unknown error. Duration: ${duration}ms")
                        }
                    }
                } else {
                    Log.d(TAG, "AI service is not available, falling back to NLP")
                }
            } else {
                Log.d(TAG, "No AI service configured, using NLP fallback")
            }

            // 使用NLP作为后备方案
            Log.d(TAG, "Using NLP parser for text: $text")
            val nlpResult = nlpParser.parseReminderText(text)
            val duration = System.currentTimeMillis() - startTime
            Log.d(TAG, "NLP parsing successful. Duration: ${duration}ms")
            Log.d(TAG, "NLP parsed result - Title: ${nlpResult.title}, " +
                    "Description: ${nlpResult.description}, " +
                    "Time: ${nlpResult.scheduledTime}")
            Result.success(nlpResult)
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            Log.e(TAG, "Failed to parse reminder text. Duration: ${duration}ms", e)
            val aiException = e.toAiException()
            try {
                // 最后的保险：使用基础NLP解析
                Log.d(TAG, "Using NLP as final fallback after ${aiException::class.simpleName}")
                val nlpResult = nlpParser.parseReminderText(text)
                Log.d(TAG, "Final fallback NLP parsing successful")
                Result.success(nlpResult)
            } catch (fallbackException: Exception) {
                Log.e(TAG, "All parsing methods failed", fallbackException)
                Result.failure(aiException) // 返回原始AI异常
            }
        }
    }

    /**
     * 提供智能时间建议
     * 优先使用AI服务，如果失败则使用NLP解析器
     */
    suspend fun suggestReminderTime(title: String, description: String?): Result<List<AiTimeSuggestion>> {
        Log.d(TAG, "Starting to suggest reminder time for title: $title")
        val startTime = System.currentTimeMillis()
        
        return try {
            val aiService = currentAiService
            Log.d(TAG, "Current AI service is ${if (aiService != null) "available" else "null"} for time suggestion")
            
            if (aiService != null) {
                Log.d(TAG, "Checking if AI service is available for time suggestion")
                val isAvailable = aiService.isAvailable()
                Log.d(TAG, "AI service availability check result for time suggestion: $isAvailable")
                
                if (isAvailable) {
                    val config = aiService.getConfig()
                    Log.d(TAG, "Using AI service (${config?.provider}) for time suggestion")
                    val result = aiService.suggestReminderTime(title, description)
                    val duration = System.currentTimeMillis() - startTime
                    
                    if (result.isSuccess && result.getOrNull()?.isNotEmpty() == true) {
                        Log.d(TAG, "AI time suggestion successful for: $title. Duration: ${duration}ms")
                        result.getOrNull()?.forEachIndexed { index, suggestion ->
                            Log.d(TAG, "Suggestion $index - Time: ${suggestion.suggestedTime}, Reason: ${suggestion.reason}")
                        }
                        return result
                    } else {
                        val exception = result.exceptionOrNull()
                        if (exception != null && exception is Exception) {
                            val aiException = exception.toAiException()
                            Log.w(TAG, "AI time suggestion failed with ${aiException::class.simpleName}: ${aiException.message}. Duration: ${duration}ms")
                        } else {
                            Log.w(TAG, "AI time suggestion failed with unknown error. Duration: ${duration}ms")
                        }
                    }
                } else {
                    Log.d(TAG, "AI service is not available for time suggestion, falling back to NLP")
                }
            } else {
                Log.d(TAG, "No AI service configured for time suggestion, using NLP fallback")
            }

            // 使用NLP作为后备方案
            Log.d(TAG, "Using NLP for time suggestions: $title")
            val nlpResult = nlpParser.suggestReminderTime(title, description)
            val duration = System.currentTimeMillis() - startTime
            Log.d(TAG, "NLP time suggestion successful. Duration: ${duration}ms")
            nlpResult.forEachIndexed { index, suggestion ->
                Log.d(TAG, "NLP Suggestion $index - Time: ${suggestion.suggestedTime}, Reason: ${suggestion.reason}")
            }
            Result.success(nlpResult)
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            Log.e(TAG, "Failed to suggest reminder time. Duration: ${duration}ms", e)
            val aiException = e.toAiException()
            try {
                // 最后的保险：使用基础NLP建议
                Log.d(TAG, "Using NLP as final fallback after ${aiException::class.simpleName}")
                val nlpResult = nlpParser.suggestReminderTime(title, description)
                Log.d(TAG, "Final fallback NLP time suggestion successful")
                Result.success(nlpResult)
            } catch (fallbackException: Exception) {
                Log.e(TAG, "All time suggestion methods failed", fallbackException)
                Result.failure(aiException) // 返回原始AI异常
            }
        }
    }

    /**
     * 检查AI服务是否可用
     */
    suspend fun isAiServiceAvailable(): Boolean {
        Log.d(TAG, "Checking AI service availability")
        return try {
            val aiService = currentAiService
            Log.d(TAG, "Current AI service is ${if (aiService != null) "available" else "null"} for availability check")
            
            if (aiService != null) {
                val isAvailable = aiService.isAvailable()
                Log.d(TAG, "AI service availability check result: $isAvailable")
                isAvailable
            } else {
                Log.d(TAG, "No AI service configured, returning false for availability")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to check AI service availability", e)
            false
        }
    }

    /**
     * 获取当前AI配置
     */
    fun getCurrentAiConfig(): AiConfig? {
        val config = currentAiService?.getConfig()
        if (config != null) {
            Log.d(TAG, "Current AI config - Provider: ${config.provider}, Model: ${config.modelName}")
        } else {
            Log.d(TAG, "No current AI config available")
        }
        return config
    }

    /**
     * 更新AI服务配置
     */
    suspend fun updateAiService(config: AiConfig) {
        try {
            Log.i(TAG, "Updating AI service with config - Provider: ${config.provider}, Model: ${config.modelName}")
            aiConfigRepository.saveAiConfig(config)
            if (config.isEnabled) {
                aiConfigRepository.setActiveAiConfig(config.id)
                currentAiService = aiServiceFactory.createAiService(config)
                Log.i(TAG, "AI service updated to provider: ${config.provider}, model: ${config.modelName}")
            } else {
                currentAiService = null
                Log.i(TAG, "AI service disabled")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update AI service", e)
            throw e
        }
    }

    /**
     * 重置AI服务
     */
    suspend fun resetAiService() {
        try {
            Log.d(TAG, "Resetting AI service")
            currentAiService = null
            val result = initialize()
            Log.d(TAG, "AI service reset completed. Initialization result: $result")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to reset AI service", e)
        }
    }
}