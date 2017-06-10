package com.ecourse.activity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zpf on 2017/3/9.
 */

public class LocalManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context context;

    public LocalManager(Context context) {
        sp = context.getSharedPreferences("loginpref", android.app.Activity.MODE_PRIVATE);
        editor = sp.edit();
        this.context = context;
    }
    /**
     * 向本地SharedPreference文件中写入登录状态
     **/
    public void setLogin(boolean isLogin) {
        editor.putBoolean("logined", isLogin);
        editor.commit();
    }

    /**
     * 从本地SharedPreference文件中获取登录状态
     * */

    public boolean getLogin() {
        boolean logined = sp.getBoolean("logined", false);
        return logined;
    }


    public void setUserId(String userId) {
        if (userId != null) {
            editor.putString("userId", userId);
        }
        else {
            editor.putString("userId", "unknown");
        }
        editor.commit();
    }

    public String getUserId() {
        String userId;
        userId = sp.getString("userId", "unknown");
        return userId;
    }
}
