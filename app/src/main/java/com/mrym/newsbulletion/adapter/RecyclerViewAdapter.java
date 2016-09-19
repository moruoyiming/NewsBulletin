package com.mrym.newsbulletion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.utils.common.MsgDateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    List<NewsEntity> datas;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<NewsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == GlobalVariable.ITEM_TEXT) {
            return new Item_Text_View(mLayoutInflater.inflate(R.layout.item_text_view, parent, false));
        } else if (viewType == GlobalVariable.ITEM_SMALLPIC) {
            return new Item_Smallpic_View(mLayoutInflater.inflate(R.layout.item_smallpic_view, parent, false));
        } else if (viewType == GlobalVariable.ITEM_BIGPIC) {
            return new Item_Bigpic_View(mLayoutInflater.inflate(R.layout.item_bigpic_view, parent, false));
        } else if (viewType == GlobalVariable.ITEM_EXCLUSIVE) {
            return new Item_Exclusive_View(mLayoutInflater.inflate(R.layout.item_exclusive_view, parent, false));
        } else if (viewType == GlobalVariable.ITEM_VIDEO) {
            return new Item_Video_View(mLayoutInflater.inflate(R.layout.item_video_view, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("onBindViewHolder", datas.size() + "  " + datas.get(position).toString());

        if (holder instanceof Item_Text_View) {
            ((Item_Text_View) holder).mTiltle.setText(datas.get(position).getTitle());
            ((Item_Text_View) holder).mAutherName.setText(datas.get(position).getAuthor());
            ((Item_Text_View) holder).mCommontNumber.setText(datas.get(position).getCommentNum() + "评论");
            ((Item_Text_View) holder).mPublicdate.setText(MsgDateUtils.getTimestampString(new Date(datas.get(position).getPublishTime())));
            Picasso.with(mContext).load(datas.get(position).getHeadimg() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Text_View) holder).mAutherHead);
        } else if (holder instanceof Item_Smallpic_View) {
            ((Item_Smallpic_View) holder).mTiltle.setText(datas.get(position).getTitle());
            ((Item_Smallpic_View) holder).mAutherName.setText(datas.get(position).getAuthor());
            ((Item_Smallpic_View) holder).mCommontNumber.setText(datas.get(position).getCommentNum() + "评论");
            ((Item_Smallpic_View) holder).mPublicdate.setText(MsgDateUtils.getTimestampString(new Date(datas.get(position).getPublishTime())));
            Picasso.with(mContext).load(datas.get(position).getHeadimg() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Smallpic_View) holder).mAutherHead);
            Picasso.with(mContext).load(datas.get(position).getPicOne() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Smallpic_View) holder).mRightpic);
        } else if (holder instanceof Item_Bigpic_View) {
            ((Item_Bigpic_View) holder).mTiltle.setText(datas.get(position).getTitle());
            ((Item_Bigpic_View) holder).mAutherName.setText(datas.get(position).getAuthor());
            ((Item_Bigpic_View) holder).mCommontNumber.setText(datas.get(position).getCommentNum() + "评论");
            ((Item_Bigpic_View) holder).mPublicdate.setText(MsgDateUtils.getTimestampString(new Date(datas.get(position).getPublishTime())));
            Picasso.with(mContext).load(datas.get(position).getHeadimg() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Bigpic_View) holder).mAutherHead);
            Picasso.with(mContext).load(datas.get(position).getPicOne() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Bigpic_View) holder).mToppic);
        } else if (holder instanceof Item_Exclusive_View) {
            ((Item_Exclusive_View) holder).mTiltle.setText(datas.get(position).getTitle());
            ((Item_Exclusive_View) holder).mAutherName.setText(datas.get(position).getAuthor());
            ((Item_Exclusive_View) holder).mCommontNumber.setText(datas.get(position).getCommentNum() + "评论");
            ((Item_Exclusive_View) holder).mPublicdate.setText(MsgDateUtils.getTimestampString(new Date(datas.get(position).getPublishTime())));
            Picasso.with(mContext).load(datas.get(position).getHeadimg() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Exclusive_View) holder).mAutherHead);
            Picasso.with(mContext).load(datas.get(position).getPicOne() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Exclusive_View) holder).mBottom1);
            Picasso.with(mContext).load(datas.get(position).getPicTwo() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Exclusive_View) holder).mBottom2);
            Picasso.with(mContext).load(datas.get(position).getPicThr() + "").placeholder(R.mipmap.touxiang).error(R.mipmap.touxiang).into(((Item_Exclusive_View) holder).mBottom3);
        } else if (holder instanceof Item_Video_View) {
        }
    }


    @Override
    public int getItemViewType(int position) {
        int item = 0;
        if (datas == null) {
        } else {
            item = datas.get(position).getNewType();
        }
        return item;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    //item1 的ViewHolder
    public static class Item_Text_View extends RecyclerView.ViewHolder {
        TextView mTiltle;
        TextView mAutherName;
        TextView mCommontNumber;
        TextView mPublicdate;
        ImageView mAutherHead;
        public Item_Text_View(View itemView) {
            super(itemView);
            mTiltle = (TextView) itemView.findViewById(R.id.item_basic_title);
            mAutherName = (TextView) itemView.findViewById(R.id.item_basic_authername);
            mCommontNumber = (TextView) itemView.findViewById(R.id.item_basic_commontnubmer);
            mPublicdate = (TextView) itemView.findViewById(R.id.item_basic_publicdate);
            mAutherHead = (ImageView) itemView.findViewById(R.id.item_basic_autherhead);
        }
    }

    //item2 的ViewHolder
    public static class Item_Smallpic_View extends RecyclerView.ViewHolder {
        ImageView mRightpic;
        TextView mTiltle;
        TextView mAutherName;
        TextView mCommontNumber;
        TextView mPublicdate;
        ImageView mAutherHead;
        public Item_Smallpic_View(View itemView) {
            super(itemView);
            mRightpic = (ImageView) itemView.findViewById(R.id.item_smallpic_rightpic);
            mTiltle = (TextView) itemView.findViewById(R.id.item_basic_title);
            mAutherName = (TextView) itemView.findViewById(R.id.item_basic_authername);
            mCommontNumber = (TextView) itemView.findViewById(R.id.item_basic_commontnubmer);
            mPublicdate = (TextView) itemView.findViewById(R.id.item_basic_publicdate);
            mAutherHead = (ImageView) itemView.findViewById(R.id.item_basic_autherhead);
        }
    }

    //item3 的ViewHolder
    public static class Item_Bigpic_View extends RecyclerView.ViewHolder {

        ImageView mToppic;
        TextView mNumber;
        TextView mTiltle;
        TextView mAutherName;
        TextView mCommontNumber;
        TextView mPublicdate;
        ImageView mAutherHead;
        public Item_Bigpic_View(View itemView) {
            super(itemView);
            mToppic = (ImageView) itemView.findViewById(R.id.item_bigpic_toppic);
            mNumber = (TextView) itemView.findViewById(R.id.item_bigpic_number);
            mTiltle = (TextView) itemView.findViewById(R.id.item_basic_title);
            mAutherName = (TextView) itemView.findViewById(R.id.item_basic_authername);
            mCommontNumber = (TextView) itemView.findViewById(R.id.item_basic_commontnubmer);
            mPublicdate = (TextView) itemView.findViewById(R.id.item_basic_publicdate);
            mAutherHead = (ImageView) itemView.findViewById(R.id.item_basic_autherhead);
        }
    }

    //item2 的ViewHolder
    public static class Item_Exclusive_View extends RecyclerView.ViewHolder {

        ImageView mBottom1;
        ImageView mBottom2;
        ImageView mBottom3;
        TextView mTiltle;
        TextView mAutherName;
        TextView mCommontNumber;
        TextView mPublicdate;
        ImageView mAutherHead;
        public Item_Exclusive_View(View itemView) {
            super(itemView);
            mBottom1 = (ImageView) itemView.findViewById(R.id.item_exclusive_im1);
            mBottom2 = (ImageView) itemView.findViewById(R.id.item_exclusive_im2);
            mBottom3 = (ImageView) itemView.findViewById(R.id.item_exclusive_im3);
            mTiltle = (TextView) itemView.findViewById(R.id.item_basic_title);
            mAutherName = (TextView) itemView.findViewById(R.id.item_basic_authername);
            mCommontNumber = (TextView) itemView.findViewById(R.id.item_basic_commontnubmer);
            mPublicdate = (TextView) itemView.findViewById(R.id.item_basic_publicdate);
            mAutherHead = (ImageView) itemView.findViewById(R.id.item_basic_autherhead);
        }
    }

    //item2 的ViewHolder
    public static class Item_Video_View extends RecyclerView.ViewHolder {

        public Item_Video_View(View itemView) {
            super(itemView);
        }
    }
}