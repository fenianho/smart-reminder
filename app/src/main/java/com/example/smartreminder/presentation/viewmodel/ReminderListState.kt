package com.example.smartreminder.presentation.viewmodel

import com.example.smartreminder.domain.model.Reminder

data class ReminderListState(
    val reminders: List<Reminder> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)