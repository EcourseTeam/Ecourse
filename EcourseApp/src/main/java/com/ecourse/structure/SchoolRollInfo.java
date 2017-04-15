package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class SchoolRollInfo extends SQLEntry implements Constants {

    public int pk_SchoolRollId;
    public int fk_SchoolId;
    public int fk_MajorId;

    public SchoolRollInfo(int schoolId, int majorId) {
        fk_SchoolId = schoolId;
        fk_MajorId = majorId;
    }

    public SchoolRollInfo(Cursor c) {
        pk_SchoolRollId = c.getInt(c.getColumnIndex("pk_SchoolRollId"));
        fk_SchoolId = c.getInt(c.getColumnIndex("fk_SchoolId"));
        fk_MajorId = c.getInt(c.getColumnIndex("fk_MajorId"));
    }

}
