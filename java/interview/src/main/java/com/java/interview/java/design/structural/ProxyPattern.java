package com.java.interview.java.design.structural;

/**
 * 代理模式 (Proxy Pattern)
 * 
 * 定义：代理模式是一种结构型设计模式，它为其他对象提供一个替身或占位符来控制对这个对象的访问。
 * 代理模式在客户端和目标对象之间起到中介的作用，可以实现对目标对象的间接访问。
 * 
 * 主要角色：
 * 1. 抽象主题(Subject)：定义真实主题和代理主题的共同接口
 * 2. 真实主题(Real Subject)：定义了代理角色所代表的真实对象
 * 3. 代理(Proxy)：持有真实主题的引用，控制对真实主题的访问
 * 4. 客户端(Client)：通过代理访问真实主题
 * 
 * 主要特点：
 * 1. 控制访问：可以控制对真实对象的访问
 * 2. 延迟初始化：可以延迟创建昂贵资源的实例
 * 3. 安全控制：可以在访问真实对象前进行权限检查
 * 
 * 应用场景：
 * - 远程代理：为一个对象在不同的地址空间提供局部代表
 * - 虚代理：根据需要创建开销很大的对象
 * - 保护代理：控制对原始对象的访问
 * - 智能引用：取代了简单的指针，可以在访问对象时执行一些附加操作
 */
public class ProxyPattern {

    /**
     * 抽象主题 - 图像接口
     */
    interface Image {
        void display();
        String getName();
    }

    /**
     * 真实主题 - 真实图像
     */
    static class RealImage implements Image {
        private String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadImageFromDisk();
        }

        private void loadImageFromDisk() {
            System.out.println("Loading image: " + fileName);
            // 模拟加载时间
            try {
                Thread.sleep(1000); // 模拟耗时操作
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Image " + fileName + " loaded successfully");
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + fileName);
        }

