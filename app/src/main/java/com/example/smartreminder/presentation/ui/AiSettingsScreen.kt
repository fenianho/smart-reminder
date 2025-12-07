package com.example.smartreminder.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiProvider
import com.example.smartreminder.presentation.viewmodel.AiSettingsEvent
import com.example.smartreminder.presentation.viewmodel.AiSettingsState
import com.example.smartreminder.presentation.viewmodel.AiSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiSettingsScreen(
    viewModel: AiSettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { 
                    viewModel.onEvent(AiSettingsEvent.CreateNewConfig)
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Config")
            }
        }
    ) { paddingValues ->
        if (state.showForm) {
            // 显示配置表单
            AiConfigForm(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {
            // 显示配置列表
            AiConfigList(
                configs = state.configs,
                activeConfigId = state.activeConfigId,
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun AiConfigList(
    configs: List<AiConfig>,
    activeConfigId: Long,
    onEvent: (AiSettingsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "AI Configurations",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (configs.isEmpty()) {
            Text(
                text = "No configurations found. Tap the + button to add one.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(configs) { config ->
                    AiConfigItem(
                        config = config,
                        isActive = config.id == activeConfigId,
                        onEdit = { onEvent(AiSettingsEvent.EditConfig(config)) },
                        onDelete = { onEvent(AiSettingsEvent.DeleteConfig(config.id)) },
                        onSetActive = { onEvent(AiSettingsEvent.SetActiveConfig(config.id)) }
                    )
                }
            }
        }
    }
}

@Composable
fun AiConfigItem(
    config: AiConfig,
    isActive: Boolean,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onSetActive: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (config.name.isNotBlank()) config.name else "${config.provider.name} - ${config.modelName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f, false)
                    )
                    if (isActive) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Active",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(18.dp)
                        )
                    }
                }
                Text(
                    text = if (config.baseUrl.isNullOrEmpty()) "Default endpoint" else config.baseUrl!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
                if (!isActive) {
                    IconButton(onClick = onSetActive) {
                        Icon(Icons.Filled.Check, contentDescription = "Set Active")
                    }
                }
            }
        }
    }
}

@Composable
fun AiConfigForm(
    state: AiSettingsState,
    onEvent: (AiSettingsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Form header with back button
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onEvent(AiSettingsEvent.CancelEdit) }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = if (state.editingConfigId == 0L) "New Configuration" else "Edit Configuration",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        // Configuration name
        OutlinedTextField(
            value = state.configName,
            onValueChange = { newValue -> onEvent(AiSettingsEvent.UpdateConfigName(newValue)) },
            label = { Text("Configuration Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Provider Selection
        Text(
            text = "AI Provider",
            style = MaterialTheme.typography.titleMedium
        )

        AiProvider.values().forEach { provider ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.selectedProvider == provider,
                    onClick = {
                        onEvent(AiSettingsEvent.SelectProvider(provider))
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = provider.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = getProviderDescription(provider),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Divider()

        // API Key Configuration
        Text(
            text = "API Configuration",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = state.apiKey,
            onValueChange = { newValue ->
                onEvent(AiSettingsEvent.UpdateApiKey(newValue))
            },
            label = { Text("API Key") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Filled.Api, contentDescription = "API Key")
            },
            supportingText = {
                Text("Enter your API key for ${state.selectedProvider.name}")
            }
        )

        // Model Name
        OutlinedTextField(
            value = state.modelName,
            onValueChange = { newValue ->
                onEvent(AiSettingsEvent.UpdateModelName(newValue))
            },
            label = { Text("Model Name") },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text("e.g., gpt-3.5-turbo, claude-3-sonnet")
            }
        )

        // Base URL (for custom providers)
        if (state.selectedProvider == AiProvider.CUSTOM) {
            OutlinedTextField(
                value = state.baseUrl ?: "",
                onValueChange = { newValue ->
                    onEvent(AiSettingsEvent.UpdateBaseUrl(newValue))
                },
                label = { Text("Base URL") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text("Custom API endpoint URL")
                }
            )
        }

        // Advanced Settings
        Text(
            text = "Advanced Settings",
            style = MaterialTheme.typography.titleMedium
        )

        // Timeout
        OutlinedTextField(
            value = state.timeoutMs.toString(),
            onValueChange = { newValue ->
                newValue.toIntOrNull()?.let { timeout ->
                    onEvent(AiSettingsEvent.UpdateTimeout(timeout))
                }
            },
            label = { Text("Timeout (ms)") },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text("Request timeout in milliseconds")
            }
        )

        // Max Retries
        OutlinedTextField(
            value = state.maxRetries.toString(),
            onValueChange = { newValue ->
                newValue.toIntOrNull()?.let { retries ->
                    onEvent(AiSettingsEvent.UpdateMaxRetries(retries))
                }
            },
            label = { Text("Max Retries") },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text("Maximum number of retry attempts")
            }
        )

        // Test Connection Button
        Button(
            onClick = {
                onEvent(AiSettingsEvent.TestConnection)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isTestingConnection && state.apiKey.isNotBlank()
        ) {
            if (state.isTestingConnection) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Testing Connection...")
                }
            } else {
                Text("Test Connection")
            }
        }

        // Connection Test Result
        state.connectionTestResult?.let { result ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (result.isSuccess) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.errorContainer
                    }
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (result.isSuccess) Icons.Filled.Check else Icons.Filled.Api,
                        contentDescription = null,
                        tint = if (result.isSuccess) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = result.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (result.isSuccess) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onErrorContainer
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    onEvent(AiSettingsEvent.CancelEdit)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text("Cancel")
            }
            
            Button(
                onClick = {
                    onEvent(AiSettingsEvent.SaveConfiguration)
                },
                modifier = Modifier.weight(1f),
                enabled = !state.isSaving
            ) {
                if (state.isSaving) {
                    Text("Saving...")
                } else {
                    Text("Save")
                }
            }
        }

        // Error Display
        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getProviderDescription(provider: AiProvider): String {
    return when (provider) {
        AiProvider.OPENAI -> "OpenAI GPT models (ChatGPT)"
        AiProvider.DEEPSEEK -> "DeepSeek AI models"
        AiProvider.CLAUDE -> "Anthropic Claude models"
        AiProvider.GEMINI -> "Google Gemini models"
        AiProvider.CUSTOM -> "Custom AI API endpoint"
    }
}