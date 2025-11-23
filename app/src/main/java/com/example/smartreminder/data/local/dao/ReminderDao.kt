package com.example.smartreminder.data.local.dao

import androidx.room.*
import com.example.smartreminder.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders WHERE isActive = 1 ORDER BY reminderTime ASC")
    fun getActiveReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders ORDER BY reminderTime ASC")
    fun getAllReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE id = :id")
    fun getReminderById(id: Long): Flow<ReminderEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity): Long

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("UPDATE reminders SET isActive = 0 WHERE id = :id")
    suspend fun deactivateReminder(id: Long)

    @Query("SELECT * FROM reminders WHERE reminderTime BETWEEN :startTime AND :endTime AND isActive = 1")
    fun getRemindersInTimeRange(startTime: Long, endTime: Long): Flow<List<ReminderEntity>>
}