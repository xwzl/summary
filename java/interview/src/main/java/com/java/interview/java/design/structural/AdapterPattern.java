package com.java.interview.java.design.structural;

/**
 * 适配器模式 (Adapter Pattern)
 * 
 * 定义：适配器模式是一种结构型设计模式，它允许不兼容的对象协同工作。
 * 通过将一个类的接口转换为客户期望的另一个接口，适配器模式可以让原本由于接口不兼容而不能一起工作的类一起工作。
 * 
 * 主要角色：
 * 1. 目标接口(Target)：客户期望使用的接口
 * 2. 适配者(Adaptee)：需要被适配的现有类，其接口与目标接口不兼容
 * 3. 适配器(Adapter)：实现目标接口，并持有适配者的引用，负责将适配者接口转换为目标接口
 * 
 * 主要特点：
 * 1. 将不兼容的接口转换为兼容的接口
 * 2. 通过组合而非继承实现接口转换
 * 3. 解决现有类与需求接口不匹配的问题
 * 
 * 应用场景：
 * - 系统使用一些现有的类，但这些类的接口不符合系统的需要
 * - 想要建立一个可以重复使用的类，与彼此之间没有关联的一些类协同工作
 * - 需要复用一些现存的类，但是其接口又与复用环境要求不一致
 */
public class AdapterPattern {

    /**
     * 目标接口 - 客户端期望的接口
     */
    interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    /**
     * 适配者接口 - 高级播放器接口，支持更多格式
     */
    interface AdvancedMediaPlayer {
        void playVlc(String fileName);
        void playMp4(String fileName);
    }

