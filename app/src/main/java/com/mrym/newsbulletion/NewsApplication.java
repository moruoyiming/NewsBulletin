package com.mrym.newsbulletion;


import android.content.Context;

import com.mcxiaoke.packer.helper.PackerNg;
import com.mrym.newsbulletion.db.GreenDaoManager;
import com.mrym.newsbulletion.domain.constans.GlobalVariable;
import com.mrym.newsbulletion.utils.common.PrefUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.tauth.Tencent;


import solid.ren.skinlibrary.base.SkinBaseApplication;


public class NewsApplication extends SkinBaseApplication {

    private static Context CONTEXT;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        refWatcher = LeakCanary.install(this);
        GreenDaoManager.getInstance();
        initDb();

    }


    /**
     * 初始化新闻分类
     */
    public void initDb() {
        if (PrefUtils.getBoolean(NewsApplication.getContext(), GlobalVariable.FIRST_LOGIN_STATE, true)) {
            PrefUtils.putBoolean(NewsApplication.getContext(), GlobalVariable.FIRST_LOGIN_STATE, false);
            GreenDaoManager.getInstance().initDB();
            GreenDaoManager.getInstance().initDB2();
        } else {

        }
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        NewsApplication application = (NewsApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    public static Context getContext() {
        return CONTEXT;
    }

    public void dabao() {
        // 如果没有使用PackerNg打包添加渠道，默认返回的是""
        // com.mcxiaoke.packer.helper.PackerNg
        final String market = PackerNg.getMarket(CONTEXT);
        // 或者使用 PackerNg.getMarket(Context,defaultValue)
        // 之后就可以使用了，比如友盟可以这样设置
//        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(CONTEXT, umeng_appkey, market));
    }

}
