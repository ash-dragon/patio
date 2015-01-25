package com.yjh.qinyuan.main.section;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.widget.HelveticaTextView;

public class DetailFragment extends BaseFragment {

    private HelveticaTextView mTitleTextView;
    private HelveticaTextView mPhone1TextView;
    private HelveticaTextView mPhone2TextView;
    private HelveticaTextView mNameTextView;
    private HelveticaTextView mAddressTextView;
    private HelveticaTextView mCategory1TextView;
    private HelveticaTextView mCategory2TextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        view.setClickable(true);
        init(view);

        return view;
    }


    @Override
    public void init(View rootView) {
        mTitleTextView = (HelveticaTextView) rootView.findViewById(R.id.n_name);
        mPhone1TextView = (HelveticaTextView) rootView.findViewById(R.id.phone1);
        mPhone2TextView = (HelveticaTextView) rootView.findViewById(R.id.phone2);
        mNameTextView = (HelveticaTextView) rootView.findViewById(R.id.contact);
        mAddressTextView = (HelveticaTextView) rootView.findViewById(R.id.address);
        mCategory1TextView = (HelveticaTextView) rootView.findViewById(R.id.category1);
        mCategory2TextView = (HelveticaTextView) rootView.findViewById(R.id.category2);

        TownBranch branch = (TownBranch) getArguments().getSerializable(Constants.MODEL_TOWN_BRANCH);
        mTitleTextView.setText(branch.getnName());
        mPhone1TextView.setText(branch.getPhone1());
        mPhone2TextView.setText(branch.getPhone2());
        mNameTextView.setText(branch.getUsername());
        mAddressTextView.setText(branch.getAddress());
        mCategory1TextView.setText(branch.getPtName());
        mCategory2TextView.setText(branch.getCtName());
    }
}
