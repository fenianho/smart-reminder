package com.example.smartreminder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.usecase.CreateReminderUseCase
import com.example.smartreminder.domain.usecase.ParseReminderTextUseCase
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddReminderState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val aiParseResult: com.example.smartreminder.domain.model.AiParseResult? = null,
    val isAiParsing: Boolean = false,
    val isSaved: Boolean = false
)

sealed class AddReminderEvent {
    data class ParseText(val text: String) : AddReminderEvent()
    data class CreateReminder(val reminder: Reminder) : AddReminderEvent()
    object ClearError : AddReminderEvent()
    object ResetSavedState : AddReminderEvent()
}

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val parseReminderTextUseCase: ParseReminderTextUseCase,
    private val createReminderUseCase: CreateReminderUseCase,
    private val scheduleReminderUseCase: ScheduleReminderUseCase,
    private val reminderScheduler: ReminderScheduler
) : ViewModel() {
    private val TAG = "AddReminderViewModel"

    private val _state = MutableStateFlow(AddReminderState())
    val state: StateFlow<AddReminderState> = _state.asStateFlow()

    fun onEvent(event: AddReminderEvent) {
        when (event) {
            is AddReminderEvent.ParseText -> parseReminderText(event.text)
            is AddReminderEvent.CreateReminder -> createReminder(event.reminder)
            is AddReminderEvent.ClearError -> _state.update { it.copy(error = null) }
            is AddReminderEvent.ResetSavedState -> _state.update { it.copy(isSaved = false) }
        }
    }

    private fun parseReminderText(text: String) {
        Log.d(TAG, "Parsing text: $text")
        if (text.isBlank()) {
            _state.update { it.copy(aiParseResult = null, isAiParsing = false) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isAiParsing = true, error = null) }
            try {
                val result = parseReminderTextUseCase(text)
                result.fold(
                    onSuccess = { parseResult ->
                        Log.d(TAG, "Text parsed successfully: $parseResult")
                        // 处理AI解析结果中的重复类型
                        val updatedParseResult = if (parseResult.repeatType != null) {
                            // 确保重复类型有效
                            parseResult.copy(repeatType = parseResult.repeatType)
                        } else {
                            parseResult
                        }
                        _state.update { it.copy(aiParseResult = updatedParseResult, isAiParsing = false) }
                    },
                    onFailure = { error ->
                        Log.e(TAG, "Failed to parse text: $text", error)
                        _state.update {
                            it.copy(
                                error = error.message ?: "Failed to parse text",
                                isAiParsing = false
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                Log.e(TAG, "Exception while parsing text: $text", e)
                _state.update {
                    it.copy(
                        error = e.message ?: "Failed to parse text",
                        isAiParsing = false
                    )
                }
            }
        }
    }

    private fun createReminder(reminder: Reminder) {
        Log.d(TAG, "Creating reminder: ${reminder.title}, repeatType: ${reminder.repeatType}, repeatDays: ${reminder.repeatDays}")
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val result = createReminderUseCase(reminder)
                if (result.isSuccess) {
                    // 使用数据库返回的实际ID创建新的reminder对象
                    val reminderId = result.getOrNull()
                    if (reminderId != null) {
                        val updatedReminder = reminder.copy(id = reminderId)
                        
                        // Schedule the reminder notification with the correct ID
                        val scheduledTimes = scheduleReminderUseCase(updatedReminder)
                        Log.d(TAG, "Scheduled times count: ${scheduledTimes.size}")
                        reminderScheduler.scheduleReminder(updatedReminder, scheduledTimes)
                        _state.update { it.copy(isLoading = false, isSaved = true) }
                        Log.d(TAG, "Reminder created and scheduled successfully")
                    } else {
                        Log.e(TAG, "Failed to get reminder ID after creation")
                        _state.update {
                            it.copy(
                                error = "Failed to get reminder ID",
                                isLoading = false
                            )
                        }
                    }
                } else {
                    Log.e(TAG, "Failed to create reminder: ${reminder.title}")
                    _state.update {
                        it.copy(
                            error = result.exceptionOrNull()?.message ?: "Failed to create reminder",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception while creating reminder: ${reminder.title}", e)
                _state.update {
                    it.copy(
                        error = e.message ?: "Failed to create reminder",
                        isLoading = false
                    )
                }
            }
        }
    }
}