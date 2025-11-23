package com.example.smartreminder.data.repository

import com.example.smartreminder.data.local.dao.ReminderDao
import com.example.smartreminder.data.local.entity.ReminderEntity
import com.example.smartreminder.domain.model.MonthlyRepeatType
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import com.example.smartreminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
) : ReminderRepository {
    
    override suspend fun insertReminder(reminder: Reminder): Long {
        return reminderDao.insertReminder(toEntity(reminder))
    }

    override suspend fun updateReminder(reminder: Reminder) {
        reminderDao.updateReminder(toEntity(reminder))
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(toEntity(reminder))
    }

    override suspend fun deactivateReminder(id: Long) {
        reminderDao.deactivateReminder(id)
    }

    override fun getReminderById(id: Long): Flow<Reminder?> {
        return reminderDao.getReminderById(id).map { it?.toReminder() }
    }

    override fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders().map { it.map { entity -> entity.toReminder() } }
    }

    override fun getActiveReminders(): Flow<List<Reminder>> {
        return reminderDao.getActiveReminders().map { it.map { entity -> entity.toReminder() } }
    }

    override fun getRemindersInTimeRange(startTime: Long, endTime: Long): Flow<List<Reminder>> {
        return reminderDao.getRemindersInTimeRange(startTime, endTime).map { it.map { entity -> entity.toReminder() } }
    }

    private fun toEntity(reminder: Reminder): ReminderEntity {
        return ReminderEntity(
            id = reminder.id,
            title = reminder.title,
            description = reminder.description,
            reminderTime = reminder.reminderTime,
            isActive = reminder.isActive,
            repeatType = reminder.repeatType.name,
            repeatInterval = reminder.repeatInterval,
            repeatDays = reminder.repeatDays?.joinToString(","),
            monthlyRepeatType = reminder.monthlyRepeatType?.name,
            monthlyRepeatDays = reminder.monthlyRepeatDays?.joinToString(","),
            monthlyRepeatWeek = reminder.monthlyRepeatWeek,
            monthlyRepeatDayOfWeek = reminder.monthlyRepeatDayOfWeek,
            createdAt = reminder.createdAt,
            updatedAt = reminder.updatedAt
        )
    }

    private fun ReminderEntity.toReminder(): Reminder {
        return Reminder(
            id = id,
            title = title,
            description = description,
            reminderTime = reminderTime,
            isActive = isActive,
            repeatType = RepeatType.valueOf(repeatType),
            repeatInterval = repeatInterval,
            repeatDays = repeatDays?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
            monthlyRepeatType = monthlyRepeatType?.let { MonthlyRepeatType.valueOf(it) },
            monthlyRepeatDays = monthlyRepeatDays?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
            monthlyRepeatWeek = monthlyRepeatWeek,
            monthlyRepeatDayOfWeek = monthlyRepeatDayOfWeek,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
