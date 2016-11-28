package com.mrym.newsbulletion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.SurfaceView;
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
public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView mTiltle;
    private TextView mAutherName;
    private TextView mCommontNumber;
    private TextView mPublicdate;
    private ImageView mAutherHead;

    private ImageView mToppic;
    private TextView mNumber;

    private ImageView mRightpic;


    private ImageView mBottom_t1;
    private ImageView mBottom_t2;

    private ImageView mBottom1;
    private ImageView mBottom2;
    private ImageView mBottom3;

    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private ImageView videologo;
    private TextView tv_from;
    private TextView tv_play_time;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mTiltle = (TextView) itemView.findViewById(R.id.item_basic_title);
        mAutherName = (TextView) itemView.findViewById(R.id.item_basic_authername);
        mCommontNumber = (TextView) itemView.findViewById(R.id.item_basic_commontnubmer);
        mPublicdate = (TextView) itemView.findViewById(R.id.item_basic_publicdate);
        mAutherHead = (ImageView) itemView.findViewById(R.id.item_basic_autherhead);
        mRightpic = (ImageView) itemView.findViewById(R.id.item_smallpic_rightpic);
        mToppic = (ImageView) itemView.findViewById(R.id.item_bigpic_toppic);
        mNumber = (TextView) itemView.findViewById(R.id.item_bigpic_number);

        mBottom_t1= (ImageView) itemView.findViewById(R.id.item_two_im1);
        mBottom_t2= (ImageView) itemView.findViewById(R.id.item_two_im2);

        mBottom1 = (ImageView) itemView.findViewById(R.id.item_three_im1);
        mBottom2 = (ImageView) itemView.findViewById(R.id.item_three_im2);
        mBottom3 = (ImageView) itemView.findViewById(R.id.item_three_im3);

        jcVideoPlayerStandard= (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        videologo= (ImageView) itemView.findViewById(R.id.iv_logo);
        tv_from= (TextView) itemView.findViewById(R.id.tv_from);
        tv_play_time= (TextView) itemView.findViewById(R.id.tv_play_time);
    }


    public TextView getmTiltle() {
        return mTiltle;
    }

    public void setmTiltle(TextView mTiltle) {
        this.mTiltle = mTiltle;
    }

    public TextView getmAutherName() {
        return mAutherName;
    }

    public void setmAutherName(TextView mAutherName) {
        this.mAutherName = mAutherName;
    }

    public TextView getmCommontNumber() {
        return mCommontNumber;
    }

    public void setmCommontNumber(TextView mCommontNumber) {
        this.mCommontNumber = mCommontNumber;
    }

    public TextView getmPublicdate() {
        return mPublicdate;
    }

    public void setmPublicdate(TextView mPublicdate) {
        this.mPublicdate = mPublicdate;
    }

    public ImageView getmAutherHead() {
        return mAutherHead;
    }

    public void setmAutherHead(ImageView mAutherHead) {
        this.mAutherHead = mAutherHead;
    }

    public ImageView getmToppic() {
        return mToppic;
    }

    public void setmToppic(ImageView mToppic) {
        this.mToppic = mToppic;
    }

    public TextView getmNumber() {
        return mNumber;
    }

    public void setmNumber(TextView mNumber) {
        this.mNumber = mNumber;
    }

    public ImageView getmRightpic() {
        return mRightpic;
    }

    public void setmRightpic(ImageView mRightpic) {
        this.mRightpic = mRightpic;
    }

    public ImageView getmBottom_t1() {
        return mBottom_t1;
    }

    public void setmBottom_t1(ImageView mBottom_t1) {
        this.mBottom_t1 = mBottom_t1;
    }

    public ImageView getmBottom_t2() {
        return mBottom_t2;
    }

    public void setmBottom_t2(ImageView mBottom_t2) {
        this.mBottom_t2 = mBottom_t2;
    }

    public ImageView getmBottom1() {
        return mBottom1;
    }

    public void setmBottom1(ImageView mBottom1) {
        this.mBottom1 = mBottom1;
    }

    public ImageView getmBottom2() {
        return mBottom2;
    }

    public void setmBottom2(ImageView mBottom2) {
        this.mBottom2 = mBottom2;
    }

    public ImageView getmBottom3() {
        return mBottom3;
    }

    public void setmBottom3(ImageView mBottom3) {
        this.mBottom3 = mBottom3;
    }

    public JCVideoPlayerStandard getJcVideoPlayerStandard() {
        return jcVideoPlayerStandard;
    }

    public void setJcVideoPlayerStandard(JCVideoPlayerStandard jcVideoPlayerStandard) {
        this.jcVideoPlayerStandard = jcVideoPlayerStandard;
    }

    public ImageView getVideologo() {
        return videologo;
    }

    public void setVideologo(ImageView videologo) {
        this.videologo = videologo;
    }

    public TextView getTv_from() {
        return tv_from;
    }

    public void setTv_from(TextView tv_from) {
        this.tv_from = tv_from;
    }

    public TextView getTv_play_time() {
        return tv_play_time;
    }

    public void setTv_play_time(TextView tv_play_time) {
        this.tv_play_time = tv_play_time;
    }
}
