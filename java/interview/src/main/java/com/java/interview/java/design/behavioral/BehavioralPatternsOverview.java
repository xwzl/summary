package com.java.interview.java.design.behavioral;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 行为型设计模式综合示例
 *
 * 本类演示了所有行为型设计模式的实际应用
 * 行为型设计模式关注对象之间的交互和职责分配
 *
 * 包含的行为型模式：
 * 1. 策略模式 (Strategy Pattern)
 * 2. 模板方法模式 (Template Method Pattern)
 * 3. 观察者模式 (Observer Pattern)
 * 4. 迭代器模式 (Iterator Pattern)
 * 5. 责任链模式 (Chain of Responsibility Pattern)
 * 6. 命令模式 (Command Pattern)
 * 7. 备忘录模式 (Memento Pattern)
 * 8. 状态模式 (State Pattern)
 * 9. 访问者模式 (Visitor Pattern)
 * 10. 解释器模式 (Interpreter Pattern)
 * 11. 中介者模式 (Mediator Pattern)
 */
public class BehavioralPatternsOverview {

    /**
     * 演示策略模式
     */
    private static void demonstrateStrategyPattern() {
        System.out.println("1. 策略模式 (Strategy Pattern)");
        System.out.println("   目的: 定义算法族，分别封装起来，让他们之间可以互相替换");

        // 创建不同的支付策略
        PaymentProcessor processor = new PaymentProcessor();
        
        processor.setPaymentStrategy(new CreditCardPaymentStrategy("张三", "1234-5678-9012-3456", "123"));
        processor.processPayment(100.0);
        
        processor.setPaymentStrategy(new PayPalPaymentStrategy("user@example.com", "password"));
        processor.processPayment(50.0);
        
        processor.setPaymentStrategy(new AlipayPaymentStrategy("user@alipay.com"));
        processor.processPayment(75.0);
    }

    /**
     * 演示模板方法模式
     */
    private static void demonstrateTemplateMethodPattern() {
        System.out.println("2. 模板方法模式 (Template Method Pattern)");
        System.out.println("   目的: 定义算法的骨架，在子类中实现算法的某些步骤");

        Game game = new Cricket();
        System.out.println("板球游戏:");
        game.play();
        
        System.out.println();
        game = new Football();
        System.out.println("足球游戏:");
        game.play();
    }

    /**
     * 演示观察者模式
     */
    private static void demonstrateObserverPattern() {
        System.out.println("3. 观察者模式 (Observer Pattern)");
        System.out.println("   目的: 定义对象间的一对多依赖关系，当一个对象状态改变时所有依赖者都会收到通知");

        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel();
        
        agency.addObserver(channel);
        agency.setNews("今天是个好天气!");
    }

    /**
     * 演示迭代器模式
     */
    private static void demonstrateIteratorPattern() {
        System.out.println("4. 迭代器模式 (Iterator Pattern)");
        System.out.println("   目的: 提供一种方法来顺序访问聚合对象中的各个元素");

        NameRepository names = new NameRepository();
        for (Iterator<String> iter = names.getIterator(); iter.hasNext();) {
            String name = iter.next();
            System.out.println("Name: " + name);
        }
    }

    /**
     * 演示责任链模式
     */
    private static void demonstrateChainOfResponsibilityPattern() {
        System.out.println("5. 责任链模式 (Chain of Responsibility Pattern)");
        System.out.println("   目的: 避免请求发送者与接收者耦合，让多个对象都有机会处理请求");

        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }

    /**
     * 演示命令模式
     */
    private static void demonstrateCommandPattern() {
        System.out.println("6. 命令模式 (Command Pattern)");
        System.out.println("   目的: 将请求封装成对象，从而使您可以使用不同的请求、队列或者日志来参数化其他对象");

        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }

    /**
     * 演示备忘录模式
     */
    private static void demonstrateMementoPattern() {
        System.out.println("7. 备忘录模式 (Memento Pattern)");
        System.out.println("   目的: 在不破坏封装的前提下，捕获一个对象的内部状态并在该对象之外保存这个状态");

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        
        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());
        
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

    /**
     * 演示状态模式
     */
    private static void demonstrateStatePattern() {
        System.out.println("8. 状态模式 (State Pattern)");
        System.out.println("   目的: 允许对象在内部状态改变时改变其行为");

        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }

