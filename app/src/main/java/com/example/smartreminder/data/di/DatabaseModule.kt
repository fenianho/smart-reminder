package com.example.smartreminder.data.di

import android.content.Context
import androidx.room.Room
import com.example.smartreminder.data.local.dao.AiConfigDao
import com.example.smartreminder.data.local.dao.ReminderDao
import com.example.smartreminder.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 数据库模块，用于提供数据库和DAO实例
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        // 删除所有迁移策略，让Room重新创建数据库
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideReminderDao(appDatabase: AppDatabase): ReminderDao {
        return appDatabase.reminderDao()
    }

    @Provides
    @Singleton
    fun provideAiConfigDao(appDatabase: AppDatabase): AiConfigDao {
        return appDatabase.aiConfigDao()
    }
}