package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.MyApplication;
import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class CommonHttpTask extends BaseHttpTask {

    public CommonHttpTask(Activity activity, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mParams.put(Constants.KEY, MyApplication.sUserKey);
    }
}
