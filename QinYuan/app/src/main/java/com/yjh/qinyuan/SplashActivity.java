package com.yjh.qinyuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.gson.UserInfoModel;

import java.util.List;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_MILLISECONDS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    @Override
    public void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<UserInfoModel> infoModels = UserInfoModel.listAll(UserInfoModel.class);

                if (infoModels.size() == 1) {
                    MyApplication.sUserKey = infoModels.get(0).getKey();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, SPLASH_MILLISECONDS);
    }
}
