package com.yjh.qinyuan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.util.SharedPreferenceUtils;
import com.yjh.qinyuan.widget.widget.HelveticaButton;
import com.yjh.qinyuan.widget.widget.HelveticaEditText;
import com.yjh.qinyuan.widget.widget.HelveticaTextView;


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
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    private boolean validLogin() {
        // todo, valid
        // todo, if success save preference:
//        SharedPreferenceUtils.saveSharedPreference(LoginActivity.this, MyApplication.LOGIN_NAME, MyApplication.LOGIN_KEY, true);
        return true;
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
