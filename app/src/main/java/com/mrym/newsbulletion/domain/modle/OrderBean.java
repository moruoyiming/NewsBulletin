package com.mrym.newsbulletion.domain.modle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shawn on 2016/8/24.
 */
public class OrderBean implements Parcelable {
    private String id;
    private String orderCreateTime;
    private String orderState;
    private String paymentType;
    private double price;
    private String shopId;
    private double total_fee;

    public OrderBean() {}

    public OrderBean(String id, String orderCreateTime, String orderState, String paymentType, double price, String shopId, double total_fee) {
        this.id = id;
        this.orderCreateTime = orderCreateTime;
        this.orderState = orderState;
        this.paymentType = paymentType;
        this.price = price;
        this.shopId = shopId;
        this.total_fee = total_fee;
    }

    protected OrderBean(Parcel in) {
        id = in.readString();
        orderCreateTime = in.readString();
        orderState = in.readString();
        paymentType = in.readString();
        price = in.readDouble();
        shopId = in.readString();
        total_fee = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(orderCreateTime);
        dest.writeString(orderState);
        dest.writeString(paymentType);
        dest.writeDouble(price);
        dest.writeString(shopId);
        dest.writeDouble(total_fee);
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    @Override
    public String toString() {
        return "OrderBean{" +
                "id='" + id + '\'' +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderState='" + orderState + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", price=" + price +
                ", shopId='" + shopId + '\'' +
                ", total_fee=" + total_fee +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }
}
