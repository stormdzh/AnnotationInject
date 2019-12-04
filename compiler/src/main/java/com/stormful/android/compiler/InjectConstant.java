package com.stormful.android.compiler;

/**
 * @Description: 注入的常量类
 * @Author: dzh
 * @CreateDate: 2019-12-04 17:10
 */
public class InjectConstant {
    //生成文件的后缀名
    public static final String SUFFIX = "$$ViewInjector";
    //生成类的模板
    public static final String INJECTOR = ""
            + "package %s;\n\n"
            + "public class %s {\n"
            + "  public static void inject(%s activity) {\n"
            + "%s"
            + "  }\n"
            + "}\n";
}
