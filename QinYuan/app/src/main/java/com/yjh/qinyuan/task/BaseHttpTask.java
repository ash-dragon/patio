//+---------------------------------------------------------------------------+
//| Copyright (c) 2013 Wayma Group, Inc.                                      |
//| All rights reserved.                                                      |
//|                                                                           |
//+---------------------------------------------------------------------------+
//| For help with this library, contact contact@wayma_group.com               |
//+---------------------------------------------------------------------------+
package com.yjh.qinyuan.task;

import android.app.Activity;
import android.util.Log;

import com.yjh.qinyuan.util.Constants;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.lang.ref.WeakReference;

public abstract class BaseHttpTask {

    public static final String TAG = "BaseHttpTask";

    public Activity mActivity;
	public StringBuilder mUrl;
	public AjaxParams mParams;
    public FinalHttp mHttp;
    public AjaxCallBack<String> mCallBack;

	public BaseHttpTask(Activity activity, AjaxCallBack<String> callBack) {
		this.mActivity = activity;
        this.mCallBack = callBack;
		this.mUrl = new StringBuilder();
        this.mParams = new AjaxParams();
        this.mHttp = new FinalHttp();
	}

//	public void buildUrl(String key, String value, boolean firstParam) {
//		if (firstParam) {
//			mUrl.append("?").append(key).append("=").append(value);
//		} else {
//			mUrl.append("&").append(key).append("=").append(value);
//		}
//	}
//
//	public void buildRESTUrl(String key, String value) {
//		mUrl.append("/").append(key).append("/").append(value);
//	}

	public void executeGet() {
        Log.e(TAG, "GET");
        Log.e(TAG, "url: " + mUrl);
        Log.e(TAG, "params:" + mParams);
        mHttp.get(mUrl.toString(), mParams, mCallBack);
    }

    public void executePost() {
        Log.e(TAG, "POST");
        Log.e(TAG, "url: " + mUrl);
        Log.e(TAG, "params: " + mParams);
        mHttp.get(mUrl.toString(), mParams, mCallBack);
    }
}

//public class UpdateProfileTask extends BaseHttpTask {
//    private ProfileInformationActivity mActivity;
//
//    public UpdateProfileTask(Activity activity) {
//        super(activity);
//        mActivity = (ProfileInformationActivity) activity;
//    }
//
//    @Override
//    public void execute() {
//        FinalHttp fh = new FinalHttp();
//        Log.e("mUrl", mUrl.toString());
//        Log.e("params", params.toString());
//        fh.post(mUrl.toString(), params, new AjaxCallBack<String>() {
//            @Override
//            public void onFailure(Throwable t, int errorNo, String strMsg) {
//                // TODO Auto-generated method stub
//                super.onFailure(t, errorNo, strMsg);
//                Log.e("onFailure", strMsg + "");
//                if (mWeakReference.get() instanceof ProfileInformationActivity) {
//                    GeneralTools.initPopupNetworkRetry(
//                            mWeakReference.get(),
//                            ((ProfileInformationActivity) mWeakReference.get()).mRetry);
//                }
//            }
//
//            @Override
//            public void onSuccess(String t) {
//                // TODO Auto-generated method stub
//                super.onSuccess(t);
//
//                try {
//                    Json_Result_Code code = JSON.parseObject(t,
//                            Json_Result_Code.class);
//                    if (code != null) {
//                        mActivity.setData(code);
//                    }
//                } catch (JSONException e) {
//                    if (mWeakReference.get() instanceof ProfileInformationActivity) {
//                        GeneralTools.initPopupNetworkRetry(
//                                mWeakReference.get(),
//                                ((ProfileInformationActivity) mWeakReference
//                                        .get()).mRetry);
//                    }
//                }
//            }
//        });
//    }
//}

//    public void launchTask() {
//        UpdateProfileTask task = new UpdateProfileTask(this);
//        task.mUrl.append(Constants.Link.HOST).append(Constants.Webservice.UPDATE_PROFILE);
//        task.params.put(Constants.Key.SSO, LoginInfos.get(this).getLoginInfo().sso_value);
//        String gender = (String) spProfileGender.getSelectedItem();
//        if (!TextUtils.isEmpty(gender)) {
//            if (gender.equals(getResources().getString(R.string.female))) {
//                task.params.put(Constants.Key.MBR_GENDER, "female");
//            } else {
//                task.params.put(Constants.Key.MBR_GENDER, "male");
//            }
//        }
//        task.params.put(Constants.Key.MBR_EMAIL, etEmail.getText().toString());
//        if (tv_birthday.getText() != null) {
//            task.params.put(Constants.Key.MBR_BIRTHDAY, tv_birthday.getText().toString());
//        }
//        task.params.put(Constants.Key.MBR_BIO, etBio.getText().toString());
//        if (tvInterestsTags.getTag() != null) {
//            task.params.put(Constants.Key.MBR_INTERESTS, tvInterestsTags.getTag().toString());
//        }
//        if (tvLookingFor.getTag() != null) {
//            task.params.put(Constants.Key.MBR_LOOKING_FOR, tvLookingFor.getTag().toString());
//        }
//
//        if (passwordChanged() && !isFacebookUser()) {
//            task.params.put(Constants.Key.MBR_PASSWORD, etPassword.getText().toString());
//        }
//
//        task.params.put(Constants.Key.LONGITUDE, LoginInfos.get(this).getLoginInfo().mbr_longitude);
//        task.params.put(Constants.Key.LATITUDE, LoginInfos.get(this).getLoginInfo().mbr_latitude);
//        task.execute();
//        UPLOAD_USER_INFO = false;
//    }
