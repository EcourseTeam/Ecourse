package com.ecourse.database;

import android.content.ContentValues;
import android.content.Context;

import com.ecourse.structure.UserInfo;
import com.ecourse.util.Constants;

public class UserInfoDao extends OfflineDaoImpl {

    public UserInfoDao(Context ctx) {
        super(ctx);
    }

    /**
     * Add a new entry into UserInfo table with username and password. Other information will be initialized in default.
     * @param username username of the user.
     * @param password password of the user.
     * @return insert status. -1 if failed.
     */
    public long add(String username, String password) {
        return add(username, username, password, "", -1, "", 1, 0);
    }

    /**
     * Add a new entry into UserInfo table with username and password. Other information will be initialized in default.
     * @param username username of the user.
     * @param password password of the user.
     * @param email
     * @param schoolRollId 学籍ID
     * @return insert status. -1 if failed.
     */
    public long add(String username, String password, String email, int schoolRollId) {
        return add(username, username, password, email, schoolRollId, "", 1, 0);
    }

    /**
     * Add a new entry into UserInfo table.
     * @param username
     * @param nickname
     * @param password
     * @param email
     * @param schoolRollId 学籍ID
     * @param studentNumber 学号
     * @param shareCourse 是否共享课程信息
     * @param permission
     * @return insert status. -1 if failed.
     */
    public long add(String username, String nickname,
                    String password, String email, int schoolRollId,
                    String studentNumber, int shareCourse, int permission) {
        UserInfo userInfo = new UserInfo(username, nickname, password, email, schoolRollId, studentNumber, shareCourse, permission);
        return addEntry(TABLE_USER_INFO, userInfo);
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return true if yes, false if not.
     */
    public boolean has(String username) {
        UserInfo userInfo = get(username);
        return (userInfo != null);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public UserInfo get(String username) {
        ContentValues filter = new ContentValues();
        filter.put("uk_Username", username);
        UserInfo[] userInfos = (UserInfo[]) getEntries(TABLE_USER_INFO, filter);
        if (userInfos.length > 0) {
            return userInfos[0];
        } else {
            return null;
        }
    }

    public boolean check(String username, String password) {
        UserInfo userInfo = get(username);
        return userInfo.checkUsernameAndPassword(username, password);
    }

}
