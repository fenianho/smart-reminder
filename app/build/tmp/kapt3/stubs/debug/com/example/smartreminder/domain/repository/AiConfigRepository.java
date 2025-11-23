package com.example.smartreminder.domain.repository;

/**
 * AI配置仓库接口
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\bH&J\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/example/smartreminder/domain/repository/AiConfigRepository;", "", "deleteAiConfig", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAiConfig", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/smartreminder/domain/model/AiConfig;", "getAiConfigById", "getAllAiConfigs", "", "saveAiConfig", "aiConfig", "(Lcom/example/smartreminder/domain/model/AiConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setActiveAiConfig", "updateAiConfig", "app_debug"})
public abstract interface AiConfigRepository {
    
    /**
     * 获取活跃的AI配置
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.smartreminder.domain.model.AiConfig> getActiveAiConfig();
    
    /**
     * 获取所有AI配置
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.AiConfig>> getAllAiConfigs();
    
    /**
     * 根据ID获取AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAiConfigById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.smartreminder.domain.model.AiConfig> $completion);
    
    /**
     * 保存AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveAiConfig(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig aiConfig, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * 更新AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAiConfig(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig aiConfig, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 删除AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAiConfig(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 设置活跃的AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setActiveAiConfig(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}