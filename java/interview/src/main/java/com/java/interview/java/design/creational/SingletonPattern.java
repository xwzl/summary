package com.java.interview.java.design.creational;

/**
 * 单例模式 (Singleton Pattern)
 *
 * 定义：单例模式是一种创建型设计模式，它确保一个类只有一个实例，
 * 并提供一个全局访问点来获取该实例。
 *
 * 主要特点：
 * 1. 确保一个类只有一个实例
 * 2. 提供全局访问点
 * 3. 延迟初始化（可选）
 *
 * 应用场景：
 * - 配置管理器
 * - 日志记录器
 * - 线程池
 * - 缓存
 * - 数据库连接池
 *
 * 实现方式包括：饿汉式、懒汉式、双重检查锁定、静态内部类、枚举等
 */
public class SingletonPattern {

    /**
     * 方法1：饿汉式（线程安全，但没有延迟加载）
     * 在类加载时就创建实例，无论是否被使用
     */
    public static class EagerSingleton {
        // 类加载时就创建实例
        private static final EagerSingleton INSTANCE = new EagerSingleton();

        // 私有构造函数，防止外部实例化
        private EagerSingleton() {}

        public static EagerSingleton getInstance() {
            return INSTANCE;
        }
    }

    /**
     * 方法2：懒汉式（线程不安全，有延迟加载）
     * 只在第一次调用getInstance()时创建实例
     */
    public static class LazySingleton {
        private static LazySingleton instance;

        private LazySingleton() {}

        public static LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    /**
     * 方法3：线程安全的懒汉式（同步方法）
     * 使用synchronized关键字保证线程安全，但性能较低
     */
    public static class ThreadSafeLazySingleton {
        private static ThreadSafeLazySingleton instance;

        private ThreadSafeLazySingleton() {}

        public static synchronized ThreadSafeLazySingleton getInstance() {
            if (instance == null) {
                instance = new ThreadSafeLazySingleton();
            }
            return instance;
        }
    }

    /**
     * 方法4：双重检查锁定（Double-Checked Locking）
     * 既保证线程安全又提高性能，是最常用的实现方式之一
     */
    public static class DoubleCheckedLockingSingleton {
        // volatile关键字确保多线程环境下的可见性和有序性
        private static volatile DoubleCheckedLockingSingleton instance;

        private DoubleCheckedLockingSingleton() {}

        public static DoubleCheckedLockingSingleton getInstance() {
            if (instance == null) {  // 第一次检查
                synchronized (DoubleCheckedLockingSingleton.class) {
                    if (instance == null) {  // 第二次检查
                        instance = new DoubleCheckedLockingSingleton();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 方法5：静态内部类（推荐）
     * 利用类加载机制保证线程安全，同时实现延迟加载
     */
    public static class StaticInnerClassSingleton {
        private StaticInnerClassSingleton() {}

        /**
         * 静态内部类，只有在被调用时才会被加载
         * JVM保证类加载过程是线程安全的
         */
        private static class SingletonHolder {
            private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
        }

        public static StaticInnerClassSingleton getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }

    /**
     * 方法6：枚举实现（最安全的方式）
     * 天然支持序列化和反序列化，防止反射攻击
     */
    public enum EnumSingleton {
        INSTANCE;

        public void doSomething() {
            System.out.println("Enum Singleton is doing something...");
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试饿汉式单例
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Eager Singleton: " + (eager1 == eager2)); // true

        // 测试双重检查锁定单例
        DoubleCheckedLockingSingleton dcl1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton dcl2 = DoubleCheckedLockingSingleton.getInstance();
        System.out.println("Double Checked Locking Singleton: " + (dcl1 == dcl2)); // true

        // 测试静态内部类单例
        StaticInnerClassSingleton static1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton static2 = StaticInnerClassSingleton.getInstance();
        System.out.println("Static Inner Class Singleton: " + (static1 == static2)); // true

        // 测试枚举单例
        EnumSingleton.INSTANCE.doSomething();
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        System.out.println("Enum Singleton: " + (enum1 == enum2)); // true
    }
}
