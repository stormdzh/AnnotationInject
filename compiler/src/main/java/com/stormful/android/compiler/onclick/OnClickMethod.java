package com.stormful.android.compiler.onclick;


import java.util.List;

/**
 * @Description: 每一个使用了@OnceClick注解的Activity或View，都会为其生成一个代理类，而一个代理中有可能有
 * 很多个@OnceClick修饰的方法，所以我们专门为每个方法有创建了一个javaBean用于保存方法信息
 * @Author: dzh
 * @CreateDate: 2019-12-04 18:17
 */
public class OnClickMethod {

    private int id;
    private String methodName;
    private List<String> methodParameters;

    OnClickMethod(int id, String methodName, List<String> methodParameters) {
        this.id = id;
        this.methodName = methodName;
        this.methodParameters = methodParameters;
    }

    int getMethodParametersSize() {
        return methodParameters == null ? 0 : methodParameters.size();
    }

    int getId() {
        return id;
    }

    String getMethodName() {
        return methodName;
    }

    List<String> getMethodParameters() {
        return methodParameters;
    }

}