package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class GetAgentListTask extends CommonHttpTask {
    public GetAgentListTask(Activity activity, String cid, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.AGENT);
        mParams.put(Constants.CID, cid);
    }
}
