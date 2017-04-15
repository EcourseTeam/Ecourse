package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

public class CoursePeriodInfo extends SQLEntry implements Constants {

    public int pk_CoursePeriodId;
    public int fk_CourseId;
    public int idx_WeekFrom;
    public int idx_WeekTo;
    public int idx_Week;
    public int idx_TimeFrom;
    public int idx_TimeTo;
    public int idx_Alarm;
    public String idx_Place;

    public CoursePeriodInfo(int courseId, int weekFrom, int weekTo, int week,
                            int timeFrom, int timeTo, int alarm,
                            String place) {
        fk_CourseId = courseId;
        idx_WeekFrom = weekFrom;
        idx_WeekTo = weekTo;
        idx_Week = week;
        idx_TimeFrom = timeFrom;
        idx_TimeTo = timeTo;
        idx_Alarm = alarm;
        idx_Place = place;
    }

    public CoursePeriodInfo(Cursor c) {
        pk_CoursePeriodId = c.getInt(c.getColumnIndex("pk_CoursePeriodId"));
        fk_CourseId = c.getInt(c.getColumnIndex("fk_CourseId"));
        idx_WeekFrom = c.getInt(c.getColumnIndex("idx_WeekFrom"));
        idx_WeekTo = c.getInt(c.getColumnIndex("idx_WeekTo"));
        idx_Week = c.getInt(c.getColumnIndex("idx_Week"));
        idx_TimeFrom = c.getInt(c.getColumnIndex("idx_TimeFrom"));
        idx_TimeTo = c.getInt(c.getColumnIndex("idx_TimeTo"));
        idx_Alarm = c.getInt(c.getColumnIndex("idx_Alarm"));
        idx_Place = c.getString(c.getColumnIndex("idx_Place"));
    }
}
