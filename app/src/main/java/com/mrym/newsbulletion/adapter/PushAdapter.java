package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.Enum.PushTypeEnum;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.ui.activity.NewsDetailActivity;
import com.mrym.newsbulletion.utils.ImageLoaderUtils;
import com.mrym.newsbulletion.utils.common.DateUtils;

import java.util.List;

/**
 * Created by Jian on 2016/11/27.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */

public class PushAdapter extends BaseRecyclerViewAdapter<NewsSummary> {
    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;
    private Context mContext;
    private BaseRecyclerViewAdapter.onInternalClickListener onInternalClickListener;
    public PushAdapter(List<NewsSummary> list, Context context) {
        super(list, context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        onInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary.AdsBean>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary.AdsBean values) {
                NewsDetailActivity.startAction(mContext,values.getUrl(),values.getImgsrc());
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
            case PushTypeEnum.PUSHTYPE_SINGLE:
                view = mInflater.inflate(R.layout.layout_item_singlepush, parent, false);
                break;
            case PushTypeEnum.PUSHTYPE_IMAGE:
                view = mInflater.inflate(R.layout.layout_item_pushmsg, parent, false);
                break;
            case PushTypeEnum.PUSHTYPE_MULTI:
                view = mInflater.inflate(R.layout.layout_item_multipush, parent, false);
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
                case PushTypeEnum.PUSHTYPE_SINGLE:
                    hd.getmSinglepushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmSingleAuthName().setText(newsSummary.getSource());
                    hd.getmSinglePushTitle().setText(newsSummary.getTitle());
                    hd.getmSinglePubliTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    ImageLoaderUtils.display(mContext,hd.getmSinglePushImage(),newsSummary.getImgsrc(),R.mipmap.shouyetu,R.mipmap.shouyetu);
                    break;
                case PushTypeEnum.PUSHTYPE_MULTI:
                    hd.getmMultipushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmMultipushTitle().setText(newsSummary.getTitle());
                    ImageLoaderUtils.display(mContext, hd.getmMultipushImage(), newsSummary.getAds().get(0).getImgsrc(),R.mipmap.shouyetu,R.mipmap.shouyetu);
                    hd.getmMultiRecycleview().setLayoutManager(new LinearLayoutManager(mContext));
                    PushMsgAdapter adapter = new PushMsgAdapter(mContext, newsSummary.getAds());
                    hd.getmMultiRecycleview().setAdapter(adapter);
                    adapter.setOnInViewClickListener(R.id.layout_item_pushmsg_l1,onInternalClickListener);
                    break;
                case PushTypeEnum.PUSHTYPE_IMAGE:
                    hd.getmMultipushTime().setText(DateUtils.formatDate(newsSummary.getLmodify()));
                    hd.getmMultipushTitle().setText(newsSummary.getTitle());
                    ImageLoaderUtils.display(mContext, hd.getmMultipushImage(), newsSummary.getImgextra().get(0).getImgsrc(),R.mipmap.shouyetu,R.mipmap.shouyetu);
                    hd.getmMultiRecycleview().setLayoutManager(new LinearLayoutManager(mContext));
                    PushMsgAdapter adapter2 = new PushMsgAdapter(mContext, newsSummary.getAds());
                    hd.getmMultiRecycleview().setAdapter(adapter2);
                    adapter2.setOnInViewClickListener(R.id.layout_item_pushmsg_l1,onInternalClickListener);
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
        NewsSummary pushInfo = list.get(position);
        int itemType = 0;
//        if (PushTypeEnum.SINGLE.getCode() == pushInfo.getPushType()) {
//            itemType = PushTypeEnum.SINGLE.getCode();
//        } else if (PushTypeEnum.MULTI.getCode() == pushInfo.getPushType()) {
//            itemType = PushTypeEnum.MULTI.getCode();
//        }else if (PushTypeEnum.IMAGE.getCode() == pushInfo.getPushType()) {
//            itemType = PushTypeEnum.IMAGE.getCode();
//        }
        if ("photoset".equals(pushInfo.getSkipType())) {
            if (pushInfo.getImgextra() != null && pushInfo.getImgextra().size() > 0) {
                itemType = PushTypeEnum.MULTI.getCode();
            } else {
                itemType = PushTypeEnum.IMAGE.getCode();
            }
        } else if ("special".equals(pushInfo.getSkipType())) {
            itemType = PushTypeEnum.SINGLE.getCode();
        } else {
            itemType = PushTypeEnum.SINGLE.getCode();
        }
        return itemType;
    }
    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
