package com.java.interview.java.design.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式 (Flyweight Pattern)
 * 
 * 定义：享元模式是一种结构型设计模式，它通过共享尽可能多的相似对象来减少内存使用和提高性能。
 * 享元模式使用共享技术有效地支持大量细粒度的对象。这种模式试图重用现有的同类对象，
 * 如果未找到匹配的对象，则创建新对象。
 * 
 * 主要角色：
 * 1. 抽象享元(Flyweight)：声明一个接口，通过该接口可以接受并作用于外部状态
 * 2. 具体享元(Concrete Flyweight)：实现抽象享元接口，为内部状态增加存储空间
 * 3. 非享元(Unshared Concrete Flyweight)：并非所有抽象享元的子类都需要被共享
 * 4. 享元工厂(Flyweight Factory)：负责创建和管理享元对象
 * 
 * 主要特点：
 * 1. 共享对象：通过共享来支持大量细粒度的对象
 * 2. 内部状态与外部状态分离：内部状态可共享，外部状态不可共享
 * 3. 内存优化：显著减少内存使用
 * 
 * 应用场景：
 * - 系统中有大量相似的对象
 * - 需要缓冲池的场景
 * - 对象创建成本很高，需要优化性能
 * - 大多数状态可以变为外部状态
 */
public class FlyweightPattern {

    /**
     * 抽象享元 - 字符接口
     */
    interface Character {
        void display(int fontSize, String fontColor, int x, int y);
        String getName();
    }

    /**
     * 具体享元 - 具体字符
     */
    static class CharacterImpl implements Character {
        private char symbol; // 内部状态：字符符号
        private String fontFamily; // 内部状态：字体

        public CharacterImpl(char symbol, String fontFamily) {
            this.symbol = symbol;
            this.fontFamily = fontFamily;
        }

        @Override
        public void display(int fontSize, String fontColor, int x, int y) {
            System.out.printf("Displaying '%c' - Font: %s, Size: %d, Color: %s at (%d, %d)\n", 
                             symbol, fontFamily, fontSize, fontColor, x, y);
        }

        @Override
        public String getName() {
            return String.valueOf(symbol);
        }
    }

    /**
     * 享元工厂 - 字符工厂
     */
    static class CharacterFactory {
        private static final Map<String, Character> characterPool = new HashMap<>();

        public static Character getCharacter(char symbol, String fontFamily) {
            String key = symbol + "-" + fontFamily;
            Character character = characterPool.get(key);

            if (character == null) {
                character = new CharacterImpl(symbol, fontFamily);
                characterPool.put(key, character);
                System.out.println("Creating new character: " + key);
            } else {
                System.out.println("Reusing character: " + key);
            }

            return character;
        }

        public static int getPoolSize() {
            return characterPool.size();
        }
    }

    /**
     * 享元模式的高级应用 - 森林生态系统
     */
    static class ForestSimulation {
        // 抽象享元 - 树木接口
        interface Tree {
            void display(int x, int y, String color, int height);
        }

        // 具体享元 - 树木类型
        static class TreeType implements Tree {
            private String name; // 内部状态：树木名称
            private String color; // 内部状态：默认颜色
            private String texture; // 内部状态：纹理

            public TreeType(String name, String color, String texture) {
                this.name = name;
                this.color = color;
                this.texture = texture;
            }

            @Override
            public void display(int x, int y, String treeColor, int height) {
                System.out.printf("Tree: %s at (%d, %d), Color: %s (was %s), Height: %d, Texture: %s\n", 
                                name, x, y, treeColor, this.color, height, texture);
            }
        }

        // 享元工厂 - 树木工厂
        static class TreeFactory {
            private static final Map<String, TreeType> treeTypes = new HashMap<>();

            public static TreeType getTreeType(String name, String color, String texture) {
                String key = name + "-" + color + "-" + texture;
                TreeType treeType = treeTypes.get(key);

                if (treeType == null) {
                    treeType = new TreeType(name, color, texture);
                    treeTypes.put(key, treeType);
                    System.out.println("Creating new tree type: " + key);
                }

                return treeType;
            }

