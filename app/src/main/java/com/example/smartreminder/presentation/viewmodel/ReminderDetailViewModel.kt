package com.example.smartreminder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartreminder.domain.usecase.GetReminderByIdUseCase
import com.example.smartreminder.domain.usecase.DeleteReminderUseCase
import kotlinx.coroutines.flow.firstOrNull
import com.example.smartreminder.domain.model.Reminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReminderDetailState(
    val reminder: Reminder? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isDeleted: Boolean = false
)

@HiltViewModel
class ReminderDetailViewModel @Inject constructor(
    private val getReminderByIdUseCase: GetReminderByIdUseCase,
    private val deleteReminderUseCase: DeleteReminderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ReminderDetailState())
    val state: StateFlow<ReminderDetailState> = _state.asStateFlow()

    fun loadReminder(reminderId: Long) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val reminder = getReminderByIdUseCase.invoke(reminderId)
                _state.update { it.copy(reminder = reminder, isLoading = false) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = "Failed to load reminder: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun deleteReminder() {
        viewModelScope.launch {
            try {
                _state.value.reminder?.let { reminder ->
                    deleteReminderUseCase(reminder)
                    _state.update { it.copy(isDeleted = true) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = "Failed to delete reminder: ${e.message}"
                    )
                }
            }
        }
    }
}

