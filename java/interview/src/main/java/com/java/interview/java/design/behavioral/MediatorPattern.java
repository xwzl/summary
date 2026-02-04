package com.java.interview.java.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式 (Mediator Pattern)
 *
 * 定义：中介者模式是一种行为型设计模式，它定义了一个中介对象来封装一系列对象之间的交互关系。
 * 中介者使各个对象不需要显式地相互引用，从而使耦合松散，而且可以独立地改变它们之间的交互。
 * 中介者模式的本质是解耦多个对象之间的复杂交互关系。
 *
 * 主要角色：
 * 1. 抽象中介者(Mediator)：定义同事对象到中介者对象的接口
 * 2. 具体中介者(Concrete Mediator)：实现抽象中介者，具体实现各个同事对象之间的协调
 * 3. 抽象同事类(Colleague)：定义同事类的接口，保存中介者对象的引用
 * 4. 具体同事类(Concrete Colleague)：实现抽象同事类的方法，需要和其他同事通信时，
 *    通知中介者，再由中介者负责与其他同事的交互
 *
 * 主要特点：
 * 1. 降低类之间的耦合：将网状依赖关系转换为星型依赖关系
 * 2. 简化对象协议：不再需要维护复杂的引用关系
 * 3. 集中化控制：将交互逻辑集中在中介者中
 * 4. 便于维护：交互逻辑集中，易于修改和扩展
 *
 * 应用场景：
 * - 系统中对象之间存在复杂的引用关系
 * - 想通过一个中间类来封装多个类的行为
 * - 想自定义一个类来控制一组对象间的交互
 */
public class MediatorPattern {

    /**
     * 抽象中介者 - 定义同事对象到中介者对象的接口
     */
    interface ChatRoomMediator {
        void sendMessage(String message, User sender);
        void addUser(User user);
        void removeUser(User user);
        List<User> getUsers();
    }

    /**
     * 抽象同事类 - 定义同事类的接口
     */
    static abstract class User {
        protected String name;
        protected ChatRoomMediator mediator;

        public User(String name, ChatRoomMediator mediator) {
            this.name = name;
            this.mediator = mediator;
        }

        public String getName() {
            return name;
        }

        // 发送消息
        public void sendMessage(String message) {
            System.out.println(name + " 发送消息: " + message);
            mediator.sendMessage(message, this);
        }

        // 接收消息 - 由子类实现
        public abstract void receiveMessage(String message, User sender);
    }

    /**
     * 具体同事类 - 普通用户
     */
    static class RegularUser extends User {
        public RegularUser(String name, ChatRoomMediator mediator) {
            super(name, mediator);
        }

        @Override
        public void receiveMessage(String message, User sender) {
            System.out.println(name + " (普通用户) 收到来自 " + sender.getName() + " 的消息: " + message);
        }
    }

    /**
     * 具体同事类 - 管理员用户
     */
    static class AdminUser extends User {
        public AdminUser(String name, ChatRoomMediator mediator) {
            super(name, mediator);
        }

        @Override
        public void receiveMessage(String message, User sender) {
            System.out.println(name + " (管理员) 收到来自 " + sender.getName() + " 的消息: " + message);
        }
    }

    /**
     * 具体同事类 - VIP用户
     */
    static class VipUser extends User {
        public VipUser(String name, ChatRoomMediator mediator) {
            super(name, mediator);
        }

        @Override
        public void receiveMessage(String message, User sender) {
            System.out.println(name + " (VIP用户) 收到来自 " + sender.getName() + " 的消息: " + message);
        }
    }

    /**
     * 具体中介者 - 聊天室
     */
    static class ChatRoom implements ChatRoomMediator {
        private List<User> users = new ArrayList<>();

        @Override
        public void sendMessage(String message, User sender) {
            // 根据消息类型和用户权限进行不同处理
            for (User user : users) {
                // 发送者不接收自己的消息
                if (!user.getName().equals(sender.getName())) {
                    // 特殊处理：管理员可以发送系统消息
                    if (sender instanceof AdminUser && message.startsWith("[SYSTEM]")) {
                        System.out.println("[系统广播] " + message.substring(9));
                        // 系统消息发送给所有用户
                        user.receiveMessage(message, sender);
                    } else if (!(sender instanceof AdminUser && message.startsWith("[PRIVATE]"))) {
                        // 非系统消息和非私密消息
                        user.receiveMessage(message, sender);
                    }
                }
            }
        }

