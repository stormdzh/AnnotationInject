package com.stormful.android.api.viewInject;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: Activity需要调用的api
 * @Author: dzh
 * @CreateDate: 2019-12-04 17:39
 */
public class AnnotationInject {

    public static void inject(Activity activity) {

        try {
            //通过反射获取到生成的java类文件
            Class<?> actClass = Class.forName(activity.getClass().getName() + "$$ViewInjector");
            //获取生成的类的inject方法
            Method inject = actClass.getMethod("inject", activity.getClass());
            //在传入的Activity中调用inject方法，达到tvTest有值的目的
            inject.invoke(null, activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
