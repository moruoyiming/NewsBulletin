//package com.mrym.newsbulletion.listener;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.NetworkInfo;
//import android.net.wifi.WifiInfo;
//import android.net.wifi.WifiManager;
//import android.widget.ImageView;
//
//import com.mrym.newsbulletion.domain.constans.GlobalVariable;
//
///**
// * Created by Jian on 2016/10/09.
// * Email: 798774875@qq.com
// * Github: https://github.com/moruoyiming
// */
//public class ChanelChangeReceiver extends BroadcastReceiver {
//
//    Context context;
//
//    public ChanelChangeReceiver(Context context) {
//// TODO Auto-generated constructor stub
//        this.context = context;
//
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        System.out.println(intent.getAction());
//        if (intent.getAction().equals(GlobalVariable.CHANELCHANGERECEIVER)) {
//            System.out.println("ChanelChangeReceiver 频道发生变化" );
//        }
//    }
//
//
//
//}