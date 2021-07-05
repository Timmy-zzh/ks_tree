package com.timmy.practice._07month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _05最长子数组长度 {

    public static void main(String[] args) {
        _05最长子数组长度 demo = new _05最长子数组长度();
//        int[] nums = {2, 3, 4, 5};
        int[] nums = {2, 2, 3, 4, 8, 99, 3};
//        int[] nums = {2, 2, 3, 4, 3};
//        int[] nums = {1, 2, 3, 1, 2, 3, 2, 2};
        PrintUtils.print(nums);
        int res = demo.solve(nums);
        System.out.println("res:" + res);
    }

    int ans = 0;

    public int solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        List<Integer> box = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            solveR(arr, box, i);
        }
        return ans;
    }

    private void solveR(int[] arr, List<Integer> box, int index) {
        if (index >= arr.length) {
            if (index == arr.length) {
                ans = Math.max(ans, box.size());
            }
            return;
        }
        if (box.contains(arr[index])) {
            ans = Math.max(ans, box.size());
            return;
        }
        box.add(arr[index]);
        solveR(arr, box, index + 1);
        box.remove(box.size() - 1);
    }

    /**
     * 描述
     * 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     * 示例1
     * 输入：
     * [2,3,4,5]
     * 复制
     * 返回值：
     * 4
     * 复制
     * 说明：
     * [2,3,4,5]是最长子数组
     * 示例2
     * 输入：
     * [2,2,3,4,3]
     * 复制
     * 返回值：
     * 3
     * 复制
     * 说明：
     * [2,3,4]是最长子数组
     * 示例3
     * 输入：
     * [9]
     * 复制
     * 返回值：
     * 1
     * 复制
     * 示例4
     * 输入：
     * [1,2,3,1,2,3,2,2]
     * 复制
     * 返回值：
     * 3
     * 复制
     * 说明：
     * 最长子数组为[1,2,3]
     * 示例5
     * 输入：
     * [2,2,3,4,8,99,3]
     * 复制
     * 返回值：
     * 5
     * 复制
     * 说明：
     * 最长子数组为[2,3,4,8,99]
     */
}
