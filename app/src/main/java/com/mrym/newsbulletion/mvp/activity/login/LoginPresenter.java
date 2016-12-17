package com.mrym.newsbulletion.mvp.activity.login;

import android.content.Context;

import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.domain.modle.DefaultInterfaceBean;
import com.mrym.newsbulletion.mvp.BasePresenter;
import com.mrym.newsbulletion.rx.ServiceFactory;
import com.mrym.newsbulletion.rx.retrofit.TransformUtils;
import com.mrym.newsbulletion.rx.service.NewsService;
import com.mrym.newsbulletion.utils.TimeCount;
import com.mrym.newsbulletion.utils.common.Validator;
import com.mrym.newsbulletion.utils.common.ToastUtils;


import rx.Subscriber;

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
        ServiceFactory.getInstance().createService(NewsService.class)
                .sendVerification(telNumber)
                .compose(TransformUtils.<DefaultInterfaceBean>defaultSchedulers())
                .subscribe(
                        new Subscriber<DefaultInterfaceBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(DefaultInterfaceBean model) {
                                mvpView.showToast("获取验证码，请等待！");
                                mvpView.hideLoading();
                            }
                        });
    }

    /**
     * 账号登陆
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        mvpView.showLoading();
        ServiceFactory.getInstance().createService(NewsService.class)
                .login(password, username)
                .compose(TransformUtils.<LoginModel>defaultSchedulers())
                .subscribe(
                        new Subscriber<LoginModel>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(LoginModel model) {
                                if (GlobalVariable.SUCCESS_CODE == model.getCode()) {
                                    mvpView.loginSuccess(model.getData());
                                } else {
                                    ToastUtils.show(model.getMsg());
                                }
                            }
                        });
    }

    /**
     * 手机号登陆
     *
     * @param telNumber
     * @param verification
     */
    public void phoneLogin(String telNumber, String verification) {
        mvpView.showLoading();

        ServiceFactory.getInstance().createService(NewsService.class)
                .phonelogin(telNumber, verification)
                .compose(TransformUtils.<LoginModel>defaultSchedulers())
                .subscribe(
                        new Subscriber<LoginModel>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(LoginModel model) {
                                if (GlobalVariable.SUCCESS_CODE == model.getCode()) {
                                    mvpView.loginSuccess(model.getData());
                                } else {
                                    ToastUtils.show(model.getMsg());
                                }
                            }
                        });
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
