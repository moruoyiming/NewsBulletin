package com.mrym.newsbulletion.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseFragmentAdapter;
import com.mrym.newsbulletion.db.entity.ChannelSelected;
import com.mrym.newsbulletion.db.other.NewsChannelTableManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.HomeOrderBean;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.home.HomePresenter;
import com.mrym.newsbulletion.mvp.fragment.home.HomeView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab)
    FrameLayout tab;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private BaseFragmentAdapter fragmentAdapter;
    private List<String> channelNames;
    private List<Fragment> mNewsFragmentList;
    private ChanelChangeReceiver chanelChangeReceiver;
    private  SmartTabLayout viewPagerTab;
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dynamicAddView(header, "background", R.color.primary_dark);
        tab.addView(LayoutInflater.from(getActivity()).inflate(R.layout.demo_smart_indicator, tab, false));
        viewPagerTab = (SmartTabLayout) getActivity().findViewById(R.id.viewpagertab);
        channelNames = new ArrayList<>();
        mNewsFragmentList = new ArrayList<>();
        List<ChannelSelected> newsChannelTables = NewsChannelTableManager.loadNewsChannelsStatic();
        for (int i = 0; i < newsChannelTables.size(); i++) {
            channelNames.add(newsChannelTables.get(i).getNewsChannelName());
            mNewsFragmentList.add(createListFragments(newsChannelTables.get(i)));
        }
        fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager());
        fragmentAdapter.setFragmentList(mNewsFragmentList);
        fragmentAdapter.setmTitles(channelNames);
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(fragmentAdapter);
        viewPagerTab.setViewPager(viewpager);
        chanelChangeReceiver=new ChanelChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalVariable.CHANELCHANGERECEIVER);
        getActivity().registerReceiver(chanelChangeReceiver, filter);
    }

    private GateGoryFragment createListFragments(ChannelSelected homeCateGory) {
        GateGoryFragment fragment = new GateGoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GlobalVariable.NEWS_ID, homeCateGory.getNewsChannelName());
        bundle.putString(GlobalVariable.NEWS_TYPE, homeCateGory.getNewsChannelId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mvpPresenter.getOrderPriceSum(tool.getAccountId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        channelNames.clear();
        mNewsFragmentList.clear();
        channelNames=null;
        mNewsFragmentList=null;
        getActivity().unregisterReceiver(chanelChangeReceiver);
        chanelChangeReceiver=null;
        mvpPresenter=null;
//        RefWatcher refWatcher = NewsApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
        ButterKnife.unbind(this);
    }


    @Override
    public void getSuccess(HomeOrderBean order) {

    }

    @Override
    public void getFailure(int code, String msg) {

    }
    class ChanelChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println(intent.getAction());
            if (intent.getAction().equals(GlobalVariable.CHANELCHANGERECEIVER)) {
                Log.d("ChanelChangeReceiver","ChanelChangeReceiver 频道发生变化");
                channelNames.clear();
                mNewsFragmentList.clear();
                List<ChannelSelected> newsChannelTables = NewsChannelTableManager.loadNewsChannelsStatic();
                for (int i = 0; i < newsChannelTables.size(); i++) {
                    channelNames.add(newsChannelTables.get(i).getNewsChannelName());
                    mNewsFragmentList.add(createListFragments(newsChannelTables.get(i)));
                }
                fragmentAdapter.setFragmentList(mNewsFragmentList);
                fragmentAdapter.setmTitles(channelNames);
                viewPagerTab.setViewPager(viewpager);
            }

        }
    }
}
