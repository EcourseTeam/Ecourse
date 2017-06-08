package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ecourse.http.HttpCallbackListener;
import com.ecourse.http.HttpUtil;
import com.ecourse.http.ServerConfig;
import com.ecourse.structure.SQLEntry;
import com.ecourse.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class OnlineDaoImpl implements OnlineDao, Constants, ServerConfig {

    private static SQLiteDatabase db;

    OnlineDaoImpl(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        if (db == null) {
            db = helper.getWritableDatabase();
        }
    }

    private JSONObject cvToJson(ContentValues cv) throws JSONException {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> item: cv.valueSet()) {
            json.put(item.getKey(), item.getValue());
        }
        return json;
    }

    private String createRequest(String sqlRequest, String table, ContentValues[] entriesCV, ContentValues filterCV) {
        try {
            JSONObject requestJson = new JSONObject();
            requestJson.put("sqlRequest", sqlRequest);
            requestJson.put("tableName", table);
            if (entriesCV != null) {
                JSONArray entriesJson = new JSONArray();
                for (int i = 0; i < entriesCV.length; ++i) {
                    entriesJson.put(cvToJson(entriesCV[i]));
                }
                requestJson.put("entries", entriesJson);
            }
            if (filterCV != null) {
                requestJson.put("filter", cvToJson(filterCV));
            }
            return requestJson.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private long sendRequest(String request, OnlineDaoListener listener) {
        if (request != null) {
            HttpUtil.sendPostHttpRequest(SERVER_URL, request, listener.getHttpCallbackListener());
            return 0;
        }
        return -1;
    }

    public long addEntry(String table, SQLEntry newEntry, OnlineDaoListener listener) {
        return addEntry(table, newEntry.getContentValues(), listener);
    }

    public long addEntry(String table, ContentValues newEntryCV, OnlineDaoListener listener) {
        ContentValues[] newEntriesCV = {newEntryCV};
        return addEntries(table, newEntriesCV, listener);
    }

    public long addEntries(String table, SQLEntry[] newEntries, OnlineDaoListener listener) {
        ContentValues[] newEntriesCV = new ContentValues[newEntries.length];
        for (int i = 0; i < newEntries.length; ++i) {
            newEntriesCV[i] = newEntries[i].getContentValues();
        }
        return addEntries(table, newEntriesCV, listener);
    }

    public long addEntries(String table, ContentValues[] newEntriesCV, OnlineDaoListener listener) {
        String request = createRequest(SQL_REQUEST_INSERT, table, newEntriesCV, null);
        return sendRequest(request, listener);
    }

    /*public int updateEntries(String table, ContentValues filter, SQLEntry newEntry) {
        return db.update(table, newEntry.getContentValues(), whereClause(filter), whereArgs(filter));
    }

    public int updateEntries(String table, ContentValues filter, ContentValues newEntryCV) {
        return db.update(table, newEntryCV, whereClause(filter), whereArgs(filter));
    }

    public int deleteEntries(String table, ContentValues filter) {
        return db.delete(table, whereClause(filter), whereArgs(filter));
    }*/

    public long getEntries(String table, OnlineDaoListener listener) {
        return getEntries(table, null, listener);
    }

    public long getEntries(String table, ContentValues filter, OnlineDaoListener listener) {
        String request = createRequest(SQL_REQUEST_SELECT, table, null, filter);
        return sendRequest(request, listener);
    }

    /*private SQLEntry[] getEntriesFromCursor(String table, Cursor c) {
        SQLEntry[] entries;
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
    }*/

    /*public void closeDB() {
        db.close();
    }*/

    static String whereClause(ContentValues filter) {
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

    static String[] whereArgs(ContentValues filter) {
        String[] strs = new String[filter.size()];
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            strs[counter++] = item.getValue().toString();
        }
        return strs;
    }
}
