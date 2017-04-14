package com.ecourse.structure;

import android.content.ContentValues;
import android.database.Cursor;

import com.ecourse.util.Constants;

import java.util.Map.Entry;

public class UserInfo implements SQLEntry, Constants {

    public static final String PK_USER_ID                       = "pk_UserId";

    public static final String UK_USERNAME                      = "uk_Username";
    public static final String IDX_NICKNAME                     = "idx_Nickname";
    public static final String IDX_PASSWORD                     = "idx_Password";
    public static final String IDX_EMAIL                        = "idx_Email";
    public static final String FK_SCHOOL_ROLL_ID                = "fk_SchoolRollId";
    public static final String IDX_STUDENT_NUMBER               = "idx_StudentNumber";
    public static final String IDX_SHARE_COURSE                 = "idx_ShareCourse";
    public static final String IDX_PERMISSION                   = "idx_Permission";

    private ContentValues cv;
//    private int pk_UserId;
//    private String uk_Username;
//    private String idx_Nickname;
//    private String idx_Password;
//    private String idx_Email;
//    private int fk_SchoolRollId;
//    private String idx_StudentNumber;
//    private int idx_ShareCourse;
//    private int idx_Permission;

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
//        uk_Username = username;
//        idx_Nickname = nickname;
//        idx_Password = password;
//        idx_Email = email;
//        fk_SchoolRollId = schoolRollId;
//        idx_StudentNumber = studentNumber;
//        idx_ShareCourse = shareCourse;
//        idx_Permission = permission;
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
//        pk_UserId = c.getInt(c.getColumnIndex(PK_USER_ID));
//        uk_Username = c.getString(c.getColumnIndex(UK_USERNAME));
//        idx_Nickname = c.getString(c.getColumnIndex(IDX_NICKNAME));
//        idx_Password = c.getString(c.getColumnIndex(IDX_PASSWORD));
//        idx_Email = c.getString(c.getColumnIndex(IDX_EMAIL));
//        fk_SchoolRollId = c.getInt(c.getColumnIndex(FK_SCHOOL_ROLL_ID));
//        idx_StudentNumber = c.getString(c.getColumnIndex(IDX_STUDENT_NUMBER));
//        idx_ShareCourse = c.getInt(c.getColumnIndex(IDX_SHARE_COURSE));
//        idx_Permission = c.getInt(c.getColumnIndex(IDX_PERMISSION));
    }

    public ContentValues getContentValues() {
//        ContentValues cv = new ContentValues();
//        cv.put("pk_UserId", pk_UserId);
//        cv.put("uk_Username", uk_Username);
//        cv.put("idx_Nickname", idx_Nickname);
//        cv.put("idx_Password", idx_Password);
//        cv.put("idx_Email", idx_Email);
//        cv.put("fk_SchoolRollId", fk_SchoolRollId);
//        cv.put("idx_StudentNumber", idx_StudentNumber);
//        cv.put("idx_ShareCourse", idx_ShareCourse);
//        cv.put("idx_Permission", idx_Permission);
        return cv;
    }

    public String toString() {
        String str = "";
        for (Entry<String, Object> item : cv.valueSet()) {
            str += item.getKey() + ": " + item.getValue().toString() + ",\n";
        }
        return str;
//        return pk_UserId + ", " + uk_Username + ", " + idx_Nickname + ", " +
//                idx_Password + ", " + idx_Email + ", " + fk_SchoolRollId + ", " +
//                idx_StudentNumber + ", " + idx_ShareCourse + ", " + idx_Permission;
    }

    public static String createTableSQL() {
        return "create table " + TABLE_USER_INFO + "(" +
                PK_USER_ID         + " integer primary key autoincrement," +
                UK_USERNAME        + " varchar(32)," +
                IDX_NICKNAME       + " text," +
                IDX_PASSWORD       + " varchar(32)," +
                IDX_EMAIL          + " varchar(64)," +
                FK_SCHOOL_ROLL_ID  + " int," +
                IDX_STUDENT_NUMBER + " varchar(32)," +
                IDX_SHARE_COURSE   + " int," +
                IDX_PERMISSION     + " int)";
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        return (username.equals(cv.get(UK_USERNAME)) && password.equals(cv.get(IDX_PASSWORD)));
    }
}
