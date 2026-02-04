package com.java.interview.java.design.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 备忘录模式 (Memento Pattern)
 *
 * 定义：备忘录模式是一种行为型设计模式，它允许在不破坏封装的情况下捕获并外部化一个对象的
 * 内部状态，以便以后可以将对象恢复到该状态。备忘录模式通常用于实现撤销/恢复功能或创建
 * 对象的快照。
 *
 * 主要角色：
 * 1. 发起人(Originator)：创建备忘录并存储当前状态，可以从备忘录恢复状态
 * 2. 备忘录(Memento)：存储发起人的内部状态，保护其内部状态不被其他对象访问
 * 3. 管理者(Caretaker)：负责保存备忘录，但不能对备忘录的内容进行操作或检查
 *
 * 主要特点：
 * 1. 在不破坏封装的前提下保存和恢复对象状态
 * 2. 提供了状态快照机制
 * 3. 支持撤销/重做功能
 * 4. 遵循单一职责原则
 *
 * 应用场景：
 * - 需要实现撤销功能
 * - 需要记录对象状态的历史记录
 * - 需要在对象间传递状态而不暴露内部细节
 */
public class MementoPattern {

    /**
     * 备忘录类 - 存储发起人的内部状态
     * 使用私有构造函数和内部类确保封装性
     */
    static class EditorMemento {
        private final String content;
        private final int cursorPosition;
        private final long timestamp;

        private EditorMemento(String content, int cursorPosition) {
            this.content = content;
            this.cursorPosition = cursorPosition;
            this.timestamp = System.currentTimeMillis();
        }

        // 获取内容
        public String getContent() {
            return content;
        }

        // 获取光标位置
        public int getCursorPosition() {
            return cursorPosition;
        }

