package com.example.smartreminder.domain.repository

import com.example.smartreminder.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getActiveReminders(): Flow<List<Reminder>>
    fun getAllReminders(): Flow<List<Reminder>>
    fun getReminderById(id: Long): Flow<Reminder?>
    suspend fun insertReminder(reminder: Reminder): Long
    suspend fun updateReminder(reminder: Reminder)
    suspend fun deleteReminder(reminder: Reminder)
    suspend fun deactivateReminder(id: Long)
    fun getRemindersInTimeRange(startTime: Long, endTime: Long): Flow<List<Reminder>>
}