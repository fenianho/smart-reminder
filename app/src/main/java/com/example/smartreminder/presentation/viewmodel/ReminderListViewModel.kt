package com.example.smartreminder.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.usecase.CreateReminderUseCase
import com.example.smartreminder.domain.usecase.GetAllRemindersUseCase
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.domain.usecase.UpdateReminderUseCase
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderListViewModel @Inject constructor(
    private val getAllRemindersUseCase: GetAllRemindersUseCase,
    private val createReminderUseCase: CreateReminderUseCase,
    private val updateReminderUseCase: UpdateReminderUseCase,
    private val scheduleReminderUseCase: ScheduleReminderUseCase,
    private val reminderScheduler: ReminderScheduler
) : ViewModel() {

    private val _state = MutableStateFlow(ReminderListState())
    val state: StateFlow<ReminderListState> = _state.asStateFlow()

    init {
        loadReminders()
    }

    fun onEvent(event: ReminderListEvent) {
        when (event) {
            is ReminderListEvent.LoadReminders -> loadReminders()
            is ReminderListEvent.CreateReminder -> createReminder(event.reminder)
            is ReminderListEvent.DeleteReminder -> deleteReminder(event.reminder)
            is ReminderListEvent.ToggleReminder -> toggleReminder(event.reminder)
        }
    }

    private fun loadReminders() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                getAllRemindersUseCase().collect { reminders ->
                    _state.update { it.copy(reminders = reminders, isLoading = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    private fun createReminder(reminder: Reminder) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val result = createReminderUseCase(reminder)
                if (result.isSuccess) {
                    // 调度提醒通知
                    scheduleReminderUseCase(reminder)
                    // 重新加载列表
                    loadReminders()
                } else {
                    _state.update { it.copy(error = result.exceptionOrNull()?.message, isLoading = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    private fun deleteReminder(reminder: Reminder) {
        // TODO: 实现删除功能
        // 这里需要添加删除的UseCase
    }

    private fun toggleReminder(reminder: Reminder) {
        viewModelScope.launch {
            try {
                // 切换提醒的激活状态
                val updatedReminder = reminder.copy(isActive = !reminder.isActive)
                val result = updateReminderUseCase(updatedReminder)
                
                if (result.isSuccess) {
                    // 如果提醒被激活，重新调度提醒通知
                    if (updatedReminder.isActive) {
                        scheduleReminderUseCase(updatedReminder)
                    } else {
                        // 如果提醒被取消激活，取消已调度的提醒通知
                        reminderScheduler.cancelReminder(reminder.id)
                    }
                    // 重新加载列表以反映更改
                    loadReminders()
                } else {
                    _state.update { it.copy(error = result.exceptionOrNull()?.message) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
}