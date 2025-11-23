package com.example.smartreminder.domain.model;

/**
 * AI解析结果
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003Jv\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0001\u00a2\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020\u000bH\u00d6\u0001J\t\u0010-\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013\u00a8\u0006."}, d2 = {"Lcom/example/smartreminder/domain/model/AiParseResult;", "", "title", "", "description", "scheduledTime", "", "repeatType", "Lcom/example/smartreminder/domain/model/RepeatType;", "monthDays", "", "", "weekDays", "monthlyWeek", "monthlyWeekDays", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/example/smartreminder/domain/model/RepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/lang/Integer;Ljava/util/Set;)V", "getDescription", "()Ljava/lang/String;", "getMonthDays", "()Ljava/util/Set;", "getMonthlyWeek", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMonthlyWeekDays", "getRepeatType", "()Lcom/example/smartreminder/domain/model/RepeatType;", "getScheduledTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTitle", "getWeekDays", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/example/smartreminder/domain/model/RepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/lang/Integer;Ljava/util/Set;)Lcom/example/smartreminder/domain/model/AiParseResult;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class AiParseResult {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long scheduledTime = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.model.RepeatType repeatType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> monthDays = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> weekDays = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer monthlyWeek = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> monthlyWeekDays = null;
    
    public AiParseResult(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.Long scheduledTime, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.RepeatType repeatType, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> monthDays, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> weekDays, @org.jetbrains.annotations.Nullable()
    java.lang.Integer monthlyWeek, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> monthlyWeekDays) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getScheduledTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.RepeatType getRepeatType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> getMonthDays() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> getWeekDays() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMonthlyWeek() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> getMonthlyWeekDays() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.RepeatType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.AiParseResult copy(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.Long scheduledTime, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.RepeatType repeatType, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> monthDays, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> weekDays, @org.jetbrains.annotations.Nullable()
    java.lang.Integer monthlyWeek, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> monthlyWeekDays) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}