package com.java.interview.java.design.behavioral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 命令模式 (Command Pattern)
 *
 * 定义：命令模式是一种行为型设计模式，它将请求封装为对象，从而使你可以使用不同的请求、
 * 队列或者日志来参数化其他对象，同时支持可撤销的操作。命令模式将发出请求的对象和执行请求
 * 的对象解耦，使得请求本身成为一种对象。
 *
 * 主要角色：
 * 1. 命令接口(Command)：声明执行操作的接口
 * 2. 具体命令(Concrete Command)：将一个接收者对象绑定于一个动作，实现命令接口
 * 3. 接收者(Receiver)：知道如何实施与执行一个请求相关的具体操作
 * 4. 调用者(Invoker)：要求该命令执行这个请求
 * 5. 客户端(Client)：创建具体的命令对象并设置其接收者
 *
 * 主要特点：
 * 1. 请求发送者和接收者解耦：发送者不需要知道接收者及其操作细节
 * 2. 支持撤销/重做：可以记录命令历史以实现撤销功能
 * 3. 参数化：可以用不同的命令配置客户端
 * 4. 队列化：可以将命令放入队列进行批量处理
 *
 * 应用场景：
 * - GUI按钮点击事件处理
 * - 撤销/重做功能
 * - 事务处理
 * - 日志记录
 */
public class CommandPattern {

    /**
     * 命令接口 - 定义执行操作的接口
     */
    interface Command {
        void execute();
        void undo();
    }

    /**
     * 接收者 - 文本编辑器
     * 知道如何实施与执行一个请求相关的具体操作
     */
    static class TextEditor {
        private StringBuilder content = new StringBuilder();
        private int cursorPosition = 0;

        public void insertText(String text) {
            content.insert(cursorPosition, text);
            cursorPosition += text.length();
            System.out.println("插入文本: \"" + text + "\"，当前位置: " + cursorPosition);
        }

        public void deleteText(int length) {
            if (cursorPosition >= length) {
                int start = cursorPosition - length;
                String deletedText = content.substring(start, cursorPosition);
                content.delete(start, cursorPosition);
                cursorPosition = start;
                System.out.println("删除文本: \"" + deletedText + "\"，当前位置: " + cursorPosition);
            }
        }

        public void moveCursor(int position) {
            cursorPosition = Math.max(0, Math.min(position, content.length()));
            System.out.println("光标移动到位置: " + cursorPosition);
        }

        public String getContent() {
            return content.toString();
        }

        public int getCursorPosition() {
            return cursorPosition;
        }

        @Override
        public String toString() {
            return "TextEditor{content='" + content + "', cursorPosition=" + cursorPosition + "}";
        }
    }

    /**
     * 具体命令 - 插入文本命令
     */
    static class InsertTextCommand implements Command {
        private TextEditor editor;
        private String text;
        private int position;
        private String previousState;

        public InsertTextCommand(TextEditor editor, String text) {
            this.editor = editor;
            this.text = text;
            this.position = editor.getCursorPosition();
            this.previousState = editor.getContent();
        }

        @Override
        public void execute() {
            System.out.println("执行插入文本命令: \"" + text + "\"");
            editor.insertText(text);
        }

        @Override
        public void undo() {
            System.out.println("撤销插入文本命令: \"" + text + "\"");
            // 恢复到插入前的状态
            editor.content = new StringBuilder(previousState);
            editor.cursorPosition = position;
            System.out.println("恢复到: " + editor);
        }
    }

    /**
     * 具体命令 - 删除文本命令
     */
    static class DeleteTextCommand implements Command {
        private TextEditor editor;
        private String deletedText;
        private int position;
        private String previousState;

        public DeleteTextCommand(TextEditor editor, int length) {
            this.editor = editor;
            this.position = editor.getCursorPosition();
            int start = Math.max(0, position - length);
            this.deletedText = editor.getContent().substring(start, position);
            this.previousState = editor.getContent();
        }

        @Override
        public void execute() {
            System.out.println("执行删除文本命令: 长度=" + deletedText.length() + ", 内容=\"" + deletedText + "\"");
            editor.deleteText(deletedText.length());
        }

        @Override
        public void undo() {
            System.out.println("撤销删除文本命令: \"" + deletedText + "\"");
            // 恢复到删除前的状态
            editor.content = new StringBuilder(previousState);
            editor.cursorPosition = position;
            System.out.println("恢复到: " + editor);
        }
    }

    /**
     * 具体命令 - 移动光标命令
     */
    static class MoveCursorCommand implements Command {
        private TextEditor editor;
        private int newPosition;
        private int oldPosition;

