package com.java.interview.java.design.behavioral;

/**
 * 状态模式 (State Pattern)
 *
 * 定义：状态模式是一种行为型设计模式，它允许一个对象在其内部状态改变时改变其行为。
 * 对象看起来似乎修改了它的类。状态模式将对象的行为封装在不同的状态对象中，
 * 并在运行时切换状态对象来改变对象的行为。
 *
 * 主要角色：
 * 1. 抽象状态(State)：定义一个接口，用以封装与上下文的一个特定状态相关的行为
 * 2. 具体状态(Concrete State)：实现抽象状态接口，每一个具体状态类对应上下文的一个具体状态
 * 3. 上下文(Context)：定义客户感兴趣的接口，并维护一个具体状态对象的实例
 *
 * 主要特点：
 * 1. 将状态相关的操作封装到具体的状态类中
 * 2. 状态转换逻辑集中在状态类中
 * 3. 通过改变对象的内部状态来改变对象的行为
 * 4. 遵循开闭原则，新增状态容易
 *
 * 应用场景：
 * - 对象的行为取决于它的状态，并且必须在运行时刻根据状态改变它的行为
 * - 操作中含有庞大的多分支语句，且这些分支依赖于对象的状态
 */
public class StatePattern {

    /**
     * 抽象状态接口 - 定义状态相关的行为
     */
    interface DocumentState {
        void review(Document document);
        void approve(Document document);
        void reject(Document document);
        void publish(Document document);
        String getStateName();
    }

    /**
     * 上下文类 - 文档类
     * 维护一个具体状态对象的实例
     */
    static class Document {
        private String title;
        private String content;
        private DocumentState state;

        public Document(String title, String content) {
            this.title = title;
            this.content = content;
            // 初始状态为草稿状态
            this.state = new DraftState();
        }

        // 状态转换方法
        public void setState(DocumentState state) {
            this.state = state;
            System.out.println("文档状态变更: " + state.getStateName());
        }

        // 委托给当前状态对象处理
        public void review() {
            state.review(this);
        }

        public void approve() {
            state.approve(this);
        }

        public void reject() {
            state.reject(this);
        }

        public void publish() {
            state.publish(this);
        }

        // getter和setter方法
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public DocumentState getState() { return state; }

        @Override
        public String toString() {
            return "Document{title='" + title + "', state=" + state.getStateName() + "}";
        }
    }

    /**
     * 具体状态 - 草稿状态
     */
    static class DraftState implements DocumentState {
        @Override
        public void review(Document document) {
            System.out.println("文档处于草稿状态，无法提交审核");
        }

        @Override
        public void approve(Document document) {
            System.out.println("文档处于草稿状态，无法批准");
        }

        @Override
        public void reject(Document document) {
            System.out.println("文档处于草稿状态，无法拒绝");
        }

        @Override
        public void publish(Document document) {
            System.out.println("文档处于草稿状态，无法发布");
        }

        @Override
        public String getStateName() {
            return "草稿";
        }
    }

    /**
     * 具体状态 - 待审核状态
     */
    static class PendingReviewState implements DocumentState {
        @Override
        public void review(Document document) {
            System.out.println("文档已经提交审核，无需重复提交");
        }

        @Override
        public void approve(Document document) {
            System.out.println("文档审核通过，进入已批准状态");
            document.setState(new ApprovedState());
        }

        @Override
        public void reject(Document document) {
            System.out.println("文档审核未通过，返回草稿状态");
            document.setState(new DraftState());
        }

        @Override
        public void publish(Document document) {
            System.out.println("文档处于待审核状态，无法发布");
        }

        @Override
        public String getStateName() {
            return "待审核";
        }
    }

    /**
     * 具体状态 - 已批准状态
     */
    static class ApprovedState implements DocumentState {
        @Override
        public void review(Document document) {
            System.out.println("文档已批准，无法重新审核");
        }

        @Override
        public void approve(Document document) {
            System.out.println("文档已经是批准状态，无需再次批准");
        }

        @Override
        public void reject(Document document) {
            System.out.println("文档已批准，无法拒绝");
        }

        @Override
        public void publish(Document document) {
            System.out.println("文档发布成功，进入已发布状态");
            document.setState(new PublishedState());
        }

        @Override
        public String getStateName() {
            return "已批准";
        }
    }

    /**
     * 具体状态 - 已发布状态
     */
    static class PublishedState implements DocumentState {
        @Override
        public void review(Document document) {
            System.out.println("文档已发布，无法提交审核");
        }

        @Override
        public void approve(Document document) {
            System.out.println("文档已发布，无法批准");
        }

        @Override
        public void reject(Document document) {
            System.out.println("文档已发布，无法拒绝");
        }

