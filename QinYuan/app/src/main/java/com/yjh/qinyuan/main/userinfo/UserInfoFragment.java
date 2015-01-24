package com.yjh.qinyuan.main.userinfo;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjh.qinyuan.LoginActivity;
import com.yjh.qinyuan.MyApplication;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.sugar.UserData;
import com.yjh.qinyuan.sugar.UserInfo;
import com.yjh.qinyuan.task.LogoutTask;
import com.yjh.qinyuan.widget.widget.HelveticaTextView;
import com.yjh.qinyuan.widget.widget.LoadingProgress;

import net.tsz.afinal.http.AjaxCallBack;

public class UserInfoFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        return view;
    }

    @Override
    public void init(View rootView) {
        HelveticaTextView usernameText = (HelveticaTextView) rootView.findViewById(R.id.username);
        HelveticaTextView nameText = (HelveticaTextView) rootView.findViewById(R.id.name);
        HelveticaTextView phone1Text = (HelveticaTextView) rootView.findViewById(R.id.phone1);
        HelveticaTextView phone2Text = (HelveticaTextView) rootView.findViewById(R.id.phone2);
        HelveticaTextView categoryText = (HelveticaTextView) rootView.findViewById(R.id.category);

        UserData userData = UserInfo.find(UserInfo.class,
                "key = ?", new String[]{MyApplication.sUserKey}).get(0).getUserData();
        usernameText.setText(userData.getUsername());
        nameText.setText(userData.getName());
        phone1Text.setText(userData.getPhone1());
        phone2Text.setText(userData.getPhone2());
        categoryText.setText(MyApplication.getUserTypeRes(userData.getUserType()));

        rootView.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutTask();
            }
        });
    }

    private void logoutTask() {
        LogoutTask task = new LogoutTask(getActivity(), new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                mProgressBar = LoadingProgress.getProgressBar(getActivity());
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                logout();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                logout();
            }
        });
        task.executeGet();
    }

    private void logout() {
        mProgressBar.dismiss();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}