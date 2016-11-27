package com.mrym.newsbulletion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrym.newsbulletion.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class PushViewHolder extends RecyclerView.ViewHolder {

    // single
    private TextView mSinglepushTime;
    private TextView mSingleAuthName;
    private TextView mSinglePubliTime;
    private TextView mSinglePushTitle;
    private ImageView mSinglePushImage;
    private TextView mSinglePushMore;
    // mult
    private TextView mMultipushTime;
    private ImageView mMultipushImage;
    private TextView mMultipushTitle;
    private RecyclerView mMultiRecycleview;
    // image
    private TextView mImagepushTitle;
    private ImageView mImagepushImage;

    public PushViewHolder(View itemView) {
        super(itemView);
        mSinglepushTime = (TextView) itemView.findViewById(R.id.item_singlepush_pushtime);
        mSingleAuthName = (TextView) itemView.findViewById(R.id.item_singlepush_name);
        mSinglePubliTime = (TextView) itemView.findViewById(R.id.item_singlepush_publishtime);
        mSinglePushTitle = (TextView) itemView.findViewById(R.id.item_singlepush_title);
        mSinglePushMore = (TextView) itemView.findViewById(R.id.item_singlepush_more);
        mSinglePushImage = (ImageView) itemView.findViewById(R.id.item_singlepush_photo);

        mMultipushTime = (TextView) itemView.findViewById(R.id.item_multipush_pushtime);
        mMultipushImage = (ImageView) itemView.findViewById(R.id.item_multipush_photo);
        mMultipushTitle = (TextView) itemView.findViewById(R.id.item_multipush_title);
        mMultiRecycleview = (RecyclerView) itemView.findViewById(R.id.layout_item_multipush_rl);

        mImagepushTitle= (TextView) itemView.findViewById(R.id.layout_item_pushmsg_title);
        mImagepushImage= (ImageView) itemView.findViewById(R.id.layout_item_pushmsg_pic);
    }


    public TextView getmSinglepushTime() {
        return mSinglepushTime;
    }

    public void setmSinglepushTime(TextView mSinglepushTime) {
        this.mSinglepushTime = mSinglepushTime;
    }

    public TextView getmSingleAuthName() {
        return mSingleAuthName;
    }

    public void setmSingleAuthName(TextView mSingleAuthName) {
        this.mSingleAuthName = mSingleAuthName;
    }

    public TextView getmSinglePubliTime() {
        return mSinglePubliTime;
    }

    public void setmSinglePubliTime(TextView mSinglePubliTime) {
        this.mSinglePubliTime = mSinglePubliTime;
    }

    public TextView getmSinglePushTitle() {
        return mSinglePushTitle;
    }

    public void setmSinglePushTitle(TextView mSinglePushTitle) {
        this.mSinglePushTitle = mSinglePushTitle;
    }

    public ImageView getmSinglePushImage() {
        return mSinglePushImage;
    }

    public void setmSinglePushImage(ImageView mSinglePushImage) {
        this.mSinglePushImage = mSinglePushImage;
    }

    public TextView getmSinglePushMore() {
        return mSinglePushMore;
    }

    public void setmSinglePushMore(TextView mSinglePushMore) {
        this.mSinglePushMore = mSinglePushMore;
    }

    public TextView getmMultipushTime() {
        return mMultipushTime;
    }

    public void setmMultipushTime(TextView mMultipushTime) {
        this.mMultipushTime = mMultipushTime;
    }

    public ImageView getmMultipushImage() {
        return mMultipushImage;
    }

    public void setmMultipushImage(ImageView mMultipushImage) {
        this.mMultipushImage = mMultipushImage;
    }

    public TextView getmMultipushTitle() {
        return mMultipushTitle;
    }

    public void setmMultipushTitle(TextView mMultipushTitle) {
        this.mMultipushTitle = mMultipushTitle;
    }

    public RecyclerView getmMultiRecycleview() {
        return mMultiRecycleview;
    }

    public void setmMultiRecycleview(RecyclerView mMultiRecycleview) {
        this.mMultiRecycleview = mMultiRecycleview;
    }

    public TextView getmImagepushTitle() {
        return mImagepushTitle;
    }

    public void setmImagepushTitle(TextView mImagepushTitle) {
        this.mImagepushTitle = mImagepushTitle;
    }

    public ImageView getmImagepushImage() {
        return mImagepushImage;
    }

    public void setmImagepushImage(ImageView mImagepushImage) {
        this.mImagepushImage = mImagepushImage;
    }
}
