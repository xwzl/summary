package com.java.interview.java.design.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 泛型工厂方法模式 - 汽车工厂示例 (Generic Factory Method Pattern)
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
public class GenericCarFactoryPattern {

    /**
     * 产品接口 - 汽车
     */
    interface Car {
        void drive();
        String getBrand();
        String getModel();
    }

    /**
     * 具体产品 - 奔驰汽车
     */
    static class BenzCar implements Car {
        private final String model;

        public BenzCar() {
            this.model = "C-Class";
        }

        public BenzCar(String model) {
            this.model = model != null ? model : "C-Class";
        }

        @Override
        public void drive() {
            System.out.println("Driving Benz " + model + "...");
        }

        @Override
        public String getBrand() {
            return "Benz";
        }

        @Override
        public String getModel() {
            return model;
        }
    }

    /**
     * 具体产品 - 宝马汽车
     */
    static class BMWCar implements Car {
        private final String model;

        public BMWCar() {
            this.model = "X5";
        }

        public BMWCar(String model) {
            this.model = model != null ? model : "X5";
        }

        @Override
        public void drive() {
            System.out.println("Driving BMW " + model + "...");
        }

        @Override
        public String getBrand() {
            return "BMW";
        }

        @Override
        public String getModel() {
            return model;
        }
    }

    /**
     * 具体产品 - 奥迪汽车
     */
    static class AudiCar implements Car {
        private final String model;

        public AudiCar() {
            this.model = "A4";
        }

        public AudiCar(String model) {
            this.model = model != null ? model : "A4";
        }

        @Override
        public void drive() {
            System.out.println("Driving Audi " + model + "...");
        }

        @Override
        public String getBrand() {
            return "Audi";
        }

        @Override
        public String getModel() {
            return model;
        }
    }

    /**
     * 泛型工厂接口 - 创建任意类型汽车的工厂
     * @param <T> 汽车类型，必须实现Car接口
     */
    interface CarFactory<T extends Car> {
        T createCar();

        // 默认方法：记录创建日志
        default void logCreation(T car) {
            System.out.println("Created car: " + car.getBrand() + " " + car.getModel());
        }

        // 默认方法：创建并使用汽车
        default void createAndUseCar() {
            T car = createCar();
            logCreation(car);
            car.drive();
        }
    }

    /**
     * 具体工厂 - 奔驰汽车工厂
     */
    static class BenzCarFactory implements CarFactory<BenzCar> {
        private final String model;

        public BenzCarFactory() {
            this.model = "C-Class";
        }

        public BenzCarFactory(String model) {
            this.model = model;
        }

        @Override
        public BenzCar createCar() {
            return new BenzCar(model);
        }
    }

    /**
     * 具体工厂 - 宝马汽车工厂
     */
    static class BMWCarFactory implements CarFactory<BMWCar> {
        private final String model;

        public BMWCarFactory() {
            this.model = "X5";
        }

        public BMWCarFactory(String model) {
            this.model = model;
        }

        @Override
        public BMWCar createCar() {
            return new BMWCar(model);
        }
    }

    /**
     * 具体工厂 - 奥迪汽车工厂
     */
    static class AudiCarFactory implements CarFactory<AudiCar> {
        private final String model;

        public AudiCarFactory() {
            this.model = "A4";
        }

        public AudiCarFactory(String model) {
            this.model = model;
        }

        @Override
        public AudiCar createCar() {
            return new AudiCar(model);
        }
    }

    /**
     * 泛型汽车工厂注册器 - 管理不同类型汽车工厂的注册和获取
     */
    static class CarFactoryRegistry {
        // 使用Map存储不同类型的汽车工厂
        private static final Map<Class<? extends Car>, CarFactory<?>> factoryMap = new HashMap<>();

        static {
            // 预注册常用工厂
            registerFactory(BenzCar.class, new BenzCarFactory());
            registerFactory(BMWCar.class, new BMWCarFactory());
            registerFactory(AudiCar.class, new AudiCarFactory());
        }

