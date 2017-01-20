package com.mrym.newsbulletion.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.mrym.newsbulletion.utils.Logger;


/**
 * Created by Jian on 2016/10/09.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class BatteryReceiver extends BroadcastReceiver {
    Context context;

    public BatteryReceiver(Context context) {
// TODO Auto-generated constructor stub
        this.context = context;

    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        int voltage = arg1.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        Logger.d("电压：" + voltage / 1000 + "." + voltage % 1000 + "V");
        int temperature = arg1.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        Logger.d("温度：" + temperature / 10 + "." + temperature % 10 + "℃");
//        if (temperature >= 300) {
//            mTvTemperature.setTextColor(Color.RED);
//        } else {
//            mTvTemperature.setTextColor(Color.BLUE);
//        }
        int level = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
        int levelPercent = (int) (((float) level / scale) * 100);
        Logger.d("电量：" + levelPercent + "%");
//        if (level <= 10) {
//            mTvLevel.setTextColor(Color.RED);
//        } else {
//            mTvLevel.setTextColor(Color.BLUE);
//        }
        int status = arg1.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
        String strStatus = "未知状态";
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                strStatus = "充电中……";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                strStatus = "放电中……";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                strStatus = "未充电";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                strStatus = "充电完成";
                break;
        }
        Logger.d("状态：" + strStatus);
        int health = arg1.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
        String strHealth = "未知 :(";
        switch (status) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                strHealth = "好 :)";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                strHealth = "过热！";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD: // 未充电时就会显示此状态，这是什么鬼？
                strHealth = "良好";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                strHealth = "电压过高！";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                strHealth = "未知 :(";
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                strHealth = "过冷！";
                break;
        }
        Logger.d("健康状况：" + strHealth);

        String technology = arg1.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
        Logger.d("电池技术：" + technology);
    }

}