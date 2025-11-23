package com.example.smartreminder.domain.usecase

import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import javax.inject.Inject

class CreateReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder): Result<Long> {
        return try {
            val id = repository.insertReminder(reminder)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}