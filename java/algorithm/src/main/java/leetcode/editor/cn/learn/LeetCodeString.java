package leetcode.editor.cn.learn;

import org.junit.Test;

/**
 * 力扣字符串
 *
 * @author xuweizhi
 * @since 2021/07/09 10:13
 */
public class LeetCodeString {

    /**
     * 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public static class ReverseStringExample {

        public void reverseString(char[] s) {
            int length = s.length / 2;
            int size = s.length;
            for (int i = 0; i < length; i++) {
                s[i] ^= s[size - i - 1];
                s[size - i - 1] ^= s[i];
                s[i] ^= s[size - i - 1];
            }
        }

        @Test
        public void testReverseString() {
            char[] s = "12345".toCharArray();
            reverseString(s);
            System.out.println(new String(s));
        }
    }
}
