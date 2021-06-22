package leetcode.editor.cn.cow.redsend;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 红包发送的载体
 */
public class RedPacketSendModel implements Delayed {

    /**
     * 发送批次(延迟时间的标识)
     */
    private long delayTime;

    /**
     * 红包被谁抢到了
     */
    private User user;

    /**
     * 发送的红包
     */
    private RedPacket redPacket;

    public RedPacketSendModel(long delayTime, RedPacket redPacket) {
        this.delayTime = delayTime;
        this.redPacket = redPacket;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 距离当前时间的延迟时间
        return delayTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {

        RedPacketSendModel e = (RedPacketSendModel) o;
        return this.delayTime <= e.delayTime ? -1 : 1;
    }

    @Override
    public String toString() {
        return "第" + delayTime + "秒的红包，金额为：" + redPacket.getMoney();
    }

    public RedPacket getRedPacket() {
        return redPacket;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public User getUser() {
        return user;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
