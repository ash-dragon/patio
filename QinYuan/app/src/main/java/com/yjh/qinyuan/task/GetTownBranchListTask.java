package com.yjh.qinyuan.task;

import android.app.Activity;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.http.AjaxCallBack;

public class GetTownBranchListTask extends CommonHttpTask {

    public GetTownBranchListTask(Activity activity, String aid, AjaxCallBack<String> callBack) {
        super(activity, callBack);
        mUrl.append(Constants.TOWN_BRANCH);
        mParams.put(Constants.AID, aid);
    }
}
