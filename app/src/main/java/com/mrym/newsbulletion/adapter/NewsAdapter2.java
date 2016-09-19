package com.mrym.newsbulletion.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.utils.common.MsgDateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsAdapter2 extends BaseRecyclerViewAdapter<NewsEntity> {

    private final String TAG = "NewsAdapter2";
    private LayoutInflater mInflater;

    public NewsAdapter2(List<NewsEntity> list, Context context) {
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
            case GlobalVariable.ITEM_SMALLPIC:
                view = mInflater.inflate(R.layout.item_smallpic_view, parent, false);
                break;
            case GlobalVariable.ITEM_BIGPIC:
                view = mInflater.inflate(R.layout.item_bigpic_view, parent, false);
                break;
            case GlobalVariable.ITEM_EXCLUSIVE:
                view = mInflater.inflate(R.layout.item_exclusive_view, parent, false);
                break;
        }
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        NewsViewHolder hd = (NewsViewHolder) holder;
        NewsEntity newsEntity = list.get(position);
        Log.d(TAG, "newsEntity内容：" + newsEntity.toString());
        hd.getmAutherName().setText(newsEntity.getAuthor());
        hd.getmTiltle().setText(newsEntity.getTitle());
        hd.getmCommontNumber().setText(newsEntity.getCommentNum() + "评论");
        hd.getmPublicdate().setText(MsgDateUtils.getTimestampString(new Date(newsEntity.getPublishTime())));
        Picasso.with(mContext).load(newsEntity.getHeadimg()).placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(hd.getmAutherHead());
        switch (getItemViewType(position)) {
            case GlobalVariable.ITEM_TEXT:
                break;
            case GlobalVariable.ITEM_BIGPIC:
                Picasso.with(mContext).load(newsEntity.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmToppic());
                break;
            case GlobalVariable.ITEM_SMALLPIC:
                Picasso.with(mContext).load(newsEntity.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmRightpic());
                break;
            case GlobalVariable.ITEM_EXCLUSIVE:
                Picasso.with(mContext).load(newsEntity.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom1());
                Picasso.with(mContext).load(newsEntity.getPicTwo()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom2());
                Picasso.with(mContext).load(newsEntity.getPicThr()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(hd.getmBottom3());
                break;
            case GlobalVariable.ITEM_VIDEO:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        NewsEntity msgContent = list.get(position);
        return msgContent.getNewType();

    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }
}
