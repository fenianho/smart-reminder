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
import com.example.smartreminder.presentation.viewmodel.ReminderListEvent
import com.example.smartreminder.presentation.viewmodel.ReminderListViewModel
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

            val timeText = formatReminderTime(reminder.reminderTime)
            val timeColor = when {
                reminder.reminderTime < System.currentTimeMillis() && reminder.isActive -> 
                    Color.Red // 过期提醒用红色
                !reminder.isActive -> MaterialTheme.colorScheme.outline
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            }
            Text(
                text = timeText,
                style = MaterialTheme.typography.bodySmall,
                color = timeColor
            )

            if (reminder.repeatType != RepeatType.NONE) {
                Text(
                    text = "Repeats: ${reminder.repeatType.name}",
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


private fun formatReminderTime(timeMillis: Long): String {
    val date = Date(timeMillis)
    val now = System.currentTimeMillis()
    val diff = timeMillis - now

    return when {
        diff < 0 -> "Overdue"
        diff < 60 * 1000 -> "In less than a minute"
        diff < 60 * 60 * 1000 -> "In ${diff / (60 * 1000)} minutes"
        diff < 24 * 60 * 60 * 1000 -> "In ${diff / (60 * 60 * 1000)} hours"
        else -> SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(date)
    }
}