package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class MajorInfo extends SQLEntry implements Constants {

    public int pk_MajorId;
    public int fk_AcademyId;
    public String idx_MajorName;
    public String idx_Education;
    public int idx_Semester = 1;
    public int idx_Week = 1;

    public static final String PK_MAJOR_ID                      = "pk_MajorId";

    public static final String FK_ACADEMY_ID                    = "fk_AcademyId";
    public static final String IDX_MAJOR_NAME                   = "idx_MajorName";
    public static final String IDX_EDUCATION                    = "idx_Education";
    public static final String IDX_SEMESTER                     = "idx_Semester";
    public static final String IDX_WEEK                         = "idx_Week";

    public MajorInfo(int academyId, String majorName, String education, int semester, int week) {
        cv = new ContentValues();
        cv.put(FK_ACADEMY_ID , academyId);
        cv.put(IDX_MAJOR_NAME, majorName);
        cv.put(IDX_EDUCATION , education);
        cv.put(IDX_SEMESTER  , semester);
        cv.put(IDX_WEEK      , week);
    }

    public MajorInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_MAJOR_ID   , c.getInt(c.getColumnIndex(PK_MAJOR_ID)));
        cv.put(FK_ACADEMY_ID , c.getInt(c.getColumnIndex(FK_ACADEMY_ID)));
        cv.put(IDX_MAJOR_NAME, c.getString(c.getColumnIndex(IDX_MAJOR_NAME)));
        cv.put(IDX_EDUCATION , c.getString(c.getColumnIndex(IDX_EDUCATION)));
        cv.put(IDX_SEMESTER  , c.getInt(c.getColumnIndex(IDX_SEMESTER)));
        cv.put(IDX_WEEK      , c.getInt(c.getColumnIndex(IDX_WEEK)));
    }

    public MajorInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_MAJOR_ID   , json.getInt(PK_MAJOR_ID));
        cv.put(FK_ACADEMY_ID , json.getInt(FK_ACADEMY_ID));
        cv.put(IDX_MAJOR_NAME, json.getString(IDX_MAJOR_NAME));
        cv.put(IDX_EDUCATION , json.getString(IDX_EDUCATION));
        cv.put(IDX_SEMESTER  , json.getInt(IDX_SEMESTER));
        cv.put(IDX_WEEK      , json.getInt(IDX_WEEK));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_MAJOR_INFO + "(" +
                PK_MAJOR_ID   + " integer primary key autoincrement," +
                FK_ACADEMY_ID + " integer," +
                IDX_MAJOR_NAME+ " text," +
                IDX_EDUCATION + " text," +
                IDX_SEMESTER  + " int," +
                IDX_WEEK      + " int)";
    }
}
