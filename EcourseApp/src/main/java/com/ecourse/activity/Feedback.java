package com.ecourse.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import es.source.code.activity.R;

public class Feedback extends AppCompatActivity {

    private ImageView emailfeed;
    private ImageView backimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        emailfeed = (ImageView)findViewById(R.id.email_logo);
        backimage=(ImageView)findViewById(R.id.back_feed);
        emailfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:395649703@qq.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, "Ecourse课程表Android版问题反馈");
                data.putExtra(Intent.EXTRA_TEXT, "Ecourse课程表客服你们好！\n我现在使用的版本是version：1.0\n我在使用中遇到了如下问题：\n");
                startActivity(data);

            }
        });

        backimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
