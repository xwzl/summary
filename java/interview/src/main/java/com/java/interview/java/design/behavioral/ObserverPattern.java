package com.java.interview.java.design.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 观察者模式 (Observer Pattern)
 *
 * 定义：观察者模式是一种行为型设计模式，它定义了对象之间的一对多依赖关系，
 * 当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知并自动更新。
 * 观察者模式也被称为发布-订阅模式、模型-视图模式、源-监听器模式或从属者模式。
 *
 * 主要角色：
 * 1. 抽象主题(Subject)：被观察的对象，维护观察者列表，提供添加、删除和通知观察者的方法
 * 2. 具体主题(Concrete Subject)：实现抽象主题接口，当自身状态改变时通知所有观察者
 * 3. 抽象观察者(Observer)：定义更新接口，当主题状态改变时更新自己
 * 4. 具体观察者(Concrete Observer)：实现抽象观察者接口，维护一个指向具体主题的引用
 *
 * 主要特点：
 * 1. 支持一对多的依赖关系
 * 2. 主题和观察者之间松耦合
 * 3. 运行时动态添加或删除观察者
 * 4. 支持广播通信
 *
 * 应用场景：
 * - 当一个对象的改变需要同时改变其他对象时
 * - 当一个对象必须通知其他对象，而又不知道其他对象是谁时
 * - 在事件驱动系统中处理事件
 */
public class ObserverPattern {

    /**
     * 抽象观察者接口 - 定义更新接口
     */
    interface Observer {
        void update(String message);
    }

    /**
     * 抽象主题接口 - 被观察的对象
     */
    interface Subject {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers(String message);
    }

    /**
     * 新闻服务类 - 具体主题
     * 维护观察者列表，当新闻更新时通知所有观察者
     */
    static class NewsService implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String latestNews;
        private String category;

