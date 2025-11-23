package com.example.smartreminder.data.di

import com.example.smartreminder.data.repository.AiConfigRepositoryImpl
import com.example.smartreminder.data.repository.ReminderRepositoryImpl
import com.example.smartreminder.domain.repository.AiConfigRepository
import com.example.smartreminder.domain.repository.ReminderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository模块，用于提供Repository实现
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAiConfigRepository(
        aiConfigRepositoryImpl: AiConfigRepositoryImpl
    ): AiConfigRepository

    @Binds
    @Singleton
    abstract fun bindReminderRepository(
        reminderRepositoryImpl: ReminderRepositoryImpl
    ): ReminderRepository
}