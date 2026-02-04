package com.java.interview.java.design.structural;

/**
 * 桥接模式 (Bridge Pattern)
 * 
 * 定义：桥接模式是一种结构型设计模式，它可以将一个大类或一系列紧密相关的类拆分为抽象和实现两个独立的层次结构，
 * 从而实现二者的解耦。这种模式将继承关系替换为组合关系，从而降低了抽象和实现之间的耦合度。
 * 
 * 主要角色：
 * 1. 抽象(Abstraction)：定义抽象类的接口
 * 2. 精确抽象(Refined Abstraction)：扩展抽象类的接口实现
 * 3. 实现者(Implementor)：定义实现类的接口
 * 4. 具体实现(Concrete Implementor)：实现实现者接口的具体类
 * 
 * 主要特点：
 * 1. 分离抽象与实现：使二者可以独立变化
 * 2. 提高扩展能力：抽象部分和实现部分可以独立扩展
 * 3. 实现细节对客户透明：客户不需要知道实现细节
 * 
 * 应用场景：
 * - 不想在抽象和其实现部分之间有一个固定的绑定关系
 * - 抽象和实现部分都应该通过子类化进行扩展
 * - 对一个抽象的实现部分的修改应对客户不产生影响
 * - 需要跨越多个平台的图形和窗口系统
 */
public class BridgePattern {

    /**
     * 实现者接口 - 定义实现部分的接口
     */
    interface DrawAPI {
        void drawCircle(int radius, int x, int y);
        void drawRectangle(int width, int height, int x, int y);
        void drawLine(int x1, int y1, int x2, int y2);
    }

    /**
     * 具体实现 - 红色绘制API
     */
    static class RedDrawAPI implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawRectangle(int width, int height, int x, int y) {
            System.out.println("Drawing Rectangle[ color: red, width: " + width + ", height: " + height + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {
            System.out.println("Drawing Line[ color: red, start: (" + x1 + "," + y1 + "), end: (" + x2 + "," + y2 + ") ]");
        }
    }

    /**
     * 具体实现 - 绿色绘制API
     */
    static class GreenDrawAPI implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawRectangle(int width, int height, int x, int y) {
            System.out.println("Drawing Rectangle[ color: green, width: " + width + ", height: " + height + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {
            System.out.println("Drawing Line[ color: green, start: (" + x1 + "," + y1 + "), end: (" + x2 + "," + y2 + ") ]");
        }
    }

    /**
     * 具体实现 - 蓝色绘制API
     */
    static class BlueDrawAPI implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: blue, radius: " + radius + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawRectangle(int width, int height, int x, int y) {
            System.out.println("Drawing Rectangle[ color: blue, width: " + width + ", height: " + height + ", x: " + x + ", y: " + y + "]");
        }

        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {
            System.out.println("Drawing Line[ color: blue, start: (" + x1 + "," + y1 + "), end: (" + x2 + "," + y2 + ") ]");
        }
    }

    /**
     * 抽象 - 形状抽象类
     * 持有一个对实现者的引用，通过组合关系连接抽象和实现
     */
    static abstract class Shape {
        protected DrawAPI drawAPI;

        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }

