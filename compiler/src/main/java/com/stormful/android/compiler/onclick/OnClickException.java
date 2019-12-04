package com.stormful.android.compiler.onclick;

/**
 * @Description: 点击事件异常
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:17
 */
public class OnClickException extends Exception {

    public OnClickException(String message) {
        //自定义异常
        super(message);
    }
}