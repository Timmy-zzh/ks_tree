package com.timmy._00review._03month;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _22多数元素_169 {

    public static void main(String[] args) {
        _22多数元素_169 demo = new _22多数元素_169();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int res = demo.magorityElement(nums);
        System.out.println("res:" + res);
    }

    public int magorityElement(int[] nums) {
        Arrays.sort(nums);
        PrintUtils.print(nums);
        return nums[nums.length / 2];
    }

    /**
     * 1.查找多数元素，（在数组中出现次数大于数组大小的一半）
     * 2。解题思路
     * -hashmap == 记录每个元素出现的次数，然后找出次数最大的对应元素
     * -先排序，排序后在位置 n/2 的下标元素一定是所求结果
     *
     * @param nums
     * @return
     */
    public int magorityElement_v1(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else {
                hashMap.put(nums[i], 1);
            }
        }
        System.out.println(hashMap.toString());

        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     * 输入：[3,2,3]
     * 输出：3
     *
     * 示例 2：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     * 进阶：
     * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     *
     * 链接：https://leetcode-cn.com/problems/majority-element
     */
}
