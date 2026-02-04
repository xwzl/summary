package com.java.interview.java.design.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式 (Composite Pattern)
 *
 * 定义：组合模式是一种结构型设计模式，它允许你将对象组合成树状结构来表现"整体/部分"层次结构。
 * 组合能让客户以一致的方式处理个别对象和对象组合。
 *
 * 主要角色：
 * 1. 抽象组件(Component)：为组合中的对象声明接口，在适当的情况下，实现所有类共有的默认行为
 * 2. 叶子节点(Leaf)：定义组合中的叶子对象的行为，表示树形结构中的叶子节点对象
 * 3. 容器(Composite)：定义有子部件的那些部件的行为，存储子部件，并在Component接口中实现与子部件有关的操作
 * 4. 客户端(Client)：通过Component接口操纵组合部件的对象
 *
 * 主要特点：
 * 1. 统一处理个体和组合：客户端可以一致地对待单个对象和组合对象
 * 2. 递归结构：形成树形结构，支持任意深度的嵌套
 * 3. 灵活性：可以很容易地在组合中加入新的对象
 *
 * 应用场景：
 * - 需要表示对象的部分-整体层次结构
 * - 希望用户忽略组合对象与单个对象的不同，统一地使用组合结构中的所有对象
 * - 文件系统、菜单系统、组织架构图等树形结构的场景
 */
public class CompositePattern {

    /**
     * 抽象组件 - 文件系统组件接口
     */
    interface FileSystemComponent {
        void showDetails();
        double getSize();
        String getName();
    }

    /**
     * 叶子节点 - 文件类
     */
    static class File implements FileSystemComponent {
        private String name;
        private double size;

