package com.yjh.qinyuan.task;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yjh.qinyuan.LoginActivity;
import com.yjh.qinyuan.MyApplication;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.gson.UserInfoModel;
import com.yjh.qinyuan.widget.widget.LoadingProgress;
import net.tsz.afinal.http.AjaxCallBack;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestCallBack extends AjaxCallBack<String> {

    private LoadingProgress mProgressBar;
    private Activity mActivity;

    public RequestCallBack(Activity activity, LoadingProgress progressBar) {
        this.mActivity = activity;
        this.mProgressBar = progressBar;
    }

    @Override
    public boolean isProgress() {
        return super.isProgress();
    }

    @Override
    public int getRate() {
        return super.getRate();
    }

    @Override
    public AjaxCallBack<String> progress(boolean progress, int rate) {
        return super.progress(progress, rate);
    }

    @Override
    public void onStart() {
        super.onStart();
        mProgressBar = LoadingProgress.show(mActivity);
    }

    @Override
    public void onLoading(long count, long current) {
        super.onLoading(count, current);
    }

    @Override
    public void onSuccess(String s) {
        super.onSuccess(s);
        mProgressBar.dismiss();

        try {
            JSONObject object = new JSONObject(s);
            Log.d("RequestCallBack", "erro: " + object.getString("erro"));
            if (object.getString("erro").equals(MyApplication.REQUEST_INCORRECT_CODE)) {
                logout();
            }
        } catch (JSONException e) {
            Log.d("RequestCallBack", "Exception: " + e);
            logout();
        }
    }

    @Override
    public void onFailure(Throwable t, int errorNo, String strMsg) {
        super.onFailure(t, errorNo, strMsg);
        mProgressBar.dismiss();
        Log.d("RequestCallBack", "strMsg: " + strMsg);
        Toast.makeText(mActivity, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    private void logout() {
        UserInfoModel.clearData();
        Intent intent = new Intent(mActivity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
