package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.VideoData;
import com.mrym.newsbulletion.utils.GlideUtils;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideosAdapter extends BaseRecyclerViewAdapter<VideoData> {

    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;
    private Context mContext;

    public VideosAdapter(List<VideoData> list, Context context) {
        super(list, context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_video_list, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            VideosViewHolder hd = (VideosViewHolder) holder;
            VideoData videoData = list.get(position);
            hd.getTv_play_time().setText(videoData.getTopicName());
            hd.getTv_from().setText(videoData.getVideosource());
            JCVideoPlayerStandard jcVideoPlayerStandard = hd.getJcVideoPlayerStandard();
            GlideUtils.getInstance().LoadContextBitmap(mContext, videoData.getTopicImg(),
                    hd.getVideologo(), R.mipmap.shouyetu, R.mipmap.shouyetu, GlideUtils.LOAD_BITMAP);
            boolean setUp =  jcVideoPlayerStandard.setUp(
                    videoData.getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    TextUtils.isEmpty(videoData.getDescription())?videoData.getTitle()+"":videoData.getDescription());
            if (setUp) {
                GlideUtils.getInstance().LoadContextBitmap(mContext, videoData.getCover(),
                        jcVideoPlayerStandard.thumbImageView, R.mipmap.shouyetu, R.mipmap.shouyetu, GlideUtils.LOAD_BITMAP);
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
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
