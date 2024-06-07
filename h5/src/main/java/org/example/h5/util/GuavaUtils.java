package org.example.h5.util;
import com.google.common.base.CaseFormat;
public class GuavaUtils {
    /**
     * 驼峰转数据库字段工具类
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("CaseFormat = " +transformToDatabaseFieldUpper("createTime"));
        System.out.println("CaseFormat = " +transformToDatabaseFieldLower("createTime"));
        System.out.println("CaseFormat = " +transformToDatabaseFieldUpper1("createTime"));

        System.out.println(String.format("%s>>>>>>%s","数据库字段转驼峰命名格式",CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"create_time")));
    }

    /**
     * 驼峰转UPPER_UNDERSCORE格式
     * @param name
     * @return
     */
    public static String transformToDatabaseFieldUpper(String name){
        //CaseFormat.LOWER_CAMEL 当前字符串本身格式  CaseFormat.UPPER_UNDERSCORE,模板格式
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,name);
    }

    /**
     * 驼峰转LOWER_UNDERSCORE格式
     * @param name
     * @return
     */
    public static String transformToDatabaseFieldLower(String name){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,name);
    }

    /**
     * 驼峰转LOWER_HYPHEN格式
     * @param name
     * @return
     */
    public static String transformToDatabaseFieldUpper1(String name){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN,name);
    }
}
