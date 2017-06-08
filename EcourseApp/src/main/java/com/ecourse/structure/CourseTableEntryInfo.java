package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseTableEntryInfo extends SQLEntry implements Constants {

    public static final String PK_COURSE_TABLE_ENTRY_ID         = "pk_CourseTableEntryId";

    public static final String FK_COURSE_TABLE_ID               = "fk_CourseTableId";
    public static final String FK_COURSE_PERIOD_ID              = "fk_CoursePeriodId";

    public CourseTableEntryInfo(int courseTableId, int coursePeriodId) {
        cv = new ContentValues();
        cv.put(FK_COURSE_TABLE_ID , courseTableId);
        cv.put(FK_COURSE_PERIOD_ID, coursePeriodId);
    }

    public CourseTableEntryInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_COURSE_TABLE_ENTRY_ID, c.getInt(c.getColumnIndex(PK_COURSE_TABLE_ENTRY_ID)));
        cv.put(FK_COURSE_TABLE_ID      , c.getInt(c.getColumnIndex(FK_COURSE_TABLE_ID)));
        cv.put(FK_COURSE_PERIOD_ID     , c.getInt(c.getColumnIndex(FK_COURSE_PERIOD_ID)));
    }

    public CourseTableEntryInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_COURSE_TABLE_ENTRY_ID, json.getInt(PK_COURSE_TABLE_ENTRY_ID));
        cv.put(FK_COURSE_TABLE_ID      , json.getInt(FK_COURSE_TABLE_ID));
        cv.put(FK_COURSE_PERIOD_ID     , json.getInt(FK_COURSE_PERIOD_ID));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_COURSE_TABLE_ENTRY_INFO + "(" +
                PK_COURSE_TABLE_ENTRY_ID + " integer primary key autoincrement," +
                FK_COURSE_TABLE_ID       + " integer," +
                FK_COURSE_PERIOD_ID      + " integer)";
    }
}
