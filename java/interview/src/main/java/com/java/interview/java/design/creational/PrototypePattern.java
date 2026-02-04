package com.java.interview.java.design.creational;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式 (Prototype Pattern)
 *
 * 定义：原型模式是一种创建型设计模式，它使你能够复制已有对象，而无需使代码依赖它们所属的类。
 * 通过实现Cloneable接口并重写clone()方法，可以在运行时动态地创建对象。
 *
 * 主要角色：
 * 1. 抽象原型(Prototype)：声明克隆自身的接口
 * 2. 具体原型(Concrete Prototype)：实现克隆方法，用于创建具体原型类的实例
 * 3. 客户端(Client)：使用原型类提供的克隆方法来复制新的对象
 *
 * 主要特点：
 * 1. 通过复制现有对象来创建新对象
 * 2. 隐藏了对象的创建细节
 * 3. 性能通常优于直接创建对象
 *
 * 应用场景：
 * - 当创建对象的成本比较大时（例如对象创建需要读取数据库或文件）
 * - 需要保存对象的状态以便后续恢复时
 * - 一个对象被多个对象共享，但每个对象都需要修改对象的部分内容时
 * - 在循环体内创建大量对象时
 *
 * 注意：原型模式分为浅拷贝和深拷贝两种方式
 */
public class PrototypePattern {

    /**
     * 原型接口 - 定义克隆方法
     */
    interface Prototype {
        Prototype clone();
    }

    /**
     * 地址类 - 作为复合对象的一部分
     */
    static class Address {
        private String street;
        private String city;
        private String zipCode;

        public Address(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        // Getter和Setter方法
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZipCode() { return zipCode; }
        public void setZipCode(String zipCode) { this.zipCode = zipCode; }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }

        // 重写clone方法
        public Address clone() {
            return new Address(this.street, this.city, this.zipCode);
        }
    }

    /**
     * 员工类 - 实现原型模式的实体类
     */
    static class Employee implements Cloneable {
        private String name;
        private int age;
        private String department;
        private Address address;  // 复合对象
        private List<String> skills;  // 复合对象列表

        public Employee(String name, int age, String department, Address address) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.address = address;
            this.skills = new ArrayList<>();
        }

        // Getter和Setter方法
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
        public List<String> getSkills() { return skills; }
        public void setSkills(List<String> skills) { this.skills = skills; }

        /**
         * 浅拷贝实现 - 只复制基本数据类型和对象引用
         * 注意：浅拷贝不会复制引用的对象，只是复制引用地址
         */
        public Employee shallowClone() throws CloneNotSupportedException {
            return (Employee) super.clone();
        }

        /**
         * 深拷贝实现 - 复制对象及其引用的所有对象
         * 深拷贝会创建全新的对象图，而不是仅仅复制引用
         */
        public Employee deepClone() {
            try {
                Employee cloned = (Employee) super.clone();
                // 深拷贝复合对象
                cloned.address = this.address.clone();
                // 深拷贝技能列表
                cloned.skills = new ArrayList<>(this.skills);
                return cloned;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Employee cloning failed", e);
            }
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", department='" + department + '\'' +
                    ", address=" + address +
                    ", skills=" + skills +
                    '}';
        }
    }

    /**
     * 图形基类 - 另一个原型模式的例子
     */
    static abstract class Shape implements Cloneable {
        protected String id;
        protected String type;

        abstract void draw();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Object cloned = super.clone();
            // 如果有复合对象，这里也需要深拷贝
            return cloned;
        }
    }

    /**
     * 矩形类 - 继承Shape
     */
    static class Rectangle extends Shape {
        public Rectangle() {
            type = "Rectangle";
        }

        @Override
        public void draw() {
            System.out.println("Drawing a Rectangle");
        }
    }

    /**
     * 圆形类 - 继承Shape
     */
    static class Circle extends Shape {
        public Circle() {
            type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("Drawing a Circle");
        }
    }

    /**
     * 形状缓存类 - 使用原型模式管理形状对象
     */
    static class ShapeCache {
        private static List<Shape> shapeList = new ArrayList<>();

        public static void loadCache() {
            Rectangle rectangle = new Rectangle();
            rectangle.setId("1");
            shapeList.add(rectangle);

            Circle circle = new Circle();
            circle.setId("2");
            shapeList.add(circle);

            Circle anotherCircle = new Circle();
            anotherCircle.setId("3");
            shapeList.add(anotherCircle);
        }

        public static Shape getShape(String shapeId) {
            for (Shape shape : shapeList) {
                if (shape.getId().equals(shapeId)) {
                    try {
                        return (Shape) shape.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            return null;
        }

        public static int getCacheSize() {
            return shapeList.size();
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 演示员工对象的原型模式使用
        System.out.println("=== 员工对象原型模式演示 ===");

        // 创建原始员工对象
        Address originalAddress = new Address("123 Main St", "Beijing", "100000");
        Employee originalEmployee = new Employee("张三", 30, "IT部门", originalAddress);
        originalEmployee.getSkills().add("Java");
        originalEmployee.getSkills().add("Spring");

        System.out.println("原始员工: " + originalEmployee);

        // 使用深拷贝创建新员工
        Employee clonedEmployee = originalEmployee.deepClone();
        clonedEmployee.setName("李四");  // 修改克隆对象的姓名
        clonedEmployee.setAge(25);      // 修改克隆对象的年龄
        clonedEmployee.getAddress().setCity("Shanghai"); // 修改克隆对象的地址城市
        clonedEmployee.getSkills().add("Python"); // 添加克隆对象的新技能

        System.out.println("克隆员工: " + clonedEmployee);
        System.out.println("原始员工: " + originalEmployee); // 原始员工不应受影响

        System.out.println("\n=== 图形对象原型模式演示 ===");

        // 加载形状缓存
        ShapeCache.loadCache();

        // 从缓存获取形状对象并克隆
        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("第一个克隆形状: " + clonedShape1.getType() + ", ID: " + clonedShape1.getId());
        clonedShape1.draw();

        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("第二个克隆形状: " + clonedShape2.getType() + ", ID: " + clonedShape2.getId());
        clonedShape2.draw();

        Shape clonedShape3 = ShapeCache.getShape("3");
        System.out.println("第三个克隆形状: " + clonedShape3.getType() + ", ID: " + clonedShape3.getId());
        clonedShape3.draw();

        System.out.println("\n=== 原型模式的关键特性 ===");
        System.out.println("1. 通过复制现有对象创建新对象，避免重复初始化");
        System.out.println("2. 性能通常优于直接创建对象，特别是复杂对象");
        System.out.println("3. 解耦对象的创建和使用");
        System.out.println("4. 支持运行时动态添加和删除产品类");
        System.out.println("5. 需要注意浅拷贝和深拷贝的区别");
        System.out.println("6. 适用于创建成本较高的对象");

        System.out.println("\n=== 浅拷贝 vs 深拷贝 ===");
        System.out.println("浅拷贝：只复制对象本身，不复制其引用的对象");
        System.out.println("深拷贝：复制对象本身及其引用的所有对象");
        System.out.println("在实际应用中，通常需要深拷贝来确保对象的独立性");
    }
}
