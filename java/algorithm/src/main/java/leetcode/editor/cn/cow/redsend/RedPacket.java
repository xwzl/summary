package leetcode.editor.cn.cow.redsend;

/**
 * 红包类
 */
public class RedPacket {
    private Long id; // 请实现自增逻辑
    private int money; // 单位分
    private byte state; // 0-未发送  1-已发送

    // 增加难度无意义字段，让红包不要太小
    // 水平不够可以删除字段简化题目
    // transient 的意思就是不用持久化，但是内存中得存在
    private transient byte[] padding = new byte[1024];

    public RedPacket(int money){
        this.id = SequenceGenUtils.genSequence("RED_PACKAGE");
        this.money = money;
    }


    @Override
    public String toString() {
        return "RedPacket{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }

    public int getMoney() {
        return money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public byte[] getPadding() {
        return padding;
    }

    public void setPadding(byte[] padding) {
        this.padding = padding;
    }
}
