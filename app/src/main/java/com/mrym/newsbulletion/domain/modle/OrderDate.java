package com.mrym.newsbulletion.domain.modle;

/**
 * Created by Shawn on 2016/8/24.
 */
public class OrderDate {

    private String havePayPriceSum;
    private String noPayPriceSum;
    private String orderCreateDate;
    private String orderSum;
    private double price;
    private String priceSum;
    private String total_fee;

    @Override
    public String toString() {
        return "OrderDate{" +
                "havePayPriceSum='" + havePayPriceSum + '\'' +
                ", noPayPriceSum='" + noPayPriceSum + '\'' +
                ", orderCreateDate='" + orderCreateDate + '\'' +
                ", orderSum='" + orderSum + '\'' +
                ", price=" + price +
                ", priceSum='" + priceSum + '\'' +
                ", total_fee='" + total_fee + '\'' +
                '}';
    }

    public String getHavePayPriceSum() {
        return havePayPriceSum;
    }

    public void setHavePayPriceSum(String havePayPriceSum) {
        this.havePayPriceSum = havePayPriceSum;
    }

    public String getNoPayPriceSum() {
        return noPayPriceSum;
    }

    public void setNoPayPriceSum(String noPayPriceSum) {
        this.noPayPriceSum = noPayPriceSum;
    }

    public String getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(String orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
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

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    //
//    private String date;
//    private int orderCount;
//    private double notGetPrice;
//    private double alreadyGetPrice;
//
//    public OrderDate() {}
//
//    public OrderDate(String date, int orderCount, double notGetPrice, double alreadyGetPrice) {
//        this.date = date;
//        this.orderCount = orderCount;
//        this.notGetPrice = notGetPrice;
//        this.alreadyGetPrice = alreadyGetPrice;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderDate{" +
//                "date='" + date + '\'' +
//                ", orderCount=" + orderCount +
//                ", notGetPrice=" + notGetPrice +
//                ", alreadyGetPrice=" + alreadyGetPrice +
//                '}';
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public int getOrderCount() {
//        return orderCount;
//    }
//
//    public void setOrderCount(int orderCount) {
//        this.orderCount = orderCount;
//    }
//
//    public double getNotGetPrice() {
//        return notGetPrice;
//    }
//
//    public void setNotGetPrice(double notGetPrice) {
//        this.notGetPrice = notGetPrice;
//    }
//
//    public double getAlreadyGetPrice() {
//        return alreadyGetPrice;
//    }
//
//    public void setAlreadyGetPrice(double alreadyGetPrice) {
//        this.alreadyGetPrice = alreadyGetPrice;
//    }
}