        public NewsService(String category) {
            this.category = category;
        }

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
            System.out.println("添加观察者到 " + category + " 新闻服务");
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
            System.out.println("从 " + category + " 新闻服务移除观察者");
        }

        @Override
        public void notifyObservers(String message) {
            System.out.println(category + " 新闻服务发布消息: " + message);
            for (Observer observer : observers) {
                observer.update(message);
            }
        }

        // 发布新闻
        public void publishNews(String news) {
            this.latestNews = news;
            notifyObservers(news);
        }

        public String getCategory() {
            return category;
        }
    }

    /**
     * 具体观察者 - 新闻读者
     */
    static class NewsReader implements Observer {
        private String name;

        public NewsReader(String name) {
            this.name = name;
        }

        @Override
        public void update(String message) {
            System.out.println(name + " 收到新闻更新: " + message);
        }
    }

    /**
     * 具体观察者 - 新闻订阅者（带偏好设置）
     */
    static class NewsSubscriber implements Observer {
        private String name;
        private String preferredCategory;

        public NewsSubscriber(String name, String preferredCategory) {
            this.name = name;
            this.preferredCategory = preferredCategory;
        }

        @Override
        public void update(String message) {
            // 只处理特定类别的新闻
            if (message.toLowerCase().contains(preferredCategory.toLowerCase())) {
                System.out.println(name + " (偏好 " + preferredCategory + ") 收到相关新闻: " + message);
            } else {
                System.out.println(name + " 忽略新闻 (不符合偏好): " + message);
            }
        }
    }

    /**
     * 具体观察者 - 新闻聚合器
     */
    static class NewsAggregator implements Observer {
        private String name;
        private List<String> collectedNews = new ArrayList<>();

        public NewsAggregator(String name) {
            this.name = name;
        }

        @Override
        public void update(String message) {
            collectedNews.add(message);
            System.out.println(name + " 聚合新闻: " + message + " (总共收集了 " + collectedNews.size() + " 条新闻)");
        }

        public List<String> getCollectedNews() {
            return new ArrayList<>(collectedNews);
        }
    }

    /**
     * 进阶用法：带过滤器的主题
     * 支持根据条件过滤通知
     */
    static class FilteredNewsService extends NewsService {
        private java.util.function.Predicate<String> filter;

        public FilteredNewsService(String category, java.util.function.Predicate<String> filter) {
            super(category);
            this.filter = filter;
        }

        @Override
        public void notifyObservers(String message) {
            if (filter.test(message)) {
                System.out.println("消息通过过滤器: " + message);
                super.notifyObservers(message);
            } else {
                System.out.println("消息被过滤器拦截: " + message);
            }
        }
    }

    /**
     * 进阶用法：带优先级的观察者
     * 不同优先级的观察者接收通知的顺序不同
     */
    static class PriorityObserver implements Observer {
        private String name;
        private int priority;

        public PriorityObserver(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public void update(String message) {
            System.out.println(name + " (优先级: " + priority + ") 收到消息: " + message);
        }

        public int getPriority() {
            return priority;
        }
    }

    /**
     * 进阶用法：优先级主题
     * 按优先级顺序通知观察者
     */
    static class PriorityNewsService implements Subject {
        private List<PriorityObserver> priorityObservers = new CopyOnWriteArrayList<>();
        private String latestNews;

        @Override
        public void addObserver(Observer observer) {
            if (observer instanceof PriorityObserver) {
                priorityObservers.add((PriorityObserver) observer);
                // 按优先级排序
                priorityObservers.sort((o1, o2) -> Integer.compare(o2.getPriority(), o1.getPriority()));
                System.out.println("添加优先级观察者，当前观察者数量: " + priorityObservers.size());
            }
        }

        @Override
        public void removeObserver(Observer observer) {
            if (observer instanceof PriorityObserver) {
                priorityObservers.remove(observer);
                System.out.println("移除优先级观察者，当前观察者数量: " + priorityObservers.size());
            }
        }

        @Override
        public void notifyObservers(String message) {
            System.out.println("按优先级顺序通知观察者，消息: " + message);
            for (PriorityObserver observer : priorityObservers) {
                observer.update(message);
            }
        }

        public void publishNews(String news) {
            this.latestNews = news;
            notifyObservers(news);
        }
    }

    /**
     * 进阶用法：异步观察者
     * 支持异步通知的观察者
     */
    static class AsyncObserver implements Observer {
        private String name;

        public AsyncObserver(String name) {
            this.name = name;
        }

        @Override
        public void update(String message) {
            new Thread(() -> {
                try {
                    // 模拟异步处理
                    Thread.sleep(100);
                    System.out.println(name + " 异步收到消息: " + message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    /**
     * 进阶用法：带历史记录的主题
     * 保存历史消息，新观察者可以获取历史消息
     */
    static class HistoricalNewsService implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private List<String> history = new ArrayList<>();
        private int maxHistorySize = 10;

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
            // 向新观察者发送历史消息
            for (String message : history) {
                observer.update("[历史消息] " + message);
            }
            System.out.println("新观察者加入，发送 " + history.size() + " 条历史消息");
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers(String message) {
            // 保存到历史记录
            history.add(message);
            if (history.size() > maxHistorySize) {
                history.remove(0);
            }

            System.out.println("通知所有观察者: " + message);
            for (Observer observer : observers) {
                observer.update(message);
            }
        }

        public void publishNews(String news) {
            notifyObservers(news);
        }

        public List<String> getHistory() {
            return new ArrayList<>(history);
        }
    }

    /**
     * 进阶用法：可观察的数据模型
     * 支持属性级别的观察
     */
    static class ObservableDataModel {
        private List<PropertyObserver> propertyObservers = new ArrayList<>();
        private String name;
        private int value;

        interface PropertyObserver {
            void onPropertyChanged(String propertyName, Object oldValue, Object newValue);
        }

        public void addPropertyObserver(PropertyObserver observer) {
            propertyObservers.add(observer);
        }

        public void removePropertyObserver(PropertyObserver observer) {
            propertyObservers.remove(observer);
        }

        public void setName(String name) {
            String oldName = this.name;
            this.name = name;
            notifyPropertyChange("name", oldName, name);
        }

        public void setValue(int value) {
            int oldValue = this.value;
            this.value = value;
            notifyPropertyChange("value", oldValue, value);
        }

        private void notifyPropertyChange(String propertyName, Object oldValue, Object newValue) {
            for (PropertyObserver observer : propertyObservers) {
                observer.onPropertyChanged(propertyName, oldValue, newValue);
            }
        }

        public String getName() { return name; }
        public int getValue() { return value; }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 观察者模式示例 ===\n");

        System.out.println("1. 基础观察者模式示例:");
        // 创建新闻服务
        NewsService techNews = new NewsService("科技");
        NewsService sportsNews = new NewsService("体育");

        // 创建观察者
        NewsReader reader1 = new NewsReader("张三");
        NewsReader reader2 = new NewsReader("李四");

        NewsSubscriber subscriber1 = new NewsSubscriber("王五", "科技");
        NewsAggregator aggregator = new NewsAggregator("新闻聚合平台");

        // 订阅新闻
        techNews.addObserver(reader1);
        techNews.addObserver(reader2);
        techNews.addObserver(subscriber1);
        techNews.addObserver(aggregator);

        // 发布新闻
        techNews.publishNews("AI技术取得重大突破");
        techNews.publishNews("某公司发布新款智能手机");
        techNews.publishNews("体育赛事新闻"); // 这条会被王五忽略

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 过滤器主题示例:");
        // 创建带过滤器的新闻服务
        FilteredNewsService filteredTechNews = new FilteredNewsService("财经",
            msg -> msg.toLowerCase().contains("股票") || msg.toLowerCase().contains("基金"));

        filteredTechNews.addObserver(new NewsReader("财经观察员"));
        filteredTechNews.addObserver(new NewsSubscriber("投资者", "投资"));

        filteredTechNews.publishNews("股市今日上涨2%"); // 通过过滤器
        filteredTechNews.publishNews("天气预报良好");   // 被过滤器拦截
        filteredTechNews.publishNews("基金收益创新高"); // 通过过滤器

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 优先级观察者示例:");
        PriorityNewsService priorityNews = new PriorityNewsService();

        PriorityObserver highPriority = new PriorityObserver("高优先级观察者", 10);
        PriorityObserver mediumPriority = new PriorityObserver("中优先级观察者", 5);
        PriorityObserver lowPriority = new PriorityObserver("低优先级观察者", 1);

        priorityNews.addObserver(highPriority);
        priorityNews.addObserver(lowPriority);
        priorityNews.addObserver(mediumPriority);

        priorityNews.publishNews("重要通知");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 异步观察者示例:");
        NewsService asyncNews = new NewsService("异步新闻");
        asyncNews.addObserver(new AsyncObserver("异步观察者1"));
        asyncNews.addObserver(new AsyncObserver("异步观察者2"));

        asyncNews.publishNews("异步新闻消息");
        System.out.println("主程序继续执行...");

        // 等待异步处理完成
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n5. 历史记录主题示例:");
        HistoricalNewsService historicalNews = new HistoricalNewsService();
        historicalNews.publishNews("历史消息1");
        historicalNews.publishNews("历史消息2");

        // 添加新观察者，会收到历史消息
        NewsReader newReader = new NewsReader("新观察者");
        historicalNews.addObserver(newReader);

        historicalNews.publishNews("新消息");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n6. 属性观察者示例:");
        ObservableDataModel model = new ObservableDataModel();
        model.addPropertyObserver((propertyName, oldValue, newValue) -> {
            System.out.println("属性 " + propertyName + " 从 " + oldValue + " 变更为 " + newValue);
        });

        model.setName("测试名称");
        model.setValue(100);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n观察者模式的关键特性:");
        System.out.println("1. 定义了一对多的依赖关系");
        System.out.println("2. 主题和观察者之间松耦合");
        System.out.println("3. 支持运行时动态添加或删除观察者");
        System.out.println("4. 支持多种变体（过滤、优先级、异步、历史记录等）");
        System.out.println("5. 适用于事件驱动系统");
        System.out.println("6. 遵循开闭原则，易于扩展");
    }
}
