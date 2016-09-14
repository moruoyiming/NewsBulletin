package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.db.entity.HomeCateGory;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.domain.modle.NewsEntity;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryPresenter;
import com.mrym.newsbulletion.mvp.fragment.category.GateGoryView;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class GateGoryFragment extends MvpFragment<GateGoryPresenter> implements GateGoryView{
    @Override
    protected GateGoryPresenter createPresenter() {
        return new GateGoryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_category, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        ArrayList<HomeCateGory> homeCateGories = HomeCateGoryUtils.getInstance(getContext()).getHomeCateGory();
        mvpPresenter.getGategoryData(homeCateGories.get(position).getField());
    }


    @Override
    public void loadingError(String errormsg) {

    }

    @Override
    public void loadingSuccess(List<NewsEntity> news) {
        ToastUtils.show("数据条数："+news.size());
    }
}
