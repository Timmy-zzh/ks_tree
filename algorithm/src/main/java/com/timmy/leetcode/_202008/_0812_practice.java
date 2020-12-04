package com.timmy.leetcode._202008;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法就是找出规律，推导出公式，然后代码实现
 */
public class _0812_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0812_practice practice = new _0812_practice();
////        String[] strings = {"flower", "flow", "flight"};
//        String[] strings = {"dog", "racecar", "car"};
//        String result = practice.longestCommonPrefix(strings);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");

//        System.out.println("-----------------------------------------");
//        _0812_practice practice = new _0812_practice();
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        int[] result = practice.twoSum(nums, target);
//        PrintUtils.print(result);
//        System.out.println("-----------------------------------------");

        System.out.println("-----------------------------------------");
        _0812_practice practice = new _0812_practice();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = practice.threeSum(nums);
        for (List<Integer> integers : result) {
            System.out.println(integers.toString());
        }
//        System.out.println("result:" + result.toString());
        System.out.println("-----------------------------------------");
    }


    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * 思路：1。暴力法
     * 2。转换成求两数之和等于第三个数
     * 要满足不重复的三元组条件，需要先进行排序，然后采用双指针法进行从左和从右两个方向进行遍历
     * 当两个元素只和等于第三个元素时，满足条件
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //1.异常处理
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);  //排序
        if (nums[0] > 0) {
            return result;
        }

        for (int i = 0; i < nums.length - 2; i++) { //第一个基准
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {       //命中
                    List<Integer> items = new ArrayList<>();
                    items.add(nums[i]);
                    items.add(nums[left]);
                    items.add(nums[right]);
                    result.add(items);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 1. 两数之和:暴力法
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 1. 两数之和:两遍哈希表
     * 空间换时间
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && map.get(temp) != i) {
                return new int[]{i, map.get(temp)};
            }
        }
        throw new IllegalArgumentException("no result");
    }

    /**
     * 1. 两数之和:一遍哈希表
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{i, map.get(temp)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no result");
    }

    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * <p>
     * --思路：拿字符数组中的第一个字符串作为基准进行遍历
     * 每次读取 所有字符的第几个，然后判断，当碰到不一样的说明可以停止了
     * 如果出现异常，则直接返回
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String firstStr = strs[0];
        boolean over = false;
        for (int i = 0; i < firstStr.length(); i++) {
            char c = firstStr.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || c != strs[j].charAt(i)) {
                    over = true;
                    break;
                }
            }
            if (over) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
