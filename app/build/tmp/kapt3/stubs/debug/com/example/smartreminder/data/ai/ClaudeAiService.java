package com.example.smartreminder.data.ai;

/**
 * Claude AI服务实现
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u000e\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00172\u0006\u0010\u000e\u001a\u00020\u0006H\u0082@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00172\u0006\u0010\u000b\u001a\u00020\u0006H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u0019J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J4\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u00172\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010$R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006%"}, d2 = {"Lcom/example/smartreminder/data/ai/ClaudeAiService;", "Lcom/example/smartreminder/data/ai/AiService;", "config", "Lcom/example/smartreminder/domain/model/AiConfig;", "(Lcom/example/smartreminder/domain/model/AiConfig;)V", "TAG", "", "baseUrl", "client", "Lokhttp3/OkHttpClient;", "createParsePrompt", "text", "createRequestBody", "Lokhttp3/RequestBody;", "prompt", "createTimeSuggestionPrompt", "title", "description", "getConfig", "isAvailable", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makeApiCall", "Lkotlin/Result;", "makeApiCall-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseReminderText", "Lcom/example/smartreminder/domain/model/AiParseResult;", "parseReminderText-gIAlu-s", "parseResponse", "responseBody", "parseTimeSuggestions", "", "Lcom/example/smartreminder/domain/model/AiTimeSuggestion;", "suggestReminderTime", "suggestReminderTime-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ClaudeAiService implements com.example.smartreminder.data.ai.AiService {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.model.AiConfig config = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String baseUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "ClaudeAiService";
    
    public ClaudeAiService(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.AiConfig config) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.smartreminder.domain.model.AiConfig getConfig() {
        return null;
    }
    
    private final okhttp3.RequestBody createRequestBody(java.lang.String prompt) {
        return null;
    }
    
    private final java.lang.String createParsePrompt(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String createTimeSuggestionPrompt(java.lang.String title, java.lang.String description) {
        return null;
    }
    
    private final com.example.smartreminder.domain.model.AiParseResult parseResponse(java.lang.String responseBody) {
        return null;
    }
    
    private final java.util.List<com.example.smartreminder.domain.model.AiTimeSuggestion> parseTimeSuggestions(java.lang.String responseBody) {
        return null;
    }
}