package com.example.smartreminder.data.ai;

/**
 * 本地NLP解析器
 * 用于解析简单的自然语言提醒文本
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0017\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/smartreminder/data/ai/NlpParser;", "", "()V", "TAG", "", "extractDate", "Ljava/time/LocalDate;", "text", "extractMonthDays", "", "", "extractMonthlyWeek", "(Ljava/lang/String;)Ljava/lang/Integer;", "extractMonthlyWeekDays", "extractRepeatInfo", "Lcom/example/smartreminder/data/ai/NlpParser$RepeatInfo;", "extractTime", "Ljava/time/LocalTime;", "extractTitle", "extractWeekDays", "parse", "Lcom/example/smartreminder/domain/model/AiParseResult;", "RepeatInfo", "app_debug"})
public final class NlpParser {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "NlpParser";
    
    @javax.inject.Inject()
    public NlpParser() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.AiParseResult parse(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractTitle(java.lang.String text) {
        return null;
    }
    
    private final java.time.LocalTime extractTime(java.lang.String text) {
        return null;
    }
    
    private final java.time.LocalDate extractDate(java.lang.String text) {
        return null;
    }
    
    private final com.example.smartreminder.data.ai.NlpParser.RepeatInfo extractRepeatInfo(java.lang.String text) {
        return null;
    }
    
    private final java.util.Set<java.lang.Integer> extractWeekDays(java.lang.String text) {
        return null;
    }
    
    private final java.util.Set<java.lang.Integer> extractMonthDays(java.lang.String text) {
        return null;
    }
    
    private final java.lang.Integer extractMonthlyWeek(java.lang.String text) {
        return null;
    }
    
    private final java.util.Set<java.lang.Integer> extractMonthlyWeekDays(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\nJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003JT\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000e\u00a8\u0006)"}, d2 = {"Lcom/example/smartreminder/data/ai/NlpParser$RepeatInfo;", "", "repeatType", "Lcom/example/smartreminder/domain/model/RepeatType;", "monthDays", "", "", "weekDays", "monthlyWeek", "monthlyWeekDays", "(Lcom/example/smartreminder/domain/model/RepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/lang/Integer;Ljava/util/Set;)V", "getMonthDays", "()Ljava/util/Set;", "setMonthDays", "(Ljava/util/Set;)V", "getMonthlyWeek", "()Ljava/lang/Integer;", "setMonthlyWeek", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getMonthlyWeekDays", "setMonthlyWeekDays", "getRepeatType", "()Lcom/example/smartreminder/domain/model/RepeatType;", "setRepeatType", "(Lcom/example/smartreminder/domain/model/RepeatType;)V", "getWeekDays", "setWeekDays", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/example/smartreminder/domain/model/RepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/lang/Integer;Ljava/util/Set;)Lcom/example/smartreminder/data/ai/NlpParser$RepeatInfo;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    static final class RepeatInfo {
        @org.jetbrains.annotations.NotNull()
        private com.example.smartreminder.domain.model.RepeatType repeatType;
        @org.jetbrains.annotations.NotNull()
        private java.util.Set<java.lang.Integer> monthDays;
        @org.jetbrains.annotations.NotNull()
        private java.util.Set<java.lang.Integer> weekDays;
        @org.jetbrains.annotations.Nullable()
        private java.lang.Integer monthlyWeek;
        @org.jetbrains.annotations.NotNull()
        private java.util.Set<java.lang.Integer> monthlyWeekDays;
        
        public RepeatInfo(@org.jetbrains.annotations.NotNull()
        com.example.smartreminder.domain.model.RepeatType repeatType, @org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> monthDays, @org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> weekDays, @org.jetbrains.annotations.Nullable()
        java.lang.Integer monthlyWeek, @org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> monthlyWeekDays) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.domain.model.RepeatType getRepeatType() {
            return null;
        }
        
        public final void setRepeatType(@org.jetbrains.annotations.NotNull()
        com.example.smartreminder.domain.model.RepeatType p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> getMonthDays() {
            return null;
        }
        
        public final void setMonthDays(@org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> getWeekDays() {
            return null;
        }
        
        public final void setWeekDays(@org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getMonthlyWeek() {
            return null;
        }
        
        public final void setMonthlyWeek(@org.jetbrains.annotations.Nullable()
        java.lang.Integer p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> getMonthlyWeekDays() {
            return null;
        }
        
        public final void setMonthlyWeekDays(@org.jetbrains.annotations.NotNull()
        java.util.Set<java.lang.Integer> p0) {
        }
        
        public RepeatInfo() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.domain.model.RepeatType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.lang.Integer> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smartreminder.data.ai.NlpParser.RepeatInfo copy(@org.jetbrains.annotations.NotNull()
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
}