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
    version = 6,
    exportSchema = false
)
@TypeConverters(RepeatTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
    abstract fun aiConfigDao(): AiConfigDao

    companion object {
        const val DATABASE_NAME = "smart_reminder_db"
        
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 为reminders表添加repeatDays列
                database.execSQL("ALTER TABLE reminders ADD COLUMN repeatDays TEXT")
            }
        }
        
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 为reminders表添加每月重复提醒相关列
                database.execSQL("ALTER TABLE reminders ADD COLUMN monthlyRepeatType TEXT")
                database.execSQL("ALTER TABLE reminders ADD COLUMN monthlyRepeatDay INTEGER")
                database.execSQL("ALTER TABLE reminders ADD COLUMN monthlyRepeatWeek INTEGER")
                database.execSQL("ALTER TABLE reminders ADD COLUMN monthlyRepeatDayOfWeek INTEGER")
            }
        }
        
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 为reminders表添加monthlyRepeatDays列，替换原有的monthlyRepeatDay列
                database.execSQL("ALTER TABLE reminders ADD COLUMN monthlyRepeatDays TEXT")
                
                // 将现有的monthlyRepeatDay数据迁移到新的monthlyRepeatDays列（如果存在）
                database.execSQL("""
                    UPDATE reminders 
                    SET monthlyRepeatDays = 
                    CASE 
                        WHEN monthlyRepeatDay IS NOT NULL THEN monthlyRepeatDay 
                        ELSE NULL 
                    END
                    WHERE monthlyRepeatType = 'BY_DATE'
                """)
                
                // 删除旧的monthlyRepeatDay列
                database.execSQL("CREATE TABLE reminders_new (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "description TEXT, " +
                    "reminderTime INTEGER NOT NULL, " +
                    "isActive INTEGER NOT NULL, " +
                    "repeatType TEXT NOT NULL, " +
                    "repeatInterval INTEGER, " +
                    "repeatDays TEXT, " +
                    "monthlyRepeatType TEXT, " +
                    "monthlyRepeatDays TEXT, " +
                    "monthlyRepeatWeek INTEGER, " +
                    "monthlyRepeatDayOfWeek INTEGER, " +
                    "createdAt INTEGER NOT NULL, " +
                    "updatedAt INTEGER NOT NULL)")
                
                database.execSQL("INSERT INTO reminders_new (id, title, description, reminderTime, isActive, repeatType, repeatInterval, repeatDays, monthlyRepeatType, monthlyRepeatDays, monthlyRepeatWeek, monthlyRepeatDayOfWeek, createdAt, updatedAt) " +
                    "SELECT id, title, description, reminderTime, isActive, repeatType, repeatInterval, repeatDays, monthlyRepeatType, monthlyRepeatDays, monthlyRepeatWeek, monthlyRepeatDayOfWeek, createdAt, updatedAt FROM reminders")
                
                database.execSQL("DROP TABLE reminders")
                database.execSQL("ALTER TABLE reminders_new RENAME TO reminders")
            }
        }
        
        // 为ai_configs表添加name列的迁移策略
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 为ai_configs表添加name列
                database.execSQL("ALTER TABLE ai_configs ADD COLUMN name TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}