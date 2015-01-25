package com.yjh.qinyuan.main.section;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.Agent;
import com.yjh.qinyuan.gson.Site;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.widget.HelveticaTextView;

public class InfoFragment extends BaseFragment {

    private HelveticaTextView mContact;
    private HelveticaTextView mPhone1;
    private HelveticaTextView mPhone2;
    private HelveticaTextView mAddress;
    private HelveticaTextView mRemark;

    private Site mSite;
    private Agent mAgent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_user_detail, container, false);
            mRootView.setClickable(true);
            init();
        }

        mSite = (Site) getArguments().getSerializable(Constants.MODEL_SITE);
        mAgent = (Agent) getArguments().getSerializable(Constants.MODEL_AGENT);

        if (mSite != null) {
            getActionBar().setTitle(mSite.getCityName());
            mContact.setText(mSite.getUsername());
            mPhone1.setText(mSite.getPhone1());
            mPhone2.setText(mSite.getPhone2());
            mAddress.setText(mSite.getAddress());
            mRemark.setText(mSite.getMemo());
        } else {
            getActionBar().setTitle(mAgent.getAgentName());
            mContact.setText(mAgent.getUsername());
            mPhone1.setText(mAgent.getPhone1());
            mPhone2.setText(mAgent.getPhone2());
            mAddress.setText(mAgent.getAddress());
            mRemark.setText(mAgent.getMemo());
        }

        getActionBar().hideRightButton();

        return mRootView;
    }


    @Override
    public void init() {
        mContact = (HelveticaTextView) mRootView.findViewById(R.id.contact);
        mPhone1 = (HelveticaTextView) mRootView.findViewById(R.id.phone1);
        mPhone2 = (HelveticaTextView) mRootView.findViewById(R.id.phone2);
        mAddress = (HelveticaTextView) mRootView.findViewById(R.id.address);
        mRemark = (HelveticaTextView) mRootView.findViewById(R.id.remark);
    }
}
