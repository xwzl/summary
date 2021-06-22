package leetcode.editor.cn.cow.redsend;

import java.util.concurrent.ConcurrentHashMap;

public class SequenceGenUtils {

    /**
     * 保存一个与业务相关的自增id存储器
     */
    private static final ConcurrentHashMap<String,Long> ID_GEN = new ConcurrentHashMap<>();

    /**
     * 根据业务，生成对应业务下的自增id
     */
    public static Long genSequence(String bizType) {

        Long id;
        Long newId;
        do {
            if (ID_GEN.get(bizType) == null) {
                synchronized (ID_GEN) {
                    if (ID_GEN.get(bizType) == null) {
                        // 初始化集合里面的数字
                        ID_GEN.put(bizType, 1L);
                    }
                }
            }
            id = ID_GEN.get(bizType);
            newId = id + 1;
        }
        while (!ID_GEN.replace(bizType, id, newId));
        return newId;
    }


    /**
     * 根据
     */



}
