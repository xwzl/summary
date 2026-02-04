package com.java.interview.java.design.creational;

/**
 * 抽象工厂模式 (Abstract Factory Pattern)
 *
 * 定义：抽象工厂模式是一种创建型设计模式，它能创建一系列相关的对象，
 * 而无需指定其具体类。它提供了一个创建一系列相关或相互依赖对象的接口，
 * 而无需指定它们具体的类。
 *
 * 主要角色：
 * 1. 抽象工厂(Abstract Factory)：声明了一组用于创建一族产品的方法
 * 2. 具体工厂(Concrete Factory)：实现了抽象工厂中定义的方法，用于创建具体的产品
 * 3. 抽象产品(Abstract Product)：为每种产品声明接口
 * 4. 具体产品(Concrete Product)：实现了抽象产品接口的具体产品
 *
 * 主要特点：
 * 1. 创建一系列相关的对象
 * 2. 不指定具体类
 * 3. 强调"产品族"的概念
 *
 * 应用场景：
 * - 一个系统要独立于它的产品的创建、组合和表示时
 * - 一个系统要由多个产品系列中的一个来配置时
 * - 当你要强调一系列相关的产品对象的设计以便进行联合使用时
 * - 当你提供一个产品类库，而只想显示它们的接口而不是实现时
 */
public class AbstractFactoryPattern {

    // 抽象产品 - 汽车
    interface Car {
        void start();
        String getName();
    }

    // 抽象产品 - 飞机
    interface Airplane {
        void fly();
        String getType();
    }

    // 具体产品 - 奔驰汽车
    static class BenzCar implements Car {
        @Override
        public void start() {
            System.out.println("Benz car is starting...");
        }

        @Override
        public String getName() {
            return "Benz Car";
        }
    }

    // 具体产品 - 宝马汽车
    static class BMWCar implements Car {
        @Override
        public void start() {
            System.out.println("BMW car is starting...");
        }

        @Override
        public String getName() {
            return "BMW Car";
        }
    }

    // 具体产品 - 波音飞机
    static class BoeingAirplane implements Airplane {
        @Override
        public void fly() {
            System.out.println("Boeing airplane is flying...");
        }

        @Override
        public String getType() {
            return "Boeing Airplane";
        }
    }

    // 具体产品 - 空客飞机
    static class AirbusAirplane implements Airplane {
        @Override
        public void fly() {
            System.out.println("Airbus airplane is flying...");
        }

        @Override
        public String getType() {
            return "Airbus Airplane";
        }
    }

    // 抽象工厂 - 交通工具工厂
    interface VehicleFactory {
        Car createCar();
        Airplane createAirplane();
    }

    // 具体工厂 - 德国交通工具工厂
    static class GermanVehicleFactory implements VehicleFactory {
        @Override
        public Car createCar() {
            return new BenzCar();  // 德国的奔驰汽车
        }

        @Override
        public Airplane createAirplane() {
            return new AirbusAirplane();  // 德国参与制造的空客飞机
        }
    }

    // 具体工厂 - 美国交通工具工厂
    static class AmericanVehicleFactory implements VehicleFactory {
        @Override
        public Car createCar() {
            return new BMWCar();  // 美国产的宝马汽车（假设）
        }

        @Override
        public Airplane createAirplane() {
            return new BoeingAirplane();  // 美国的波音飞机
        }
    }

    // 具体工厂 - 中国交通工具工厂
    static class ChineseVehicleFactory implements VehicleFactory {
        @Override
        public Car createCar() {
            // 这里可以创建中国的汽车品牌，如比亚迪、吉利等
            return new BenzCar();  // 简化示例
        }

        @Override
        public Airplane createAirplane() {
            // 这里可以创建中国的飞机品牌，如ARJ21、C919等
            return new BoeingAirplane();  // 简化示例
        }
    }

    // 产品族管理类
    static class VehicleManager {
        private VehicleFactory factory;

        public VehicleManager(VehicleFactory factory) {
            this.factory = factory;
        }

        public void produceVehicles() {
            Car car = factory.createCar();
            Airplane airplane = factory.createAirplane();

            System.out.println("Producing vehicles from: " + factory.getClass().getSimpleName());
            System.out.println("Car: " + car.getName());
            car.start();
            System.out.println("Airplane: " + airplane.getType());
            airplane.fly();
            System.out.println("------------------------");
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 使用德国工厂生产交通工具
        VehicleFactory germanFactory = new GermanVehicleFactory();
        VehicleManager germanManager = new VehicleManager(germanFactory);
        germanManager.produceVehicles();

        // 使用美国工厂生产交通工具
        VehicleFactory americanFactory = new AmericanVehicleFactory();
        VehicleManager americanManager = new VehicleManager(americanFactory);
        americanManager.produceVehicles();

        // 使用中国工厂生产交通工具
        VehicleFactory chineseFactory = new ChineseVehicleFactory();
        VehicleManager chineseManager = new VehicleManager(chineseFactory);
        chineseManager.produceVehicles();

        System.out.println("=== 抽象工厂模式的关键特性 ===");
        System.out.println("1. 创建一系列相关的产品对象");
        System.out.println("2. 产品族概念：同一工厂生产的不同产品属于同一族");
        System.out.println("3. 客户端只需要知道工厂类型，不需要知道具体产品类");
        System.out.println("4. 易于交换产品系列，因为具体工厂类在一个应用中只需要在初始化时出现一次");
        System.out.println("5. 保证同一产品族中的产品兼容");
    }
}
