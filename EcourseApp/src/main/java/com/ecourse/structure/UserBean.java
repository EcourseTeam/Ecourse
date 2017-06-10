package com.ecourse.structure;

/**
 * Created by zpf on 2017/6/9.
 */

public class UserBean {
    private String userName;
    private String nickname;
    private String school;
    private String email;
    private String password;
    private String studentNo;

    public UserBean(String userName, String nickname, String school, String email, String password, String studentNo) {
        this.userName = userName;
        this.nickname = nickname;
        this.school = school;
        this.email = email;
        this.password = password;
        this.studentNo = studentNo;
    }

    public UserBean() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
