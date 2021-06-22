package leetcode.editor.cn.cow.redsend;

import java.util.LinkedList;
import java.util.List;

public class User implements Runnable {
    private Long id; // 请实现自增逻辑
    private String name; // 请Mock一个名字

    // 增加难度无意义字段，让红包不要太小
    // 水平不够可以删除字段简化题目
    // transient 的意思就是不用持久化，但是内存中得存在
    transient byte[] padding = new byte[1024];

    // 红包集合
    private RedPacketSend redPacketSend;

    // 抢到的红包
    private List<RedPacket> redPackets = new LinkedList<>();

    public User(RedPacketSend redPacketSend) {
        this.id = SequenceGenUtils.genSequence("RED_PACKAGE");
        this.name = "RED_PACKAGE" + this.id;
        this.redPacketSend = redPacketSend;
    }

    @Override
    public void run() {

        while (true){
            try {
                // 模拟用户刷新页面等红包，如果没有红包，就等着
                // 由于队列的特殊性，这一行是线程安全，take出来，就表示抢到红包了
                RedPacketSendModel redPacketSendModel = redPacketSend.getDelayQueue().take();
                if(redPacketSendModel.getUser() == null){
                    System.out.println("抢到自己红包了");
                    redPacketSendModel.setUser(this);
                    redPackets.add(redPacketSendModel.getRedPacket());
                }else {
                    System.out.println("对不起，该红包已经有主人了");
                    redPacketSendModel.getUser().getRedPackets().add(redPacketSendModel.getRedPacket());
                }
                try {
                    // 发送自己的，也可以帮助补偿发送一下其他人发送失败的
                    redPacketSend.sendTo(redPacketSendModel);
                } catch (RedPacketSend.FailToSendException e) {
                    System.out.println("红包发送失败");
                    System.out.println("回滚用户所属的红包");
                    // 回滚用户所属的红包
                    redPackets.remove(redPacketSendModel.getRedPacket());
                    redPacketSend.getDelayQueue().add(redPacketSendModel);
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPadding() {
        return padding;
    }

    public void setPadding(byte[] padding) {
        this.padding = padding;
    }

    public RedPacketSend getRedPacketSend() {
        return redPacketSend;
    }

    public void setRedPacketSend(RedPacketSend redPacketSend) {
        this.redPacketSend = redPacketSend;
    }

    public List<RedPacket> getRedPackets() {
        return redPackets;
    }

    public void setRedPackets(List<RedPacket> redPackets) {
        this.redPackets = redPackets;
    }
}
