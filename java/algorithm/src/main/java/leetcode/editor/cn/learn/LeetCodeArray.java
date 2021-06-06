package leetcode.editor.cn.learn;

import org.junit.Test;

import java.util.Arrays;

/**
 * 初级算法--数组
 *
 * @author xuweizhi
 * @since 2021/06/02 11:03
 */
public class LeetCodeArray {

    /**
     * 删除排序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * <p>
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * <p>
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     * <p>
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     */
    public static class DeleteOrderArraysRepeatNum {

        public int removeDuplicates(int[] nums) {
            int length = nums.length;
            if (length == 0) {
                return 0;
            }
            int temp = nums[0], cursor = 0;
            for (int i = 1; i < length; i++) {
                if (temp != nums[i]) {
                    nums[cursor++] = temp;
                    temp = nums[i];
                }
            }
            if (nums[cursor] != temp) {
                nums[cursor] = temp;
            }
            return ++cursor;
        }

        @Test
        public void testRemoveDuplicates() {
            int[] nums = {1, 1, 2};
            System.out.println(removeDuplicates(nums));
            System.out.println(Arrays.toString(nums));
            System.out.println(removeDuplicates(nums));
            System.out.println(Arrays.toString(nums));
            System.out.println(removeDuplicates1(nums));
            System.out.println(Arrays.toString(nums));
        }

        public int removeDuplicates1(int[] nums) {
            int length = nums.length;
            if (length == 0) {
                return 0;
            }
            int index = 0;
            for (int i = 0; i < length; i++) {
                // 1 1 1 2
                if (nums[index] != nums[i]) {
                    nums[++index] = nums[i];
                }
            }
            return ++index;
        }
    }

    /**
     * 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: prices = [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: prices = [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 3 * 104
     * 0 <= prices[i] <= 104
     */
    public static class MaxProfit {
        public int maxProfit(int[] prices) {
            int length = prices.length;
            if (length == 0) {
                return 0;
            }
            int sum = 0, temp = prices[0], index = 0;
            for (int i = 1; i < length; i++) {
                // 小于 1 3 5 7 3 4
                if (temp > prices[i]) {
                    if (temp != prices[index]) {
                        sum += temp - prices[index];
                    }
                    index = i;
                }
                temp = prices[i];
            }
            if (prices[index] != prices[length - 1]) {
                sum += prices[length - 1] - prices[index];
            }
            return sum;
        }

        @Test
        public void testMaxProfit() {
            int[] ints = {7, 1, 5, 3, 6, 4, 9};
            System.out.println(maxProfit(ints));
        }
    }

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 进阶：
     * <p>
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 2 * 104
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     */
    public static class RotateArray {

        public void rotate(int[] nums, int k) {
            int length = nums.length;
            int remainder = k % length;
            if (length == 0 || remainder == 0) {
                return;
            }
            int[] temps = new int[length];
            System.arraycopy(nums, 0, temps, 0, length);
            for (int i = 0; i < length; i++) {
                    nums[(remainder + i) % length] = temps[i];
            }
        }

        public void rotateMax(int[] nums, int k) {
            int hold = nums[0];
            int index = 0;
            int length = nums.length;
            boolean[] visited = new boolean[length];
            for (int i = 0; i < length; i++) {
                index = (index + k) % length;
                if (visited[index]) {
                    index = (index + 1) % length;
                    hold = nums[index];
                    i--;
                } else {
                    visited[index] = true;
                    int temp = nums[index];
                    nums[index] = hold;
                    hold = temp;
                }
            }
        }


        @Test
        public void testRotate() {
            int[] nums = {1, 2, 3, 4, 5, 6, 7};
            rotate(nums, 2);
            System.out.println(Arrays.toString(nums));

        }
    }

}



