package com.example.smartreminder.domain.usecase;

/**
 * 管理AI配置用例
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\rJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\b2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0018J$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006 "}, d2 = {"Lcom/example/smartreminder/domain/usecase/ManageAiConfigUseCase;", "", "aiConfigRepository", "Lcom/example/smartreminder/domain/repository/AiConfigRepository;", "aiServiceManager", "Lcom/example/smartreminder/data/ai/AiServiceManager;", "(Lcom/example/smartreminder/domain/repository/AiConfigRepository;Lcom/example/smartreminder/data/ai/AiServiceManager;)V", "deleteAiConfig", "Lkotlin/Result;", "", "id", "", "deleteAiConfig-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAiConfig", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/smartreminder/domain/model/AiConfig;", "getAiConfig", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAiConfigs", "", "saveAiConfig", "aiConfig", "saveAiConfig-gIAlu-s", "(Lcom/example/smartreminder/domain/model/AiConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setActiveAiConfig", "setActiveAiConfig-gIAlu-s", "testAiConnection", "", "testAiConnection-gIAlu-s", "updateAiConfig", "updateAiConfig-gIAlu-s", "app_debug"})
public final class ManageAiConfigUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.repository.AiConfigRepository aiConfigRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.AiServiceManager aiServiceManager = null;
    
    @javax.inject.Inject()
    public ManageAiConfigUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.repository.AiConfigRepository aiConfigRepository, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.AiServiceManager aiServiceManager) {
        super();
    }
    
    /**
     * 获取活跃的AI配置
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.smartreminder.domain.model.AiConfig> getActiveAiConfig() {
        return null;
    }
    
    /**
     * 获取所有AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllAiConfigs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.smartreminder.domain.model.AiConfig>> $completion) {
        return null;
    }
    
    /**
     * 获取活跃的AI配置（非Flow版本）
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAiConfig(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.smartreminder.domain.model.AiConfig> $completion) {
        return null;
    }
}