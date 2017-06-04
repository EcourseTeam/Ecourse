package com.ecourse.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import es.source.code.activity.R;

public class About extends AppCompatActivity {

    private ImageView backimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        MyApplication.getInstance().addActivity(this);

        backimage=(ImageView)findViewById(R.id.back_about);
        //为返回按钮绑定监听器
        backimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
