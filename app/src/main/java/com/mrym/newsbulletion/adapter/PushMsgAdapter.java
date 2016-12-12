package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.utils.ImageLoaderUtils;
import com.mrym.newsbulletion.utils.PicassoUtils;

import java.util.List;

/**
 * Created by Jian on 2016/11/27.
 */

public class PushMsgAdapter extends BaseRecyclerViewAdapter<NewsSummary.AdsBean> {
    private LayoutInflater mInflater;

    public PushMsgAdapter(Context context, List<NewsSummary.AdsBean> list) {
        super(list,context);
        this.mInflater = LayoutInflater.from(mContext);

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_pushmsg, parent, false);
        return new PushViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            PushViewHolder hd = (PushViewHolder) holder;
            NewsSummary.AdsBean newInfo = list.get(position);
//            ImageLoaderUtils.display(mContext,hd.getmImagepushImage(),newInfo.getImgsrc(),R.mipmap.shouyetu,R.mipmap.shouyetu);
            PicassoUtils.display(mContext,hd.getmImagepushImage(), newInfo.getImgsrc());
            hd.getmImagepushTitle().setText(newInfo.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
