package com.mrym.newsbulletion.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.mrym.newsbulletion.R;

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

    private ImageView mRightpic;

    private ImageView mToppic;
    private TextView mNumber;

    private ImageView mBottom1;
    private ImageView mBottom2;
    private ImageView mBottom3;

    private SimpleExoPlayerView simpleExoPlayerView;

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
        mBottom1 = (ImageView) itemView.findViewById(R.id.item_exclusive_im1);
        mBottom2 = (ImageView) itemView.findViewById(R.id.item_exclusive_im2);
        mBottom3 = (ImageView) itemView.findViewById(R.id.item_exclusive_im3);
        simpleExoPlayerView = (SimpleExoPlayerView) itemView.findViewById(R.id.player_view);

    }

    public SimpleExoPlayerView getSimpleExoPlayerView() {
        return simpleExoPlayerView;
    }

    public TextView getmTiltle() {
        return mTiltle;
    }

    public TextView getmAutherName() {
        return mAutherName;
    }

    public TextView getmCommontNumber() {
        return mCommontNumber;
    }

    public TextView getmPublicdate() {
        return mPublicdate;
    }

    public ImageView getmAutherHead() {
        return mAutherHead;
    }

    public ImageView getmRightpic() {
        return mRightpic;
    }

    public ImageView getmToppic() {
        return mToppic;
    }

    public TextView getmNumber() {
        return mNumber;
    }

    public ImageView getmBottom1() {
        return mBottom1;
    }

    public ImageView getmBottom2() {
        return mBottom2;
    }

    public ImageView getmBottom3() {
        return mBottom3;
    }
}
