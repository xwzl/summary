package com.java.interview.java.design.structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结构型设计模式综合示例
 * 
 * 本类演示了所有结构型设计模式的实际应用
 * 结构型设计模式关注类和对象的组合，用于描述如何形成更大的结构
 * 
 * 包含的结构型模式：
 * 1. 适配器模式 (Adapter Pattern)
 * 2. 桥接模式 (Bridge Pattern)
 * 3. 组合模式 (Composite Pattern)
 * 4. 装饰器模式 (Decorator Pattern)
 * 5. 外观模式 (Facade Pattern)
 * 6. 享元模式 (Flyweight Pattern)
 * 7. 代理模式 (Proxy Pattern)
 */
public class StructuralPatternsOverview {

    public static void main(String[] args) {
        System.out.println("=== 结构型设计模式综合演示 ===\n");

        // 1. 适配器模式演示
        demonstrateAdapterPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 2. 桥接模式演示
        demonstrateBridgePattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 3. 组合模式演示
        demonstrateCompositePattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 4. 装饰器模式演示
        demonstrateDecoratorPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 5. 外观模式演示
        demonstrateFacadePattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 6. 享元模式演示
        demonstrateFlyweightPattern();

        System.out.println("\n" + "=".repeat(60) + "\n");

        // 7. 代理模式演示
        demonstrateProxyPattern();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("结构型设计模式总结:");
        System.out.println("1. 适配器模式: 将一个类的接口转换为客户期望的另一个接口");
        System.out.println("2. 桥接模式: 将抽象与实现分离，使二者可以独立变化");
        System.out.println("3. 组合模式: 将对象组合成树状结构以表现整体/部分层次");
        System.out.println("4. 装饰器模式: 动态地给对象添加一些额外的职责");
        System.out.println("5. 外观模式: 为子系统中的一组接口提供一个统一的接口");
        System.out.println("6. 享元模式: 通过共享尽可能多的相似对象来减少内存使用");
        System.out.println("7. 代理模式: 为其他对象提供一个替身或占位符来控制访问");
    }

    /**
     * 演示适配器模式
     */
    private static void demonstrateAdapterPattern() {
        System.out.println("1. 适配器模式 (Adapter Pattern)");
        System.out.println("   目的: 将一个类的接口转换为客户期望的另一个接口");

        // 创建音频播放器（使用适配器支持不同格式）
        AdapterPattern.AudioPlayer audioPlayer = new AdapterPattern.AudioPlayer();
        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_me.avi");
    }

    /**
     * 演示桥接模式
     */
    private static void demonstrateBridgePattern() {
        System.out.println("2. 桥接模式 (Bridge Pattern)");
        System.out.println("   目的: 将抽象与实现分离，使二者可以独立变化");

        // 创建不同颜色的圆形
        BridgePattern.Shape redCircle = new BridgePattern.Circle(100, 100, 10, new BridgePattern.RedDrawAPI());
        redCircle.draw();

        BridgePattern.Shape greenRectangle = new BridgePattern.Rectangle(50, 50, 20, 30, new BridgePattern.GreenDrawAPI());
        greenRectangle.draw();
    }

    /**
     * 演示组合模式
     */
    private static void demonstrateCompositePattern() {
        System.out.println("3. 组合模式 (Composite Pattern)");
        System.out.println("   目的: 将对象组合成树状结构以表现整体/部分层次");

        // 创建文件系统结构
        CompositePattern.Folder rootFolder = new CompositePattern.Folder("Root");
        CompositePattern.Folder documentsFolder = new CompositePattern.Folder("Documents");
        CompositePattern.File resume = new CompositePattern.File("resume.docx", 1024);

        documentsFolder.add(resume);
        rootFolder.add(documentsFolder);

        rootFolder.showDetails();
        System.out.println("Total size: " + rootFolder.getSize() + " KB");
    }

