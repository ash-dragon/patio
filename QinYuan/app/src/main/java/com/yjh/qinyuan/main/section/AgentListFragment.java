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
import com.yjh.qinyuan.task.GetAgentListTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.HttpUtils;

import java.util.ArrayList;

public class AgentListFragment extends BaseFragment {

    private ListView mListView;
    private ArrayList<Agent> mAgents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agent_list, container, false);
        view.setClickable(true);
        init(view);

        return view;
    }


    @Override
    public void init(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.AID, mAgents.get(position).getAgentId());
                TownBranchListFragment fragment = new TownBranchListFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        getTownBranchFragment(rootView);
    }

    private void getTownBranchFragment(final View rootView) {
        String siteId = getArguments().getString(Constants.CID);
        GetAgentListTask task = new GetAgentListTask(getActivity(),
                siteId, new RequestCallBack(getActivity(), mProgressBar) {
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
