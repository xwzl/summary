package com.summary.security.utils;

/**
 * Mysql util
 *
 * @author xuweizhi
 * @since 2022/01/11 20:37
 */
@SuppressWarnings("all")
public class GeneratorUtil {

    public static void main(String[] args) {
        generatorSql();
    }

    public static void generatorSql() {
//        FastAutoGenerator.create("jdbc:mysql://123.57.107.76:3306/security?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true", "root", "root")
//                .globalConfig(builder -> {
//                    builder.author("xuweizhi") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("/Users/xuweizhi/Documents/root/summary/spring-cloud/spring-cloud-security/src/main/java/com/summary/security"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.summary.security") // 设置父包名
//                            .moduleName("test") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/xuweizhi/Documents/root/summary/spring-cloud/spring-cloud-security/src/main/java/com/summary/security")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("user", "role", "user_role", "permission", "role_permission");
//                })
//                .execute();
    }
}
