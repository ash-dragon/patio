package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class LoginTask extends BaseHttpTask {

    public LoginTask(Activity activity, String username, String password, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.CHECK_USER);
        mParams.put(Constants.USERNAME, username);
        mParams.put(Constants.PASSWORD, password);
    }
}