        @Override
        public String getName() {
            return fileName;
        }
    }

    /**
     * 代理 - 图像代理
     */
    static class ImageProxy implements Image {
        private String fileName;
        private RealImage realImage;

        public ImageProxy(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }

        @Override
        public String getName() {
            return fileName;
        }
    }

    /**
     * 代理模式的高级应用 - 虚拟代理
     */
    static class VirtualProxyExample {
        // 抽象主题 - 文档接口
        interface Document {
            void open();
            void save();
        }

        // 真实主题 - 重型文档
        static class HeavyDocument implements Document {
            private String fileName;

            public HeavyDocument(String fileName) {
                this.fileName = fileName;
                System.out.println("Loading heavy document: " + fileName);
                // 模拟加载大型文档
                try {
                    Thread.sleep(2000); // 模拟耗时的加载过程
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Heavy document " + fileName + " loaded completely");
            }

            @Override
            public void open() {
                System.out.println("Opening document: " + fileName);
            }

            @Override
            public void save() {
                System.out.println("Saving document: " + fileName);
            }
        }

        // 虚拟代理 - 延迟加载文档
        static class VirtualDocumentProxy implements Document {
            private String fileName;
            private HeavyDocument realDocument;

            public VirtualDocumentProxy(String fileName) {
                this.fileName = fileName;
                System.out.println("Virtual proxy created for document: " + fileName);
            }

            @Override
            public void open() {
                if (realDocument == null) {
                    System.out.println("Lazy loading document: " + fileName);
                    realDocument = new HeavyDocument(fileName);
                }
                realDocument.open();
            }

            @Override
            public void save() {
                if (realDocument == null) {
                    System.out.println("Lazy loading document: " + fileName);
                    realDocument = new HeavyDocument(fileName);
                }
                realDocument.save();
            }
        }
    }

    /**
     * 代理模式的进一步应用 - 保护代理
     */
    static class ProtectionProxyExample {
        // 抽象主题 - 银行账户接口
        interface BankAccount {
            void deposit(double amount);
            void withdraw(double amount);
            double getBalance();
        }

        // 真实主题 - 实际银行账户
        static class RealBankAccount implements BankAccount {
            private double balance;
            private String owner;

            public RealBankAccount(String owner, double initialBalance) {
                this.owner = owner;
                this.balance = initialBalance;
                System.out.println("Bank account created for: " + owner + " with initial balance: $" + initialBalance);
            }

            @Override
            public void deposit(double amount) {
                if (amount > 0) {
                    balance += amount;
                    System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
                }
            }

            @Override
            public void withdraw(double amount) {
                if (amount > 0 && balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrew: $" + amount + ", Remaining balance: $" + balance);
                } else {
                    System.out.println("Insufficient funds or invalid amount for withdrawal");
                }
            }

            @Override
            public double getBalance() {
                return balance;
            }
        }

        // 保护代理 - 带权限检查的银行账户
        static class ProtectedBankAccountProxy implements BankAccount {
            private RealBankAccount realAccount;
            private String authorizedUser;
            private String currentUser;

            public ProtectedBankAccountProxy(String owner, double initialBalance, String authorizedUser) {
                this.realAccount = new RealBankAccount(owner, initialBalance);
                this.authorizedUser = authorizedUser;
            }

            public void setCurrentUser(String currentUser) {
                this.currentUser = currentUser;
            }

            private boolean isAuthorized() {
                return authorizedUser.equals(currentUser);
            }

            @Override
            public void deposit(double amount) {
                if (!isAuthorized()) {
                    System.out.println("Access denied: " + currentUser + " is not authorized to deposit");
                    return;
                }
                realAccount.deposit(amount);
            }

            @Override
            public void withdraw(double amount) {
                if (!isAuthorized()) {
                    System.out.println("Access denied: " + currentUser + " is not authorized to withdraw");
                    return;
                }
                realAccount.withdraw(amount);
            }

            @Override
            public double getBalance() {
                if (!isAuthorized()) {
                    System.out.println("Access denied: " + currentUser + " is not authorized to check balance");
                    return -1;
                }
                return realAccount.getBalance();
            }
        }
    }

    /**
     * 代理模式的进一步应用 - 远程代理模拟
     */
    static class RemoteProxyExample {
        // 抽象主题 - 网络服务接口
        interface NetworkService {
            String requestData(String query);
            boolean ping();
        }

        // 真实主题 - 真实网络服务
        static class RealNetworkService implements NetworkService {
            private String serviceName;
            private String host;

            public RealNetworkService(String serviceName, String host) {
                this.serviceName = serviceName;
                this.host = host;
                System.out.println("Connecting to remote service: " + serviceName + " at " + host);
            }

            @Override
            public String requestData(String query) {
                System.out.println("Sending query to " + host + ": " + query);
                // 模拟网络延迟
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Response for query: " + query + " from " + serviceName;
            }

            @Override
            public boolean ping() {
                System.out.println("Pinging " + host);
                // 模拟网络延迟
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return true; // 假设总是成功
            }
        }

        // 远程代理 - 模拟远程网络服务代理
        static class NetworkServiceProxy implements NetworkService {
            private String serviceName;
            private String host;
            private RealNetworkService realService;
            private int requestCount;
            private long totalLatency;

            public NetworkServiceProxy(String serviceName, String host) {
                this.serviceName = serviceName;
                this.host = host;
                this.requestCount = 0;
                this.totalLatency = 0;
                System.out.println("Network service proxy created for: " + serviceName);
            }

            @Override
            public String requestData(String query) {
                long startTime = System.nanoTime();
                
                if (realService == null) {
                    realService = new RealNetworkService(serviceName, host);
                }
                
                String response = realService.requestData(query);
                
                long endTime = System.nanoTime();
                long latency = (endTime - startTime) / 1_000_000; // 转换为毫秒
                totalLatency += latency;
                requestCount++;
                
                System.out.println("Request latency: " + latency + "ms, Average: " + (totalLatency / requestCount) + "ms");
                return response;
            }

            @Override
            public boolean ping() {
                if (realService == null) {
                    realService = new RealNetworkService(serviceName, host);
                }
                return realService.ping();
            }
        }
    }

    /**
     * 代理模式的进一步应用 - 智能引用代理
     */
    static class SmartReferenceProxyExample {
        // 抽象主题 - 文件接口
        interface File {
            void read();
            void write(String content);
            String getContent();
        }

        // 真实主题 - 实际文件
        static class RealFile implements File {
            private String fileName;
            private String content;
            private int referenceCount;

            public RealFile(String fileName, String content) {
                this.fileName = fileName;
                this.content = content;
                this.referenceCount = 0;
                System.out.println("File created: " + fileName);
            }

            public void incrementReference() {
                referenceCount++;
                System.out.println("Reference count for " + fileName + " incremented to: " + referenceCount);
            }

            public void decrementReference() {
                referenceCount--;
                System.out.println("Reference count for " + fileName + " decremented to: " + referenceCount);
                if (referenceCount <= 0) {
                    finalize();
                }
            }

            @Override
            protected void finalize() {
                System.out.println("File " + fileName + " is being garbage collected");
            }

            @Override
            public void read() {
                System.out.println("Reading file: " + fileName);
            }

            @Override
            public void write(String content) {
                this.content = content;
                System.out.println("Writing to file: " + fileName);
            }

            @Override
            public String getContent() {
                return content;
            }
        }

        // 智能引用代理 - 跟踪引用计数
        static class SmartFileProxy implements File {
            private String fileName;
            private String content;
            private RealFile realFile;

            public SmartFileProxy(String fileName, String content) {
                this.fileName = fileName;
                this.content = content;
                System.out.println("Smart proxy created for file: " + fileName);
            }

            private void acquireRealFile() {
                if (realFile == null) {
                    realFile = new RealFile(fileName, content);
                }
                realFile.incrementReference();
            }

            private void releaseRealFile() {
                if (realFile != null) {
                    realFile.decrementReference();
                }
            }

            @Override
            public void read() {
                acquireRealFile();
                realFile.read();
                releaseRealFile();
            }

            @Override
            public void write(String content) {
                acquireRealFile();
                realFile.write(content);
                releaseRealFile();
            }

            @Override
            public String getContent() {
                acquireRealFile();
                String content = realFile.getContent();
                releaseRealFile();
                return content;
            }
        }
    }

    /**
     * 代理模式的进一步应用 - 缓存代理
     */
    static class CacheProxyExample {
        // 抽象主题 - 数据访问接口
        interface DataProvider {
            String getData(String key);
        }

        // 真实主题 - 数据库访问
        static class DatabaseProvider implements DataProvider {
            @Override
            public String getData(String key) {
                System.out.println("Fetching data from database for key: " + key);
                // 模拟数据库访问延迟
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Data for " + key + " from database";
            }
        }

        // 缓存代理 - 提供缓存功能
        static class CachedDataProvider implements DataProvider {
            private DatabaseProvider databaseProvider;
            private java.util.Map<String, String> cache;

            public CachedDataProvider() {
                this.databaseProvider = new DatabaseProvider();
                this.cache = new java.util.HashMap<>();
            }

            @Override
            public String getData(String key) {
                // 检查缓存
                if (cache.containsKey(key)) {
                    System.out.println("Cache hit for key: " + key);
                    return cache.get(key);
                }

                System.out.println("Cache miss for key: " + key);
                String data = databaseProvider.getData(key);
                cache.put(key, data);
                return data;
            }

            public void clearCache() {
                cache.clear();
                System.out.println("Cache cleared");
            }

            public int getCacheSize() {
                return cache.size();
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 代理模式 (Proxy Pattern) 示例 ===\n");

        // 基础代理模式示例 - 图像代理
        System.out.println("1. 基础代理模式示例 - 图像代理:");
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.png");

        System.out.println("Images created, but not loaded yet");
        System.out.println("Loading first image:");
        image1.display(); // 此时才真正加载
        System.out.println("Loading second image:");
        image2.display(); // 此时才真正加载
        System.out.println();

        // 虚拟代理示例
        System.out.println("2. 虚拟代理示例 - 延迟加载文档:");
        VirtualProxyExample.Document doc = new VirtualProxyExample.VirtualDocumentProxy("report.docx");
        System.out.println("Document proxy created, but not loaded yet");
        System.out.println("Opening document (will trigger loading):");
        doc.open();
        System.out.println();

        // 保护代理示例
        System.out.println("3. 保护代理示例 - 银行账户:");
        ProtectionProxyExample.ProtectedBankAccountProxy account = 
            new ProtectionProxyExample.ProtectedBankAccountProxy("John Doe", 1000.0, "John");
        
        account.setCurrentUser("John");
        account.deposit(500);
        System.out.println("Balance: $" + account.getBalance());
        
        account.setCurrentUser("Jane"); // 未授权用户
        account.withdraw(200); // 应该被拒绝
        System.out.println();

        // 远程代理示例
        System.out.println("4. 远程代理示例 - 网络服务:");
        RemoteProxyExample.NetworkServiceProxy service = 
            new RemoteProxyExample.NetworkServiceProxy("Weather Service", "weather.api.com");
        
        service.ping();
        service.requestData("temperature in Beijing");
        service.requestData("forecast for tomorrow");
        System.out.println();

        // 智能引用代理示例
        System.out.println("5. 智能引用代理示例 - 文件引用计数:");
        SmartReferenceProxyExample.File file = 
            new SmartReferenceProxyExample.SmartFileProxy("document.txt", "Initial content");
        file.read();
        file.write("Updated content");
        System.out.println("File content: " + file.getContent());
        System.out.println();

        // 缓存代理示例
        System.out.println("6. 缓存代理示例 - 数据访问:");
        CacheProxyExample.CachedDataProvider cachedProvider = 
            new CacheProxyExample.CachedDataProvider();
        
        System.out.println("First access (cache miss):");
        cachedProvider.getData("user1");
        System.out.println("Second access (cache hit):");
        cachedProvider.getData("user1");
        System.out.println("Third access to different key (cache miss):");
        cachedProvider.getData("user2");
        System.out.println("Cache size: " + cachedProvider.getCacheSize());
        cachedProvider.clearCache();
        System.out.println();

        System.out.println("=== 代理模式的关键特性 ===");
        System.out.println("1. 控制访问: 可以控制对真实对象的访问");
        System.out.println("2. 延迟初始化: 可以延迟创建昂贵资源的实例");
        System.out.println("3. 安全控制: 可以在访问真实对象前进行权限检查");
        System.out.println("4. 附加功能: 可以在访问前后执行额外操作");
        System.out.println("5. 透明性: 客户端无需知道使用的是代理还是真实对象");
        System.out.println("6. 多样性: 可以实现多种不同类型的代理（虚拟、保护、远程、智能引用等）");
    }
}