package com.mrym.newsbulletion.authenticator;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取手机唯一标识
 * Created by w on 2016/3/21.
 * http://blog.csdn.net/ljz2009y/article/details/22895297
 */
public class UniqueIdentity {
    private static UniqueIdentity INSTANCE;
    private Context mContext;
    private final String USER_UNIQUE_PREFIX = "user-";
    private final String USER_STATE_UNIQUE_PREFIX = USER_UNIQUE_PREFIX + "state-";
    private final String GROUP_UNIQUE_PREFIX = "shop-";
    //private final String GAME_TYPE_UNIQUE_PREFIX = "game-type-";
    //private final String GAME_UNIQUE_PREFIX = "game-";
    public final String SYSTEM_PUSH_UNIQUE_NAME = "system-push";
    private final String COMMENT_DYNAMIC = USER_UNIQUE_PREFIX + "comment-dynamic-";
    //private String mySelfIdentity = "";
    private String mCombinedDeviceId;

    private UniqueIdentity(Context context) {
        this.mContext = context;
    }

    public static UniqueIdentity getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UniqueIdentity(context.getApplicationContext());
        }
        return INSTANCE;
    }

    /**
     * IMEI
     *
     * @return 唯一标识
     **/
    public String getIMEI() {
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            return TelephonyMgr.getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Pseudo-Unique ID
     *
     * @return 唯一标识
     **/
    public String getPseudoUniqueId() {
        try {
            return "35" + //we make this look like a valid IMEI

                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        } catch (Exception e) {
            return "";
        }

    }


    /**
     * android id
     *
     * @return 唯一标识
     **/
    public String getAndroidId() {
        try {
            return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Wlan Mac Address
     *
     * @return 唯一标识
     **/
    public String getWlanMacAddress() {
        try {
            WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
            return wm.getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            return "";
        }


    }

    /**
     * Bluetooth Mac Address
     *
     * @return 唯一标识
     **/
    public String getBtMacAddress() {
        try {
            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            return m_BluetoothAdapter.getAddress();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * Combined Device ID
     *
     * @return 唯一标识
     **/
    public String getCombinedDeviceId() {
        String m_szLongID = getIMEI() + getPseudoUniqueId()
                + getAndroidId() + getWlanMacAddress() + getBtMacAddress();
        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        // get md5 bytes
        byte p_md5Data[] = m.digest();
        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            // if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }   // hex string to uppercase
        return m_szUniqueID.toUpperCase();
    }

    /**
     * 获取用户唯一标识
     *
     * @param userId 用户ID
     * @param assign 是否赋值
     * @return 唯一标识
     **/
    private String getUserUnique(String userId, boolean assign) {
        //userId = "test_client";
        //Long myUserId = Long.valueOf(userId);
        //if (assign) mySelfIdentity = userId;
        if (userId == null || userId.trim().equals("")) {
            if (mCombinedDeviceId == null) {
                mCombinedDeviceId = getCombinedDeviceId();
            }
            //mySelfIdentity = mCombinedDeviceId;
//            if (!mySelfIdentity.equals("") && !userId.equals(mySelfIdentity)) {
//                ClientConnection.getInstance(mContext).disconnection();
//            }
            return mCombinedDeviceId;
        }
        return USER_UNIQUE_PREFIX + userId;
    }

    public String getUserUniqueAssign(String userId) {
        return getUserUnique(userId, true);
    }

    public String getUserUniqueNotAssign(String userId) {
        return getUserUnique(userId, false);
    }

    /**
     * 获取用户状态唯一标识
     **/
    public String getUserStateUnique(String userId) {
        if (userId == null) return null;
        return USER_STATE_UNIQUE_PREFIX + userId;
    }

    /**
     * 获取群组唯一标识
     *
     * @param groupId 群组ID
     * @return 唯一标识
     **/

    public String getGroupUnique(String groupId) {
        if (groupId == null) return null;
        return GROUP_UNIQUE_PREFIX + groupId;
    }

    /**
     * 动态评论唯一标识
     *
     * @param userId 用户id
     * @return 唯一标识
     **/
    public String getCommentDynamicUnique(String userId) {
        if (userId == null) return null;
        return COMMENT_DYNAMIC + userId;
    }
}
