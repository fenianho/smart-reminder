package com.example.smartreminder.data.local.converter

import androidx.room.TypeConverter
import com.example.smartreminder.domain.model.MonthlyRepeatType
import com.example.smartreminder.domain.model.RepeatType

class RepeatTypeConverter {
    @TypeConverter
    fun fromRepeatType(repeatType: RepeatType): String {
        return repeatType.name
    }

    @TypeConverter
    fun toRepeatType(value: String): RepeatType {
        return try {
            RepeatType.valueOf(value)
        } catch (e: IllegalArgumentException) {
            RepeatType.NONE
        }
    }

    @TypeConverter
    fun fromMonthlyRepeatType(monthlyRepeatType: MonthlyRepeatType): String {
        return monthlyRepeatType.name
    }

    @TypeConverter
    fun toMonthlyRepeatType(value: String): MonthlyRepeatType {
        return try {
            MonthlyRepeatType.valueOf(value)
        } catch (e: IllegalArgumentException) {
            MonthlyRepeatType.BY_DATE
        }
    }

    @TypeConverter
    fun fromMonthlyRepeatDays(days: Set<Int>?): String? {
        return days?.joinToString(",")
    }

    @TypeConverter
    fun toMonthlyRepeatDays(value: String?): Set<Int>? {
        return value?.split(",")?.mapNotNull { it.toIntOrNull() }?.toSet()
    }
}