    /**
     * 适配者实现 - VLC播放器
     */
    static class VlcPlayer implements AdvancedMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {
            // VLC播放器不支持MP4格式，所以不实现
            System.out.println("VLC player can't play mp4 file: " + fileName);
        }
    }

    /**
     * 适配者实现 - MP4播放器
     */
    static class Mp4Player implements AdvancedMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            // MP4播放器不支持VLC格式，所以不实现
            System.out.println("MP4 player can't play vlc file: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    /**
     * 适配器 - 媒体播放器适配器
     * 将高级播放器接口适配到普通播放器接口
     */
    static class MediaAdapter implements MediaPlayer {
        private AdvancedMediaPlayer advancedMusicPlayer;

        public MediaAdapter(String audioType) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer = new VlcPlayer();
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMusicPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer.playVlc(fileName);
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMusicPlayer.playMp4(fileName);
            }
        }
    }

    /**
     * 音乐播放器 - 客户端类
     * 只能使用MediaPlayer接口，但通过适配器可以播放各种格式
     */
    static class AudioPlayer implements MediaPlayer {
        @Override
        public void play(String audioType, String fileName) {
            // 内置支持MP3格式
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing mp3 file. Name: " + fileName);
            }
            // 通过适配器支持其他格式
            else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
                MediaAdapter mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            }
            // 不支持的格式
            else {
                System.out.println("Invalid media. " + audioType + " format not supported");
            }
        }
    }

    /**
     * 对象适配器示例 - 使用组合方式实现适配器
     */
    static class ObjectAdapterExample {
        public static class LegacyRectangle {
            public double getLength() {
                return 10.0;
            }

            public double getWidth() {
                return 5.0;
            }
        }

        public interface ModernShape {
            double getArea();
        }

        // 适配器 - 将旧的矩形类适配到新的形状接口
        public static class RectangleAdapter implements ModernShape {
            private LegacyRectangle legacyRectangle;

            public RectangleAdapter(LegacyRectangle legacyRectangle) {
                this.legacyRectangle = legacyRectangle;
            }

            @Override
            public double getArea() {
                // 适配旧接口到新接口
                return legacyRectangle.getLength() * legacyRectangle.getWidth();
            }
        }
    }

    /**
     * 类适配器示例 - 使用继承方式实现适配器
     * 注意：Java不支持多继承，所以类适配器在Java中较少使用
     * 这里使用接口来模拟类适配器的概念
     */
    static class ClassAdapterExample {
        // 旧的支付系统
        public static class PaymentProcessor {
            public void makePayment(double amount) {
                System.out.println("Processing payment of $" + amount + " via old system");
            }
        }

        // 新的目标接口
        public interface ModernPaymentService {
            void processPayment(double amount);
            void refund(double amount);
        }

        // 适配器 - 同时实现新接口并继承旧类（这里通过组合模拟）
        public static class PaymentAdapter extends PaymentProcessor implements ModernPaymentService {
            @Override
            public void processPayment(double amount) {
                // 使用父类的支付功能
                super.makePayment(amount);
            }

            @Override
            public void refund(double amount) {
                // 实现退款功能
                System.out.println("Refunding $" + amount + " via old system");
            }
        }
    }

    /**
     * 双向适配器示例 - 可以在两个方向上进行转换
     */
    static class BidirectionalAdapterExample {
        // 两个不兼容的接口
        public interface CelsiusReporter {
            double getTemperatureInCelsius();
        }

        public interface FahrenheitReporter {
            double getTemperatureInFahrenheit();
        }

        // 双向适配器 - 可以在摄氏度和华氏度之间双向转换
        public static class TemperatureAdapter implements CelsiusReporter, FahrenheitReporter {
            private double temperatureInCelsius;

            public TemperatureAdapter(double temperatureInCelsius) {
                this.temperatureInCelsius = temperatureInCelsius;
            }

            @Override
            public double getTemperatureInCelsius() {
                return temperatureInCelsius;
            }

            @Override
            public double getTemperatureInFahrenheit() {
                return (temperatureInCelsius * 9 / 5) + 32;
            }

            public void setTemperatureInCelsius(double temperatureInCelsius) {
                this.temperatureInCelsius = temperatureInCelsius;
            }

            public void setTemperatureInFahrenheit(double temperatureInFahrenheit) {
                this.temperatureInCelsius = (temperatureInFahrenheit - 32) * 5 / 9;
            }
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 适配器模式 (Adapter Pattern) 示例 ===\n");

        // 普通适配器示例
        System.out.println("1. 普通适配器示例 - 音频播放器:");
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_me.avi");
        System.out.println();

        // 对象适配器示例
        System.out.println("2. 对象适配器示例 - 矩形面积计算:");
        ObjectAdapterExample.LegacyRectangle legacyRect = new ObjectAdapterExample.LegacyRectangle();
        ObjectAdapterExample.RectangleAdapter adapter = 
            new ObjectAdapterExample.RectangleAdapter(legacyRect);
        System.out.println("Area calculated via adapter: " + adapter.getArea());
        System.out.println();

        // 类适配器示例
        System.out.println("3. 类适配器示例 - 支付系统:");
        ClassAdapterExample.PaymentAdapter paymentAdapter = new ClassAdapterExample.PaymentAdapter();
        paymentAdapter.processPayment(100.0);
        paymentAdapter.refund(20.0);
        System.out.println();

        // 双向适配器示例
        System.out.println("4. 双向适配器示例 - 温度转换:");
        BidirectionalAdapterExample.TemperatureAdapter tempAdapter = 
            new BidirectionalAdapterExample.TemperatureAdapter(25.0);
        System.out.println("Temperature in Celsius: " + tempAdapter.getTemperatureInCelsius());
        System.out.println("Temperature in Fahrenheit: " + tempAdapter.getTemperatureInFahrenheit());
        
        // 设置华氏温度
        tempAdapter.setTemperatureInFahrenheit(77.0);
        System.out.println("After setting to 77°F, in Celsius: " + tempAdapter.getTemperatureInCelsius());
        System.out.println();

        System.out.println("=== 适配器模式的关键特性 ===");
        System.out.println("1. 接口转换: 将一个类的接口转换为客户期望的另一个接口");
        System.out.println("2. 复用现有类: 无需修改现有类的代码即可复用");
        System.out.println("3. 松耦合: 降低类之间的耦合度");
        System.out.println("4. 开闭原则: 对扩展开放，对修改关闭");
        System.out.println("5. 对象适配器: 使用组合关系实现，更灵活");
        System.out.println("6. 类适配器: 使用继承关系实现，适用范围有限");
    }
}