        public File(String name, double size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public void showDetails() {
            System.out.println("File: " + name + " (Size: " + size + " KB)");
        }

        @Override
        public double getSize() {
            return size;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * 容器 - 文件夹类
     */
    static class Folder implements FileSystemComponent {
        private String name;
        private List<FileSystemComponent> children;

        public Folder(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public void add(FileSystemComponent component) {
            children.add(component);
        }

        public void remove(FileSystemComponent component) {
            children.remove(component);
        }

        @Override
        public void showDetails() {
            System.out.println("Folder: " + name);
            for (FileSystemComponent component : children) {
                // 添加缩进以显示层级结构
                System.out.print("  ");
                component.showDetails();
            }
        }

        @Override
        public double getSize() {
            double totalSize = 0;
            for (FileSystemComponent component : children) {
                totalSize += component.getSize();
            }
            return totalSize;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * 组合模式的高级应用 - 公司组织架构
     */
    static class OrganizationStructure {
        // 抽象组件 - 员工接口
        interface Employee {
            String getName();
            String getPosition();
            double getSalary();
            void showDetails();
            void add(Employee employee);
            void remove(Employee employee);
            List<Employee> getSubordinates();
        }

        // 叶子节点 - 普通员工
        static class Developer implements Employee {
            private String name;
            private String position;
            private double salary;

            public Developer(String name, String position, double salary) {
                this.name = name;
                this.position = position;
                this.salary = salary;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getPosition() {
                return position;
            }

            @Override
            public double getSalary() {
                return salary;
            }

            @Override
            public void showDetails() {
                System.out.println("Developer: " + name + ", Position: " + position + ", Salary: $" + salary);
            }

            @Override
            public void add(Employee employee) {
                throw new UnsupportedOperationException("Cannot add to a leaf node (Developer)");
            }

            @Override
            public void remove(Employee employee) {
                throw new UnsupportedOperationException("Cannot remove from a leaf node (Developer)");
            }

            @Override
            public List<Employee> getSubordinates() {
                throw new UnsupportedOperationException("Developer has no subordinates");
            }
        }

        // 叶子节点 - 设计师
        static class Designer implements Employee {
            private String name;
            private String position;
            private double salary;

            public Designer(String name, String position, double salary) {
                this.name = name;
                this.position = position;
                this.salary = salary;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getPosition() {
                return position;
            }

            @Override
            public double getSalary() {
                return salary;
            }

            @Override
            public void showDetails() {
                System.out.println("Designer: " + name + ", Position: " + position + ", Salary: $" + salary);
            }

            @Override
            public void add(Employee employee) {
                throw new UnsupportedOperationException("Cannot add to a leaf node (Designer)");
            }

            @Override
            public void remove(Employee employee) {
                throw new UnsupportedOperationException("Cannot remove from a leaf node (Designer)");
            }

            @Override
            public List<Employee> getSubordinates() {
                throw new UnsupportedOperationException("Designer has no subordinates");
            }
        }

        // 容器 - 管理层
        static class Manager implements Employee {
            private String name;
            private String position;
            private double salary;
            private List<Employee> subordinates;

            public Manager(String name, String position, double salary) {
                this.name = name;
                this.position = position;
                this.salary = salary;
                this.subordinates = new ArrayList<>();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getPosition() {
                return position;
            }

            @Override
            public double getSalary() {
                return salary;
            }

            @Override
            public void showDetails() {
                System.out.println("Manager: " + name + ", Position: " + position + ", Salary: $" + salary);
                for (Employee subordinate : subordinates) {
                    System.out.print("  ");
                    subordinate.showDetails();
                }
            }

            @Override
            public void add(Employee employee) {
                subordinates.add(employee);
            }

            @Override
            public void remove(Employee employee) {
                subordinates.remove(employee);
            }

            @Override
            public List<Employee> getSubordinates() {
                return subordinates;
            }
        }

        // 高级管理层
        static class Director implements Employee {
            private String name;
            private String position;
            private double salary;
            private List<Employee> subordinates;

            public Director(String name, String position, double salary) {
                this.name = name;
                this.position = position;
                this.salary = salary;
                this.subordinates = new ArrayList<>();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getPosition() {
                return position;
            }

            @Override
            public double getSalary() {
                return salary;
            }

            @Override
            public void showDetails() {
                System.out.println("Director: " + name + ", Position: " + position + ", Salary: $" + salary);
                for (Employee subordinate : subordinates) {
                    System.out.print("  ");
                    subordinate.showDetails();
                }
            }

            @Override
            public void add(Employee employee) {
                subordinates.add(employee);
            }

            @Override
            public void remove(Employee employee) {
                subordinates.remove(employee);
            }

            @Override
            public List<Employee> getSubordinates() {
                return subordinates;
            }
        }
    }

    /**
     * 组合模式的进一步应用 - 菜单系统
     */
    static class MenuSystem {
        // 抽象组件 - 菜单项接口
        interface MenuItem {
            String getName();
            String getDescription();
            double getPrice();
            boolean isVegetarian();
            void print();
        }

        // 叶子节点 - 菜单项
        static class MenuItemImpl implements MenuItem {
            private String name;
            private String description;
            private boolean vegetarian;
            private double price;

            public MenuItemImpl(String name, String description, boolean vegetarian, double price) {
                this.name = name;
                this.description = description;
                this.vegetarian = vegetarian;
                this.price = price;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public double getPrice() {
                return price;
            }

            @Override
            public boolean isVegetarian() {
                return vegetarian;
            }

            @Override
            public void print() {
                System.out.print(" " + getName());
                if (isVegetarian()) {
                    System.out.print("(v)");
                }
                System.out.println(", " + getPrice());
                System.out.println(" -- " + getDescription());
            }
        }

        // 容器 - 菜单
        static class Menu implements MenuItem {
            private String name;
            private String description;
            private List<MenuItem> menuItems;

            public Menu(String name, String description) {
                this.name = name;
                this.description = description;
                this.menuItems = new ArrayList<>();
            }

            public void add(MenuItem menuItem) {
                menuItems.add(menuItem);
            }

            public void remove(MenuItem menuItem) {
                menuItems.remove(menuItem);
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public double getPrice() {
                double totalPrice = 0;
                for (MenuItem item : menuItems) {
                    totalPrice += item.getPrice();
                }
                return totalPrice;
            }

            @Override
            public boolean isVegetarian() {
                // 如果所有项目都是素食，则菜单是素食
                for (MenuItem item : menuItems) {
                    if (!item.isVegetarian()) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void print() {
                System.out.println("\n" + getName() + ", " + getDescription());
                System.out.println("---------------------");

                for (MenuItem menuItem : menuItems) {
                    menuItem.print();
                }
            }
        }

        // 菜单组合 - 可以包含子菜单
        static class MenuComposite implements MenuItem {
            private String name;
            private String description;
            private List<MenuItem> menuItems;

            public MenuComposite(String name, String description) {
                this.name = name;
                this.description = description;
                this.menuItems = new ArrayList<>();
            }

            public void add(MenuItem menuItem) {
                menuItems.add(menuItem);
            }

            public void remove(MenuItem menuItem) {
                menuItems.remove(menuItem);
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public double getPrice() {
                double totalPrice = 0;
                for (MenuItem item : menuItems) {
                    totalPrice += item.getPrice();
                }
                return totalPrice;
            }

            @Override
            public boolean isVegetarian() {
                for (MenuItem item : menuItems) {
                    if (!item.isVegetarian()) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void print() {
                System.out.println("\n" + getName() + ", " + getDescription());
                System.out.println("===================");

                for (MenuItem menuItem : menuItems) {
                    menuItem.print();
                }
            }
        }
    }

    /**
     * 安全的组合模式实现 - 将容器特有方法从组件接口中移除
     */
    static class SafeComposite {
        // 抽象组件 - 只包含所有元素共有的方法
        interface Component {
            String getName();
            void display();
        }

        // 叶子节点 - 只实现组件接口
        static class Leaf implements Component {
            private String name;

            public Leaf(String name) {
                this.name = name;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void display() {
                System.out.println("Leaf: " + name);
            }
        }

        // 容器 - 包含自己的方法
        static class Composite implements Component {
            private String name;
            private List<Component> children;

            public Composite(String name) {
                this.name = name;
                this.children = new ArrayList<>();
            }

            public void add(Component component) {
                children.add(component);
            }

            public void remove(Component component) {
                children.remove(component);
            }

            public List<Component> getChildren() {
                return children;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void display() {
                System.out.println("Composite: " + name);
                for (Component child : children) {
                    System.out.print("  ");
                    child.display();
                }
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 组合模式 (Composite Pattern) 示例 ===\n");

        // 基础组合模式示例 - 文件系统
        System.out.println("1. 基础组合模式示例 - 文件系统:");
        Folder rootFolder = new Folder("Root");
        Folder documentsFolder = new Folder("Documents");
        Folder picturesFolder = new Folder("Pictures");

        File resume = new File("resume.docx", 1024);
        File coverLetter = new File("cover_letter.docx", 512);
        File photo = new File("photo.jpg", 2048);
        File screenshot = new File("screenshot.png", 1024);

        documentsFolder.add(resume);
        documentsFolder.add(coverLetter);
        picturesFolder.add(photo);
        picturesFolder.add(screenshot);

        rootFolder.add(documentsFolder);
        rootFolder.add(picturesFolder);

        rootFolder.showDetails();
        System.out.println("Total size: " + rootFolder.getSize() + " KB");
        System.out.println();

        // 高级组合模式示例 - 组织架构
        System.out.println("2. 高级组合模式示例 - 组织架构:");
        OrganizationStructure.Manager engineeringManager =
            new OrganizationStructure.Manager("Alice", "Engineering Manager", 120000);
        OrganizationStructure.Manager marketingManager =
            new OrganizationStructure.Manager("Bob", "Marketing Manager", 110000);

        OrganizationStructure.Developer dev1 =
            new OrganizationStructure.Developer("Charlie", "Senior Developer", 90000);
        OrganizationStructure.Developer dev2 =
            new OrganizationStructure.Developer("David", "Junior Developer", 70000);
        OrganizationStructure.Designer designer =
            new OrganizationStructure.Designer("Eve", "UI/UX Designer", 80000);

        engineeringManager.add(dev1);
        engineeringManager.add(dev2);
        marketingManager.add(designer);

        OrganizationStructure.Director ceo =
            new OrganizationStructure.Director("Frank", "CEO", 200000);
        ceo.add(engineeringManager);
        ceo.add(marketingManager);

        ceo.showDetails();
        System.out.println();

        // 菜单系统示例
        System.out.println("3. 菜单系统示例:");
        MenuSystem.Menu breakfastMenu = new MenuSystem.Menu("Breakfast Menu", "Morning favorites");
        MenuSystem.Menu lunchMenu = new MenuSystem.Menu("Lunch Menu", "Midday specials");

        breakfastMenu.add(new MenuSystem.MenuItemImpl("Pancakes", "Fluffy pancakes with syrup", false, 5.99));
        breakfastMenu.add(new MenuSystem.MenuItemImpl("Oatmeal", "Healthy oatmeal with fruits", true, 4.99));

        lunchMenu.add(new MenuSystem.MenuItemImpl("BLT Sandwich", "Bacon, lettuce, tomato", false, 8.99));
        lunchMenu.add(new MenuSystem.MenuItemImpl("Veggie Burger", "Plant-based burger", true, 9.99));

        MenuSystem.MenuComposite fullMenu = new MenuSystem.MenuComposite("Full Menu", "All our dishes");
        fullMenu.add(breakfastMenu);
        fullMenu.add(lunchMenu);

        fullMenu.print();
        System.out.println();

        // 安全的组合模式示例
        System.out.println("4. 安全的组合模式示例:");
        SafeComposite.Composite company = new SafeComposite.Composite("Company");
        SafeComposite.Composite department = new SafeComposite.Composite("Development Dept");

        SafeComposite.Leaf emp1 = new SafeComposite.Leaf("Employee 1");
        SafeComposite.Leaf emp2 = new SafeComposite.Leaf("Employee 2");

        department.add(emp1);
        department.add(emp2);
        company.add(department);

        company.display();
        System.out.println();

        System.out.println("=== 组合模式的关键特性 ===");
        System.out.println("1. 统一处理: 客户端可以一致地对待单个对象和组合对象");
        System.out.println("2. 递归结构: 形成树形结构，支持任意深度的嵌套");
        System.out.println("3. 灵活性: 可以很容易地在组合中加入新的对象");
        System.out.println("4. 透明性: 客户端不知道处理的是单个对象还是组合对象");
        System.out.println("5. 安全性: 可以选择是否在接口中暴露容器特有方法");
        System.out.println("6. 开闭原则: 对扩展开放，对修改关闭");
    }
}
