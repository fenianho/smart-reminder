package com.example.smartreminder.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.smartreminder.data.local.entity.ReminderEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReminderDao_Impl implements ReminderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReminderEntity> __insertionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __deletionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __updateAdapterOfReminderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateReminder;

  public ReminderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReminderEntity = new EntityInsertionAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reminders` (`id`,`title`,`description`,`reminderTime`,`isActive`,`repeatType`,`repeatInterval`,`repeatDays`,`monthlyRepeatType`,`monthlyRepeatDays`,`monthlyRepeatWeek`,`monthlyRepeatDayOfWeek`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindLong(4, entity.getReminderTime());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getRepeatType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRepeatType());
        }
        if (entity.getRepeatInterval() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getRepeatInterval());
        }
        if (entity.getRepeatDays() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRepeatDays());
        }
        if (entity.getMonthlyRepeatType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getMonthlyRepeatType());
        }
        if (entity.getMonthlyRepeatDays() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getMonthlyRepeatDays());
        }
        if (entity.getMonthlyRepeatWeek() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getMonthlyRepeatWeek());
        }
        if (entity.getMonthlyRepeatDayOfWeek() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getMonthlyRepeatDayOfWeek());
        }
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `reminders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `reminders` SET `id` = ?,`title` = ?,`description` = ?,`reminderTime` = ?,`isActive` = ?,`repeatType` = ?,`repeatInterval` = ?,`repeatDays` = ?,`monthlyRepeatType` = ?,`monthlyRepeatDays` = ?,`monthlyRepeatWeek` = ?,`monthlyRepeatDayOfWeek` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindLong(4, entity.getReminderTime());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getRepeatType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRepeatType());
        }
        if (entity.getRepeatInterval() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getRepeatInterval());
        }
        if (entity.getRepeatDays() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRepeatDays());
        }
        if (entity.getMonthlyRepeatType() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getMonthlyRepeatType());
        }
        if (entity.getMonthlyRepeatDays() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getMonthlyRepeatDays());
        }
        if (entity.getMonthlyRepeatWeek() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getMonthlyRepeatWeek());
        }
        if (entity.getMonthlyRepeatDayOfWeek() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getMonthlyRepeatDayOfWeek());
        }
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
        statement.bindLong(15, entity.getId());
      }
    };
    this.__preparedStmtOfDeactivateReminder = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE reminders SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertReminder(final ReminderEntity reminder,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfReminderEntity.insertAndReturnId(reminder);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReminderEntity.handle(reminder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReminderEntity.handle(reminder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateReminder(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateReminder.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeactivateReminder.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ReminderEntity>> getActiveReminders() {
    final String _sql = "SELECT * FROM reminders WHERE isActive = 1 ORDER BY reminderTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderTime");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfMonthlyRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatType");
          final int _cursorIndexOfMonthlyRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDays");
          final int _cursorIndexOfMonthlyRepeatWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatWeek");
          final int _cursorIndexOfMonthlyRepeatDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDayOfWeek");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpReminderTime;
            _tmpReminderTime = _cursor.getLong(_cursorIndexOfReminderTime);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final String _tmpRepeatType;
            if (_cursor.isNull(_cursorIndexOfRepeatType)) {
              _tmpRepeatType = null;
            } else {
              _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
            }
            final Integer _tmpRepeatInterval;
            if (_cursor.isNull(_cursorIndexOfRepeatInterval)) {
              _tmpRepeatInterval = null;
            } else {
              _tmpRepeatInterval = _cursor.getInt(_cursorIndexOfRepeatInterval);
            }
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final String _tmpMonthlyRepeatType;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatType)) {
              _tmpMonthlyRepeatType = null;
            } else {
              _tmpMonthlyRepeatType = _cursor.getString(_cursorIndexOfMonthlyRepeatType);
            }
            final String _tmpMonthlyRepeatDays;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDays)) {
              _tmpMonthlyRepeatDays = null;
            } else {
              _tmpMonthlyRepeatDays = _cursor.getString(_cursorIndexOfMonthlyRepeatDays);
            }
            final Integer _tmpMonthlyRepeatWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatWeek)) {
              _tmpMonthlyRepeatWeek = null;
            } else {
              _tmpMonthlyRepeatWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatWeek);
            }
            final Integer _tmpMonthlyRepeatDayOfWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDayOfWeek)) {
              _tmpMonthlyRepeatDayOfWeek = null;
            } else {
              _tmpMonthlyRepeatDayOfWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatDayOfWeek);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpReminderTime,_tmpIsActive,_tmpRepeatType,_tmpRepeatInterval,_tmpRepeatDays,_tmpMonthlyRepeatType,_tmpMonthlyRepeatDays,_tmpMonthlyRepeatWeek,_tmpMonthlyRepeatDayOfWeek,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReminderEntity>> getAllReminders() {
    final String _sql = "SELECT * FROM reminders ORDER BY reminderTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderTime");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfMonthlyRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatType");
          final int _cursorIndexOfMonthlyRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDays");
          final int _cursorIndexOfMonthlyRepeatWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatWeek");
          final int _cursorIndexOfMonthlyRepeatDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDayOfWeek");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpReminderTime;
            _tmpReminderTime = _cursor.getLong(_cursorIndexOfReminderTime);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final String _tmpRepeatType;
            if (_cursor.isNull(_cursorIndexOfRepeatType)) {
              _tmpRepeatType = null;
            } else {
              _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
            }
            final Integer _tmpRepeatInterval;
            if (_cursor.isNull(_cursorIndexOfRepeatInterval)) {
              _tmpRepeatInterval = null;
            } else {
              _tmpRepeatInterval = _cursor.getInt(_cursorIndexOfRepeatInterval);
            }
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final String _tmpMonthlyRepeatType;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatType)) {
              _tmpMonthlyRepeatType = null;
            } else {
              _tmpMonthlyRepeatType = _cursor.getString(_cursorIndexOfMonthlyRepeatType);
            }
            final String _tmpMonthlyRepeatDays;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDays)) {
              _tmpMonthlyRepeatDays = null;
            } else {
              _tmpMonthlyRepeatDays = _cursor.getString(_cursorIndexOfMonthlyRepeatDays);
            }
            final Integer _tmpMonthlyRepeatWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatWeek)) {
              _tmpMonthlyRepeatWeek = null;
            } else {
              _tmpMonthlyRepeatWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatWeek);
            }
            final Integer _tmpMonthlyRepeatDayOfWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDayOfWeek)) {
              _tmpMonthlyRepeatDayOfWeek = null;
            } else {
              _tmpMonthlyRepeatDayOfWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatDayOfWeek);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpReminderTime,_tmpIsActive,_tmpRepeatType,_tmpRepeatInterval,_tmpRepeatDays,_tmpMonthlyRepeatType,_tmpMonthlyRepeatDays,_tmpMonthlyRepeatWeek,_tmpMonthlyRepeatDayOfWeek,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<ReminderEntity> getReminderById(final long id) {
    final String _sql = "SELECT * FROM reminders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<ReminderEntity>() {
      @Override
      @Nullable
      public ReminderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderTime");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfMonthlyRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatType");
          final int _cursorIndexOfMonthlyRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDays");
          final int _cursorIndexOfMonthlyRepeatWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatWeek");
          final int _cursorIndexOfMonthlyRepeatDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDayOfWeek");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ReminderEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpReminderTime;
            _tmpReminderTime = _cursor.getLong(_cursorIndexOfReminderTime);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final String _tmpRepeatType;
            if (_cursor.isNull(_cursorIndexOfRepeatType)) {
              _tmpRepeatType = null;
            } else {
              _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
            }
            final Integer _tmpRepeatInterval;
            if (_cursor.isNull(_cursorIndexOfRepeatInterval)) {
              _tmpRepeatInterval = null;
            } else {
              _tmpRepeatInterval = _cursor.getInt(_cursorIndexOfRepeatInterval);
            }
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final String _tmpMonthlyRepeatType;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatType)) {
              _tmpMonthlyRepeatType = null;
            } else {
              _tmpMonthlyRepeatType = _cursor.getString(_cursorIndexOfMonthlyRepeatType);
            }
            final String _tmpMonthlyRepeatDays;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDays)) {
              _tmpMonthlyRepeatDays = null;
            } else {
              _tmpMonthlyRepeatDays = _cursor.getString(_cursorIndexOfMonthlyRepeatDays);
            }
            final Integer _tmpMonthlyRepeatWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatWeek)) {
              _tmpMonthlyRepeatWeek = null;
            } else {
              _tmpMonthlyRepeatWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatWeek);
            }
            final Integer _tmpMonthlyRepeatDayOfWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDayOfWeek)) {
              _tmpMonthlyRepeatDayOfWeek = null;
            } else {
              _tmpMonthlyRepeatDayOfWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatDayOfWeek);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ReminderEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpReminderTime,_tmpIsActive,_tmpRepeatType,_tmpRepeatInterval,_tmpRepeatDays,_tmpMonthlyRepeatType,_tmpMonthlyRepeatDays,_tmpMonthlyRepeatWeek,_tmpMonthlyRepeatDayOfWeek,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ReminderEntity>> getRemindersInTimeRange(final long startTime,
      final long endTime) {
    final String _sql = "SELECT * FROM reminders WHERE reminderTime BETWEEN ? AND ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderTime");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatType");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatDays");
          final int _cursorIndexOfMonthlyRepeatType = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatType");
          final int _cursorIndexOfMonthlyRepeatDays = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDays");
          final int _cursorIndexOfMonthlyRepeatWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatWeek");
          final int _cursorIndexOfMonthlyRepeatDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRepeatDayOfWeek");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpReminderTime;
            _tmpReminderTime = _cursor.getLong(_cursorIndexOfReminderTime);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final String _tmpRepeatType;
            if (_cursor.isNull(_cursorIndexOfRepeatType)) {
              _tmpRepeatType = null;
            } else {
              _tmpRepeatType = _cursor.getString(_cursorIndexOfRepeatType);
            }
            final Integer _tmpRepeatInterval;
            if (_cursor.isNull(_cursorIndexOfRepeatInterval)) {
              _tmpRepeatInterval = null;
            } else {
              _tmpRepeatInterval = _cursor.getInt(_cursorIndexOfRepeatInterval);
            }
            final String _tmpRepeatDays;
            if (_cursor.isNull(_cursorIndexOfRepeatDays)) {
              _tmpRepeatDays = null;
            } else {
              _tmpRepeatDays = _cursor.getString(_cursorIndexOfRepeatDays);
            }
            final String _tmpMonthlyRepeatType;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatType)) {
              _tmpMonthlyRepeatType = null;
            } else {
              _tmpMonthlyRepeatType = _cursor.getString(_cursorIndexOfMonthlyRepeatType);
            }
            final String _tmpMonthlyRepeatDays;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDays)) {
              _tmpMonthlyRepeatDays = null;
            } else {
              _tmpMonthlyRepeatDays = _cursor.getString(_cursorIndexOfMonthlyRepeatDays);
            }
            final Integer _tmpMonthlyRepeatWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatWeek)) {
              _tmpMonthlyRepeatWeek = null;
            } else {
              _tmpMonthlyRepeatWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatWeek);
            }
            final Integer _tmpMonthlyRepeatDayOfWeek;
            if (_cursor.isNull(_cursorIndexOfMonthlyRepeatDayOfWeek)) {
              _tmpMonthlyRepeatDayOfWeek = null;
            } else {
              _tmpMonthlyRepeatDayOfWeek = _cursor.getInt(_cursorIndexOfMonthlyRepeatDayOfWeek);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpReminderTime,_tmpIsActive,_tmpRepeatType,_tmpRepeatInterval,_tmpRepeatDays,_tmpMonthlyRepeatType,_tmpMonthlyRepeatDays,_tmpMonthlyRepeatWeek,_tmpMonthlyRepeatDayOfWeek,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
