package com.example.smartreminder.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0016\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\f\u0010\u001a\u001a\u00020\f*\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/smartreminder/data/repository/ReminderRepositoryImpl;", "Lcom/example/smartreminder/domain/repository/ReminderRepository;", "reminderDao", "Lcom/example/smartreminder/data/local/dao/ReminderDao;", "(Lcom/example/smartreminder/data/local/dao/ReminderDao;)V", "deactivateReminder", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminder", "Lcom/example/smartreminder/domain/model/Reminder;", "(Lcom/example/smartreminder/domain/model/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveReminders", "Lkotlinx/coroutines/flow/Flow;", "", "getAllReminders", "getReminderById", "getRemindersInTimeRange", "startTime", "endTime", "insertReminder", "toEntity", "Lcom/example/smartreminder/data/local/entity/ReminderEntity;", "updateReminder", "toReminder", "app_debug"})
public final class ReminderRepositoryImpl implements com.example.smartreminder.domain.repository.ReminderRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartreminder.data.local.dao.ReminderDao reminderDao = null;
    
    @javax.inject.Inject()
    public ReminderRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.local.dao.ReminderDao reminderDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.domain.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deactivateReminder(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.smartreminder.domain.model.Reminder> getReminderById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getAllReminders() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getActiveReminders() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.domain.model.Reminder>> getRemindersInTimeRange(long startTime, long endTime) {
        return null;
    }
    
    private final com.example.smartreminder.data.local.entity.ReminderEntity toEntity(com.example.smartreminder.domain.model.Reminder reminder) {
        return null;
    }
    
    private final com.example.smartreminder.domain.model.Reminder toReminder(com.example.smartreminder.data.local.entity.ReminderEntity $this$toReminder) {
        return null;
    }
}