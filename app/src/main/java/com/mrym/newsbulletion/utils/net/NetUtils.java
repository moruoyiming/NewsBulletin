package com.mrym.newsbulletion.utils.net;

import com.mrym.newsbulletion.domain.constans.GlobalVariable;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 网络请求
 * author : nan
 * time : 2016/3/21.
 */
public class NetUtils {
    /**
     * get请求
     *
     * @return
     */
    public static void get(RequestParams params, Callback.CommonCallback<String> callback) {
        HttpManager http = x.http();
        params.setConnectTimeout(GlobalVariable.CONNECT_TIMEOUT);
        http.get(params, callback);
    }

//    /**
//     * 带参get
//     * @param url
//     * @param res
//     * @param callback
//     */
//    public static void get(final String url, Map res, Callback.CommonCallback<String> callback) {
//        RequestParams params = new RequestParams(url);
//        Iterator it = res.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            params.addBodyParameter((String)key,(String)value);
//        }
//        HttpManager http = x.http();
//        params.setCacheMaxAge(0);//设置不缓存
//        params.setConnectTimeout(5000);
//        http.get(params, callback);
//    }
}