    /**
     * 演示访问者模式
     */
    private static void demonstrateVisitorPattern() {
        System.out.println("9. 访问者模式 (Visitor Pattern)");
        System.out.println("   目的: 表示一个作用于某对象结构中的各元素的操作");

        ComputerPart computer = new Keyboard();
        computer.accept(new ComputerPartDisplayVisitor());

        computer = new Monitor();
        computer.accept(new ComputerPartDisplayVisitor());

        computer = new Mouse();
        computer.accept(new ComputerPartDisplayVisitor());

        computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }

    /**
     * 演示解释器模式
     */
    private static void demonstrateInterpreterPattern() {
        System.out.println("10. 解释器模式 (Interpreter Pattern)");
        System.out.println("   目的: 给定一个语言，定义它的文法的一种表示，并定义一个解释器");

        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        Person robert = new Person("Robert", "Male", "Single");
        Person john = new Person("John", "Male", "Married");
        Person laura = new Person("Laura", "Female", "Married");
        Person julie = new Person("Julie", "Female", "Single");

        System.out.println("Robert is male? " + isMale.interpret(robert));
        System.out.println("Julie is a married woman? " + isMarriedWoman.interpret(julie));
        System.out.println("Laura is a married woman? " + isMarriedWoman.interpret(laura));
    }

    /**
     * 演示中介者模式
     */
    private static void demonstrateMediatorPattern() {
        System.out.println("11. 中介者模式 (Mediator Pattern)");
        System.out.println("   目的: 用一个中介对象来封装一系列的对象交互");

        User robert = new User("Robert", new ChatMediatorImpl());
        User john = new User("John", new ChatMediatorImpl());

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }

    // 以下是各种行为型模式的辅助类实现
    // 为了简洁，这里只列出关键类的实现

    // 策略模式相关类
    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPaymentStrategy implements PaymentStrategy {
        private String name;
        private String cardNumber;
        private String cvv;

        public CreditCardPaymentStrategy(String name, String cardNumber, String cvv) {
            this.name = name;
            this.cardNumber = cardNumber;
            this.cvv = cvv;
        }

        @Override
        public void pay(double amount) {
            System.out.println("使用信用卡支付: " + amount + " 人民币");
            System.out.println("姓名: " + name + ", 卡号: " + cardNumber);
        }
    }

    static class PayPalPaymentStrategy implements PaymentStrategy {
        private String email;
        private String password;

        public PayPalPaymentStrategy(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        public void pay(double amount) {
            System.out.println("使用PayPal支付: " + amount + " 人民币");
            System.out.println("邮箱: " + email);
        }
    }

    static class AlipayPaymentStrategy implements PaymentStrategy {
        private String account;

        public AlipayPaymentStrategy(String account) {
            this.account = account;
        }

        @Override
        public void pay(double amount) {
            System.out.println("使用支付宝支付: " + amount + " 人民币");
            System.out.println("账户: " + account);
        }
    }

    static class PaymentProcessor {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void processPayment(double amount) {
            strategy.pay(amount);
        }
    }

    // 观察者模式相关类
    interface Observer {
        void update(String news);
    }

    interface Subject {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers(String news);
    }

    static class NewsAgency implements Subject {
        private String news;
        private List<Observer> observers = new ArrayList<>();

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers(String news) {
            for (Observer obs : observers) {
                obs.update(news);
            }
        }

        public void setNews(String news) {
            this.news = news;
            notifyObservers(news);
        }

        public String getNews() {
            return news;
        }
    }

    static class NewsChannel implements Observer {
        private String news;

        @Override
        public void update(String news) {
            this.news = news;
            System.out.println("新闻频道收到新闻: " + news);
        }
    }

    // 迭代器模式相关类
    static class NameRepository {
        public String[] names = {"Robert", "John", "Julie", "Lora"};

        public Iterator<String> getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator<String> {
            int index;

            @Override
            public boolean hasNext() {
                return index < names.length;
            }

            @Override
            public String next() {
                if (this.hasNext()) {
                    return names[index++];
                }
                return null;
            }
        }
    }

    // 模板方法模式相关类
    abstract static class Game {
        abstract void initialize();
        abstract void startPlay();
        abstract void endPlay();

        public final void play() {
            initialize();
            startPlay();
            endPlay();
        }
    }

    static class Cricket extends Game {
        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }

