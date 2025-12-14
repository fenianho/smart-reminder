package com.example.smartreminder.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.presentation.viewmodel.ReminderDetailViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDetailScreen(
    reminderId: Long,
    viewModel: ReminderDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onEditReminder: (Reminder) -> Unit = {},
    onDeleteReminder: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    // 监听删除状态，如果删除成功则导航回上一页
    LaunchedEffect(state.isDeleted) {
        if (state.isDeleted) {
            onNavigateBack()
        }
    }

    LaunchedEffect(reminderId) {
        viewModel.loadReminder(reminderId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reminder Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { state.reminder?.let { onEditReminder(it) } }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            )
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
                        Button(onClick = { viewModel.loadReminder(reminderId) }) {
                            Text("Retry")
                        }
                    }
                }
                state.reminder == null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Reminder not found",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = onNavigateBack) {
                            Text("Go Back")
                        }
                    }
                }
                else -> {
                    ReminderDetailContent(
                        reminder = state.reminder!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Reminder") },
            text = { Text("Are you sure you want to delete this reminder? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteReminder()
                        showDeleteDialog = false
                        // 不再直接调用onDeleteReminder，而是依赖ViewModel的状态变化
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun ReminderDetailContent(
    reminder: Reminder,
    modifier: Modifier = Modifier
) {
    // 计算显示的提醒时间（对于重复提醒，显示下次提醒时间）
    var displayTime by remember(reminder.id, reminder.reminderTime) { 
        mutableStateOf(reminder.reminderTime) 
    }
    
    // 如果是重复提醒且时间已过，则计算下次提醒时间
    LaunchedEffect(reminder.id, reminder.reminderTime) {
        if (reminder.repeatType != com.example.smartreminder.domain.model.RepeatType.NONE && 
            reminder.reminderTime <= System.currentTimeMillis() && 
            reminder.isActive) {
            
            val scheduleUseCase = com.example.smartreminder.domain.usecase.ScheduleReminderUseCase()
            val nextTimes = scheduleUseCase(reminder)
            
            if (nextTimes.isNotEmpty()) {
                // 更新显示时间为下次提醒时间
                displayTime = nextTimes.first()
            }
        }
    }
    
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Text(
            text = reminder.title,
            style = MaterialTheme.typography.headlineMedium
        )

        // Description
        reminder.description?.let { description ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Reminder Time
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Reminder Time",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = formatReminderTime(displayTime),
                    style = MaterialTheme.typography.bodyLarge
                )
                
                // 如果是重复提醒且显示的时间不同于原始时间，额外显示原始设置时间
                if (reminder.repeatType != com.example.smartreminder.domain.model.RepeatType.NONE && 
                    displayTime != reminder.reminderTime) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Originally set: ${formatReminderTime(reminder.reminderTime)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Status
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Status",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (reminder.isActive) "Active" else "Inactive",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(
                        containerColor = if (reminder.isActive) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outline
                        }
                    ) {
                        Text(
                            text = if (reminder.isActive) "Active" else "Inactive",
                            color = if (reminder.isActive) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }

        // Repeat Type
        if (reminder.repeatType != com.example.smartreminder.domain.model.RepeatType.NONE) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Repeat",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = reminder.repeatType.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    reminder.repeatInterval?.let { interval ->
                        Text(
                            text = "Interval: $interval",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    // 显示重复的星期几
                    reminder.repeatDays?.let { days ->
                        if (days.isNotEmpty()) {
                            val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
                            val repeatDaysText = days.sorted().joinToString(", ") { dayIndex ->
                                if (dayIndex in 1..7) daysOfWeek[dayIndex - 1] else "Unknown"
                            }
                            Text(
                                text = "Repeat on: $repeatDaysText",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Timestamps
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Timestamps",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Created: ${formatTimestamp(reminder.createdAt)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Updated: ${formatTimestamp(reminder.updatedAt)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun formatReminderTime(timeMillis: Long): String {
    val date = Date(timeMillis)
    return SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' HH:mm", Locale.getDefault()).format(date)
}

private fun formatTimestamp(timeMillis: Long): String {
    val date = Date(timeMillis)
    return SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.getDefault()).format(date)
}