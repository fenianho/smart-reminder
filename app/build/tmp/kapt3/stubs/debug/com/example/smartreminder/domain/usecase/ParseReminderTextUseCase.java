package com.example.smartreminder.domain.usecase;

/**
 * 解析提醒文本用例
 * 使用AI解析用户输入的自然语言文本，提取提醒信息
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\f"}, d2 = {"Lcom/example/smartreminder/domain/usecase/ParseReminderTextUseCase;", "", "aiServiceManager", "Lcom/example/smartreminder/data/ai/AiServiceManager;", "(Lcom/example/smartreminder/data/ai/AiServiceManager;)V", "invoke", "Lkotlin/Result;", "Lcom/example/smartreminder/domain/model/AiParseResult;", "text", "", "invoke-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ParseReminderTextUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.ai.AiServiceManager aiServiceManager = null;
    
    @javax.inject.Inject()
    public ParseReminderTextUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.ai.AiServiceManager aiServiceManager) {
        super();
    }
}