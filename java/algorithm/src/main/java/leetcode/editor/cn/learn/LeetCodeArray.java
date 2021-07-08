package leetcode.editor.cn.learn;

import org.junit.Test;

import java.util.*;

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

    /**
     * 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     * <p>
     * 示例 1:
     * 输入: [1,2,3,1]
     * 输出: true
     * <p>
     * 示例 2:
     * 输入: [1,2,3,4]
     * 输出: false
     * <p>
     * 示例 3:
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     */
    @SuppressWarnings("all")
    public static class RepeatArray {


        public boolean containsDuplicate(int[] nums) {
            int length = nums.length;
            if (length == 0) {
                return false;
            }
            int nodeLength = length / 8 + 1;
            Node[] nodes = new Node[nodeLength];
            for (int value : nums) {
                int index = value % nodeLength;
                if (add(nodes, index < 0 ? -index : index, value)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 先排序
         * 时间复杂度：O(N\log N)O(NlogN)，其中 NN 为数组的长度。需要对数组进行排序。
         * 空间复杂度：O(\log N)O(logN)，其中 NN 为数组的长度。注意我们在这里应当考虑递归调用栈的深度。
         */
        public boolean containsDuplicate1(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 复杂度分析
         * 时间复杂度：O(N)O(N)，其中 NN 为数组的长度。
         * 空间复杂度：O(N)O(N)，其中 NN 为数组的长度。
         */
        public boolean containsDuplicate2(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int x : nums) {
                if (!set.add(x)) {
                    return true;
                }
            }
            return false;
        }


        public boolean add(Node[] nodes, int index, int data) {
            Node node = nodes[index];
            if (node == null) {
                nodes[index] = new Node(data);
                return false;
            }
            Node result = node.addNode(new Node(data));
            if (result != null) {
                nodes[index] = result;
                return false;
            }
            return true;
        }

        @Test
        public void isContainsDuplicate() {
            for (int j = 1; j < 100; j++) {
                int[] ints = new int[j];
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = i;
                }
                System.out.println(containsDuplicate(ints));
            }

        }
    }

    public static class Node {

        private final int data;

        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node addNode(Node node) {
            Node temp = this;
            do {
                if (node.data == temp.data) {
                    return null;
                }
            } while ((temp = temp.next) != null);
            node.next = this;
            return node;

        }
    }

    /**
     * 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     * <p>
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public static class OnceNumber {
        /**
         * 异或解决问题
         * 1. a 与 0 异或： a^0 = a;
         * 2. a 与 a 异或： a^a = 0;
         * 3. 异或满足交换律： a ^ b ^ a = b ^ (a ^ a)= b ^ 0 = b
         */
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int num : nums) {
                result ^= num;
            }
            return result;
        }

        @Test
        public void testSingleNumber() {
            int[] nums = {1, 2, 3, 4, 1, 2, 3, 4, 5};
            System.out.println(singleNumber(nums));
        }
    }

    /**
     * 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * <p>
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * <p>
     * 说明：
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     * <p>
     * 进阶：
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     */
    public static class IntersectionArrays {

        /**
         * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
         * <p>
         * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则
         * 将该数字添加到答案，并减少哈希表中该数字出现的次数。
         * <p>
         * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
         * <p>
         * 复杂度分析
         * <p>
         * 时间复杂度：O(m+n)O(m+n)，其中 mm 和 nn 分别是两个数组的长度。需要遍历两个数组并对哈希表进行操作，哈希表操作的时间复杂度是 O(1)O(1)，因此总时间复杂度与两
         * 个数组的长度和呈线性关系。
         * <p>
         * 空间复杂度：O(min(m,n))O(min(m,n))，其中 mm 和 nn 分别是两个数组的长度。对较短的数组进行哈希表的操作，哈希表的大小不会超过较短的数组的长度。为返回值创建
         * 一个数组 intersection，其长度为较短的数组的长度。
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int value : nums1) {
                int count = map.getOrDefault(value, 0) + 1;
                map.put(value, count);
            }
            int[] intersects = new int[nums1.length];
            int index = 0;
            for (int value : nums2) {
                Integer count = map.get(value);
                if (count != null) {
                    intersects[index++] = value;
                    if (count > 1) {
                        map.put(value, --count);
                    } else {
                        map.remove(value);
                    }
                }
            }
            return Arrays.copyOfRange(intersects, 0, index);
        }

        /**
         * 排序 + 双指针
         * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
         * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
         * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右移
         * 一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
         * <p>
         * 时间复杂度：O(m log m+n log n)O(mlogm+nlogn)，其中 mm 和 nn 分别是两个数组的长度。对两个数组进行排序的时间复杂度是 O(m log m+n log n)O(mlogm+nlogn)，
         * 遍历两个数组的时间复杂度是 O(m+n)O(m+n)，因此总时间复杂度是 O(m log m+n log n)O(mlogm+nlogn)。
         * <p>
         * 空间复杂度：O(min(m,n))O(min(m,n))，其中 mm 和 nn 分别是两个数组的长度。为返回值创建一个数组 intersection，其长度为较短的数组的长度。不过在 C++ 中，我们
         * 可以直接创建一个 vector，不需要把答案临时存放在一个额外的数组中，所以这种实现的空间复杂度为 O(1)O(1)。
         * <p>
         * 如果 \textit{nums}_2nums2的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。那么就无法高效地对 \textit{nums}_2nums2进行排序，因此
         * 推荐使用方法一而不是方法二。在方法一中，\textit{nums}_2nums2只关系到查询操作，因此每次读取 \textit{nums}_2nums中的一部分数据，并进行处理即可
         */
        public int[] intersect2(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int l1 = nums1.length, l2 = nums2.length;
            int[] intersects = new int[Math.min(l1, l2)];
            int index1 = 0, index2 = 0, index = 0;
            while (index1 < l1 && index2 < l2) {
                if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else if (nums1[index1] > nums2[index2]) {
                    index2++;
                } else {
                    intersects[index++] = nums1[index1++];
                    index2++;
                }
            }
            return Arrays.copyOfRange(intersects, 0, index);
        }
    }

    /**
     * 加一
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * <p>
     * 示例 2：
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * <p>
     * 示例 3：
     * 输入：digits = [0]
     * 输出：[1]
     */
    public static class NumberPlusOne {

        public int[] plusOne(int[] digits) {
            int len = digits.length;
            for (int i = len - 1; i >= 0; i--) {
                digits[i]++;
                int temp = digits[i] % 10;
                digits[i] = temp;
                if (temp != 0) {
                    return digits;
                }
            }
            digits = new int[len + 1];
            digits[0] = 1;
            return digits;
        }
    }

    /**
     * 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public static class MoveZero {
        public void moveZeros(int[] nums) {
            int index = 0, len = nums.length;
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    continue;
                }
                nums[index++] = nums[i];
            }
            for (int i = len - 1; i >= index; i--) {
                nums[i] = 0;
            }
        }

        @Test
        public void moveZeroTest() {
            int[] nums = {0, 1, 0, 3, 12};
            moveZeros(nums);
            for (int num : nums) {
                System.out.println(num);
            }
        }
    }


    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * <p>
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * <p>
     * <p>
     * 提示：
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     */
    public static class AddTwoNumber {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> result = new HashMap<>();
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                if (result.containsKey(target - nums[i])) {
                    return new int[]{result.get(target - nums[i]), i};
                }
                result.put(nums[i], i);
            }
            return new int[0];
        }
    }

    /**
     * 有效的数独
     * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * 注意：
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     */
    public static class Sudoku {

        /**
         * 利用循环填充数据，来判断是否为数独
         *
         * @param board 数据源
         * @return 返回值
         */
        public boolean isValidSudoku(char[][] board) {
            int len = 9;
            boolean[][] row = new boolean[len][len];
            boolean[][] col = new boolean[len][len];
            boolean[][] checkered = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    int num = board[i][j] - '1';
                    int index = (i / 3) * 3 + j / 3;
                    if (row[i][num] || col[j][num] || checkered[index][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        checkered[index][num] = true;
                    }
                }
            }
            return true;
        }
    }

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */
    public static class RotateExample {

        public void rotate(int[][] matrix) {
            int cows = matrix.length;
            int cowNumber = cows / 2;

            for (int i = 0; i < cowNumber; i++) {
                int end = cows - i * 2;
                for (int j = 0; j < end - 1; j++) {
                    int x1 = matrix[i][i + j];
                    int x2 = matrix[i + j][cows - i - 1];
                    int x3 = matrix[cows - i - 1][cows - j - i - 1];
                    int x4 = matrix[cows - i - j - 1][i];
                    matrix[i][i + j] = x4;
                    matrix[i + j][i + end - 1] = x1;
                    matrix[cows - i - 1][cows - j - i - 1] = x2;
                    matrix[cows - i - j - 1][i] = x3;
                }
            }
        }

        public static void main(String[] args) {
            int[][] nums = {
                    {5, 1, 9, 11},
                    {2, 4, 8, 10},
                    {13, 3, 6, 7},
                    {15, 14, 12, 16}
            };
            RotateExample rotateExample = new RotateExample();
            rotateExample.rotate(nums);
            for (int[] num : nums) {
                for (int i : num) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }

}



