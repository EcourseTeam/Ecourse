package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class SchoolRollInfo extends SQLEntry implements Constants {

    public static final String PK_SCHOOL_ROLL_ID = "pk_SchoolRollId";
    public static final String FK_SCHOOL_ID = "fk_SchoolId";
    public static final String FK_MAJOR_ID = "fk_MajorId";

    public SchoolRollInfo(int schoolId, int majorId) {
        cv = new ContentValues();
        cv.put(FK_SCHOOL_ID        , schoolId);
        cv.put(FK_MAJOR_ID       , majorId);
    }

    public SchoolRollInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_SCHOOL_ROLL_ID        , c.getInt(c.getColumnIndex(PK_SCHOOL_ROLL_ID)));
        cv.put(FK_SCHOOL_ID        , c.getInt(c.getColumnIndex(FK_SCHOOL_ID)));
        cv.put(FK_MAJOR_ID        , c.getInt(c.getColumnIndex(FK_MAJOR_ID)));
    }

    public SchoolRollInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_SCHOOL_ROLL_ID        , json.getInt(PK_SCHOOL_ROLL_ID));
        cv.put(FK_SCHOOL_ID        , json.getInt(FK_SCHOOL_ID));
        cv.put(FK_MAJOR_ID        , json.getInt(FK_MAJOR_ID));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_SCHOOL_ROLL_INFO + "(" +
                PK_SCHOOL_ROLL_ID + " integer primary key autoincrement," +
                FK_SCHOOL_ID  + " integer," +
                FK_MAJOR_ID + " integer)";
    }

}
