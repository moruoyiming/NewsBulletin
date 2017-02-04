package com.mrym.newsbulletion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.NewsSummary;
import com.mrym.newsbulletion.ui.activity.NewsDetailActivity;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.mrym.newsbulletion.utils.common.DateUtils;

import java.util.List;

/**
 * Created by Jian on 2016/11/27.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */

public class PushAdapter2 extends BaseMultiItemQuickAdapter<NewsSummary, BaseViewHolder> {

    private final String TAG = "PushAdapter";
    private BaseRecyclerViewAdapter.onInternalClickListener onInternalClickListener;

    public PushAdapter2(Context context, List data) {
        super(data);
        addItemType(GlobalVariable.ITEM_ONE_PIC, R.layout.layout_item_singlepush);
        addItemType(GlobalVariable.ITEM_BIGPIC, R.layout.layout_item_pushmsg);
        addItemType(GlobalVariable.ITEM_TWO_PIC, R.layout.layout_item_multipush);
        addItemType(GlobalVariable.ITEM_OTHER, R.layout.layout_item_pushmsg);
        onInternalClickListener = new BaseRecyclerViewAdapter.onInternalClickListener<NewsSummary.AdsBean>() {
            @Override
            public void OnClickListener(View parentV, View v, Integer position, NewsSummary.AdsBean values) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra(GlobalVariable.NEWS_POST_ID, values.getUrl());
                intent.putExtra(GlobalVariable.NEWS_IMG_RES, values.getImgsrc());
                mContext.startActivity(intent);
//                NewsDetailActivity.startAction(mContext, values.getUrl(), values.getImgsrc());
            }

            @Override
            public void OnLongClickListener(View parentV, View v, Integer position, NewsSummary.AdsBean values) {

            }
        };
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsSummary newsSummary) {
        if (newsSummary == null) {
            return;
        }
        baseViewHolder.addOnClickListener(R.id.item_singlepush_layout).addOnClickListener(R.id.item_multipush_layout);
        switch (baseViewHolder.getItemViewType()) {
            case GlobalVariable.ITEM_ONE_PIC:
                baseViewHolder.setText(R.id.item_singlepush_pushtime, DateUtils.formatDate(newsSummary.getLmodify()));
                baseViewHolder.setText(R.id.item_singlepush_name, newsSummary.getSource());
                baseViewHolder.setText(R.id.item_singlepush_title, newsSummary.getTitle());
                baseViewHolder.setText(R.id.item_singlepush_publishtime, DateUtils.formatDate(newsSummary.getLmodify()));
                PicassoUtils.display(mContext, (ImageView) baseViewHolder.getView(R.id.item_singlepush_photo), newsSummary.getImgsrc());
                break;
            case GlobalVariable.ITEM_TWO_PIC:
                baseViewHolder.setText(R.id.item_multipush_pushtime, DateUtils.formatDate(newsSummary.getLmodify()));
                baseViewHolder.setText(R.id.item_multipush_title, newsSummary.getTitle());
                if (newsSummary.getAds() != null && newsSummary.getImgextra() != null) {
                    if (newsSummary.getAds().size() > 0) {
                        PicassoUtils.display(mContext, (ImageView) baseViewHolder.getView(R.id.item_multipush_photo), newsSummary.getAds().get(0).getImgsrc());
                    } else if (newsSummary.getImgextra().size() > 0) {
                        PicassoUtils.display(mContext, (ImageView) baseViewHolder.getView(R.id.item_multipush_photo), newsSummary.getImgextra().get(0).getImgsrc());
                    }
                }
                RecyclerView recyclerView2 = (RecyclerView) baseViewHolder.getView(R.id.layout_item_multipush_rl);
                recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
                PushMsgAdapter adapter = new PushMsgAdapter(mContext, newsSummary.getAds());
                recyclerView2.setAdapter(adapter);
                adapter.setOnInViewClickListener(R.id.layout_item_pushmsg_l1, onInternalClickListener);
                break;
            case GlobalVariable.ITEM_BIGPIC:
                baseViewHolder.setText(R.id.item_multipush_pushtime, DateUtils.formatDate(newsSummary.getLmodify()));
                baseViewHolder.setText(R.id.item_multipush_title, newsSummary.getTitle());
                baseViewHolder.setText(R.id.item_multipush_pushtime, DateUtils.formatDate(newsSummary.getLmodify()));
                PicassoUtils.display(mContext, (ImageView) baseViewHolder.getView(R.id.item_multipush_photo), newsSummary.getAds().get(0).getImgsrc());
                RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.layout_item_multipush_rl);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                PushMsgAdapter adapter2 = new PushMsgAdapter(mContext, newsSummary.getAds());
                recyclerView.setAdapter(adapter2);
                adapter2.setOnInViewClickListener(R.id.layout_item_pushmsg_l1, onInternalClickListener);
                break;
            case GlobalVariable.ITEM_OTHER:
                baseViewHolder.setText(R.id.item_singlepush_pushtime, DateUtils.formatDate(newsSummary.getLmodify()));
                baseViewHolder.setText(R.id.item_singlepush_name, newsSummary.getSource());
                baseViewHolder.setText(R.id.item_singlepush_title, newsSummary.getTitle());
                baseViewHolder.setText(R.id.item_singlepush_publishtime, DateUtils.formatDate(newsSummary.getLmodify()));
                PicassoUtils.display(mContext, (ImageView) baseViewHolder.getView(R.id.item_singlepush_photo), newsSummary.getAds().get(0).getImgsrc());
                break;
        }
    }

}
