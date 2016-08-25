package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangkong.fastpay.R;
import com.zhangkong.fastpay.domain.bean.OrderBean;
import com.zhangkong.fastpay.mvp.MvpFragment;
import com.zhangkong.fastpay.mvp.fragment.home.HomePresenter;
import com.zhangkong.fastpay.mvp.fragment.home.HomeView;
import com.zhangkong.fastpay.ui.activity.CaptureActivity;
import com.zhangkong.fastpay.ui.activity.CashGatheringActivity;
import com.zhangkong.fastpay.ui.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 2016/8/18.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";
    @Bind(R.id.tv_today_order)
    TextView tvTodayOrder;
    @Bind(R.id.tv_today_trade_money)
    TextView tvTodayTradeMoney;

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

    @OnClick({R.id.tv_cash_receive, R.id.tv_receipt_scan, R.id.rl_home_today_order, R.id.rl_home_today_count})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cash_receive:
                startActivity(new Intent(mActivity, CashGatheringActivity.class));
                break;
            case R.id.tv_receipt_scan:
                startActivity(new Intent(mActivity, CaptureActivity.class));
                break;
            case R.id.rl_home_today_order:
                ((MainActivity) mActivity).goToOrder();
                break;
            case R.id.rl_home_today_count:
                ((MainActivity) mActivity).goToOrder();
                break;
        }
    }

    @Override
    public void getSuccess(OrderBean order) {
        Log.e(TAG, order.toString());
//        tvTodayOrder.setText(order.getOrderSum());
//        tvTodayTradeMoney.setText(getString(R.string.rmb_num, order.getPriceSum()));
    }

    @Override
    public void getFailure(int code, String msg) {
        Log.e(TAG, "loginFailure : code : " + code + "\nmsg : " + msg);
        toastShow(msg);
    }
}
