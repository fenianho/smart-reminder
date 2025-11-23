package com.example.smartreminder.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\fH\'J\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f2\u0006\u0010\u0004\u001a\u00020\u0005H\'J$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\f2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\'J\u0016\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/example/smartreminder/data/local/dao/ReminderDao;", "", "deactivateReminder", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminder", "Lcom/example/smartreminder/data/local/entity/ReminderEntity;", "(Lcom/example/smartreminder/data/local/entity/ReminderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveReminders", "Lkotlinx/coroutines/flow/Flow;", "", "getAllReminders", "getReminderById", "getRemindersInTimeRange", "startTime", "endTime", "insertReminder", "updateReminder", "app_debug"})
@androidx.room.Dao()
public abstract interface ReminderDao {
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE isActive = 1 ORDER BY reminderTime ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.data.local.entity.ReminderEntity>> getActiveReminders();
    
    @androidx.room.Query(value = "SELECT * FROM reminders ORDER BY reminderTime ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.data.local.entity.ReminderEntity>> getAllReminders();
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.smartreminder.data.local.entity.ReminderEntity> getReminderById(long id);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.local.entity.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.local.entity.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    com.example.smartreminder.data.local.entity.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE reminders SET isActive = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivateReminder(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminders WHERE reminderTime BETWEEN :startTime AND :endTime AND isActive = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartreminder.data.local.entity.ReminderEntity>> getRemindersInTimeRange(long startTime, long endTime);
}