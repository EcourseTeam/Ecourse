package com.ecourse.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.source.code.activity.R;

public class AddCoursesActivity extends AppCompatActivity {
    public static DataBase db;
    static class SingleInstance{
        static SingleInstance si;
        private SingleInstance(){
            for(int i=0;i<7;i++){
                db.createTable(i);
            }
        }
        static SingleInstance createTable(){
            if(si==null)
                return si=new SingleInstance();
            return null;
        }
    }

    private TextView mTvAddCourseSchoolname;
    private ListView mLvAddCourseCourselists;
    private ImageView backimage;

    private void assignViews() {
        mTvAddCourseSchoolname = (TextView) findViewById(R.id.tv_add_course_schoolname);
        mLvAddCourseCourselists = (ListView) findViewById(R.id.lv_add_course_courselists);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);
        db=new DataBase(this);
        backimage=(ImageView)findViewById(R.id.back_addcourse);
        initView();
    }

    private void initView() {
        assignViews();
        //这里从数据库获得的是游标所以可以为当前的listview设置游标适配器
        Cursor c= db.select(2);
        mLvAddCourseCourselists.setAdapter(new android.support.v4.widget.SimpleCursorAdapter(this, R.layout.add_course_lists,c,new String[]{"_id","classes","location",
                "teacher","zhoushu"},new int[]{R.id.number,R.id.ltext0,R.id.ltext1,R.id.ltext6}));

    }

    public void addCoursesManually(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher2);
        builder.setTitle("手动添加课程");
        //弹出自定义对话框,这里不能直接使用布局文件，版本21以下不支持
        //builder.setView(R.layout.select_course_daysandtime);
        final EditText et_day=new EditText(this);
        et_day.setHint("请输入周几上课(填写数字,周日为0)");
        final EditText et_time=new EditText(this);
        et_time.setHint("请输入第几节开始(填写数字)");

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(et_day);
        ll.addView(et_time);
        builder.setView(ll);
        builder.setPositiveButton("确定添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //把参数传入并且关闭当前对话框
                String str1 = et_day.getText().toString();
                if (!checkDay(str1)){
                    Toast.makeText(getApplication(),"error!!",Toast.LENGTH_LONG);
                    return;
                }else {
                    new AddCourseDialog(AddCoursesActivity.this).add(Integer.parseInt(et_day.getText().toString().trim()), Integer.parseInt(et_time.getText().toString().trim()) - 1);
                    dialogInterface.dismiss();
                }
            }
        });
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AddCoursesActivity.this, CourseTable.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private boolean checkDay(String s) {
        Pattern p=Pattern.compile("^[0-6]");
        Matcher matcher = p.matcher(s);
        return matcher.matches();
    }
    private boolean checkTime(String s) {
        return s.matches("^[1-12]");
    }

}
