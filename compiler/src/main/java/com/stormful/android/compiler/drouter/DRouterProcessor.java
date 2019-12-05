package com.stormful.android.compiler.drouter;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.stormful.android.annotation.DRouter;
import com.stormful.android.annotation.InjectView;
import com.stormful.android.annotation.OnClick;
import com.stormful.android.compiler.viewinject.InjectConstant;
import com.stormful.android.compiler.viewinject.entity.InjectViewEntity;

import java.io.Writer;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @Description: DRouterProcessor:编译生成代码的类
 * @Author: dzh
 * @CreateDate: 2019-12-04 16:02
 */
@AutoService(Processor.class)
public class DRouterProcessor extends AbstractProcessor {

    //文件相关的辅助类
    private Filer mFiler;
    //元素相关的辅助类
    private Elements mElementUtils;
    //日志相关的辅助类
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mInit();
    }

    /**
     * 初始化常用对象
     */
    private void mInit() {
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
        printLog("DRouterProcessor:" + "mInit");
    }

    /**
     * 打印log
     *
     * @param msg  信息
     * @param args 参数
     */
    private void printLog(String msg, Object... args) {
        //printLog("Generate file failed, reason: %s", "init");
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }

    /**
     * 打印log
     *
     * @param msg 信息
     */
    private void printLog(String msg) {
        //printLog("init");
        mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //return super.getSupportedAnnotationTypes();
        Set<String> types = new LinkedHashSet<>();
        types.add(DRouter.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //获取支持的版本号
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        printLog("DRouterProcessor:" + "process");
        //处理DRouter注解
        handlerDRouter(env);
        return true;
    }


    /**
     * 处理InjectView注解
     *
     * @param env RoundEnvironment
     */
    private void handlerDRouter(RoundEnvironment env) {

        Map<String, String> routerMaps = new LinkedHashMap<>();

        //获取带有InjectView注解的数据
        Set<? extends Element> injectViewSet = env.getElementsAnnotatedWith(DRouter.class);
        printLog("injectViewSet ==> %s", injectViewSet);
        //遍历数据,把MainActivity等类的数据存储到injectionsByClass中
        for (Element element : injectViewSet) {
            Element enclosingElement = element.getEnclosingElement();
            //enclosingElement ==> com.stormful.android.annotationinject.MainActivity
//            printLog("enclosingElement ==> %s", enclosingElement);
            printLog("element ==> %s", element);
            String path = element.getAnnotation(DRouter.class).value();
            printLog("path ==> %s", path);

            routerMaps.put(path, element.toString());
        }

        String packageName="com.stormful.android.annotationinject";
        String className="DRoute$$Router";

        //通过注解类的数据生成java文件
        try {
            JavaFileObject classFile = mFiler.createSourceFile(packageName + "." + className);
            Writer writer = classFile.openWriter();
            writer.write(DRouterConstant.getDRouterCode(routerMaps));
            writer.flush();
            writer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
