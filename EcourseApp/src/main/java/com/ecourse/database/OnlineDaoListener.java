package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.http.HttpCallbackListener;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OnlineDaoListener implements Constants {

    HttpCallbackListener httpCallbackListener;

    public OnlineDaoListener(final String sqlRequest) {
        httpCallbackListener = new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                try {
                    switch (sqlRequest) {
                        case SQL_REQUEST_INSERT:
                            if (response == "true") {
                                finish(true);
                            } else if (response == "false") {
                                finish(false);
                            }
                            break;
                        case SQL_REQUEST_SELECT:
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("entries");
                            int length = jsonArray.length();
                            SQLEntry[] sqlEntries;
                            String table = jsonResponse.getString("tableName");
                            switch (table) {
                                case TABLE_ACADEMY_INFO:
                                    sqlEntries = new AcademyInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new AcademyInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_COURSE_INFO:
                                    sqlEntries = new CourseInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new CourseInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_COURSE_PERIOD_INFO:
                                    sqlEntries = new CoursePeriodInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new CoursePeriodInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_COURSE_TABLE_ENTRY_INFO:
                                    sqlEntries = new CourseTableEntryInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new CourseTableEntryInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_COURSE_TABLE_INFO:
                                    sqlEntries = new CourseTableInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new CourseTableInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_MAJOR_INFO:
                                    sqlEntries = new MajorInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new MajorInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_MEMO_INFO:
                                    sqlEntries = new MemoInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new MemoInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_SCHOOL_INFO:
                                    sqlEntries = new SchoolInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new SchoolInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_SCHOOL_ROLL_INFO:
                                    sqlEntries = new SchoolRollInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new SchoolRollInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_TEACHER_INFO:
                                    sqlEntries = new TeacherInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new TeacherInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                case TABLE_USER_INFO:
                                    sqlEntries = new UserInfo[length];
                                    for (int i = 0; i < length; ++i) {
                                        sqlEntries[i] = new UserInfo(jsonArray.getJSONObject(i));
                                    }
                                    break;
                                default:
                                    Exception e = new Exception();
                                    e.equals("No such table!");
                                    error(e);
                                    return;
                            }
                            finish(sqlEntries);
                            break;
                        default:
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Exception e) {
                error(e);
            }
        };
    }

    public void finish(boolean success) {

    }

    public void finish(SQLEntry[] sqlEntries) {

    }

    public void error(Exception e) {
        e.printStackTrace();
    }

    HttpCallbackListener getHttpCallbackListener() {
        return httpCallbackListener;
    }
}
