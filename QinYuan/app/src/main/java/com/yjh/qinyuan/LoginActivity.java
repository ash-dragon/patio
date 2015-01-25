package com.yjh.qinyuan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.gson.UserInfoModel;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.task.LoginTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.HttpUtils;
import com.yjh.qinyuan.widget.HelveticaButton;
import com.yjh.qinyuan.widget.HelveticaEditText;
import com.yjh.qinyuan.widget.MyActionBar;


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

        ((MyActionBar) findViewById(R.id.action_bar)).setTitle(R.string.login);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validLogin()){
                    getUserTask();
                }
            }
        });

        mPasswordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mLoginButton.performClick();
                }
                return true;
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
                mPasswordText.getText().toString(), new RequestCallBack(this, mProgressBar) {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);

                try {
                    UserInfoModel userInfoModel = HttpUtils.getJsonData(s, UserInfoModel.class);
                    if (userInfoModel.isSuccess()) {
                        userInfoModel.saveData(userInfoModel);
                        MyApplication.sUserKey = userInfoModel.getKey();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                R.string.invalid_username_or_password, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this,
                            R.string.invalid_username_or_password, Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);

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
