package com.mrym.newsbulletion.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrym.newsbulletion.domain.modle.NewsEntity;

import java.util.List;


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
    public NewsAdapter(List<NewsEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {

    }
}