            public static int getTreeTypeCount() {
                return treeTypes.size();
            }
        }

        // 树木 - 包含外部状态
        static class TreeObject {
            private int x, y; // 外部状态：位置
            private int height; // 外部状态：高度
            private String color; // 外部状态：颜色
            private TreeType type; // 享元：类型信息

            public TreeObject(int x, int y, int height, String color, TreeType type) {
                this.x = x;
                this.y = y;
                this.height = height;
                this.color = color;
                this.type = type;
            }

            public void display() {
                type.display(x, y, color, height);
            }
        }

        // 森林 - 管理大量树木
        static class Forest {
            private java.util.List<TreeObject> trees = new java.util.ArrayList<>();

            public void plantTree(int x, int y, String name, String color, String texture, int height) {
                TreeType type = TreeFactory.getTreeType(name, color, texture);
                TreeObject tree = new TreeObject(x, y, height, color, type);
                trees.add(tree);
            }

            public void displayForest() {
                System.out.println("\nDisplaying forest with " + trees.size() + " trees:");
                for (TreeObject tree : trees) {
                    tree.display();
                }
            }
        }
    }

    /**
     * 享元模式的进一步应用 - 游戏中的敌人
     */
    static class GameEnemySystem {
        // 抽象享元 - 敌人接口
        interface Enemy {
            void render(int x, int y, String skinColor, int health);
            String getType();
        }

        // 具体享元 - 敌人类型
        static class EnemyType implements Enemy {
            private String type; // 内部状态：敌人类型
            private int baseDamage; // 内部状态：基础伤害
            private String weapon; // 内部状态：武器
            private int maxHealth; // 内部状态：最大生命值

            public EnemyType(String type, int baseDamage, String weapon, int maxHealth) {
                this.type = type;
                this.baseDamage = baseDamage;
                this.weapon = weapon;
                this.maxHealth = maxHealth;
            }

            @Override
            public void render(int x, int y, String skinColor, int health) {
                System.out.printf("%s enemy at (%d, %d), Skin: %s, Health: %d/%d, Weapon: %s, Damage: %d\n", 
                                type, x, y, skinColor, health, maxHealth, weapon, baseDamage);
            }

            @Override
            public String getType() {
                return type;
            }
        }

        // 享元工厂 - 敌人工厂
        static class EnemyFactory {
            private static final Map<String, EnemyType> enemyTypes = new HashMap<>();

            public static EnemyType getEnemyType(String type, int baseDamage, String weapon, int maxHealth) {
                String key = type + "-" + baseDamage + "-" + weapon + "-" + maxHealth;
                EnemyType enemyType = enemyTypes.get(key);

                if (enemyType == null) {
                    enemyType = new EnemyType(type, baseDamage, weapon, maxHealth);
                    enemyTypes.put(key, enemyType);
                    System.out.println("Creating new enemy type: " + key);
                }

                return enemyType;
            }

            public static int getEnemyTypeCount() {
                return enemyTypes.size();
            }
        }

        // 敌人实例 - 包含外部状态
        static class EnemyInstance {
            private int x, y; // 外部状态：位置
            private int health; // 外部状态：当前生命值
            private String skinColor; // 外部状态：皮肤颜色
            private EnemyType type; // 享元：类型信息

            public EnemyInstance(int x, int y, int health, String skinColor, EnemyType type) {
                this.x = x;
                this.y = y;
                this.health = health;
                this.skinColor = skinColor;
                this.type = type;
            }

            public void render() {
                type.render(x, y, skinColor, health);
            }

            public String getType() {
                return type.getType();
            }
        }

        // 游戏关卡 - 管理大量敌人
        static class GameLevel {
            private java.util.List<EnemyInstance> enemies = new java.util.ArrayList<>();

