package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class GetSiteListTask extends CommonHttpTask {

    public GetSiteListTask(Activity activity, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.SITE);
    }
}
