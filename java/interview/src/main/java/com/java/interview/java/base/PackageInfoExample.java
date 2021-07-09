package com.java.interview.java.base;

import com.java.interview.java.base.packages.PackageInfoAnnotation;

import java.lang.annotation.Annotation;

/**
 *  只输出对应的包的注解，包内部的类是没有该注解的。
 *
 * @author xuweizhi
 * @since 2021/07/09 15:06
 */
public class PackageInfoExample {

    public static void main(String[] args) throws ClassNotFoundException {
        // 查看包注解
        String pkgName = "com.java.interview.java.base";
        Package pkg = Package.getPackage(pkgName);
        Annotation[] annotations = pkg.getAnnotations();
        for (Annotation an : annotations) {
            if (an instanceof PackageInfoAnnotation) {
                System.out.println("Hi,I'm the TestPkg");

            }
        }

        // 查看包下面对应的类的注解
        Class test = Class.forName("com.java.interview.java.base.GenericVO");
        Annotation[] annotations2 = test.getAnnotations();
        for (Annotation an : annotations2) {
            if (an instanceof PackageInfoAnnotation) {
                System.out.println("Hi,I'm the Class TestPkg");
            }
        }

        // 包类常量引用
        System.out.println(PackageConstant.TEST_01);
    }

}
