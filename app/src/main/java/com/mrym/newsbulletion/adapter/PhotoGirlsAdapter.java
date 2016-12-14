package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.PhotoGirl;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.mrym.newsbulletion.utils.common.MsgDateUtils;

import java.util.Date;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class PhotoGirlsAdapter extends BaseRecyclerViewAdapter<PhotoGirl> {

    private final String TAG = "PhotoGirlsAdapter";
    private LayoutInflater mInflater;

    public PhotoGirlsAdapter(List<PhotoGirl> list, Context context) {
        super(list, context);
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_grils, parent, false);
        return new GirlsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            GirlsViewHolder hd = (GirlsViewHolder) holder;
            PhotoGirl photoGirl = list.get(position);
            PicassoUtils.display(mContext,hd.getGirlsimage(),photoGirl.getUrl());
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