            public void spawnEnemy(int x, int y, String type, int health, String skinColor) {
                EnemyType enemyType;
                if ("Zombie".equals(type)) {
                    enemyType = EnemyFactory.getEnemyType("Zombie", 10, "Claws", 50);
                } else if ("Skeleton".equals(type)) {
                    enemyType = EnemyFactory.getEnemyType("Skeleton", 15, "Bow", 30);
                } else if ("Orc".equals(type)) {
                    enemyType = EnemyFactory.getEnemyType("Orc", 20, "Axe", 80);
                } else {
                    enemyType = EnemyFactory.getEnemyType("Goblin", 8, "Sword", 25);
                }

                EnemyInstance enemy = new EnemyInstance(x, y, health, skinColor, enemyType);
                enemies.add(enemy);
            }

            public void renderEnemies() {
                System.out.println("\nRendering game level with " + enemies.size() + " enemies:");
                for (EnemyInstance enemy : enemies) {
                    enemy.render();
                }
            }
        }
    }

    /**
     * 享元模式的进一步应用 - 文档编辑器
     */
    static class DocumentEditor {
        // 抽象享元 - 文本格式接口
        interface TextFormat {
            void applyFormat(String text, int x, int y);
        }

        // 具体享元 - 文本格式
        static class TextFormatImpl implements TextFormat {
            private String fontFamily; // 内部状态：字体
            private int fontSize; // 内部状态：字号
            private boolean bold; // 内部状态：粗体
            private boolean italic; // 内部状态：斜体
            private String textColor; // 内部状态：文字颜色

            public TextFormatImpl(String fontFamily, int fontSize, boolean bold, boolean italic, String textColor) {
                this.fontFamily = fontFamily;
                this.fontSize = fontSize;
                this.bold = bold;
                this.italic = italic;
                this.textColor = textColor;
            }

            @Override
            public void applyFormat(String text, int x, int y) {
                String style = (bold ? "Bold " : "") + (italic ? "Italic " : "") + fontSize + "pt " + fontFamily;
                System.out.printf("Formatting text: '%s' at (%d, %d) with style: %s, color: %s\n", 
                                text, x, y, style, textColor);
            }
        }

        // 享元工厂 - 文本格式工厂
        static class TextFormatFactory {
            private static final Map<String, TextFormatImpl> formats = new HashMap<>();

            public static TextFormatImpl getFormat(String fontFamily, int fontSize, boolean bold, boolean italic, String textColor) {
                String key = fontFamily + "-" + fontSize + "-" + bold + "-" + italic + "-" + textColor;
                TextFormatImpl format = formats.get(key);

                if (format == null) {
                    format = new TextFormatImpl(fontFamily, fontSize, bold, italic, textColor);
                    formats.put(key, format);
                    System.out.println("Creating new text format: " + key);
                }

                return format;
            }

            public static int getFormatCount() {
                return formats.size();
            }
        }

        // 文本段落 - 包含外部状态
        static class TextParagraph {
            private String text; // 外部状态：文本内容
            private int x, y; // 外部状态：位置
            private TextFormatImpl format; // 享元：格式信息

            public TextParagraph(String text, int x, int y, TextFormatImpl format) {
                this.text = text;
                this.x = x;
                this.y = y;
                this.format = format;
            }

            public void render() {
                format.applyFormat(text, x, y);
            }
        }

        // 文档 - 管理大量文本段落
        static class Document {
            private java.util.List<TextParagraph> paragraphs = new java.util.ArrayList<>();

            public void addParagraph(String text, int x, int y, String fontFamily, int fontSize, 
                                   boolean bold, boolean italic, String textColor) {
                TextFormatImpl format = TextFormatFactory.getFormat(fontFamily, fontSize, bold, italic, textColor);
                TextParagraph paragraph = new TextParagraph(text, x, y, format);
                paragraphs.add(paragraph);
            }

