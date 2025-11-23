package com.example.smartreminder.domain.usecase

import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveRemindersUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    operator fun invoke(): Flow<List<Reminder>> {
        return repository.getActiveReminders()
    }
}