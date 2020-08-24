package com.zl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: ZL
 * @Date: 2020/7/24 16:21
 * @Description:
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetParameter {

    /**
     * 定义注解的一个元素 并给定默认值
     * @return
     */
    String value() default "定义在参数上的注解元素value的默认值";

}
