package com.example.smartreminder.data.ai;

/**
 * 重试策略
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0003H\u0002JF\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\u0004\b\u0000\u0010\u000e2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"Lcom/example/smartreminder/data/ai/RetryPolicy;", "", "maxRetries", "", "initialDelayMs", "", "maxDelayMs", "backoffMultiplier", "", "(IJJD)V", "calculateDelay", "attempt", "executeWithRetry", "Lkotlin/Result;", "T", "operation", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "executeWithRetry-gIAlu-s", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldRetry", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Companion", "app_debug"})
public final class RetryPolicy {
    private final int maxRetries = 0;
    private final long initialDelayMs = 0L;
    private final long maxDelayMs = 0L;
    private final double backoffMultiplier = 0.0;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "RetryPolicy";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartreminder.data.ai.RetryPolicy.Companion Companion = null;
    
    public RetryPolicy(int maxRetries, long initialDelayMs, long maxDelayMs, double backoffMultiplier) {
        super();
    }
    
    /**
     * 计算延迟时间（指数退避）
     */
    private final long calculateDelay(int attempt) {
        return 0L;
    }
    
    /**
     * 检查是否应该重试给定的异常
     */
    public final boolean shouldRetry(@org.jetbrains.annotations.NotNull()
    java.lang.Exception exception) {
        return false;
    }
    
    public RetryPolicy() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/smartreminder/data/ai/RetryPolicy$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}