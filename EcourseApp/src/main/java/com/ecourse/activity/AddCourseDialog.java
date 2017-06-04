package com.ecourse.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import es.source.code.activity.R;

/**
 * Created by zpf on 2017/6/3.
 */
public class AddCourseDialog {

    private EditText course_name;
    private EditText course_address;
    private EditText course_teacher;
    private EditText course_week;
    //	private EditText course_time1,course_time2;
    private EditText course_count;


    private View view;
    private Context context;
    private LayoutInflater inflater;
    private AlertDialog.Builder builder;
    String s1="",s2="",s3="",s4="",s5="",s6="",s7="";

    public AddCourseDialog(Context context){
        this.context=context;
    }
    /*
    * 点击未编辑的课程列表跳出”添加课程“对话框
    */
    public void add(final int day,final int n){
        //填装对话框的view
        inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.edit_shedule,null);
        findWidgetes();//取部件
        final Button course_time1=(Button)view.findViewById(R.id.time1);
        final Button course_time2=(Button)view.findViewById(R.id.time2);
        //为两个输入时间的按钮绑定监听器
        course_time1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimeSet_Dialog(course_time1);
            }
        });
        course_time2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimeSet_Dialog(course_time2);
            }
        });

        builder=new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_launcher2)
                .setTitle("编辑课程信息")
                .setView(view)
                .setPositiveButton("确认",new DialogInterface.OnClickListener(){

                    @SuppressWarnings("deprecation")
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if(!(s1=course_name.getText().toString()).equals("")) s1=s1;
                        if(!(s2=course_address.getText().toString()).equals("")) s2=s2;
                        if(!(s3=course_teacher.getText().toString()).equals("")) s3=s3;
                        if(!(s4=course_week.getText().toString()).equals("")) s4=s4;
                        if(!(s6=course_time1.getText().toString()).equals("")) s6=s6;
                        if(!(s7=course_time2.getText().toString()).equals("")) ;

                        if((s5=course_count.getText().toString()).equals("")||s1.equals("")) {
                            Toast.makeText(context, "请正确输入课程及节数！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            int i=Integer.parseInt(s5.trim());//i为节数
                            for(int m=0;m<i;m++){
                                AddCoursesActivity.db.update(day,n+m+1,s1,s2,s3,s4,s5,s6,s7,Integer.toString(m));
                            }
                        }
                    }

                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                    }

                });
        builder.create().show();

    }

    private void findWidgetes(){
        course_name=(EditText)view.findViewById(R.id.editText1);
        course_address=(EditText)view.findViewById(R.id.editText2);
        course_teacher=(EditText)view.findViewById(R.id.editText3);
        course_week=(EditText)view.findViewById(R.id.editText4);
        course_count=(EditText)view.findViewById(R.id.jieshu);
    }

    public void TimeSet_Dialog(final TextView text){
        Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来。
        new TimePickerDialog(context,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute){
                        //获取完整的时间，在只有一位的数字前面加0
                        StringBuffer s_hour = new StringBuffer();
                        StringBuffer s_minute = new StringBuffer();
                        s_hour.append(hourOfDay);
                        s_minute.append(minute);
                        if(hourOfDay<10){
                            s_hour.insert(0,"0");
                        }
                        if(minute<10){
                            s_minute.insert(0,"0");
                        }
                        //将结果显示在edit中
                        text.setText(s_hour.toString() + ":" + s_minute.toString());
                    }
                }
                //设置初始时间
                , c.get(Calendar.HOUR_OF_DAY)
                , c.get(Calendar.MINUTE)
                //true表示采用24小时制
                , true).show();
    }
}
