package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class CoursePeriodInfo extends SQLEntry implements Constants {

    public static final String PK_COURSE_PERIOD_ID              = "pk_CoursePeriodId";

    public static final String FK_COURSE_ID                     = "fk_CourseId";
    public static final String IDX_WEEK_FROM                    = "idx_WeekFrom";
    public static final String IDX_WEEK_TO                      = "idx_WeekTo";
    public static final String IDX_WEEK                         = "idx_Week";
    public static final String IDX_TIME_FROM                    = "idx_TimeFrom";
    public static final String IDX_TIME_TO                      = "idx_TimeTo";
    public static final String IDX_ALARM                        = "idx_Alarm";
    public static final String IDX_PLACE                        = "idx_Place";

    public CoursePeriodInfo(int courseId, int weekFrom, int weekTo, int week,
                            int timeFrom, int timeTo, int alarm,
                            String place) {
        cv = new ContentValues();
        cv.put(FK_COURSE_ID , courseId);
        cv.put(IDX_WEEK_FROM, weekFrom);
        cv.put(IDX_WEEK_TO  , weekTo);
        cv.put(IDX_WEEK     , week);
        cv.put(IDX_TIME_FROM, timeFrom);
        cv.put(IDX_TIME_TO  , timeTo);
        cv.put(IDX_ALARM    , alarm);
        cv.put(IDX_PLACE    , place);
    }

    public CoursePeriodInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_COURSE_PERIOD_ID, c.getInt(c.getColumnIndex(PK_COURSE_PERIOD_ID)));
        cv.put(FK_COURSE_ID , c.getInt(c.getColumnIndex(FK_COURSE_ID)));
        cv.put(IDX_WEEK_FROM, c.getInt(c.getColumnIndex(IDX_WEEK_FROM)));
        cv.put(IDX_WEEK_TO  , c.getInt(c.getColumnIndex(IDX_WEEK_TO)));
        cv.put(IDX_WEEK     , c.getInt(c.getColumnIndex(IDX_WEEK)));
        cv.put(IDX_TIME_FROM, c.getInt(c.getColumnIndex(IDX_TIME_FROM)));
        cv.put(IDX_TIME_TO  , c.getInt(c.getColumnIndex(IDX_TIME_TO)));
        cv.put(IDX_ALARM    , c.getInt(c.getColumnIndex(IDX_ALARM)));
        cv.put(IDX_PLACE    , c.getString(c.getColumnIndex(IDX_PLACE)));
    }

    public CoursePeriodInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_COURSE_PERIOD_ID, json.getInt(PK_COURSE_PERIOD_ID));
        cv.put(FK_COURSE_ID , json.getInt(FK_COURSE_ID));
        cv.put(IDX_WEEK_FROM, json.getInt(IDX_WEEK_FROM));
        cv.put(IDX_WEEK_TO  , json.getInt(IDX_WEEK_TO));
        cv.put(IDX_WEEK     , json.getInt(IDX_WEEK));
        cv.put(IDX_TIME_FROM, json.getInt(IDX_TIME_FROM));
        cv.put(IDX_TIME_TO  , json.getInt(IDX_TIME_TO));
        cv.put(IDX_ALARM    , json.getInt(IDX_ALARM));
        cv.put(IDX_PLACE    , json.getString(IDX_PLACE));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_COURSE_PERIOD_INFO + "(" +
                PK_COURSE_PERIOD_ID + " integer primary key autoincrement," +
                FK_COURSE_ID  + " integer," +
                IDX_WEEK_FROM + " int," +
                IDX_WEEK_TO   + " int," +
                IDX_WEEK      + " int," +
                IDX_TIME_FROM + " int," +
                IDX_TIME_TO   + " int," +
                IDX_ALARM     + " int," +
                IDX_PLACE     + " text)";
    }
}
