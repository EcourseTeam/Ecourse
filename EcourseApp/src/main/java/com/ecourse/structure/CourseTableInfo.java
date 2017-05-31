package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class CourseTableInfo extends SQLEntry implements Constants {

    /*public int pk_CourseTableId;
    public int fk_UserId;
    public String idx_CourseTableName;
    public int idx_OneClassTime;
    public int idx_CourseClassCount;
    public int idx_Class1Time;
    public int idx_Class2Time;
    public int idx_Class3Time;
    public int idx_Class4Time;
    public int idx_Class5Time;
    public int idx_Class6Time;
    public int idx_Class7Time;
    public int idx_Class8Time;
    public int idx_Class9Time;
    public int idx_Class10Time;
    public int idx_Class11Time;
    public int idx_Class12Time;
    public int idx_Class13Time;
    public int idx_Class14Time;

    public CourseTableInfo(int userId, String courseTableName) {
        fk_UserId = userId;
        idx_CourseTableName = courseTableName;
    }

    public CourseTableInfo(Cursor c) {
        pk_CourseTableId = c.getInt(c.getColumnIndex("pk_CourseTableId"));
        fk_UserId = c.getInt(c.getColumnIndex("fk_UserId"));
        idx_CourseTableName = c.getString(c.getColumnIndex("idx_CourseTableName"));
    }*/

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

    public static String createTableSQL() {
        return "create table " + TABLE_COURSE_TABLE_INFO + "(" +
                PK_COURSE_TABLE_ID    + " integer primary key autoincrement," +
                FK_USER_ID            + " integer," +
                IDX_COURSE_TABLE_NAME + " text)";
    }
}
