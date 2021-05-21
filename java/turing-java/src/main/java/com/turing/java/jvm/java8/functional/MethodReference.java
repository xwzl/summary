package com.turing.java.jvm.java8.functional;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class MethodReference {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public static void print() {
        System.out.println("静态方法");
    }

    public static MethodReference instance() {
        return new MethodReference();
    }


    public Integer gett(Object str, Integer integer) {
        setAge(integer);
        return getAge();
    }


    public Integer gett(int integer, Object str) {
        setAge(integer);
        return getAge();
    }

    @Test
    public void test() {
        //一个user实例
        MethodReference user = new MethodReference();

        //类型上的实例方法引用：ClassName::methodName
        Function<MethodReference, Integer> userIntegerFunction2 = MethodReference::getAge;

        //类型上的实例方法引用：ClassName::methodName
        Consumer<MethodReference> userConsumer2 = System.out::println;

        //实例上的实例方法引用：instanceReference::methodName
        Supplier<Integer> supplier = user::getAge;

        //类型上的静态方法引用：ClassName::methodName
        Supplier<MethodReference> userSupplier = MethodReference::instance;

        //类型上的静态方法引用：ClassName::methodName
        Print print = MethodReference::print;

        //超类实例上的实例方法引用：super::methodName
        Supplier<Class<?>> SupSupplier = super::getClass;

        //构造方法引用：ClassName::new
        Supplier<Object> newSupplier = MethodReference::new;

        //数组构造方法引用：TypeClassName[]::new
        Function<Integer, MethodReference[]> arrayFunction = MethodReference[]::new;

        //如果内部调用gett(String str, Object integer)方法
        ThFunction<MethodReference, Integer, Integer, String> thFunction1 = new ThFunction<MethodReference, Integer, Integer, String>() {
            @Override
            public Integer apply(MethodReference o, Integer o2, String o3) {
                return o.gett(o3, o2);
            }
        };

        //那么不能使用方法引用
        //因为虽然抽象方法的第一个参数就是内部调用该方法的实例，后面的参数和方法参数的顺序不一致
        //参数顺序不一致，即  o2 o3  ->  o3 o2
        ThFunction<MethodReference, Integer, Integer, String> thFunction2 = (o, o2, o3) -> o.gett(o3, o2);

        //如果内部调用gett(Integer integer, Object str)方法
        ThFunction<MethodReference, Integer, Integer, String> thFunction3 = new ThFunction<MethodReference, Integer, Integer, String>() {
            @Override
            public Integer apply(MethodReference o, Integer o2, String o3) {
                return o.gett(o2, o3);
            }
        };
        //那么能使用方法引用
        //因为抽象方法的第一个参数就是内部调用该方法的实例，后面的参数和方法参数的顺序一致，类型兼容
        //参数顺序一致，即  o2 o3  ->   o2 o3
        ThFunction<MethodReference, Integer, Integer, String> thFunction4 = MethodReference::gett;
    }


    /**
     * 自定义函数式接口，无参数无返回值
     */
    @FunctionalInterface
    public interface Print {
        /**
         * 输出
         */
        void print();
    }

    @FunctionalInterface
    public interface ThFunction<T, U, R, K> {
        R apply(T t, U u, K k);
    }
}
