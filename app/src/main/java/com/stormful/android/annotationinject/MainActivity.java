package com.stormful.android.annotationinject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stormful.android.annotation.InjectView;
import com.stormful.android.annotation.OnClick;
import com.stormful.android.api.onclick.OnClickInit;
import com.stormful.android.api.viewInject.AnnotationInject;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tvTest)
    TextView tvTest;
    @InjectView(R.id.tvTest2)
    TextView tvTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化findviewbyid
        AnnotationInject.inject(this);
        //初始化点击事件
        OnClickInit.init(this, 2000);

        tvTest2.setText("我终于自己实现了通过注解找id了");
    }

    @OnClick(R.id.tvTest)
    public void onClick(View v) {
        Toast.makeText(this, "onClick 被点击了", Toast.LENGTH_SHORT).show();

    }
}
