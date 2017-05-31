package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class SchoolInfo extends SQLEntry implements Constants {

    public static final String PK_SCHOOL_ID                     = "pk_SchoolId";

    public static final String UK_SCHOOL_NAME                   = "uk_SchoolName";

    public SchoolInfo(String schoolName) {
        cv = new ContentValues();
        cv.put(UK_SCHOOL_NAME, schoolName);
    }

    public SchoolInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_SCHOOL_ID        , c.getInt(c.getColumnIndex(PK_SCHOOL_ID)));
        cv.put(UK_SCHOOL_NAME      , c.getString(c.getColumnIndex(UK_SCHOOL_NAME)));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_SCHOOL_INFO + "(" +
                PK_SCHOOL_ID         + " integer primary key autoincrement," +
                UK_SCHOOL_NAME       + " text)";
    }

}
