package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class CourseTableEntryInfo extends SQLEntry implements Constants {

    public int pk_CourseTableEntryId;
    public int fk_CourseTableId;
    public int fk_CoursePeriodId;

    public CourseTableEntryInfo(int courseTableId, int coursePeriodId) {
        fk_CourseTableId = courseTableId;
        fk_CoursePeriodId = coursePeriodId;
    }

    public CourseTableEntryInfo(Cursor c) {
        pk_CourseTableEntryId = c.getInt(c.getColumnIndex("pk_CourseTableEntryId"));
        fk_CourseTableId = c.getInt(c.getColumnIndex("fk_CourseTableId"));
        fk_CoursePeriodId = c.getInt(c.getColumnIndex("fk_CoursePeriodId"));
    }
}
