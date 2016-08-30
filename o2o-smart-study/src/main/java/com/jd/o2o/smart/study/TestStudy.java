package com.jd.o2o.smart.study;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class TestStudy {
    private Long age;
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("hello world!");
        System.out.println("hello world!");
        TestStudy test = new TestStudy();
        test.setAge(-1L);

//        test.checkMoney("getAge","setAge",test);
        test.checkMoney("setAge",test);
        System.out.println(test.getAge());
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    //1.校验金额，若金额是否为null，则赋值为0L, 若金额为小于0 ，则抛出异常
    public void checkMoney(String getMethodName, String setMethodName, Object object) {
        try {
            Method getMethod = this.getClass().getMethod(getMethodName);
            Long value = (Long) getMethod.invoke(object);
            if (value == null) {
                Method setMethod = this.getClass().getMethod(setMethodName, Long.class);
                setMethod.invoke(object, 0L);
            }else if (value < 0L){
                throw new RuntimeException();
            }
        } catch (Exception e) {
//            throw new BusinessException(MoneyErrorCode.CHECK_FEE_INVOKE_EXCEPTION);
            System.out.println("异常啦1111");
        }
    }
    // 2. 校验 金额，若金额为null或者小于0 ，则抛出异常
    public void checkMoney(String methodName, Object object) {
        try {
            Method getMethod = this.getClass().getMethod(methodName);
            Long value = (Long) getMethod.invoke(object);
            if (value == null || value < 0L) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("异常啦2222");
        }
    }



    private boolean checkPrice(Class clas) {
        Object orderMoney = null;
        try {
            orderMoney = clas.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Field f : clas.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object value = f.get(orderMoney);
                if (f.getType().toString().equals("Long")) {
                    Long val = (Long) value;
                    if (val == null) {
                        f.set(orderMoney, 0L);
                        continue;
                    }
                    if (val < 0L) {
                        return false;
                    }
                } else if (f.getType().toString().equals("Object")) {
                    return checkPrice(f.get(orderMoney).getClass());
                } else if (f.getType().toString().equals("List")) {
                    for(Object bean:(List)f.get(orderMoney)){
                        if(!checkPrice(bean.getClass())){
                            break;
                        }
                    }
                    return false;
                }else {
                    continue;
                }
            } catch (Exception e) {
//                throw new BusinessException(MoneyErrorCode.CHECK_FEE_INVOKE_EXCEPTION);
                System.out.println("哈哈哈哈哈");
            }
        }
        return true;
    }
}
