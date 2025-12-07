package com.example.smartreminder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val reminderTime: Long,
    val isActive: Boolean = true,
    val repeatType: String = "NONE", // 存储为字符串以保持向后兼容
    val repeatInterval: Int? = null,
    val repeatDays: String? = null, // 存储为逗号分隔的字符串，例如 "1,3,5"
    val monthlyRepeatType: String? = null, // 存储为字符串：BY_DATE 或 BY_WEEKDAY
    val monthlyRepeatDays: String? = null, // 存储为逗号分隔的字符串，例如 "1,2,15,31" 支持多选日期
    val monthlyRepeatWeeks: String? = null, // 按星期重复时是多个周次（1-4，-1表示最后一个），存储为逗号分隔的字符串
    val monthlyRepeatDaysOfWeek: String? = null, // 按星期重复时是多个星期几（1-7），存储为逗号分隔的字符串
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
