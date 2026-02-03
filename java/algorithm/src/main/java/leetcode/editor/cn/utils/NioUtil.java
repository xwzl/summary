package leetcode.editor.cn.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java 读写工具类
 *
 * @author xuweizhi
 * @since 2020/08/06 16:47
 */
public class NioUtil {

    public static String read(String filePath) {
        try (FileInputStream input = new FileInputStream(new File(filePath))) {
            FileChannel ch = input.getChannel();
            ByteBuffer bf = ByteBuffer.allocate(1024 * 8);
            int length = -1;
            StringBuilder sb = new StringBuilder();
            while ((length = ch.read(bf)) != -1) {
                bf.clear();
                byte[] array = bf.array();
                sb.append(new String(array, 0, length));
            }
            ch.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void write(int[] nums, String filePath) {
        try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
            FileChannel ch = out.getChannel();
            int capacity = 1024 * 8;
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            // String s = JSONObject.toJSONString(nums);
            String s = "";
            byte[] bytes = s.getBytes();
            int index = bytes.length / capacity;
            int remainder = bytes.length % capacity;
            if (remainder != 0) {
                index += 1;
            }
            for (int j = 0; j < index; j++) {
                int length = 0;
                if (j == index - 1) {
                    bf.put(bytes, j * capacity, remainder == 0 ? capacity : remainder);
                } else {
                    bf.put(bytes, j * capacity, capacity);
                }
                bf.flip();
                while ((length = ch.write(bf)) != 0) {
                    /*
                     * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                     */
                    System.out.println("写入长度:" + length);
                }
                bf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
