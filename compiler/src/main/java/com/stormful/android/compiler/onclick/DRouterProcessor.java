package com.stormful.android.compiler.onclick;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.stormful.android.annotation.InjectView;
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
        printLog("AbstractProcessor:" + "mInit");
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
        types.add(Override.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        printLog("AbstractProcessor:" + "process");
        //处理InjectView注解
        handlerDRouter(env);
        return true;
    }


    /**
     * 处理InjectView注解
     *
     * @param env RoundEnvironment
     */
    private void handlerDRouter(RoundEnvironment env) {

        //获取带有InjectView注解的数据
        Set<? extends Element> injectViewSet = env.getElementsAnnotatedWith(InjectView.class);

        Map<TypeElement, Set<InjectViewEntity>> injectionsSetByClass = new LinkedHashMap<>();
        //遍历数据,把MainActivity等类的数据存储到injectionsByClass中
        for (Element element : injectViewSet) {
            Element enclosingElement = element.getEnclosingElement();
            //enclosingElement ==> com.stormful.android.annotationinject.MainActivity
            printLog("enclosingElement ==> %s", enclosingElement);

            //从缓存中获取注解数据
            Set<InjectViewEntity> injectVariables = injectionsSetByClass.get(enclosingElement);
            if (injectVariables == null) { //没保存过，需要保存
                injectVariables = new HashSet<>();
                injectionsSetByClass.put((TypeElement) enclosingElement, injectVariables);
            }

            String variableName = element.getSimpleName().toString();
            String type = element.asType().toString();
            int value = element.getAnnotation(InjectView.class).value();
            injectVariables.add(new InjectViewEntity(variableName, type, value));
            //变量名称：tvTest  类型：android.widget.TextView 值：2131165300
            printLog("变量名称：%s  类型：%s 值：%s", variableName, type, value);

        }


        //通过注解类的数据生成java文件
        Set<Map.Entry<TypeElement, Set<InjectViewEntity>>> InjectViewEntityEntries = injectionsSetByClass.entrySet();

        for (Map.Entry<TypeElement, Set<InjectViewEntity>> injectEntrySet : InjectViewEntityEntries) {
            TypeElement enclosingElement = injectEntrySet.getKey();
            //KEY--enclosingElement===>com.stormful.android.annotationinject.MainActivity
            printLog("KEY--enclosingElement===>%s", enclosingElement);
            String targetClassFullName = enclosingElement.getQualifiedName().toString();
            //com.stormful.android.annotationinject.MainActivity
            printLog("VALUE--getQualifiedName===>%s", targetClassFullName);
            int lastDotIndex = targetClassFullName.lastIndexOf(".");
            String activityType = targetClassFullName.substring(lastDotIndex + 1);
            //VALUE--activityType===>MainActivity
            printLog("VALUE--activityType===>%s", activityType);
            String className = activityType + InjectConstant.SUFFIX;
            printLog("VALUE--生成的类名===>%s", className);
            String packageName = targetClassFullName.substring(0, lastDotIndex);


            Set<InjectViewEntity> values = injectEntrySet.getValue();
            StringBuilder injectVies = new StringBuilder();
            for (InjectViewEntity viewEntity : values) {
                //activity.tvTest = (android.widget.TextView) activity.findViewById(2131165300);
                printLog("VALUE--viewEntity===>%s", viewEntity);
                injectVies.append(viewEntity).append("\n");
            }

            try {
                JavaFileObject classFile = mFiler.createSourceFile(packageName + "." + className, enclosingElement);
                Writer writer = classFile.openWriter();
                writer.write(String.format(InjectConstant.INJECTOR, packageName, className, activityType, injectVies.toString()));
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


}