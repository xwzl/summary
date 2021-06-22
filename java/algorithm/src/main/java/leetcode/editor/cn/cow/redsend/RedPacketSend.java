package leetcode.editor.cn.cow.redsend;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 红包发送类
 */
public class RedPacketSend {

    /**
     * 红包发放的延迟队列
     * 这个队列里面，只维护一秒钟内应该发送的红包，如果一秒内发送的红包数量没有消费完，会导致
     * 后面红包的阻塞
     */
    private DelayQueue<RedPacketSendModel> delayQueue;


    /**
     * 单次发送红包的数量
     */
    private int singleSend;

    /**
     * 一秒发送多少个红包
     * @param singleSend
     */
    public RedPacketSend(int singleSend) {
        this.delayQueue = new DelayQueue<>();
        this.singleSend = singleSend;

//        // 每一个消息发送器，都有一个线程监控失败队列
//        new Thread(() -> {
//            while (true) {
//                RedPacketSendModel take = null;
//                try {
//                    take = failDelayQueue.take();
//                    System.out.println("开始补偿发送失败的红包，user.name=" + take.getUser() + "redPackageId=" + take.getRedPacket().getId());
//                    sendTo(take);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (FailToSendException e) {
//                    failDelayQueue.add(take);
//                    System.out.println("补偿红包失败，user.name=" + take.getUser() + "redPackageId=" + take.getRedPacket().getId());
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }


    /**
     * 往队列里面准备红包，让用户来抢
     * 一直往延迟队列里面塞红包，按照秒来塞
     * 此方法只用于单线程，发送的时候，可以看成一个  RedPacketSend就是一个线程
     * @param redPackets
     * @param startTime 活动开始时间
     */
    public void prepareRedPackage(LinkedBlockingDeque<RedPacket> redPackets,long startTime){

        // 延迟的开始时间标记
        int delayTime = 0;

        // 延迟的标记
        int delayFlag = 0;

        while (!redPackets.isEmpty()){
            RedPacket redPacket = redPackets.poll();

            // 生成红包发送模型  开始时间+延迟时间的标记
            RedPacketSendModel redPacketSendModel = new RedPacketSendModel(startTime + delayTime * 1000, redPacket);
            delayQueue.add(redPacketSendModel);

            // 线程安全，直接比对
            if (++delayFlag == singleSend) {
                 // 当前这一秒的红包已经初始化完成
                delayFlag = 0;
                delayTime++;
            }
        }
    }

    static class FailToSendException extends Exception {
    }

    public void  sendTo(RedPacketSendModel take) throws InterruptedException, FailToSendException {
        // 模拟发送，什么都不做
        Thread.sleep((long) (1000 + (Math.random() * 1000)));
        if(Math.random() > 0.99) {
            throw new FailToSendException();
        }
    }

    public DelayQueue<RedPacketSendModel> getDelayQueue() {
        return delayQueue;
    }

}
