package com.example.smartreminder.data.repository

import com.example.smartreminder.data.local.dao.AiConfigDao
import com.example.smartreminder.data.local.entity.AiConfigEntity
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.repository.AiConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * AI配置仓库实现
 */
class AiConfigRepositoryImpl @Inject constructor(
    private val aiConfigDao: AiConfigDao
) : AiConfigRepository {

    override fun getActiveAiConfig(): Flow<AiConfig?> {
        return aiConfigDao.getActiveAiConfig().map { it?.toDomain() }
    }

    override fun getAllAiConfigs(): Flow<List<AiConfig>> {
        return aiConfigDao.getAllAiConfigs().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getAiConfigById(id: Long): AiConfig? {
        return aiConfigDao.getAiConfigById(id)?.toDomain()
    }

    override suspend fun saveAiConfig(aiConfig: AiConfig): Long {
        val entity = AiConfigEntity.fromDomain(aiConfig)
        return aiConfigDao.insertAiConfig(entity)
    }

    override suspend fun updateAiConfig(aiConfig: AiConfig) {
        val entity = AiConfigEntity.fromDomain(aiConfig)
        aiConfigDao.updateAiConfig(entity)
    }

    override suspend fun deleteAiConfig(id: Long) {
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
}