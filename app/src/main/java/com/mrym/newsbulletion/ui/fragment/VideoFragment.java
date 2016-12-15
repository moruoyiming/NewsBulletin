package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseFragmentAdapter;
import com.mrym.newsbulletion.db.other.VideosChannelTableManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.VideoChannelTable;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.video.VideoPresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.leakcanary.RefWatcher;

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
    @Bind(R.id.l1)
    LinearLayout l1;
    private BaseFragmentAdapter fragmentAdapter;
    @Override
    protected VideoPresenter createPresenter() {
        return new VideoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =View.inflate(getActivity(), R.layout.fragment_video, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarCompat.setStatusBarColor(getActivity(), R.color.primary_dark);
        dynamicAddView(l1, "background", R.color.primary_dark);
        tab2.addView(LayoutInflater.from(getActivity()).inflate(R.layout.demo_smart_indicator2, tab2, false));
        SmartTabLayout viewPagerTab = (SmartTabLayout) getActivity().findViewById(R.id.viewpagertab2);
        List<String> channelNames = new ArrayList<>();
        List<VideoChannelTable> videoChannelTableList = VideosChannelTableManager.loadVideosChannelsMine();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        for (int i = 0; i < videoChannelTableList.size(); i++) {
            channelNames.add(videoChannelTableList.get(i).getChannelName());
            mNewsFragmentList.add(createListFragments(videoChannelTableList.get(i)));
        }
        fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager());
        fragmentAdapter.setFragmentList(mNewsFragmentList);
        fragmentAdapter.setmTitles(channelNames);
        viewpager2.setAdapter(fragmentAdapter);
        viewPagerTab.setViewPager(viewpager2);
    }

    private VideosFragment createListFragments(VideoChannelTable videoChannelTable) {
        VideosFragment fragment = new VideosFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GlobalVariable.VIDEO_TYPE, videoChannelTable.getChannelId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<VideoData> news) {

    }

    @Override
    public void loadComplete() {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tab2=null;
        viewpager2=null;
        fragmentAdapter=null;
        mvpPresenter=null;
        RefWatcher refWatcher = NewsApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
        ButterKnife.unbind(this);
    }
}
