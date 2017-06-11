package com.ecourse.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import es.source.code.activity.R;

public class LoadActivity extends Activity {

    private static final int LOAD_DISPLAY_TIME = 3000;
    private LocalManager localManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_load);
        localManager = new LocalManager(this);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (localManager.getLogin()){
                    Intent intent_main = new Intent(LoadActivity.this, Login.class);
                    LoadActivity.this.startActivity(intent_main);
                    LoadActivity.this.finish();
                }else {
                    Intent intent_reg = new Intent(LoadActivity.this, Register.class);
                    LoadActivity.this.startActivity(intent_reg);
                    LoadActivity.this.finish();
                }
            }
        }, LOAD_DISPLAY_TIME);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }
}
