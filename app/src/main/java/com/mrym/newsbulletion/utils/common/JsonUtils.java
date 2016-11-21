package com.mrym.newsbulletion.utils.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class JsonUtils {
    /**
     * 解析Json
     *
     * @param jsonStr
     * @param T
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonStr, Class<T> T) {
        Gson gson = new Gson();
        T bean = gson.fromJson(jsonStr, T);
        return bean;
    }

    /**
     * 解析Json
     *
     * @param jsonStr
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonStr, Type typeToken) {
        Gson gson = new Gson();
        T bean = gson.fromJson(jsonStr, typeToken);
        return bean;
    }

    /**
     * 创建一个json字符串
     *
     * @param obj
     * @return
     */
    public static String createJsonString(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        return jsonStr;
    }

    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }
}
