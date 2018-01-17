package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.VideosAdapter;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.video.VideoView;
import com.mrym.newsbulletion.mvp.fragment.videos.VideosPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideosFragment extends MvpFragment<VideosPresenter> implements VideoView {
    @BindView(R.id.category_list)
    XRecyclerView categoryList;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;
    private VideosAdapter videosAdapter;
    private List<VideoData> mViedeos;
    private String mVideoType = null;

    @Override
    protected VideosPresenter createPresenter() {
        return new VideosPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        mViedeos = new ArrayList<>();
        videosAdapter = new VideosAdapter(mViedeos, getActivity());
        categoryList.setAdapter(videosAdapter);
        if (getArguments() != null) {
            mVideoType = getArguments().getString(GlobalVariable.VIDEO_TYPE);
        }
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
        categoryList.refresh();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViedeos.clear();
        mViedeos = null;
        categoryList = null;
        mvpPresenter = null;
        JCVideoPlayer.releaseAllVideos();
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
                mvpPresenter.getVideosData(mVideoType, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                mvpPresenter.getVideosData(mVideoType, mCurrentPageIndex);
                break;
        }
    }

    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<VideoData> videoDatas) {
        videosAdapter.addAll(videoDatas);
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