        public abstract void draw();
    }

    /**
     * 精确抽象 - 圆形类
     */
    static class Circle extends Shape {
        private int x, y, radius;

        public Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void draw() {
            drawAPI.drawCircle(radius, x, y);
        }
    }

    /**
     * 精确抽象 - 矩形类
     */
    static class Rectangle extends Shape {
        private int x, y, width, height;

        public Rectangle(int x, int y, int width, int height, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw() {
            drawAPI.drawRectangle(width, height, x, y);
        }
    }

    /**
     * 精确抽象 - 线条类
     */
    static class Line extends Shape {
        private int x1, y1, x2, y2;

        public Line(int x1, int y1, int x2, int y2, DrawAPI drawAPI) {
            super(drawAPI);
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public void draw() {
            drawAPI.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 桥接模式的高级应用 - 操作系统与UI框架的桥接
     */
    static class BridgeAdvancedExample {
        // 实现者接口 - UI渲染引擎
        interface UIEngine {
            void renderButton(String text);
            void renderTextBox(String content);
            void renderWindow(String title);
        }

        // 具体实现 - Windows UI引擎
        static class WindowsUIEngine implements UIEngine {
            @Override
            public void renderButton(String text) {
                System.out.println("Rendering button '" + text + "' with Windows style");
            }

            @Override
            public void renderTextBox(String content) {
                System.out.println("Rendering textbox with content '" + content + "' in Windows style");
            }

            @Override
            public void renderWindow(String title) {
                System.out.println("Rendering window titled '" + title + "' with Windows style border");
            }
        }

        // 具体实现 - macOS UI引擎
        static class MacOSUIEngine implements UIEngine {
            @Override
            public void renderButton(String text) {
                System.out.println("Rendering button '" + text + "' with macOS style");
            }

            @Override
            public void renderTextBox(String content) {
                System.out.println("Rendering textbox with content '" + content + "' in macOS style");
            }

            @Override
            public void renderWindow(String title) {
                System.out.println("Rendering window titled '" + title + "' with macOS style border");
            }
        }

        // 具体实现 - Linux UI引擎
        static class LinuxUIEngine implements UIEngine {
            @Override
            public void renderButton(String text) {
                System.out.println("Rendering button '" + text + "' with Linux style");
            }

            @Override
            public void renderTextBox(String content) {
                System.out.println("Rendering textbox with content '" + content + "' in Linux style");
            }

            @Override
            public void renderWindow(String title) {
                System.out.println("Rendering window titled '" + title + "' with Linux style border");
            }
        }

        // 抽象 - UI控件基类
        abstract static class UIControl {
            protected UIEngine uiEngine;

            protected UIControl(UIEngine uiEngine) {
                this.uiEngine = uiEngine;
            }

            public abstract void render();
        }

        // 精确抽象 - 按钮控件
        static class Button extends UIControl {
            private String text;

            public Button(String text, UIEngine uiEngine) {
                super(uiEngine);
                this.text = text;
            }

            @Override
            public void render() {
                uiEngine.renderButton(text);
            }
        }

        // 精确抽象 - 文本框控件
        static class TextBox extends UIControl {
            private String content;

            public TextBox(String content, UIEngine uiEngine) {
                super(uiEngine);
                this.content = content;
            }

            @Override
            public void render() {
                uiEngine.renderTextBox(content);
            }
        }

        // 精确抽象 - 窗口控件
        static class Window extends UIControl {
            private String title;

            public Window(String title, UIEngine uiEngine) {
                super(uiEngine);
                this.title = title;
            }

            @Override
            public void render() {
                uiEngine.renderWindow(title);
            }
        }
    }

    /**
     * 桥接模式的进一步应用 - 设备与功能的桥接
     */
    static class DeviceFunctionBridge {
        // 实现者接口 - 设备功能
        interface DeviceFunction {
            void operate();
            void adjustVolume(int level);
            void setChannel(int channel);
        }

        // 具体实现 - TV功能
        static class TVFunction implements DeviceFunction {
            private boolean isOn = false;
            private int volume = 50;
            private int channel = 1;

            @Override
            public void operate() {
                isOn = !isOn;
                System.out.println("TV is now " + (isOn ? "ON" : "OFF"));
            }

            @Override
            public void adjustVolume(int level) {
                this.volume = Math.max(0, Math.min(100, level));
                System.out.println("TV volume adjusted to: " + volume);
            }

            @Override
            public void setChannel(int channel) {
                this.channel = channel;
                System.out.println("TV channel set to: " + channel);
            }
        }

        // 具体实现 - 音响功能
        static class RadioFunction implements DeviceFunction {
            private boolean isOn = false;
            private int volume = 30;

            @Override
            public void operate() {
                isOn = !isOn;
                System.out.println("Radio is now " + (isOn ? "ON" : "OFF"));
            }

            @Override
            public void adjustVolume(int level) {
                this.volume = Math.max(0, Math.min(100, level));
                System.out.println("Radio volume adjusted to: " + volume);
            }

            @Override
            public void setChannel(int channel) {
                System.out.println("Radio frequency set to: " + channel + " MHz");
            }
        }

        // 抽象 - 遥控器
        abstract static class RemoteControl {
            protected DeviceFunction deviceFunction;

            protected RemoteControl(DeviceFunction deviceFunction) {
                this.deviceFunction = deviceFunction;
            }

            public abstract void power();
            public abstract void volumeUp();
            public abstract void volumeDown();
            public abstract void channelUp();
            public abstract void channelDown();
        }

        // 精确抽象 - 基础遥控器
        static class BasicRemote extends RemoteControl {
            public BasicRemote(DeviceFunction deviceFunction) {
                super(deviceFunction);
            }

            @Override
            public void power() {
                deviceFunction.operate();
            }

            @Override
            public void volumeUp() {
                deviceFunction.adjustVolume(deviceFunction instanceof TVFunction ? 60 : 40);
            }

            @Override
            public void volumeDown() {
                deviceFunction.adjustVolume(deviceFunction instanceof TVFunction ? 40 : 20);
            }

            @Override
            public void channelUp() {
                deviceFunction.setChannel(deviceFunction instanceof TVFunction ? 5 : 101);
            }

            @Override
            public void channelDown() {
                deviceFunction.setChannel(deviceFunction instanceof TVFunction ? 3 : 99);
            }
        }

        // 精确抽象 - 高级遥控器
        static class AdvancedRemote extends RemoteControl {
            public AdvancedRemote(DeviceFunction deviceFunction) {
                super(deviceFunction);
            }

            @Override
            public void power() {
                deviceFunction.operate();
            }

            @Override
            public void volumeUp() {
                // 获取当前音量并增加
                // 这里简化处理，直接增加20
                deviceFunction.adjustVolume(getCurrentVolume() + 20);
            }

            @Override
            public void volumeDown() {
                deviceFunction.adjustVolume(getCurrentVolume() - 20);
            }

            @Override
            public void channelUp() {
                deviceFunction.setChannel(getCurrentChannel() + 1);
            }

            @Override
            public void channelDown() {
                deviceFunction.setChannel(getCurrentChannel() - 1);
            }

            private int getCurrentVolume() {
                // 简化实现，返回预设值
                return deviceFunction instanceof TVFunction ? 50 : 30;
            }

            private int getCurrentChannel() {
                // 简化实现，返回预设值
                return deviceFunction instanceof TVFunction ? 1 : 100;
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 桥接模式 (Bridge Pattern) 示例 ===\n");

        // 基础桥接模式示例
        System.out.println("1. 基础桥接模式示例 - 图形绘制:");
        Shape redCircle = new Circle(100, 100, 10, new RedDrawAPI());
        redCircle.draw();

        Shape greenRectangle = new Rectangle(50, 50, 20, 30, new GreenDrawAPI());
        greenRectangle.draw();

        Shape blueLine = new Line(0, 0, 100, 100, new BlueDrawAPI());
        blueLine.draw();
        System.out.println();

        // 高级桥接模式示例 - UI渲染
        System.out.println("2. 高级桥接模式示例 - UI渲染:");
        BridgeAdvancedExample.Button windowsButton = 
            new BridgeAdvancedExample.Button("Submit", new BridgeAdvancedExample.WindowsUIEngine());
        windowsButton.render();

        BridgeAdvancedExample.TextBox macosTextBox = 
            new BridgeAdvancedExample.TextBox("Hello World", new BridgeAdvancedExample.MacOSUIEngine());
        macosTextBox.render();

        BridgeAdvancedExample.Window linuxWindow = 
            new BridgeAdvancedExample.Window("My App", new BridgeAdvancedExample.LinuxUIEngine());
        linuxWindow.render();
        System.out.println();

        // 设备功能桥接示例
        System.out.println("3. 设备功能桥接示例 - 遥控器:");
        DeviceFunctionBridge.RemoteControl tvRemote = 
            new DeviceFunctionBridge.BasicRemote(new DeviceFunctionBridge.TVFunction());
        tvRemote.power();
        tvRemote.volumeUp();
        tvRemote.channelUp();

        DeviceFunctionBridge.RemoteControl radioRemote = 
            new DeviceFunctionBridge.AdvancedRemote(new DeviceFunctionBridge.RadioFunction());
        radioRemote.power();
        radioRemote.volumeUp();
        System.out.println();

        System.out.println("=== 桥接模式的关键特性 ===");
        System.out.println("1. 解耦抽象与实现: 抽象部分和实现部分可以独立变化");
        System.out.println("2. 提高扩展性: 可以独立扩展抽象部分和实现部分");
        System.out.println("3. 实现细节透明: 客户端不需要了解实现细节");
        System.out.println("4. 减少子类数量: 避免类爆炸问题");
        System.out.println("5. 组合优于继承: 使用组合关系替代继承关系");
        System.out.println("6. 支持多维度变化: 可以处理多个维度的变化");
    }
}