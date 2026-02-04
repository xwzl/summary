package com.java.interview.java.design.creational;

/**
 * 工厂方法模式 (Factory Method Pattern)
 *
 * 定义：工厂方法模式是一种创建型设计模式，它定义了一个创建对象的接口，
 * 但让实现这个接口的类来决定实例化哪个类。工厂方法使一个类的实例化延迟到其子类。
 *
 * 主要角色：
 * 1. 抽象产品(Product)：定义产品的公共接口
 * 2. 具体产品(Concrete Product)：实现抽象产品接口的具体产品类
 * 3. 抽象工厂(Creator)：声明工厂方法，返回一个产品对象
 * 4. 具体工厂(Concrete Creator)：重写工厂方法以返回具体产品实例
 *
 * 主要特点：
 * 1. 将对象的创建委托给子类
 * 2. 遵循开闭原则
 * 3. 创建对象而不指定具体类
 *
 * 应用场景：
 * - 当一个类不知道它所必须创建的对象的类的时候
 * - 当一个类希望由它的子类来指定它所创建的对象的时候
 * - 当类将创建对象的职责委托给多个帮助子类中的某一个，并且你希望将哪一个帮助子类是代理者这一信息局部化的时候
 */
public class FactoryMethodPattern {

    // 抽象产品 - 汽车接口
    interface Car {
        void drive();
    }

    // 具体产品 - 奔驰汽车
    static class BenzCar implements Car {
        @Override
        public void drive() {
            System.out.println("Driving Benz car...");
        }
    }

    // 具体产品 - 宝马汽车
    static class BMWCar implements Car {
        @Override
        public void drive() {
            System.out.println("Driving BMW car...");
        }
    }

    // 具体产品 - 奥迪汽车
    static class AudiCar implements Car {
        @Override
        public void drive() {
            System.out.println("Driving Audi car...");
        }
    }

    // 抽象工厂 - 汽车工厂接口
    abstract static class CarFactory {
        // 工厂方法，子类需要实现此方法来创建具体产品
        public abstract Car createCar();

        // 可以有其他通用方法
        public void introduceCar() {
            Car car = createCar();
            System.out.println("Created a new car:");
            car.drive();
        }
    }

    // 具体工厂 - 奔驰汽车工厂
    static class BenzCarFactory extends CarFactory {
        @Override
        public Car createCar() {
            return new BenzCar();
        }
    }

    // 具体工厂 - 宝马汽车工厂
    static class BMWCarFactory extends CarFactory {
        @Override
        public Car createCar() {
            return new BMWCar();
        }
    }

    // 具体工厂 - 奥迪汽车工厂
    static class AudiCarFactory extends CarFactory {
        @Override
        public Car createCar() {
            return new AudiCar();
        }
    }

    // 不同类型的汽车工厂
    static class LuxuryCarFactory extends CarFactory {
        @Override
        public Car createCar() {
            String carType = System.getProperty("car.type");
            if ("benz".equalsIgnoreCase(carType)) {
                return new BenzCar();
            } else if ("bmw".equalsIgnoreCase(carType)) {
                return new BMWCar();
            } else if ("audi".equalsIgnoreCase(carType)) {
                return new AudiCar();
            } else {
                return new BenzCar(); // 默认创建奔驰
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 使用奔驰汽车工厂创建奔驰汽车
        CarFactory benzFactory = new BenzCarFactory();
        Car benzCar = benzFactory.createCar();
        benzCar.drive(); // 输出: Driving Benz car...
        benzFactory.introduceCar(); // 输出: Created a new car: \n Driving Benz car...

        // 使用宝马汽车工厂创建宝马汽车
        CarFactory bmwFactory = new BMWCarFactory();
        Car bmwCar = bmwFactory.createCar();
        bmwCar.drive(); // 输出: Driving BMW car...

        // 使用奥迪汽车工厂创建奥迪汽车
        CarFactory audiFactory = new AudiCarFactory();
        Car audiCar = audiFactory.createCar();
        audiCar.drive(); // 输出: Driving Audi car...

        // 使用豪华汽车工厂（基于系统属性）
        System.setProperty("car.type", "bmw");
        CarFactory luxuryFactory = new LuxuryCarFactory();
        Car luxuryCar = luxuryFactory.createCar();
        luxuryCar.drive(); // 输出: Driving BMW car...

        System.out.println("\n=== 使用工厂方法模式的优势 ===");
        System.out.println("1. 创建对象的过程被封装，客户端不需要知道具体创建细节");
        System.out.println("2. 符合开闭原则，增加新产品只需扩展工厂类");
        System.out.println("3. 客户端与具体产品解耦，只依赖抽象产品");
    }
}
