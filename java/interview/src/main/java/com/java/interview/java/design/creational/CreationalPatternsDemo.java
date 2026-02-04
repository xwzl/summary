package com.java.interview.java.design.creational;

/**
 * 创建型设计模式综合演示类
 *
 * 本类演示了所有创建型设计模式的实际应用
 * 创建型设计模式关注对象的创建过程，旨在使对象的创建与使用相分离
 *
 * 包含的创建型模式：
 * 1. 单例模式 (Singleton Pattern)
 * 2. 工厂方法模式 (Factory Method Pattern)
 * 3. 泛型工厂方法模式 (Generic Factory Method Pattern)
 * 4. 抽象工厂模式 (Abstract Factory Pattern)
 * 5. 建造者模式 (Builder Pattern)
 * 6. 原型模式 (Prototype Pattern)
 */
public class CreationalPatternsDemo {

    public static void main(String[] args) {
        System.out.println("=== 创建型设计模式综合演示 ===\n");

        // 1. 单例模式演示
        demonstrateSingletonPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 2. 工厂方法模式演示
        demonstrateFactoryMethodPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 3. 泛型工厂方法模式演示
        demonstrateGenericFactoryMethodPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 4. 抽象工厂模式演示
        demonstrateAbstractFactoryPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 5. 建造者模式演示
        demonstrateBuilderPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 6. 原型模式演示
        demonstratePrototypePattern();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("创建型设计模式总结:");
        System.out.println("1. 单例模式: 确保类只有一个实例，提供全局访问点");
        System.out.println("2. 工厂方法模式: 定义创建对象的接口，由子类决定实例化哪个类");
        System.out.println("3. 泛型工厂方法模式: 使用泛型增强类型安全性的工厂方法模式");
        System.out.println("4. 抽象工厂模式: 创建一系列相关或相互依赖的对象");
        System.out.println("5. 建造者模式: 分步骤创建复杂对象，可以改变内部表示");
        System.out.println("6. 原型模式: 通过复制现有对象创建新对象，无需依赖具体类");
    }

    /**
     * 演示单例模式
     */
    private static void demonstrateSingletonPattern() {
        System.out.println("1. 单例模式 (Singleton Pattern)");
        System.out.println("   目的: 确保一个类只有一个实例，并提供全局访问点");

        // 测试静态内部类单例
        SingletonPattern.StaticInnerClassSingleton instance1 =
            SingletonPattern.StaticInnerClassSingleton.getInstance();
        SingletonPattern.StaticInnerClassSingleton instance2 =
            SingletonPattern.StaticInnerClassSingleton.getInstance();

        System.out.println("   静态内部类单例 - 两次获取实例是否相同: " + (instance1 == instance2));

        // 测试枚举单例
        SingletonPattern.EnumSingleton.INSTANCE.doSomething();
        System.out.println("   枚举单例 - 两次获取实例是否相同: " +
            (SingletonPattern.EnumSingleton.INSTANCE == SingletonPattern.EnumSingleton.INSTANCE));
    }

    /**
     * 演示泛型工厂方法模式
     */
    private static void demonstrateGenericFactoryMethodPattern() {
        System.out.println("3. 泛型工厂方法模式 (Generic Factory Method Pattern)");
        System.out.println("   目的: 使用泛型增强工厂方法模式的类型安全性");

        // 使用具体泛型工厂创建汽车
        GenericCarFactoryPattern.CarFactory<GenericCarFactoryPattern.BenzCar> benzFactory = 
            new GenericCarFactoryPattern.BenzCarFactory("S-Class");
        GenericCarFactoryPattern.BenzCar benzCar = benzFactory.createCar();
        System.out.print("   ");
        benzCar.drive();

        GenericCarFactoryPattern.CarFactory<GenericCarFactoryPattern.BMWCar> bmwFactory = 
            new GenericCarFactoryPattern.BMWCarFactory("X7");
        GenericCarFactoryPattern.BMWCar bmwCar = bmwFactory.createCar();
        System.out.print("   ");
        bmwCar.drive();

        // 使用泛型工厂工具类
        GenericCarFactoryPattern.BenzCar utilBenz = 
            GenericCarFactoryPattern.CarFactoryRegistry.createCar(GenericCarFactoryPattern.BenzCar.class);
        System.out.print("   ");
        utilBenz.drive();

        // 演示类型安全优势
        System.out.println("   类型安全验证: 泛型确保了创建的类型一致性");
    }

    /**
     * 演示工厂方法模式
     */
    private static void demonstrateFactoryMethodPattern() {
        System.out.println("2. 工厂方法模式 (Factory Method Pattern)");
        System.out.println("   目的: 定义创建对象的接口，让子类决定实例化哪个类");

        // 使用不同工厂创建不同汽车
        FactoryMethodPattern.CarFactory benzFactory = new FactoryMethodPattern.BenzCarFactory();
        FactoryMethodPattern.Car benzCar = benzFactory.createCar();
        System.out.print("   ");
        benzCar.drive();

        FactoryMethodPattern.CarFactory bmwFactory = new FactoryMethodPattern.BMWCarFactory();
        FactoryMethodPattern.Car bmwCar = bmwFactory.createCar();
        System.out.print("   ");
        bmwCar.drive();

        FactoryMethodPattern.CarFactory audiFactory = new FactoryMethodPattern.AudiCarFactory();
        FactoryMethodPattern.Car audiCar = audiFactory.createCar();
        System.out.print("   ");
        audiCar.drive();
    }

