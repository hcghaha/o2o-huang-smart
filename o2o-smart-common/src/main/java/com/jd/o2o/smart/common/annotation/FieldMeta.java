package com.jd.o2o.smart.common.annotation;

import java.lang.annotation.*;

/**
 * Created by huangchaoguang on 2016/8/30.
 */
@Retention(RetentionPolicy.RUNTIME)  // 注解在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD}) //定义注解的作用范围
@Documented //说明该注解将被包含在Javadoc中
public @interface FieldMeta {
    /**
     * 是否需要校验
     * @return true为需求校验，false为不需要
     */
    boolean isNeedCheck() default true;
}
