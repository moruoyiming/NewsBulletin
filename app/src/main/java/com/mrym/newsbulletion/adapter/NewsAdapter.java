package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.utils.ImageLoaderUtils;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsAdapter extends BaseRecyclerViewAdapter<NewsSummary> {

    private final String TAG = "NewsAdapter";
    private LayoutInflater mInflater;

    public NewsAdapter(List<NewsSummary> list, Context context) {
        super(list, context);
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
            case GlobalVariable.ITEM_BIGPIC:
                view = mInflater.inflate(R.layout.item_bigpic_view, parent, false);
                break;
            case GlobalVariable.ITEM_ONE_PIC:
                view = mInflater.inflate(R.layout.item_one_pic_view, parent, false);
                break;
            case GlobalVariable.ITEM_TWO_PIC:
                view = mInflater.inflate(R.layout.item_two_pic_view, parent, false);
                break;
            case GlobalVariable.ITEM_THREE_PIC:
                view = mInflater.inflate(R.layout.item_three_pic_view, parent, false);
                break;
            case GlobalVariable.ITEM_VIDEO:
                view = mInflater.inflate(R.layout.item_video_view, parent, false);
                break;
            case GlobalVariable.ITEM_OTHER:
                view = mInflater.inflate(R.layout.item_bigpic_view, parent, false);
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
            hd.getmAutherName().setText(newsEntity.getSource());
            hd.getmTiltle().setText(newsEntity.getTitle());
            hd.getmCommontNumber().setText(newsEntity.getReplyCount() + "评论");
            hd.getmPublicdate().setText(newsEntity.getPtime());
            Picasso.with(mContext)
                    .load(newsEntity.getImgsrc())
                    .placeholder(R.mipmap.shouyetu)
                    .error(R.mipmap.shouyetu)
                    .config(Bitmap.Config.RGB_565)
                    .into(hd.getmAutherHead());
            switch (getItemViewType(position)) {
                case GlobalVariable.ITEM_TEXT:
                    break;
                case GlobalVariable.ITEM_BIGPIC:
                    Picasso.with(mContext)
                            .load(newsEntity.getImgsrc())
                            .placeholder(R.mipmap.shouyetu)
                            .error(R.mipmap.shouyetu)
                            .config(Bitmap.Config.RGB_565)
                            .into(hd.getmToppic());
                    break;
                case GlobalVariable.ITEM_ONE_PIC:
                    Picasso.with(mContext)
                            .load(newsEntity.getImgsrc())
                            .placeholder(R.mipmap.shouyetu)
                            .error(R.mipmap.shouyetu)
                            .config(Bitmap.Config.RGB_565)
                            .into(hd.getmRightpic());
                    break;
                case GlobalVariable.ITEM_TWO_PIC:
                    if (newsEntity.getAds() != null && newsEntity.getAds().size() > 0) {
                        Picasso.with(mContext)
                                .load(newsEntity.getAds().get(0).getImgsrc())
                                .placeholder(R.mipmap.shouyetu)
                                .error(R.mipmap.shouyetu)
                                .config(Bitmap.Config.RGB_565)
                                .into(hd.getmBottom_t1());
                        Picasso.with(mContext)
                                .load(newsEntity.getAds().get(1).getImgsrc())
                                .placeholder(R.mipmap.shouyetu)
                                .error(R.mipmap.shouyetu)
                                .config(Bitmap.Config.RGB_565)
                                .into(hd.getmBottom_t2());
                    } else if (newsEntity.getImgextra() != null && newsEntity.getImgextra().size() > 0) {
                        Picasso.with(mContext)
                                .load(newsEntity.getImgextra().get(0).getImgsrc())
                                .placeholder(R.mipmap.shouyetu)
                                .error(R.mipmap.shouyetu)
                                .config(Bitmap.Config.RGB_565)
                                .into(hd.getmBottom_t1());
                        Picasso.with(mContext)
                                .load(newsEntity.getImgextra().get(1).getImgsrc())
                                .placeholder(R.mipmap.shouyetu)
                                .error(R.mipmap.shouyetu)
                                .config(Bitmap.Config.RGB_565)
                                .into(hd.getmBottom_t2());
                    }
                    break;
                case GlobalVariable.ITEM_THREE_PIC:
                    if (newsEntity.getAds() != null && newsEntity.getAds().size() > 0) {
                        Glide.with(mContext).load(newsEntity.getAds().get(0).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom1());
                        Glide.with(mContext).load(newsEntity.getAds().get(1).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom2());
                        Glide.with(mContext).load(newsEntity.getAds().get(2).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom3());
                    } else {
                        Glide.with(mContext).load(newsEntity.getImgextra().get(0).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom1());
                        Glide.with(mContext).load(newsEntity.getImgextra().get(1).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom2());
                        Glide.with(mContext).load(newsEntity.getImgextra().get(2).getImgsrc() + "").dontAnimate().centerCrop().placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom3());
                    }
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
                case GlobalVariable.ITEM_OTHER:
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
                if (msgContent.getImgextra().size() >= 3) {
                    return GlobalVariable.ITEM_THREE_PIC;
                } else if (msgContent.getImgextra().size() == 2) {
                    return GlobalVariable.ITEM_TWO_PIC;
                } else if (msgContent.getImgextra().size() == 1) {
                    return GlobalVariable.ITEM_BIGPIC;
                } else {
                    return GlobalVariable.ITEM_BIGPIC;
                }
            } else if (msgContent.getAds() != null && msgContent.getAds().size() > 0) {
                if (msgContent.getAds().size() >= 3) {
                    return GlobalVariable.ITEM_THREE_PIC;
                } else if (msgContent.getAds().size() == 2) {
                    return GlobalVariable.ITEM_TWO_PIC;
                } else if (msgContent.getAds().size() == 1) {
                    return GlobalVariable.ITEM_BIGPIC;
                } else {
                    return GlobalVariable.ITEM_BIGPIC;
                }
            } else {
                return GlobalVariable.ITEM_ONE_PIC;
            }
        } else if ("special".equals(msgContent.getSkipType())) {
            return GlobalVariable.ITEM_BIGPIC;
        } else if (1 == msgContent.getHasImg()) {
            return GlobalVariable.ITEM_OTHER;
        } else {
            return GlobalVariable.ITEM_ONE_PIC;
        }
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