        @Override
        public void addUser(User user) {
            users.add(user);
            System.out.println("用户 " + user.getName() + " 加入聊天室");
        }

        @Override
        public void removeUser(User user) {
            users.remove(user);
            System.out.println("用户 " + user.getName() + " 离开聊天室");
        }

        @Override
        public List<User> getUsers() {
            return new ArrayList<>(users);
        }
    }

    /**
     * 进阶用法：带消息过滤的中介者
     */
    static class FilteredChatRoom extends ChatRoom {
        private List<String> blockedWords = new ArrayList<>();

        public void addBlockedWord(String word) {
            blockedWords.add(word.toLowerCase());
        }

        public void removeBlockedWord(String word) {
            blockedWords.remove(word.toLowerCase());
        }

        @Override
        public void sendMessage(String message, User sender) {
            // 检查是否包含敏感词
            String filteredMessage = message;
            for (String word : blockedWords) {
                if (filteredMessage.toLowerCase().contains(word)) {
                    filteredMessage = filteredMessage.replaceAll("(?i)" + word, "***");
                }
            }

            if (!filteredMessage.equals(message)) {
                System.out.println("消息已被过滤: " + message + " -> " + filteredMessage);
            }

            super.sendMessage(filteredMessage, sender);
        }
    }

    /**
     * 进阶用法：带消息优先级的中介者
     */
    static class PriorityChatRoom implements ChatRoomMediator {
        private List<User> users = new ArrayList<>();
        private List<String> messageQueue = new ArrayList<>();

        @Override
        public void sendMessage(String message, User sender) {
            // 根据用户类型设置消息优先级
            if (sender instanceof AdminUser) {
                // 管理员消息优先级最高，直接发送
                processImmediateMessage(message, sender);
            } else {
                // 其他消息进入队列
                messageQueue.add(message);
                processMessageQueue();
            }
        }

        private void processImmediateMessage(String message, User sender) {
            for (User user : users) {
                if (!user.getName().equals(sender.getName())) {
                    user.receiveMessage(message, sender);
                }
            }
        }

        private void processMessageQueue() {
            // 简单实现：按顺序处理队列中的消息
            for (String message : messageQueue) {
                for (User user : users) {
                    if (message.contains(user.getName())) {
                        // 如果消息提到了特定用户，优先发送给该用户
                        user.receiveMessage(message, null); // 这里简化处理
                    }
                }
            }
            messageQueue.clear();
        }

        @Override
        public void addUser(User user) {
            users.add(user);
            System.out.println("用户 " + user.getName() + " 加入优先级聊天室");
        }

        @Override
        public void removeUser(User user) {
            users.remove(user);
            System.out.println("用户 " + user.getName() + " 离开优先级聊天室");
        }

        @Override
        public List<User> getUsers() {
            return new ArrayList<>(users);
        }
    }

    /**
     * 进阶用法：分布式中介者
     * 模拟多个中介者之间的协作
     */
    static class DistributedMediator implements ChatRoomMediator {
        private List<User> localUsers = new ArrayList<>();
        private List<ChatRoomMediator> connectedRooms = new ArrayList<>();

        public void connectToRoom(ChatRoomMediator room) {
            connectedRooms.add(room);
        }

        @Override
        public void sendMessage(String message, User sender) {
            // 在本地房间发送消息
            for (User user : localUsers) {
                if (!user.getName().equals(sender.getName())) {
                    user.receiveMessage(message, sender);
                }
            }

            // 将消息转发到连接的其他房间
            for (ChatRoomMediator room : connectedRooms) {
                // 简化实现：转发给其他房间的所有用户
                room.sendMessage("[转发] " + sender.getName() + ": " + message, sender);
            }
        }

        @Override
        public void addUser(User user) {
            localUsers.add(user);
            System.out.println("用户 " + user.getName() + " 加入分布式聊天室");
        }

        @Override
        public void removeUser(User user) {
            localUsers.remove(user);
            System.out.println("用户 " + user.getName() + " 离开分布式聊天室");
        }

        @Override
        public List<User> getUsers() {
            return new ArrayList<>(localUsers);
        }
    }

    /**
     * 进阶用法：带历史记录的中介者
     */
    static class HistoryChatRoom implements ChatRoomMediator {
        private List<User> users = new ArrayList<>();
        private List<String> messageHistory = new ArrayList<>();
        private int maxHistorySize = 100;

