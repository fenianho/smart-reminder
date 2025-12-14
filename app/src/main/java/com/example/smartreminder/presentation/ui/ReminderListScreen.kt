package com.example.smartreminder.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.presentation.viewmodel.ReminderListEvent
import com.example.smartreminder.presentation.viewmodel.ReminderListViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderListScreen(
    viewModel: ReminderListViewModel = hiltViewModel(),
    onAddReminder: () -> Unit = {},
    onReminderClick: (Reminder) -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Reminder") },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddReminder) {
                Icon(Icons.Filled.Add, contentDescription = "Add Reminder")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${state.error}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.onEvent(ReminderListEvent.LoadReminders) }) {
                            Text("Retry")
                        }
                    }
                }
                state.reminders.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No reminders yet",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tap the + button to create your first reminder",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    ReminderList(
                        reminders = state.reminders,
                        onReminderClick = onReminderClick,
                        onDeleteReminder = { viewModel.onEvent(ReminderListEvent.DeleteReminder(it)) },
                        onToggleReminder = { viewModel.onEvent(ReminderListEvent.ToggleReminder(it)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ReminderList(
    reminders: List<Reminder>,
    onReminderClick: (Reminder) -> Unit,
    onDeleteReminder: (Reminder) -> Unit,
    onToggleReminder: (Reminder) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reminders) { reminder ->
            ReminderCard(
                reminder = reminder,
                onClick = { onReminderClick(reminder) },
                onDelete = { onDeleteReminder(reminder) },
                onToggle = { onToggleReminder(reminder) }
            )
        }
    }
}

@Composable
private fun ReminderCard(
    reminder: Reminder,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onToggle: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = reminder.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (reminder.isActive) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                    
                    if (!reminder.isActive) {
                        Text(
                            text = "Inactive",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Switch(
                    checked = reminder.isActive,
                    onCheckedChange = { onToggle() }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            reminder.description?.let { description -> 
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (reminder.isActive) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            CountdownTimerText(reminder = reminder)

            // 显示详细的重复规则
            if (reminder.repeatType != RepeatType.NONE) {
                val repeatRuleText = formatRepeatRule(reminder)
                Text(
                    text = repeatRuleText,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (reminder.isActive) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                )
            }
        }
    }
}

@Composable
private fun CountdownTimerText(reminder: Reminder) {
    var displayTime by remember(reminder.id, reminder.reminderTime) { mutableStateOf(reminder.reminderTime) }
    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }
    
    // 定期更新当前时间，确保倒计时正常工作
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // 每秒更新一次
            currentTime = System.currentTimeMillis()
        }
    }
    
    // 计算并更新显示时间 - 统一处理初始设置和后续更新
    LaunchedEffect(reminder, currentTime) {
        // 始终基于当前的提醒状态和时间来决定显示时间
        val newDisplayTime = when {
            // 如果不是重复提醒，直接使用原始提醒时间
            reminder.repeatType == RepeatType.NONE -> {
                reminder.reminderTime
            }
            // 如果是重复提醒但已停用，也使用原始时间
            !reminder.isActive -> {
                reminder.reminderTime
            }
            // 如果是重复提醒且当前时间已超过显示时间，计算下一个提醒时间
            displayTime <= currentTime -> {
                val scheduleUseCase = ScheduleReminderUseCase()
                val nextTimes = scheduleUseCase(reminder)
                if (nextTimes.isNotEmpty()) nextTimes.first() else reminder.reminderTime
            }
            // 否则保持当前显示时间
            else -> {
                displayTime
            }
        }
        
        if (newDisplayTime != displayTime) {
            displayTime = newDisplayTime
        }
    }
    
    val timeText = formatReminderTime(displayTime, currentTime)
    val timeColor = when {
        displayTime < currentTime && reminder.isActive -> 
            Color.Red // 过期提醒用红色
        !reminder.isActive -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    
    Text(
        text = timeText,
        style = MaterialTheme.typography.bodySmall,
        color = timeColor
    )
}

private fun formatReminderTime(reminderTime: Long, currentTime: Long = System.currentTimeMillis()): String {
    val diff = reminderTime - currentTime
    
    return when {
        diff < 0 -> "Overdue"
        diff < 60 * 1000 -> "In less than a minute"
        diff < 60 * 60 * 1000 -> { // 小于1小时，显示分和秒
            val minutes = diff / (60 * 1000)
            val seconds = (diff % (60 * 1000)) / 1000
            String.format("In %d min %d sec", minutes, seconds)
        }
        else -> { // 大于等于1小时，显示小时和分钟
            val hours = diff / (60 * 60 * 1000)
            val minutes = (diff % (60 * 60 * 1000)) / (60 * 1000)
            String.format("In %d hr %d min", hours, minutes)
        }
    }
}

// 添加 formatRepeatRule 函数
private fun formatRepeatRule(reminder: Reminder): String {
    return when (reminder.repeatType) {
        RepeatType.DAILY -> {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = reminder.reminderTime
            }
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            "Daily ${String.format("%02d:%02d", hour, minute)}"
        }
        RepeatType.WEEKLY -> {
            val daysOfWeek = reminder.repeatDays ?: emptySet()
            val dayNames = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            val selectedDays = daysOfWeek.sorted().map { dayIndex ->
                if (dayIndex in 1..7) dayNames[dayIndex - 1] else "Unknown"
            }.joinToString(", ")
            
            val calendar = Calendar.getInstance().apply {
                timeInMillis = reminder.reminderTime
            }
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            
            "Weekly $selectedDays ${String.format("%02d:%02d", hour, minute)}"
        }
        RepeatType.MONTHLY -> {
            val monthlyType = reminder.monthlyRepeatType
            val calendar = Calendar.getInstance().apply {
                timeInMillis = reminder.reminderTime
            }
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            
            when (monthlyType) {
                com.example.smartreminder.domain.model.MonthlyRepeatType.BY_DATE -> {
                    val days = reminder.monthlyRepeatDays ?: emptySet()
                    val dayNumbers = days.sorted().joinToString(", ")
                    "Monthly on days $dayNumbers ${String.format("%02d:%02d", hour, minute)}"
                }
                com.example.smartreminder.domain.model.MonthlyRepeatType.BY_WEEKDAY -> {
                    val weekNumbers = reminder.monthlyRepeatWeeks ?: setOf(1)
                    val daysOfWeek = reminder.monthlyRepeatDaysOfWeek ?: setOf(1)
                    val weekText = when {
                        weekNumbers.contains(-1) -> "last"
                        weekNumbers.contains(1) && weekNumbers.size == 1 -> "first"
                        weekNumbers.contains(2) && weekNumbers.size == 1 -> "second"
                        weekNumbers.contains(3) && weekNumbers.size == 1 -> "third"
                        weekNumbers.contains(4) && weekNumbers.size == 1 -> "fourth"
                        else -> "weeks"
                    }
                    
                    val dayNames = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    val selectedDays = daysOfWeek.sorted().map { dayIndex ->
                        if (dayIndex in 1..7) dayNames[dayIndex - 1] else "Unknown"
                    }.joinToString(", ")
                    
                    "Monthly $weekText $selectedDays ${String.format("%02d:%02d", hour, minute)}"
                }
                null -> {
                    "Monthly ${String.format("%02d:%02d", hour, minute)}"
                }
            }
        }
        RepeatType.YEARLY -> {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = reminder.reminderTime
            }
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            
            "Yearly ${String.format("%02d/%02d %02d:%02d", month, day, hour, minute)}"
        }
        else -> {
            "Repeats: ${reminder.repeatType.name}"
        }
    }
}