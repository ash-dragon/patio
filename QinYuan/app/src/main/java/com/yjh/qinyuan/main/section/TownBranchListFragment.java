package com.yjh.qinyuan.main.section;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.Agent;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.gson.TownBranchModel;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.task.GetTownBranchListTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.HttpUtils;

import java.util.ArrayList;

public class TownBranchListFragment extends BaseFragment {

    private ListView mListView;
    private ArrayList<TownBranch> mTownBranches;
    private Agent mAgent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_branch_list, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(mAgent.getAgentName());
        getActionBar().setRightButton(R.string.info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.MODEL_AGENT, mAgent);
                InfoFragment fragment = new InfoFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return mRootView;
    }

    @Override
    public void init() {
        mAgent = (Agent) getArguments().getSerializable(Constants.MODEL_AGENT);
        mListView = (ListView) mRootView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.MODEL_TOWN_BRANCH, mTownBranches.get(position));
                DetailFragment fragment = new DetailFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }
        });


        getAgentsTask(mRootView);
    }

    private void getAgentsTask(final View rootView) {
        GetTownBranchListTask task = new GetTownBranchListTask(getActivity(),
                mAgent.getAgentId(), new RequestCallBack(getActivity(), mProgressBar) {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                mTownBranches = HttpUtils.getJsonData(s, TownBranchModel.class).getTownBranches();
                ArrayList<String> names = new ArrayList<>();

                for (TownBranch branch : mTownBranches) {
                    names.add(branch.getnName());
                }

                if (names.size() > 0) {
                    rootView.findViewById(R.id.no_data_text).setVisibility(View.GONE);
                    mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1, names));
                } else {
                    rootView.findViewById(R.id.no_data_text).setVisibility(View.VISIBLE);
                }
            }
        });
        task.executeGet();
    }
}
