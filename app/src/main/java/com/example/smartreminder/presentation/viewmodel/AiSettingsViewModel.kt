package com.example.smartreminder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiProvider
import com.example.smartreminder.domain.usecase.ManageAiConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AiSettingsState(
    val configs: List<AiConfig> = emptyList(),
    val activeConfigId: Long = 0,
    val showForm: Boolean = false,
    val editingConfigId: Long = 0,
    val configName: String = "",
    val selectedProvider: AiProvider = AiProvider.OPENAI,
    val apiKey: String = "",
    val modelName: String = "",
    val baseUrl: String? = null,
    val timeoutMs: Int = 30000,
    val maxRetries: Int = 3,
    val isTestingConnection: Boolean = false,
    val connectionTestResult: ConnectionTestResult? = null,
    val isSaving: Boolean = false,
    val error: String? = null
)

data class ConnectionTestResult(
    val isSuccess: Boolean,
    val message: String
)

sealed class AiSettingsEvent {
    data class SelectProvider(val provider: AiProvider) : AiSettingsEvent()
    data class UpdateConfigName(val name: String) : AiSettingsEvent()
    data class UpdateApiKey(val apiKey: String) : AiSettingsEvent()
    data class UpdateModelName(val modelName: String) : AiSettingsEvent()
    data class UpdateBaseUrl(val baseUrl: String) : AiSettingsEvent()
    data class UpdateTimeout(val timeout: Int) : AiSettingsEvent()
    data class UpdateMaxRetries(val maxRetries: Int) : AiSettingsEvent()
    data class EditConfig(val config: AiConfig) : AiSettingsEvent()
    data class DeleteConfig(val id: Long) : AiSettingsEvent()
    data class SetActiveConfig(val id: Long) : AiSettingsEvent()
    object CreateNewConfig : AiSettingsEvent()
    object CancelEdit : AiSettingsEvent()
    object TestConnection : AiSettingsEvent()
    object SaveConfiguration : AiSettingsEvent()
    object LoadConfiguration : AiSettingsEvent()
    object ClearError : AiSettingsEvent()
}

