package com.example.smartreminder.data.repository

import com.example.smartreminder.data.local.SecureStorageManager
import com.example.smartreminder.data.local.dao.AiConfigDao
import com.example.smartreminder.data.local.entity.AiConfigEntity
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.repository.AiConfigRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * 安全的AI配置仓库实现
 * API密钥通过安全存储管理器加密存储
 */
class SecureAiConfigRepositoryImpl @Inject constructor(
    private val aiConfigDao: AiConfigDao,
    private val secureStorageManager: SecureStorageManager,
    private val gson: Gson = Gson()
) : AiConfigRepository {

    override fun getActiveAiConfig(): Flow<AiConfig?> {
        return aiConfigDao.getActiveAiConfig().map { entity ->
            entity?.let { loadSecureAiConfig(it) }
        }
    }

    override fun getAllAiConfigs(): Flow<List<AiConfig>> {
        return aiConfigDao.getAllAiConfigs().map { entities ->
            entities.map { loadSecureAiConfig(it) }
        }
    }

    override suspend fun getAiConfigById(id: Long): AiConfig? {
        return aiConfigDao.getAiConfigById(id)?.let { loadSecureAiConfig(it) }
    }

    override suspend fun saveAiConfig(aiConfig: AiConfig): Long {
        // 安全存储API密钥
        secureStorageManager.storeApiKey(aiConfig.provider, aiConfig.apiKey)

        // 数据库只存储非敏感信息
        val entity = createSecureEntity(aiConfig)
        return aiConfigDao.insertAiConfig(entity)
    }

    override suspend fun updateAiConfig(aiConfig: AiConfig) {
        // 更新安全存储的API密钥
        secureStorageManager.storeApiKey(aiConfig.provider, aiConfig.apiKey)

        // 更新数据库
        val entity = createSecureEntity(aiConfig)
        aiConfigDao.updateAiConfig(entity)
    }

    override suspend fun deleteAiConfig(id: Long) {
        // 获取配置以删除相关的安全存储数据
        val config = getAiConfigById(id)
        config?.let {
            secureStorageManager.removeApiKey(it.provider)
            secureStorageManager.removeAiConfig(it.id)
        }

        // 删除数据库记录
        aiConfigDao.deleteAiConfig(id)
    }

    override suspend fun setActiveAiConfig(id: Long) {
        aiConfigDao.disableAllExcept(id)
        val aiConfig = getAiConfigById(id)
        if (aiConfig != null) {
            val enabledConfig = aiConfig.copy(isEnabled = true)
            updateAiConfig(enabledConfig)
        }
    }

    /**
     * 从数据库实体和安全存储加载完整的AI配置
     */
    private fun loadSecureAiConfig(entity: AiConfigEntity): AiConfig {
        // 从安全存储获取API密钥
        val apiKey = secureStorageManager.getApiKey(
            com.example.smartreminder.domain.model.AiProvider.valueOf(entity.provider)
        ) ?: ""

        return AiConfig(
            id = entity.id,
            provider = com.example.smartreminder.domain.model.AiProvider.valueOf(entity.provider),
            apiKey = apiKey,
            modelName = entity.modelName,
            isEnabled = entity.isEnabled,
            baseUrl = entity.baseUrl,
            timeoutMs = entity.timeoutMs,
            maxRetries = entity.maxRetries
        )
    }

    /**
     * 创建安全的数据库实体（不包含API密钥）
     */
    private fun createSecureEntity(aiConfig: AiConfig): AiConfigEntity {
        return AiConfigEntity(
            id = aiConfig.id,
            provider = aiConfig.provider.name,
            apiKey = "", // API密钥不存储在数据库中
            modelName = aiConfig.modelName,
            isEnabled = aiConfig.isEnabled,
            baseUrl = aiConfig.baseUrl,
            timeoutMs = aiConfig.timeoutMs,
            maxRetries = aiConfig.maxRetries
        )
    }

    /**
     * 验证API密钥格式
     */
    fun validateApiKey(provider: com.example.smartreminder.domain.model.AiProvider, apiKey: String): Boolean {
        return secureStorageManager.isValidApiKeyFormat(provider, apiKey)
    }
}