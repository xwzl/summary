package com.java.interview.java.design.behavioral;

/**
 * 策略模式 (Strategy Pattern)
 * 
 * 定义：策略模式是一种行为型设计模式，它定义了一系列算法，并将每种算法分别放入独立的类中，
 * 从而使算法的变化独立于使用它们的客户端。
 * 
 * 主要角色：
 * 1. 策略接口(Strategy)：声明了所有支持的算法的公共操作
 * 2. 具体策略(Concrete Strategy)：实现了策略接口的具体算法
 * 3. 上下文(Context)：使用某种具体策略来实现算法
 * 4. 客户端(Client)：使用上下文对象
 * 
 * 主要特点：
 * 1. 算法可互换：不同的算法可以互相替换
 * 2. 解耦：算法和使用算法的客户端解耦
 * 3. 灵活性：可以动态切换算法
 * 
 * 应用场景：
 * - 系统需要在多种算法中动态选择一种
 * - 需要提供算法的统一接口
 * - 需要避免使用多重条件判断语句
 */
public class StrategyPattern {

    /**
     * 策略接口 - 排序策略
     */
    interface SortingStrategy {
        void sort(int[] arr);
    }

    /**
     * 具体策略 - 快速排序
     */
    static class QuickSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] arr) {
            System.out.println("Sorting using Quick Sort algorithm");
            quickSort(arr, 0, arr.length - 1);
        }

        private void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = (low - 1);

            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }
    }

    /**
     * 具体策略 - 归并排序
     */
    static class MergeSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] arr) {
            System.out.println("Sorting using Merge Sort algorithm");
            mergeSort(arr, 0, arr.length - 1);
        }

        private void mergeSort(int[] arr, int left, int right) {
            if (left < right) {
                int middle = (left + right) / 2;
                mergeSort(arr, left, middle);
                mergeSort(arr, middle + 1, right);
                merge(arr, left, middle, right);
            }
        }

        private void merge(int[] arr, int left, int middle, int right) {
            int n1 = middle - left + 1;
            int n2 = right - middle;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];

            System.arraycopy(arr, left, leftArray, 0, n1);
            System.arraycopy(arr, middle + 1, rightArray, 0, n2);

            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (leftArray[i] <= rightArray[j]) {
                    arr[k] = leftArray[i];
                    i++;
                } else {
                    arr[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                arr[k] = leftArray[i];
                i++;
                k++;
            }

            while (j < n2) {
                arr[k] = rightArray[j];
                j++;
                k++;
            }
        }
    }

    /**
     * 具体策略 - 冒泡排序
     */
    static class BubbleSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] arr) {
            System.out.println("Sorting using Bubble Sort algorithm");
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    /**
     * 上下文 - 排序器
     */
    static class Sorter {
        private SortingStrategy strategy;

        public Sorter(SortingStrategy strategy) {
            this.strategy = strategy;
        }

        public void setStrategy(SortingStrategy strategy) {
            this.strategy = strategy;
        }

        public void executeSort(int[] arr) {
            strategy.sort(arr);
        }
    }

    /**
     * 策略模式的高级应用 - 促销策略
     */
    static class PromotionStrategyExample {
        // 策略接口 - 促销策略
        interface PromotionStrategy {
            double calculateDiscount(double originalPrice);
        }

        // 具体策略 - 满减策略
        static class FullReductionStrategy implements PromotionStrategy {
            private double threshold;
            private double reduction;

            public FullReductionStrategy(double threshold, double reduction) {
                this.threshold = threshold;
                this.reduction = reduction;
            }

            @Override
            public double calculateDiscount(double originalPrice) {
                if (originalPrice >= threshold) {
                    return originalPrice - reduction;
                }
                return originalPrice;
            }
        }

        // 具体策略 - 折扣策略
        static class DiscountStrategy implements PromotionStrategy {
            private double discountRate; // 折扣率，例如0.8表示八折

            public DiscountStrategy(double discountRate) {
                this.discountRate = discountRate;
            }

            @Override
            public double calculateDiscount(double originalPrice) {
                return originalPrice * discountRate;
            }
        }

        // 具体策略 - 买一送一策略
        static class BuyOneGetOneStrategy implements PromotionStrategy {
            @Override
            public double calculateDiscount(double originalPrice) {
                // 假设商品单价为originalPrice/2，买一送一相当于打五折
                return originalPrice / 2.0;
            }
        }

        // 上下文 - 价格计算器
        static class PriceCalculator {
            private PromotionStrategy strategy;

            public PriceCalculator(PromotionStrategy strategy) {
                this.strategy = strategy;
            }

            public void setPromotionStrategy(PromotionStrategy strategy) {
                this.strategy = strategy;
            }

            public double calculateFinalPrice(double originalPrice) {
                return strategy.calculateDiscount(originalPrice);
            }
        }
    }

    /**
     * 策略模式的进一步应用 - 导航策略
     */
    static class NavigationStrategyExample {
        // 策略接口 - 导航策略
        interface NavigationStrategy {
            String navigate(String startPoint, String endPoint);
            double getEstimatedTime();
            double getEstimatedCost();
        }

        // 具体策略 - 步行导航
        static class WalkingStrategy implements NavigationStrategy {
            private double estimatedTime;
            private double estimatedCost;

            public WalkingStrategy() {
                this.estimatedTime = 0.0; // 初始化值，实际会在navigate中计算
                this.estimatedCost = 0.0; // 步行通常免费
            }

            @Override
            public String navigate(String startPoint, String endPoint) {
                String route = "Walking route from " + startPoint + " to " + endPoint;
                this.estimatedTime = calculateTime(startPoint, endPoint);
                return route;
            }

            @Override
            public double getEstimatedTime() {
                return estimatedTime;
            }

            @Override
            public double getEstimatedCost() {
                return estimatedCost;
            }

            private double calculateTime(String start, String end) {
                // 简化计算：假设距离为起点和终点字符串长度差的绝对值乘以5分钟
                return Math.abs(start.length() - end.length()) * 5.0;
            }
        }

        // 具体策略 - 驾车导航
        static class DrivingStrategy implements NavigationStrategy {
            private double estimatedTime;
            private double estimatedCost;

            @Override
            public String navigate(String startPoint, String endPoint) {
                String route = "Driving route from " + startPoint + " to " + endPoint;
                this.estimatedTime = calculateTime(startPoint, endPoint);
                this.estimatedCost = calculateCost(startPoint, endPoint);
                return route;
            }

            @Override
            public double getEstimatedTime() {
                return estimatedTime;
            }

            @Override
            public double getEstimatedCost() {
                return estimatedCost;
            }

            private double calculateTime(String start, String end) {
                // 简化计算
                return Math.abs(start.length() - end.length()) * 2.0;
            }

            private double calculateCost(String start, String end) {
                // 简化计算：假设每公里$0.5
                return Math.abs(start.length() - end.length()) * 0.5;
            }
        }

        // 具体策略 - 公共交通导航
        static class PublicTransportStrategy implements NavigationStrategy {
            private double estimatedTime;
            private double estimatedCost;

            @Override
            public String navigate(String startPoint, String endPoint) {
                String route = "Public transport route from " + startPoint + " to " + endPoint;
                this.estimatedTime = calculateTime(startPoint, endPoint);
                this.estimatedCost = calculateCost(startPoint, endPoint);
                return route;
            }

            @Override
            public double getEstimatedTime() {
                return estimatedTime;
            }

            @Override
            public double getEstimatedCost() {
                return estimatedCost;
            }

            private double calculateTime(String start, String end) {
                // 简化计算
                return Math.abs(start.length() - end.length()) * 3.0;
            }

            private double calculateCost(String start, String end) {
                // 简化计算：假设每次公交费用$2
                return 2.0;
            }
        }

        // 上下文 - 导航系统
        static class NavigationSystem {
            private NavigationStrategy strategy;

            public NavigationSystem(NavigationStrategy strategy) {
                this.strategy = strategy;
            }

            public void setNavigationStrategy(NavigationStrategy strategy) {
                this.strategy = strategy;
            }

            public String getRoute(String startPoint, String endPoint) {
                return strategy.navigate(startPoint, endPoint);
            }

            public void showTripDetails(String startPoint, String endPoint) {
                String route = getRoute(startPoint, endPoint);
                System.out.println(route);
                System.out.println("Estimated time: " + strategy.getEstimatedTime() + " minutes");
                System.out.println("Estimated cost: $" + strategy.getEstimatedCost());
            }
        }
    }

    /**
     * 策略模式的进一步应用 - 支付策略
     */
    static class PaymentStrategyExample {
        // 策略接口 - 支付策略
        interface PaymentStrategy {
            boolean pay(double amount);
            String getPaymentMethod();
        }

        // 具体策略 - 信用卡支付
        static class CreditCardPayment implements PaymentStrategy {
            private String cardNumber;
            private String cvv;
            private String dateOfExpiry;

            public CreditCardPayment(String cardNumber, String cvv, String dateOfExpiry) {
                this.cardNumber = cardNumber;
                this.cvv = cvv;
                this.dateOfExpiry = dateOfExpiry;
            }

            @Override
            public boolean pay(double amount) {
                System.out.println(amount + " paid with credit card: " + cardNumber.substring(0, 4) + "****");
                return true; // 简化处理，假设支付总是成功
            }

            @Override
            public String getPaymentMethod() {
                return "Credit Card";
            }
        }

        // 具体策略 - 支付宝支付
        static class AlipayPayment implements PaymentStrategy {
            private String email;

            public AlipayPayment(String email) {
                this.email = email;
            }

            @Override
            public boolean pay(double amount) {
                System.out.println(amount + " paid using Alipay: " + email);
                return true; // 简化处理
            }

            @Override
            public String getPaymentMethod() {
                return "Alipay";
            }
        }

        // 具体策略 - 微信支付
        static class WechatPayPayment implements PaymentStrategy {
            private String phoneNumber;

            public WechatPayPayment(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            @Override
            public boolean pay(double amount) {
                System.out.println(amount + " paid using WeChat Pay: " + phoneNumber);
                return true; // 简化处理
            }

            @Override
            public String getPaymentMethod() {
                return "WeChat Pay";
            }
        }

        // 上下文 - 购物车
        static class ShoppingCart {
            private PaymentStrategy paymentStrategy;

            public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
                this.paymentStrategy = paymentStrategy;
            }

            public void checkout(double amount) {
                if (paymentStrategy != null) {
                    System.out.println("Processing payment of $" + amount + " using " + paymentStrategy.getPaymentMethod());
                    boolean success = paymentStrategy.pay(amount);
                    if (success) {
                        System.out.println("Payment successful!");
                    } else {
                        System.out.println("Payment failed!");
                    }
                } else {
                    System.out.println("No payment method selected!");
                }
            }
        }
    }

    /**
     * 策略模式的进一步应用 - 日志策略
     */
    static class LoggingStrategyExample {
        // 策略接口 - 日志策略
        interface LoggingStrategy {
            void log(String message);
        }

        // 具体策略 - 控制台日志
        static class ConsoleLoggingStrategy implements LoggingStrategy {
            @Override
            public void log(String message) {
                System.out.println("[CONSOLE] " + java.time.LocalDateTime.now() + " - " + message);
            }
        }

        // 具体策略 - 文件日志
        static class FileLoggingStrategy implements LoggingStrategy {
            @Override
            public void log(String message) {
                System.out.println("[FILE] " + java.time.LocalDateTime.now() + " - " + message + " (written to file)");
                // 在实际应用中，这里会写入文件
            }
        }

        // 具体策略 - 数据库日志
        static class DatabaseLoggingStrategy implements LoggingStrategy {
            @Override
            public void log(String message) {
                System.out.println("[DATABASE] " + java.time.LocalDateTime.now() + " - " + message + " (saved to DB)");
                // 在实际应用中，这里会保存到数据库
            }
        }

        // 上下文 - 日志记录器
        static class Logger {
            private LoggingStrategy strategy;

            public Logger(LoggingStrategy strategy) {
                this.strategy = strategy;
            }

            public void setLoggingStrategy(LoggingStrategy strategy) {
                this.strategy = strategy;
            }

            public void log(String message) {
                strategy.log(message);
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 策略模式 (Strategy Pattern) 示例 ===\n");

        // 基础策略模式示例 - 排序
        System.out.println("1. 基础策略模式示例 - 排序:");
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + java.util.Arrays.toString(arr1));

        Sorter sorter = new Sorter(new QuickSortStrategy());
        sorter.executeSort(arr1);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr1));
        System.out.println();

        // 促销策略示例
        System.out.println("2. 促销策略示例:");
        PromotionStrategyExample.PriceCalculator calculator = 
            new PromotionStrategyExample.PriceCalculator(new PromotionStrategyExample.DiscountStrategy(0.8));
        
        double originalPrice = 100.0;
        double discountedPrice = calculator.calculateFinalPrice(originalPrice);
        System.out.println("Original price: $" + originalPrice + ", Discounted price: $" + discountedPrice);

        // 切换策略
        calculator.setPromotionStrategy(new PromotionStrategyExample.FullReductionStrategy(50, 10));
        double fullReductionPrice = calculator.calculateFinalPrice(originalPrice);
        System.out.println("Full reduction price: $" + fullReductionPrice);
        System.out.println();

        // 导航策略示例
        System.out.println("3. 导航策略示例:");
        NavigationStrategyExample.NavigationSystem navSystem = 
            new NavigationStrategyExample.NavigationSystem(new NavigationStrategyExample.WalkingStrategy());
        
        navSystem.showTripDetails("Point A", "Point B");
        
        // 切换到驾车模式
        navSystem.setNavigationStrategy(new NavigationStrategyExample.DrivingStrategy());
        navSystem.showTripDetails("Point A", "Point B");
        System.out.println();

        // 支付策略示例
        System.out.println("4. 支付策略示例:");
        PaymentStrategyExample.ShoppingCart cart = new PaymentStrategyExample.ShoppingCart();
        
        cart.setPaymentStrategy(new PaymentStrategyExample.CreditCardPayment("1234567890123456", "123", "12/25"));
        cart.checkout(99.99);
        
        cart.setPaymentStrategy(new PaymentStrategyExample.AlipayPayment("user@example.com"));
        cart.checkout(49.99);
        System.out.println();

        // 日志策略示例
        System.out.println("5. 日志策略示例:");
        LoggingStrategyExample.Logger logger = 
            new LoggingStrategyExample.Logger(new LoggingStrategyExample.ConsoleLoggingStrategy());
        
        logger.log("Application started");
        
        logger.setLoggingStrategy(new LoggingStrategyExample.FileLoggingStrategy());
        logger.log("User logged in");
        
        logger.setLoggingStrategy(new LoggingStrategyExample.DatabaseLoggingStrategy());
        logger.log("Transaction completed");
        System.out.println();

        System.out.println("=== 策略模式的关键特性 ===");
        System.out.println("1. 算法可互换: 不同的算法可以互相替换");
        System.out.println("2. 解耦: 算法和使用算法的客户端解耦");
        System.out.println("3. 灵活性: 可以动态切换算法");
        System.out.println("4. 扩展性: 新增算法无需修改现有代码");
        System.out.println("5. 开闭原则: 对扩展开放，对修改关闭");
        System.out.println("6. 简化单元测试: 每个策略可以独立测试");
    }
}