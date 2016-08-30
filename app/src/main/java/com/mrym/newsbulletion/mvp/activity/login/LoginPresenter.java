package com.mrym.newsbulletion.mvp.activity.login;

import android.content.Context;

import com.mrym.newsbulletion.authenticator.account.AccountTool;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rxjava.ApiCallback;
import com.mrym.newsbulletion.rxjava.SubscriberCallBack;
import com.mrym.newsbulletion.utils.TimeCount;
import com.mrym.newsbulletion.utils.common.Validator;
import com.mrym.newsbulletion.utils.common.ToastUtils;

/**
 * Created by Jian on 2016/8/30.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView loginView) {
        attachView(loginView);
    }

    /**
     * 获取验证码
     *
     * @param telNumber
     */
    public void sendVerification(String telNumber) {
        if (!Validator.isMobile(telNumber)) {
            mvpView.showToast("请输入正确的手机号");
            return;
        }
        mvpView.showLoading();
        TimeCount time = new TimeCount(mvpView, 60000, 1000);
        time.start();
        addSubscription(apiStores.sendVerification(telNumber),
                new SubscriberCallBack<>(new ApiCallback<DefaultInterfaceBean>() {
                    @Override
                    public void onSuccess(DefaultInterfaceBean model) {
                        mvpView.showToast("获取验证码，请等待！");
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

//    /**
//     * 账号登陆
//     *
//     * @param username
//     * @param password
//     */
//    public void login(String username, String password) {
//        mvpView.showLoading();
//        addSubscription(apiStores.login(password, username),
//                new SubscriberCallBack<>(new ApiCallback<LoginModel>() {
//                    @Override
//                    public void onSuccess(LoginModel model) {
//                        if (GlobalVariable.SUCCESS_CODE == model.getCode()) {
//                            mvpView.loginSuccess(model.getData());
//                        } else {
//                            ToastUtils.show(model.getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        mvpView.loginFailure(code, msg);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mvpView.hideLoading();
//                    }
//                }));
//    }

    /**
     * 手机号登陆
     *
     * @param telNumber
     * @param verification
     */
    public void phoneLogin(String telNumber, String verification) {
        mvpView.showLoading();
        addSubscription(apiStores.phonelogin(telNumber, verification),
                new SubscriberCallBack<>(new ApiCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
//                        if (GlobalVariable.SUCCESS_CODE == model.getCode()) {
//                            mvpView.loginSuccess(model.getData());
//                        } else {
//                            ToastUtils.show(model.getMsg());
//                        }
                        mvpView.showToast("登陆成功 保存数据");
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.loginFailure(code, msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

    /**
     * 阅读协议
     */
    public void readAgreement(Context context) {
        mvpView.showToast("阅读条约");
//        Intent intent = new Intent(context, CordovaPageActivity.class);
//        intent.putExtra(CordovaPageActivity.INTENT_URL_KEY, UrlFactory.PROVISION_PRIVACY);
//        mActivity.startActivity(intent);
    }
}
