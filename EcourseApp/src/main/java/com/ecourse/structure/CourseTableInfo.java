package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseTableInfo extends SQLEntry implements Constants {

    public static final String PK_COURSE_TABLE_ID               = "pk_CourseTableId";

    public static final String FK_USER_ID                       = "fk_UserId";
    public static final String IDX_COURSE_TABLE_NAME            = "idx_CourseTableName";

    public CourseTableInfo(int userId, String courseTableName) {
        cv = new ContentValues();
        cv.put(FK_USER_ID           , userId);
        cv.put(IDX_COURSE_TABLE_NAME, courseTableName);
    }

    public CourseTableInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_COURSE_TABLE_ID   , c.getInt(c.getColumnIndex(PK_COURSE_TABLE_ID)));
        cv.put(FK_USER_ID           , c.getInt(c.getColumnIndex(FK_USER_ID)));
        cv.put(IDX_COURSE_TABLE_NAME, c.getString(c.getColumnIndex(IDX_COURSE_TABLE_NAME)));
    }

    public CourseTableInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_COURSE_TABLE_ID   , json.getInt(PK_COURSE_TABLE_ID));
        cv.put(FK_USER_ID           , json.getInt(FK_USER_ID));
        cv.put(IDX_COURSE_TABLE_NAME, json.getString(IDX_COURSE_TABLE_NAME));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_COURSE_TABLE_INFO + "(" +
                PK_COURSE_TABLE_ID    + " integer primary key autoincrement," +
                FK_USER_ID            + " integer," +
                IDX_COURSE_TABLE_NAME + " text)";
    }
}
