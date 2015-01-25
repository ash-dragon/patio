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
import com.yjh.qinyuan.gson.AgentModel;
import com.yjh.qinyuan.gson.Site;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.main.userinfo.UserInfoFragment;
import com.yjh.qinyuan.task.GetAgentListTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.HttpUtils;

import java.util.ArrayList;

public class AgentListFragment extends BaseFragment {

    private ListView mListView;
    private ArrayList<Agent> mAgents;
    private Site mSite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_agent_list, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(mSite.getCityName());
        getActionBar().setRightButton(R.string.info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.MODEL_SITE, mSite);
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
        mSite = (Site) getArguments().getSerializable(Constants.MODEL_SITE);
        mListView = (ListView) mRootView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.MODEL_AGENT, mAgents.get(position));
                TownBranchListFragment fragment = new TownBranchListFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        getTownBranchFragment(mRootView);
    }

    private void getTownBranchFragment(final View rootView) {
        GetAgentListTask task = new GetAgentListTask(getActivity(),
                mSite.getCid(), new RequestCallBack(getActivity(), mProgressBar) {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                mAgents = HttpUtils.getJsonData(s, AgentModel.class).getAgents();
                ArrayList<String> names = new ArrayList<>();

                for (Agent agent : mAgents) {
                    names.add(agent.getAgentName());
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
