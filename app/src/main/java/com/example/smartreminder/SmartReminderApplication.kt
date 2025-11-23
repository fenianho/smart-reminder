package com.example.smartreminder

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.smartreminder.data.ai.AiServiceManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SmartReminderApplication : Application(), Configuration.Provider {
    companion object {
        private const val TAG = "SmartReminderApplication"
    }

    @Inject
    lateinit var aiServiceManager: AiServiceManager

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        // 初始化WorkManager
        WorkManager.initialize(this, workManagerConfiguration)
        
        // 初始化AI服务
        initializeAiService()
    }

    private fun initializeAiService() {
        applicationScope.launch {
            try {
                Log.d(TAG, "Initializing AI service manager")
                val result = aiServiceManager.initialize()
                Log.d(TAG, "AI service manager initialization result: $result")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to initialize AI service manager", e)
            }
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}