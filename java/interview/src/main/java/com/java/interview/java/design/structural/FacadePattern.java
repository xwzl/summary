package com.java.interview.java.design.structural;

/**
 * 外观模式 (Facade Pattern)
 * 
 * 定义：外观模式是一种结构型设计模式，它为子系统中的一组接口提供一个统一的接口。
 * 外观模式定义了一个高层接口，让子系统更容易使用。它隐藏了系统的复杂性，
 * 并向客户端提供了一个简单的接口来访问子系统。
 * 
 * 主要角色：
 * 1. 外观(Facade)：知道哪些子系统类负责处理请求，并将客户的请求委派给适当的子系统对象
 * 2. 子系统类(SubSystem Classes)：实现子系统的功能，处理外观分配的任务，没有外观的任何相关信息
 * 3. 客户端(Client)：通过外观接口调用子系统，不直接访问子系统内部对象
 * 
 * 主要特点：
 * 1. 简化接口：为复杂的子系统提供简单的接口
 * 2. 解耦：减少了客户端与子系统的耦合度
 * 3. 分层：提供了子系统和客户端之间清晰的层次结构
 * 
 * 应用场景：
 * - 当要为一个复杂子系统提供一个简单接口时
 * - 客户程序与抽象类的实现部分之间存在很大依赖性时
 * - 当需要构建一个层次结构的子系统时，使用外观模式定义子系统中每一层的入口点
 */
public class FacadePattern {

    /**
     * 子系统类 - 银行系统
     */
    static class Bank {
        public boolean hasSufficientSavings(Customer customer, int amount) {
            System.out.println("Bank: Checking customer's savings account for " + customer.getName());
            return customer.getSavings() >= amount;
        }
    }

    /**
     * 子系统类 - 信用系统
     */
    static class Credit {
        public boolean hasGoodCredit(Customer customer) {
            System.out.println("Credit: Checking customer's credit score for " + customer.getName());
            return customer.getCreditScore() > 700; // 假设700以上为良好信用
        }
    }

    /**
     * 子系统类 - 贷款系统
     */
    static class Loan {
        public boolean hasNoBadLoans(Customer customer) {
            System.out.println("Loan: Checking customer's loan history for " + customer.getName());
            return !customer.hasBadLoans();
        }
    }

    /**
     * 客户类
     */
    static class Customer {
        private String name;
        private int savings;
        private int creditScore;
        private boolean hasBadLoans;

        public Customer(String name, int savings, int creditScore, boolean hasBadLoans) {
            this.name = name;
            this.savings = savings;
            this.creditScore = creditScore;
            this.hasBadLoans = hasBadLoans;
        }

        public String getName() {
            return name;
        }

        public int getSavings() {
            return savings;
        }

        public int getCreditScore() {
            return creditScore;
        }

        public boolean hasBadLoans() {
            return hasBadLoans;
        }
    }

    /**
     * 外观 - 银行贷款申请系统
     */
    static class LoanApplicationFacade {
        private Bank bank;
        private Credit credit;
        private Loan loan;

        public LoanApplicationFacade() {
            this.bank = new Bank();
            this.credit = new Credit();
            this.loan = new Loan();
        }

        public boolean approveLoan(Customer customer, int loanAmount) {
            System.out.println("Applying for loan for customer: " + customer.getName());
            
            boolean eligible = bank.hasSufficientSavings(customer, loanAmount) &&
                              credit.hasGoodCredit(customer) &&
                              loan.hasNoBadLoans(customer);

            if (eligible) {
                System.out.println("Loan approved for customer: " + customer.getName());
            } else {
                System.out.println("Loan rejected for customer: " + customer.getName());
            }
            
            return eligible;
        }
    }

    /**
     * 外观模式的高级应用 - 家庭自动化系统
     */
    static class HomeAutomationFacade {
        // 子系统类 - 灯光系统
        static class LightSystem {
            public void turnOnLights() {
                System.out.println("Turning on all lights in the house");
            }

