package com.ecourse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ecourse.database.UserInfoDao;

import es.source.code.activity.R;


public class Register extends Activity {
    private EditText username;
    private EditText password;
    private EditText nickname;
    private EditText schoolid;
    private EditText studentnum;
    private EditText email;
    private Button button_reg;
    private Button button_back;
    private int isFromBack = 1;
    private int result_code1 = 101;
    private int result_code2 = 101;

    private UserInfoDao userInfoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        nickname = (EditText) findViewById(R.id.nickname);
        password = (EditText)findViewById(R.id.password);
        schoolid = (EditText) findViewById(R.id.school_id);
        studentnum = (EditText) findViewById(R.id.student_num);
        email = (EditText)findViewById(R.id.email);
        button_reg = (Button)findViewById(R.id.button_reg);
        button_back = (Button)findViewById(R.id.button_back);
        userInfoDao = new UserInfoDao(this);

        button_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();
                String nicknameStr = nickname.getText().toString();
                String schoolidStr = schoolid.getText().toString();
                String studentnumStr = studentnum.getText().toString();
                String emailStr = email.getText().toString();

                if (!checkString(usernameStr)) {
                    username.setError("输入内容不符合规则");
                }else if (userInfoDao.has(usernameStr)) {
                    Toast.makeText(Register.this, "用户名已被占用!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!checkString(passwordStr)) {
                    password.setError("输入内容不符合规则");
                } else if (nicknameStr == null){
                    Toast.makeText(Register.this, "昵称不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (schoolidStr == null) {
                    Toast.makeText(Register.this, "学校不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (studentnumStr == null) {
                    Toast.makeText(Register.this, "学号不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (emailStr == null) {
                    Toast.makeText(Register.this, "邮箱不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userInfoDao.add(usernameStr, nicknameStr, passwordStr, emailStr, -1, studentnumStr, 1, 0);

                    Toast.makeText(Register.this, "注册成功!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(Register.this,Login.class);
                    startActivity(intent);
                }

                }


        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("Key3","Return");
                bundle.putInt("isFromBack",isFromBack);
                intent.putExtras(bundle);
                setResult(result_code2,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    private boolean checkString(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
