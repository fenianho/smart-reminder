package com.example.smartreminder.data.ai;

/**
 * AI服务的重试包装器
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u000e\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J4\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\f2\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000fH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0019"}, d2 = {"Lcom/example/smartreminder/data/ai/AiServiceWithRetry;", "Lcom/example/smartreminder/data/ai/AiService;", "aiService", "retryPolicy", "Lcom/example/smartreminder/data/ai/RetryPolicy;", "(Lcom/example/smartreminder/data/ai/AiService;Lcom/example/smartreminder/data/ai/RetryPolicy;)V", "getConfig", "Lcom/example/smartreminder/domain/model/AiConfig;", "isAvailable", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseReminderText", "Lkotlin/Result;", "Lcom/example/smartreminder/domain/model/AiParseResult;", "text", "", "parseReminderText-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suggestReminderTime", "", "Lcom/example/smartreminder/domain/model/AiTimeSuggestion;", "title", "description", "suggestReminderTime-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AiServiceWithRetry implements com.example.smartreminder.data.ai.AiService {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.AiService aiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.RetryPolicy retryPolicy = null;
    
    public AiServiceWithRetry(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.AiService aiService, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.RetryPolicy retryPolicy) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.example.smartreminder.domain.model.AiConfig getConfig() {
        return null;
    }
}