package com.mrym.newsbulletion.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ImageView;

/**
 * Created by Jian on 2016/10/09.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class WifiStateReceiver extends BroadcastReceiver {

    ImageView wifiStateImage;
    Context context;

    public WifiStateReceiver(Context context, ImageView imageView) {
// TODO Auto-generated constructor stub
        this.wifiStateImage = imageView;
        this.context = context;
        int strength = getStrength(context);
        wifiStateImage.setImageLevel(strength);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(intent.getAction());
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
            int strength = getStrength(context);
            System.out.println("当前信号" + strength);
            wifiStateImage.setImageLevel(strength);
        } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            System.out.println("网络状态改变");
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {//如果断开连接
                wifiStateImage.setImageLevel(0);
            }
        } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            //WIFI开关
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {//如果关闭
                wifiStateImage.setImageLevel(0);
            }
        }

    }

    public int getStrength(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info.getBSSID() != null) {
            int strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            //链接速度
            int speed = info.getLinkSpeed();
            // 链接速度单位
            String units = WifiInfo.LINK_SPEED_UNITS;
            // Wifi源名称
            String ssid = info.getSSID();
            return strength;
        }
        return 0;
    }

}