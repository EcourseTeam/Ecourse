package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class CourseInfo extends SQLEntry implements Constants {

    public static final String PK_COURSE_ID                     = "pk_CourseId";

    public static final String FK_ACADEMY_ID                    = "fk_AcademyId";
    public static final String IDX_COURSE_NAME                  = "idx_CourseName";
    public static final String IDX_COURSE_SHORT_NAME            = "idx_CourseShortName";
    public static final String IDX_COURSE_NUMBER                = "idx_CourseNumber";
    public static final String FK_TEACHER_ID                    = "fk_TeacherId";
    public static final String IDX_SEMESTER                     = "idx_Semester";
    public static final String IDX_COURSE_HOURS                 = "idx_CourseHours";
    public static final String IDX_MATERIALS                    = "idx_Materials";

    public CourseInfo(int academyId, String courseName, String courseShortName,
                      String courseNumber, int teacherId, int semester,
                      int courseHours, String materials) {
        cv = new ContentValues();
        cv.put(FK_ACADEMY_ID        , academyId);
        cv.put(IDX_COURSE_NAME      , courseName);
        cv.put(IDX_COURSE_SHORT_NAME, courseShortName);
        cv.put(IDX_COURSE_NUMBER    , courseNumber);
        cv.put(FK_TEACHER_ID        , teacherId);
        cv.put(IDX_SEMESTER         , semester);
        cv.put(IDX_COURSE_HOURS     , courseHours);
        cv.put(IDX_MATERIALS        , materials);
    }

    public CourseInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_COURSE_ID         , c.getInt(c.getColumnIndex(PK_COURSE_ID)));
        cv.put(FK_ACADEMY_ID        , c.getInt(c.getColumnIndex(FK_ACADEMY_ID)));
        cv.put(IDX_COURSE_NAME      , c.getString(c.getColumnIndex(IDX_COURSE_NAME)));
        cv.put(IDX_COURSE_SHORT_NAME, c.getString(c.getColumnIndex(IDX_COURSE_SHORT_NAME)));
        cv.put(IDX_COURSE_NUMBER    , c.getString(c.getColumnIndex(IDX_COURSE_NUMBER)));
        cv.put(FK_TEACHER_ID        , c.getInt(c.getColumnIndex(FK_TEACHER_ID)));
        cv.put(IDX_SEMESTER         , c.getInt(c.getColumnIndex(IDX_SEMESTER)));
        cv.put(IDX_COURSE_HOURS     , c.getInt(c.getColumnIndex(IDX_COURSE_HOURS)));
        cv.put(IDX_MATERIALS        , c.getString(c.getColumnIndex(IDX_MATERIALS)));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_COURSE_INFO + "(" +
                PK_COURSE_ID          + " integer primary key autoincrement," +
                FK_ACADEMY_ID         + " integer," +
                IDX_COURSE_NAME       + " text," +
                IDX_COURSE_SHORT_NAME + " text," +
                IDX_COURSE_NUMBER     + " varchar(32)," +
                FK_TEACHER_ID         + " integer," +
                IDX_SEMESTER          + " int," +
                IDX_COURSE_HOURS      + " int," +
                IDX_MATERIALS         + " text)";
    }
}
