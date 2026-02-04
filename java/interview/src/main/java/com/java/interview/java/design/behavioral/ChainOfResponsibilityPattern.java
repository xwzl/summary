package com.java.interview.java.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式 (Chain of Responsibility Pattern)
 *
 * 定义：责任链模式是一种行为型设计模式，它允许多个对象有机会处理请求，
 * 从而避免请求的发送者和接收者之间的耦合。将这些对象连成一条链，
 * 并沿着这条链传递请求，直到有一个对象处理它为止。
 *
 * 主要角色：
 * 1. 抽象处理器(Handler)：定义处理请求的接口，并持有下一个处理器的引用
 * 2. 具体处理器(Concrete Handler)：处理它负责的请求，如果无法处理则转发给下一个处理器
 * 3. 客户端(Client)：创建处理器链并发起请求
 *
 * 主要特点：
 * 1. 请求发送者和接收者解耦：发送者不需要知道请求由谁处理
 * 2. 动态组合：可以在运行时动态组合处理器链
 * 3. 增强灵活性：可以自由添加或删除处理器
 *
 * 应用场景：
 * - 多级审批系统
 * - 过滤器链
 * - 异常处理机制
 * - 权限控制
 */
public class ChainOfResponsibilityPattern {

    /**
     * 请求类 - 表示需要处理的请求
     */
    static class Request {
        private final String type;
        private final String content;
        private final int level;

        public Request(String type, String content, int level) {
            this.type = type;
            this.content = content;
            this.level = level;
        }

        public String getType() { return type; }
        public String getContent() { return content; }
        public int getLevel() { return level; }

        @Override
        public String toString() {
            return "Request{" +
                    "type='" + type + '\'' +
                    ", content='" + content + '\'' +
                    ", level=" + level +
                    '}';
        }
    }

    /**
     * 抽象处理器 - 定义处理请求的接口
     */
    abstract static class Handler {
        protected Handler nextHandler;
        protected String handlerName;

        public Handler(String handlerName) {
            this.handlerName = handlerName;
        }

        // 设置下一个处理器
        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        // 处理请求的抽象方法
        public abstract boolean handleRequest(Request request);

        // 转发请求到下一个处理器
        protected boolean forwardRequest(Request request) {
            if (nextHandler != null) {
                System.out.println(handlerName + " 无法完全处理请求，转发给 " + nextHandler.handlerName);
                return nextHandler.handleRequest(request);
            } else {
                System.out.println("请求无法被处理: " + request);
                return false;
            }
        }
    }

    /**
     * 具体处理器 - 初级处理者
     */
    static class JuniorHandler extends Handler {
        public JuniorHandler(String handlerName) {
            super(handlerName);
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println(handlerName + " 正在处理请求: " + request);

            if (request.getLevel() <= 1) {
                // 级别较低的请求可以处理
                System.out.println(handlerName + " 成功处理了 " + request.getType() + " 请求: " + request.getContent());
                return true;
            } else {
                // 级别较高的请求转交给上级
                System.out.println(handlerName + " 无法处理级别为 " + request.getLevel() + " 的请求");
                return forwardRequest(request);
            }
        }
    }

    /**
     * 具体处理器 - 中级处理者
     */
    static class MiddleHandler extends Handler {
        public MiddleHandler(String handlerName) {
            super(handlerName);
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println(handlerName + " 正在处理请求: " + request);

            if (request.getLevel() <= 3) {
                // 级别中等及以下的请求可以处理
                System.out.println(handlerName + " 成功处理了 " + request.getType() + " 请求: " + request.getContent());
                return true;
            } else {
                // 级别较高的请求转交给上级
                System.out.println(handlerName + " 无法处理级别为 " + request.getLevel() + " 的请求");
                return forwardRequest(request);
            }
        }
    }

    /**
     * 具体处理器 - 高级处理者
     */
    static class SeniorHandler extends Handler {
        public SeniorHandler(String handlerName) {
            super(handlerName);
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println(handlerName + " 正在处理请求: " + request);

            if (request.getLevel() <= 5) {
                // 级别较高的请求可以处理
                System.out.println(handlerName + " 成功处理了 " + request.getType() + " 请求: " + request.getContent());
                return true;
            } else {
                // 级别非常高的请求转交给总监
                System.out.println(handlerName + " 无法处理级别为 " + request.getLevel() + " 的请求");
                return forwardRequest(request);
            }
        }
    }

    /**
     * 具体处理器 - 总监处理者
     */
    static class DirectorHandler extends Handler {
        public DirectorHandler(String handlerName) {
            super(handlerName);
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println(handlerName + " 正在处理请求: " + request);

            // 总监可以处理所有级别的请求
            System.out.println(handlerName + " 成功处理了 " + request.getType() + " 请求: " + request.getContent());
            return true;
        }
    }

    /**
     * 进阶用法：动态责任链
     * 支持运行时动态添加和移除处理器
     */
    static class DynamicChain {
        private List<Handler> handlers = new ArrayList<>();
        private int currentIndex = 0;

        public void addHandler(Handler handler) {
            if (!handlers.isEmpty()) {
                handlers.get(handlers.size() - 1).setNextHandler(handler);
            }
            handlers.add(handler);
        }

        public void removeHandler(Handler handler) {
            int index = handlers.indexOf(handler);
            if (index != -1) {
                handlers.remove(index);
                // 重新链接链
                if (index > 0 && index < handlers.size()) {
                    handlers.get(index - 1).setNextHandler(handlers.get(index));
                } else if (index == 0 && !handlers.isEmpty()) {
                    // 如果移除的是第一个处理器，需要重新设置链
                    rebuildChain();
                }
            }
        }

