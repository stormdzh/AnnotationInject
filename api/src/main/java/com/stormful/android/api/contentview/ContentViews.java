package com.stormful.android.api.contentview;

import android.app.Activity;

import com.stormful.android.annotation.ContentView;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:16
 */
public class ContentViews {

    public static void inject(Activity activity) {
        Class<? extends Activity> actClass = activity.getClass();
        ContentView annotation = actClass.getAnnotation(ContentView.class);
        if (annotation != null) {
            int layoutId = annotation.value();
            if (layoutId > 0) {
                activity.setContentView(layoutId);
            }
        }

    }
}
