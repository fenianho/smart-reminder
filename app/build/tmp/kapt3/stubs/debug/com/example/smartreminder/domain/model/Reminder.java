package com.example.smartreminder.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b0\b\u0087\b\u0018\u00002\u00020\u0001B\u00b3\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010.\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fH\u00c6\u0003J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fH\u00c6\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fH\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\tH\u00c6\u0003J\t\u00107\u001a\u00020\u000bH\u00c6\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0011\u00109\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fH\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003J\u00c0\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010<J\u0013\u0010=\u001a\u00020\t2\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010?\u001a\u00020\rH\u00d6\u0001J\t\u0010@\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001dR\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0015\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010\'R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0019\u00a8\u0006A"}, d2 = {"Lcom/example/smartreminder/domain/model/Reminder;", "", "id", "", "title", "", "description", "reminderTime", "isActive", "", "repeatType", "Lcom/example/smartreminder/domain/model/RepeatType;", "repeatInterval", "", "repeatDays", "", "monthlyRepeatType", "Lcom/example/smartreminder/domain/model/MonthlyRepeatType;", "monthlyRepeatDays", "monthlyRepeatWeeks", "monthlyRepeatDaysOfWeek", "createdAt", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;JZLcom/example/smartreminder/domain/model/RepeatType;Ljava/lang/Integer;Ljava/util/Set;Lcom/example/smartreminder/domain/model/MonthlyRepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;JJ)V", "getCreatedAt", "()J", "getDescription", "()Ljava/lang/String;", "getId", "()Z", "getMonthlyRepeatDays", "()Ljava/util/Set;", "getMonthlyRepeatDaysOfWeek", "getMonthlyRepeatType", "()Lcom/example/smartreminder/domain/model/MonthlyRepeatType;", "getMonthlyRepeatWeeks", "getReminderTime", "getRepeatDays", "getRepeatInterval", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRepeatType", "()Lcom/example/smartreminder/domain/model/RepeatType;", "getTitle", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;JZLcom/example/smartreminder/domain/model/RepeatType;Ljava/lang/Integer;Ljava/util/Set;Lcom/example/smartreminder/domain/model/MonthlyRepeatType;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;JJ)Lcom/example/smartreminder/domain/model/Reminder;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class Reminder {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String description = null;
    private final long reminderTime = 0L;
    private final boolean isActive = false;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.domain.model.RepeatType repeatType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer repeatInterval = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Set<java.lang.Integer> repeatDays = null;
    @org.jetbrains.annotations.Nullable()
    private final com.example.smartreminder.domain.model.MonthlyRepeatType monthlyRepeatType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Set<java.lang.Integer> monthlyRepeatDays = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Set<java.lang.Integer> monthlyRepeatWeeks = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Set<java.lang.Integer> monthlyRepeatDaysOfWeek = null;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    public Reminder(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String description, long reminderTime, boolean isActive, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.RepeatType repeatType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer repeatInterval, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> repeatDays, @org.jetbrains.annotations.Nullable()
    com.example.smartreminder.domain.model.MonthlyRepeatType monthlyRepeatType, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatDays, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatWeeks, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatDaysOfWeek, long createdAt, long updatedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final long getReminderTime() {
        return 0L;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.RepeatType getRepeatType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getRepeatInterval() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> getRepeatDays() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.smartreminder.domain.model.MonthlyRepeatType getMonthlyRepeatType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> getMonthlyRepeatDays() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> getMonthlyRepeatWeeks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> getMonthlyRepeatDaysOfWeek() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> component12() {
        return null;
    }
    
    public final long component13() {
        return 0L;
    }
    
    public final long component14() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.RepeatType component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.Integer> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.smartreminder.domain.model.MonthlyRepeatType component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartreminder.domain.model.Reminder copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String description, long reminderTime, boolean isActive, @org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.RepeatType repeatType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer repeatInterval, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> repeatDays, @org.jetbrains.annotations.Nullable()
    com.example.smartreminder.domain.model.MonthlyRepeatType monthlyRepeatType, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatDays, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatWeeks, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.Integer> monthlyRepeatDaysOfWeek, long createdAt, long updatedAt) {
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