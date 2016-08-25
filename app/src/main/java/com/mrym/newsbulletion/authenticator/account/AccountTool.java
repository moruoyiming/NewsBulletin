package com.mrym.newsbulletion.authenticator.account;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mrym.newsbulletion.authenticator.UniqueIdentity;
import com.mrym.newsbulletion.domain.modle.UserBean;
import com.mrym.newsbulletion.listener.DefaultListener;
import com.mrym.newsbulletion.utils.common.AppUtils;
import com.mrym.newsbulletion.utils.common.PackageUtils;

import java.lang.ref.SoftReference;


/**
 * 账户相关工具
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class AccountTool {
    private static final String TAG = "AccountUtil";
    private AccountManager mAccountManager;
    private String mPackageName;
    private DefaultListener mDefaultListener;
    private Context mContext;
    private String userDataKey = "data";
    private SoftReference<UserBean> userInfoSoftReference;
    private static AccountTool INSTANCE;

    public static AccountTool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountTool();
        }
        return INSTANCE;
    }

    private AccountTool() {
        init(null);
    }

    private AccountTool(DefaultListener defaultListener) {
        init(defaultListener);
    }

    private void init(DefaultListener defaultListener) {
        this.mContext = AppUtils.getContext();
        mAccountManager = AccountManager.get(mContext);
        this.mPackageName = PackageUtils.getAppPackageInfo(mContext).packageName;
        this.mDefaultListener = defaultListener;
    }

    public void login(String userName, String password, String data) {
        if (checkAccountIfExits()) {
            errorDefaultListener(mContext.getString(R.string.more_than_one_account_error));
            return;
        }

        saveAccount(userName, password, data);
    }

    // public void phoneLogin(String userName, String password, String user,
    // DefaultListener defaultListener) {
    // mDefaultListener = defaultListener;
    // phoneLogin(userName, password, user);
    // }

    public void saveAccount(String userName, String password, String data) {
        final Account account = new Account(userName, mPackageName);
        Intent accountIntent = new Intent();
        accountIntent.putExtra(userDataKey, data);
        // accountIntent.putExtra("sessionToken", user.getSessionToken());
        // accountIntent.putExtra("objectId", user.getObjectId());
        mAccountManager.addAccountExplicitly(account, password, accountIntent.getExtras());

        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, userName);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, mPackageName);

        new AccountAuthenticatorActivity().setAccountAuthenticatorResult(intent.getExtras());
        Log.e(TAG, "login---------------------------");
        successDefaultListener();
    }

    /**
     * 检查账户是否存在
     *
     * @return boolean true 存在 false 不存在
     */
    public boolean checkAccountIfExits() {
        Account account = getCurrentAccount();
        return account != null;
    }

    /**
     * 获取当前账户信息 如果账户存在则返回账户信息，反之则返回到登陆页
     */
    public Account getCurrentAccount() {
        Account[] accounts = mAccountManager.getAccountsByType(mPackageName);
        if (accounts != null && accounts.length > 0) {
            return accounts[0];
        }
        return null;
    }

    /**
     * 获取账户数据
     **/
    public UserBean getAccountData() {
        if (userInfoSoftReference != null && userInfoSoftReference.get() != null) {
            return userInfoSoftReference.get();
        }
        if (checkAccountIfExits()) {
            Account account = getCurrentAccount();
            String userData = mAccountManager.getUserData(account, userDataKey);
            UserBean userInfo = new Gson().fromJson(userData, new TypeToken<UserBean>() {
            }.getType());
            userInfoSoftReference = new SoftReference<UserBean>(userInfo);
            return userInfo;
        } else {
            return null;
        }
    }

    /**
     * 更新账户数据
     *
     * @param data 账户数据(用户信息)
     **/
    public void updateAccountData(String data) {
        if (!checkAccountIfExits()) return;
        Account account = getCurrentAccount();
        mAccountManager.setUserData(account, userDataKey, data);
    }

    /**
     * 获取账户id
     **/
    public long getAccountId() {
        UserBean data = getAccountData();
        if (data != null) return data.getShopId();
        return -1;
    }

    /**
     * 获取账户唯一标识
     **/
    public String getAccountUnique() {
        return UniqueIdentity.getInstance(mContext).getUserUniqueNotAssign(String.valueOf(getAccountId()));
    }

    public void removeAccount() {
        Account account = getCurrentAccount();
        if (account == null) {
            errorDefaultListener("没有可以移除的账户");
            return;
        }
        AccountManager.get(mContext).removeAccount(account, new AccountManagerCallback<Boolean>() {
                    @Override
                    public void run(AccountManagerFuture<Boolean> future) {
                        try {
                            userInfoSoftReference.clear();
                            boolean result = future.getResult();
                            if (!result) {
                                errorDefaultListener("移除账户失败");
                                return;
                            }

                        } catch (Exception e) {
                            errorDefaultListener("移除账户失败");
                            e.printStackTrace();
                        }
                        successDefaultListener();
                    }
                }

                , null);
//		XmppConnectionManager.closeConnection();
    }

    /**
     * 失败回调
     *
     * @param message 失败提示信息
     */
    private void errorDefaultListener(String message) {
        if (mDefaultListener == null) return;
        mDefaultListener.onError(message);
    }

    /**
     * 成功回调
     */
    private void successDefaultListener() {
        if (mDefaultListener == null) return;
        mDefaultListener.onSuccess();
    }

    public DefaultListener getDefaultListener() {
        return mDefaultListener;
    }

    public void setDefaultListener(DefaultListener mDefaultListener) {
        this.mDefaultListener = mDefaultListener;
    }

    public String getUserData() {
        return mAccountManager.getUserData(getCurrentAccount(), userDataKey);
    }

}