            public void turnOffLights() {
                System.out.println("Turning off all lights in the house");
            }

            public void setBrightness(int level) {
                System.out.println("Setting light brightness to: " + level + "%");
            }
        }

        // 子系统类 - 安防系统
        static class SecuritySystem {
            public void armSystem() {
                System.out.println("Arming home security system");
            }

            public void disarmSystem() {
                System.out.println("Disarming home security system");
            }

            public void activateAlarm() {
                System.out.println("Activating security alarm");
            }
        }

        // 子系统类 - 空调系统
        static class AirConditioningSystem {
            public void turnOnAC() {
                System.out.println("Turning on air conditioning");
            }

            public void turnOffAC() {
                System.out.println("Turning off air conditioning");
            }

            public void setTemperature(int temp) {
                System.out.println("Setting AC temperature to: " + temp + "°C");
            }
        }

        // 子系统类 - 娱乐系统
        static class EntertainmentSystem {
            public void turnOnTV() {
                System.out.println("Turning on TV");
            }

            public void turnOffTV() {
                System.out.println("Turning off TV");
            }

            public void setVolume(int volume) {
                System.out.println("Setting TV volume to: " + volume);
            }

            public void selectChannel(String channel) {
                System.out.println("Selecting TV channel: " + channel);
            }
        }

        // 子系统类 - 窗帘系统
        static class CurtainSystem {
            public void openCurtains() {
                System.out.println("Opening curtains");
            }

            public void closeCurtains() {
                System.out.println("Closing curtains");
            }
        }

        // 外观 - 家庭自动化系统
        static class SmartHomeFacade {
            private LightSystem lightSystem;
            private SecuritySystem securitySystem;
            private AirConditioningSystem acSystem;
            private EntertainmentSystem entertainmentSystem;
            private CurtainSystem curtainSystem;

            public SmartHomeFacade() {
                this.lightSystem = new LightSystem();
                this.securitySystem = new SecuritySystem();
                this.acSystem = new AirConditioningSystem();
                this.entertainmentSystem = new EntertainmentSystem();
                this.curtainSystem = new CurtainSystem();
            }

            public void leaveHome() {
                System.out.println("\n--- Leaving Home Sequence ---");
                entertainmentSystem.turnOffTV();
                acSystem.turnOffAC();
                lightSystem.turnOffLights();
                securitySystem.armSystem();
                curtainSystem.closeCurtains();
                System.out.println("Home automation: Left home successfully\n");
            }

            public void arriveHome() {
                System.out.println("\n--- Arriving Home Sequence ---");
                securitySystem.disarmSystem();
                lightSystem.turnOnLights();
                curtainSystem.openCurtains();
                acSystem.turnOnAC();
                System.out.println("Home automation: Arrived home successfully\n");
            }

            public void watchMovie() {
                System.out.println("\n--- Watch Movie Sequence ---");
                lightSystem.setBrightness(20);
                entertainmentSystem.turnOnTV();
                entertainmentSystem.selectChannel("Movie Channel");
                entertainmentSystem.setVolume(15);
                curtainSystem.closeCurtains();
                System.out.println("Home automation: Ready to watch movie\n");
            }

            public void goToSleep() {
                System.out.println("\n--- Going to Sleep Sequence ---");
                entertainmentSystem.turnOffTV();
                lightSystem.setBrightness(10);
                acSystem.setTemperature(22);
                System.out.println("Home automation: Ready for sleep\n");
            }
        }
    }

    /**
     * 外观模式的进一步应用 - 订单处理系统
     */
    static class OrderProcessingFacade {
        // 子系统类 - 库存管理
        static class InventorySystem {
            public boolean checkAvailability(String productId) {
                System.out.println("Checking inventory for product: " + productId);
                // 简化逻辑：假设所有产品都有库存
                return true;
            }

