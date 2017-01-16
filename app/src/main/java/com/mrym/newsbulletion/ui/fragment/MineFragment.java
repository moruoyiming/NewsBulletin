package com.mrym.newsbulletion.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.Weather;
import com.mrym.newsbulletion.domain.modle.UserBean;
import com.mrym.newsbulletion.listener.WifiStateReceiver;
import com.mrym.newsbulletion.mvp.MvpFragment;
import com.mrym.newsbulletion.mvp.fragment.mine.MinePresenter;
import com.mrym.newsbulletion.mvp.fragment.mine.MineView;
import com.mrym.newsbulletion.ui.activity.AboutActivity;
import com.mrym.newsbulletion.ui.activity.ChannelActivity;
import com.mrym.newsbulletion.ui.activity.PushMsgActivity;
import com.mrym.newsbulletion.ui.activity.SettingActivity;
import com.mrym.newsbulletion.ui.activity.SkinChangeActivity;
import com.mrym.newsbulletion.ui.activity.LoginActivity;
import com.mrym.newsbulletion.ui.activity.UserDetailsActivity;
import com.mrym.newsbulletion.utils.PicassoUtils;
import com.mrym.newsbulletion.utils.common.ToastUtils;
import com.mrym.newsbulletion.widget.DialogView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class MineFragment extends MvpFragment<MinePresenter> implements MineView {

    @Bind(R.id.main_iv_placeholder)
    ImageView mainIvPlaceholder;
    @Bind(R.id.fragment_mine_setting)
    ImageView wifistate;
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
    @Bind(R.id.profile_name)
    TextView profileName;
    private Dialog loadingDialog;
    private WifiStateReceiver wifiReceiver;
    @Bind(R.id.weather_icon)
    ImageView weathericon;
    @Bind(R.id.weather)
    TextView mweather;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.district)
    TextView district;
    @Bind(R.id.week)
    TextView week;
    @Bind(R.id.temperature)
    TextView temperature;


    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.initUserData();
        mvpPresenter.initMap();
        //WIFI状态接收器
        wifiReceiver = new WifiStateReceiver(getActivity(), wifistate);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        getActivity().registerReceiver(wifiReceiver, filter);
    }

    @Override
    public void showLoading(String msg) {
        loadingDialog = DialogView.createLoadingDialog(NewsApplication.getContext(), msg, false);
        loadingDialog.show();
    }

    @Override
    public void hideLoading(String msg, int code) {
        loadingDialog.dismiss();
    }

    @Override
    public void initUserData(UserBean userBean) {
        Log.i("initUserData", userBean.toString());
        profileName.setText("游客");
//        PicassoUtils.display(NewsApplication.getContext(), profileImage, , R.mipmap.touxiang, R.mipmap.touxiang);
        Glide.with(getActivity()).load(R.mipmap.touxiang).crossFade().placeholder(R.mipmap.touxiang).into(profileImage);
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
    public void showWeather(Weather weather,String addres) {
        city.setText( weather.getCity());
        mweather.setText(weather.getWeather());
        week.setText(weather.getWeek());
        district.setText(addres);
        temperature.setText(weather.getTemperature());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        RefWatcher refWatcher = NewsApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
        getActivity().unregisterReceiver(wifiReceiver);
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.main_iv_placeholder, R.id.profile_image, R.id.fragment_mine_collect_r1, R.id.fragment_mine_commont_r1, R.id.fragment_mine_setting_r1, R.id.mine_rl_message, R.id.mine_rl_offline, R.id.mine_rl_skin, R.id.mine_rl_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_iv_placeholder:
//                ToastUtils.show("背景");
                break;
            case R.id.profile_image:
//                ToastUtils.show("头像");
                break;
            case R.id.fragment_mine_collect_r1:
                ToastUtils.show("收藏功能正在玩命开发中！");
                break;
            case R.id.fragment_mine_commont_r1:
                ToastUtils.show("评论功能正在玩命开发中！");
                break;
            case R.id.fragment_mine_setting_r1:
                Intent intent5 = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent5);
                break;
            case R.id.mine_rl_message:
                Intent intent4 = new Intent(getActivity(), PushMsgActivity.class);
                getActivity().startActivity(intent4);
                break;
            case R.id.mine_rl_offline:
                Intent intent3 = new Intent(getActivity(), ChannelActivity.class);
                getActivity().startActivityForResult(intent3, 2);
                break;
            case R.id.mine_rl_skin:
                Intent intent2 = new Intent(getActivity(), SkinChangeActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.mine_rl_about:
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
