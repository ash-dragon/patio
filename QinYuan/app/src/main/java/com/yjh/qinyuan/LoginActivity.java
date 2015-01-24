package com.yjh.qinyuan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.sugar.UserInfo;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.task.LoginTask;
import com.yjh.qinyuan.util.HttpUtils;
import com.yjh.qinyuan.widget.widget.HelveticaButton;
import com.yjh.qinyuan.widget.widget.HelveticaEditText;
import com.yjh.qinyuan.widget.widget.LoadingProgress;

import net.tsz.afinal.http.AjaxCallBack;


public class LoginActivity extends BaseActivity {

    private HelveticaEditText mUsernameText;
    private HelveticaEditText mPasswordText;
    private HelveticaButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    @Override
    public void init() {
        mUsernameText = (HelveticaEditText) findViewById(R.id.username);
        mPasswordText = (HelveticaEditText) findViewById(R.id.password);
        mLoginButton = (HelveticaButton) findViewById(R.id.login);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validLogin()){
                    getUserTask();
                }
            }
        });
    }

    private boolean validLogin() {
        boolean isValid = true;
        if (TextUtils.isEmpty(mUsernameText.getText())) {
            mUsernameText.setError(getString(R.string.empty_username));
            isValid = false;
        }

        if (TextUtils.isEmpty(mPasswordText.getText())) {
            mPasswordText.setError(getString(R.string.empty_password));
            isValid = false;
        }

        return isValid;
    }

    private void getUserTask() {
        LoginTask task = new LoginTask(this, mUsernameText.getText().toString(),
                mPasswordText.getText().toString(), new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                mProgressBar = LoadingProgress.getProgressBar(LoginActivity.this);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                mProgressBar.dismiss();
                UserInfo userInfo = HttpUtils.getJsonData(s, UserInfo.class);
                userInfo.saveData(userInfo);
                MyApplication.sUserKey = userInfo.getKey();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }


            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                mProgressBar.dismiss();
                Toast.makeText(LoginActivity.this,
                        R.string.invalid_username_or_password, Toast.LENGTH_LONG).show();
            }
        });
        task.executeGet();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.tip)
                    .setMessage(R.string.quit_app_question)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
    }
}
