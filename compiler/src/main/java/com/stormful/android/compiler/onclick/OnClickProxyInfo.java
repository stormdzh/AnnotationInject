package com.stormful.android.compiler.onclick;


import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

/**
 * @Description: 这个类也没什么好讲的，就是用StringBuidler拼出一个类来。
 *  *             ProxyInfo保存的是类信息，方法信息我们用List methods保存。然后根据这些信息生成类。
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:17
 */
public class OnClickProxyInfo {

    private String packageName;
    private String targetClassName;
    private String proxyClassName;
    private TypeElement typeElement;
    private List<OnClickMethod> methods;
    private static final String PROXY = "$$OnClick";

    OnClickProxyInfo(String packageName, String className) {
        this.packageName = packageName;
        this.targetClassName = className;
        this.proxyClassName = className + PROXY;
    }

    String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    String generateJavaCode() throws OnClickException {

        StringBuilder builder = new StringBuilder();
        builder.append("// 编译生成的代码，不要修改\n");
        builder.append("// 更多内容：https://github.com/yangchong211\n");
        builder.append("package ").append(packageName).append(";\n\n");

        //写入导包，注意如果更换了Finder和AbstractInjector类的路径，此处也需要更改
        builder.append("import android.view.View;\n");
        builder.append("import com.stormful.android.api.onclick.Finder;\n");
        builder.append("import com.stormful.android.api.onclick.AbstractInjector;\n");
        builder.append('\n');

        builder.append("public class ").append(proxyClassName)
                .append("<T extends ").append(getTargetClassName()).append(">")
                .append(" implements AbstractInjector<T>").append(" {\n");
        builder.append('\n');

        generateInjectMethod(builder);
        builder.append('\n');

        builder.append("}\n");
        return builder.toString();

    }

    private String getTargetClassName() {
        return targetClassName.replace("$", ".");
    }

    private void generateInjectMethod(StringBuilder builder) throws OnClickException {
        builder.append("    public long intervalTime; \n");
        builder.append('\n');

        builder.append("    @Override \n")
                .append("    public void setIntervalTime(long time) {\n")
                .append("        intervalTime = time;\n    } \n");
        builder.append('\n');

        builder.append("    @Override \n")
                .append("    public void inject(final Finder finder, final T target, Object source) {\n");
        builder.append("        View view;");
        builder.append('\n');

        //这一步是遍历所有的方法
        for (OnClickMethod method : getMethods()) {
            builder.append("        view = ")
                    .append("finder.findViewById(source, ")
                    .append(method.getId())
                    .append(");\n");
            builder.append("        if(view != null){\n")
                    .append("            view.setOnClickListener(new View.OnClickListener() {\n")
                    .append("            long time = 0L;\n");
            builder.append("            @Override\n")
                    .append("            public void onClick(View v) {\n");
            builder.append("                long temp = System.currentTimeMillis();\n")
                    .append("                if (temp - time >= intervalTime) {\n" +
                            "                    time = temp;\n");
            if (method.getMethodParametersSize() == 1) {
                if (method.getMethodParameters().get(0).equals("android.view.View")) {
                    builder.append("                    target.")
                            .append(method.getMethodName()).append("(v);");
                } else {
                    throw new OnClickException("Parameters must be android.view.View");
                }
            } else if (method.getMethodParametersSize() == 0) {
                builder.append("                    target.")
                        .append(method.getMethodName()).append("();");
            } else {
                throw new OnClickException("Does not support more than one parameter");
            }
            builder.append("\n                }\n")
                    .append("            }")
                    .append("});\n        }\n");
        }

        builder.append("  }\n");
    }

    TypeElement getTypeElement() {
        return typeElement;
    }

    void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    private List<OnClickMethod> getMethods() {
        return methods == null ? new ArrayList<OnClickMethod>() : methods;
    }

    void addMethod(OnClickMethod onceMethod) {
        if (methods == null) {
            methods = new ArrayList<>();
        }
        methods.add(onceMethod);
    }
}