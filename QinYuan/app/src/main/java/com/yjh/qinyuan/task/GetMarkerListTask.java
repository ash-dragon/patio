package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class GetMarkerListTask extends CommonHttpTask {
    public GetMarkerListTask(Activity activity, String longitude, String latitude, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.NEARBY_LOCATIONS);
        mParams.put(Constants.LONGITUDE, longitude);
        mParams.put(Constants.LATITUDE, latitude);
        mParams.put(Constants.LEN, "10000");
    }
}
