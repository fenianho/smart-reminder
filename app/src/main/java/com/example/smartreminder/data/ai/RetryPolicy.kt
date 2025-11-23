package com.example.smartreminder.data.ai

import android.util.Log
import kotlinx.coroutines.delay
import kotlin.math.pow

/**
 * 重试策略
 */
class RetryPolicy(
    private val maxRetries: Int = 3,
    private val initialDelayMs: Long = 1000,
    private val maxDelayMs: Long = 30000,
    private val backoffMultiplier: Double = 2.0
) {

    companion object {
        private const val TAG = "RetryPolicy"
    }

    /**
     * 执行带重试的操作
     */
    suspend fun <T> executeWithRetry(
        operation: suspend () -> Result<T>
    ): Result<T> {
        var lastException: Exception? = null

        for (attempt in 0..maxRetries) {
            try {
                val result = operation()
                if (result.isSuccess) {
                    return result
                } else {
                    lastException = Exception(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                lastException = e
                Log.w(TAG, "Attempt ${attempt + 1} failed: ${e.message}")
            }

            if (attempt < maxRetries) {
                val delayMs = calculateDelay(attempt)
                Log.d(TAG, "Retrying in ${delayMs}ms (attempt ${attempt + 1}/${maxRetries + 1})")
                delay(delayMs)
            }
        }

        return Result.failure(lastException ?: Exception("All retry attempts failed"))
    }

    /**
     * 计算延迟时间（指数退避）
     */
    private fun calculateDelay(attempt: Int): Long {
        val delay = initialDelayMs * backoffMultiplier.pow(attempt.toDouble())
        return delay.toLong().coerceAtMost(maxDelayMs)
    }

    /**
     * 检查是否应该重试给定的异常
     */
    fun shouldRetry(exception: Exception): Boolean {
        return when (exception) {
            // 网络错误
            is java.net.SocketTimeoutException,
            is java.net.UnknownHostException,
            is java.io.IOException -> true

            // HTTP 5xx 服务器错误
            is retrofit2.HttpException -> {
                val code = exception.code()
                code in 500..599
            }

            // 其他情况不重试
            else -> false
        }
    }
}

/**
 * AI服务的重试包装器
 */
class AiServiceWithRetry(
    private val aiService: AiService,
    private val retryPolicy: RetryPolicy = RetryPolicy()
) : AiService by aiService {

    override suspend fun parseReminderText(text: String): Result<com.example.smartreminder.domain.model.AiParseResult> {
        return retryPolicy.executeWithRetry {
            aiService.parseReminderText(text)
        }
    }

    override suspend fun suggestReminderTime(
        title: String,
        description: String?
    ): Result<List<com.example.smartreminder.domain.model.AiTimeSuggestion>> {
        return retryPolicy.executeWithRetry {
            aiService.suggestReminderTime(title, description)
        }
    }

    override suspend fun isAvailable(): Boolean {
        // 可用性检查不需要重试
        return try {
            aiService.isAvailable()
        } catch (e: Exception) {
            false
        }
    }

    override fun getConfig(): com.example.smartreminder.domain.model.AiConfig? {
        return aiService.getConfig()
    }
}