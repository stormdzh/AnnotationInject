package com.stormful.android.annotationinject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.stormful.android.annotation.InjectView;
import com.stormful.android.api.AnnotationInject;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tvTest)
    TextView tvTest;
    @InjectView(R.id.tvTest2)
    TextView tvTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotationInject.inject(this);

        tvTest2.setText("我终于自己实现了通过注解找id了");
    }
}
