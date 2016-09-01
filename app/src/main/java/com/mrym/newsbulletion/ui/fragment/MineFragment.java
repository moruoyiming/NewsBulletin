package com.mrym.newsbulletion.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.UserBean;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.mine.MinePresenter;
import com.mrym.newsbulletion.mvp.fragment.mine.MineView;
import com.mrym.newsbulletion.ui.activity.LoginActivity;
import com.mrym.newsbulletion.ui.activity.UserDetailsActivity;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.widget.DialogView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shawn on 2016/8/18.
 */
public class MineFragment extends MvpFragment<MinePresenter> implements MineView {

    @Bind(R.id.main_iv_placeholder)
    ImageView mainIvPlaceholder;
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.fragment_mine_collect_r1)
    RelativeLayout fragmentMineCollectR1;
    @Bind(R.id.fragment_mine_commont_r1)
    RelativeLayout fragmentMineCommontR1;
    @Bind(R.id.fragment_mine_setting_r1)
    RelativeLayout fragmentMineSettingR1;
    @Bind(R.id.mine_rl_message)
    RelativeLayout mineRlMessage;
    @Bind(R.id.mine_rl_offline)
    RelativeLayout mineRlOffline;
    @Bind(R.id.mine_rl_skin)
    RelativeLayout mineRlSkin;
    @Bind(R.id.mine_rl_about)
    RelativeLayout mineRlAbout;
    private Dialog loadingDialog;

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mvpPresenter.initUserData();
    }

    @Override
    public void showLoading(String msg) {
        loadingDialog = DialogView.createLoadingDialog(mActivity, msg, false);
        loadingDialog.show();
    }

    @Override
    public void hideLoading(String msg, int code) {
        loadingDialog.dismiss();
    }

    @Override
    public void initUserData(UserBean userBean) {
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void startUserDetilsActivity() {
        Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.main_iv_placeholder, R.id.profile_image, R.id.fragment_mine_collect_r1, R.id.fragment_mine_commont_r1, R.id.fragment_mine_setting_r1, R.id.mine_rl_message, R.id.mine_rl_offline, R.id.mine_rl_skin, R.id.mine_rl_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_iv_placeholder:
                ToastUtils.show("背景");
                break;
            case R.id.profile_image:
                ToastUtils.show("头像");
                break;
            case R.id.fragment_mine_collect_r1:
                ToastUtils.show("收藏");
                break;
            case R.id.fragment_mine_commont_r1:
                ToastUtils.show("评论");
                break;
            case R.id.fragment_mine_setting_r1:
                ToastUtils.show("设置");
                break;
            case R.id.mine_rl_message:
                ToastUtils.show("消息");
                break;
            case R.id.mine_rl_offline:
                ToastUtils.show("离线");
                break;
            case R.id.mine_rl_skin:
                ToastUtils.show("换肤");
                break;
            case R.id.mine_rl_about:
                ToastUtils.show("关于");
                break;
        }
    }
}