    /**
     * 演示抽象工厂模式
     */
    private static void demonstrateAbstractFactoryPattern() {
        System.out.println("3. 抽象工厂模式 (Abstract Factory Pattern)");
        System.out.println("   目的: 提供一个创建一系列相关或相互依赖对象的接口");

        // 使用德国工厂创建交通工具
        AbstractFactoryPattern.VehicleFactory germanFactory =
            new AbstractFactoryPattern.GermanVehicleFactory();
        AbstractFactoryPattern.Car germanCar = germanFactory.createCar();
        AbstractFactoryPattern.Airplane germanPlane = germanFactory.createAirplane();

        System.out.print("   德国工厂创建 - ");
        System.out.print("汽车: " + germanCar.getName() + ", ");
        germanCar.start();
        System.out.print("   ");
        System.out.print("飞机: " + germanPlane.getType() + ", ");
        germanPlane.fly();

        // 使用美国工厂创建交通工具
        AbstractFactoryPattern.VehicleFactory americanFactory =
            new AbstractFactoryPattern.AmericanVehicleFactory();
        AbstractFactoryPattern.Car americanCar = americanFactory.createCar();
        AbstractFactoryPattern.Airplane americanPlane = americanFactory.createAirplane();

        System.out.print("   美国工厂创建 - ");
        System.out.print("汽车: " + americanCar.getName() + ", ");
        americanCar.start();
        System.out.print("   ");
        System.out.print("飞机: " + americanPlane.getType() + ", ");
        americanPlane.fly();
    }

    /**
     * 演示建造者模式
     */
    private static void demonstrateBuilderPattern() {
        System.out.println("4. 建造者模式 (Builder Pattern)");
        System.out.println("   目的: 分步骤创建复杂对象，可以改变内部表示");

        BuilderPattern.ComputerDirector director = new BuilderPattern.ComputerDirector();

        // 构建游戏电脑
        BuilderPattern.Computer gamingComputer = director.constructGamingComputer();
        System.out.println("   游戏电脑配置: " + gamingComputer.getCpu() + " | " +
                          gamingComputer.getGraphicsCard());

        // 构建办公电脑
        BuilderPattern.Computer officeComputer = director.constructOfficeComputer();
        System.out.println("   办公电脑配置: " + officeComputer.getCpu() + " | " +
                          officeComputer.getMemory());

        // 手动构建自定义电脑
        BuilderPattern.Computer customComputer = new BuilderPattern.GamingComputerBuilder()
                .setCpu("AMD Ryzen 7 5800X")
                .setMemory("32GB DDR4")
                .setStorage("1TB NVMe SSD")
                .build();
        System.out.println("   自定义电脑配置: " + customComputer.getCpu() + " | " +
                          customComputer.getStorage());
    }

    /**
     * 演示原型模式
     */
    private static void demonstratePrototypePattern() {
        System.out.println("5. 原型模式 (Prototype Pattern)");
        System.out.println("   目的: 通过复制现有对象创建新对象，无需依赖具体类");

        // 创建原始员工
        PrototypePattern.Address address = new PrototypePattern.Address("456 Tech Rd", "Shanghai", "200000");
        PrototypePattern.Employee originalEmployee = new PrototypePattern.Employee(
            "王五", 28, "研发部", address);
        originalEmployee.getSkills().add("JavaScript");
        originalEmployee.getSkills().add("React");

        System.out.println("   原始员工: " + originalEmployee.getName() +
                          " | 城市: " + originalEmployee.getAddress().getCity() +
                          " | 技能: " + originalEmployee.getSkills());

        // 使用原型模式克隆员工
        PrototypePattern.Employee clonedEmployee = originalEmployee.deepClone();
        clonedEmployee.setName("赵六");
        clonedEmployee.getAddress().setCity("Guangzhou");
        clonedEmployee.getSkills().add("Vue.js");

        System.out.println("   克隆员工: " + clonedEmployee.getName() +
                          " | 城市: " + clonedEmployee.getAddress().getCity() +
                          " | 技能: " + clonedEmployee.getSkills());
        System.out.println("   原始员工未受影响: " + originalEmployee.getName() +
                          " | 城市: " + originalEmployee.getAddress().getCity() +
                          " | 技能: " + originalEmployee.getSkills());

        // 演示图形原型
        PrototypePattern.ShapeCache.loadCache();
        PrototypePattern.Shape clonedShape = PrototypePattern.ShapeCache.getShape("1");
        System.out.print("   克隆图形: ");
        clonedShape.draw();
    }
}
