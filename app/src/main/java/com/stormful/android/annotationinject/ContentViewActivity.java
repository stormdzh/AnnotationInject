package com.stormful.android.annotationinject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stormful.android.annotation.ContentView;
import com.stormful.android.api.contentview.ContentViews;

/**
 * @Description: 测试运行时注解实现setContentView
 * @Author: dzh
 * @CreateDate: 2019-12-04 20:02
 */
@ContentView(R.layout.activity_contentview)
public class ContentViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentViews.inject(this);
    }
}
