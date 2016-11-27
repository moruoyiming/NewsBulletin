package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.Enum.PushTypeEnum;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewInfo;
import com.mrym.newsbulletion.domain.modle.PushInfo;
import com.mrym.newsbulletion.utils.GlideUtils;
import com.mrym.newsbulletion.utils.ImageLoaderUtils;
import com.mrym.newsbulletion.utils.common.DateUtils;

import java.util.List;

/**
 * Created by Jian on 2016/11/27.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */

public class PushAdapter extends BaseRecyclerViewAdapter<PushInfo> {
    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;
    private Context mContext;

    public PushAdapter(List<PushInfo> list, Context context) {
        super(list, context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
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
            PushInfo pushInfo = list.get(position);
            switch (getItemViewType(position)) {
                case PushTypeEnum.PUSHTYPE_SINGLE:
                    hd.getmSinglepushTime().setText(DateUtils.formatDate(pushInfo.getPushTime()));
                    hd.getmSingleAuthName().setText(pushInfo.getAuther());
                    hd.getmSinglePushTitle().setText(pushInfo.getTitle());
                    hd.getmSinglePubliTime().setText(DateUtils.formatDate(pushInfo.getPublishTime()));
                    ImageLoaderUtils.display(mContext,hd.getmSinglePushImage(),pushInfo.getPicUrl(),R.mipmap.shouyetu,R.mipmap.shouyetu);
                    break;
                case PushTypeEnum.PUSHTYPE_MULTI:
                    hd.getmMultipushTime().setText(DateUtils.formatDate(pushInfo.getPushTime()));
                    hd.getmMultipushTitle().setText(pushInfo.getTitle());
                    hd.getmMultiRecycleview().setLayoutManager(new LinearLayoutManager(mContext));
                    PushMsgAdapter adapter = new PushMsgAdapter(mContext, pushInfo.getMsgInfos());
                    hd.getmMultiRecycleview().setAdapter(adapter);
                    break;
                case PushTypeEnum.PUSHTYPE_IMAGE:
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
        PushInfo pushInfo = list.get(position);
        int itemType = 0;
        if (PushTypeEnum.SINGLE.getCode() == pushInfo.getPushType()) {
            itemType = PushTypeEnum.SINGLE.getCode();
        } else if (PushTypeEnum.MULTI.getCode() == pushInfo.getPushType()) {
            itemType = PushTypeEnum.MULTI.getCode();
        }else if (PushTypeEnum.IMAGE.getCode() == pushInfo.getPushType()) {
            itemType = PushTypeEnum.IMAGE.getCode();
        }
        return itemType;
    }
    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
