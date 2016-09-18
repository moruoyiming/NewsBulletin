package com.mrym.newsbulletion.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.utils.GlideUtils;
import com.mrym.newsbulletion.utils.common.MsgDateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Jian on 2016/9/13.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsEntity> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewsAdapter(Context context, List<NewsEntity> data) {
        super(data);
        addItemType(GlobalVariable.ITEM_TEXT, R.layout.item_text_view);
        addItemType(GlobalVariable.ITEM_SMALLPIC, R.layout.item_smallpic_view);
        addItemType(GlobalVariable.ITEM_BIGPIC, R.layout.item_bigpic_view);
        addItemType(GlobalVariable.ITEM_EXCLUSIVE, R.layout.item_exclusive_view);
        addItemType(GlobalVariable.ITEM_VIDEO, R.layout.item_video_view);
    }


    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        if (item == null) {
            return;
        }
        helper.setText(R.id.item_basic_title, item.getTitle());
        helper.setText(R.id.item_basic_authername, item.getAuthor());
        helper.setText(R.id.item_basic_commontnubmer, item.getCommentNum() + "评论");
        helper.setText(R.id.item_basic_publicdate, MsgDateUtils.getTimestampString(new Date(item.getPublishTime())));
        Picasso.with(mContext).load(item.getHeadimg()).placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(helper.getImageView(R.id.item_basic_autherhead));
        switch (item.getNewType()) {
            case GlobalVariable.ITEM_TEXT:
                break;
            case GlobalVariable.ITEM_SMALLPIC:
                Picasso.with(mContext).load(item.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(helper.getImageView(R.id.item_smallpic_rightpic));
                break;
            case GlobalVariable.ITEM_BIGPIC:
                helper.setText(R.id.item_bigpic_number, item.getPicList().size() + "张");
                Picasso.with(mContext).load(item.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(helper.getImageView(R.id.item_bigpic_toppic));
                break;
            case GlobalVariable.ITEM_EXCLUSIVE:
                Picasso.with(mContext).load(item.getPicOne()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(helper.getImageView(R.id.item_exclusive_im1));
                Picasso.with(mContext).load(item.getPicTwo()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(helper.getImageView(R.id.item_exclusive_im2));
                Picasso.with(mContext).load(item.getPicThr()).placeholder(R.mipmap.shouyetu).error(R.mipmap.shouyetu).into(helper.getImageView(R.id.item_exclusive_im3));
                break;
            case GlobalVariable.ITEM_VIDEO:
                // set imgs data
                break;
        }
    }
}
