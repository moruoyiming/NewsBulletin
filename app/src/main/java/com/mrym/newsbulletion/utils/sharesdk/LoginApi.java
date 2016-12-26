package com.mrym.newsbulletion.utils.sharesdk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.mrym.newsbulletion.NewsApplication;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

public class LoginApi implements Callback {
	private static final String TAG = LoginApi.class.getCanonicalName();
	private static final int MSG_AUTH_CANCEL = 1;
	private static final int MSG_AUTH_ERROR= 2;
	private static final int MSG_AUTH_COMPLETE = 3;
//	private Dialog loadingDialog;

	private String platform;
	private Context context;
	private Handler handler;

	public LoginApi() {
		handler = new Handler(Looper.getMainLooper(), this);
//		this.loadingDialog = loadingDialog;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public void login() {
		this.context = NewsApplication.getContext();
		if (platform == null) {
			return;
		}

		//初始化SDK
		ShareSDK.initSDK(context);
		Platform plat = ShareSDK.getPlatform(platform);
		if (plat == null) {
			return;
		}


		if (plat.isAuthValid()) {
			plat.removeAccount(true);
//			return;
//			plat.setPlatformActionListener(paListener);
//			plat.authorize();
//			移除授权
//			plat.removeAccount(true);
		}

		//使用SSO授权，通过客户单授权
		plat.SSOSetting(false);
		plat.setPlatformActionListener(new PlatformActionListener() {
			public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_COMPLETE;
					msg.arg2 = action;
					msg.obj = new Object[]{plat.getName(), res};
					handler.sendMessage(msg);
				}
			}

			public void onError(Platform plat, int action, Throwable t) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_ERROR;
					msg.arg2 = action;
					msg.obj = t;
					handler.sendMessage(msg);
				}
				t.printStackTrace();
			}

			public void onCancel(Platform plat, int action) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_CANCEL;
					msg.arg2 = action;
					msg.obj = plat;
					handler.sendMessage(msg);
				}
			}
		});
		plat.showUser(null);
	}

	/**处理操作结果*/
	public boolean handleMessage(Message msg) {
		switch(msg.what) {
			case MSG_AUTH_CANCEL:
				// 取消
//				loadingDialog.dismiss();
				loginListener.onCancel();
//				Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show();
				break;
			case MSG_AUTH_ERROR:
				// 失败
//				loadingDialog.dismiss();
				Throwable t = (Throwable) msg.obj;
				String text = "caught error: " + t.getMessage();
				if (TextUtils.equals("cn.sharesdk.wechat.utils.WechatClientNotExistException", msg.obj.toString())){
					Toast.makeText(context, "没有安装微信客户端，请安装后重试！", Toast.LENGTH_SHORT).show();
					break;
				}
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
				t.printStackTrace();
				break;
			case MSG_AUTH_COMPLETE:
				// 成功
				Object[] objs = (Object[]) msg.obj;
				String plat = (String) objs[0];
				@SuppressWarnings("unchecked")
				HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
				if (loginListener!= null && loginListener.onLogin(plat, res)) {
//					RegisterPageActivity.setOnLoginListener(loginListener);
//					RegisterPageActivity.setPlatform(plat);
//					Intent intent=new Intent(context, RegisterPageActivity.class);
//					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					context.startActivity(intent);
//					Toast.makeText(context.getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
//					((Activity)context).finish();
				} else {
					Toast.makeText(context.getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
				}
				break;
		}
		return false;
	}

}