        public MoveCursorCommand(TextEditor editor, int newPosition) {
            this.editor = editor;
            this.newPosition = newPosition;
            this.oldPosition = editor.getCursorPosition();
        }

        @Override
        public void execute() {
            System.out.println("执行移动光标命令: 从 " + oldPosition + " 到 " + newPosition);
            editor.moveCursor(newPosition);
        }

        @Override
        public void undo() {
            System.out.println("撤销移动光标命令: 从 " + newPosition + " 回到 " + oldPosition);
            editor.moveCursor(oldPosition);
        }
    }

    /**
     * 具体命令 - 复合命令（宏命令）
     * 将多个命令组合成一个复合命令
     */
    static class MacroCommand implements Command {
        private List<Command> commands = new ArrayList<>();

        public void addCommand(Command command) {
            commands.add(command);
        }

        public void removeCommand(Command command) {
            commands.remove(command);
        }

        @Override
        public void execute() {
            System.out.println("执行复合命令，包含 " + commands.size() + " 个子命令:");
            for (Command command : commands) {
                command.execute();
            }
        }

        @Override
        public void undo() {
            System.out.println("撤销复合命令，按相反顺序撤销子命令:");
            // 按相反顺序撤销命令
            for (int i = commands.size() - 1; i >= 0; i--) {
                commands.get(i).undo();
            }
        }
    }

    /**
     * 调用者 - 命令执行器
     * 要求命令执行请求，维护命令历史以支持撤销功能
     */
    static class CommandExecutor {
        private Stack<Command> commandHistory = new Stack<>();
        private Stack<Command> redoStack = new Stack<>();

        public void executeCommand(Command command) {
            System.out.println("--- 执行命令 ---");
            command.execute();
            commandHistory.push(command);
            // 清空重做栈，因为执行新命令后之前的重做操作失效
            redoStack.clear();
            System.out.println("当前编辑器状态: " + commandHistory.peek().getClass().getSimpleName() + " 已执行");
        }

        public void undo() {
            if (!commandHistory.isEmpty()) {
                System.out.println("--- 执行撤销 ---");
                Command command = commandHistory.pop();
                command.undo();
                redoStack.push(command);
                System.out.println("撤销完成，可重做命令数: " + redoStack.size());
            } else {
                System.out.println("没有可撤销的命令");
            }
        }

        public void redo() {
            if (!redoStack.isEmpty()) {
                System.out.println("--- 执行重做 ---");
                Command command = redoStack.pop();
                command.execute();
                commandHistory.push(command);
                System.out.println("重做完成");
            } else {
                System.out.println("没有可重做的命令");
            }
        }

