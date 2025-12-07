package com.example.smartreminder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.usecase.GetReminderByIdUseCase
import com.example.smartreminder.domain.usecase.UpdateReminderUseCase
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.LocalDate

data class EditReminderState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null,
    val reminder: Reminder? = null
)

sealed class EditReminderEvent {
    data class UpdateReminder(val reminder: Reminder) : EditReminderEvent()
    data class LoadReminder(val id: Long) : EditReminderEvent()
    data class ShowErrorMessage(val message: String) : EditReminderEvent()
    object ClearError : EditReminderEvent()
}

@HiltViewModel
class EditReminderViewModel @Inject constructor(
    private val getReminderByIdUseCase: GetReminderByIdUseCase,
    private val updateReminderUseCase: UpdateReminderUseCase,
    private val scheduleReminderUseCase: ScheduleReminderUseCase,
    private val reminderScheduler: ReminderScheduler
) : ViewModel() {
    private val TAG = "EditReminderViewModel"

    private val _state = MutableStateFlow(EditReminderState())
    val state: StateFlow<EditReminderState> = _state.asStateFlow()

    fun loadReminder(reminderId: Long) {
        Log.d(TAG, "Loading reminder with id: $reminderId")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val reminder = getReminderByIdUseCase(reminderId)
                Log.d(TAG, "Loaded reminder: ${reminder?.title}, repeatDays: ${reminder?.repeatDays}")
                _state.update { it.copy(reminder = reminder, isLoading = false) }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load reminder with id: $reminderId", e)
                _state.update {
                    it.copy(
                        errorMessage = "Failed to load reminder: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: EditReminderEvent) {
        when (event) {
            is EditReminderEvent.UpdateReminder -> updateReminder(event.reminder)
            is EditReminderEvent.LoadReminder -> loadReminder(event.id)
            is EditReminderEvent.ShowErrorMessage -> showErrorMessage(event.message)
            is EditReminderEvent.ClearError -> _state.update { it.copy(errorMessage = null) }
        }
    }

    private fun showErrorMessage(message: String) {
        _state.value = _state.value.copy(errorMessage = message)
    }

    private fun updateReminder(reminder: Reminder) {
        Log.d(TAG, "Updating reminder: ${reminder.title}, repeatType: ${reminder.repeatType}, repeatDays: ${reminder.repeatDays}")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                updateReminderUseCase(reminder)
                // Schedule the reminder notification only if the reminder is active
                if (reminder.isActive) {
                    val scheduledTimes = scheduleReminderUseCase(reminder)
                    Log.d(TAG, "Scheduled times count: ${scheduledTimes.size}")
                    reminderScheduler.scheduleReminder(reminder, scheduledTimes)
                } else {
                    // Cancel any existing scheduled notifications for inactive reminders
                    reminderScheduler.cancelReminder(reminder.id)
                }
                _state.update { it.copy(isLoading = false, isSaved = true) }
                Log.d(TAG, "Reminder updated and scheduled successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to update reminder: ${reminder.title}", e)
                _state.update { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
}