package com.java.interview.java.base.packages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package-info 注解测试
 *
 * 1. 给包提供注释 {@link com.java.interview.java.base.PackageInfoExample}
 * 2. 如果一整个包都是过时的话，可以直接在package-info.java下面，添加注解@Deprecated，表示该源码包已过时。
 * 3. 提供包级别变量:如果想在包里面使用对应的变量，而不想让其他包使用，就可以将变量放到package-info.java下面，实现分包自用的理念。
 * 4. 使用JavaDoc的时候，通过在package-info.java添加注释，生成JavaDoc实现对应包的注释说明。
 *
 * @author xuweizhi
 * @since 2021/07/09 15:03
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageInfoAnnotation {
}
