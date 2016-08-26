package com.mrym.newsbulletion.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.HomeOrderBean;
import com.mrym.newsbulletion.domain.modle.OrderBean;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.home.HomePresenter;
import com.mrym.newsbulletion.mvp.fragment.home.HomeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 2016/8/18.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_home, null);
        initToolBar(root, R.string.app_name);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
        mvpPresenter.getOrderPriceSum(tool.getAccountId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void getSuccess(HomeOrderBean order) {

    }

    @Override
    public void getFailure(int code, String msg) {

    }
}