    /**
     * 演示装饰器模式
     */
    private static void demonstrateDecoratorPattern() {
        System.out.println("4. 装饰器模式 (Decorator Pattern)");
        System.out.println("   目的: 动态地给对象添加一些额外的职责");

        // 创建基础咖啡并逐步添加装饰
        DecoratorPattern.Coffee coffee = new DecoratorPattern.SimpleCoffee();
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        coffee = new DecoratorPattern.MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        coffee = new DecoratorPattern.ChocolateDecorator(coffee);
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());
    }

    /**
     * 演示外观模式
     */
    private static void demonstrateFacadePattern() {
        System.out.println("5. 外观模式 (Facade Pattern)");
        System.out.println("   目的: 为子系统中的一组接口提供一个统一的接口");

        // 使用贷款申请外观简化复杂的审批流程
        FacadePattern.LoanApplicationFacade loanFacade = new FacadePattern.LoanApplicationFacade();
        FacadePattern.Customer customer = new FacadePattern.Customer("John Doe", 50000, 750, false);
        
        boolean approved = loanFacade.approveLoan(customer, 10000);
        System.out.println("Loan application result: " + (approved ? "Approved" : "Rejected"));
    }

    /**
     * 演示享元模式
     */
    private static void demonstrateFlyweightPattern() {
        System.out.println("6. 享元模式 (Flyweight Pattern)");
        System.out.println("   目的: 通过共享尽可能多的相似对象来减少内存使用");

        // 使用字符工厂创建字符对象，相同字符会被重用
        System.out.println("Creating characters:");
        FlyweightPattern.Character charA1 = FlyweightPattern.CharacterFactory.getCharacter('A', "Arial");
        FlyweightPattern.Character charA2 = FlyweightPattern.CharacterFactory.getCharacter('A', "Arial"); // 重用
        FlyweightPattern.Character charB = FlyweightPattern.CharacterFactory.getCharacter('B', "Arial");

        charA1.display(12, "black", 10, 20);
        charB.display(14, "red", 30, 40);

        System.out.println("Character pool size: " + FlyweightPattern.CharacterFactory.getPoolSize());
    }

    /**
     * 演示代理模式
     */
    private static void demonstrateProxyPattern() {
        System.out.println("7. 代理模式 (Proxy Pattern)");
        System.out.println("   目的: 为其他对象提供一个替身或占位符来控制访问");

        // 使用图像代理延迟加载大图片
        ProxyPattern.Image image = new ProxyPattern.ImageProxy("large_photo.jpg");
        System.out.println("Image proxy created, but image not loaded yet");
        image.display(); // 此时才加载图片
    }
}

/**
 * 高级综合示例 - 在一个真实场景中使用多种结构型模式
 */
class AdvancedStructuralExample {
    // 使用适配器模式整合不同的支付系统
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalAdapter implements PaymentProcessor {
        private PayPalAPI paypal;

        public PayPalAdapter() {
            this.paypal = new PayPalAPI();
        }

        @Override
        public void processPayment(double amount) {
            paypal.sendPayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private StripeAPI stripe;

        public StripeAdapter() {
            this.stripe = new StripeAPI();
        }

        @Override
        public void processPayment(double amount) {
            stripe.charge(amount);
        }
    }

    // 桥接模式 - 分离支付渠道和支付类型
    interface PaymentChannel {
        void executePayment(double amount);
    }

    static class OnlineChannel implements PaymentChannel {
        private PaymentProcessor processor;

        public OnlineChannel(PaymentProcessor processor) {
            this.processor = processor;
        }

        @Override
        public void executePayment(double amount) {
            System.out.println("Processing online payment...");
            processor.processPayment(amount);
        }
    }

    // 组合模式 - 订单结构
    interface OrderItem {
        double getPrice();
        String getName();
    }

    static class Product implements OrderItem {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public double getPrice() { return price; }

        @Override
        public String getName() { return name; }
    }

    static class OrderComposite implements OrderItem {
        private String name;
        private List<OrderItem> items;

        public OrderComposite(String name) {
            this.name = name;
            this.items = new ArrayList<>();
        }

        public void addItem(OrderItem item) {
            items.add(item);
        }

        @Override
        public double getPrice() {
            return items.stream().mapToDouble(OrderItem::getPrice).sum();
        }

        @Override
        public String getName() { return name; }
    }

    // 装饰器模式 - 订单处理增强
    static abstract class OrderProcessorDecorator implements OrderItem {
        protected OrderItem orderItem;

        public OrderProcessorDecorator(OrderItem orderItem) {
            this.orderItem = orderItem;
        }

        @Override
        public double getPrice() { return orderItem.getPrice(); }

        @Override
        public String getName() { return orderItem.getName(); }
    }

    static class TaxDecorator extends OrderProcessorDecorator {
        public TaxDecorator(OrderItem orderItem) {
            super(orderItem);
        }

        @Override
        public double getPrice() {
            return super.getPrice() * 1.1; // 10% tax
        }
    }

    // 外观模式 - 简化复杂的订单处理流程
    static class OrderProcessingFacade {
        private PaymentChannel paymentChannel;
        private OrderItem order;

        public OrderProcessingFacade(PaymentChannel channel, OrderItem order) {
            this.paymentChannel = channel;
            this.order = order;
        }

        public void processOrder() {
            System.out.println("Processing order: " + order.getName());
            System.out.println("Total amount: $" + order.getPrice());
            paymentChannel.executePayment(order.getPrice());
            System.out.println("Order processed successfully!");
        }
    }

    // 享元模式 - 用户权限类型
    static class PermissionType {
        private String permissionName;
        private String description;

        public PermissionType(String permissionName, String description) {
            this.permissionName = permissionName;
            this.description = description;
        }

        public void grantPermission(String userId) {
            System.out.println("Granting " + permissionName + " permission to user: " + userId);
        }
    }

    static class PermissionFactory {
        private static final Map<String, PermissionType> permissions = new HashMap<>();

        public static PermissionType getPermission(String name, String desc) {
            String key = name + ":" + desc;
            PermissionType perm = permissions.get(key);

            if (perm == null) {
                perm = new PermissionType(name, desc);
                permissions.put(key, perm);
            }

            return perm;
        }
    }

    // 代理模式 - 安全访问控制
    static class SecureOrderProcessor {
        private OrderProcessingFacade realProcessor;
        private String authorizedUser;
        private String currentUser;

        public SecureOrderProcessor(OrderProcessingFacade facade, String authorizedUser) {
            this.realProcessor = facade;
            this.authorizedUser = authorizedUser;
        }

        public void setCurrentUser(String user) {
            this.currentUser = user;
        }

        public void processOrder() {
            if (!authorizedUser.equals(currentUser)) {
                System.out.println("Access denied: User " + currentUser + " is not authorized");
                return;
            }
            realProcessor.processOrder();
        }
    }

    // 模拟API类
    static class PayPalAPI {
        public void sendPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through PayPal");
        }
    }

