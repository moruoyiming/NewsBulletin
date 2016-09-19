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

}
