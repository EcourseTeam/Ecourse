package com.ecourse.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.ecourse.structure.AcademyInfo;
import com.ecourse.structure.CourseInfo;
import com.ecourse.structure.CoursePeriodInfo;
import com.ecourse.structure.CourseTableEntryInfo;
import com.ecourse.structure.CourseTableInfo;
import com.ecourse.structure.MajorInfo;
import com.ecourse.structure.MemoInfo;
import com.ecourse.structure.SchoolInfo;
import com.ecourse.structure.SchoolRollInfo;
import com.ecourse.structure.TeacherInfo;
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
        db.execSQL(SchoolRollInfo.createTableSQL());
        db.execSQL(SchoolInfo.createTableSQL());
        db.execSQL(AcademyInfo.createTableSQL());
        db.execSQL(MajorInfo.createTableSQL());
        db.execSQL(TeacherInfo.createTableSQL());
        db.execSQL(CourseInfo.createTableSQL());
        db.execSQL(CoursePeriodInfo.createTableSQL());
        db.execSQL(CourseTableInfo.createTableSQL());
        db.execSQL(CourseTableEntryInfo.createTableSQL());
        db.execSQL(MemoInfo.createTableSQL());
        Log.i(tag, "Create Table Finished!");
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