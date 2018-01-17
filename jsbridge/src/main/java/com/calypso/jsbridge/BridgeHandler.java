package com.calypso.jsbridge;


/**
 * Created by zhikang on 2017/10/26.
 * 回掉接口
 */
public interface BridgeHandler {

    void handler(String data, CallBackFunction function);

}
