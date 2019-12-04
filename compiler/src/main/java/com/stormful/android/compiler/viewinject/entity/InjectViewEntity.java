package com.stormful.android.compiler.viewinject.entity;

/**
 * @Description: 注解的数据实例
 * @Author: dzh
 * @CreateDate: 2019-12-04 16:23
 */
public class InjectViewEntity {

    //activity.tvTest = (android.widget.TextView) activity.findViewById(2131165300);
    private static final String INJECTION = "    activity.%s = (%s) activity.findViewById(%s);";

    private final String variableName; //需要找的控件的变量名称，例如：tvTest
    private final String type;         //控件的类型，例如：android.widget.TextView
    private final int value;           //控件的id，例如：2131165300

    public InjectViewEntity(String variableName, String type, int value) {
        this.variableName = variableName;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(INJECTION, variableName, type, value);
    }
}
