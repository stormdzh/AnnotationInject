package com.stormful.android.compiler.drouter;

import java.util.Map;
import java.util.Set;

/**
 * @Description: 生成router的模板代码
 * @Author: dzh
 * @CreateDate: 2019-12-05 18:35
 */
public class DRouterConstant {


    //一下是需要生成的代码

    /**
     * package com.stormful.android.annotationinject;
     *
     * import java.util.Map;
     *
     * public class DRoute$$Router {
     *
     *     public void init(Map<String, String> maps) {
     *         maps.put("path", "activitypath");
     *         maps.put("path", "activitypath");
     *     }
     * }
     */

    public static String getDRouterCode(Map<String ,String> routes){

        String codeTop="package com.stormful.android.annotationinject;\n" +
                "\n" +
                "import com.stormful.android.api.drouter.IDRoute;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class DRoute$$Router implements IDRoute {\n" +
                "\n" +
                "    @Override\n" +
                "    public void init(Map<String, String> maps) {\n" ;

                //codetop后面需要加+
//                "        maps.put(\"path\", \"activitypath\");\n" +

        String codeBottom="    }\n" +
                "}\n" +
                "\n";

        StringBuilder stringBuilder=new StringBuilder();
        Set<Map.Entry<String, String>> entries =  routes.entrySet();

        for(Map.Entry<String, String> entrie:entries){
            stringBuilder.append(String.format(oneRoute,entrie.getKey(),entrie.getValue()));
        }

        return codeTop+stringBuilder.toString()+codeBottom;
    }

    public static String getDRouterCodeold(Map<String ,String> routes){

        String codeTop="package com.stormful.android.annotationinject;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class DRoute$$Router {\n" +
                "\n" +
                "    public void init(Map<String, String> maps) {\n";

                //codetop后面需要加+
//                "        maps.put(\"path\", \"activitypath\");\n" +

        String codeBottom="    }\n" +
                "}\n" +
                "\n";

        StringBuilder stringBuilder=new StringBuilder();
        Set<Map.Entry<String, String>> entries =  routes.entrySet();

        for(Map.Entry<String, String> entrie:entries){
            stringBuilder.append(String.format(oneRoute,entrie.getKey(),entrie.getValue()));
        }

        return codeTop+stringBuilder.toString()+codeBottom;
    }

    static String  oneRoute="        maps.put(\"%s\", \"%s\");\n" ;

}
