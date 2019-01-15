package com.mfl.mycore;

import java.io.Serializable;

/**
 * Created by lij on 2017/7/31/031.
 * 用户表bean
 */

public class UserBean implements Serializable {


    private String loginName;
    private String loginPwd;

    public String getLoginName() {
        return loginName == null ? "" : loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? "" : loginName;
    }

    public String getLoginPwd() {
        return loginPwd == null ? "" : loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? "" : loginPwd;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                '}';
    }
}
