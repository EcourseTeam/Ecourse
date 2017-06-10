package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo extends SQLEntry implements Constants {

    public static final String PK_USER_ID                       = "pk_UserId";

    public static final String UK_USERNAME                      = "uk_Username";
    public static final String IDX_NICKNAME                     = "idx_Nickname";
    public static final String IDX_PASSWORD                     = "idx_Password";
    public static final String IDX_EMAIL                        = "idx_Email";
    public static final String FK_SCHOOL_ROLL_ID                = "fk_SchoolRollId";
    public static final String IDX_STUDENT_NUMBER               = "idx_StudentNumber";
    public static final String IDX_SHARE_COURSE                 = "idx_ShareCourse";
    public static final String IDX_PERMISSION                   = "idx_Permission";

    public UserInfo(String username, String nickname,
                    String password, String email, int schoolRollId,
                    String studentNumber, int shareCourse, int permission) {
        cv = new ContentValues();
        cv.put(UK_USERNAME       , username);
        cv.put(IDX_NICKNAME      , nickname);
        cv.put(IDX_PASSWORD      , password);
        cv.put(IDX_EMAIL         , email);
        cv.put(FK_SCHOOL_ROLL_ID , schoolRollId);
        cv.put(IDX_STUDENT_NUMBER, studentNumber);
        cv.put(IDX_SHARE_COURSE  , shareCourse);
        cv.put(IDX_PERMISSION    , permission);
    }

    public UserInfo(Cursor c) {
        cv = new ContentValues();
        cv.put(PK_USER_ID        , c.getInt(c.getColumnIndex(PK_USER_ID)));
        cv.put(UK_USERNAME       , c.getString(c.getColumnIndex(UK_USERNAME)));
        cv.put(IDX_NICKNAME      , c.getString(c.getColumnIndex(IDX_NICKNAME)));
        cv.put(IDX_PASSWORD      , c.getString(c.getColumnIndex(IDX_PASSWORD)));
        cv.put(IDX_EMAIL         , c.getString(c.getColumnIndex(IDX_EMAIL)));
        cv.put(FK_SCHOOL_ROLL_ID , c.getInt(c.getColumnIndex(FK_SCHOOL_ROLL_ID)));
        cv.put(IDX_STUDENT_NUMBER, c.getString(c.getColumnIndex(IDX_STUDENT_NUMBER)));
        cv.put(IDX_SHARE_COURSE  , c.getInt(c.getColumnIndex(IDX_SHARE_COURSE)));
        cv.put(IDX_PERMISSION    , c.getInt(c.getColumnIndex(IDX_PERMISSION)));
    }

    public UserInfo(JSONObject json) throws JSONException {
        cv = new ContentValues();
        cv.put(PK_USER_ID        , json.getInt(PK_USER_ID));
        cv.put(UK_USERNAME       , json.getString(UK_USERNAME));
        cv.put(IDX_NICKNAME      , json.getString(IDX_NICKNAME));
        cv.put(IDX_PASSWORD      , json.getString(IDX_PASSWORD));
        cv.put(IDX_EMAIL         , json.getString(IDX_EMAIL));
        cv.put(FK_SCHOOL_ROLL_ID , json.getInt(FK_SCHOOL_ROLL_ID));
        cv.put(IDX_STUDENT_NUMBER, json.getString(IDX_STUDENT_NUMBER));
        cv.put(IDX_SHARE_COURSE  , json.getInt(IDX_SHARE_COURSE));
        cv.put(IDX_PERMISSION    , json.getInt(IDX_PERMISSION));
    }

    public static String createTableSQL() {
        return "create table " + TABLE_USER_INFO + "(" +
                PK_USER_ID         + " integer primary key autoincrement," +
                UK_USERNAME        + " varchar(32)," +
                IDX_NICKNAME       + " text," +
                IDX_PASSWORD       + " varchar(32)," +
                IDX_EMAIL          + " varchar(64)," +
                FK_SCHOOL_ROLL_ID  + " integer," +
                IDX_STUDENT_NUMBER + " varchar(32)," +
                IDX_SHARE_COURSE   + " int," +
                IDX_PERMISSION     + " int)";
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        return (username.equals(cv.get(UK_USERNAME)) && password.equals(cv.get(IDX_PASSWORD)));
    }

    public UserBean getUserBean() {
        UserBean userBean = new UserBean();
        userBean.setEmail((String) cv.get(IDX_EMAIL));
        userBean.setNickname((String) cv.get(IDX_NICKNAME));
        userBean.setUserName((String) cv.get(UK_USERNAME));
        userBean.setSchool((int)cv.get(FK_SCHOOL_ROLL_ID) + "");
        userBean.setStudentNo((String) cv.get(IDX_STUDENT_NUMBER));
        userBean.setPassword((String) cv.get(IDX_PASSWORD));
        return userBean;
    }
}
