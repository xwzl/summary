package com.java.interview.java.design.creational;

/**
 * 建造者模式 (Builder Pattern)
 *
 * 定义：建造者模式是一种创建型设计模式，它允许你分步骤创建复杂对象。
 * 该模式允许你使用相同创建代码生成不同类型和形式的对象。
 *
 * 主要角色：
 * 1. 产品(Product)：被构建的复杂对象，包含多个组成部分
 * 2. 抽象建造者(Builder)：为创建一个产品对象的各个部件指定抽象接口
 * 3. 具体建造者(Concrete Builder)：实现抽象建造者接口，构建和装配该产品的各个部件
 * 4. 指挥者(Director)：构建一个使用Builder接口的对象，它主要是用于创建一个复杂对象
 *
 * 主要特点：
 * 1. 分步骤创建复杂对象
 * 2. 可以改变内部表示
 * 3. 更精细的控制构建过程
 *
 * 应用场景：
 * - 需要生成的对象具有复杂的内部结构
 * - 需要生成的对象内部的属性被赋值的顺序不固定
 * - 对象的创建过程独立于部件的装配过程
 * - 构建代码和表示代码分离
 */
public class BuilderPattern {

    /**
     * 产品类 - 电脑
     * 表示一个复杂对象，由多个部分组成
     */
    static class Computer {
        private String cpu;           // CPU
        private String motherboard;   // 主板
        private String memory;        // 内存
        private String storage;       // 存储设备
        private String graphicsCard;  // 显卡
        private String powerSupply;   // 电源
        private String caseType;      // 机箱

        // 私有构造函数，只能通过Builder创建
        private Computer(ComputerBuilder builder) {
            this.cpu = builder.getCpu();
            this.motherboard = builder.getMotherboard();
            this.memory = builder.getMemory();
            this.storage = builder.getStorage();
            this.graphicsCard = builder.getGraphicsCard();
            this.powerSupply = builder.getPowerSupply();
            this.caseType = builder.getCaseType();
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "cpu='" + cpu + '\'' +
                    ", motherboard='" + motherboard + '\'' +
                    ", memory='" + memory + '\'' +
                    ", storage='" + storage + '\'' +
                    ", graphicsCard='" + graphicsCard + '\'' +
                    ", powerSupply='" + powerSupply + '\'' +
                    ", caseType='" + caseType + '\'' +
                    '}';
        }

        // Getter方法
        public String getCpu() { return cpu; }
        public String getMotherboard() { return motherboard; }
        public String getMemory() { return memory; }
        public String getStorage() { return storage; }
        public String getGraphicsCard() { return graphicsCard; }
        public String getPowerSupply() { return powerSupply; }
        public String getCaseType() { return caseType; }
    }

    /**
     * 抽象建造者 - 定义构建Computer对象的各个组件的方法
     */
    interface ComputerBuilder {
        ComputerBuilder setCpu(String cpu);
        ComputerBuilder setMotherboard(String motherboard);
        ComputerBuilder setMemory(String memory);
        ComputerBuilder setStorage(String storage);
        ComputerBuilder setGraphicsCard(String graphicsCard);
        ComputerBuilder setPowerSupply(String powerSupply);
        ComputerBuilder setCaseType(String caseType);
        Computer build();

        // Getter方法，用于访问建造者的字段
        String getCpu();
        String getMotherboard();
        String getMemory();
        String getStorage();
        String getGraphicsCard();
        String getPowerSupply();
        String getCaseType();

    }

    /**
     * 具体建造者 - 游戏电脑建造者
     */
    static class GamingComputerBuilder implements ComputerBuilder {
        private String cpu;
        private String motherboard;
        private String memory;
        private String storage;
        private String graphicsCard;
        private String powerSupply;
        private String caseType;

        @Override
        public ComputerBuilder setCpu(String cpu) {
            this.cpu = cpu;
            return this;  // 返回this以支持链式调用
        }

