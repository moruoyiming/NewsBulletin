package com.mrym.newsbulletion.utils.common;

import android.content.res.Resources;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class UIUtils {
    //dp dip ----->px
    //布局文件中的dp转换成px的方法

    //dp转换成px,关键看手机的像素密度

    //0,1dp = 0.75px  320*240
    //1,1dp = 1px  480*320
    //2,1dp = 1.5px 800*480  3.7英寸--4.1英寸
    //3,1dp = 2px   1280(竖直方向像素)*720(水平方向像素) 5英寸(手机斜边)
    //4,1dp = 3px   三星s4  1920*1080  5英寸
    public static int dip2px(int dip){
        //获取不同手机对应的不同的转换因子
        float d = getResources().getDisplayMetrics().density;
        //四舍五入( 80.5+0.5)   80.1+0.5
        return (int)(d*dip+0.5);
    }

    public static int px2dip(int px){
        float d = getResources().getDisplayMetrics().density;
        return (int)(px/d+0.5);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
//        return (int) (spValue * fontScale + 0.5f);
        return (int) (spValue * getResources().getDisplayMetrics().scaledDensity+ 0.5f);
    }

    private static Resources getResources(){
        return AppUtils.getContext().getResources();
    }
}
