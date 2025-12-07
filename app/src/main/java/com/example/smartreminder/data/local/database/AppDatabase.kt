package com.example.smartreminder.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.smartreminder.data.local.dao.AiConfigDao
import com.example.smartreminder.data.local.dao.ReminderDao
import com.example.smartreminder.data.local.entity.AiConfigEntity
import com.example.smartreminder.data.local.entity.ReminderEntity
import com.example.smartreminder.data.local.converter.RepeatTypeConverter

@Database(
    entities = [ReminderEntity::class, AiConfigEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RepeatTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
    abstract fun aiConfigDao(): AiConfigDao

    companion object {
        const val DATABASE_NAME = "smart_reminder_db"
    }
}