        // 获取时间戳
        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "EditorMemento{content='" + content + "', cursorPos=" + cursorPosition + ", time=" + timestamp + "}";
        }
    }

    /**
     * 发起人类 - 文本编辑器
     * 负责创建备忘录并存储当前状态，也可以从备忘录恢复状态
     */
    static class TextEditor {
        private String content = "";
        private int cursorPosition = 0;

        public void setContent(String content) {
            this.content = content;
        }

        public void setCursorPosition(int cursorPosition) {
            this.cursorPosition = cursorPosition;
        }

        public String getContent() {
            return content;
        }

        public int getCursorPosition() {
            return cursorPosition;
        }

        // 创建备忘录 - 保存当前状态
        public EditorMemento save() {
            System.out.println("保存当前状态: 内容='" + content + "', 光标位置=" + cursorPosition);
            return new EditorMemento(content, cursorPosition);
        }

        // 从备忘录恢复状态
        public void restore(EditorMemento memento) {
            this.content = memento.getContent();
            this.cursorPosition = memento.getCursorPosition();
            System.out.println("恢复到状态: 内容='" + content + "', 光标位置=" + cursorPosition);
        }

        // 插入文本
        public void insertText(String text) {
            content = content.substring(0, cursorPosition) + text + content.substring(cursorPosition);
            cursorPosition += text.length();
            System.out.println("插入文本后: '" + content + "', 光标位置=" + cursorPosition);
        }

        // 删除文本
        public void deleteText(int length) {
            if (cursorPosition >= length) {
                int start = cursorPosition - length;
                content = content.substring(0, start) + content.substring(cursorPosition);
                cursorPosition = start;
                System.out.println("删除文本后: '" + content + "', 光标位置=" + cursorPosition);
            }
        }

        @Override
        public String toString() {
            return "TextEditor{content='" + content + "', cursorPos=" + cursorPosition + "}";
        }
    }

    /**
     * 管理者类 - 备忘录管理者
     * 负责保存备忘录，但不能访问备忘录的内部状态
     */
    static class EditorHistory {
        private Stack<EditorMemento> history = new Stack<>();
        private Stack<EditorMemento> redoStack = new Stack<>();
        private int maxHistorySize = 10; // 最大历史记录数量

        // 保存备忘录
        public void save(EditorMemento memento) {
            history.push(memento);
            // 清空重做栈，因为新保存的状态使之前的重做操作失效
            redoStack.clear();
            
            // 如果历史记录超过最大数量，移除最早的记录
            if (history.size() > maxHistorySize) {
                history.remove(0);
            }
            
            System.out.println("保存到历史记录，当前历史记录数量: " + history.size());
        }

        // 恢复到上一个状态
        public EditorMemento undo() {
            if (history.isEmpty()) {
                System.out.println("没有可撤销的状态");
                return null;
            }
            
            EditorMemento lastState = history.pop();
            redoStack.push(lastState);
            System.out.println("撤销操作，剩余历史记录数量: " + history.size() + 
                             ", 可重做数量: " + redoStack.size());
            return lastState;
        }

        // 重做操作
        public EditorMemento redo() {
            if (redoStack.isEmpty()) {
                System.out.println("没有可重做的状态");
                return null;
            }
            
            EditorMemento nextState = redoStack.pop();
            history.push(nextState);
            System.out.println("重做操作，历史记录数量: " + history.size() + 
                             ", 剩余可重做数量: " + redoStack.size());
            return nextState;
        }

        // 获取历史记录数量
        public int getHistorySize() {
            return history.size();
        }

        // 清空历史记录
        public void clear() {
            history.clear();
            redoStack.clear();
            System.out.println("清空历史记录");
        }
    }

    /**
     * 进阶用法：带命名的备忘录
     * 支持为备忘录命名，便于识别特定状态
     */
    static class NamedMemento extends EditorMemento {
        private final String name;

        public NamedMemento(String content, int cursorPosition, String name) {
            super(content, cursorPosition);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "NamedMemento{name='" + name + "', " + super.toString() + "}";
        }
    }

    /**
     * 进阶用法：带标签的备忘录管理者
     * 支持按标签查找和恢复特定状态
     */
    static class TaggedEditorHistory {
        private List<NamedMemento> taggedHistory = new ArrayList<>();
        private int maxTaggedHistorySize = 20;

        public void save(NamedMemento memento) {
            taggedHistory.add(memento);
            if (taggedHistory.size() > maxTaggedHistorySize) {
                taggedHistory.remove(0);
            }
            System.out.println("保存带标签的备忘录: " + memento.getName());
        }

        public EditorMemento restoreByTag(String tagName) {
            for (int i = taggedHistory.size() - 1; i >= 0; i--) {
                NamedMemento memento = taggedHistory.get(i);
                if (memento.getName().equals(tagName)) {
                    System.out.println("找到标签为 '" + tagName + "' 的备忘录");
                    return memento;
                }
            }
            System.out.println("未找到标签为 '" + tagName + "' 的备忘录");
            return null;
        }

        public List<String> getAllTags() {
            List<String> tags = new ArrayList<>();
            for (NamedMemento memento : taggedHistory) {
                tags.add(memento.getName());
            }
            return tags;
        }
    }

    /**
     * 进阶用法：深层备份的备忘录
     * 支持复杂对象的深度复制
     */
    static class DocumentMemento {
        private final String title;
        private final String content;
        private final List<String> metadata;
        private final long timestamp;

        public DocumentMemento(String title, String content, List<String> metadata) {
            this.title = title;
            this.content = content;
            // 深度复制metadata列表
            this.metadata = new ArrayList<>(metadata);
            this.timestamp = System.currentTimeMillis();
        }

        public String getTitle() { return title; }
        public String getContent() { return content; }
        public List<String> getMetadata() { return new ArrayList<>(metadata); }
        public long getTimestamp() { return timestamp; }
    }

    /**
     * 进阶用法：文档编辑器 - 支持复杂对象的备忘录
     */
    static class DocumentEditor {
        private String title;
        private String content;
        private List<String> metadata;

        public DocumentEditor(String title, String content) {
            this.title = title;
            this.content = content;
            this.metadata = new ArrayList<>();
        }

        public void setTitle(String title) { this.title = title; }
        public void setContent(String content) { this.content = content; }
        public void addMetadata(String meta) { this.metadata.add(meta); }
        public void removeMetadata(String meta) { this.metadata.remove(meta); }

        public DocumentMemento save() {
            System.out.println("保存文档状态: 标题='" + title + "', 内容长度=" + content.length());
            return new DocumentMemento(title, content, metadata);
        }

        public void restore(DocumentMemento memento) {
            this.title = memento.getTitle();
            this.content = memento.getContent();
            this.metadata = memento.getMetadata(); // 深度复制的metadata
            System.out.println("恢复文档状态: 标题='" + title + "', 内容长度=" + content.length());
        }

        @Override
        public String toString() {
            return "DocumentEditor{title='" + title + "', content='" + content + "', metadata=" + metadata + "}";
        }
    }

    /**
     * 进阶用法：自动保存的备忘录
     * 定期自动保存状态
     */
    static class AutoSaveEditor extends TextEditor {
        private EditorHistory history;
        private long autoSaveInterval;
        private Thread autoSaveThread;
        private volatile boolean running = false;

        public AutoSaveEditor(EditorHistory history, long autoSaveInterval) {
            this.history = history;
            this.autoSaveInterval = autoSaveInterval;
        }

        public void startAutoSave() {
            if (running) return;
            
            running = true;
            autoSaveThread = new Thread(() -> {
                while (running) {
                    try {
                        Thread.sleep(autoSaveInterval);
                        if (running) {
                            System.out.println("自动保存...");
                            history.save(save());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            autoSaveThread.start();
            System.out.println("启动自动保存，间隔: " + autoSaveInterval + "ms");
        }

        public void stopAutoSave() {
            running = false;
            if (autoSaveThread != null) {
                autoSaveThread.interrupt();
            }
            System.out.println("停止自动保存");
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 备忘录模式示例 ===\n");

        System.out.println("1. 基础备忘录模式示例:");
        // 创建文本编辑器
        TextEditor editor = new TextEditor();
        // 创建历史记录管理者
        EditorHistory history = new EditorHistory();

        // 初始状态
        System.out.println("初始状态: " + editor);

        // 修改内容并保存状态
        editor.insertText("Hello ");
        history.save(editor.save());

        editor.insertText("World!");
        history.save(editor.save());

        System.out.println("\n当前状态: " + editor);

        // 撤销操作
        EditorMemento prevState = history.undo();
        if (prevState != null) {
            editor.restore(prevState);
            System.out.println("撤销后状态: " + editor);
        }

        // 再次撤销
        prevState = history.undo();
        if (prevState != null) {
            editor.restore(prevState);
            System.out.println("再次撤销后状态: " + editor);
        }

        // 重做操作
        EditorMemento nextState = history.redo();
        if (nextState != null) {
            editor.restore(nextState);
            System.out.println("重做后状态: " + editor);
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n2. 带标签的备忘录示例:");
        TaggedEditorHistory taggedHistory = new TaggedEditorHistory();
        TextEditor taggedEditor = new TextEditor();

        taggedEditor.setContent("这是初稿");
        taggedHistory.save(new NamedMemento(taggedEditor.getContent(), taggedEditor.getCursorPosition(), "初稿"));

        taggedEditor.insertText(" - 已修改");
        taggedHistory.save(new NamedMemento(taggedEditor.getContent(), taggedEditor.getCursorPosition(), "第一版"));

        taggedEditor.insertText(" - 最终版");
        taggedHistory.save(new NamedMemento(taggedEditor.getContent(), taggedEditor.getCursorPosition(), "最终版"));

        System.out.println("所有标签: " + taggedHistory.getAllTags());

        // 恢复到第一版
        EditorMemento firstVersion = taggedHistory.restoreByTag("第一版");
        if (firstVersion != null) {
            taggedEditor.restore(firstVersion);
            System.out.println("恢复到第一版后: " + taggedEditor);
        }

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 复杂对象备忘录示例:");
        DocumentEditor docEditor = new DocumentEditor("我的文档", "这是文档内容");
        docEditor.addMetadata("作者: 张三");
        docEditor.addMetadata("创建日期: 2023-01-01");

        System.out.println("初始文档: " + docEditor);

        // 保存状态
        DocumentMemento docMemento = docEditor.save();

        // 修改文档
        docEditor.setTitle("我的文档 - 修改版");
        docEditor.setContent("这是修改后的文档内容");
        docEditor.addMetadata("修改日期: 2023-01-02");

        System.out.println("修改后文档: " + docEditor);

        // 恢复到之前的状态
        docEditor.restore(docMemento);
        System.out.println("恢复后文档: " + docEditor);

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 自动保存备忘录示例:");
        EditorHistory autoHistory = new EditorHistory();
        AutoSaveEditor autoEditor = new AutoSaveEditor(autoHistory, 2000); // 2秒自动保存
        
        // 开始自动保存
        autoEditor.startAutoSave();

        // 模拟一些编辑操作
        autoEditor.setContent("自动保存测试内容");
        try {
            Thread.sleep(2500); // 等待自动保存
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 停止自动保存
        autoEditor.stopAutoSave();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n备忘录模式的关键特性:");
        System.out.println("1. 在不破坏封装的情况下保存和恢复对象状态");
        System.out.println("2. 支持撤销/重做功能");
        System.out.println("3. 提供状态快照机制");
        System.out.println("4. 支持带标签和命名的备忘录");
        System.out.println("5. 支持复杂对象的深度备份");
        System.out.println("6. 可实现自动保存功能");
        System.out.println("7. 遵循单一职责原则，各角色职责明确");
    }
}