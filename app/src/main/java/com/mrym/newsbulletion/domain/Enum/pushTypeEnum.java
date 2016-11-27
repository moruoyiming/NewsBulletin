package com.mrym.newsbulletion.domain.Enum;

/**
 * Created by Jian on 2016/8/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public enum PushTypeEnum {


    SINGLE(0, "single"),

    MULTI(1, "multi"),

    IMAGE(2,"image");

    private int code;
    private String name;
    public static final int PUSHTYPE_SINGLE = 0;
    public static final int PUSHTYPE_MULTI = 1;
    public static final int PUSHTYPE_IMAGE = 2;
    private PushTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getEnumNameByType(int code) {
        for (PushTypeEnum pushType : PushTypeEnum.values()) {
            if (pushType.getCode() == code) {
                return pushType.getName();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
