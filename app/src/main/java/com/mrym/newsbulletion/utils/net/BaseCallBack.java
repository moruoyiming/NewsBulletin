package com.mrym.newsbulletion.utils.net;

import android.util.Log;


import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.utils.common.ToastUtils;

import org.xutils.common.Callback;

/**
 * author : nan
 * time : 2016/3/25.
 */
public class BaseCallBack<T> implements Callback.CacheCallback<T> {

    private static final String TAG = BaseCallBack.class.getCanonicalName();

    @Override
    public void onSuccess(T result) {
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.e(TAG,"错误的消息:"+ex.getMessage());
    }

    @Override
    public void onCancelled(CancelledException cex) {
    }

    @Override
    public void onFinished() {
    }

    @Override
    public boolean onCache(T result) {
        if (onCache(result)) {
            onSuccess(result);
            return true;
        }
        return false;
    }

}
