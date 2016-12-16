package com.mrym.newsbulletion.utils.json;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class JsonConvert<T> extends AbsConvert<T> {
    
    private String mDataName = null;

    @Override
    public T parseData(String result) {
        T t = null;
        try {
            Type trueType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(mDataName)) {
                JSONObject jsonObject = new JSONObject(result);
                t = gson.fromJson(jsonObject.getString(mDataName), trueType);
            } else {
                t = gson.fromJson(result, trueType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 在数据中要获取的name（当数据类型为集合时）
     *
     * @return data_name
     */
    public void setDataName(String dataName) {
        mDataName = dataName;
    }
}
