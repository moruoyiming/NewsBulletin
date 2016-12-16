package com.mrym.newsbulletion.rx.retrofit;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class HttpResult<T> {

    public boolean error;
    //@SerializedName(value = "results", alternate = {"result"})
    public T results;
}
