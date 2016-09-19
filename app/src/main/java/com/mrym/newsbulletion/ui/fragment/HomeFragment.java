package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.domain.modle.HomeOrderBean;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.home.HomePresenter;
import com.mrym.newsbulletion.mvp.fragment.home.HomeView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab)
    FrameLayout tab;
    @Bind(R.id.header)
    LinearLayout header;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.fragment_home)
    LinearLayout fragmentHome;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_home, null);
        initToolBar(root, R.string.app_name);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab.addView(LayoutInflater.from(getActivity()).inflate(R.layout.demo_smart_indicator, tab, false));
        SmartTabLayout viewPagerTab = (SmartTabLayout) getActivity().findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        ArrayList<HomeCateGory> homeCateGories = HomeCateGoryUtils.getInstance(getContext()).getHomeCateGory();
        for (HomeCateGory homeCateGory : homeCateGories) {
            pages.add(FragmentPagerItem.of(homeCateGory.getCategory(), GateGoryFragment.class));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getFragmentManager(), pages);
        viewpager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
//        mvpPresenter.getOrderPriceSum(tool.getAccountId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void getSuccess(HomeOrderBean order) {

    }

    @Override
    public void getFailure(int code, String msg) {

    }
}
