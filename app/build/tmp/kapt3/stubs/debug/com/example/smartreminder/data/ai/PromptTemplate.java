package com.example.smartreminder.data.ai;

/**
 * AI提示词模板
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004\u00a8\u0006\t"}, d2 = {"Lcom/example/smartreminder/data/ai/PromptTemplate;", "", "()V", "createParsePrompt", "", "text", "createTimeSuggestionPrompt", "title", "description", "app_debug"})
public final class PromptTemplate {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartreminder.data.ai.PromptTemplate INSTANCE = null;
    
    private PromptTemplate() {
        super();
    }
    
    /**
     * 创建解析提醒文本的提示词
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String createParsePrompt(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    /**
     * 创建时间建议的提示词
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String createTimeSuggestionPrompt(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
        return null;
    }
}