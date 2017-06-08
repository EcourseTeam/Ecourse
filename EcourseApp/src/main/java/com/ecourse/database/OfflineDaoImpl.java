package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecourse.structure.AcademyInfo;
import com.ecourse.structure.CourseInfo;
import com.ecourse.structure.CoursePeriodInfo;
import com.ecourse.structure.CourseTableEntryInfo;
import com.ecourse.structure.CourseTableInfo;
import com.ecourse.structure.MajorInfo;
import com.ecourse.structure.MemoInfo;
import com.ecourse.structure.SQLEntry;
import com.ecourse.structure.SchoolInfo;
import com.ecourse.structure.SchoolRollInfo;
import com.ecourse.structure.TeacherInfo;
import com.ecourse.structure.UserInfo;
import com.ecourse.util.Constants;

import java.util.Map;

public class OfflineDaoImpl implements OfflineDao, Constants {

    private static SQLiteDatabase db;

    OfflineDaoImpl(Context ctx) {
        DBHelper helper = new DBHelper(ctx);
        if (db == null) {
            db = helper.getWritableDatabase();
        }
    }

    public long addEntry(String table, SQLEntry newEntry) {
        return addEntry(table, newEntry.getContentValues());
    }

    public long addEntry(String table, ContentValues newEntryCV) {
        return db.insert(table, null, newEntryCV);
    }

    public int updateEntries(String table, ContentValues filter, SQLEntry newEntry) {
        return db.update(table, newEntry.getContentValues(), whereClause(filter), whereArgs(filter));
    }

    public int updateEntries(String table, ContentValues filter, ContentValues newEntryCV) {
        return db.update(table, newEntryCV, whereClause(filter), whereArgs(filter));
    }

    public int deleteEntries(String table, ContentValues filter) {
        return db.delete(table, whereClause(filter), whereArgs(filter));
    }

    public SQLEntry[] getEntries(String table) {
        return getEntries(table, null);
    }

    public SQLEntry[] getEntries(String table, ContentValues filter) {
        String sql = "select * from " + table;
        if (filter == null) {
            return getEntriesFromCursor(table, db.rawQuery(sql, null));
        } else {
            return getEntriesFromCursor(table, db.rawQuery(sql + " where " + whereClause(filter), whereArgs(filter)));
        }
    }

    private SQLEntry[] getEntriesFromCursor(String table, Cursor c) {
        SQLEntry[] entries;
        int counter = 0;
        switch (table) {
            case TABLE_USER_INFO:
                entries = new UserInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new UserInfo(c);
                }
                break;
            case TABLE_ACADEMY_INFO:
                entries = new AcademyInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new AcademyInfo(c);
                }
                break;
            case TABLE_COURSE_INFO:
                entries = new CourseInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new CourseInfo(c);
                }
                break;
            case TABLE_COURSE_PERIOD_INFO:
                entries = new CoursePeriodInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new CoursePeriodInfo(c);
                }
                break;
            case TABLE_COURSE_TABLE_INFO:
                entries = new CourseTableInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new CourseTableInfo(c);
                }
                break;
            case TABLE_COURSE_TABLE_ENTRY_INFO:
                entries = new CourseTableEntryInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new CourseTableEntryInfo(c);
                }
                break;
            case TABLE_MAJOR_INFO:
                entries = new MajorInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new MajorInfo(c);
                }
                break;
            case TABLE_MEMO_INFO:
                entries = new MemoInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new MemoInfo(c);
                }
                break;
            case TABLE_SCHOOL_INFO:
                entries = new SchoolInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new SchoolInfo(c);
                }
                break;
            case TABLE_SCHOOL_ROLL_INFO:
                entries = new SchoolRollInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new SchoolRollInfo(c);
                }
                break;
            case TABLE_TEACHER_INFO:
                entries = new TeacherInfo[c.getCount()];
                while (c.moveToNext()) {
                    entries[counter++] = new TeacherInfo(c);
                }
                break;
            default:
                return null;
        }
        return entries;
    }

    public void closeDB() {
        db.close();
    }

    static String whereClause(ContentValues filter) {
        String str = "";
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            if (++counter < filter.size()) {
                str += item.getKey() + " = ? and ";
            } else {
                str += item.getKey() + " = ?";
            }
        }
        return str;
    }

    static String[] whereArgs(ContentValues filter) {
        String[] strs = new String[filter.size()];
        int counter = 0;
        for (Map.Entry<String, Object> item : filter.valueSet()) {
            strs[counter++] = item.getValue().toString();
        }
        return strs;
    }
}
