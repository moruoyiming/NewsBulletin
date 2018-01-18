package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class FollowFragment extends MvpFragment<FollowPresenter> implements FollowView {

    @BindView(R.id.fragment_follow_r)
    XRecyclerView girlsList;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;
    private List<PhotoGirl> mPhotogirls;
    private PhotoGirlsAdapter mPhotoAdapter;
    private BaseRecyclerViewAdapter.onInternalClickListener onPhotoGirlsClick;

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void initView() {
        mPhotogirls = new ArrayList<>();
        mPhotoAdapter = new PhotoGirlsAdapter(mPhotogirls, getActivity());
        girlsList.setAdapter(new ScaleInAnimationAdapter(mPhotoAdapter));
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
        girlsList.refresh();
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
                mvpPresenter.getPhotoGrils(GlobalVariable.SIZE, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                mvpPresenter.getPhotoGrils(GlobalVariable.SIZE, mCurrentPageIndex);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPhotogirls.clear();
        mPhotogirls = null;
        mPhotoAdapter = null;
        mvpPresenter = null;
        onPhotoGirlsClick = null;
        girlsList = null;
    }

    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<PhotoGirl> girls) {
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
