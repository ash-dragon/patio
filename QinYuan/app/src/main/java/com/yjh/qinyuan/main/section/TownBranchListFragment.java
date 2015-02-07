package com.yjh.qinyuan.main.section;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.Agent;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.gson.TownBranchModel;
import com.yjh.qinyuan.main.DetailActivity;
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
        getActionBar().setRightButton(R.drawable.btn_info, new View.OnClickListener() {
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
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.MODEL_TOWN_BRANCH, mTownBranches.get(position));
//                DetailFragment fragment = new DetailFragment();
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.content, fragment);
//                fragmentTransaction.commit();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constants.MODEL_TOWN_BRANCH, mTownBranches.get(position));
                startActivity(intent);
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
//                    mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
//                            android.R.layout.simple_list_item_1, names));
                    mListView.setAdapter(new ListAdapter());
                } else {
                    rootView.findViewById(R.id.no_data_text).setVisibility(View.VISIBLE);
                }
            }
        });
        task.executeGet();
    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTownBranches == null ? 0 : mTownBranches.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TownBranch branch = mTownBranches.get(position);
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout, null);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(branch.getnName());

            if (branch.getPtId() == 1000) {
                convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.light_green));
            } else if (branch.getPtId() == 1002) {
                convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.light_purple));

            } else {
                convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.light_red));
            }

            return convertView;
        }
    }
}