        @Override
        public void sendMessage(String message, User sender) {
            // 记录消息历史
            messageHistory.add(sender.getName() + ": " + message);
            if (messageHistory.size() > maxHistorySize) {
                messageHistory.remove(0); // 移除最旧的消息
            }

            // 发送消息给其他用户
            for (User user : users) {
                if (!user.getName().equals(sender.getName())) {
                    user.receiveMessage(message, sender);
                }
            }
        }

        public void showHistory(User user) {
            System.out.println("=== " + user.getName() + " 的消息历史 ===");
            for (String historyMsg : messageHistory) {
                System.out.println(historyMsg);
            }
        }

        @Override
        public void addUser(User user) {
            users.add(user);
            System.out.println("用户 " + user.getName() + " 加入带历史记录的聊天室");
        }

        @Override
        public void removeUser(User user) {
            users.remove(user);
            System.out.println("用户 " + user.getName() + " 离开带历史记录的聊天室");
        }

        @Override
        public List<User> getUsers() {
            return new ArrayList<>(users);
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 中介者模式示例 ===\n");

        System.out.println("1. 基础中介者模式示例:");
        // 创建聊天室中介者
        ChatRoomMediator chatRoom = new ChatRoom();

        // 创建用户
        User alice = new RegularUser("Alice", chatRoom);
        User bob = new RegularUser("Bob", chatRoom);
        User charlie = new AdminUser("Charlie", chatRoom);
        User diana = new VipUser("Diana", chatRoom);

        // 将用户添加到聊天室
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        chatRoom.addUser(diana);

        System.out.println("\n用户开始聊天:");
        alice.sendMessage("大家好！");
        bob.sendMessage("你好 Alice！");
        charlie.sendMessage("欢迎来到我们的聊天室！");
        diana.sendMessage("很高兴认识大家！");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 带消息过滤的中介者示例:");
        FilteredChatRoom filteredRoom = new FilteredChatRoom();
        filteredRoom.addBlockedWord("垃圾");
        filteredRoom.addBlockedWord("讨厌");

        User eve = new RegularUser("Eve", filteredRoom);
        User frank = new RegularUser("Frank", filteredRoom);

        filteredRoom.addUser(eve);
        filteredRoom.addUser(frank);

        eve.sendMessage("这是一个垃圾信息");
        frank.sendMessage("我不讨厌你");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 带优先级的中介者示例:");
        PriorityChatRoom priorityRoom = new PriorityChatRoom();

        User admin = new AdminUser("Admin", priorityRoom);
        User user1 = new RegularUser("User1", priorityRoom);
        User user2 = new RegularUser("User2", priorityRoom);

        priorityRoom.addUser(admin);
        priorityRoom.addUser(user1);
        priorityRoom.addUser(user2);

        user1.sendMessage("普通消息1");
        admin.sendMessage("管理员公告");
        user2.sendMessage("普通消息2");

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 带历史记录的中介者示例:");
        HistoryChatRoom historyRoom = new HistoryChatRoom();

        User grace = new RegularUser("Grace", historyRoom);
        User henry = new RegularUser("Henry", historyRoom);

        historyRoom.addUser(grace);
        historyRoom.addUser(henry);

        grace.sendMessage("第一条消息");
        henry.sendMessage("第二条消息");
        grace.sendMessage("第三条消息");

        System.out.println("\n显示历史记录:");
        historyRoom.showHistory(grace);

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n5. 分布式中介者示例:");
        DistributedMediator room1 = new DistributedMediator();
        DistributedMediator room2 = new DistributedMediator();

        // 连接两个房间
        room1.connectToRoom(room2);
        room2.connectToRoom(room1);

        User userInRoom1 = new RegularUser("Room1_User", room1);
        User userInRoom2 = new RegularUser("Room2_User", room2);

        room1.addUser(userInRoom1);
        room2.addUser(userInRoom2);

        System.out.println("用户在房间1发送消息:");
        userInRoom1.sendMessage("来自房间1的消息");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n中介者模式的关键特性:");
        System.out.println("1. 解耦多个对象之间的复杂交互");
        System.out.println("2. 将网状依赖关系转换为星型依赖关系");
        System.out.println("3. 集中化控制对象间的交互逻辑");
        System.out.println("4. 便于维护和扩展交互行为");
        System.out.println("5. 支持多种高级功能（过滤、优先级、历史记录等）");
        System.out.println("6. 适用于需要协调多个对象的复杂场景");
    }
}