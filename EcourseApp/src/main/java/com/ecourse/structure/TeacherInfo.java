package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class TeacherInfo extends SQLEntry implements Constants {

    public static final String PK_TEACHER_ID                    = "pk_TeacherId";

    public static final String FK_ACADEMY_ID                    = "fk_AcademyId";
    public static final String IDX_TEACHER_NAME                 = "idx_TeacherName";
    public static final String IDX_OFFICE_PHONE                 = "idx_OfficePhone";
    public static final String IDX_CELL_PHONE                   = "idx_CellPhone";
    public static final String IDX_EMAIL                        = "idx_Email";

    public TeacherInfo(int academyId, String teacherName, String officePhone,
                       String cellPhone, String email) {
        cv = new ContentValues();
        cv.put(FK_ACADEMY_ID   , academyId);
        cv.put(IDX_TEACHER_NAME, teacherName);
        cv.put(IDX_OFFICE_PHONE, officePhone);
        cv.put(IDX_CELL_PHONE  , cellPhone);
        cv.put(IDX_EMAIL       , email);
    }

    public TeacherInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_TEACHER_ID   , c.getInt(c.getColumnIndex(PK_TEACHER_ID)));
        cv.put(FK_ACADEMY_ID   , c.getInt(c.getColumnIndex(FK_ACADEMY_ID)));
        cv.put(IDX_TEACHER_NAME, c.getString(c.getColumnIndex(IDX_TEACHER_NAME)));
        cv.put(IDX_OFFICE_PHONE, c.getString(c.getColumnIndex(IDX_OFFICE_PHONE)));
        cv.put(IDX_CELL_PHONE  , c.getString(c.getColumnIndex(IDX_CELL_PHONE)));
        cv.put(IDX_EMAIL       , c.getString(c.getColumnIndex(IDX_EMAIL)));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_TEACHER_INFO + "(" +
                PK_TEACHER_ID    + " integer primary key autoincrement," +
                FK_ACADEMY_ID    + " integer," +
                IDX_TEACHER_NAME + " text," +
                IDX_OFFICE_PHONE + " varchar(16)," +
                IDX_CELL_PHONE   + " varchar(16)," +
                IDX_EMAIL        + " varchar(64))";
    }
}
