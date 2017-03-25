package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Map;

import com.ecourse.structure.Entry;
import com.ecourse.structure.UserInfo;
import com.ecourse.util.Constants;

public class OfflineDaoImpl implements OfflineDao {

    private static SQLiteDatabase db;

    public OfflineDaoImpl(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        if (db == null) {
            db = helper.getWritableDatabase();
            db.delete(TABLE_USER_INFO, null, null);
        }
    }

    public long addEntry(String table, Entry newEntry) {
        return addEntry(table, newEntry.getContentValues());
    }

    public long addEntry(String table, ContentValues newEntryCV) {
        Cursor c = db.rawQuery("select " + IDX_MAX_ID + " from " + TABLE_ID_POOL +
                " where " + PK_TABLE_NAME + " = ?", new String[]{table});
        c.moveToNext();
        //Log.i("OfflineDaoImpl", "table:" + table);
        int id = c.getInt(c.getColumnIndex(IDX_MAX_ID));
        ContentValues cv = new ContentValues();
        cv.put(PK_TABLE_NAME, table);
        cv.put(IDX_MAX_ID, ++id);
        //Log.i("OfflineDaoImpl", "id:" + id);
        ContentValues filter = new ContentValues();
        filter.put(PK_TABLE_NAME, table);
        db.update(TABLE_ID_POOL, cv, whereClause(filter), whereArgs(filter));
        newEntryCV.put(findPrimaryKeyName(newEntryCV), id);
        return db.insert(table, null, newEntryCV);
    }

    public int updateEntries(String table, ContentValues filter, Entry newEntry) {
        return db.update(table, newEntry.getContentValues(), whereClause(filter), whereArgs(filter));
    }

    public int updateEntries(String table, ContentValues filter, ContentValues newEntryCV) {
        return db.update(table, newEntryCV, whereClause(filter), whereArgs(filter));
    }

    public int deleteEntries(String table, ContentValues filter) {
        return db.delete(table, whereClause(filter), whereArgs(filter));
    }

    public Entry[] getEntries(String table) {
        return getEntries(table, null);
    }

    public Entry[] getEntries(String table, ContentValues filter) {
        String sql = "select * from " + table;
        if (filter == null) {
            return getEntriesFromCursor(table, db.rawQuery(sql, null));
        } else {
            return getEntriesFromCursor(table, db.rawQuery(sql + " where " + whereClause(filter), whereArgs(filter)));
        }
    }

    private String findPrimaryKeyName(ContentValues cv) {
        String str;
        for (Map.Entry<String, Object> item : cv.valueSet()) {
            str = item.getKey().toString();
            //Log.i("OfflineDaoImpl", "str:" + str);
            if (str.startsWith("pk_")) {
                return str;
            }
        }
        return null;
    }

    private String whereClause(ContentValues filter) {
        String str = "";
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            if (++counter < filter.size()) {
                str += item.getKey() + " = ? and ";
            } else {
                str += item.getKey() + " = ?";
            }
        }
        return str;
    }

    private String[] whereArgs(ContentValues filter) {
        String[] strs = new String[filter.size()];
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            strs[counter++] = item.getValue().toString();
        }
        return strs;
    }

    private Entry[] getEntriesFromCursor(String table, Cursor c) {
        Entry[] entries;
        int counter = 0;
        switch (table) {
            case TABLE_USER_INFO:
                entries = new UserInfo[c.getCount()];
                break;
            default:
                return null;
        }
        while (c.moveToNext()) {
            entries[counter++] = new UserInfo(c);
        }
        return entries;
    }

    public void closeDB() {
        db.close();
    }
}
