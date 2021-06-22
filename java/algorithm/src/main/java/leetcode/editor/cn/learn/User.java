package leetcode.editor.cn.learn;

public class User {
    //
    private int id;
    private String name; // 请Mock一个名字

    // 增加难度无意义字段，让红包不要太小
    // 水平不够可以删除字段简化题目
    // transient 的意思就是不用持久化，但是内存中得存在
    transient byte[] padding = new byte[1024];

    public User(String name){
        this.name = name;
    }


}
