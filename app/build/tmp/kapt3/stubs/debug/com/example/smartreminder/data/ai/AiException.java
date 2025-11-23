package com.example.smartreminder.data.ai;

/**
 * AI相关异常类
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0007\b\t\n\u000b\f\r\u000eB\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u0082\u0001\u0007\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "ConfigurationException", "InvalidApiKeyException", "NetworkException", "ParseException", "RateLimitException", "ServiceUnavailableException", "UnknownException", "Lcom/example/smartreminder/data/ai/AiException$ConfigurationException;", "Lcom/example/smartreminder/data/ai/AiException$InvalidApiKeyException;", "Lcom/example/smartreminder/data/ai/AiException$NetworkException;", "Lcom/example/smartreminder/data/ai/AiException$ParseException;", "Lcom/example/smartreminder/data/ai/AiException$RateLimitException;", "Lcom/example/smartreminder/data/ai/AiException$ServiceUnavailableException;", "Lcom/example/smartreminder/data/ai/AiException$UnknownException;", "app_debug"})
public abstract class AiException extends java.lang.Exception {
    
    private AiException(java.lang.String message, java.lang.Throwable cause) {
        super();
    }
    
    /**
     * 配置错误
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$ConfigurationException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "(Ljava/lang/String;)V", "app_debug"})
    public static final class ConfigurationException extends com.example.smartreminder.data.ai.AiException {
        
        public ConfigurationException(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
    }
    
    /**
     * API密钥无效或缺失
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$InvalidApiKeyException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "(Ljava/lang/String;)V", "app_debug"})
    public static final class InvalidApiKeyException extends com.example.smartreminder.data.ai.AiException {
        
        public InvalidApiKeyException(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
        
        public InvalidApiKeyException() {
        }
    }
    
    /**
     * 网络连接错误
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$NetworkException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_debug"})
    public static final class NetworkException extends com.example.smartreminder.data.ai.AiException {
        
        public NetworkException(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
        }
    }
    
    /**
     * API响应解析错误
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$ParseException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_debug"})
    public static final class ParseException extends com.example.smartreminder.data.ai.AiException {
        
        public ParseException(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
        }
    }
    
    /**
     * 请求频率限制
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$RateLimitException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_debug"})
    public static final class RateLimitException extends com.example.smartreminder.data.ai.AiException {
        
        public RateLimitException(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
        }
    }
    
    /**
     * API服务不可用
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$ServiceUnavailableException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_debug"})
    public static final class ServiceUnavailableException extends com.example.smartreminder.data.ai.AiException {
        
        public ServiceUnavailableException(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
        }
    }
    
    /**
     * 未知错误
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/smartreminder/data/ai/AiException$UnknownException;", "Lcom/example/smartreminder/data/ai/AiException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "app_debug"})
    public static final class UnknownException extends com.example.smartreminder.data.ai.AiException {
        
        public UnknownException(@org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
        }
    }
}