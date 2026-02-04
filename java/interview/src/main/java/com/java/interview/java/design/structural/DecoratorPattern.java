package com.java.interview.java.design.structural;

/**
 * 装饰器模式 (Decorator Pattern)
 * 
 * 定义：装饰器模式是一种结构型设计模式，它允许你动态地给对象添加一些额外的职责，
 * 而不改变其原始类的代码。装饰器模式通过创建一个包装对象（即装饰器）来实现，
 * 该包装对象包含原对象并在其行为前后添加新的功能。
 * 
 * 主要角色：
 * 1. 抽象组件(Component)：定义一个对象接口，可以给这些对象动态地添加职责
 * 2. 具体组件(Concrete Component)：定义一个将要接收附加职责的对象
 * 3. 抽象装饰器(Decorator)：维持一个指向组件对象的指针，并定义一个与组件接口一致的接口
 * 4. 具体装饰器(Concrete Decorator)：向组件添加职责
 * 
 * 主要特点：
 * 1. 动态添加功能：可以在运行时动态地给对象添加功能
 * 2. 灵活性：比继承更灵活，可以动态地添加和移除功能
 * 3. 透明性：客户端不需要知道对象是否被装饰过
 * 
 * 应用场景：
 * - 在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责
 * - 需要动态地给一个对象增加功能，这些功能还可以动态地撤销
 * - 需要增加由一些基本功能的排列组合而产生的非常大量的功能
 * - 不能使用继承进行扩展的情况
 */
public class DecoratorPattern {

    /**
     * 抽象组件 - 咖啡接口
     */
    interface Coffee {
        String getDescription();
        double cost();
    }

    /**
     * 具体组件 - 基础咖啡
     */
    static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() {
            return "Simple coffee";
        }

