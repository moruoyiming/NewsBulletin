package com.mrym.newsbulletion.rx.retrofit.func;


import com.mrym.newsbulletion.rx.retrofit.HttpResult;
import com.mrym.newsbulletion.utils.json.JsonConvert;

import rx.functions.Func1;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class ResultFunc<T> implements Func1<String, HttpResult<T>> {
    @Override
    public HttpResult<T> call(String result) {
        JsonConvert<HttpResult<T>> convert = new JsonConvert<HttpResult<T>>() {
        };
        return convert.parseData(result);
    }
}
