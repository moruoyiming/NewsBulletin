package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.ui.activity.NewsDetailActivity;
import com.mrym.newsbulletion.utils.ImageLoaderUtils;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.mrym.newsbulletion.utils.common.DateUtils;

import java.util.List;

/**
 * Created by Jian on 2016/11/27.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */

public class PushAdapter extends BaseRecyclerViewAdapter<NewsSummary> {
    private final String TAG = "PushAdapter";
    private LayoutInflater mInflater;
    private BaseRecyclerViewAdapter.onInternalClickListener onInternalClickListener;

    public PushAdapter(List<NewsSummary> list, Context context) {
        super(list, context);
        this.mInflater = LayoutInflater.from(mContext);
        onInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary.AdsBean>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary.AdsBean values) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra(GlobalVariable.NEWS_POST_ID, values.getUrl());
                intent.putExtra(GlobalVariable.NEWS_IMG_RES, values.getImgsrc());
                mContext.startActivity(intent);
//                NewsDetailActivity.startAction(mContext, values.getUrl(), values.getImgsrc());
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsSummary.AdsBean values) {

            }
        };
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case GlobalVariable.ITEM_ONE_PIC:
                view = mInflater.inflate(R.layout.layout_item_singlepush, parent, false);
                break;
            case GlobalVariable.ITEM_BIGPIC:
                view = mInflater.inflate(R.layout.layout_item_pushmsg, parent, false);
                break;
            case GlobalVariable.ITEM_TWO_PIC:
                view = mInflater.inflate(R.layout.layout_item_multipush, parent, false);
                break;
            case GlobalVariable.ITEM_OTHER:
                view = mInflater.inflate(R.layout.layout_item_pushmsg, parent, false);
                break;
        }
        return new PushViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            PushViewHolder hd = (PushViewHolder) holder;
            NewsSummary newsSummary = list.get(position);
            switch (getItemViewType(position)) {
                case GlobalVariable.ITEM_ONE_PIC:
                    hd.getmSinglepushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmSingleAuthName().setText(newsSummary.getSource());
                    hd.getmSinglePushTitle().setText(newsSummary.getTitle());
                    hd.getmSinglePubliTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
//                    ImageLoaderUtils.display(mContext,hd.getmSinglePushImage(),newsSummary.getImgsrc(),R.mipmap.shouyetu,R.mipmap.shouyetu);
                    PicassoUtils.display(mContext, hd.getmSinglePushImage(), newsSummary.getImgsrc());
                    break;
                case GlobalVariable.ITEM_TWO_PIC:
                    hd.getmMultipushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmMultipushTitle().setText(newsSummary.getTitle());
                    if (newsSummary.getAds() != null && newsSummary.getAds().size() > 0) {
                        PicassoUtils.display(mContext, hd.getmMultipushImage(), newsSummary.getAds().get(0).getImgsrc());
                    } else {
                        PicassoUtils.display(mContext, hd.getmMultipushImage(), newsSummary.getImgextra().get(0).getImgsrc());
                    }
                    hd.getmMultiRecycleview().setLayoutManager(new LinearLayoutManager(mContext));
                    PushMsgAdapter adapter = new PushMsgAdapter(mContext, newsSummary.getAds());
                    hd.getmMultiRecycleview().setAdapter(adapter);
                    adapter.setOnInViewClickListener(R.id.layout_item_pushmsg_l1, onInternalClickListener);
                    break;
                case GlobalVariable.ITEM_BIGPIC:
                    hd.getmMultipushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmMultipushTitle().setText(newsSummary.getTitle());
                    PicassoUtils.display(mContext, hd.getmMultipushImage(), newsSummary.getImgextra().get(0).getImgsrc());
                    hd.getmMultiRecycleview().setLayoutManager(new LinearLayoutManager(mContext));
                    PushMsgAdapter adapter2 = new PushMsgAdapter(mContext, newsSummary.getAds());
                    hd.getmMultiRecycleview().setAdapter(adapter2);
                    adapter2.setOnInViewClickListener(R.id.layout_item_pushmsg_l1, onInternalClickListener);
                    break;
                case GlobalVariable.ITEM_OTHER:
                    hd.getmSinglepushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmSingleAuthName().setText(newsSummary.getSource());
                    hd.getmSinglePushTitle().setText(newsSummary.getTitle());
                    hd.getmSinglePubliTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    PicassoUtils.display(mContext, hd.getmSinglePushImage(), newsSummary.getAds().get(0).getImgsrc());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list == null) {
            Log.i(TAG, "list must not null");
        }
        NewsSummary msgContent = list.get(position);
        if ("photoset".equals(msgContent.getSkipType())) {
            if (msgContent.getImgextra() != null && msgContent.getImgextra().size() > 0) {
                return GlobalVariable.ITEM_TWO_PIC;
            } else if (msgContent.getAds() != null && msgContent.getAds().size() > 0) {
                return GlobalVariable.ITEM_TWO_PIC;
            } else {
                return GlobalVariable.ITEM_ONE_PIC;
            }
        } else if ("special".equals(msgContent.getSkipType())) {
            return GlobalVariable.ITEM_BIGPIC;
        } else if (1 == msgContent.getHasImg()) {
            return GlobalVariable.ITEM_OTHER;
        } else {
            return GlobalVariable.ITEM_ONE_PIC;
        }
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
