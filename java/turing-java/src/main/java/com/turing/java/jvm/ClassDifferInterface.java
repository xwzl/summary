package com.turing.java.jvm;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * 类与接口的不同
 *
 * @author xuweizhi
 * @since 2020/08/03 21:10
 */
public class ClassDifferInterface {


    @Test
    public void initInterface() {
        System.out.println(CatFamily.p);
    }

    /**
     * -XX:+TraceClassLoading 打印类加载信息
     */
    @Test
    public void initImplements() {
        System.out.println(Cat.race);
        System.out.println("并未打印出 Cat 类被加载的信息，因为 Cat.race 直接被编译为常量存储在这一块代码中");
        System.out.println(Cat.age);
        System.out.println("虽然 Cat 被初始化，但是其父接口依然没有被初始化");
    }

    /**
     * 如果子类直接调用父类的静态变量，子类是不会被初始化的
     */
    @Test
    public void initChildClass() {
        System.out.println(LittleCat.name);
        //System.out.println(LittleCat.p);
        LittleCat littleCat = new LittleCat();
        // 由此可见，父接口初始化了，其子接口未被调用，一样也不会被初始化
        littleCat.print();
    }
}

interface Zoo {

    Thread zoo = new Thread() {
        {
            System.out.println("父接口初始化时，只有当前接口或者变量被调用才会初始化");
        }
    };

    default void print() {
        System.out.println("hello");
    }
}

interface CatFamily extends Zoo {

    /**
     * 必须使用编译器确定值，否则 CatFamily 不会被初始化。
     */
    String p = UUID.randomUUID().toString();

    Thread cat = new Thread() {
        {
            System.out.println("子接口的调用并不会初始化父接口");
            System.out.println("实现了主动调用方法的实现时，会初始化接口");
        }
    };

}

class Cat implements CatFamily {

    /**
     * 对于静态常量来说，直接编译进调用该变量的代码内，可以直接认为就是一个字符串。
     */
    public static final String race = "猫科动物";

    /**
     * 对于编译器不可以的确定的常量，运行期才能确定的变量，使用该变量会导致类的初始化。
     */
    public static final int age = new Random(10).nextInt();

    /**
     * 对于静态变量来说，属于主动使用的一类情况，因此会导致类的初始话。
     */
    public static String description = "它是一种猫科动物";

    static {
        System.out.println("对类的主动使用会导致类的初始化");
    }
}

class LittleCat extends Cat {

    public static String name = "小猫咪";

    static {
        System.out.println("对于类来说，子类的主动使用会导致父类的初始化");
        System.out.println("对于接口来说，子类的主动使用不会导致父接口的初始化");
    }
}
