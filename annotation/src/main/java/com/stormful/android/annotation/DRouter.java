package com.stormful.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: DRouter 路由
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:06
 */


@Retention(RetentionPolicy.RUNTIME)//@Retention用来修饰这是一个什么类型的注解。这里表示该注解是一个运行时注解。
@Target({ElementType.TYPE})//@Target用来表示这个注解可以使用在哪些地方。比如：类、方法、属性、接口等等。
public @interface DRouter {
    String value(); //用来保存跳转的路径
}
