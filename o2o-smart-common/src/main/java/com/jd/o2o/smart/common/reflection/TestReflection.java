package com.jd.o2o.smart.common.reflection;

import com.jd.o2o.smart.common.annotation.FieldMeta;
import com.jd.o2o.smart.common.annotation.obj.OrderMoney;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by huangchaoguang on 2016/8/30.
 */
public class TestReflection {
    public static void main(String[] args){

        OrderMoney orderMoney1 = new OrderMoney();
        orderMoney1.setFreight(10L);
        orderMoney1.setCouponVoucherType(-1);
        orderMoney1.setJingBeanDiscount(-1L);
        orderMoney1.setStartCharge(null);
        TestReflection test = new TestReflection();
        boolean flag = test.checkMoney(orderMoney1);
        if (flag){
            System.out.println("1通过");
        } else{
            System.out.println("1失败");

        }
    }

    /**
     * list方式
     * @param obj
     * @param list
     * @return
     */
    private boolean checkMoney(Object obj, List<String> list) {
        Class clas = obj.getClass();
        for (String fieldName : list) {
            try {
                Field f = clas.getDeclaredField(fieldName);
                f.setAccessible(true);
                if (f.getType().getSimpleName().equals("Long")) {
                    Long val = (Long) f.get(obj);
                    if (val == null) {
                        f.set(obj, 0L);
                        continue;
                    }
                    if (val < 0L) {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 注解方式
     * @param obj
     * @return
     */
    private boolean checkMoney(Object obj) {
        Class clas = obj.getClass();
        for (Field f : clas.getDeclaredFields()) {
            try {
                f.setAccessible(true);
                FieldMeta meta = f.getAnnotation(FieldMeta.class);
                if (meta != null && meta.isNeedCheck()) {
                    Long val = (Long) f.get(obj);
                    if (val == null) {
                        f.set(obj, 0L);
                        continue;
                    }
                    if (val < 0L) {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    /**
     * 递归方式
     * @param obj
     * @return
     */
    private boolean checkPrice(Object obj) {
        Class clas = obj.getClass();
        for (Field f : clas.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object value = f.get(obj);
                System.out.println(f.getType().getSimpleName());

                if (f.getType().getSimpleName().equals("Long")) {
                    FieldMeta meta = f.getAnnotation(FieldMeta.class);
                    if (meta == null || !meta.isNeedCheck()) {
                        continue;
                    }
                    Long val = (Long) value;
                    if (val == null) {
                        f.set(obj, 0L);
                        continue;
                    }
                    if (val < 0L) {
                        return false;
                    }
                } else if (f.getType().getSimpleName().equals("Object")) {
                    return checkPrice(f.get(obj));
                } else if (f.getType().getSimpleName().equals("List")) {
                    for(Object bean:(List)f.get(obj)){
                        if(!checkPrice(bean)){
                            break;
                        }
                    }
                    return false;
                }else {
                    continue;
                }
            } catch (Exception e) {
//                throw new BusinessException(MoneyErrorCode.CHECK_FEE_INVOKE_EXCEPTION);
                e.printStackTrace();
            }
        }
        return true;
    }
}
