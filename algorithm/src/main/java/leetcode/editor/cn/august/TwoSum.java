package leetcode.editor.cn.august;

import leetcode.editor.cn.utils.ExecutionTime;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * @author xuweizhi
 * @since 2020/08/06 14:52
 */
public class TwoSum {

    int target = 50000000;
    int[] nums = new int[target];

    @Before
    public void init() {
        File file = new File("a.txt");
        //if (!file.exists()) {
        Random random = new Random(1);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(target);
        }
        //NioUtil.write(nums, "a.txt");
        //} else {
        //    String read = NioUtil.read("a.txt");
        //    String substring = read.substring(1, read.length() - 1);
        //    String[] split = substring.split(",");
        //    for (int i = 0; i < nums.length; i++) {
        //        nums[i] = Integer.parseInt(split[i]);
        //    }
        //}

    }

    @Test
    public void test() {
        long result1 = 0l;
        long result2 = 0l;
        long result3 = 0l;
        int index = 10;
        int[] results = new int[index];
        Random random = new Random(1);
        for (int i = 0; i < index; i++) {
            results[i] = random.nextInt(target);
        }
        for (int i = 0; i < index; i++) {
            target = results[i];
            result1 += ExecutionTime.executionTime(this::twoSum);
            result2 += ExecutionTime.executionTime(this::violence);
            //result3 += ExecutionTime.executionTime(() -> twoSum(nums, target));
        }
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    private Object violence() {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    //System.out.println("" + i + " " + j);
                    //System.out.println(nums[i] + " + " + nums[j] + " = " + target);
                    return null;
                }
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                //System.out.println(i);
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum() {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
