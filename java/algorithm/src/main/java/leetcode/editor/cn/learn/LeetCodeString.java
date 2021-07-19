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


    /**
     * 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    public static class IntegerConvertExample {
        @SuppressWarnings("all")
        public int reverse(int x) {
            boolean flag = x < 0;


            String s = x + "";
            if (s.charAt(0) == '-') {
                s = s.substring(1);
            }
            char[] chars = s.toCharArray();
            int length = chars.length / 2;
            int size = chars.length;
            for (int i = 0; i < length; i++) {
                chars[i] ^= chars[size - i - 1];
                chars[size - i - 1] ^= chars[i];
                chars[i] ^= chars[size - i - 1];
            }
            if (Long.parseLong(new String(chars)) > Integer.MAX_VALUE) {
                return 0;
            }

            return (int) (flag ? -Long.parseLong(new String(chars)) : Long.parseLong(new String(chars)));
        }

        @Test
        public void reverseTest() {
            IntegerConvertExample integerConvertExample = new IntegerConvertExample();
            System.out.println(integerConvertExample.reverse(-2147483648));

        }
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public static class UniqueCharExample {
        public int firstUniqueChar(String s) {
            char[] chars = s.toCharArray();
            int[] temp = new int[128];
            int length = chars.length;
            for (int i = 0; i < length; i++) {
                temp[chars[i] - 65] += 1;
            }
            for (int i = 0; i < length; i++) {
                if (temp[chars[i] - 65] == 1) {
                    return i;
                }
            }
            return -1;
        }

        @Test
        public void mainTest() {
            UniqueCharExample uniqueCharExample = new UniqueCharExample();
            System.out.println(uniqueCharExample.firstUniqueChar("swa"));

        }
    }


    /**
     * 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     */
    public static class AnagramExample {

        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int length = s.length();
            int[] temp = new int[128];
            for (int i = 0; i < length; i++) {
                ++temp[s.charAt(i) - 'a'];
                --temp[t.charAt(i) - 'a'];
            }
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     */
    public static class PalindromeExample {
        public boolean isPalindrome(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    stringBuilder.append(Character.toLowerCase(c));
                }
            }
            String s1 = stringBuilder.toString();
            //return s1.equals(stringBuilder.reverse().toString());
            int left = 0, right = s1.length() - 1;
            while (left < right) {
                if(s1.charAt(left) != s1.charAt(right)){
                    return false;
                }
                left++;
                right++;
            }
            return true;
        }

        @Test
        public void charTest() {
            System.out.println(Character.isLetterOrDigit('a'));
            System.out.println(Character.isLetterOrDigit(','));
        }
    }

}
