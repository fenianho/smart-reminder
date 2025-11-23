package com.example.smartreminder.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import androidx.work.*
import com.example.smartreminder.data.local.database.AppDatabase
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.service.scheduler.ReminderScheduler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        const val REMINDER_ID_KEY = "reminder_id"
        const val CHANNEL_ID = "reminder_channel"
        const val CHANNEL_NAME = "提醒通知"
        private const val TAG = "NotificationWorker"
    }

    override suspend fun doWork(): Result {
        val startTime = System.currentTimeMillis()
        Log.d(TAG, "Starting NotificationWorker execution at: $startTime")
        
        val reminderId = inputData.getLong(REMINDER_ID_KEY, -1)
        Log.d(TAG, "Processing notification for reminderId: $reminderId")
        
        if (reminderId == -1L) {
            Log.e(TAG, "Invalid reminder ID provided")
            return Result.failure()
        }

        return try {
            val database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            ).build()
            
            val reminderEntity = database.reminderDao().getReminderById(reminderId).first()
            Log.d(TAG, "Retrieved reminder entity: $reminderEntity")
            
            reminderEntity?.let { entity ->
                // 将ReminderEntity转换为Reminder模型
                val reminder = Reminder(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    reminderTime = entity.reminderTime,
                    isActive = entity.isActive,
                    repeatType = com.example.smartreminder.domain.model.RepeatType.valueOf(entity.repeatType),
                    repeatInterval = entity.repeatInterval,
                    repeatDays = entity.repeatDays?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
                    monthlyRepeatType = entity.monthlyRepeatType?.let { type -> com.example.smartreminder.domain.model.MonthlyRepeatType.valueOf(type) },
                    monthlyRepeatDays = entity.monthlyRepeatDays?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
                    monthlyRepeatWeek = entity.monthlyRepeatWeek,
                    monthlyRepeatDayOfWeek = entity.monthlyRepeatDayOfWeek,
                    createdAt = entity.createdAt,
                    updatedAt = entity.updatedAt
                )
                
                Log.d(TAG, "Converted reminder: id=${reminder.id}, title=${reminder.title}, repeatType=${reminder.repeatType}, repeatDays=${reminder.repeatDays}")
                showNotification(reminder)
                
                // 如果是重复提醒，计算并设置下一次调度
                if (reminder.repeatType != com.example.smartreminder.domain.model.RepeatType.NONE) {
                    scheduleNextReminder(reminder)
                }
                
                val endTime = System.currentTimeMillis()
                Log.d(TAG, "NotificationWorker execution completed at: $endTime, total time: ${endTime - startTime} ms")
                Result.success()
            } ?: run {
                Log.e(TAG, "Reminder not found for id: $reminderId")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in NotificationWorker for reminderId: $reminderId", e)
            Result.failure()
        }
    }

    private suspend fun scheduleNextReminder(reminder: Reminder) {
        try {
            Log.d(TAG, "Scheduling next reminder for repeating reminder: ${reminder.title}")
            
            // 创建调度用例和调度器
            val scheduleUseCase = ScheduleReminderUseCase()
            val scheduler = ReminderScheduler(applicationContext)
            
            // 计算下一次提醒时间
            val nextTimes = scheduleUseCase(reminder)
            Log.d(TAG, "Calculated next reminder times, count: ${nextTimes.size}")
            
            if (nextTimes.isNotEmpty()) {
                // 调度下一次提醒
                scheduler.scheduleReminder(reminder, nextTimes)
                Log.d(TAG, "Scheduled next reminder for: ${java.util.Date(nextTimes.first())}")
            } else {
                Log.d(TAG, "No future times calculated for reminder, cancelling any existing work")
                val scheduler = ReminderScheduler(applicationContext)
                scheduler.cancelReminder(reminder.id)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error scheduling next reminder for: ${reminder.title}", e)
        }
    }

    private fun showNotification(reminder: Reminder) {
        val startTime = System.currentTimeMillis()
        Log.d(TAG, "Showing notification for reminder: ${reminder.title} at: $startTime")
        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(reminder.title)
            .setContentText(reminder.description ?: reminder.title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            
        // Set the small icon correctly without ambiguity
        notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_info)

        val notification = notificationBuilder.build()

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(reminder.id.toInt(), notification)
        
        val endTime = System.currentTimeMillis()
        Log.d(TAG, "Notification shown for reminder: ${reminder.title} at: $endTime, display time: ${endTime - startTime} ms")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "智能提醒通知"
            }

            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
