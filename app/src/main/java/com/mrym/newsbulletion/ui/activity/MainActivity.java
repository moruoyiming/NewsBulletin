package com.mrym.newsbulletion.ui.activity;

import android.os.Bundle;

import com.commander.newsonline.R;
import com.commander.newsonline.bean.MainModel;
import com.commander.newsonline.mvp.presenter.MainPresenterImpl;
import com.commander.newsonline.mvp.view.MainView;

public class MainActivity extends MvpActivity<MainPresenterImpl> implements MainView {

//    @BindView(R.id.text)
//    TextView text;
//    @BindView(R.id.mProgressBar)
//    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mvpPresenter.loadData("101010100");
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public void getDataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
//        text.setText(showData);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络不给力");
    }

    @Override
    public void showLoading() {
//        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        mProgressBar.setVisibility(View.GONE);
    }
}
