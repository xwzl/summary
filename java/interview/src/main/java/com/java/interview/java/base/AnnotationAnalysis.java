package com.java.interview.java.base;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解机制详解
 *
 * @author xuweizhi
 * @since 2021/04/29 13:53
 */
@SuppressWarnings("all")
public class AnnotationAnalysis {

    /**
     * 注解是JDK1.5版本开始引入的一个特性，用于对代码进行说明，可以对包、类、接口、字段、方法参数、局部变量等进行注解。它主要的作用有以下四方面：
     * <ul>
     *     <li>生成文档，通过代码里标识的元数据生成javadoc文档。</li>
     *     <li>编译检查，通过代码里标识的元数据让编译器在编译期间进行检查验证。</li>
     *     <li>编译时动态处理，编译时通过代码里标识的元数据动态处理，例如动态生成代码。</li>
     *     <li>运行时动态处理，运行时通过代码里标识的元数据动态处理，例如使用反射注入实例。 </li>
     * </ul>
     *  这么来说是比较抽象的，我们具体看下注解的常见分类：
     *  <ul>
     *      <li>Java自带的标准注解，包括@Override、@Deprecated和@SuppressWarnings，分别用于标明重写某个方法、标明某个类或方法过时、标明要忽略的警告，用这些注解标明后编译器就会进行检查。</li>
     *      <li>元注解，元注解是用于定义注解的注解，包括@Retention、@Target、@Inherited、@Documented，@Retention用于标明注解被保留的阶段，@Target用于标明注解使用的范围，@Inherited用于标明注解可继承，@Documented用于标明是否生成javadoc文档。</li>
     *      <li>自定义注解，可以根据自己的需求定义注解，并可用元注解对自定义注解进行注解。 接下来我们通过这个分类角度来理解注解。</li>
     *  </ul>
     */
    public @interface AnnotationModel {

    }

    class A {
        public void test() {

        }
    }

    class B extends A {

        /**
         * 重载父类的test方法
         */
        @Override
        public void test() {
        }

        /**
         * 被弃用的方法
         */
        @Deprecated
        public void oldMethod() {
        }

        /**
         * 忽略告警
         *
         * @return
         */
        @SuppressWarnings("rawtypes")
        public List processList() {
            List list = new ArrayList();
            System.out.println("");
            return list;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SourcePolicy {

    }

    @Retention(RetentionPolicy.CLASS)
    public @interface ClassPolicy {

    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface RuntimePolicy {

    }

    public class RetentionTest {

        @SourcePolicy
        public void sourcePolicy() {
        }

        @ClassPolicy
        public void classPolicy() {
        }

        @RuntimePolicy
        public void runtimePolicy() {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authority {
        String role();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Authorities {
        Authority[] value();
    }

    /**
     * jdk 8 之前重复注解的使用
     */
    public class RepeatAnnotationUseOldVersion {
        @Authorities({@Authority(role = "Admin"), @Authority(role = "Manager")})
        public void doSomeThing() {
        }
    }


    /**
     * jdk 8 重复注解的使用，把 Student 与 School 注解做关联
     */
    @Repeatable(School.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Student {
        String studentName();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface School {
        Student[] value();
    }

    public class RepeatAnnotationUseNewVersion {
        @Student(studentName = "Admin")
        @Student(studentName = "Manager")
        public void doSomeThing() {
        }
    }

    /**
     * 定义注解后，如何获取注解中的内容呢？反射包java.lang.reflect下的AnnotatedElement接口提供这些方法。
     * <p>
     * 这里注意：只有注解被定义为RUNTIME后，该注解才能是运行时可见，当class文件被装载时被保存在class文件中的Annotation才会被虚拟机读取。
     */
    public static class RepeatableTest {

        @Test
        public void test() throws NoSuchMethodException {
            // jdk 8 前重复注解的书写方法，类似于 JSON 数据的嵌套方式
            Class<RepeatAnnotationUseOldVersion> old = RepeatAnnotationUseOldVersion.class;
            Method method = old.getMethod("doSomeThing");

            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                System.out.println(declaredAnnotation);
            }

            Class<RepeatAnnotationUseNewVersion> newReaptable = RepeatAnnotationUseNewVersion.class;
            Method doSomeThing = newReaptable.getMethod("doSomeThing");
            for (Annotation annotation : doSomeThing.getDeclaredAnnotations()) {
                if (annotation instanceof School) {
                    School school = (School) annotation;
                    Student[] value = school.value();
                    for (Student student : value) {
                        System.out.println(student.studentName());
                    }
                }
            }
        }

        @Test
        public void annationTest() {
            try {
                // 获取所有methods
                Method[] methods = TestMethodAnnotation.class.getMethods();

                // 遍历
                for (Method method : methods) {
                    // 方法上是否有MyMethodAnnotation注解
                    if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                        try {
                            // 获取并遍历方法上的所有注解
                            for (Annotation anno : method.getDeclaredAnnotations()) {
                                System.out.println("Annotation in Method '"
                                        + method + "' : " + anno);
                            }

                            // 获取MyMethodAnnotation对象信息
                            MyMethodAnnotation methodAnno = method
                                    .getAnnotation(MyMethodAnnotation.class);

                            System.out.println(methodAnno.title());

                        } catch (Throwable ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyMethodAnnotation {

        public String title() default "";

        public String description() default "";

    }

    public static class TestMethodAnnotation {

        @Override
        @MyMethodAnnotation(title = "toStringMethod", description = "override toString method")
        public String toString() {
            return "Override toString method";
        }

        @Deprecated
        @MyMethodAnnotation(title = "old static method", description = "deprecated old static method")
        public static void oldMethod() {
            System.out.println("old method, don't use it.");
        }

        @SuppressWarnings({"unchecked", "deprecation"})
        @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
        public static void genericsTest() throws FileNotFoundException {
            List l = new ArrayList();
            l.add("abc");
            oldMethod();
        }
    }


}