            public void updateInventory(String productId, int quantity) {
                System.out.println("Updating inventory for product: " + productId + ", quantity: " + quantity);
            }
        }

        // 子系统类 - 支付处理
        static class PaymentSystem {
            public boolean processPayment(String customerId, double amount, String paymentMethod) {
                System.out.println("Processing payment for customer: " + customerId + 
                                 ", amount: $" + amount + ", method: " + paymentMethod);
                // 简化逻辑：假设所有支付都成功
                return true;
            }
        }

        // 子系统类 - 订单管理
        static class OrderManagementSystem {
            public String createOrder(String customerId, String productId, int quantity) {
                System.out.println("Creating order for customer: " + customerId + 
                                 ", product: " + productId + ", quantity: " + quantity);
                // 返回订单号
                return "ORDER-" + System.currentTimeMillis();
            }

            public void updateOrderStatus(String orderId, String status) {
                System.out.println("Updating order " + orderId + " status to: " + status);
            }
        }

        // 子系统类 - 物流配送
        static class ShippingSystem {
            public void shipOrder(String orderId, String shippingAddress) {
                System.out.println("Shipping order: " + orderId + " to address: " + shippingAddress);
            }

            public void updateTrackingInfo(String orderId, String trackingNumber) {
                System.out.println("Updating tracking info for order: " + orderId + 
                                 ", tracking number: " + trackingNumber);
            }
        }

        // 外观 - 订单处理系统
        static class OrderProcessingFacadeSystem {
            private InventorySystem inventory;
            private PaymentSystem payment;
            private OrderManagementSystem orderManagement;
            private ShippingSystem shipping;

            public OrderProcessingFacadeSystem() {
                this.inventory = new InventorySystem();
                this.payment = new PaymentSystem();
                this.orderManagement = new OrderManagementSystem();
                this.shipping = new ShippingSystem();
            }

            public boolean processOrder(String customerId, String productId, int quantity, 
                                      double amount, String paymentMethod, String shippingAddress) {
                System.out.println("\n--- Processing Order ---");
                
                // 检查库存
                if (!inventory.checkAvailability(productId)) {
                    System.out.println("Order processing failed: Insufficient inventory");
                    return false;
                }

                // 处理支付
                if (!payment.processPayment(customerId, amount, paymentMethod)) {
                    System.out.println("Order processing failed: Payment declined");
                    return false;
                }

                // 创建订单
                String orderId = orderManagement.createOrder(customerId, productId, quantity);
                orderManagement.updateOrderStatus(orderId, "CONFIRMED");

                // 更新库存
                inventory.updateInventory(productId, quantity);

                // 发货
                shipping.shipOrder(orderId, shippingAddress);
                shipping.updateTrackingInfo(orderId, "SHIP-" + System.currentTimeMillis());

                orderManagement.updateOrderStatus(orderId, "SHIPPED");

                System.out.println("Order " + orderId + " processed successfully!\n");
                return true;
            }
        }
    }

    /**
     * 外观模式的进一步应用 - 数据库连接池
     */
    static class DatabaseConnectionFacade {
        // 子系统类 - 连接管理
        static class ConnectionManager {
            public void initializePool() {
                System.out.println("Initializing database connection pool");
            }

            public Object getConnection() {
                System.out.println("Getting connection from pool");
                return new Object(); // 简化：返回一个对象代表连接
            }

            public void releaseConnection(Object connection) {
                System.out.println("Releasing connection back to pool");
            }

            public void closePool() {
                System.out.println("Closing database connection pool");
            }
        }

        // 子系统类 - 查询优化
        static class QueryOptimizer {
            public String optimizeQuery(String sql) {
                System.out.println("Optimizing SQL query: " + sql);
                return sql; // 简化：直接返回原查询
            }
        }

        // 子系统类 - 结果集处理
        static class ResultSetHandler {
            public void processResults(Object results) {
                System.out.println("Processing query results");
            }
        }

