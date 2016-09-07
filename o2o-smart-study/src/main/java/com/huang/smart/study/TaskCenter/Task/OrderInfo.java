package com.huang.smart.study.TaskCenter.Task;

/**
 * Goal：the function of class
 * User:  huangchaoguang
 * Date: 2016/9/2
 * Time: 10:29
 * Version: 1.0
 */
public class OrderInfo {
    public String BillNumber;
    public String BuildDate;
    public String Customer;
    public String GoodsName;
    public float Amount;
    public float SaleMoney;


    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }

    public String getBuildDate() {
        return BuildDate;
    }

    public void setBuildDate(String buildDate) {
        BuildDate = buildDate;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public float getSaleMoney() {
        return SaleMoney;
    }

    public void setSaleMoney(float saleMoney) {
        SaleMoney = saleMoney;
    }
}
