package com.example.smartreminder.data.ai

import android.util.Log
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiProvider
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
    private var activeConfig: AiConfig? = null

    init {
        // 初始化时加载活跃配置
        initialize()
    }

    /**
     * 初始化AI服务管理器
     * 从仓库获取活跃的AI配置并创建对应的AI服务实例
     */
    fun initialize() {
        try {
            Log.d(TAG, "Initializing AI service manager")
            // 在实际应用中，这里应该从仓库获取活跃配置
            // 由于这是一个简化示例，我们暂时不实现具体的初始化逻辑
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize AI service manager", e)
        }
    }

    /**
     * 解析提醒文本
     * 首先尝试使用AI服务进行解析，如果失败则使用本地NLP解析
     */
    suspend fun parseReminderText(text: String): Result<AiParseResult> {
        Log.d(TAG, "Starting to parse reminder text: $text")
        val startTime = System.currentTimeMillis()

        try {
            // 尝试使用AI服务解析
            val aiService = getCurrentAiService()
            if (aiService != null) {
                try {
                    Log.d(TAG, "Trying AI service for parsing")
                    val result = aiService.parseReminderText(text)
                    
                    if (result.isSuccess) {
                        val parsedResult = result.getOrThrow()
                        val duration = System.currentTimeMillis() - startTime
                        Log.d(TAG, "AI parsing successful. Duration: ${duration}ms")
                        Log.d(TAG, "Parsed result - Title: ${parsedResult.title}, " +
                                "Description: ${parsedResult.description}, " +
                                "Time: ${parsedResult.scheduledTime}")
                        return Result.success(parsedResult)
                    } else {
                        Log.w(TAG, "AI service parsing failed: ${result.exceptionOrNull()?.message}")
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "AI service threw exception during parsing", e)
                }
            }

            // 使用NLP作为后备方案
            Log.d(TAG, "Using NLP parser for text: $text")
            val nlpResult = nlpParser.parse(text)
            val duration = System.currentTimeMillis() - startTime
            Log.d(TAG, "NLP parsing successful. Duration: ${duration}ms")
            Log.d(TAG, "NLP parsed result - Title: ${nlpResult.title}, " +
                    "Description: ${nlpResult.description}, " +
                    "Time: ${nlpResult.scheduledTime}")
            return Result.success(nlpResult)
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            Log.e(TAG, "Failed to parse reminder text. Duration: ${duration}ms", e)
            try {
                // 最后的保险：使用基础NLP解析
                Log.d(TAG, "Using NLP as final fallback")
                val nlpResult = nlpParser.parse(text)
                Log.d(TAG, "Final fallback NLP parsing successful")
                return Result.success(nlpResult)
            } catch (fallbackException: Exception) {
                Log.e(TAG, "Final fallback NLP parsing also failed", fallbackException)
                return Result.failure(Exception("所有AI服务和本地解析都失败了", e))
            }
        }
    }

    /**
     * 建议提醒时间
     * 根据提醒的标题和描述，使用AI建议合适的时间
     */
    suspend fun suggestReminderTime(title: String, description: String?): Result<List<AiTimeSuggestion>> {
        Log.d(TAG, "Starting to suggest reminder time. Title: $title")
        val startTime = System.currentTimeMillis()

        try {
            // 尝试使用AI服务进行时间建议
            val aiService = getCurrentAiService()
            if (aiService != null) {
                try {
                    Log.d(TAG, "Trying AI service for time suggestion")
                    val result = aiService.suggestReminderTime(title, description)
                    
                    if (result.isSuccess) {
                        val suggestions = result.getOrThrow()
                        val duration = System.currentTimeMillis() - startTime
                        Log.d(TAG, "AI time suggestion successful. Duration: ${duration}ms")
                        suggestions.forEachIndexed { index, suggestion -> 
                            Log.d(TAG, "Suggestion $index - Time: ${suggestion.suggestedTime}, Reason: ${suggestion.reason}")
                        }
                        return Result.success(suggestions)
                    } else {
                        Log.w(TAG, "AI service time suggestion failed: ${result.exceptionOrNull()?.message}")
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "AI service threw exception during time suggestion", e)
                }
            }

            // 使用NLP作为后备方案
            Log.d(TAG, "Using NLP for time suggestions: $title")
            // 注意：当前NLP实现不支持时间建议功能，此处仅为占位
            val nlpResult = emptyList<AiTimeSuggestion>() 
            val duration = System.currentTimeMillis() - startTime
            Log.d(TAG, "NLP time suggestion successful. Duration: ${duration}ms")
            return Result.success(nlpResult)
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            Log.e(TAG, "Failed to suggest reminder time. Duration: ${duration}ms", e)
            try {
                // 最后的保险：使用基础NLP建议
                Log.d(TAG, "Using NLP as final fallback")
                // 注意：当前NLP实现不支持时间建议功能，此处仅为占位
                val nlpResult = emptyList<AiTimeSuggestion>()
                Log.d(TAG, "Final fallback NLP time suggestion successful")
                return Result.success(nlpResult)
            } catch (fallbackException: Exception) {
                Log.e(TAG, "Final fallback NLP time suggestion also failed", fallbackException)
                return Result.failure(Exception("所有AI服务和本地解析都失败了", e))
            }
        }
    }

    /**
     * 检查AI服务是否可用
     */
    suspend fun isAiServiceAvailable(): Boolean {
        val aiService = getCurrentAiService()
        return aiService?.isAvailable() ?: false
    }

    /**
     * 更新AI服务
     * 当AI配置发生变化时调用此方法更新当前的AI服务实例
     */
    fun updateAiService(aiConfig: AiConfig) {
        Log.d(TAG, "Updating AI service with config: ${aiConfig.name}")
        try {
            currentAiService = aiServiceFactory.createAiService(aiConfig)
            activeConfig = aiConfig
            Log.d(TAG, "AI service updated successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update AI service", e)
        }
    }

    /**
     * 重置AI服务
     * 当需要重新初始化AI服务时调用此方法
     */
    fun resetAiService() {
        Log.d(TAG, "Resetting AI service")
        currentAiService = null
        activeConfig = null
        initialize()
    }

    /**
     * 获取当前的AI服务实例
     * 如果当前没有AI服务实例，则尝试从活跃配置创建一个
     */
    private suspend fun getCurrentAiService(): AiService? {
        if (currentAiService == null) {
            Log.d(TAG, "Current AI service is null, trying to create from active config")
            val config = aiConfigRepository.getActiveAiConfig().firstOrNull()
            if (config != null) {
                try {
                    Log.d(TAG, "Creating AI service from config: ${config.name}")
                    currentAiService = aiServiceFactory.createAiService(config)
                    activeConfig = config
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to create AI service from config", e)
                }
            } else {
                Log.d(TAG, "No active AI config found")
            }
        }
        return currentAiService
    }
}