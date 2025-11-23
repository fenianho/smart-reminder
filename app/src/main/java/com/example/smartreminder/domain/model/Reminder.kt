package com.example.smartreminder.domain.model

data class Reminder(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val reminderTime: Long,
    val isActive: Boolean = true,
    val repeatType: RepeatType = RepeatType.NONE,
    val repeatInterval: Int? = null,
    val repeatDays: Set<Int>? = null, // 1=Monday, 2=Tuesday, ..., 7=Sunday
    val monthlyRepeatType: MonthlyRepeatType? = null, // 每月重复类型：按日期或按星期
    val monthlyRepeatDays: Set<Int>? = null, // 按日期重复时的多选日期（1-31）
    val monthlyRepeatWeek: Int? = null, // 按星期重复时是第几个星期（1-4，-1表示最后一个）
    val monthlyRepeatDayOfWeek: Int? = null, // 按星期重复时是星期几（1-7）
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
