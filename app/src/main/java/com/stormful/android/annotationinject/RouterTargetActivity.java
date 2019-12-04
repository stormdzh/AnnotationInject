package com.stormful.android.annotationinject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stormful.android.annotation.DRouter;

/**
 * @Description: 路由的Activity
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:34
 */
@DRouter("/router/target")
public class RouterTargetActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_routertarget);
    }
}