@HiltViewModel
class AiSettingsViewModel @Inject constructor(
    private val manageAiConfigUseCase: ManageAiConfigUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AiSettingsState())
    val state: StateFlow<AiSettingsState> = _state.asStateFlow()

    init {
        loadAllConfigurations()
    }

    fun onEvent(event: AiSettingsEvent) {
        when (event) {
            is AiSettingsEvent.SelectProvider -> {
                _state.update {
                    it.copy(
                        selectedProvider = event.provider,
                        connectionTestResult = null
                    )
                }
            }
            is AiSettingsEvent.UpdateConfigName -> {
                _state.update {
                    it.copy(configName = event.name)
                }
            }
            is AiSettingsEvent.UpdateApiKey -> {
                _state.update {
                    it.copy(
                        apiKey = event.apiKey,
                        connectionTestResult = null
                    )
                }
            }
            is AiSettingsEvent.UpdateModelName -> {
                _state.update { it.copy(modelName = event.modelName) }
            }
            is AiSettingsEvent.UpdateBaseUrl -> {
                _state.update {
                    it.copy(
                        baseUrl = event.baseUrl.takeIf { it.isNotBlank() }
                    )
                }
            }
            is AiSettingsEvent.UpdateTimeout -> {
                _state.update { it.copy(timeoutMs = event.timeout) }
            }
            is AiSettingsEvent.UpdateMaxRetries -> {
                _state.update { it.copy(maxRetries = event.maxRetries) }
            }
            is AiSettingsEvent.TestConnection -> testConnection()
            is AiSettingsEvent.SaveConfiguration -> saveConfiguration()
            is AiSettingsEvent.LoadConfiguration -> loadAllConfigurations()
            is AiSettingsEvent.ClearError -> _state.update { it.copy(error = null) }
            is AiSettingsEvent.CreateNewConfig -> showConfigForm()
            is AiSettingsEvent.EditConfig -> showConfigForm(event.config)
            is AiSettingsEvent.DeleteConfig -> deleteConfiguration(event.id)
            is AiSettingsEvent.SetActiveConfig -> setActiveConfiguration(event.id)
            is AiSettingsEvent.CancelEdit -> hideConfigForm()
        }
    }

    private fun loadAllConfigurations() {
        viewModelScope.launch {
            try {
                val configs = manageAiConfigUseCase.getAllAiConfigs()
                val activeConfig = manageAiConfigUseCase.getAiConfig()
                _state.update {
                    it.copy(
                        configs = configs,
                        activeConfigId = activeConfig?.id ?: 0,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Failed to load configurations: ${e.message}")
                }
            }
        }
    }

    private fun showConfigForm(config: AiConfig? = null) {
        if (config != null) {
            // 编辑现有配置
            _state.update {
                it.copy(
                    showForm = true,
                    editingConfigId = config.id,
                    configName = config.name,
                    selectedProvider = config.provider,
                    apiKey = config.apiKey,
                    modelName = config.modelName,
                    baseUrl = config.baseUrl,
                    timeoutMs = config.timeoutMs,
                    maxRetries = config.maxRetries,
                    connectionTestResult = null
                )
            }
        } else {
            // 创建新配置
            _state.update {
                it.copy(
                    showForm = true,
                    editingConfigId = 0,
                    configName = "",
                    selectedProvider = AiProvider.OPENAI,
                    apiKey = "",
                    modelName = "",
                    baseUrl = null,
                    timeoutMs = 30000,
                    maxRetries = 3,
                    connectionTestResult = null
                )
            }
        }
    }

    private fun hideConfigForm() {
        _state.update { it.copy(showForm = false) }
        loadAllConfigurations() // 重新加载配置列表
    }

    private fun testConnection() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isTestingConnection = true,
                    connectionTestResult = null,
                    error = null
                )
            }

            try {
                val config = AiConfig(
                    id = state.value.editingConfigId,
                    name = state.value.configName,
                    provider = state.value.selectedProvider,
                    apiKey = state.value.apiKey,
                    modelName = state.value.modelName,
                    isEnabled = true,
                    baseUrl = state.value.baseUrl,
                    timeoutMs = state.value.timeoutMs,
                    maxRetries = state.value.maxRetries
                )

                val result = manageAiConfigUseCase.testAiConnection(config)
                val testResult = if (result.isSuccess) {
                    ConnectionTestResult(true, "Connection successful")
                } else {
                    ConnectionTestResult(
                        false,
                        result.exceptionOrNull()?.message ?: "Connection failed"
                    )
                }

                _state.update {
                    it.copy(
                        isTestingConnection = false,
                        connectionTestResult = testResult
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isTestingConnection = false,
                        connectionTestResult = ConnectionTestResult(
                            false,
                            "Test failed: ${e.message}"
                        )
                    )
                }
            }
        }
    }

    private fun saveConfiguration() {
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true, error = null) }

            try {
                val config = AiConfig(
                    id = state.value.editingConfigId,
                    name = state.value.configName,
                    provider = state.value.selectedProvider,
                    apiKey = state.value.apiKey,
                    modelName = state.value.modelName,
                    isEnabled = state.value.editingConfigId == state.value.activeConfigId, // 保持激活状态
                    baseUrl = state.value.baseUrl,
                    timeoutMs = state.value.timeoutMs,
                    maxRetries = state.value.maxRetries
                )

                if (state.value.editingConfigId == 0L) {
                    // 新建配置
                    manageAiConfigUseCase.saveAiConfig(config)
                } else {
                    // 更新配置
                    manageAiConfigUseCase.updateAiConfig(config)
                }
                
                _state.update {
                    it.copy(
                        isSaving = false,
                        error = null
                    )
                }
                
                hideConfigForm() // 保存成功后隐藏表单
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isSaving = false,
                        error = "Failed to save configuration: ${e.message}"
                    )
                }
            }
        }
    }

    private fun deleteConfiguration(id: Long) {
        viewModelScope.launch {
            try {
                manageAiConfigUseCase.deleteAiConfig(id)
                loadAllConfigurations() // 重新加载配置列表
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Failed to delete configuration: ${e.message}")
                }
            }
        }
    }

    private fun setActiveConfiguration(id: Long) {
        viewModelScope.launch {
            try {
                manageAiConfigUseCase.setActiveAiConfig(id)
                loadAllConfigurations() // 重新加载配置列表以更新激活状态
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Failed to set active configuration: ${e.message}")
                }
            }
        }
    }
}