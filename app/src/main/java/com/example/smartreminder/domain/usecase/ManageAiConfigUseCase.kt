package com.example.smartreminder.domain.usecase

import com.example.smartreminder.data.ai.AiServiceManager
import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.repository.AiConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * 管理AI配置用例
 */
class ManageAiConfigUseCase @Inject constructor(
    private val aiConfigRepository: AiConfigRepository,
    private val aiServiceManager: AiServiceManager
) {

    /**
     * 获取活跃的AI配置
     */
    fun getActiveAiConfig(): Flow<AiConfig?> {
        return aiConfigRepository.getActiveAiConfig()
    }

    /**
     * 获取所有AI配置
     */
    suspend fun getAllAiConfigs(): List<AiConfig> {
        return aiConfigRepository.getAllAiConfigs().firstOrNull() ?: emptyList()
    }

    /**
     * 保存AI配置
     */
    suspend fun saveAiConfig(aiConfig: AiConfig): Result<Long> {
        return try {
            val id = aiConfigRepository.saveAiConfig(aiConfig)
            // 如果是启用的配置，更新AI服务
            if (aiConfig.isEnabled) {
                aiServiceManager.updateAiService(aiConfig)
            }
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 更新AI配置
     */
    suspend fun updateAiConfig(aiConfig: AiConfig): Result<Unit> {
        return try {
            aiConfigRepository.updateAiConfig(aiConfig)
            // 如果是启用的配置，更新AI服务
            if (aiConfig.isEnabled) {
                aiServiceManager.updateAiService(aiConfig)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 删除AI配置
     */
    suspend fun deleteAiConfig(id: Long): Result<Unit> {
        return try {
            aiConfigRepository.deleteAiConfig(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 设置活跃的AI配置
     */
    suspend fun setActiveAiConfig(id: Long): Result<Unit> {
        return try {
            aiConfigRepository.setActiveAiConfig(id)
            // 重新初始化AI服务
            aiServiceManager.resetAiService()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 获取活跃的AI配置（非Flow版本）
     */
    suspend fun getAiConfig(): AiConfig? {
        return getActiveAiConfig().firstOrNull()
    }

    /**
     * 测试AI配置连接
     */
    suspend fun testAiConnection(aiConfig: AiConfig): Result<Boolean> {
        return try {
            val testService = com.example.smartreminder.data.ai.AiServiceFactory()
                .createAiService(aiConfig)
            val isAvailable = testService.isAvailable()
            Result.success(isAvailable)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}