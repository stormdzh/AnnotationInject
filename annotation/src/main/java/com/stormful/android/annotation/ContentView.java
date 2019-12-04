package com.stormful.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: setContentView
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:06
 */


@Retention(RetentionPolicy.RUNTIME)//@Retention用来修饰这是一个什么类型的注解。这里表示该注解是一个运行时注解。
@Target({ElementType.TYPE})//@Target用来表示这个注解可以使用在哪些地方。比如：类、方法、属性、接口等等。
//这里ElementType.TYPE 表示这个注解可以用来修饰：Class, interface or enum declaration。
//当你用ContentView修饰一个方法时，编译器会提示错误。
//这里的interface并不是说ContentView是一个接口。
//就像申明类用关键字class。申明枚举用enum。申明注解用的就是@interface。
public @interface ContentView {
    int value();
}
