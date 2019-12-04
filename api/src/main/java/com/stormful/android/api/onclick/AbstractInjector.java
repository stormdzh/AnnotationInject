package com.stormful.android.api.onclick;

/**
 * @Description: 代理类接口，所有生成代码类都要实现这个接口。
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:34
 */

public interface AbstractInjector<T> {

    /**
     * 注射代码
     *
     * @param finder finder
     * @param target target
     * @param source source
     */
    void inject(Finder finder, T target, Object source);

    /**
     * 设置间隔时间
     *
     * @param time time
     */
    void setIntervalTime(long time);
}