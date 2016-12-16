package com.mrym.newsbulletion.rx.retrofit.subscriber;

import com.mrym.newsbulletion.rx.retrofit.HttpResult;

import rx.Subscriber;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            e.printStackTrace();
            //在这里做全局的错误处理
//            if (e instanceof HttpException) {
//                // ToastUtils.getInstance().showToast(e.getMessage());
//            }
            if (e.getMessage() == null) {//e.getMessage()可能为null
                _onError(new Throwable(e.toString()));
            } else {
                _onError(e);
            }
        } else {
            _onError(new Exception("null message"));
        }
    }

    @Override
    public void onNext(HttpResult<T> t) {
        if (!t.error) {
            onSuccess(t.results);
        } else {
            _onError(new Throwable("error=true"));
        }
    }

    public abstract void onSuccess(T t);

    public abstract void _onError(Throwable e);
}