        @Override
        public void publish(Document document) {
            System.out.println("文档已经是发布状态，无需重复发布");
        }

        @Override
        public String getStateName() {
            return "已发布";
        }
    }

    /**
     * 进阶用法：带权限控制的状态
     * 不同用户角色在不同状态下有不同的操作权限
     */
    enum UserRole {
        AUTHOR, REVIEWER, ADMIN
    }

    static class PermissionControlledDocument {
        private String title;
        private String content;
        private DocumentState state;
        private UserRole currentUserRole;

        public PermissionControlledDocument(String title, String content, UserRole role) {
            this.title = title;
            this.content = content;
            this.currentUserRole = role;
            this.state = new PermissionDraftState(role);
        }

        public void setState(DocumentState state) {
            this.state = state;
            System.out.println("文档状态变更: " + state.getStateName());
        }

        public void setCurrentUserRole(UserRole role) {
            this.currentUserRole = role;
            // 根据新角色调整状态
            if (state instanceof PermissionDraftState) {
                ((PermissionDraftState) state).setUserRole(role);
            }
        }

        public void review() {
            Document tempDoc = new Document(this.title, this.content);
            tempDoc.setState(this.state);
            state.review(tempDoc);
        }

        public void approve() {
            Document tempDoc = new Document(this.title, this.content);
            tempDoc.setState(this.state);
            state.approve(tempDoc);
        }

        public void reject() {
            Document tempDoc = new Document(this.title, this.content);
            tempDoc.setState(this.state);
            state.reject(tempDoc);
        }

        public void publish() {
            Document tempDoc = new Document(this.title, this.content);
            tempDoc.setState(this.state);
            state.publish(tempDoc);
        }

        public String getTitle() { return title; }
        public String getContent() { return content; }
        public DocumentState getState() { return state; }
        public UserRole getCurrentUserRole() { return currentUserRole; }
    }

    /**
     * 带权限控制的草稿状态
     */
    static class PermissionDraftState implements DocumentState {
        private UserRole userRole;

        public PermissionDraftState(UserRole userRole) {
            this.userRole = userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }

        @Override
        public void review(Document document) {
            if (userRole == UserRole.AUTHOR) {
                System.out.println("作者提交文档审核");
                document.setState(new PendingReviewState());
            } else {
                System.out.println("只有作者才能提交审核");
            }
        }

        @Override
        public void approve(Document document) {
            System.out.println("草稿状态无法批准");
        }

        @Override
        public void reject(Document document) {
            System.out.println("草稿状态无法拒绝");
        }

        @Override
        public void publish(Document document) {
            System.out.println("草稿状态无法发布");
        }

        @Override
        public String getStateName() {
            return "权限草稿";
        }
    }

    /**
     * 进阶用法：状态转换历史记录
     * 记录状态转换的过程
     */
    static class StatefulDocument extends Document {
        private java.util.List<String> stateHistory = new java.util.ArrayList<>();

        public StatefulDocument(String title, String content) {
            super(title, content);
            stateHistory.add(getState().getStateName());
        }

        @Override
        public void setState(DocumentState state) {
            String oldState = getState() != null ? getState().getStateName() : "初始状态";
            super.setState(state);
            String newState = state.getStateName();
            stateHistory.add(newState);
            System.out.println("状态从 '" + oldState + "' 变更为 '" + newState + "'");
        }

        public java.util.List<String> getStateHistory() {
            return new java.util.ArrayList<>(stateHistory);
        }

        public void printStateHistory() {
            System.out.println("状态转换历史: " + stateHistory);
        }
    }

    /**
     * 进阶用法：带时间限制的状态
     * 某些状态只能在特定时间内保持
     */
    static class TimeLimitedState implements DocumentState {
        private String stateName;
        private long enterTime;
        private long maxDuration;

        public TimeLimitedState(String stateName, long maxDuration) {
            this.stateName = stateName;
            this.maxDuration = maxDuration;
            this.enterTime = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - enterTime > maxDuration;
        }

        @Override
        public void review(Document document) {
            if (isExpired()) {
                System.out.println("状态已超时，自动转换");
                document.setState(new DraftState()); // 超时后回到草稿状态
            } else {
                System.out.println("执行 " + stateName + " 状态下的审核操作");
            }
        }

        @Override
        public void approve(Document document) {
            if (isExpired()) {
                System.out.println("状态已超时，无法批准");
            } else {
                System.out.println("批准文档，进入下一状态");
                document.setState(new ApprovedState());
            }
        }

        @Override
        public void reject(Document document) {
            if (isExpired()) {
                System.out.println("状态已超时，无法拒绝");
            } else {
                System.out.println("拒绝文档");
                document.setState(new DraftState());
            }
        }

