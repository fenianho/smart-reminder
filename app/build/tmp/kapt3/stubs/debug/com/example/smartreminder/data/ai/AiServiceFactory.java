package com.example.smartreminder.data.ai;

/**
 * AI服务工厂类
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lcom/example/smartreminder/data/ai/AiServiceFactory;", "", "()V", "createAiService", "Lcom/example/smartreminder/data/ai/AiService;", "config", "Lcom/example/smartreminder/domain/model/AiConfig;", "createAiServiceWithoutRetry", "app_debug"})
public final class AiServiceFactory {
    
    @javax.inject.Inject()
    public AiServiceFactory() {
        super();
    }
    
    /**
     * 创建AI服务实例（带重试机制）
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.data.ai.AiService createAiService(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig config) {
        return null;
    }
    
    /**
     * 创建AI服务实例（不带重试，用于测试）
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.data.ai.AiService createAiServiceWithoutRetry(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig config) {
        return null;
    }
}