        public void showHistory() {
            System.out.println("命令历史 (" + commandHistory.size() + " 个):");
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + commandHistory.get(i).getClass().getSimpleName());
            }
        }

        public boolean canUndo() {
            return !commandHistory.isEmpty();
        }

        public boolean canRedo() {
            return !redoStack.isEmpty();
        }
    }

    /**
     * 进阶用法：延迟命令执行
     * 支持延迟执行的命令
     */
    static class DelayedCommand implements Command {
        private Command command;
        private int delaySeconds;

        public DelayedCommand(Command command, int delaySeconds) {
            this.command = command;
            this.delaySeconds = delaySeconds;
        }

        @Override
        public void execute() {
            System.out.println("延迟命令将在 " + delaySeconds + " 秒后执行");
            new Thread(() -> {
                try {
                    Thread.sleep(delaySeconds * 1000);
                    System.out.println("延迟时间到，开始执行命令");
                    command.execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        @Override
        public void undo() {
            command.undo();
        }
    }

    /**
     * 进阶用法：条件命令
     * 只在满足特定条件时执行的命令
     */
    static class ConditionalCommand implements Command {
        private Command command;
        private java.util.function.Supplier<Boolean> condition;

        public ConditionalCommand(Command command, java.util.function.Supplier<Boolean> condition) {
            this.command = command;
            this.condition = condition;
        }

        @Override
        public void execute() {
            if (condition.get()) {
                System.out.println("条件满足，执行命令");
                command.execute();
            } else {
                System.out.println("条件不满足，跳过命令执行");
            }
        }

        @Override
        public void undo() {
            if (condition.get()) {
                command.undo();
            }
        }
    }

    /**
     * 进阶用法：事务命令
     * 将多个命令作为一个事务执行，要么全部成功，要么全部回滚
     */
    static class TransactionCommand implements Command {
        private List<Command> commands;
        private List<String> backupStates;

        public TransactionCommand(List<Command> commands) {
            this.commands = new ArrayList<>(commands);
        }

        @Override
        public void execute() {
            backupStates = new ArrayList<>();
            
            // 备份初始状态
            for (Command cmd : commands) {
                if (cmd instanceof InsertTextCommand) {
                    backupStates.add(((InsertTextCommand) cmd).editor.getContent());
                } else if (cmd instanceof DeleteTextCommand) {
                    backupStates.add(((DeleteTextCommand) cmd).editor.getContent());
                }
            }

            System.out.println("开始事务执行 " + commands.size() + " 个命令:");
            boolean allSucceeded = true;
            for (int i = 0; i < commands.size(); i++) {
                try {
                    System.out.println("执行第 " + (i + 1) + " 个命令:");
                    commands.get(i).execute();
                } catch (Exception e) {
                    System.out.println("命令执行失败: " + e.getMessage());
                    allSucceeded = false;
                    break;
                }
            }

            if (!allSucceeded) {
                System.out.println("事务执行失败，正在回滚...");
                rollback();
            } else {
                System.out.println("事务执行成功");
            }
        }

        @Override
        public void undo() {
            // 按相反顺序撤销命令
            for (int i = commands.size() - 1; i >= 0; i--) {
                commands.get(i).undo();
            }
        }

        private void rollback() {
            System.out.println("回滚到事务开始前的状态");
            // 这里简化处理，实际应用中可能需要更复杂的回滚逻辑
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 命令模式示例 ===\n");

        // 创建接收者
        TextEditor editor = new TextEditor();
        System.out.println("初始编辑器状态: " + editor);

        // 创建调用者
        CommandExecutor executor = new CommandExecutor();

        System.out.println("\n1. 基础命令模式示例:");
        
        // 创建并执行命令
        Command insertCmd1 = new InsertTextCommand(editor, "Hello ");
        executor.executeCommand(insertCmd1);
        System.out.println("编辑器内容: " + editor.getContent());

        Command insertCmd2 = new InsertTextCommand(editor, "World!");
        executor.executeCommand(insertCmd2);
        System.out.println("编辑器内容: " + editor.getContent());

        Command moveCmd = new MoveCursorCommand(editor, 5);
        executor.executeCommand(moveCmd);

        Command deleteCmd = new DeleteTextCommand(editor, 1);
        executor.executeCommand(deleteCmd);
        System.out.println("编辑器内容: " + editor.getContent());

        System.out.println("\n2. 撤销/重做功能示例:");
        executor.showHistory();
        
        // 撤销删除操作
        executor.undo();
        System.out.println("撤销后编辑器内容: " + editor.getContent());
        
        // 重做删除操作
        executor.redo();
        System.out.println("重做后编辑器内容: " + editor.getContent());

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n3. 复合命令示例:");
        // 创建复合命令
        MacroCommand macro = new MacroCommand();
        macro.addCommand(new InsertTextCommand(editor, " Java"));
        macro.addCommand(new InsertTextCommand(editor, " Programming"));
        macro.addCommand(new MoveCursorCommand(editor, 0));

        executor.executeCommand(macro);
        System.out.println("复合命令执行后编辑器内容: " + editor.getContent());

        // 撤销复合命令
        executor.undo();
        System.out.println("撤销复合命令后编辑器内容: " + editor.getContent());

        System.out.println("\n" + "=".repeat(60));

        System.out.println("\n4. 条件命令示例:");
        // 创建一个只有在编辑器内容长度小于某个值时才执行的命令
        Command conditionalInsert = new ConditionalCommand(
            new InsertTextCommand(editor, " [条件插入]"),
            () -> editor.getContent().length() < 50
        );
        executor.executeCommand(conditionalInsert);

        // 创建一个条件不满足的命令
        Command conditionalDelete = new ConditionalCommand(
            new DeleteTextCommand(editor, 5),
            () -> editor.getContent().length() > 100  // 当前内容长度不足100
        );
        executor.executeCommand(conditionalDelete);

        System.out.println("\n编辑器最终内容: " + editor.getContent());

        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n命令模式的关键特性:");
        System.out.println("1. 解耦请求发送者和接收者");
        System.out.println("2. 支持撤销/重做操作");
        System.out.println("3. 支持命令的排队和日志记录");
        System.out.println("4. 容易扩展新的命令类型");
        System.out.println("5. 支持宏命令（组合命令）");
        System.out.println("6. 可以实现事务性操作");
        System.out.println("7. 支持延迟执行和条件执行");
    }
}