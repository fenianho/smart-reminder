package com.example.smartreminder.data.ai;

/**
 * AI服务管理器
 * 负责管理AI服务实例和提供统一的AI功能接口
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u0011J4\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u00142\u0006\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010!J\u0016\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010$R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006%"}, d2 = {"Lcom/example/smartreminder/data/ai/AiServiceManager;", "", "aiConfigRepository", "Lcom/example/smartreminder/domain/repository/AiConfigRepository;", "aiServiceFactory", "Lcom/example/smartreminder/data/ai/AiServiceFactory;", "nlpParser", "Lcom/example/smartreminder/data/ai/NlpParser;", "(Lcom/example/smartreminder/domain/repository/AiConfigRepository;Lcom/example/smartreminder/data/ai/AiServiceFactory;Lcom/example/smartreminder/data/ai/NlpParser;)V", "TAG", "", "currentAiService", "Lcom/example/smartreminder/data/ai/AiService;", "getCurrentAiConfig", "Lcom/example/smartreminder/domain/model/AiConfig;", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAiServiceAvailable", "parseReminderText", "Lkotlin/Result;", "Lcom/example/smartreminder/domain/model/AiParseResult;", "text", "parseReminderText-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetAiService", "", "suggestReminderTime", "", "Lcom/example/smartreminder/domain/model/AiTimeSuggestion;", "title", "description", "suggestReminderTime-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAiService", "config", "(Lcom/example/smartreminder/domain/model/AiConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AiServiceManager {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.repository.AiConfigRepository aiConfigRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.AiServiceFactory aiServiceFactory = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.NlpParser nlpParser = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "AiServiceManager";
    @org.jetbrains.annotations.Nullable()
    private com.example.smartreminder.data.ai.AiService currentAiService;
    
    @javax.inject.Inject()
    public AiServiceManager(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.repository.AiConfigRepository aiConfigRepository, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.AiServiceFactory aiServiceFactory, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.NlpParser nlpParser) {
        super();
    }
    
    /**
     * 初始化AI服务
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * 检查AI服务是否可用
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isAiServiceAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * 获取当前AI配置
     */
    @org.jetbrains.annotations.Nullable()
    public final com.example.smartreminder.domain.model.AiConfig getCurrentAiConfig() {
        return null;
    }
    
    /**
     * 更新AI服务配置
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAiService(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * 重置AI服务
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resetAiService(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}