    static class StripeAPI {
        public void charge(double amount) {
            System.out.println("Charging $" + amount + " through Stripe");
        }
    }

    /**
     * 运行高级综合示例
     */
    public static void runAdvancedExample() {
        System.out.println("\n=== 高级综合示例 ===");
        System.out.println("展示多种结构型模式在真实场景中的协同工作\n");

        // 1. 创建产品和订单（组合模式）
        OrderItem laptop = new Product("Laptop", 1000.0);
        OrderItem mouse = new Product("Mouse", 25.0);
        
        OrderComposite order = new OrderComposite("Electronics Order");
        order.addItem(laptop);
        order.addItem(mouse);

        System.out.println("1. 使用组合模式创建订单结构:");
        System.out.println("Order total: $" + order.getPrice());
        System.out.println();

        // 2. 添加税费（装饰器模式）
        OrderItem taxedOrder = new TaxDecorator(order);
        System.out.println("2. 使用装饰器模式添加税费:");
        System.out.println("Order with tax: $" + taxedOrder.getPrice());
        System.out.println();

        // 3. 创建支付渠道（桥接模式 + 适配器模式）
        PaymentProcessor paypalProcessor = new PayPalAdapter();
        PaymentChannel onlineChannel = new OnlineChannel(paypalProcessor);
        
        System.out.println("3. 使用桥接模式和适配器模式整合支付系统:");
        onlineChannel.executePayment(1127.5); // 1000 + 25 + 10% tax
        System.out.println();

        // 4. 使用外观模式简化订单处理
        OrderProcessingFacade facade = new OrderProcessingFacade(onlineChannel, taxedOrder);
        System.out.println("4. 使用外观模式简化订单处理:");
        facade.processOrder();
        System.out.println();

        // 5. 使用享元模式管理权限
        System.out.println("5. 使用享元模式管理用户权限:");
        PermissionType adminPerm = PermissionFactory.getPermission("ADMIN", "Administrator access");
        PermissionType userPerm = PermissionFactory.getPermission("USER", "Basic user access");
        PermissionType adminPerm2 = PermissionFactory.getPermission("ADMIN", "Administrator access"); // 重用
        
        System.out.println("Same admin permission reused: " + (adminPerm == adminPerm2));
        adminPerm.grantPermission("user123");
        System.out.println();

        // 6. 使用代理模式进行安全控制
        System.out.println("6. 使用代理模式进行安全控制:");
        SecureOrderProcessor secureProcessor = new SecureOrderProcessor(facade, "admin_user");
        secureProcessor.setCurrentUser("regular_user");
        secureProcessor.processOrder(); // 应该被拒绝
        
        secureProcessor.setCurrentUser("admin_user");
        secureProcessor.processOrder(); // 应该成功
    }
}