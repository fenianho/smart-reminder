package com.example.smartreminder.domain.repository

import com.example.smartreminder.domain.model.AiConfig
import kotlinx.coroutines.flow.Flow

/**
 * AI配置仓库接口
 */
interface AiConfigRepository {

    /**
     * 获取活跃的AI配置
     */
    fun getActiveAiConfig(): Flow<AiConfig?>

    /**
     * 获取所有AI配置
     */
    fun getAllAiConfigs(): Flow<List<AiConfig>>

    /**
     * 根据ID获取AI配置
     */
    suspend fun getAiConfigById(id: Long): AiConfig?

    /**
     * 保存AI配置
     */
    suspend fun saveAiConfig(aiConfig: AiConfig): Long

    /**
     * 更新AI配置
     */
    suspend fun updateAiConfig(aiConfig: AiConfig)

    /**
     * 删除AI配置
     */
    suspend fun deleteAiConfig(id: Long)

    /**
     * 设置活跃的AI配置
     */
    suspend fun setActiveAiConfig(id: Long)
}