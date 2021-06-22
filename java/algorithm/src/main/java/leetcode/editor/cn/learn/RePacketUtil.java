package leetcode.editor.cn.learn;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 发送一亿红包
 *
 * @author xuweizhi
 * @since 2021/06/10 17:22
 */
public class RePacketUtil {


    public static void main(String[] args) throws InterruptedException {
        // 一亿钱
        AtomicInteger totalMoney = new AtomicInteger(100000000);
        SecureRandom secureRandom = new SecureRandom("Happy new year".getBytes(StandardCharsets.UTF_8));
        int money = secureRandom.nextInt(10);
        AtomicInteger sum = new AtomicInteger(0);
        if (sum.addAndGet(money) <= totalMoney.get()) {
            RedPacket redPacket =RedPacket.create(money);
        }

    }
}
