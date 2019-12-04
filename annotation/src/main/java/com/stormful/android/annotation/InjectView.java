package com.stormful.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: findViewById的注解
 * @Author: dzh
 * @CreateDate: 2019-12-04 15:56
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface InjectView {
    int value();
}
