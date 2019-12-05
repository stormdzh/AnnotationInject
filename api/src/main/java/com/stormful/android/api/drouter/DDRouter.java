package com.stormful.android.api.drouter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2019-12-05 19:01
 */
public class DDRouter {

    private static Context mContext;

    //调用测试化方法
    public static void init(Context context) {
        mContext = context;

        //com.stormful.android.annotationinject.DRoute$$Router
        String className = "com.stormful.android.annotationinject.DRoute$$Router";
        try {
            Class<?> actClass = Class.forName("com.stormful.android.annotationinject.MainActivity$$ViewInjector");
            //拿到反射字节码
            Class<?> aClass = Class.forName(className);
            //通过反射获取到生成的java类文件
            //获取对象
            Object o = aClass.getConstructor().newInstance();
            ((IDRoute) o).init(DRouterList.routerList);
            Log.i("dzh", "已经获取到路由列表信息");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //通过路径跳转到目标Activity
    public static void navigation(String path) {
        if (DRouterList.routerList == null) {
            Log.i("dzh", "没有生成路由表信息");
            return;
        }
        if (!DRouterList.routerList.containsKey(path)) {
            Log.i("dzh", "还没有生成路由：" + path);
        }

        String actStr = DRouterList.routerList.get(path);

        if (!TextUtils.isEmpty(actStr)) {
            startActivity(actStr);
        }

    }

    private static void startActivity(String actStr) {

        if (mContext == null) return;
        Intent intent = new Intent(actStr);
        mContext.startActivity(intent);
    }
}