        @Override
        public void publish(Document document) {
            if (isExpired()) {
                System.out.println("状态已超时，无法发布");
            } else {
                System.out.println("发布文档");
                document.setState(new PublishedState());
            }
        }

        @Override
        public String getStateName() {
            long remaining = Math.max(0, maxDuration - (System.currentTimeMillis() - enterTime));
            return stateName + " (剩余时间: " + remaining + "ms)";
        }
    }

    /**
     * 进阶用法：复合状态
     * 一个状态可能包含子状态
     */
    static class CompositeState implements DocumentState {
        private DocumentState currentState;
        private String stateName;

        public CompositeState(String stateName, DocumentState initialState) {
            this.stateName = stateName;
            this.currentState = initialState;
        }

        @Override
        public void review(Document document) {
            currentState.review(document);
        }

        @Override
        public void approve(Document document) {
            currentState.approve(document);
        }

        @Override
        public void reject(Document document) {
            currentState.reject(document);
        }

        @Override
        public void publish(Document document) {
            currentState.publish(document);
        }

        @Override
        public String getStateName() {
            return stateName + " -> " + currentState.getStateName();
        }

        public void transitionTo(DocumentState newState) {
            this.currentState = newState;
        }
    }

    /**
     * 进阶用法：状态上下文监听器
     * 监听状态变化事件
     */
    interface StateChangeListener {
        void onStateChanged(String oldState, String newState, Object context);
    }

    static class ObservableDocument extends Document {
        private java.util.List<StateChangeListener> listeners = new java.util.ArrayList<>();

        public ObservableDocument(String title, String content) {
            super(title, content);
        }

        public void addStateChangeListener(StateChangeListener listener) {
            listeners.add(listener);
        }

        public void removeStateChangeListener(StateChangeListener listener) {
            listeners.remove(listener);
        }

        @Override
        public void setState(DocumentState state) {
            String oldState = getState() != null ? getState().getStateName() : "无";
            super.setState(state);
            String newState = state.getStateName();
            
            // 通知所有监听器
            for (StateChangeListener listener : listeners) {
                listener.onStateChanged(oldState, newState, this);
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 状态模式示例 ===\n");

        System.out.println("1. 基础状态模式示例:");
        // 创建文档
        Document document = new Document("年度报告", "这是年度报告的内容...");
        System.out.println("初始状态: " + document);

        // 尝试在草稿状态下执行各种操作
        document.review();
        document.approve();
        document.reject();
        document.publish();

        System.out.println();

        // 将文档设置为待审核状态
        document.setState(new PendingReviewState());
        document.review(); // 重复提交审核
        document.approve(); // 审核通过

        System.out.println();

        // 文档现在是已批准状态
        document.approve(); // 重复批准
        document.publish(); // 发布文档

        System.out.println();

        // 文档现在是已发布状态
        document.publish(); // 重复发布

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 状态转换历史记录示例:");
        StatefulDocument statefulDoc = new StatefulDocument("测试文档", "内容");
        
        statefulDoc.setState(new PendingReviewState());
        statefulDoc.setState(new ApprovedState());
        statefulDoc.setState(new PublishedState());
        
        statefulDoc.printStateHistory();

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 带时间限制的状态示例:");
        Document timedDocument = new Document("限时文档", "内容");
        // 使用时间限制状态
        timedDocument.setState(new TimeLimitedState("限时审核", 5000)); // 5秒限制
        timedDocument.review();
        timedDocument.approve();

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 状态监听器示例:");
        ObservableDocument observableDoc = new ObservableDocument("可观察文档", "内容");
        
        // 添加监听器
        observableDoc.addStateChangeListener((oldState, newState, context) -> {
            System.out.println("状态变化: " + oldState + " -> " + newState);
        });
        
        // 进行状态转换
        observableDoc.setState(new PendingReviewState());
        observableDoc.setState(new ApprovedState());

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n5. 复合状态示例:");
        // 创建复合状态
        CompositeState compositeState = new CompositeState("复合状态", new DraftState());
        System.out.println("复合状态: " + compositeState.getStateName());
        
        // 转换子状态
        compositeState.transitionTo(new PendingReviewState());
        System.out.println("转换后的复合状态: " + compositeState.getStateName());

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n状态模式的关键特性:");
        System.out.println("1. 将状态相关的行为封装在具体状态类中");
        System.out.println("2. 状态转换逻辑集中在状态类中");
        System.out.println("3. 通过改变对象的内部状态来改变对象的行为");
        System.out.println("4. 遵循开闭原则，新增状态容易");
        System.out.println("5. 消除了庞大的条件分支语句");
        System.out.println("6. 支持多种高级功能（权限控制、历史记录、时间限制等）");
    }
}