    static class Football extends Game {
        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }

    // 责任链模式相关类
    abstract static class AbstractLogger {
        public static int INFO = 1;
        public static int DEBUG = 2;
        public static int ERROR = 3;

        protected int level;

        protected AbstractLogger nextLogger;

        public void setNextLogger(AbstractLogger nextLogger) {
            this.nextLogger = nextLogger;
        }

        public void logMessage(int level, String message) {
            if (this.level <= level) {
                write(message);
            }
            if (nextLogger != null) {
                nextLogger.logMessage(level, message);
            }
        }

        abstract protected void write(String message);
    }

    static class ConsoleLogger extends AbstractLogger {
        public ConsoleLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Standard Console::Logger: " + message);
        }
    }

    static class ErrorLogger extends AbstractLogger {
        public ErrorLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Error Console::Logger: " + message);
        }
    }

    static class FileLogger extends AbstractLogger {
        public FileLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("File::Logger: " + message);
        }
    }

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    // 命令模式相关类
    interface Order {
        void execute();
    }

    static class Stock {
        private String name = "ABC";
        private int quantity = 10;

        public void buy() {
            System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
        }

        public void sell() {
            System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
        }
    }

    static class BuyStock implements Order {
        private Stock abcStock;

        public BuyStock(Stock abcStock) {
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.buy();
        }
    }

    static class SellStock implements Order {
        private Stock abcStock;

        public SellStock(Stock abcStock) {
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.sell();
        }
    }

    static class Broker {
        private List<Order> orderList = new ArrayList<>();

        public void takeOrder(Order order) {
            orderList.add(order);
        }

        public void placeOrders() {
            for (Order order : orderList) {
                order.execute();
            }
            orderList.clear();
        }
    }

    // 备忘录模式相关类
    static class Memento {
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    static class Originator {
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento memento) {
            state = memento.getState();
        }
    }

    static class CareTaker {
        private List<Memento> mementoList = new ArrayList<>();

        public void add(Memento state) {
            mementoList.add(state);
        }

        public Memento get(int index) {
            return mementoList.get(index);
        }
    }

    // 状态模式相关类
    interface State {
        String toString();
    }

    static class StartState implements State {
        @Override
        public String toString() {
            return "Start State";
        }

        public void doAction(Context context) {
            System.out.println("Player is in start state");
            context.setState(this);
        }
    }

    static class StopState implements State {
        @Override
        public String toString() {
            return "Stop State";
        }

        public void doAction(Context context) {
            System.out.println("Player is in stop state");
            context.setState(this);
        }
    }

    static class Context {
        private State state;

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }
    }

    // 访问者模式相关类
    interface ComputerPart {
        void accept(ComputerPartVisitor computerPartVisitor);
    }

    static class Keyboard implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    static class Monitor implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    static class Mouse implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    static class Computer implements ComputerPart {
        ComputerPart[] parts;

        public Computer() {
            parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
        }

        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            for (int i = 0; i < parts.length; i++) {
                parts[i].accept(computerPartVisitor);
            }
            computerPartVisitor.visit(this);
        }
    }

    interface ComputerPartVisitor {
        void visit(Keyboard keyboard);
        void visit(Mouse mouse);
        void visit(Monitor monitor);
        void visit(Computer computer);
    }

    static class ComputerPartDisplayVisitor implements ComputerPartVisitor {
        @Override
        public void visit(Computer computer) {
            System.out.println("Displaying Computer.");
        }

        @Override
        public void visit(Mouse mouse) {
            System.out.println("Displaying Mouse.");
        }

        @Override
        public void visit(Keyboard keyboard) {
            System.out.println("Displaying Keyboard.");
        }

        @Override
        public void visit(Monitor monitor) {
            System.out.println("Displaying Monitor.");
        }
    }

    // 解释器模式相关类
    interface Expression {
        boolean interpret(Person context);
    }

    static class TerminalExpression implements Expression {
        private String data;
        private String field; // "name", "gender", or "maritalStatus"

        public TerminalExpression(String data, String field) {
            this.data = data;
            this.field = field;
        }
        
        public TerminalExpression(String data) {
            this.data = data;
            this.field = "name"; // default to name field
        }

        @Override
        public boolean interpret(Person context) {
            switch(field) {
                case "name":
                    return context.getName().contains(data);
                case "gender":
                    return context.getGender().contains(data);
                case "maritalStatus":
                    return context.getMaritalStatus().contains(data);
                default:
                    return false;
            }
        }
    }

    static class OrExpression implements Expression {
        private Expression expr1 = null;
        private Expression expr2 = null;

        public OrExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public boolean interpret(Person context) {
            return expr1.interpret(context) || expr2.interpret(context);
        }
    }

    static class AndExpression implements Expression {
        private Expression expr1 = null;
        private Expression expr2 = null;

        public AndExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public boolean interpret(Person context) {
            return expr1.interpret(context) && expr2.interpret(context);
        }
    }

    static class Person {
        private String name;
        private String gender;
        private String maritalStatus;

        public Person(String name, String gender, String maritalStatus) {
            this.name = name;
            this.gender = gender;
            this.maritalStatus = maritalStatus;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public boolean isMale() {
            return gender.equalsIgnoreCase("MALE");
        }

        public boolean isMarried() {
            return maritalStatus.equalsIgnoreCase("MARRIED");
        }
    }

    static class MaleExpression implements Expression {
        @Override
        public boolean interpret(Person context) {
            return context.isMale();
        }
    }

    static class MarriedWomanExpression implements Expression {
        @Override
        public boolean interpret(Person context) {
            return context.getGender().equalsIgnoreCase("FEMALE") && context.isMarried();
        }
    }

    public static Expression getMaleExpression() {
        Expression male = new TerminalExpression("Male", "gender");
        return male;
    }

    public static Expression getMarriedWomanExpression() {
        Expression married = new TerminalExpression("Married", "maritalStatus");
        Expression female = new TerminalExpression("Female", "gender");
        
        // Check if person is female AND married
        return new AndExpression(female, married);
    }

    // 中介者模式相关类
    interface Mediator {
        void showMessage(User user, String message);
    }

    static class ChatMediatorImpl implements Mediator {
        private List<User> users;

        public ChatMediatorImpl() {
            this.users = new CopyOnWriteArrayList<>();
        }

        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public void showMessage(User user, String message) {
            for (User u : users) {
                if (u != user) {
                    u.receive(message);
                }
            }
        }
    }

    static class User {
        private String name;
        private Mediator mediator;

        public User(String name, Mediator mediator) {
            this.name = name;
            this.mediator = mediator;
        }

        public void send(String message) {
            System.out.println(this.name + ": Sending Message=" + message);
            mediator.showMessage(this, message);
        }

        public void receive(String message) {
            System.out.println(this.name + ": Received Message:" + message);
        }

        public void sendMessage(String message) {
            send(message);
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 行为型设计模式综合演示 ===\n");

        // 演示各种行为型模式
        demonstrateStrategyPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateTemplateMethodPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateObserverPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateIteratorPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateChainOfResponsibilityPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateCommandPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateMementoPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateStatePattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateVisitorPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateInterpreterPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateMediatorPattern();
        System.out.println("\n" + "=".repeat(60) + "\n");

        System.out.println("行为型设计模式总结:");
        System.out.println("1. 策略模式: 定义算法族，分别封装起来，让他们之间可以互相替换");
        System.out.println("2. 模板方法模式: 定义算法的骨架，在子类中实现算法的某些步骤");
        System.out.println("3. 观察者模式: 定义对象间的一对多依赖关系，当一个对象状态改变时所有依赖者都会收到通知");
        System.out.println("4. 迭代器模式: 提供一种方法来顺序访问聚合对象中的各个元素");
        System.out.println("5. 责任链模式: 避免请求发送者与接收者耦合，让多个对象都有机会处理请求");
        System.out.println("6. 命令模式: 将请求封装成对象，从而使您可以使用不同的请求、队列或者日志来参数化其他对象");
        System.out.println("7. 备忘录模式: 在不破坏封装的前提下，捕获一个对象的内部状态并在该对象之外保存这个状态");
        System.out.println("8. 状态模式: 允许对象在内部状态改变时改变其行为");
        System.out.println("9. 访问者模式: 表示一个作用于某对象结构中的各元素的操作");
        System.out.println("10. 解释器模式: 给定一个语言，定义它的文法的一种表示，并定义一个解释器");
        System.out.println("11. 中介者模式: 用一个中介对象来封装一系列的对象交互");
    }
}