package com.mrym.newsbulletion.domain.modle;

/**
 * Created by Shawn on 2016/8/23.
 */
public class HomeOrderBean {
    private String orderSum;
    private double price;
    private String priceSum;
    private double total_fee;

    @Override
    public String toString() {
        return "HomeOrderBean{" +
                "orderSum='" + orderSum + '\'' +
                ", price=" + price +
                ", priceSum='" + priceSum + '\'' +
                ", total_fee=" + total_fee +
                '}';
    }

    public String getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(String orderSum) {
        this.orderSum = orderSum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(String priceSum) {
        this.priceSum = priceSum;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }
}
