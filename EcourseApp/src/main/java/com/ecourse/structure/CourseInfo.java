package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class CourseInfo extends SQLEntry implements Constants {

    public int pk_CourseId;
    public int fk_AcademyId;
    public String idx_CourseName;
    public String idx_CourseShortName;
    public String idx_CourseNumber;
    public int fk_TeacherId;
    public int idx_Semester;
    public int idx_CourseHours;
    public String idx_Materials;

    public CourseInfo(int academyId, String courseName, String courseShortName,
                      String courseNumber, int teacherId, int semester,
                      int courseHours, String materials) {
        fk_AcademyId = academyId;
        idx_CourseName = courseName;
        idx_CourseShortName = courseShortName;
        idx_CourseNumber = courseNumber;
        fk_TeacherId = teacherId;
        idx_Semester = semester;
        idx_CourseHours = courseHours;
        idx_Materials = materials;
    }

    public CourseInfo(Cursor c) {
        pk_CourseId = c.getInt(c.getColumnIndex("pk_CourseId"));
        fk_AcademyId = c.getInt(c.getColumnIndex("fk_AcademyId"));
        idx_CourseName = c.getString(c.getColumnIndex("idx_CourseName"));
        idx_CourseShortName = c.getString(c.getColumnIndex("idx_CourseShortName"));
        idx_CourseNumber = c.getString(c.getColumnIndex("idx_CourseNumber"));
        fk_TeacherId = c.getInt(c.getColumnIndex("fk_TeacherId"));
        idx_Semester = c.getInt(c.getColumnIndex("idx_Semester"));
        idx_CourseHours = c.getInt(c.getColumnIndex("idx_CourseHours"));
        idx_Materials = c.getString(c.getColumnIndex("idx_Materials"));
    }
}
