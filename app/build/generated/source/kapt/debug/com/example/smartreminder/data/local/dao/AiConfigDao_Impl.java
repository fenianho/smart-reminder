package com.example.smartreminder.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
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
import com.example.smartreminder.data.local.entity.AiConfigEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class AiConfigDao_Impl implements AiConfigDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AiConfigEntity> __insertionAdapterOfAiConfigEntity;

  private final EntityDeletionOrUpdateAdapter<AiConfigEntity> __updateAdapterOfAiConfigEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAiConfig;

  private final SharedSQLiteStatement __preparedStmtOfDisableAllExcept;

  public AiConfigDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAiConfigEntity = new EntityInsertionAdapter<AiConfigEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ai_configs` (`id`,`name`,`provider`,`apiKey`,`modelName`,`isEnabled`,`baseUrl`,`timeoutMs`,`maxRetries`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AiConfigEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getProvider() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProvider());
        }
        if (entity.getApiKey() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getApiKey());
        }
        if (entity.getModelName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getModelName());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getBaseUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBaseUrl());
        }
        statement.bindLong(8, entity.getTimeoutMs());
        statement.bindLong(9, entity.getMaxRetries());
      }
    };
    this.__updateAdapterOfAiConfigEntity = new EntityDeletionOrUpdateAdapter<AiConfigEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `ai_configs` SET `id` = ?,`name` = ?,`provider` = ?,`apiKey` = ?,`modelName` = ?,`isEnabled` = ?,`baseUrl` = ?,`timeoutMs` = ?,`maxRetries` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AiConfigEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getProvider() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProvider());
        }
        if (entity.getApiKey() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getApiKey());
        }
        if (entity.getModelName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getModelName());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getBaseUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBaseUrl());
        }
        statement.bindLong(8, entity.getTimeoutMs());
        statement.bindLong(9, entity.getMaxRetries());
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAiConfig = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ai_configs WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDisableAllExcept = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE ai_configs SET isEnabled = 0 WHERE id != ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAiConfig(final AiConfigEntity aiConfig,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAiConfigEntity.insertAndReturnId(aiConfig);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAiConfig(final AiConfigEntity aiConfig,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAiConfigEntity.handle(aiConfig);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAiConfig(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAiConfig.acquire();
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
          __preparedStmtOfDeleteAiConfig.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object disableAllExcept(final long exceptId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDisableAllExcept.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, exceptId);
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
          __preparedStmtOfDisableAllExcept.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<AiConfigEntity> getActiveAiConfig() {
    final String _sql = "SELECT * FROM ai_configs WHERE isEnabled = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_configs"}, new Callable<AiConfigEntity>() {
      @Override
      @Nullable
      public AiConfigEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProvider = CursorUtil.getColumnIndexOrThrow(_cursor, "provider");
          final int _cursorIndexOfApiKey = CursorUtil.getColumnIndexOrThrow(_cursor, "apiKey");
          final int _cursorIndexOfModelName = CursorUtil.getColumnIndexOrThrow(_cursor, "modelName");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfBaseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "baseUrl");
          final int _cursorIndexOfTimeoutMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timeoutMs");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final AiConfigEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpProvider;
            if (_cursor.isNull(_cursorIndexOfProvider)) {
              _tmpProvider = null;
            } else {
              _tmpProvider = _cursor.getString(_cursorIndexOfProvider);
            }
            final String _tmpApiKey;
            if (_cursor.isNull(_cursorIndexOfApiKey)) {
              _tmpApiKey = null;
            } else {
              _tmpApiKey = _cursor.getString(_cursorIndexOfApiKey);
            }
            final String _tmpModelName;
            if (_cursor.isNull(_cursorIndexOfModelName)) {
              _tmpModelName = null;
            } else {
              _tmpModelName = _cursor.getString(_cursorIndexOfModelName);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpBaseUrl;
            if (_cursor.isNull(_cursorIndexOfBaseUrl)) {
              _tmpBaseUrl = null;
            } else {
              _tmpBaseUrl = _cursor.getString(_cursorIndexOfBaseUrl);
            }
            final int _tmpTimeoutMs;
            _tmpTimeoutMs = _cursor.getInt(_cursorIndexOfTimeoutMs);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _result = new AiConfigEntity(_tmpId,_tmpName,_tmpProvider,_tmpApiKey,_tmpModelName,_tmpIsEnabled,_tmpBaseUrl,_tmpTimeoutMs,_tmpMaxRetries);
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
  public Object getAiConfigById(final long id,
      final Continuation<? super AiConfigEntity> $completion) {
    final String _sql = "SELECT * FROM ai_configs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AiConfigEntity>() {
      @Override
      @Nullable
      public AiConfigEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProvider = CursorUtil.getColumnIndexOrThrow(_cursor, "provider");
          final int _cursorIndexOfApiKey = CursorUtil.getColumnIndexOrThrow(_cursor, "apiKey");
          final int _cursorIndexOfModelName = CursorUtil.getColumnIndexOrThrow(_cursor, "modelName");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfBaseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "baseUrl");
          final int _cursorIndexOfTimeoutMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timeoutMs");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final AiConfigEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpProvider;
            if (_cursor.isNull(_cursorIndexOfProvider)) {
              _tmpProvider = null;
            } else {
              _tmpProvider = _cursor.getString(_cursorIndexOfProvider);
            }
            final String _tmpApiKey;
            if (_cursor.isNull(_cursorIndexOfApiKey)) {
              _tmpApiKey = null;
            } else {
              _tmpApiKey = _cursor.getString(_cursorIndexOfApiKey);
            }
            final String _tmpModelName;
            if (_cursor.isNull(_cursorIndexOfModelName)) {
              _tmpModelName = null;
            } else {
              _tmpModelName = _cursor.getString(_cursorIndexOfModelName);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpBaseUrl;
            if (_cursor.isNull(_cursorIndexOfBaseUrl)) {
              _tmpBaseUrl = null;
            } else {
              _tmpBaseUrl = _cursor.getString(_cursorIndexOfBaseUrl);
            }
            final int _tmpTimeoutMs;
            _tmpTimeoutMs = _cursor.getInt(_cursorIndexOfTimeoutMs);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _result = new AiConfigEntity(_tmpId,_tmpName,_tmpProvider,_tmpApiKey,_tmpModelName,_tmpIsEnabled,_tmpBaseUrl,_tmpTimeoutMs,_tmpMaxRetries);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AiConfigEntity>> getAllAiConfigs() {
    final String _sql = "SELECT * FROM ai_configs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ai_configs"}, new Callable<List<AiConfigEntity>>() {
      @Override
      @NonNull
      public List<AiConfigEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProvider = CursorUtil.getColumnIndexOrThrow(_cursor, "provider");
          final int _cursorIndexOfApiKey = CursorUtil.getColumnIndexOrThrow(_cursor, "apiKey");
          final int _cursorIndexOfModelName = CursorUtil.getColumnIndexOrThrow(_cursor, "modelName");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfBaseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "baseUrl");
          final int _cursorIndexOfTimeoutMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timeoutMs");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final List<AiConfigEntity> _result = new ArrayList<AiConfigEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AiConfigEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpProvider;
            if (_cursor.isNull(_cursorIndexOfProvider)) {
              _tmpProvider = null;
            } else {
              _tmpProvider = _cursor.getString(_cursorIndexOfProvider);
            }
            final String _tmpApiKey;
            if (_cursor.isNull(_cursorIndexOfApiKey)) {
              _tmpApiKey = null;
            } else {
              _tmpApiKey = _cursor.getString(_cursorIndexOfApiKey);
            }
            final String _tmpModelName;
            if (_cursor.isNull(_cursorIndexOfModelName)) {
              _tmpModelName = null;
            } else {
              _tmpModelName = _cursor.getString(_cursorIndexOfModelName);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpBaseUrl;
            if (_cursor.isNull(_cursorIndexOfBaseUrl)) {
              _tmpBaseUrl = null;
            } else {
              _tmpBaseUrl = _cursor.getString(_cursorIndexOfBaseUrl);
            }
            final int _tmpTimeoutMs;
            _tmpTimeoutMs = _cursor.getInt(_cursorIndexOfTimeoutMs);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _item = new AiConfigEntity(_tmpId,_tmpName,_tmpProvider,_tmpApiKey,_tmpModelName,_tmpIsEnabled,_tmpBaseUrl,_tmpTimeoutMs,_tmpMaxRetries);
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
