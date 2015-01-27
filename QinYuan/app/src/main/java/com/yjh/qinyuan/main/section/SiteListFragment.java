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
import com.yjh.qinyuan.gson.Site;
import com.yjh.qinyuan.gson.SiteModel;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.task.GetSiteListTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.HttpUtils;
import com.yjh.qinyuan.widget.XListView;

import java.util.ArrayList;

public class SiteListFragment extends BaseFragment {

    private XListView mListView;
    private ArrayList<Site> mSites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_site_list, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(R.string.section);
        getActionBar().hideRightButton();

        return mRootView;
    }

    @Override
    public void init() {
        mListView = (XListView) mRootView.findViewById(R.id.list_view);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(false);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.MODEL_SITE, mSites.get(position - 1));
                AgentListFragment fragment = new AgentListFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }
        });

        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getSitesTask(mRootView);
            }

            @Override
            public void onLoadMore() {

            }
        });


        getSitesTask(mRootView);

    }

    private void getSitesTask(final View rootView) {
        mSites = new ArrayList<>();
        GetSiteListTask task = new GetSiteListTask(getActivity(), new RequestCallBack(getActivity(), mProgressBar) {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                mListView.completeLoad();
                try {
                    SiteModel model = HttpUtils.getJsonData(s, SiteModel.class);
                    ArrayList<String> names = new ArrayList<>();
                    for (Site site : model.getSites()) {
                        names.add(site.getCityName());
                        mSites.add(site);
                    }

                    if (names.size() > 0) {
                        rootView.findViewById(R.id.no_data_text).setVisibility(View.GONE);
                        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, names));
                    } else {
                        rootView.findViewById(R.id.no_data_text).setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                mListView.completeLoad();
            }
        });
        task.executeGet();
    }

}
