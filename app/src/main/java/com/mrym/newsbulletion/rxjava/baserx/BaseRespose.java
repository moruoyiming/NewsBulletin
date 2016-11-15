package com.mrym.newsbulletion.rxjava.baserx;

import java.io.Serializable;

/**
 * 封装服务器返回数据
 * Created by Jian on 2016/9/19.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class BaseRespose<T> implements Serializable {
    public String code;
    public String msg;

    public T data;

    public boolean success() {
        return "1".equals(code);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
