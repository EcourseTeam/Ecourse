package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import java.util.Map.Entry;

public class MemoInfo extends SQLEntry implements Constants {

    public static final String PK_MEMO_ID                       = "pk_MemoId";

    public static final String FK_USER_ID                       = "fk_UserId";
    public static final String FK_COURSE_ID                     = "fk_CourseId";
    public static final String IDX_DEADLINE                     = "idx_Deadline";
    public static final String IDX_CONTENT                      = "idx_Content";

    public MemoInfo(int userId, int courseId,
                    int deadline, String content) {
        cv = new ContentValues();
        cv.put(FK_USER_ID  , userId);
        cv.put(FK_COURSE_ID, courseId);
        cv.put(IDX_DEADLINE, deadline);
        cv.put(IDX_CONTENT , content);
    }

    public MemoInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_MEMO_ID  , c.getInt(c.getColumnIndex(PK_MEMO_ID)));
        cv.put(FK_USER_ID  , c.getInt(c.getColumnIndex(FK_USER_ID)));
        cv.put(FK_COURSE_ID, c.getInt(c.getColumnIndex(FK_COURSE_ID)));
        cv.put(IDX_DEADLINE, c.getInt(c.getColumnIndex(IDX_DEADLINE)));
        cv.put(IDX_CONTENT , c.getString(c.getColumnIndex(IDX_CONTENT)));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_MEMO_INFO + "(" +
                PK_MEMO_ID   + " integer primary key autoincrement," +
                FK_USER_ID   + " integer," +
                FK_COURSE_ID + " integer," +
                IDX_DEADLINE + " integer," +
                IDX_CONTENT  + " text)";
    }
}
