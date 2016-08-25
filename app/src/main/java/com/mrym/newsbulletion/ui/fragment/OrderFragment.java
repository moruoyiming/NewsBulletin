package com.mrym.newsbulletion.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangkong.fastpay.R;
import com.zhangkong.fastpay.mvp.MvpFragment;
import com.zhangkong.fastpay.mvp.fragment.order.OrderPresenter;
import com.zhangkong.fastpay.mvp.fragment.order.OrderView;
import com.zhangkong.fastpay.ui.activity.SearchActivity;
import com.zhangkong.fastpay.ui.view.MultiRadioGroup;
import com.zhangkong.fastpay.ui.view.OrderTitlePopView;
import com.zhangkong.fastpay.util.common.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shawn on 2016/8/18.
 */
public class OrderFragment extends MvpFragment<OrderPresenter> implements OrderView {
    private static final String TAG = "OrderFragment";
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @Bind(R.id.order_content)
    FrameLayout orderContent;

    private OrderTitlePopView pop;
    private long dismissTime;

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_order, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View popView = LayoutInflater.from(mActivity).inflate(R.layout.pop_order, null);
        pop = new OrderTitlePopView(popView);
        ((MultiRadioGroup) popView.findViewById(R.id.rg_pop)).setOnCheckedChangeListener(mvpPresenter.new OnPopCheckedChangeListener());
        pop.setOnDismissListener(mvpPresenter.new OnPopDismissListener());
    }

    @OnClick({R.id.iv_search_btn, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_btn:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
            case R.id.tv_title:
                final long l = System.currentTimeMillis() - dismissTime;
                Log.e(TAG, "time : " + l);
                if (l >= 200) {
                    Rect frame = new Rect();
                    mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                    pop.showAtLocation(orderContent, Gravity.TOP, 0, rlTitleRoot.getHeight() + frame.top);
                    tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrows_up, 0);
                    AppUtils.setBackgroundAlpha(mActivity, 0.7f);
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPopDismiss() {
        AppUtils.setBackgroundAlpha(mActivity, 1);
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.arrows_down, 0);
        dismissTime = System.currentTimeMillis();
    }

    @Override
    public void onPopCheckedChanged(MultiRadioGroup group, int checkedId) {
        pop.dismiss();
        pop.setSuperChecked(checkedId);
        switch (checkedId) {
            case R.id.rb_all:
                tvTitle.setText(R.string.all);
                break;
            case R.id.rb_mobile_gathering:
                tvTitle.setText(R.string.mobile_gathering);
                break;
            case R.id.rb_cash_gathering:
                tvTitle.setText(R.string.cash_gathering);
                break;
            case R.id.rb_swiping_card_gathering:
                tvTitle.setText(R.string.swiping_card_gathering);
                break;
            case R.id.rb_not_gathering:
                tvTitle.setText(R.string.not_gathering);
                break;
            case R.id.rb_already_refund:
                tvTitle.setText(R.string.already_refund);
                break;
        }
    }
}