        /**
         * 注册汽车工厂
         * @param carClass 汽车类型
         * @param factory 对应的工厂
         * @param <T> 汽车类型
         */
        public static <T extends Car> void registerFactory(Class<T> carClass, CarFactory<T> factory) {
            factoryMap.put(carClass, factory);
        }

        /**
         * 获取指定类型的汽车工厂
         * @param carClass 汽车类型
         * @param <T> 汽车类型
         * @return 对应的工厂
         */
        @SuppressWarnings("unchecked")
        public static <T extends Car> CarFactory<T> getFactory(Class<T> carClass) {
            CarFactory<?> factory = factoryMap.get(carClass);
            if (factory == null) {
                throw new IllegalArgumentException("No factory registered for: " + carClass.getSimpleName());
            }
            return (CarFactory<T>) factory;
        }

        /**
         * 使用指定类型的工厂创建汽车
         * @param carClass 汽车类型
         * @param <T> 汽车类型
         * @return 创建的汽车实例
         */
        public static <T extends Car> T createCar(Class<T> carClass) {
            CarFactory<T> factory = getFactory(carClass);
            T car = factory.createCar();
            factory.logCreation(car);
            return car;
        }
    }

    /**
     * 类型安全的汽车经销商 - 使用泛型来管理特定类型的汽车
     */
    static class TypeSafeCarDealer<T extends Car> {
        private final CarFactory<T> factory;

        public TypeSafeCarDealer(CarFactory<T> factory) {
            this.factory = factory;
        }

        public T sellCar() {
            T car = factory.createCar();
            factory.logCreation(car);
            System.out.println("Selling a " + car.getBrand() + " " + car.getModel());
            return car;
        }

        public void sellMultipleCars(int count) {
            for (int i = 0; i < count; i++) {
                T car = factory.createCar();
                factory.logCreation(car);
                System.out.println("Sold car #" + (i + 1) + ": " + car.getBrand() + " " + car.getModel());
            }
        }
    }

    /**
     * 参数化泛型汽车工厂 - 根据参数创建不同类型汽车
     */
    static class ParametricCarFactory {
        @SuppressWarnings("unchecked")
        public static <T extends Car> T createCar(Class<T> clazz, String model) {
            try {
                if (clazz == BenzCar.class) {
                    return (T) new BenzCar(model);
                } else if (clazz == BMWCar.class) {
                    return (T) new BMWCar(model);
                } else if (clazz == AudiCar.class) {
                    return (T) new AudiCar(model);
                } else {
                    throw new IllegalArgumentException("Unsupported car type: " + clazz.getSimpleName());
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to create car: " + clazz.getSimpleName(), e);
            }
        }

        // 使用Supplier创建汽车的工厂方法
        public static <T extends Car> T createCarWithSupplier(Supplier<T> supplier) {
            T car = supplier.get();
            System.out.println("Created car via supplier: " + car.getBrand() + " " + car.getModel());
            return car;
        }
    }

    /**
     * 汽车生产线 - 展示泛型工厂在生产线上的应用
     */
    static class CarProductionLine<T extends Car> {
        private final CarFactory<T> factory;
        private final int productionCapacity;

        public CarProductionLine(CarFactory<T> factory, int productionCapacity) {
            this.factory = factory;
            this.productionCapacity = productionCapacity;
        }

