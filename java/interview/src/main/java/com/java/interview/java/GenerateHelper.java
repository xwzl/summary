package com.java.interview.java;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2022/05/31 11:32
 */
public class GenerateHelper {

    public static void main(String[] args) {
        String url = "jdbc:mysql://1.15.19.68:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
        String root = "root";
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, root, root))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();


    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
