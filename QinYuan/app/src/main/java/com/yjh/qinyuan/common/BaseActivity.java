package com.yjh.qinyuan.common;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.yjh.qinyuan.widget.LoadingProgress;
import com.yjh.qinyuan.widget.MyActionBar;

public abstract class BaseActivity extends ActionBarActivity {

    public LoadingProgress mProgressBar;
    private MyActionBar mActionBar;

    public MyActionBar getMyActionBar() {
        return mActionBar;
    }

    public void setMyActionBar(MyActionBar actionBar) {
        this.mActionBar = actionBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public abstract void init();

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
