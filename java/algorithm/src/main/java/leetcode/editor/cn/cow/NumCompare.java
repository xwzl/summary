package leetcode.editor.cn.cow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 牛客 day one
 *
 * @author xuweizhi
 * @since 2020/11/16 15:06
 */
public class NumCompare {

    /**
     * 第一个为测试多少组数据,后面依次输入 a,b,c 判断 a + b > c ?
     */
    @Test
    public void numCompare() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            long c = scanner.nextLong();
            System.out.printf("Case #%d: %b%n", i + 1, a + b > c);
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 5, 15, 26, 38, 40};
        int[] b = {1, 3, 4, 6, 7};
        int tempIndex = 0;
        List<String> strings = new ArrayList<>();
        for (int k : b) {
            if (tempIndex == a.length - 1) {
                break;
            }
            for (int j = tempIndex; j < a.length; j++) {
                if (k > a[j]) {
                    continue;
                }
                tempIndex = j + 1;
                strings.add(k + ":" + a[j]);
                break;
            }
            if (tempIndex == 0) {
                break;
            }
        }
        System.out.println(strings);
    }

    /**
     * 给定一系列正整数，请按要求对数字进行分类，并输出以下5个数字：
     * <p>
     * A1 = 能被5整除的数字中所有偶数的和；
     * A2 = 将被5除后余1的数字按给出顺序进行交错求和，即计算n1-n2+n3-n4...；
     * A3 = 被5除后余2的数字的个数；
     * A4 = 被5除后余3的数字的平均数，精确到小数点后1位；
     * A5 = 被5除后余4的数字中最大数字。
     */
    @Test
    public void test() {
        //13 1 2 3 4 5 6 7 8 9 10 20 16 18
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        int a41 = 0;
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            int inInt = scanner.nextInt();
            switch (inInt % 5) {
                case 0:
                    if ((inInt & 1) == 0) a1 += inInt;
                    break;
                case 1:
                    if (flag) {
                        a2 += inInt;
                        flag = false;
                    } else {
                        a2 -= inInt;
                        flag = true;
                    }
                    break;
                case 2:
                    a3++;
                    break;
                case 3:
                    a4 += inInt;
                    a4 += 1;
                    break;
                case 4:
                    if (a5 < inInt) a5 = inInt;
                    break;
            }
        }

        System.out.printf("N %d %d N %d %n", a2, a3, a5);
    }


    /**
     * 输出指定位数的素数
     */
    @Test
    public void xToYPrimeNumbers() {
        Scanner input = new Scanner(System.in);
        int minIndex = input.nextInt();
        int maxIndex = input.nextInt();
        int index = 0;
        int k = 0;

        for (int i = 1; i <= 999999; i++) {
            if (prime(i) == true) {
                index++;
                if (index >= minIndex && index <= maxIndex) {
                    k++;
                    if (k % 10 == 0) {
                        System.out.print(i);
                        System.out.println();
                    } else if (index == maxIndex) {
                        System.out.print(i);
                    } else {
                        System.out.print(i + " ");
                    }
                }
            }
        }
    }

    public static boolean prime(int key) {
        if (key == 1) return false;
        else {
            for (int i = 1; i <= Math.sqrt(key); i++) {
                if (key % i == 0 && i != 1)
                    return false;
            }
        }
        return true;
    }
}
