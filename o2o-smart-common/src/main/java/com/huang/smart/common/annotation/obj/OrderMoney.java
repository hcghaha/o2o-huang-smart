package com.huang.smart.common.annotation.obj;

import com.huang.smart.common.annotation.FieldMeta;

/**
 * Created by huangchaoguang on 2016/8/29.
 */
public class OrderMoney {
    @FieldMeta
    private Long freight;
    @FieldMeta
    private Long startCharge;

    private Integer couponVoucherType; //优惠券类型

    private Long jingBeanDiscount;


    public Long getFreight() {
        return freight;
    }

    public void setFreight(Long freight) {
        this.freight = freight;
    }

    public Long getStartCharge() {
        return startCharge;
    }

    public void setStartCharge(Long startCharge) {
        this.startCharge = startCharge;
    }

    public Integer getCouponVoucherType() {
        return couponVoucherType;
    }

    public void setCouponVoucherType(Integer couponVoucherType) {
        this.couponVoucherType = couponVoucherType;
    }

    public Long getJingBeanDiscount() {
        return jingBeanDiscount;
    }

    public void setJingBeanDiscount(Long jingBeanDiscount) {
        this.jingBeanDiscount = jingBeanDiscount;
    }
}