        public void startProduction() {
            System.out.println("Starting production line for " +
                factory.getClass().getSimpleName() + ", capacity: " + productionCapacity);

            for (int i = 0; i < productionCapacity; i++) {
                T car = factory.createCar();
                System.out.println("Produced car #" + (i + 1) + ": " +
                    car.getBrand() + " " + car.getModel());
            }

            System.out.println("Production line completed.\n");
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 泛型工厂方法模式 - 汽车工厂示例 ===\n");

        // 1. 使用具体工厂创建汽车
        System.out.println("1. 使用具体工厂创建汽车:");
        CarFactory<BenzCar> benzFactory = new BenzCarFactory("S-Class");
        BenzCar benzCar = benzFactory.createCar();
        benzCar.drive();

        CarFactory<BMWCar> bmwFactory = new BMWCarFactory("X7");
        BMWCar bmwCar = bmwFactory.createCar();
        bmwCar.drive();
        System.out.println();

        // 2. 使用工厂的默认方法
        System.out.println("2. 使用工厂的默认方法:");
        CarFactory<AudiCar> audiFactory = new AudiCarFactory("A8");
        audiFactory.createAndUseCar(); // 使用默认方法创建并使用汽车
        System.out.println();

        // 3. 使用汽车工厂注册器
        System.out.println("3. 使用汽车工厂注册器:");
        BenzCar registryBenz = CarFactoryRegistry.createCar(BenzCar.class);
        BMWCar registryBMW = CarFactoryRegistry.createCar(BMWCar.class);
        AudiCar registryAudi = CarFactoryRegistry.createCar(AudiCar.class);
        System.out.println();

        // 4. 使用类型安全的汽车经销商
        System.out.println("4. 使用类型安全的汽车经销商:");
        TypeSafeCarDealer<BenzCar> benzDealer = new TypeSafeCarDealer<>(new BenzCarFactory("GLE"));
        BenzCar soldBenz = benzDealer.sellCar();

        TypeSafeCarDealer<BMWCar> bmwDealer = new TypeSafeCarDealer<>(new BMWCarFactory("iX"));
        bmwDealer.sellMultipleCars(2);
        System.out.println();

        // 5. 使用参数化工厂
        System.out.println("5. 使用参数化工厂:");
        BenzCar paramBenz = ParametricCarFactory.createCar(BenzCar.class, "AMG GT");
        paramBenz.drive();

        BMWCar paramBMW = ParametricCarFactory.createCar(BMWCar.class, "M5");
        paramBMW.drive();
        System.out.println();

        // 6. 使用Supplier创建汽车
        System.out.println("6. 使用Supplier创建汽车:");
        BenzCar supplierBenz = ParametricCarFactory.createCarWithSupplier(() -> new BenzCar("EQS"));
        BMWCar supplierBMW = ParametricCarFactory.createCarWithSupplier(() -> new BMWCar("i4"));
        System.out.println();

        // 7. 汽车生产线示例
        System.out.println("7. 汽车生产线示例:");
        CarProductionLine<BenzCar> benzLine = new CarProductionLine<>(new BenzCarFactory("CLA"), 3);
        benzLine.startProduction();

        CarProductionLine<BMWCar> bmwLine = new CarProductionLine<>(new BMWCarFactory("Z4"), 2);
        bmwLine.startProduction();

        // 8. 演示类型安全优势
        System.out.println("8. 演示类型安全优势:");
        // 由于使用了泛型，以下操作在编译时就是类型安全的
        CarFactory<BenzCar> typedBenzFactory = new BenzCarFactory("SL");
        BenzCar typedBenzCar = typedBenzFactory.createCar(); // 无需类型转换

        // 不能错误地将BMWCar赋值给BenzCar变量，这是类型安全的体现
        System.out.println("类型安全验证 - 正确创建了: " + typedBenzCar.getBrand() + " " + typedBenzCar.getModel());
        System.out.println();

        System.out.println("=== 泛型工厂模式的关键特性 ===");
        System.out.println("1. 类型安全：编译时检查类型错误，避免运行时ClassCastException");
        System.out.println("2. 减少强制类型转换：直接获得正确的类型");
        System.out.println("3. 代码复用：单一工厂接口处理多种类型");
        System.out.println("4. 更好的API设计：清晰的类型关系表达");
        System.out.println("5. 运行时类型信息：结合Class对象进行动态创建");
        System.out.println("6. 灵活性：支持参数化创建和Supplier模式");
    }
}
