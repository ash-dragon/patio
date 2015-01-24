package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.MyApplication;
import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class LogoutTask extends BaseHttpTask {

    public LogoutTask(Activity activity, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.SIGN_OUT);
        mParams.put(Constants.KEY, MyApplication.sUserKey);
    }
}
