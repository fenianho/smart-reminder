package com.example.smartreminder.data.repository;

/**
 * AI配置仓库实现
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016J\u0018\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\u000bH\u0016J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/smartreminder/data/repository/AiConfigRepositoryImpl;", "Lcom/example/smartreminder/domain/repository/AiConfigRepository;", "aiConfigDao", "Lcom/example/smartreminder/data/local/dao/AiConfigDao;", "(Lcom/example/smartreminder/data/local/dao/AiConfigDao;)V", "deleteAiConfig", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAiConfig", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/smartreminder/domain/model/AiConfig;", "getAiConfigById", "getAllAiConfigs", "", "saveAiConfig", "aiConfig", "(Lcom/example/smartreminder/domain/model/AiConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setActiveAiConfig", "updateAiConfig", "app_debug"})
public final class AiConfigRepositoryImpl implements com.example.smartreminder.domain.repository.AiConfigRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.local.dao.AiConfigDao aiConfigDao = null;
    
    @javax.inject.Inject()
    public AiConfigRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.local.dao.AiConfigDao aiConfigDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.smartreminder.domain.model.AiConfig> getActiveAiConfig() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.AiConfig>> getAllAiConfigs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAiConfigById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.smartreminder.domain.model.AiConfig> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveAiConfig(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig aiConfig, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateAiConfig(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig aiConfig, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteAiConfig(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setActiveAiConfig(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}