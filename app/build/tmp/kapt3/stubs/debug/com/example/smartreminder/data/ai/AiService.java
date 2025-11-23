package com.example.smartreminder.data.ai;

/**
 * AI服务接口
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u000e\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ4\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\b2\u0006\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0015"}, d2 = {"Lcom/example/smartreminder/data/ai/AiService;", "", "getConfig", "Lcom/example/smartreminder/domain/model/AiConfig;", "isAvailable", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseReminderText", "Lkotlin/Result;", "Lcom/example/smartreminder/domain/model/AiParseResult;", "text", "", "parseReminderText-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suggestReminderTime", "", "Lcom/example/smartreminder/domain/model/AiTimeSuggestion;", "title", "description", "suggestReminderTime-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AiService {
    
    /**
     * 检查AI服务是否可用
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    /**
     * 获取当前配置
     */
    @org.jetbrains.annotations.Nullable()
    public abstract com.example.smartreminder.domain.model.AiConfig getConfig();
}