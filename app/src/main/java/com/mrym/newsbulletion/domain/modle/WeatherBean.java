package com.mrym.newsbulletion.domain.modle;

import java.util.List;

public class WeatherBean {
    private String msg;

    private List<Weather> result;

    private String retCode;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setResult(List<Weather> result) {
        this.result = result;
    }

    public List<Weather> getResult() {
        return this.result;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return this.retCode;
    }

}