        @Override
        public String getCpu() {
            return this.cpu;
        }

        @Override
        public String getMotherboard() {
            return this.motherboard;
        }

        @Override
        public String getMemory() {
            return this.memory;
        }

        @Override
        public String getStorage() {
            return this.storage;
        }

        @Override
        public String getGraphicsCard() {
            return this.graphicsCard;
        }

        @Override
        public String getPowerSupply() {
            return this.powerSupply;
        }

        @Override
        public String getCaseType() {
            return this.caseType;
        }

        @Override
        public ComputerBuilder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        @Override
        public ComputerBuilder setMemory(String memory) {
            this.memory = memory;
            return this;
        }

        @Override
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        @Override
        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        @Override
        public ComputerBuilder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        @Override
        public ComputerBuilder setCaseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        @Override
        public Computer build() {
            // 验证必需的组件
            if (cpu == null || motherboard == null || memory == null) {
                throw new IllegalStateException("CPU, Motherboard, and Memory are required for a gaming computer!");
            }

            // 创建游戏电脑，通常配备高性能组件
            if (graphicsCard == null) {
                graphicsCard = "High-end Gaming Graphics Card";  // 默认高性能显卡
            }
            if (powerSupply == null) {
                powerSupply = "High Wattage Power Supply";      // 默认高功率电源
            }

            return new Computer(this);
        }
    }

    /**
     * 具体建造者 - 办公电脑建造者
     */
    static class OfficeComputerBuilder implements ComputerBuilder {
        private String cpu;
        private String motherboard;
        private String memory;
        private String storage;
        private String graphicsCard;
        private String powerSupply;
        private String caseType;

        @Override
        public ComputerBuilder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        @Override
        public String getCpu() {
            return this.cpu;
        }

        @Override
        public String getMotherboard() {
            return this.motherboard;
        }

        @Override
        public String getMemory() {
            return this.memory;
        }

        @Override
        public String getStorage() {
            return this.storage;
        }

        @Override
        public String getGraphicsCard() {
            return this.graphicsCard;
        }

        @Override
        public String getPowerSupply() {
            return this.powerSupply;
        }

        @Override
        public String getCaseType() {
            return this.caseType;
        }

        @Override
        public ComputerBuilder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        @Override
        public ComputerBuilder setMemory(String memory) {
            this.memory = memory;
            return this;
        }

        @Override
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        @Override
        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        @Override
        public ComputerBuilder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        @Override
        public ComputerBuilder setCaseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        @Override
        public Computer build() {
            // 验证必需的组件
            if (cpu == null || motherboard == null || memory == null) {
                throw new IllegalStateException("CPU, Motherboard, and Memory are required for an office computer!");
            }

            // 创建办公电脑，通常配备基础组件
            if (graphicsCard == null) {
                graphicsCard = "Integrated Graphics";  // 默认集成显卡
            }
            if (powerSupply == null) {
                powerSupply = "Standard Power Supply"; // 默认标准电源
            }

            return new Computer(this);
        }
    }

    /**
     * 具体建造者 - 服务器电脑建造者
     */
    static class ServerComputerBuilder implements ComputerBuilder {
        private String cpu;
        private String motherboard;
        private String memory;
        private String storage;
        private String graphicsCard;
        private String powerSupply;
        private String caseType;

        @Override
        public ComputerBuilder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        @Override
        public String getCpu() {
            return this.cpu;
        }

        @Override
        public String getMotherboard() {
            return this.motherboard;
        }

        @Override
        public String getMemory() {
            return this.memory;
        }

        @Override
        public String getStorage() {
            return this.storage;
        }

        @Override
        public String getGraphicsCard() {
            return this.graphicsCard;
        }

        @Override
        public String getPowerSupply() {
            return this.powerSupply;
        }

        @Override
        public String getCaseType() {
            return this.caseType;
        }

        @Override
        public ComputerBuilder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        @Override
        public ComputerBuilder setMemory(String memory) {
            this.memory = memory;
            return this;
        }