        // 子系统类 - 事务管理
        static class TransactionManager {
            public void beginTransaction() {
                System.out.println("Beginning transaction");
            }

            public void commitTransaction() {
                System.out.println("Committing transaction");
            }

            public void rollbackTransaction() {
                System.out.println("Rolling back transaction");
            }
        }

        // 外观 - 数据库连接池系统
        static class DatabaseFacade {
            private ConnectionManager connectionManager;
            private QueryOptimizer queryOptimizer;
            private ResultSetHandler resultSetHandler;
            private TransactionManager transactionManager;

            public DatabaseFacade() {
                this.connectionManager = new ConnectionManager();
                this.queryOptimizer = new QueryOptimizer();
                this.resultSetHandler = new ResultSetHandler();
                this.transactionManager = new TransactionManager();
            }

            public void executeQuery(String sql) {
                System.out.println("\n--- Executing Database Query ---");
                transactionManager.beginTransaction();
                
                try {
                    String optimizedSql = queryOptimizer.optimizeQuery(sql);
                    Object connection = connectionManager.getConnection();
                    // 模拟执行查询和处理结果
                    Object results = new Object(); // 简化：模拟查询结果
                    resultSetHandler.processResults(results);
                    
                    transactionManager.commitTransaction();
                    System.out.println("Query executed successfully\n");
                } catch (Exception e) {
                    transactionManager.rollbackTransaction();
                    System.out.println("Query execution failed: " + e.getMessage() + "\n");
                }
            }

            public void initialize() {
                connectionManager.initializePool();
            }

            public void shutdown() {
                connectionManager.closePool();
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 外观模式 (Facade Pattern) 示例 ===\n");

        // 基础外观模式示例 - 贷款申请
        System.out.println("1. 基础外观模式示例 - 贷款申请:");
        LoanApplicationFacade loanFacade = new LoanApplicationFacade();
        
        Customer goodCustomer = new Customer("John Doe", 50000, 750, false);
        Customer badCustomer = new Customer("Jane Smith", 1000, 600, true);
        
        loanFacade.approveLoan(goodCustomer, 10000);
        loanFacade.approveLoan(badCustomer, 10000);
        System.out.println();

        // 家庭自动化系统示例
        System.out.println("2. 家庭自动化系统示例:");
        HomeAutomationFacade.SmartHomeFacade smartHome = 
            new HomeAutomationFacade.SmartHomeFacade();
        
        smartHome.arriveHome();
        smartHome.watchMovie();
        smartHome.goToSleep();
        smartHome.leaveHome();
        System.out.println();

        // 订单处理系统示例
        System.out.println("3. 订单处理系统示例:");
        OrderProcessingFacade.OrderProcessingFacadeSystem orderFacade = 
            new OrderProcessingFacade.OrderProcessingFacadeSystem();
        
        orderFacade.processOrder(
            "CUST-001", 
            "PROD-123", 
            2, 
            99.99, 
            "CREDIT_CARD", 
            "123 Main St, City, State"
        );
        System.out.println();

        // 数据库连接池示例
        System.out.println("4. 数据库连接池示例:");
        DatabaseConnectionFacade.DatabaseFacade dbFacade = 
            new DatabaseConnectionFacade.DatabaseFacade();
        
        dbFacade.initialize();
        dbFacade.executeQuery("SELECT * FROM users WHERE active = 1");
        dbFacade.shutdown();
        System.out.println();

        System.out.println("=== 外观模式的关键特性 ===");
        System.out.println("1. 简化接口: 为复杂的子系统提供简单的接口");
        System.out.println("2. 解耦: 减少了客户端与子系统的耦合度");
        System.out.println("3. 分层: 提供了子系统和客户端之间清晰的层次结构");
        System.out.println("4. 隔离: 客户端不需要了解子系统的内部实现");
        System.out.println("5. 易用性: 使复杂的系统更容易使用");
        System.out.println("6. 可维护性: 降低了系统的复杂性，提高了可维护性");
    }
}