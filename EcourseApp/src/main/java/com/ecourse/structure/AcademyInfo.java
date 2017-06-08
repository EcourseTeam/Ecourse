package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class AcademyInfo extends SQLEntry implements Constants {

    public static final String PK_ACADEMY_ID = "pk_AcademyId";

    public static final String FK_SCHOOL_ID = "fk_SchoolId";
    public static final String IDX_ACADEMY_NAME = "idx_AcademyName";

    public AcademyInfo(int schoolId, String academyName) {
        cv = new ContentValues();
        cv.put(FK_SCHOOL_ID, schoolId);
        cv.put(IDX_ACADEMY_NAME, academyName);
    }

    public AcademyInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_ACADEMY_ID, c.getInt(c.getColumnIndex(PK_ACADEMY_ID)));
        cv.put(FK_SCHOOL_ID, c.getInt(c.getColumnIndex(FK_SCHOOL_ID)));
        cv.put(IDX_ACADEMY_NAME, c.getString(c.getColumnIndex(IDX_ACADEMY_NAME)));
    }

    public AcademyInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_ACADEMY_ID, json.getInt(PK_ACADEMY_ID));
        cv.put(FK_SCHOOL_ID, json.getInt(FK_SCHOOL_ID));
        cv.put(IDX_ACADEMY_NAME, json.getString(IDX_ACADEMY_NAME));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_ACADEMY_INFO + "(" +
                PK_ACADEMY_ID + " integer primary key autoincrement," +
                FK_SCHOOL_ID  + " integer," +
                IDX_ACADEMY_NAME + " text)";
    }
}
