package com.example.smartreminder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smartreminder.data.local.entity.AiConfigEntity
import kotlinx.coroutines.flow.Flow

/**
 * AI配置数据访问对象
 */
@Dao
interface AiConfigDao {

    @Query("SELECT * FROM ai_configs WHERE isEnabled = 1 LIMIT 1")
    fun getActiveAiConfig(): Flow<AiConfigEntity?>

    @Query("SELECT * FROM ai_configs WHERE id = :id")
    suspend fun getAiConfigById(id: Long): AiConfigEntity?

    @Query("SELECT * FROM ai_configs")
    fun getAllAiConfigs(): Flow<List<AiConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAiConfig(aiConfig: AiConfigEntity): Long

    @Update
    suspend fun updateAiConfig(aiConfig: AiConfigEntity)

    @Query("DELETE FROM ai_configs WHERE id = :id")
    suspend fun deleteAiConfig(id: Long)

    @Query("UPDATE ai_configs SET isEnabled = 0 WHERE id != :exceptId")
    suspend fun disableAllExcept(exceptId: Long)
}