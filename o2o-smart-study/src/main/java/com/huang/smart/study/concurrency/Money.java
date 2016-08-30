package com.huang.smart.study.concurrency;

import java.util.List;

/**
 * Created by huangchaoguang on 2016/8/29.
 */
public class Money {
    private Long price;
    private Long num;
    private Integer integer;
    private List<Obj> myList;

    private Obj obj;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public List<Obj> getMyList() {
        return myList;
    }

    public void setMyList(List<Obj> myList) {
        this.myList = myList;
    }
}

class Obj{
    private Long field1;
    private Long field2;
    private Integer field3;

    public Long getField1() {
        return field1;
    }

    public void setField1(Long field1) {
        this.field1 = field1;
    }

    public Long getField2() {
        return field2;
    }

    public void setField2(Long field2) {
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }
}
