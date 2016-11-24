package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
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
public class NewsAdapter extends BaseRecyclerViewAdapter<NewsSummary> {

    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;
    private Context mContext;

    public NewsAdapter(List<NewsSummary> list, Context context) {
        super(list, context);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
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
            NewsSummary newsEntity = list.get(position);
//            Log.d(TAG, "newsEntity内容：" + newsEntity.toString());
            hd.getmAutherName().setText(newsEntity.getSource());
            hd.getmTiltle().setText(newsEntity.getTitle());
            hd.getmCommontNumber().setText(newsEntity.getReplyCount() + "评论");
            hd.getmPublicdate().setText(newsEntity.getPtime());
            Glide.with(mContext).load(newsEntity.getImgsrc()).dontAnimate().placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(hd.getmAutherHead());

            switch (getItemViewType(position)) {
                case GlobalVariable.ITEM_TEXT:
                    break;
                case GlobalVariable.ITEM_BIGPIC:
                    Glide.with(mContext).load(newsEntity.getImgsrc()).dontAnimate().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmToppic());
                    break;
                case GlobalVariable.ITEM_SMALLPIC:
                    Glide.with(mContext).load(newsEntity.getImgsrc()).dontAnimate().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmRightpic());
                    break;
                case GlobalVariable.ITEM_EXCLUSIVE:
                    Glide.with(mContext).load(newsEntity.getImgextra().get(0).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom1());
                    Glide.with(mContext).load(newsEntity.getImgextra().get(1).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom2());
//                    Glide.with(mContext).load(newsEntity.getImgextra().get(2).getImgsrc() + "").dontAnimate().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom3());
                    break;
                case GlobalVariable.ITEM_VIDEO:
//                    JCVideoPlayerStandard jcVideoPlayerStandard=hd.getJcVideoPlayerStandard();
//                    boolean setUp = jcVideoPlayerStandard.setUp(
//                            newsEntity.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                            newsEntity.getTitle());
//                    if (setUp) {
//                        Glide.with(mContext).load(newsEntity.getVideoPic())
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .centerCrop()
//                                .error(R.mipmap.ic_launcher)
//                                .crossFade().into(jcVideoPlayerStandard.thumbImageView);
//                    }
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
                return GlobalVariable.ITEM_EXCLUSIVE;
            } else {
                return GlobalVariable.ITEM_SMALLPIC;
            }
        } else if ("special".equals(msgContent.getSkipType())) {
            return GlobalVariable.ITEM_BIGPIC;
        } else {
            return GlobalVariable.ITEM_SMALLPIC;
        }
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
