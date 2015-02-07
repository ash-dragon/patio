package com.yjh.qinyuan.main.userinfo;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjh.qinyuan.LoginActivity;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.UserData;
import com.yjh.qinyuan.gson.UserInfoModel;
import com.yjh.qinyuan.task.LogoutTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Utils;
import com.yjh.qinyuan.widget.HelveticaTextView;

public class UserInfoFragment extends BaseFragment implements View.OnClickListener {

    private UserData mUserData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_user_info, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(R.string.user_info);

        return mRootView;
    }

    @Override
    public void init() {
        HelveticaTextView usernameText = (HelveticaTextView) mRootView.findViewById(R.id.username);
        HelveticaTextView nameText = (HelveticaTextView) mRootView.findViewById(R.id.name);
        HelveticaTextView phone1Text = (HelveticaTextView) mRootView.findViewById(R.id.phone1);
        HelveticaTextView phone2Text = (HelveticaTextView) mRootView.findViewById(R.id.phone2);
        HelveticaTextView categoryText = (HelveticaTextView) mRootView.findViewById(R.id.category);

        mUserData = UserInfoModel.listAll(UserInfoModel.class).get(0).getUserData();
        usernameText.setText(mUserData.getUsername());
        nameText.setText(mUserData.getName());
        phone1Text.setText(mUserData.getPhone1());
        phone2Text.setText(mUserData.getPhone2());

        if (mUserData.getUserType() == 0) {
            categoryText.setText(R.string.type_1);
        } else if (mUserData.getUserType() == 1) {
            categoryText.setText(R.string.type_2);
        } else if (mUserData.getUserType() == 2) {
            categoryText.setText(R.string.type_3);
        }

        mRootView.findViewById(R.id.logout).setOnClickListener(this);
        mRootView.findViewById(R.id.icon_phone1).setOnClickListener(this);
        mRootView.findViewById(R.id.icon_phone2).setOnClickListener(this);
    }

    private void logoutTask() {
        LogoutTask task = new LogoutTask(getActivity(), new RequestCallBack(getActivity(), mProgressBar) {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                logout();
            }
        });
        task.executeGet();
    }

    private void logout() {
        UserInfoModel.clearData();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                logoutTask();
                break;
            case R.id.icon_phone1:
                Utils.phoneCall(getActivity(), ((HelveticaTextView)
                        mRootView.findViewById(R.id.phone1)).getText().toString());
                break;
            case R.id.icon_phone2:
                Utils.phoneCall(getActivity(), ((HelveticaTextView)
                        mRootView.findViewById(R.id.phone2)).getText().toString());
                break;
        }
    }
}
