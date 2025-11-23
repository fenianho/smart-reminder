package com.example.smartreminder.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartreminder.domain.model.MonthlyRepeatType
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import com.example.smartreminder.presentation.viewmodel.EditReminderEvent
import com.example.smartreminder.presentation.viewmodel.EditReminderViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditReminderScreen(
    reminderId: Long,
    onNavigateBack: () -> Unit,
    viewModel: EditReminderViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDateTime by remember { mutableStateOf(LocalDateTime.now()) }
    var isActive by remember { mutableStateOf(true) }
    var repeatType by remember { mutableStateOf(RepeatType.NONE) }
    var repeatDays by remember { mutableStateOf(setOf<Int>()) }
    var monthlyRepeatType by remember { mutableStateOf(MonthlyRepeatType.BY_DATE) }
    var monthlyRepeatDays by remember { mutableStateOf(setOf<Int>()) }
    var monthlyRepeatWeek by remember { mutableStateOf<Int?>(null) }
    var monthlyRepeatDayOfWeek by remember { mutableStateOf<Int?>(null) }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val snackbarHostState = remember { SnackbarHostState() }

    // 监听保存状态并在保存成功时通知
    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            // 立即导航回去，然后显示提示
            onNavigateBack()
            snackbarHostState.showSnackbar("Reminder updated successfully!")
        }
    }

    // 加载提醒数据
    LaunchedEffect(Unit) {
        viewModel.loadReminder(reminderId)
    }

    // 初始化表单数据
    LaunchedEffect(state.reminder) {
        state.reminder?.let { reminder ->
            title = reminder.title
            description = reminder.description ?: ""
            selectedDateTime = LocalDateTime.ofEpochSecond(
                reminder.reminderTime / 1000,
                0,
                ZoneId.systemDefault().rules.getOffset(java.time.Instant.now())
            )
            isActive = reminder.isActive
            repeatType = reminder.repeatType
            repeatDays = reminder.repeatDays ?: emptySet()
            monthlyRepeatType = reminder.monthlyRepeatType ?: MonthlyRepeatType.BY_DATE
            monthlyRepeatDays = reminder.monthlyRepeatDays ?: emptySet()
            monthlyRepeatWeek = reminder.monthlyRepeatWeek
            monthlyRepeatDayOfWeek = reminder.monthlyRepeatDayOfWeek
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Reminder") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 添加一个单独的可滚动区域，而不是让整个Column可滚动
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                // Date & Time Picker
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { dateDialogState.show() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("开始日期: ${selectedDateTime.toLocalDate()}")
                    }

                    OutlinedButton(
                        onClick = { timeDialogState.show() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("开始时间: ${selectedDateTime.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))}")
                    }
                }

                // Active Status Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Switch(
                        checked = isActive,
                        onCheckedChange = { isActive = it }
                    )
                }

                // Repeat Options
                Text(
                    text = "Repeat",
                    style = MaterialTheme.typography.titleMedium
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RepeatType.values().forEach { type ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = repeatType == type,
                                onClick = { repeatType = type }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(type.name)
                        }
                    }
                }

                // Weekly repeat days selection
                if (repeatType == RepeatType.WEEKLY) {
                    Text(
                        text = "Repeat on",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    val daysOfWeek = listOf(
                        "Monday" to 1,
                        "Tuesday" to 2,
                        "Wednesday" to 3,
                        "Thursday" to 4,
                        "Friday" to 5,
                        "Saturday" to 6,
                        "Sunday" to 7
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        daysOfWeek.forEach { (dayName, dayValue) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = dayValue in repeatDays,
                                    onCheckedChange = { checked ->
                                        repeatDays = if (checked) {
                                            repeatDays + dayValue
                                        } else {
                                            repeatDays - dayValue
                                        }
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(dayName)
                            }
                        }
                    }
                }
                
                // Monthly repeat options
                if (repeatType == RepeatType.MONTHLY) {
                    Text(
                        text = "Monthly repeat options",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    // Monthly repeat type selection
                    val monthlyRepeatTypes = listOf(
                        "By date" to MonthlyRepeatType.BY_DATE,
                        "By weekday" to MonthlyRepeatType.BY_WEEKDAY
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        monthlyRepeatTypes.forEach { (typeName, typeValue) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = monthlyRepeatType == typeValue,
                                    onClick = { monthlyRepeatType = typeValue }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(typeName)
                            }
                        }
                    }
                    
                    // By date options (1-31) - 支持多选
                    if (monthlyRepeatType == MonthlyRepeatType.BY_DATE) {
                        Text(
                            text = "Select dates",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        val days = (1..31).toList()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(days) { day ->
                                FilterChip(
                                    selected = day in monthlyRepeatDays,
                                    onClick = { 
                                        monthlyRepeatDays = if (day in monthlyRepeatDays) {
                                            monthlyRepeatDays - day
                                        } else {
                                            monthlyRepeatDays + day
                                        }
                                    },
                                    label = { 
                                        // 将31号显示为"31或最后1天"
                                        if (day == 31) {
                                            Text("31或最后1天")
                                        } else {
                                            Text(day.toString())
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(2.dp)
                                )
                            }
                        }
                        
                        // 显示已选择的日期
                        if (monthlyRepeatDays.isNotEmpty()) {
                            val displayDays = monthlyRepeatDays.map { day ->
                                if (day == 31) "31或最后1天" else day.toString()
                            }
                            Text(
                                text = "已选择: ${displayDays.sorted().joinToString(", ")}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    
                    // By weekday options
                    if (monthlyRepeatType == MonthlyRepeatType.BY_WEEKDAY) {
                        Text(
                            text = "Select weekday",
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Week selection
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Week",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                
                                val weeks = listOf(
                                    "First" to 1,
                                    "Second" to 2,
                                    "Third" to 3,
                                    "Fourth" to 4,
                                    "Last" to -1
                                )
                                
                                weeks.forEach { (weekName, weekValue) ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { monthlyRepeatWeek = weekValue }
                                            .padding(vertical = 4.dp)
                                    ) {
                                        RadioButton(
                                            selected = monthlyRepeatWeek == weekValue,
                                            onClick = { monthlyRepeatWeek = weekValue }
                                        )
                                        Text(
                                            text = weekName,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                            
                            // Day of week selection
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Day",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                
                                val daysOfWeek = listOf(
                                    "Monday" to 1,
                                    "Tuesday" to 2,
                                    "Wednesday" to 3,
                                    "Thursday" to 4,
                                    "Friday" to 5,
                                    "Saturday" to 6,
                                    "Sunday" to 7
                                )
                                
                                daysOfWeek.forEach { (dayName, dayValue) ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { monthlyRepeatDayOfWeek = dayValue }
                                            .padding(vertical = 4.dp)
                                    ) {
                                        RadioButton(
                                            selected = monthlyRepeatDayOfWeek == dayValue,
                                            onClick = { monthlyRepeatDayOfWeek = dayValue }
                                        )
                                        Text(
                                            text = dayName,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Action Buttons (outside of scrollable area)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        // 创建一个没有秒数的时间
                        val cleanDateTime = selectedDateTime.withSecond(0).withNano(0)
                        
                        val reminder = Reminder(
                            id = state.reminder?.id ?: 0L,
                            title = title,
                            description = if (description.isBlank()) null else description,
                            reminderTime = cleanDateTime.toInstant(
                                ZoneId.systemDefault().rules.getOffset(cleanDateTime)
                            ).toEpochMilli(),
                            repeatType = repeatType,
                            repeatDays = if (repeatType == RepeatType.WEEKLY) repeatDays else null,
                            monthlyRepeatType = if (repeatType == RepeatType.MONTHLY) monthlyRepeatType else null,
                            monthlyRepeatDays = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_DATE) monthlyRepeatDays else null,
                            monthlyRepeatWeek = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_WEEKDAY) monthlyRepeatWeek else null,
                            monthlyRepeatDayOfWeek = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_WEEKDAY) monthlyRepeatDayOfWeek else null,
                            isActive = isActive,
                            createdAt = state.reminder?.createdAt ?: System.currentTimeMillis(),
                            updatedAt = System.currentTimeMillis()
                        )

                        viewModel.onEvent(EditReminderEvent.UpdateReminder(reminder))
                    },
                    modifier = Modifier.weight(1f),
                    // Disable the button when the title is blank OR when it's loading
                    enabled = title.isNotBlank() && !state.isLoading
                ) {
                    if (state.isLoading) {
                        // Show a loading indicator
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        // Show the button text
                        Text("Save")
                    }
                }
            }
        }
    }

    // Date Picker Dialog
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = selectedDateTime.toLocalDate(),
            onDateChange = { date ->
                selectedDateTime = selectedDateTime.withYear(date.year)
                    .withMonth(date.monthValue)
                    .withDayOfMonth(date.dayOfMonth)
            }
        )
    }

    // Time Picker Dialog
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("Cancel")
        }
    ) {
        timepicker(
            initialTime = selectedDateTime.toLocalTime(),
            onTimeChange = { time ->
                selectedDateTime = selectedDateTime.withHour(time.hour)
                    .withMinute(time.minute)
            }
        )
    }
}