        private void rebuildChain() {
            for (int i = 0; i < handlers.size() - 1; i++) {
                handlers.get(i).setNextHandler(handlers.get(i + 1));
            }
            if (!handlers.isEmpty()) {
                handlers.get(handlers.size() - 1).setNextHandler(null);
            }
        }

        public boolean processRequest(Request request) {
            if (handlers.isEmpty()) {
                System.out.println("没有可用的处理器");
                return false;
            }
            return handlers.get(0).handleRequest(request);
        }
    }

    /**
     * 进阶用法：基于条件的责任链
     * 根据请求的不同属性选择不同的处理路径
     */
    static class ConditionalChain {
        private Handler bugHandler;
        private Handler featureHandler;
        private Handler securityHandler;

        public ConditionalChain(Handler bugHandler, Handler featureHandler, Handler securityHandler) {
            this.bugHandler = bugHandler;
            this.featureHandler = featureHandler;
            this.securityHandler = securityHandler;
        }

        public boolean processRequest(Request request) {
            switch (request.getType().toLowerCase()) {
                case "bug":
                    System.out.println("根据请求类型路由到Bug处理链");
                    return bugHandler.handleRequest(request);
                case "feature":
                    System.out.println("根据请求类型路由到Feature处理链");
                    return featureHandler.handleRequest(request);
                case "security":
                    System.out.println("根据请求类型路由到Security处理链");
                    return securityHandler.handleRequest(request);
                default:
                    System.out.println("未知的请求类型: " + request.getType());
                    return false;
            }
        }
    }

    /**
     * 进阶用法：异步责任链
     * 支持异步处理请求
     */
    static class AsyncChain {
        private Handler handler;

        public AsyncChain(Handler handler) {
            this.handler = handler;
        }

        public void processRequestAsync(Request request) {
            new Thread(() -> {
                System.out.println("异步处理请求开始...");
                handler.handleRequest(request);
                System.out.println("异步处理请求结束");
            }).start();
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 责任链模式示例 ===\n");

        // 创建处理器链
        JuniorHandler junior = new JuniorHandler("初级工程师");
        MiddleHandler middle = new MiddleHandler("中级工程师");
        SeniorHandler senior = new SeniorHandler("高级工程师");
        DirectorHandler director = new DirectorHandler("技术总监");

        // 构建责任链
        junior.setNextHandler(middle);
        middle.setNextHandler(senior);
        senior.setNextHandler(director);

        System.out.println("1. 基础责任链示例:");
        // 测试不同级别的请求
        Request simpleBug = new Request("Bug", "修复登录页面样式问题", 1);
        Request complexFeature = new Request("Feature", "实现用户权限管理系统", 4);
        Request criticalIssue = new Request("Critical", "系统崩溃紧急修复", 6);

        System.out.println("\n处理简单Bug:");
        junior.handleRequest(simpleBug);

        System.out.println("\n处理复杂功能:");
        junior.handleRequest(complexFeature);

        System.out.println("\n处理严重问题:");
        junior.handleRequest(criticalIssue);

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 动态责任链示例:");
        DynamicChain dynamicChain = new DynamicChain();
        dynamicChain.addHandler(new JuniorHandler("张三"));
        dynamicChain.addHandler(new MiddleHandler("李四"));
        dynamicChain.addHandler(new SeniorHandler("王五"));

        Request mediumRequest = new Request("Optimization", "优化数据库查询性能", 2);
        dynamicChain.processRequest(mediumRequest);

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 条件责任链示例:");
        // 创建不同类型请求的处理链
        JuniorHandler bugJunior = new JuniorHandler("Bug-小张");
        MiddleHandler bugMiddle = new MiddleHandler("Bug-小李");
        bugJunior.setNextHandler(bugMiddle);

        SeniorHandler featureSenior = new SeniorHandler("Feature-老王");
        DirectorHandler featureDirector = new DirectorHandler("Feature-总监");
        featureSenior.setNextHandler(featureDirector);

        SeniorHandler securitySenior = new SeniorHandler("Security-安全部");
        DirectorHandler securityDirector = new DirectorHandler("Security-安全部总监");
        securitySenior.setNextHandler(securityDirector);

        ConditionalChain conditionalChain = new ConditionalChain(bugJunior, featureSenior, securitySenior);

        Request bugRequest = new Request("Bug", "修复登录失败问题", 2);
        Request featureRequest = new Request("Feature", "添加用户反馈功能", 3);
        Request securityRequest = new Request("Security", "修复SQL注入漏洞", 5);

        conditionalChain.processRequest(bugRequest);
        System.out.println();
        conditionalChain.processRequest(featureRequest);
        System.out.println();
        conditionalChain.processRequest(securityRequest);

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 异步责任链示例:");
        AsyncChain asyncChain = new AsyncChain(new JuniorHandler("异步处理员"));
        Request asyncRequest = new Request("AsyncTask", "执行长时间计算任务", 1);
        asyncChain.processRequestAsync(asyncRequest);

        // 等待异步处理完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n责任链模式的关键特性:");
        System.out.println("1. 解耦发送者和接收者");
        System.out.println("2. 灵活的请求处理顺序");
        System.out.println("3. 支持动态组合处理器链");
        System.out.println("4. 可以随时添加或移除处理器");
        System.out.println("5. 每个处理器只关注自己的职责");
        System.out.println("6. 支持多种变体（动态链、条件链、异步链等）");
    }
}