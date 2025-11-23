package com.example.smartreminder.data.di

import android.content.Context
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Provides
    @Singleton
    fun provideReminderScheduler(@ApplicationContext context: Context): ReminderScheduler {
        return ReminderScheduler(context)
    }
}