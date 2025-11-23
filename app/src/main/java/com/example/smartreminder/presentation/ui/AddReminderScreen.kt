package com.example.smartreminder.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.smartreminder.domain.model.RepeatType
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.presentation.viewmodel.AddReminderEvent
import com.example.smartreminder.presentation.viewmodel.AddReminderViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddReminderViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDateTime by remember { mutableStateOf(LocalDateTime.now()) }
    var repeatType by remember { mutableStateOf(RepeatType.NONE) }
    var repeatDays by remember { mutableStateOf(setOf<Int>()) }
    var monthlyRepeatType by remember { mutableStateOf(MonthlyRepeatType.BY_DATE) }
    var monthlyRepeatDays by remember { mutableStateOf(setOf<Int>()) }
    var monthlyRepeatWeek by remember { mutableStateOf<Int?>(null) }
    var monthlyRepeatDayOfWeek by remember { mutableStateOf<Int?>(null) }
    var inputText by remember { mutableStateOf("") }
    
    // 监听AI解析结果的变化
    LaunchedEffect(state.aiParseResult) {
        state.aiParseResult?.let { parseResult ->
            title = parseResult.title
            description = parseResult.description ?: ""
            parseResult.scheduledTime?.let { time ->
                selectedDateTime = LocalDateTime.ofInstant(
                    java.time.Instant.ofEpochMilli(time),
                    ZoneId.systemDefault()
                )
            }
            // 使用AI解析的重复类型
            if (parseResult.repeatType != null) {
                repeatType = parseResult.repeatType
            }
            // 使用AI解析的每月重复日期
            if (parseResult.repeatType == RepeatType.MONTHLY && parseResult.monthDays.isNotEmpty()) {
                monthlyRepeatType = MonthlyRepeatType.BY_DATE
                monthlyRepeatDays = parseResult.monthDays
            }
            // 使用AI解析的每周重复日期
            if (parseResult.repeatType == RepeatType.WEEKLY) {
                repeatDays = parseResult.weekDays
            }
        }
    }

    // 监听保存状态并在保存成功时通知
    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            // 立即导航回去，然后显示提示
            onNavigateBack()
            snackbarHostState.showSnackbar("Reminder created successfully!")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Reminder") },
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
                // Natural Language Input with AI
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("Describe your reminder in natural language") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        placeholder = { Text("e.g., 'Remind me to call mom tomorrow at 3 PM'") },
                        leadingIcon = {
                            Icon(Icons.Filled.Lightbulb, contentDescription = "AI")
                        },
                        enabled = !state.isAiParsing,
                        trailingIcon = {
                            if (state.isAiParsing) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp
                                )
                            }
                        }
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                if (inputText.isNotBlank()) {
                                    viewModel.onEvent(com.example.smartreminder.presentation.viewmodel.AddReminderEvent.ParseText(inputText))
                                }
                            },
                            enabled = inputText.isNotBlank() && !state.isAiParsing
                        ) {
                            Text("Parse with AI")
                        }
                    }
                }

                state.aiParseResult?.let { parseResult ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "AI Parsed Result",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Title: ${parseResult.title}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            parseResult.description?.let { desc ->
                                Text(
                                    text = "Description: $desc",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            parseResult.scheduledTime?.let { time ->
                                Text(
                                    text = "Time: ${formatDateTime(time)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            // 显示重复类型
                            if (parseResult.repeatType != RepeatType.NONE) {
                                Text(
                                    text = "Repeat: ${parseResult.repeatType.name}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = {
                                        title = parseResult.title
                                        description = parseResult.description ?: ""
                                        parseResult.scheduledTime?.let { time ->
                                            selectedDateTime = java.time.Instant.ofEpochMilli(time)
                                                .atZone(java.time.ZoneId.systemDefault())
                                                .toLocalDateTime()
                                        }
                                        // 使用AI解析的重复类型
                                        repeatType = parseResult.repeatType
                                        // 使用AI解析的每月重复日期
                                        if (parseResult.repeatType == RepeatType.MONTHLY && parseResult.monthDays.isNotEmpty()) {
                                            monthlyRepeatType = MonthlyRepeatType.BY_DATE
                                            monthlyRepeatDays = parseResult.monthDays
                                        }
                                        // 使用AI解析的每周重复日期
                                        if (parseResult.repeatType == RepeatType.WEEKLY) {
                                            repeatDays = parseResult.weekDays
                                        }
                                    }
                                ) {
                                    Text("Use This")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                TextButton(
                                    onClick = {
                                        inputText = ""
                                        // 清除AI解析结果
                                        viewModel.onEvent(AddReminderEvent.ClearError)
                                    }
                                ) {
                                    Text("Clear")
                                }
                            }
                        }
                    }
                }

                // Title Input
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Description Input
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

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(7),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            // Days 1-31
                            items((1..31).toList()) { day ->
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            monthlyRepeatDays = if (day in monthlyRepeatDays) {
                                                monthlyRepeatDays - day
                                            } else {
                                                monthlyRepeatDays + day
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = day.toString(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = if (day in monthlyRepeatDays) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        }
                                    )
                                }
                            }

                            // Last day option
                            item {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            val lastDay = 31 // 特殊值表示最后一天
                                            monthlyRepeatDays = if (lastDay in monthlyRepeatDays) {
                                                monthlyRepeatDays - lastDay
                                            } else {
                                                monthlyRepeatDays + lastDay
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Last",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = if (31 in monthlyRepeatDays) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        }
                                    )
                                }
                            }
                        }

                        // 显示已选择的日期
                        if (monthlyRepeatDays.isNotEmpty()) {
                            val displayDays = monthlyRepeatDays.map { day ->
                                if (day == 31) "Last day" else day.toString()
                            }
                            Text(
                                text = "Selected: ${displayDays.sorted().joinToString(", ")}",
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
                            id = 0L,
                            title = title,
                            description = if (description.isBlank()) null else description,
                            reminderTime = cleanDateTime.toInstant(
                                ZoneId.systemDefault().rules.getOffset(cleanDateTime)
                            ).toEpochMilli(),
                            isActive = true,
                            repeatType = repeatType,
                            repeatDays = if (repeatType == RepeatType.WEEKLY) repeatDays else null,
                            monthlyRepeatType = if (repeatType == RepeatType.MONTHLY) monthlyRepeatType else null,
                            monthlyRepeatDays = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_DATE) monthlyRepeatDays else null,
                            monthlyRepeatWeek = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_WEEKDAY) monthlyRepeatWeek else null,
                            monthlyRepeatDayOfWeek = if (repeatType == RepeatType.MONTHLY && monthlyRepeatType == MonthlyRepeatType.BY_WEEKDAY) monthlyRepeatDayOfWeek else null,
                            repeatInterval = null,
                            createdAt = System.currentTimeMillis(),
                            updatedAt = System.currentTimeMillis()
                        )
                        viewModel.onEvent(AddReminderEvent.CreateReminder(reminder))
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
}

fun formatDateTime(time: Long): String {
    return java.time.LocalDateTime.ofInstant(
        java.time.Instant.ofEpochMilli(time),
        java.time.ZoneId.systemDefault()
    ).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
}