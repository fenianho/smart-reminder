package com.example.smartreminder.service.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import androidx.work.*
import com.example.smartreminder.R
import com.example.smartreminder.data.local.database.AppDatabase
import com.example.smartreminder.data.local.entity.ReminderEntity
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.usecase.ScheduleReminderUseCase
import com.example.smartreminder.presentation.ui.MainActivity
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
                    monthlyRepeatWeeks = entity.monthlyRepeatWeeks?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
                    monthlyRepeatDaysOfWeek = entity.monthlyRepeatDaysOfWeek?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet(),
                    createdAt = entity.createdAt,
                )
                
                Log.d(TAG, "Converted reminder: id=${reminder.id}, title=${reminder.title}, repeatType=${reminder.repeatType}, repeatDays=${reminder.repeatDays}")
                showNotification(reminder)
                
                // 如果是重复提醒，计算并设置下一次调度
                if (reminder.repeatType != com.example.smartreminder.domain.model.RepeatType.NONE) {
                    scheduleNextReminder(reminder, database)
                }
                
                val endTime = System.currentTimeMillis()
                Log.d(TAG, "NotificationWorker execution completed at: $endTime, total time: ${endTime - startTime} ms")
            }
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error processing notification for reminderId: $reminderId", e)
            Result.failure()
        }
    }

    private fun scheduleNextReminder(reminder: Reminder, database: AppDatabase) {
        var success = false
        try {
            Log.d(TAG, "Scheduling next reminder for: ${reminder.title}")
            
            // 使用正确的构造方式创建调度用例实例（避免直接new）
            // 由于Worker通常不直接支持DI注入usecase，我们保持简单实例化但确保正确性
            val scheduleUseCase = ScheduleReminderUseCase()
            
            // 计算下一次提醒时间
            val nextTimes = runCatching { scheduleUseCase(reminder) }
                .onFailure { e -> Log.e(TAG, "Failed to calculate next reminder times", e) }
                .getOrNull()
            
            if (nextTimes.isNullOrEmpty()) {
                Log.w(TAG, "No future times calculated for repeating reminder or calculation failed")
                return
            }
            
            Log.d(TAG, "Calculated next reminder times, count: ${nextTimes.size}")
            
            // 更新提醒时间
            val nextTime = nextTimes.first()
            val updatedReminder = reminder.copy(reminderTime = nextTime)
            Log.d(TAG, "Updating reminder time to: ${java.util.Date(nextTime)}")
            
            // 转换为实体并保存更新后的提醒到数据库
            val reminderEntity = ReminderEntity(
                id = updatedReminder.id,
                title = updatedReminder.title,
                description = updatedReminder.description,
                reminderTime = updatedReminder.reminderTime,
                isActive = updatedReminder.isActive,
                repeatType = updatedReminder.repeatType.name,
                repeatInterval = updatedReminder.repeatInterval,
                repeatDays = updatedReminder.repeatDays?.joinToString(","),
                monthlyRepeatType = updatedReminder.monthlyRepeatType?.name,
                monthlyRepeatDays = updatedReminder.monthlyRepeatDays?.joinToString(","),
                monthlyRepeatWeeks = updatedReminder.monthlyRepeatWeeks?.joinToString(","),
                monthlyRepeatDaysOfWeek = updatedReminder.monthlyRepeatDaysOfWeek?.joinToString(","),
                createdAt = updatedReminder.createdAt,
                updatedAt = System.currentTimeMillis()
            )
            
            // 使用协程安全地更新数据库
            runCatching {
                kotlinx.coroutines.runBlocking {
                    database.reminderDao().updateReminder(reminderEntity)
                }
            }.onSuccess {
                Log.d(TAG, "Reminder time updated in database")
                success = true
            }.onFailure { e ->
                Log.e(TAG, "Failed to update reminder in database", e)
            }
            
            // 只有在数据库更新成功后才调度下一次提醒
            if (success) {
                val scheduler = ReminderScheduler(applicationContext)
                scheduler.scheduleReminder(updatedReminder, nextTimes)
                Log.d(TAG, "Next reminder scheduled successfully")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error scheduling next reminder", e)
        } finally {
            // 确保数据库连接被正确关闭
            runCatching { database.close() }
                .onFailure { e -> Log.w(TAG, "Failed to close database", e) }
        }
    }

    private fun showNotification(reminder: Reminder) {
        createNotificationChannel()

        // 创建点击通知时打开应用的意图
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 
            reminder.id.toInt(), 
            intent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // 使用系统内置图标
            .setContentTitle(reminder.title)
            .setContentText(reminder.description ?: "提醒时间到了")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // 添加默认声音和振动
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // 确保通知在锁屏上可见
            .setCategory(NotificationCompat.CATEGORY_ALARM) // 设置为闹钟类别，提高优先级
            .setWhen(System.currentTimeMillis()) // 设置通知时间
            .setShowWhen(true) // 显示通知时间
            .setOnlyAlertOnce(false) // 允许重复提醒

        try {
            val notificationManager = NotificationManagerCompat.from(applicationContext)
            
            // 检查通知权限
            val areNotificationsEnabled = notificationManager.areNotificationsEnabled()
            Log.d(TAG, "Notification permission status: $areNotificationsEnabled")
            
            // 构建通知
            val notification = builder.build()
            Log.d(TAG, "Notification built successfully. Title: ${reminder.title}")
            
            if (areNotificationsEnabled) {
                // 尝试显示通知
                notificationManager.notify(reminder.id.toInt(), notification)
                Log.d(TAG, "Notification shown successfully for reminder: ${reminder.id}")
                
                // 额外检查通知是否真的显示了
                try {
                    val activeNotifications = notificationManager.activeNotifications
                    val statusBarNotification = activeNotifications.find { 
                        it.id == reminder.id.toInt() 
                    }
                    if (statusBarNotification != null) {
                        Log.d(TAG, "Verified: Notification is active in status bar for reminder: ${reminder.id}")
                    } else {
                        Log.w(TAG, "Warning: Notification may not be visible for reminder: ${reminder.id}. Active notifications count: ${activeNotifications.size}")
                    }
                } catch (e: SecurityException) {
                    Log.w(TAG, "Cannot check active notifications due to security exception", e)
                }
            } else {
                Log.w(TAG, "Notifications are disabled for this app")
                // 即使权限检查失败，也尝试显示通知（某些设备可能检查不准确）
                try {
                    notificationManager.notify(reminder.id.toInt(), notification)
                    Log.d(TAG, "Notification shown despite permission check failure for reminder: ${reminder.id}")
                } catch (e: SecurityException) {
                    Log.e(TAG, "Failed to show notification due to security exception", e)
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Notification permission not granted", e)
        } catch (e: Exception) {
            Log.e(TAG, "Error showing notification for reminder: ${reminder.id}", e)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = "Channel for reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableLights(true) // 启用LED灯
                enableVibration(true) // 启用振动
                vibrationPattern = longArrayOf(0, 1000, 500, 1000) // 设置振动模式
                setShowBadge(true) // 在桌面图标上显示角标
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC // 锁屏显示
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            
            // 检查渠道是否已存在
            val existingChannel = notificationManager.getNotificationChannel(CHANNEL_ID)
            if (existingChannel == null) {
                notificationManager.createNotificationChannel(channel)
                Log.d(TAG, "Created new notification channel")
            } else {
                Log.d(TAG, "Notification channel already exists")
            }
        }
    }
}