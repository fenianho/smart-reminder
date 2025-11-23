package com.example.smartreminder.domain.usecase

import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull

class GetReminderByIdUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(id: Long): Reminder? {
        return repository.getReminderById(id).firstOrNull()
    }
}