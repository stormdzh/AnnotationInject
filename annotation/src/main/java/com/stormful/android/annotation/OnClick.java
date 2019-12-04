package com.stormful.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 点击事件
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:05
 */

@Retention(RetentionPolicy.CLASS)//@Retention用来修饰这是一个什么类型的注解。这里表示该注解是一个编译时注解。
@Target(ElementType.METHOD)
//@Target用来表示这个注解可以使用在哪些地方。比如：类、方法、属性、接口等等。这里ElementType.METHOD 表示这个注解可以用来修饰：方法

public @interface OnClick { //这里的interface并不是说OnceClick是一个接口。就像申明类用关键字class。申明注解用的就是@interface。

    //返回值表示这个注解里可以存放什么类型值
    int value();
}