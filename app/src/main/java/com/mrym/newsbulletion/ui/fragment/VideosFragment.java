package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseRecyclerViewAdapter;
import com.mrym.newsbulletion.adapter.NewsAdapter;
import com.mrym.newsbulletion.adapter.VideosAdapter;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryPresenter;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.mvp.fragment.videos.VideosPresenter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * des:视频fragment
 * Created by xsf
 * on 2016.09.17:30
 */
public class VideosFragment extends MvpFragment<VideosPresenter> implements VideoView{
    @Bind(R.id.category_list)
    XRecyclerView categoryList;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;
    private VideosAdapter ma;
    private List<VideoData> mViedeos;
    private String mCurrentCate = null;
    private int i = 0;
    private BaseRecyclerViewAdapter.onInternalClickListener onInternalClickListener, picOnInternalClickListener;

    @Override
    protected VideosPresenter createPresenter() {
        return new VideosPresenter(this);
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
        mViedeos = new ArrayList<>();
        ma = new VideosAdapter(mViedeos, getActivity());
        categoryList.setAdapter(ma);
        int position = FragmentPagerItem.getPosition(getArguments());
        ArrayList<HomeCateGory> homeCateGories = HomeCateGoryUtils.getInstance(getContext()).getHomeCateGory();
        mCurrentCate = homeCateGories.get(position).getField();
        categoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryList.setEmptyView(View.inflate(getContext(), R.layout.item_empty_view, null));
        categoryList.setRefreshProgressStyle(ProgressStyle.BallBeat);
        categoryList.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        categoryList.setLoadingMoreEnabled(false);
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
        Log.i("onActivityCreated", "界面被创建" + i++);
        onInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsEntity>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsEntity values) {
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsEntity values) {

            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    public void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case GlobalVariable.ACTION_REFRESH:
                mViedeos.clear();
                mCurrentPageIndex = 1;
                mvpPresenter.getVideosData(mCurrentCate, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                mvpPresenter.getVideosData(mCurrentCate, mCurrentPageIndex);
                break;
        }
    }
    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<VideoData> videoDatas) {
        ma.addAll(videoDatas);
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
}
