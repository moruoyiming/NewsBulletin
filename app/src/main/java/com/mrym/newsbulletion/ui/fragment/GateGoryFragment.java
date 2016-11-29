package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseRecyclerViewAdapter;
import com.mrym.newsbulletion.adapter.NewsAdapter;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.NewsPhotoDetail;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryPresenter;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryView;
import com.mrym.newsbulletion.ui.activity.NewsBrowserActivity;
import com.mrym.newsbulletion.ui.activity.NewsDetailActivity;
import com.mrym.newsbulletion.ui.activity.PhotosDetailActivity;
import com.mrym.newsbulletion.ui.activity.ViewPagerActivity;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryFragment extends MvpFragment<GateGoryPresenter> implements GateGoryView {
    @Bind(R.id.category_list)
    XRecyclerView categoryList;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 0;
    private NewsAdapter ma;
    private List<NewsSummary> mNews;
    private String mCurrentCate = null;
    private BaseRecyclerViewAdapter.onInternalClickListener onInternalClickListener, picOnInternalClickListener;
    private String mNewsId;
    private String mNewsType;
    @Override
    protected GateGoryPresenter createPresenter() {
        return new GateGoryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_category, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNews = new ArrayList<>();
        ma = new NewsAdapter(mNews, getActivity());
        categoryList.setAdapter(ma);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(GlobalVariable.NEWS_ID);
            mNewsType = getArguments().getString(GlobalVariable.NEWS_TYPE);
        }
//        mNewsType="T1348647909107";
        categoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryList.setEmptyView(View.inflate(getContext(), R.layout.item_empty_view, null));
        categoryList.setRefreshProgressStyle(ProgressStyle.BallBeat);
        categoryList.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        categoryList.setLoadingMoreEnabled(true);
        categoryList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(GlobalVariable.ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(GlobalVariable.ACTION_LOAD_MORE);
            }
        });
        categoryList.setRefreshing(true);
        onInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary values) {

                try {
                    NewsDetailActivity.startAction(getActivity(),values.getPostid(),values.getImgsrc());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsSummary values) {

            }
        };
        picOnInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary values) {
                try {

                    NewsPhotoDetail newsPhotoDetail=new NewsPhotoDetail();
                    List<NewsPhotoDetail.Picture> list=new ArrayList<>();
                    if(values.getAds()!=null&&values.getAds().size()>0){
                        for(int i=0;i<values.getAds().size();i++){
                            NewsPhotoDetail.Picture picture=new NewsPhotoDetail.Picture();
                            picture.setImgSrc(values.getAds().get(i).getImgsrc());
                            picture.setTitle(values.getAds().get(i).getTitle());
                            list.add(picture);
                        }
                    }else if(values.getImgextra()!=null&&values.getImgextra().size()>0){
                        for(int i=0;i<values.getImgextra().size();i++){
                            NewsPhotoDetail.Picture picture=new NewsPhotoDetail.Picture();
                            picture.setImgSrc(values.getImgextra().get(i).getImgsrc());
                            list.add(picture);
                        }
                    }
                    newsPhotoDetail.setTitle(values.getTitle());
                    newsPhotoDetail.setPictures(list);
                    ViewPagerActivity.startAction(getActivity(),newsPhotoDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsSummary values) {

            }
        };
//        ma.setOnInViewClickListener(R.id.item_video_center, onInternalClickListener);
        ma.setOnInViewClickListener(R.id.item_smallpic_center, onInternalClickListener);
        ma.setOnInViewClickListener(R.id.item_bigpic_center, onInternalClickListener);
        ma.setOnInViewClickListener(R.id.item_two_center, picOnInternalClickListener);
        ma.setOnInViewClickListener(R.id.item_three_center, onInternalClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //TODO now visible to user
            Log.i("onHiddenChanged","  "+hidden);

        } else {
            //TODO now invisible to user
            Log.i("onHiddenChanged"," WHAT "+hidden);
            JCVideoPlayer.releaseAllVideos();
        }
    }


    public void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case GlobalVariable.ACTION_REFRESH:
                mNews.clear();
                mCurrentPageIndex = 0;
                mvpPresenter.getGategoryData(mNewsType, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex+=20;
                mvpPresenter.getGategoryData(mNewsType, mCurrentPageIndex);
                break;
        }
    }

    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<NewsSummary> news,String mtag) {
        ma.addAll(news);
    }


    @Override
    public void loadComplete() {
        try {
            if (mCurrentAction == GlobalVariable.ACTION_REFRESH) {
                categoryList.refreshComplete();
            }
            if (mCurrentAction == GlobalVariable.ACTION_LOAD_MORE) {
                categoryList.loadMoreComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNews = null;
        mvpPresenter = null;
        categoryList = null;
        ButterKnife.unbind(this);
    }
}
