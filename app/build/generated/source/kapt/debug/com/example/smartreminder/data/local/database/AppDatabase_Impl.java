package com.example.smartreminder.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.smartreminder.data.local.dao.AiConfigDao;
import com.example.smartreminder.data.local.dao.AiConfigDao_Impl;
import com.example.smartreminder.data.local.dao.ReminderDao;
import com.example.smartreminder.data.local.dao.ReminderDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ReminderDao _reminderDao;

  private volatile AiConfigDao _aiConfigDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `reminders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `reminderTime` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `repeatType` TEXT NOT NULL, `repeatInterval` INTEGER, `repeatDays` TEXT, `monthlyRepeatType` TEXT, `monthlyRepeatDays` TEXT, `monthlyRepeatWeek` INTEGER, `monthlyRepeatDayOfWeek` INTEGER, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ai_configs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `provider` TEXT NOT NULL, `apiKey` TEXT NOT NULL, `modelName` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `baseUrl` TEXT, `timeoutMs` INTEGER NOT NULL, `maxRetries` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd2bd831041aa22aca52559cc5adb2f8d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `reminders`");
        db.execSQL("DROP TABLE IF EXISTS `ai_configs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsReminders = new HashMap<String, TableInfo.Column>(14);
        _columnsReminders.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("reminderTime", new TableInfo.Column("reminderTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("repeatType", new TableInfo.Column("repeatType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("repeatInterval", new TableInfo.Column("repeatInterval", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("repeatDays", new TableInfo.Column("repeatDays", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("monthlyRepeatType", new TableInfo.Column("monthlyRepeatType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("monthlyRepeatDays", new TableInfo.Column("monthlyRepeatDays", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("monthlyRepeatWeek", new TableInfo.Column("monthlyRepeatWeek", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("monthlyRepeatDayOfWeek", new TableInfo.Column("monthlyRepeatDayOfWeek", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReminders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReminders = new TableInfo("reminders", _columnsReminders, _foreignKeysReminders, _indicesReminders);
        final TableInfo _existingReminders = TableInfo.read(db, "reminders");
        if (!_infoReminders.equals(_existingReminders)) {
          return new RoomOpenHelper.ValidationResult(false, "reminders(com.example.smartreminder.data.local.entity.ReminderEntity).\n"
                  + " Expected:\n" + _infoReminders + "\n"
                  + " Found:\n" + _existingReminders);
        }
        final HashMap<String, TableInfo.Column> _columnsAiConfigs = new HashMap<String, TableInfo.Column>(9);
        _columnsAiConfigs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("provider", new TableInfo.Column("provider", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("apiKey", new TableInfo.Column("apiKey", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("modelName", new TableInfo.Column("modelName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("baseUrl", new TableInfo.Column("baseUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("timeoutMs", new TableInfo.Column("timeoutMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiConfigs.put("maxRetries", new TableInfo.Column("maxRetries", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAiConfigs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAiConfigs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAiConfigs = new TableInfo("ai_configs", _columnsAiConfigs, _foreignKeysAiConfigs, _indicesAiConfigs);
        final TableInfo _existingAiConfigs = TableInfo.read(db, "ai_configs");
        if (!_infoAiConfigs.equals(_existingAiConfigs)) {
          return new RoomOpenHelper.ValidationResult(false, "ai_configs(com.example.smartreminder.data.local.entity.AiConfigEntity).\n"
                  + " Expected:\n" + _infoAiConfigs + "\n"
                  + " Found:\n" + _existingAiConfigs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d2bd831041aa22aca52559cc5adb2f8d", "c8c268b07db27ba278c0015183fca12b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "reminders","ai_configs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `reminders`");
      _db.execSQL("DELETE FROM `ai_configs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AiConfigDao.class, AiConfigDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ReminderDao reminderDao() {
    if (_reminderDao != null) {
      return _reminderDao;
    } else {
      synchronized(this) {
        if(_reminderDao == null) {
          _reminderDao = new ReminderDao_Impl(this);
        }
        return _reminderDao;
      }
    }
  }

  @Override
  public AiConfigDao aiConfigDao() {
    if (_aiConfigDao != null) {
      return _aiConfigDao;
    } else {
      synchronized(this) {
        if(_aiConfigDao == null) {
          _aiConfigDao = new AiConfigDao_Impl(this);
        }
        return _aiConfigDao;
      }
    }
  }
}
