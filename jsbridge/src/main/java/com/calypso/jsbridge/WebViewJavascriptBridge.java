package com.calypso.jsbridge;

/**
 * Created by zhikang on 2017/10/26.
 */
public interface WebViewJavascriptBridge {

    public void send(String data);

    public void send(String data, CallBackFunction responseCallback);


}
