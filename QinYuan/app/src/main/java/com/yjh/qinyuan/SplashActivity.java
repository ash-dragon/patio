package com.yjh.qinyuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.gson.UserInfoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

                if (!isValid()) {
                    Toast.makeText(SplashActivity.this, "App过期啦！", Toast.LENGTH_LONG).show();
                    SplashActivity.this.finish();
                } else {
                    if (infoModels.size() == 1) {
                        MyApplication.sUserKey = infoModels.get(0).getKey();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }

                finish();
            }
        }, SPLASH_MILLISECONDS);
    }

    private boolean isValid() {
//        String valid_until = "30/07/2015";
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        try {
//            Date strDate = sdf.parse(valid_until);
//            Log.d("DATE", "Decimal: " + (new Date().getTime()- strDate.getTime())/(24*60*60*1000));
//            if (new Date().after(strDate)) {
//                return false;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        return true;
    }
}
