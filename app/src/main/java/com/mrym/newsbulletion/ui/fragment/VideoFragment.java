package com.mrym.newsbulletion.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseFragmentAdapter;
import com.mrym.newsbulletion.db.other.VideosChannelTableManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.VideoChannelTable;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.video.VideoPresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideoFragment extends MvpFragment<VideoPresenter> implements VideoView {
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewpager2)
    ViewPager viewpager2;
    @BindView(R.id.l1)
    LinearLayout l1;
    private BaseFragmentAdapter fragmentAdapter;
    private CommonNavigatorAdapter commonNavigatorAdapter;
    private List<String> channelNames;

    @Override
    protected VideoPresenter createPresenter() {
        return new VideoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        dynamicAddView(l1, "background", R.color.primary_dark);
        channelNames = new ArrayList<>();
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
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigatorAdapter = new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return channelNames == null ? 0 : channelNames.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(channelNames.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewpager2.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewpager2);
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
        viewpager2 = null;
        fragmentAdapter = null;
        mvpPresenter = null;
    }
}
