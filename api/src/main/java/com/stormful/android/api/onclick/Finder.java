package com.stormful.android.api.onclick;

import android.app.Activity;
import android.view.View;


/**
 * @Description: Finder的作用是根据不同的类型，去实现不同的findViewById方法。
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:35
 */
public enum Finder {
    VIEW {
        @Override
        public View findViewById(Object source, int id) {
            return ((View) source).findViewById(id);
        }
    },
    ACTIVITY {
        @Override
        public View findViewById(Object source, int id) {
            return ((Activity) source).findViewById(id);
        }
    };

    public abstract View findViewById(Object source, int id);
}