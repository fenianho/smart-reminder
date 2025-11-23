package com.example.smartreminder.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\fH&J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\fH&J\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f2\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\f2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H&J\u0016\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/example/smartreminder/domain/repository/ReminderRepository;", "", "deactivateReminder", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "(Lcom/example/smartreminder/domain/model/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveReminders", "Lkotlinx/coroutines/flow/Flow;", "", "getAllReminders", "getReminderById", "getRemindersInTimeRange", "startTime", "endTime", "insertReminder", "updateReminder", "app_debug"})
public abstract interface ReminderRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getActiveReminders();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getAllReminders();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.smartreminder.domain.model.Reminder> getReminderById(long id);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivateReminder(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getRemindersInTimeRange(long startTime, long endTime);
}