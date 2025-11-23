package com.example.smartreminder.domain.usecase

import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import javax.inject.Inject

class UpdateReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder): Result<Unit> {
        return try {
            repository.updateReminder(reminder)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}