package com.stormful.android.api.contentview;

/**
 * @Description: 方式一：让子类实现给这个基类，就实现了setContentView的功能
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:13
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.stormful.android.annotation.ContentView;


@SuppressLint("Registered")
public class ContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注解解析
        //遍历所有的子类
        for (Class c = this.getClass(); c != Context.class; c = c.getSuperclass()) {
            assert c != null;
            //找到修饰了注解ContentView的类
            ContentView annotation = (ContentView) c.getAnnotation(ContentView.class);
            if (annotation != null) {
                try {
                    //获取ContentView的属性值
                    int value = annotation.value();
                    //调用setContentView方法设置view
                    this.setContentView(value);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}