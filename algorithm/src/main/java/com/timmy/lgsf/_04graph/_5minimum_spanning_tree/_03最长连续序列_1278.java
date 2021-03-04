package com.timmy.lgsf._04graph._5minimum_spanning_tree;

import java.util.HashSet;

public class _03最长连续序列_1278 {
    public static void main(String[] args) {
        _03最长连续序列_1278 demo = new _03最长连续序列_1278();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = demo.longestConsecutive(nums);
        System.out.println("result:" + result);
    }

    /**
     * 1.解题思路
     * -输入一个未排序的数据，里面的数字存在连续增加的元素，求数字连续的最长序列
     * 2.解题思路1
     * -先排序，然后从第一位元素开始遍历，如果后面元素i+1比前面元素多1，则用一个标记记录下当前连续的次数，依次类推
     * --时间复杂度为O（nlogn）
     * 2.解题思路2
     * -可以对数组元素中的数字进行分区间，每个区间的数字连续升序，
     * --然后找到区间中最小的那个，然后遍历递增的元素，并记录连续次数
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (!hashSet.contains(num - 1)) {  //不包含，则num是区间的最小值
                int currNum = num;
                int currRes = 1;
                while (hashSet.contains(currNum + 1)) {
                    currRes++;
                    currNum = currNum + 1;
                }
                res = Math.max(res, currRes);
            }
        }
        return res;
    }
    /**
     *给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     *
     * 示例 1：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * 示例 2：
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
     */
}
