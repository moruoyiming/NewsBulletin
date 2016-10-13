package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseRecyclerViewAdapter;
import com.mrym.newsbulletion.adapter.NewsAdapter;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.video.VideoPresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.ui.activity.ViewPagerActivity;
import com.mrym.newsbulletion.ui.activity.vitamio.VitamioListActivity;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideoFragment extends MvpFragment<VideoPresenter> implements VideoView {
    @Bind(R.id.tab2)
    FrameLayout tab2;
    @Bind(R.id.viewpager2)
    ViewPager viewpager2;
    @Override
    protected VideoPresenter createPresenter() {
        return new VideoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =View.inflate(getActivity(), R.layout.fragment_video, null);
//        initToolBar(root, R.string.app_name);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dynamicAddView(tab2, "background", R.color.primary_dark);
        tab2.addView(LayoutInflater.from(getActivity()).inflate(R.layout.demo_smart_indicator, tab2, false));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SmartTabLayout viewPagerTab = (SmartTabLayout) getActivity().findViewById(R.id.viewpagertab2);
        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        ArrayList<HomeCateGory> homeCateGories = HomeCateGoryUtils.getInstance(getContext()).getHomeCateGory();
        for (HomeCateGory homeCateGory : homeCateGories) {
            pages.add(FragmentPagerItem.of(homeCateGory.getCategory(), GateGoryFragment.class));
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getFragmentManager(), pages);
        viewpager2.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager2);
    }
    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<NewsEntity> news) {

    }

    @Override
    public void loadComplete() {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
