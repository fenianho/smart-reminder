package com.example.smartreminder.domain.usecase

import android.content.Context
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeleteReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(reminder: Reminder) {
        // 取消提醒的调度
        val scheduler = ReminderScheduler(context)
        scheduler.cancelReminder(reminder.id)
        // 删除提醒
        repository.deleteReminder(reminder)
    }
}