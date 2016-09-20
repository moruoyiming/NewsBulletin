package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.utils.common.MsgDateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsAdapter extends BaseRecyclerViewAdapter<NewsEntity> {

    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;
    private Context mContext;
    SimpleExoPlayer player;

    public NewsAdapter(List<NewsEntity> list, Context context) {
        super(list, context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case GlobalVariable.ITEM_TEXT:
                //接收文本
                view = mInflater.inflate(R.layout.item_text_view, parent, false);
                break;
            case GlobalVariable.ITEM_SMALLPIC:
                view = mInflater.inflate(R.layout.item_smallpic_view, parent, false);
                break;
            case GlobalVariable.ITEM_BIGPIC:
                view = mInflater.inflate(R.layout.item_bigpic_view, parent, false);
                break;
            case GlobalVariable.ITEM_EXCLUSIVE:
                view = mInflater.inflate(R.layout.item_exclusive_view, parent, false);
                break;
            case GlobalVariable.ITEM_VIDEO:
                view = mInflater.inflate(R.layout.item_video_view, parent, false);
                break;
        }
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        try {
            NewsViewHolder hd = (NewsViewHolder) holder;
            NewsEntity newsEntity = list.get(position);
//            Log.d(TAG, "newsEntity内容：" + newsEntity.toString());
            hd.getmAutherName().setText(newsEntity.getAuthor());
            hd.getmTiltle().setText(newsEntity.getTitle());
            hd.getmCommontNumber().setText(newsEntity.getCommentNum() + "评论");
            hd.getmPublicdate().setText(MsgDateUtils.getTimestampString(new Date(newsEntity.getPublishTime())));
            Glide.with(mContext).load(newsEntity.getHeadimg()).dontAnimate().fitCenter().placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(hd.getmAutherHead());
            switch (getItemViewType(position)) {
                case GlobalVariable.ITEM_TEXT:
                    break;
                case GlobalVariable.ITEM_BIGPIC:
                    Glide.with(mContext).load(newsEntity.getPicOne()).dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmToppic());
                    break;
                case GlobalVariable.ITEM_SMALLPIC:
                    Glide.with(mContext).load(newsEntity.getPicOne()).dontAnimate().fitCenter().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmRightpic());
                    break;
                case GlobalVariable.ITEM_EXCLUSIVE:
                    Glide.with(mContext).load(newsEntity.getPicOne()).dontAnimate().fitCenter().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom1());
                    Glide.with(mContext).load(newsEntity.getPicTwo()).dontAnimate().fitCenter().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom2());
                    Glide.with(mContext).load(newsEntity.getPicThr()).dontAnimate().fitCenter().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom3());
                    break;
                case GlobalVariable.ITEM_VIDEO:
                    ((NewsViewHolder) holder).getSimpleExoPlayerView().setPlayer(player);
                    player.setPlayWhenReady(true);
//                    player.prepare(videoSource);
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
        NewsEntity msgContent = list.get(position);
        return msgContent.getNewType();

    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
