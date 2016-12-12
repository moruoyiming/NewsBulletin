package com.mrym.newsbulletion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.adapter.BaseRecyclerViewAdapter;
import com.mrym.newsbulletion.adapter.PushAdapter;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.mvp.MvpActivity;
import com.mrym.newsbulletion.mvp.activity.push.PushPresenter;
import com.mrym.newsbulletion.mvp.activity.push.PushView;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.utils.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Jian on 2016/11/28.
 */

public class PushMsgActivity extends MvpActivity<PushPresenter> implements PushView {
    public static final String TAG = PushMsgActivity.class.getCanonicalName();
    @Bind(R.id.activity_pushmsg_rc)
    XRecyclerView mPushXRecyclerView;
    @Bind(R.id.left_back_title)
    TextView mTitle;
    @Bind(R.id.leftback_toobar_l1)
    RelativeLayout back;
    @Bind(R.id.header)
    LinearLayout header;
    protected int mCurrentAction = GlobalVariable.ACTION_REFRESH;
    protected int mCurrentPageIndex = 0;
    private int ccount = 10;
    private PushAdapter mPushAdapter;
    private List<NewsSummary> newsSummaries;
    private BaseRecyclerViewAdapter.onInternalClickListener onSinglepushClickListener,onmultipushClickListener;

    @Override
    protected PushPresenter createPresenter() {
        return new PushPresenter(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushmsg);
        StatusBarCompat.translucentStatusBar(PushMsgActivity.this,true);
        dynamicAddView(header, "background", R.color.primary_dark);
        mTitle.setText("消息推送");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsSummaries = new ArrayList<>();
        mPushAdapter = new PushAdapter(newsSummaries, PushMsgActivity.this);
        mPushXRecyclerView.setAdapter(mPushAdapter);
        mPushXRecyclerView.setLayoutManager(new LinearLayoutManager(PushMsgActivity.this));
        mPushXRecyclerView.setEmptyView(View.inflate(PushMsgActivity.this, R.layout.item_empty_view, null));
        mPushXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat);
        mPushXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        mPushXRecyclerView.setLoadingMoreEnabled(true);
        mPushXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(GlobalVariable.ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(GlobalVariable.ACTION_LOAD_MORE);
            }
        });
        mPushXRecyclerView.setRefreshing(true);
        onSinglepushClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary values) {
                NewsDetailActivity.startAction(PushMsgActivity.this,values.getPostid(),values.getImgsrc());
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsSummary values) {

            }
        };
        mPushAdapter.setOnInViewClickListener(R.id.item_singlepush_layout, onSinglepushClickListener);
        mPushAdapter.setOnInViewClickListener(R.id.item_multipush_layout, onSinglepushClickListener);
    }

    public void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case GlobalVariable.ACTION_REFRESH:
                newsSummaries.clear();
                mCurrentPageIndex = 0;
                mvpPresenter.getPushMsg(ccount, mCurrentPageIndex);
                break;
            case GlobalVariable.ACTION_LOAD_MORE:
                mCurrentPageIndex += 20;
                mvpPresenter.getPushMsg(ccount, mCurrentPageIndex);
                break;
        }
    }
    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, PushMsgActivity.class);
        mContext.startActivity(intent);
    }
    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<NewsSummary> news) {
        mPushAdapter.addAll(news);
    }

    @Override
    public void loadComplete() {
        try {
            if (mCurrentAction == GlobalVariable.ACTION_REFRESH) {
                mPushXRecyclerView.refreshComplete();
            }
            if (mCurrentAction == GlobalVariable.ACTION_LOAD_MORE) {
                mPushXRecyclerView.loadMoreComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
