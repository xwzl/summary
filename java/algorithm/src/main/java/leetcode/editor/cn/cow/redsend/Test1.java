package leetcode.editor.cn.cow.redsend;

import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {

    @Test
    public void expressionTemplates() throws InterruptedException {

        // 红包总金额
        int totalAmount = 100000;

        // 发放延迟时间
        int delayTime = 0;

        // 发放时间 秒
        int sendTime = 1;

        // 红包平均金额
        int avgAmount = 100;


        // 初始化红包池
        CashPool cashPool = new CashPool(totalAmount);

        ThreadPoolExecutor executorService =(ThreadPoolExecutor) Executors.newFixedThreadPool(12);
        for (int i = 0; i <= 12; i++) {
            executorService.submit(() -> {
                try {
                    while (!cashPool.buildRedPacket(avgAmount)) {
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        while (executorService.getActiveCount() != 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AtomicInteger amount = new AtomicInteger(0);
        cashPool.getRedPackets().stream().forEach(redPacket -> {
            int i = amount.get();
            amount.set(i+redPacket.getMoney());
        });
//        Assert.isTrue(amount.get() == totalAmount);


        // 构造一个发送消息的主体
        // 计算一秒钟要发多少钱
        // 假设10秒钟发完
        // 只是按照大概的规律，计算出对应一秒钟应该发多少个红包
        RedPacketSend redPacketSend = new RedPacketSend(totalAmount / (sendTime * avgAmount));
        redPacketSend.prepareRedPackage(cashPool.getRedPackets(), new Date().getTime() + delayTime * 1000);
        AtomicInteger sendAmount = new AtomicInteger(0);
        redPacketSend.getDelayQueue().stream().forEach(redPacketSendModel -> {
            sendAmount.set(sendAmount.get() +redPacketSendModel.getRedPacket().getMoney());
        });

//        Assert.isTrue(sendAmount.get() == totalAmount);

        // 抢红包 这边就模拟系统不停的发红包，等用户来抢
        // 初始化用户，来抢对应的红包
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User(redPacketSend));
        }

        // 开抢
        for (int i = 0; i < users.size(); i++) {
            new Thread(users.get(i)).start();
        }
        for (int i = delayTime; i != 0; i--) {
            Thread.sleep(1*1000);
            System.out.println(i + "秒后开始抢红包了");
        }

        while (!redPacketSend.getDelayQueue().isEmpty()){}

        // 校验用户抢到的红包，金额是否正确
        AtomicInteger userMoney = new AtomicInteger(0);
        users.stream().forEach(user->{
            List<RedPacket> redPackets = user.getRedPackets();
            redPackets.stream().forEach(redPacket -> {
                userMoney.set(userMoney.get()+redPacket.getMoney());
            });
        });
        System.out.println("用户抢到的红包总金额数："+userMoney.get());
        System.out.println("红包总金额数："+totalAmount);
//        Assert.isTrue(userMoney.get() == totalAmount);

        // 遍历用户，打印出大家抢到的红包金额
        users.stream().forEach(user -> {
            int sum = user.getRedPackets().stream().mapToInt(RedPacket::getMoney).sum();
            System.out.println("用户：" + user.getName() + "抢到了红包" + sum / 100.00 + "元");
        });

    }
}