        @Override
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        @Override
        public ComputerBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        @Override
        public ComputerBuilder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        @Override
        public ComputerBuilder setCaseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        @Override
        public Computer build() {
            // 验证必需的组件
            if (cpu == null || motherboard == null || memory == null) {
                throw new IllegalStateException("CPU, Motherboard, and Memory are required for a server computer!");
            }

            // 创建服务器电脑，通常配备大容量存储和高稳定性组件
            if (storage == null) {
                storage = "Large Capacity SSD Array";  // 默认大容量SSD阵列
            }
            if (powerSupply == null) {
                powerSupply = "Redundant Power Supply"; // 默认冗余电源
            }

            return new Computer(this);
        }
    }

    /**
     * 指挥者 - 负责按照一定算法或流程来组装产品
     * 它主要用来创建一个复杂对象，其中主要有两个作用：
     * 1. 隔离客户与对象的生产过程
     * 2. 负责控制产品对象的生产过程
     */
    static class ComputerDirector {
        public Computer constructGamingComputer() {
            return new GamingComputerBuilder()
                    .setCpu("Intel i9-13900K")
                    .setMotherboard("ASUS ROG Maximus Z790 Hero")
                    .setMemory("32GB DDR5 6000MHz")
                    .setStorage("2TB NVMe SSD")
                    .setGraphicsCard("RTX 4090 24GB")
                    .setPowerSupply("1600W Gold Rated")
                    .setCaseType("Full Tower RGB")
                    .build();
        }

        public Computer constructOfficeComputer() {
            return new OfficeComputerBuilder()
                    .setCpu("Intel i5-12400")
                    .setMotherboard("MSI B660 Chipset")
                    .setMemory("16GB DDR4 3200MHz")
                    .setStorage("512GB SSD")
                    .setPowerSupply("650W Standard")
                    .setCaseType("Mid Tower")
                    .build();
        }

        public Computer constructServerComputer() {
            return new ServerComputerBuilder()
                    .setCpu("AMD EPYC 7763")
                    .setMotherboard("Supermicro Server Board")
                    .setMemory("128GB ECC RDIMM")
                    .setStorage("4x 4TB Enterprise HDD RAID")
                    .setPowerSupply("2000W Redundant PSU")
                    .setCaseType("4U Rackmount")
                    .build();
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 使用指挥者构建电脑
        ComputerDirector director = new ComputerDirector();

        // 构建游戏电脑
        Computer gamingComputer = director.constructGamingComputer();
        System.out.println("Gaming Computer: " + gamingComputer);

        // 构建办公电脑
        Computer officeComputer = director.constructOfficeComputer();
        System.out.println("Office Computer: " + officeComputer);

        // 构建服务器电脑
        Computer serverComputer = director.constructServerComputer();
        System.out.println("Server Computer: " + serverComputer);

        System.out.println("\n=== 手动构建特定配置电脑 ===");

        // 使用建造者手动构建特定配置的电脑
        Computer customComputer = new GamingComputerBuilder()
                .setCpu("AMD Ryzen 9 7950X")
                .setMotherboard("ROG Crosshair X670E Hero")
                .setMemory("64GB DDR5 6200MHz")
                .setStorage("4TB PCIe 5.0 NVMe SSD")
                .setGraphicsCard("RTX 4090 Distinguished Edition")
                .setPowerSupply("2000W Platinum Rated")
                .setCaseType("Modular Full Tower")
                .build();

        System.out.println("Custom Computer: " + customComputer);

        System.out.println("\n=== 建造者模式的关键特性 ===");
        System.out.println("1. 分步构建复杂对象");
        System.out.println("2. 可以改变内部表示");
        System.out.println("3. 更精细地控制构建过程");
        System.out.println("4. 相同的构建过程可以创建不同的表示");
        System.out.println("5. 支持链式调用，API使用友好");
        System.out.println("6. 将对象的构建与表示分离");
    }
}
