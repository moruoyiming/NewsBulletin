package com.mrym.newsbulletion.rx.retrofit.func;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class StringFunc implements Func1<ResponseBody, String> {
    @Override
    public String call(ResponseBody responseBody) {
        String result = null;
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
