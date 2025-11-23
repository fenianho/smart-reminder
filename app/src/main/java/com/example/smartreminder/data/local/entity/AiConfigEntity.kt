package com.example.smartreminder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.smartreminder.domain.model.AiProvider

/**
 * AI配置实体类
 */
@Entity(tableName = "ai_configs")
data class AiConfigEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val provider: String, // AiProvider name
    val apiKey: String,
    val modelName: String,
    val isEnabled: Boolean = true,
    val baseUrl: String? = null,
    val timeoutMs: Int = 30000,
    val maxRetries: Int = 3
) {
    fun toDomain(): com.example.smartreminder.domain.model.AiConfig {
        return com.example.smartreminder.domain.model.AiConfig(
            id = id,
            name = name,
            provider = AiProvider.valueOf(provider),
            apiKey = apiKey,
            modelName = modelName,
            isEnabled = isEnabled,
            baseUrl = baseUrl,
            timeoutMs = timeoutMs,
            maxRetries = maxRetries
        )
    }

    companion object {
        fun fromDomain(aiConfig: com.example.smartreminder.domain.model.AiConfig): AiConfigEntity {
            return AiConfigEntity(
                id = aiConfig.id,
                name = aiConfig.name,
                provider = aiConfig.provider.name,
                apiKey = aiConfig.apiKey,
                modelName = aiConfig.modelName,
                isEnabled = aiConfig.isEnabled,
                baseUrl = aiConfig.baseUrl,
                timeoutMs = aiConfig.timeoutMs,
                maxRetries = aiConfig.maxRetries
            )
        }
    }
}