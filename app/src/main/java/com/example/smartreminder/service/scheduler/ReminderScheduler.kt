package com.example.smartreminder.service.scheduler

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.service.notification.NotificationWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val TAG = "ReminderScheduler"

    fun scheduleReminder(reminder: Reminder, scheduledTimes: List<Long>) {
        val workManager = WorkManager.getInstance(context)
        // 首先取消该提醒的任何现有工作
        cancelReminder(reminder.id, workManager)
        
        Log.d(TAG, "Scheduling reminder: ${reminder.title}, id: ${reminder.id}, scheduledTimes count: ${scheduledTimes.size}")
        
        val currentTime = System.currentTimeMillis()
        Log.d(TAG, "Current time: ${java.util.Date(currentTime)}")
        
        scheduledTimes.forEachIndexed { index, scheduledTime ->
            val delay = scheduledTime - currentTime
            Log.d(TAG, "Processing scheduled time $index: ${java.util.Date(scheduledTime)}, delay: $delay ms")
            
            if (delay > 0) {
                scheduleNotification(reminder.id, delay, workManager)
                Log.d(TAG, "Scheduled notification for reminder id: ${reminder.id} at ${java.util.Date(scheduledTime)} with delay: $delay ms")
            } else {
                Log.d(TAG, "Skipped scheduling notification for past time: ${java.util.Date(scheduledTime)}")
            }
        }
        
        Log.d(TAG, "Finished scheduling reminder: ${reminder.title}, total scheduled notifications: ${scheduledTimes.count { it > currentTime }}")
    }

    fun cancelReminder(reminderId: Long) {
        val workManager = WorkManager.getInstance(context)
        cancelReminder(reminderId, workManager)
    }

    private fun cancelReminder(reminderId: Long, workManager: WorkManager) {
        workManager.cancelAllWorkByTag("reminder_$reminderId")
        Log.d(TAG, "Cancelled all work for reminder id: $reminderId")
    }

    private fun scheduleNotification(reminderId: Long, delayMillis: Long, workManager: WorkManager) {
        val inputData = Data.Builder()
            .putLong(NotificationWorker.REMINDER_ID_KEY, reminderId)
            .build()

        val notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(inputData)
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .addTag("reminder_$reminderId")  // 添加标签以便识别和取消
            .setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(false)
                    .build()
            )
            .build()

        workManager.enqueue(notificationWork)
        Log.d(TAG, "Enqueued notification work for reminder id: $reminderId with delay: $delayMillis ms")
    }
}