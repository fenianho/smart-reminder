package com.example.smartreminder.data.ai

/**
 * AI相关异常类
 */
sealed class AiException(message: String, cause: Throwable? = null) : Exception(message, cause) {

    /**
     * API密钥无效或缺失
     */
    class InvalidApiKeyException(message: String = "API密钥无效或缺失") : AiException(message)

    /**
     * 网络连接错误
     */
    class NetworkException(message: String, cause: Throwable? = null) : AiException(message, cause)

    /**
     * API服务不可用
     */
    class ServiceUnavailableException(message: String, cause: Throwable? = null) : AiException(message, cause)

    /**
     * 请求频率限制
     */
    class RateLimitException(message: String, cause: Throwable? = null) : AiException(message, cause)

    /**
     * API响应解析错误
     */
    class ParseException(message: String, cause: Throwable? = null) : AiException(message, cause)

    /**
     * 配置错误
     */
    class ConfigurationException(message: String) : AiException(message)

    /**
     * 未知错误
     */
    class UnknownException(message: String, cause: Throwable? = null) : AiException(message, cause)
}

/**
 * 从异常创建AiException
 */
fun Exception.toAiException(): AiException {
    return when (this) {
        is retrofit2.HttpException -> {
            val statusCode = response()?.code() ?: -1
            when (statusCode) {
                401 -> AiException.InvalidApiKeyException("API密钥无效")
                429 -> AiException.RateLimitException("请求频率过高，请稍后再试")
                in 500..599 -> AiException.ServiceUnavailableException("AI服务暂时不可用", this)
                else -> AiException.NetworkException("HTTP错误: $statusCode", this)
            }
        }
        is java.net.SocketTimeoutException -> AiException.NetworkException("网络连接超时", this)
        is java.net.UnknownHostException -> AiException.NetworkException("网络连接失败，请检查网络设置", this)
        is java.io.IOException -> AiException.NetworkException("网络IO错误", this)
        is org.json.JSONException -> AiException.ParseException("响应数据解析失败", this)
        else -> AiException.UnknownException("未知错误: ${message}", this)
    }
}