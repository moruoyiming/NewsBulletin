package com.mrym.newsbulletion.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.db.utils.HomeCateGoryUtils;
import com.mrym.newsbulletion.utils.file.FileOperate;

import org.xutils.x;

/**
 * Created by Jian on 2016/8/31.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.mrym.newsbulletion.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        //TODO 启动加载超时操作

        if (FileOperate.copyStartPage()) {
            FileOperate.unZipStartPage();
        }
    }
}