        @Override
        public double cost() {
            return 1.00;
        }
    }

    /**
     * 抽象装饰器 - 咖啡装饰器
     */
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        @Override
        public String getDescription() {
            return decoratedCoffee.getDescription();
        }

        @Override
        public double cost() {
            return decoratedCoffee.cost();
        }
    }

    /**
     * 具体装饰器 - 牛奶装饰器
     */
    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Milk";
        }

        @Override
        public double cost() {
            return super.cost() + 0.50;
        }
    }

    /**
     * 具体装饰器 - 糖装饰器
     */
    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Sugar";
        }

        @Override
        public double cost() {
            return super.cost() + 0.25;
        }
    }

    /**
     * 具体装饰器 - 巧克力装饰器
     */
    static class ChocolateDecorator extends CoffeeDecorator {
        public ChocolateDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Chocolate";
        }

        @Override
        public double cost() {
            return super.cost() + 0.75;
        }
    }

    /**
     * 具体装饰器 - 奶泡装饰器
     */
    static class FoamDecorator extends CoffeeDecorator {
        public FoamDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Foam";
        }

        @Override
        public double cost() {
            return super.cost() + 0.30;
        }
    }

    /**
     * 装饰器模式的高级应用 - 数据流处理
     */
    static class DataStreamDecorator {
        // 抽象组件 - 数据流接口
        interface DataStream {
            String readData();
            void writeData(String data);
        }

        // 具体组件 - 基础文件流
        static class FileStream implements DataStream {
            private String fileName;
            private String data;

            public FileStream(String fileName) {
                this.fileName = fileName;
                this.data = "";
            }

            @Override
            public String readData() {
                System.out.println("Reading data from file: " + fileName);
                return data;
            }

            @Override
            public void writeData(String data) {
                System.out.println("Writing data to file: " + fileName);
                this.data = data;
            }
        }

        // 抽象装饰器 - 数据流装饰器
        static abstract class DataStreamDecoratorBase implements DataStream {
            protected DataStream dataStream;

            public DataStreamDecoratorBase(DataStream stream) {
                this.dataStream = stream;
            }

            @Override
            public String readData() {
                return dataStream.readData();
            }

            @Override
            public void writeData(String data) {
                dataStream.writeData(data);
            }
        }

        // 具体装饰器 - 加密装饰器
        static class EncryptionDecorator extends DataStreamDecoratorBase {
            public EncryptionDecorator(DataStream stream) {
                super(stream);
            }

            @Override
            public String readData() {
                String encryptedData = super.readData();
                return decrypt(encryptedData);
            }

            @Override
            public void writeData(String data) {
                String encryptedData = encrypt(data);
                super.writeData(encryptedData);
            }

            private String encrypt(String data) {
                // 简单的加密实现（实际中应使用强加密算法）
                StringBuilder encrypted = new StringBuilder();
                for (char c : data.toCharArray()) {
                    encrypted.append((char)(c + 1));
                }
                System.out.println("Encrypting data: " + data + " -> " + encrypted.toString());
                return encrypted.toString();
            }

            private String decrypt(String data) {
                // 简单的解密实现
                StringBuilder decrypted = new StringBuilder();
                for (char c : data.toCharArray()) {
                    decrypted.append((char)(c - 1));
                }
                System.out.println("Decrypting data: " + data + " -> " + decrypted.toString());
                return decrypted.toString();
            }
        }

        // 具体装饰器 - 压缩装饰器
        static class CompressionDecorator extends DataStreamDecoratorBase {
            public CompressionDecorator(DataStream stream) {
                super(stream);
            }

            @Override
            public String readData() {
                String compressedData = super.readData();
                return decompress(compressedData);
            }

            @Override
            public void writeData(String data) {
                String compressedData = compress(data);
                super.writeData(compressedData);
            }

            private String compress(String data) {
                System.out.println("Compressing data: " + data);
                // 简化的压缩逻辑（实际中应使用真实的压缩算法）
                return data.replace(" ", "_"); // 简单替换空格为下划线作为示例
            }

            private String decompress(String data) {
                System.out.println("Decompressing data: " + data);
                // 简化的解压逻辑
                return data.replace("_", " "); // 简单替换下划线为空格作为示例
            }
        }

        // 具体装饰器 - 缓存装饰器
        static class CacheDecorator extends DataStreamDecoratorBase {
            private String cachedData = null;

            public CacheDecorator(DataStream stream) {
                super(stream);
            }

            @Override
            public String readData() {
                if (cachedData != null) {
                    System.out.println("Returning cached data");
                    return cachedData;
                }
                cachedData = super.readData();
                System.out.println("Caching data: " + cachedData);
                return cachedData;
            }

            @Override
            public void writeData(String data) {
                cachedData = data; // 更新缓存
                super.writeData(data);
            }
        }
    }

    /**
     * 装饰器模式的进一步应用 - GUI组件
     */
    static class GuiDecorator {
        // 抽象组件 - 窗口接口
        interface Window {
            void draw();
            String getDescription();
        }

        // 具体组件 - 基础窗口
        static class SimpleWindow implements Window {
            @Override
            public void draw() {
                System.out.println("Drawing a simple window");
            }

            @Override
            public String getDescription() {
                return "Simple window";
            }
        }

        // 抽象装饰器 - 窗口装饰器
        static abstract class WindowDecorator implements Window {
            protected Window window;

            public WindowDecorator(Window window) {
                this.window = window;
            }

            @Override
            public void draw() {
                window.draw();
            }

            @Override
            public String getDescription() {
                return window.getDescription();
            }
        }

        // 具体装饰器 - 边框装饰器
        static class BorderDecorator extends WindowDecorator {
            public BorderDecorator(Window window) {
                super(window);
            }

            @Override
            public void draw() {
                super.draw();
                drawBorder();
            }

            @Override
            public String getDescription() {
                return super.getDescription() + " with border";
            }

            private void drawBorder() {
                System.out.println("Adding border to the window");
            }
        }

        // 具体装饰器 - 滚动条装饰器
        static class ScrollBarDecorator extends WindowDecorator {
            public ScrollBarDecorator(Window window) {
                super(window);
            }

            @Override
            public void draw() {
                super.draw();
                drawScrollBar();
            }

            @Override
            public String getDescription() {
                return super.getDescription() + " with scroll bar";
            }

            private void drawScrollBar() {
                System.out.println("Adding scroll bar to the window");
            }
        }

        // 具体装饰器 - 标题栏装饰器
        static class TitleBarDecorator extends WindowDecorator {
            private String title;

            public TitleBarDecorator(Window window, String title) {
                super(window);
                this.title = title;
            }

            @Override
            public void draw() {
                super.draw();
                drawTitleBar();
            }

            @Override
            public String getDescription() {
                return super.getDescription() + " with title bar (" + title + ")";
            }

            private void drawTitleBar() {
                System.out.println("Adding title bar with title: " + title);
            }
        }
    }

    /**
     * 装饰器模式的进一步应用 - 认证和授权
     */
    static class SecurityDecorator {
        // 抽象组件 - 服务接口
        interface Service {
            String execute(String request);
        }

        // 具体组件 - 基础服务
        static class BasicService implements Service {
            @Override
            public String execute(String request) {
                return "Executing basic service with request: " + request;
            }
        }

        // 抽象装饰器 - 服务装饰器
        static abstract class ServiceDecorator implements Service {
            protected Service service;

            public ServiceDecorator(Service service) {
                this.service = service;
            }

            @Override
            public String execute(String request) {
                return service.execute(request);
            }
        }

        // 具体装饰器 - 认证装饰器
        static class AuthenticationDecorator extends ServiceDecorator {
            private String username;
            private String password;

            public AuthenticationDecorator(Service service, String username, String password) {
                super(service);
                this.username = username;
                this.password = password;
            }

            @Override
            public String execute(String request) {
                if (authenticate()) {
                    System.out.println("Authentication successful for user: " + username);
                    return super.execute(request);
                } else {
                    return "Authentication failed for user: " + username;
                }
            }

            private boolean authenticate() {
                // 简化的认证逻辑
                return "admin".equals(username) && "password".equals(password);
            }
        }

        // 具体装饰器 - 授权装饰器
        static class AuthorizationDecorator extends ServiceDecorator {
            private String role;

            public AuthorizationDecorator(Service service, String role) {
                super(service);
                this.role = role;
            }

            @Override
            public String execute(String request) {
                if (authorize()) {
                    System.out.println("Authorization successful for role: " + role);
                    return super.execute(request);
                } else {
                    return "Authorization failed for role: " + role;
                }
            }

            private boolean authorize() {
                // 简化的授权逻辑
                return "admin".equals(role) || "user".equals(role);
            }
        }

        // 具体装饰器 - 日志装饰器
        static class LoggingDecorator extends ServiceDecorator {
            public LoggingDecorator(Service service) {
                super(service);
            }

            @Override
            public String execute(String request) {
                System.out.println("Logging: Request received - " + request);
                String result = super.execute(request);
                System.out.println("Logging: Response sent - " + result);
                return result;
            }
        }
    }

    /**
     * 装饰器模式的链式应用示例
     */
    static class ChainDecoratorExample {
        // 抽象组件 - 文本处理器
        interface TextProcessor {
            String process(String text);
        }

        // 具体组件 - 基础文本处理器
        static class BaseTextProcessor implements TextProcessor {
            @Override
            public String process(String text) {
                return text;
            }
        }

        // 抽象装饰器
        static abstract class TextProcessorDecorator implements TextProcessor {
            protected TextProcessor processor;

            public TextProcessorDecorator(TextProcessor processor) {
                this.processor = processor;
            }

            @Override
            public String process(String text) {
                return processor.process(text);
            }
        }

        // 具体装饰器 - 大写装饰器
        static class UpperCaseDecorator extends TextProcessorDecorator {
            public UpperCaseDecorator(TextProcessor processor) {
                super(processor);
            }

            @Override
            public String process(String text) {
                return super.process(text).toUpperCase();
            }
        }

        // 具体装饰器 - 去空格装饰器
        static class TrimDecorator extends TextProcessorDecorator {
            public TrimDecorator(TextProcessor processor) {
                super(processor);
            }

            @Override
            public String process(String text) {
                return super.process(text).trim();
            }
        }

        // 具体装饰器 - 替换装饰器
        static class ReplaceDecorator extends TextProcessorDecorator {
            private String target;
            private String replacement;

            public ReplaceDecorator(TextProcessor processor, String target, String replacement) {
                super(processor);
                this.target = target;
                this.replacement = replacement;
            }

            @Override
            public String process(String text) {
                return super.process(text).replace(target, replacement);
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 装饰器模式 (Decorator Pattern) 示例 ===\n");

        // 基础装饰器模式示例 - 咖啡
        System.out.println("1. 基础装饰器模式示例 - 咖啡:");
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        // 添加牛奶
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        // 再添加糖
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        // 再添加巧克力
        coffee = new ChocolateDecorator(coffee);
        System.out.println(coffee.getDescription() + ": $" + coffee.cost());

        // 最后再加奶泡
        Coffee fancyCoffee = new FoamDecorator(coffee);
        System.out.println(fancyCoffee.getDescription() + ": $" + fancyCoffee.cost());
        System.out.println();

        // 数据流装饰器示例
        System.out.println("2. 数据流装饰器示例:");
        DataStreamDecorator.DataStream fileStream = new DataStreamDecorator.FileStream("data.txt");
        DataStreamDecorator.DataStream securedStream = 
            new DataStreamDecorator.EncryptionDecorator(
                new DataStreamDecorator.CompressionDecorator(fileStream)
            );
        
        securedStream.writeData("Hello, World!");
        String result = securedStream.readData();
        System.out.println("Final result: " + result);
        System.out.println();

        // GUI装饰器示例
        System.out.println("3. GUI装饰器示例:");
        GuiDecorator.Window window = new GuiDecorator.SimpleWindow();
        window = new GuiDecorator.BorderDecorator(window);
        window = new GuiDecorator.ScrollBarDecorator(window);
        window = new GuiDecorator.TitleBarDecorator(window, "Main Window");
        
        System.out.println("Window: " + window.getDescription());
        window.draw();
        System.out.println();

        // 安全装饰器示例
        System.out.println("4. 安全装饰器示例:");
        SecurityDecorator.Service service = new SecurityDecorator.BasicService();
        service = new SecurityDecorator.LoggingDecorator(service);
        service = new SecurityDecorator.AuthenticationDecorator(service, "admin", "password");
        service = new SecurityDecorator.AuthorizationDecorator(service, "admin");
        
        String response = service.execute("GET /api/data");
        System.out.println("Response: " + response);
        System.out.println();

        // 链式装饰器示例
        System.out.println("5. 链式装饰器示例:");
        ChainDecoratorExample.TextProcessor processor = new ChainDecoratorExample.BaseTextProcessor();
        processor = new ChainDecoratorExample.UpperCaseDecorator(processor);
        processor = new ChainDecoratorExample.TrimDecorator(processor);
        processor = new ChainDecoratorExample.ReplaceDecorator(processor, "HELLO", "HI");
        
        String processedText = processor.process("  hello world  ");
        System.out.println("Processed text: '" + processedText + "'");
        System.out.println();

        System.out.println("=== 装饰器模式的关键特性 ===");
        System.out.println("1. 动态添加功能: 可以在运行时动态地给对象添加功能");
        System.out.println("2. 灵活性: 比继承更灵活，可以动态地添加和移除功能");
        System.out.println("3. 透明性: 客户端不需要知道对象是否被装饰过");
        System.out.println("4. 组合优于继承: 使用组合关系来扩展功能");
        System.out.println("5. 开闭原则: 对扩展开放，对修改关闭");
        System.out.println("6. 可定制性: 可以按需组合不同的装饰器");
    }
}