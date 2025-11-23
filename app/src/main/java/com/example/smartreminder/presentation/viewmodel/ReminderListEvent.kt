package com.example.smartreminder.presentation.viewmodel

import com.example.smartreminder.domain.model.Reminder

sealed class ReminderListEvent {
    object LoadReminders : ReminderListEvent()
    data class DeleteReminder(val reminder: Reminder) : ReminderListEvent()
    data class ToggleReminder(val reminder: Reminder) : ReminderListEvent()
    data class CreateReminder(val reminder: Reminder) : ReminderListEvent()
}