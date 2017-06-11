package com.ecourse.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ecourse.database.UserInfoDao;
import com.ecourse.structure.UserBean;
import com.ecourse.structure.UserInfo;

import es.source.code.activity.R;

public class Info extends AppCompatActivity {
    private TextView edtUsername;
    private TextView edtNickname;
    private TextView edtSchool;
    private TextView edtStudentNo;
    private TextView edtEmail;
    private LocalManager localManager;
    private String userNameStr;
    private UserInfoDao userInfoDao;
    private UserInfo userInfo;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        edtEmail = (TextView) findViewById(R.id.edtEmail);
        edtNickname = (TextView) findViewById(R.id.edtNickname);
        edtSchool = (TextView) findViewById(R.id.edtSchool);
        edtStudentNo = (TextView) findViewById(R.id.edtStudentNo);
        edtUsername = (TextView) findViewById(R.id.edtUsername);

        localManager = new LocalManager(this);
        userNameStr = localManager.getUserId();
        userInfoDao = new UserInfoDao(this);

        userInfo = userInfoDao.get(userNameStr);
        userBean = userInfo.getUserBean();
        edtEmail.setText(userBean.getEmail());
        edtUsername.setText(userBean.getUserName());
        edtNickname.setText(userBean.getNickname());
        edtStudentNo.setText(userBean.getStudentNo());
        edtSchool.setText("中国科学技术大学");
    }
}
