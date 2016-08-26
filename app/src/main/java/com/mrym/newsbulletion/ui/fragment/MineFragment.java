package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.mine.MinePresenter;
import com.mrym.newsbulletion.mvp.fragment.mine.MineView;

/**
 * Created by Shawn on 2016/8/18.
 */
public class MineFragment extends MvpFragment<MinePresenter> implements MineView {
    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_mine, null);
    }
}
