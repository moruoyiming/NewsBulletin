package com.mrym.newsbulletion.utils.common;

import java.text.DecimalFormat;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 * 金钱的工具类
 */
public class MoneyUtil {
    public static String MoneyFomatWithTwoPoint(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}