            public void renderDocument() {
                System.out.println("\nRendering document with " + paragraphs.size() + " paragraphs:");
                for (TextParagraph paragraph : paragraphs) {
                    paragraph.render();
                }
            }
        }
    }

    /**
     * 享元模式的进一步应用 - 网络连接池
     */
    static class NetworkConnectionPool {
        // 抽象享元 - 网络连接接口
        interface NetworkConnection {
            void connect(String host, int port, String protocol, String credentials);
            void disconnect();
        }

        // 具体享元 - 连接类型
        static class ConnectionType implements NetworkConnection {
            private String connectionProtocol; // 内部状态：协议
            private int timeout; // 内部状态：超时时间
            private String encryption; // 内部状态：加密方式

            public ConnectionType(String protocol, int timeout, String encryption) {
                this.connectionProtocol = protocol;
                this.timeout = timeout;
                this.encryption = encryption;
            }

            @Override
            public void connect(String host, int port, String protocol, String credentials) {
                System.out.printf("Connecting to %s:%d using %s (timeout: %ds, encryption: %s) with credentials: ***\n", 
                                host, port, connectionProtocol, timeout, encryption);
            }

            @Override
            public void disconnect() {
                System.out.println("Disconnecting from " + connectionProtocol + " connection");
            }
        }

        // 享元工厂 - 连接类型工厂
        static class ConnectionTypeFactory {
            private static final Map<String, ConnectionType> connectionTypes = new HashMap<>();

            public static ConnectionType getConnectionType(String protocol, int timeout, String encryption) {
                String key = protocol + "-" + timeout + "-" + encryption;
                ConnectionType connType = connectionTypes.get(key);

                if (connType == null) {
                    connType = new ConnectionType(protocol, timeout, encryption);
                    connectionTypes.put(key, connType);
                    System.out.println("Creating new connection type: " + key);
                }

                return connType;
            }

            public static int getConnectionTypeCount() {
                return connectionTypes.size();
            }
        }

        // 连接实例 - 包含外部状态
        static class ConnectionInstance {
            private String host; // 外部状态：主机
            private int port; // 外部状态：端口
            private String credentials; // 外部状态：凭据
            private ConnectionType type; // 享元：类型信息
            private boolean isConnected; // 连接状态

            public ConnectionInstance(String host, int port, String credentials, ConnectionType type) {
                this.host = host;
                this.port = port;
                this.credentials = credentials;
                this.type = type;
                this.isConnected = false;
            }

            public void connect() {
                if (!isConnected) {
                    type.connect(host, port, type.connectionProtocol, credentials);
                    isConnected = true;
                }
            }

