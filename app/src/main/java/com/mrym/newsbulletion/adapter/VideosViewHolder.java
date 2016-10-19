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
public class VideosViewHolder extends RecyclerView.ViewHolder {

    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private ImageView videologo;
    private TextView tv_from;
    private TextView tv_play_time;

    public VideosViewHolder(View itemView) {
        super(itemView);
        jcVideoPlayerStandard= (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        videologo= (ImageView) itemView.findViewById(R.id.iv_logo);
        tv_from= (TextView) itemView.findViewById(R.id.tv_from);
        tv_play_time= (TextView) itemView.findViewById(R.id.tv_play_time);
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
