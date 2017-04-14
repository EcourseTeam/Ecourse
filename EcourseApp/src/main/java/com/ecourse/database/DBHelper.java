package com.ecourse.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.ecourse.structure.MemoInfo;
import com.ecourse.structure.UserInfo;
import com.ecourse.util.Constants;

class DBHelper extends SQLiteOpenHelper implements Constants {

    private final static String DB_NAME ="offline.db"; //数据库名
    private final static int VERSION = 1; //版本号
    private final static String tag = "DBHelper";

    //自带的构造方法
    public DBHelper(Context ctx, String name, CursorFactory factory,
                    int version) {
        super(ctx, name, factory, version);
    }

    //为了每次构造时不用传入dbName和版本号，自己得新定义一个构造方法
    public DBHelper(Context ctx){
        this(ctx, DB_NAME, null, VERSION);//调用上面的构造方法
    }

    //版本变更时
    public DBHelper(Context ctx,int version) {
        this(ctx, DB_NAME, null, version);
    }

    //当数据库创建的时候调用
    public void onCreate(SQLiteDatabase db) {
        Log.i(tag, "Create Table!");
        db.execSQL(UserInfo.createTableSQL());
        Log.i(tag, TABLE_USER_INFO + " created!");
        /* SchoolRollInfo */
        db.execSQL("create table " + TABLE_SCHOOL_ROLL_INFO + "(" +
                "pk_SchoolRollId int primary key," +
                "fk_SchoolId int," +
                "fk_MajorId int)");
        //addIdPool(db, TABLE_SCHOOL_ROLL_INFO);
        Log.i(tag, TABLE_SCHOOL_ROLL_INFO + " created!");
        /* SchoolInfo */
        db.execSQL("create table " + TABLE_SCHOOL_INFO + "(" +
                "pk_SchoolId int primary key," +
                "uk_SchoolName text)");
        //addIdPool(db, TABLE_SCHOOL_INFO);
        Log.i(tag, TABLE_SCHOOL_INFO + " created!");
        /* AcademyInfo */
        db.execSQL("create table " + TABLE_ACADEMY_INFO + "(" +
                "pk_AcademyId int primary key," +
                "fk_SchoolId int," +
                "idx_AcademyName text)");
        //addIdPool(db, TABLE_ACADEMY_INFO);
        Log.i(tag, TABLE_ACADEMY_INFO + " created!");
        /* MajorInfo */
        db.execSQL("create table " + TABLE_MAJOR_INFO + "(" +
                "pk_MajorId int primary key," +
                "fk_AcademyId int," +
                "idx_MajorName text," +
                "idx_Education text," +
                "idx_Semester int," +
                "idx_Week int)");
        //addIdPool(db, TABLE_MAJOR_INFO);
        Log.i(tag, TABLE_MAJOR_INFO + " created!");
        /* TeacherInfo */
        db.execSQL("create table " + TABLE_TEACHER_INFO + "(" +
                "pk_TeacherId int primary key," +
                "fk_AcademyId int," +
                "idx_TeacherName text," +
                "idx_OfficePhone var(32)," +
                "idx_CellPhone var(32)," +
                "idx_Email var(32))");
        //addIdPool(db, TABLE_TEACHER_INFO);
        Log.i(tag, TABLE_TEACHER_INFO + " created!");
        /* CourseInfo */
        db.execSQL("create table " + TABLE_COURSE_INFO + "(" +
                "pk_CourseId int primary key," +
                "fk_AcademyId int," +
                "idx_CourseName text," +
                "idx_CourseShortName text," +
                "idx_CourseNumber var(16)," +
                "fk_TeacherId int," +
                "idx_Semester int," +
                "idx_CourseHours int," +
                "idx_Materials text)");
        //addIdPool(db, TABLE_COURSE_INFO);
        Log.i(tag, TABLE_COURSE_INFO + " created!");
        /* CoursePeriodInfo */
        db.execSQL("create table " + TABLE_COURSE_PERIOD_INFO + "(" +
                "pk_CoursePeriodId int primary key," +
                "fk_CourseId int," +
                "idx_WeekFrom int," +
                "idx_WeekTo int," +
                "idx_Week int," +
                "idx_TimeFrom int," +
                "idx_TimeTo int," +
                "idx_Alarm int," +
                "idx_Place text)");
        //addIdPool(db, TABLE_COURSE_PERIOD_INFO);
        Log.i(tag, TABLE_COURSE_PERIOD_INFO + " created!");
        /* CourseTableInfo */
        db.execSQL("create table " + TABLE_COURSE_TABLE_INFO + "(" +
                "pk_CourseTableId int primary key," +
                "fk_UserId int," +
                "idx_CourseTableName text)");
        //addIdPool(db, TABLE_COURSE_TABLE_INFO);
        Log.i(tag, TABLE_COURSE_TABLE_INFO + " created!");
        /* CourseTableEntryInfo */
        db.execSQL("create table " + TABLE_COURSE_TABLE_ENTRY_INFO + "(" +
                "pk_CourseTableEntryId int primary key," +
                "fk_CourseTableId int," +
                "fk_CoursePeriodId int)");
        //addIdPool(db, TABLE_COURSE_TABLE_ENTRY_INFO);
        Log.i(tag, TABLE_COURSE_TABLE_ENTRY_INFO + " created!");
        db.execSQL(MemoInfo.createTableSQL());
    }

    //版本更新时调用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql  = "update student ....";//自己的Update操作
        db.execSQL(sql);
    }

//    long //addIdPool(SQLiteDatabase db, String tableName) {
//        ContentValues cv = new ContentValues();
//        cv.put(PK_TABLE_NAME, tableName);
//        cv.put(IDX_MAX_ID, 0);
//        return db.insert(TABLE_ID_POOL, null, cv);
//    }
}