            public void disconnect() {
                if (isConnected) {
                    type.disconnect();
                    isConnected = false;
                }
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 享元模式 (Flyweight Pattern) 示例 ===\n");

        // 基础享元模式示例 - 字符处理
        System.out.println("1. 基础享元模式示例 - 字符处理:");
        Character charA1 = CharacterFactory.getCharacter('A', "Arial");
        charA1.display(12, "black", 10, 20);

        Character charA2 = CharacterFactory.getCharacter('A', "Arial"); // 应该重用
        charA2.display(14, "red", 30, 40);

        Character charB = CharacterFactory.getCharacter('B', "Arial");
        charB.display(12, "blue", 50, 60);

        Character charA3 = CharacterFactory.getCharacter('A', "Times New Roman"); // 不同字体，新建
        charA3.display(16, "green", 70, 80);

        System.out.println("Character pool size: " + CharacterFactory.getPoolSize());
        System.out.println();

        // 森林生态系统示例
        System.out.println("2. 森林生态系统示例:");
        ForestSimulation.Forest forest = new ForestSimulation.Forest();
        
        // 添加多种树木（许多相同类型的树木）
        for (int i = 0; i < 5; i++) {
            forest.plantTree(i * 10, i * 5, "Oak", "Brown", "Rough", 10 + i);
        }
        
        for (int i = 0; i < 3; i++) {
            forest.plantTree(i * 15, i * 8, "Pine", "Green", "Smooth", 8 + i);
        }
        
        // 添加一些不同类型的树木
        forest.plantTree(100, 50, "Oak", "Dark Brown", "Very Rough", 15);
        forest.plantTree(120, 60, "Pine", "Light Green", "Smooth", 12);
        
        forest.displayForest();
        System.out.println("Unique tree types created: " + ForestSimulation.TreeFactory.getTreeTypeCount());
        System.out.println();

        // 游戏敌人系统示例
        System.out.println("3. 游戏敌人系统示例:");
        GameEnemySystem.GameLevel level = new GameEnemySystem.GameLevel();
        
        // 生成大量敌人（许多相同类型的敌人）
        for (int i = 0; i < 10; i++) {
            level.spawnEnemy(i * 20, i * 10, "Zombie", 30 - i, "Green");
        }
        
        for (int i = 0; i < 5; i++) {
            level.spawnEnemy(i * 25, i * 15, "Skeleton", 20 - i, "White");
        }
        
        level.renderEnemies();
        System.out.println("Unique enemy types created: " + GameEnemySystem.EnemyFactory.getEnemyTypeCount());
        System.out.println();

        // 文档编辑器示例
        System.out.println("4. 文档编辑器示例:");
        DocumentEditor.Document doc = new DocumentEditor.Document();
        
        // 添加多个使用相同格式的段落
        for (int i = 0; i < 3; i++) {
            doc.addParagraph("Paragraph " + (i+1), 10, 10 + i * 30, "Arial", 12, false, false, "Black");
        }
        
        // 添加一些不同格式的段落
        doc.addParagraph("Header", 10, 100, "Arial", 18, true, false, "Blue");
        doc.addParagraph("Emphasized text", 10, 130, "Arial", 14, true, true, "Red");
        
        doc.renderDocument();
        System.out.println("Unique text formats created: " + DocumentEditor.TextFormatFactory.getFormatCount());
        System.out.println();

        // 网络连接池示例
        System.out.println("5. 网络连接池示例:");
        NetworkConnectionPool.ConnectionTypeFactory.getConnectionType("HTTPS", 30, "TLS 1.3");
        NetworkConnectionPool.ConnectionTypeFactory.getConnectionType("HTTPS", 30, "TLS 1.3"); // 重用
        NetworkConnectionPool.ConnectionTypeFactory.getConnectionType("HTTP", 15, "None"); // 新建
        
        System.out.println("Unique connection types: " + NetworkConnectionPool.ConnectionTypeFactory.getConnectionTypeCount());
        
        NetworkConnectionPool.ConnectionInstance conn1 = 
            new NetworkConnectionPool.ConnectionInstance("server1.com", 443, "user1:pass1", 
                NetworkConnectionPool.ConnectionTypeFactory.getConnectionType("HTTPS", 30, "TLS 1.3"));
        conn1.connect();
        conn1.disconnect();
        
        NetworkConnectionPool.ConnectionInstance conn2 = 
            new NetworkConnectionPool.ConnectionInstance("server2.com", 443, "user2:pass2", 
                NetworkConnectionPool.ConnectionTypeFactory.getConnectionType("HTTPS", 30, "TLS 1.3")); // 重用类型
        conn2.connect();
        conn2.disconnect();
        
        System.out.println();

        System.out.println("=== 享元模式的关键特性 ===");
        System.out.println("1. 共享对象: 通过共享来支持大量细粒度的对象");
        System.out.println("2. 内外状态分离: 内部状态可共享，外部状态不可共享");
        System.out.println("3. 内存优化: 显著减少内存使用");
        System.out.println("4. 性能提升: 减少对象创建开销");
        System.out.println("5. 适合大量相似对象: 当系统中存在大量相似对象时效果明显");
        System.out.println("6. 状态管理: 需要仔细管理内部状态和外部状态的分离");
    }
}