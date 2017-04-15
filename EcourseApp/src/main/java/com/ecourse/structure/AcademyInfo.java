package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class AcademyInfo extends SQLEntry implements Constants {

    public int pk_AcademyId;
    public int fk_SchoolId;
    public String idx_AcademyName;

    public AcademyInfo(int schoolId, String academyName) {
        fk_SchoolId = schoolId;
        idx_AcademyName = academyName;
    }

    public AcademyInfo(Cursor c) {
        pk_AcademyId = c.getInt(c.getColumnIndex("pk_AcademyId"));
        fk_SchoolId = c.getInt(c.getColumnIndex("fk_SchoolId"));
        idx_AcademyName = c.getString(c.getColumnIndex("idx_AcademyName"));
    }
}
