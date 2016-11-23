package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseRecyclerViewAdapter;
import com.mrym.newsbulletion.adapter.PhotoGirlsAdapter;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowPresenter;
import com.mrym.newsbulletion.mvp.fragment.follow.FollowView;
import com.mrym.newsbulletion.ui.activity.PhotosDetailActivity;
import com.mrym.newsbulletion.ui.activity.ViewPagerActivity;
import com.mrym.newsbulletion.utils.GlideImageLoader;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowFragment extends MvpFragment<FollowPresenter> implements FollowView {

    @Bind(R.id.fragment_follow_r)
    XRecyclerView girlsList;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;
    private static int SIZE = 20;
    private List<PhotoGirl> mPhotogirls;
    private PhotoGirlsAdapter mPhotoAdapter;
    private BaseRecyclerViewAdapter.onInternalClickListener onPhotoGirlsClick;
    private ArrayList<String> images;
    private String[] titles;

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_follow, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPhotogirls = new ArrayList<>();
        mPhotoAdapter = new PhotoGirlsAdapter(mPhotogirls, getActivity());
        girlsList.setAdapter(mPhotoAdapter);
        images = new ArrayList<>();
        images.add("http://f.hiphotos.baidu.com/news/q%3D100/sign=c0c87a1c7af082022b92953f7bfafb8a/4d086e061d950a7b6f8bd0fb03d162d9f2d3c957.jpg");
        images.add("http://d.hiphotos.baidu.com/news/q%3D100/sign=ca91accda844ad3428bf8387e0a30c08/35a85edf8db1cb1312e63233d454564e92584b06.jpg");
        titles = new String[]{"习近平出席亚太峰会并发表主旨演讲", "习近平出席亚太咨询理事会代表对话会"};
        View view = View.inflate(getActivity(), R.layout.fragment_banner, null);
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        girlsList.addHeaderView(view);
        girlsList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlsList.setEmptyView(View.inflate(getContext(), R.layout.item_empty_view, null));
        girlsList.setRefreshProgressStyle(ProgressStyle.BallBeat);
        girlsList.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        girlsList.setLoadingMoreEnabled(true);
        girlsList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(GlobalVariable.ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(GlobalVariable.ACTION_LOAD_MORE);
            }
        });
        girlsList.setRefreshing(true);
        onPhotoGirlsClick = new BaseRecyclerViewAdapter.onInternalClickListener<PhotoGirl>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, PhotoGirl values) {
                PhotosDetailActivity.startAction(getActivity(), values.getUrl());
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, PhotoGirl values) {

            }
        };
        mPhotoAdapter.setOnInViewClickListener(R.id.item_grils_im, onPhotoGirlsClick);
    }

    public void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case GlobalVariable.ACTION_REFRESH:
                mPhotogirls.clear();
                mCurrentPageIndex = 1;
                mvpPresenter.getPhotoGrils(SIZE, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                mvpPresenter.getPhotoGrils(SIZE, mCurrentPageIndex);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<PhotoGirl> girls) {
        Log.i("FollowFragment", mPhotogirls.size() + "daxiao");
        mPhotoAdapter.addAll(girls);
    }

    @Override
    public void loadComplete() {
        try {
            if (mCurrentAction == GlobalVariable.ACTION_REFRESH) {
                girlsList.refreshComplete();
            }
            if (mCurrentAction == GlobalVariable.ACTION_LOAD_MORE) {
                girlsList.loadMoreComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
