package com.java.interview.java.design.creational;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型工厂方法模式 (Generic Factory Method Pattern)
 *
 * 定义：在传统工厂方法模式基础上引入泛型，使得工厂能够创建具有类型安全性的对象，
 * 避免类型转换错误，提高代码的健壮性和可维护性。
 *
 * 泛型工厂的主要优势：
 * 1. 类型安全：在编译时就能发现类型错误
 * 2. 减少类型转换：无需手动进行类型转换
 * 3. 代码复用：一个工厂可以处理多种类型
 * 4. 更好的API设计：清晰表达类型关系
 */
public class GenericFactoryMethodPattern {

    /**
     * 产品接口 - 定义产品的通用行为
     */
    interface Product {
        void use();
        String getName();
    }

    /**
     * 具体产品A
     */
    static class ConcreteProductA implements Product {
        private final String name;

        public ConcreteProductA() {
            this.name = "Product A";
        }

        public ConcreteProductA(String name) {
            this.name = name != null ? name : "Product A";
        }

        @Override
        public void use() {
            System.out.println("Using " + name);
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * 具体产品B
     */
    static class ConcreteProductB implements Product {
        private final String name;

        public ConcreteProductB() {
            this.name = "Product B";
        }

        public ConcreteProductB(String name) {
            this.name = name != null ? name : "Product B";
        }

        @Override
        public void use() {
            System.out.println("Using " + name);
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * 具体产品C
     */
    static class ConcreteProductC implements Product {
        private final String name;

        public ConcreteProductC() {
            this.name = "Product C";
        }

        public ConcreteProductC(String name) {
            this.name = name != null ? name : "Product C";
        }

        @Override
        public void use() {
            System.out.println("Using " + name);
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * 抽象工厂接口 - 使用泛型T来指定创建的产品类型
     * @param <T> 产品类型，必须实现Product接口
     */
    interface GenericFactory<T extends Product> {
        T createProduct();

        // 可以添加更多通用方法
        default void logCreation(T product) {
            System.out.println("Created product: " + product.getName());
        }
    }

    /**
     * 具体工厂A - 创建ProductA的工厂
     */
    static class ConcreteProductAFactory implements GenericFactory<ConcreteProductA> {
        @Override
        public ConcreteProductA createProduct() {
            return new ConcreteProductA();
        }
    }

    /**
     * 具体工厂B - 创建ProductB的工厂
     */
    static class ConcreteProductBFactory implements GenericFactory<ConcreteProductB> {
        @Override
        public ConcreteProductB createProduct() {
            return new ConcreteProductB();
        }
    }

    /**
     * 具体工厂C - 创建ProductC的工厂
     */
    static class ConcreteProductCFactory implements GenericFactory<ConcreteProductC> {
        @Override
        public ConcreteProductC createProduct() {
            return new ConcreteProductC();
        }
    }

    /**
     * 泛型工厂工具类 - 提供通用的工厂创建和管理功能
     */
    static class GenericFactoryUtil {
        // 使用Map存储不同类型的工厂，键为Class对象，值为对应的工厂
        private static final Map<Class<?>, GenericFactory<?>> factoryMap = new HashMap<>();

        static {
            // 注册默认工厂
            registerFactory(ConcreteProductA.class, new ConcreteProductAFactory());
            registerFactory(ConcreteProductB.class, new ConcreteProductBFactory());
            registerFactory(ConcreteProductC.class, new ConcreteProductCFactory());
        }

        /**
         * 注册工厂
         * @param productClass 产品类型
         * @param factory 对应的工厂
         * @param <T> 产品类型
         */
        public static <T extends Product> void registerFactory(Class<T> productClass, GenericFactory<T> factory) {
            factoryMap.put(productClass, factory);
        }

        /**
         * 获取指定类型的工厂
         * @param productClass 产品类型
         * @param <T> 产品类型
         * @return 对应的工厂
         */
        @SuppressWarnings("unchecked")
        public static <T extends Product> GenericFactory<T> getFactory(Class<T> productClass) {
            GenericFactory<?> factory = factoryMap.get(productClass);
            if (factory == null) {
                throw new IllegalArgumentException("No factory registered for: " + productClass.getName());
            }
            return (GenericFactory<T>) factory;
        }

        /**
         * 使用指定类型的工厂创建产品
         * @param productClass 产品类型
         * @param <T> 产品类型
         * @return 创建的产品实例
         */
        public static <T extends Product> T create(Class<T> productClass) {
            GenericFactory<T> factory = getFactory(productClass);
            T product = factory.createProduct();
            factory.logCreation(product);
            return product;
        }
    }

    /**
     * 类型安全的工厂管理者 - 使用泛型来管理特定类型的产品
     */
    static class TypeSafeProductManager<T extends Product> {
        private final GenericFactory<T> factory;

        public TypeSafeProductManager(GenericFactory<T> factory) {
            this.factory = factory;
        }

        public T createAndUseProduct() {
            T product = factory.createProduct();
            product.use();
            return product;
        }

        public void createAndManageMultipleProducts(int count) {
            for (int i = 0; i < count; i++) {
                T product = factory.createProduct();
                factory.logCreation(product);
                product.use();
            }
        }
    }

    /**
     * 字符串参数化的泛型工厂 - 可以根据字符串参数创建不同类型的产品
     */
    static class ParameterizedGenericFactory {
        @SuppressWarnings("unchecked")
        public static <T extends Product> T createProduct(Class<T> clazz, String... params) {
            try {
                if (clazz == ConcreteProductA.class) {
                    return (T) new ConcreteProductA(params.length > 0 ? params[0] : null);
                } else if (clazz == ConcreteProductB.class) {
                    return (T) new ConcreteProductB(params.length > 0 ? params[0] : null);
                } else if (clazz == ConcreteProductC.class) {
                    return (T) new ConcreteProductC(params.length > 0 ? params[0] : null);
                } else {
                    throw new IllegalArgumentException("Unsupported product type: " + clazz.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to create product: " + clazz.getName(), e);
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 泛型工厂方法模式演示 ===\n");

        // 1. 使用具体工厂创建产品
        System.out.println("1. 使用具体工厂创建产品:");
        GenericFactory<ConcreteProductA> factoryA = new ConcreteProductAFactory();
        ConcreteProductA productA = factoryA.createProduct();
        productA.use();

        GenericFactory<ConcreteProductB> factoryB = new ConcreteProductBFactory();
        ConcreteProductB productB = factoryB.createProduct();
        productB.use();
        System.out.println();

        // 2. 使用泛型工厂工具类
        System.out.println("2. 使用泛型工厂工具类:");
        ConcreteProductA utilProductA = GenericFactoryUtil.create(ConcreteProductA.class);
        ConcreteProductB utilProductB = GenericFactoryUtil.create(ConcreteProductB.class);
        ConcreteProductC utilProductC = GenericFactoryUtil.create(ConcreteProductC.class);
        System.out.println();

        // 3. 使用类型安全的产品管理者
        System.out.println("3. 使用类型安全的产品管理者:");
        TypeSafeProductManager<ConcreteProductA> managerA =
            new TypeSafeProductManager<>(new ConcreteProductAFactory());
        managerA.createAndUseProduct();

        TypeSafeProductManager<ConcreteProductB> managerB =
            new TypeSafeProductManager<>(new ConcreteProductBFactory());
        managerB.createAndManageMultipleProducts(2);
        System.out.println();

        // 4. 使用参数化的泛型工厂
        System.out.println("4. 使用参数化的泛型工厂:");
        ConcreteProductA namedProductA = ParameterizedGenericFactory.createProduct(
            ConcreteProductA.class, "Special Product A");
        namedProductA.use();

        ConcreteProductB namedProductB = ParameterizedGenericFactory.createProduct(
            ConcreteProductB.class, "Custom Product B");
        namedProductB.use();
        System.out.println();

        // 5. 演示类型安全优势
        System.out.println("5. 演示类型安全优势:");
        // 由于使用了泛型，以下操作在编译时就是类型安全的
        GenericFactory<ConcreteProductA> typedFactory = new ConcreteProductAFactory();
        ConcreteProductA typedProduct = typedFactory.createProduct(); // 无需类型转换

        // 不能错误地将ProductB赋值给ProductA变量，这是类型安全的体现
        System.out.println("类型安全验证 - 正确创建了: " + typedProduct.getName());
        System.out.println();

        System.out.println("=== 泛型工厂模式的关键特性 ===");
        System.out.println("1. 类型安全：编译时检查类型错误");
        System.out.println("2. 减少强制类型转换：直接获得正确的类型");
        System.out.println("3. 代码复用：单一工厂接口处理多种类型");
        System.out.println("4. 更好的API设计：清晰的类型关系表达");
        System.out.println("5. 运行时类型信息：结合Class对象进行动态创建");
    }
}
