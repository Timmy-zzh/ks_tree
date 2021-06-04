package com.timmy._review._09double_point;

import com.timmy.common.PrintUtils;

public class _05数组中的最长山脉_845 {

    public static void main(String[] args) {
        _05数组中的最长山脉_845 demo = new _05数组中的最长山脉_845();
//        int[] arr = {2, 1, 4, 7, 3, 2, 5};
        int[] arr = {2, 2, 2};
        PrintUtils.print(arr);
        int res = demo.longestMountain(arr);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个表示山高度的数组，要求山脉的最大长度
     * 2。解题思路
     * -先计算每个高度的趋势（上升1，山峰2，下降-1，山谷-2，平原0）
     * 3。总结：
     * -区间范围内要求存在山峰
     * <p>
     * todo :error
     */
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            getHeight(heights, arr, i);
        }
        PrintUtils.print(heights);

        int left = 0;
        boolean first = false;  //是否找到第一个山谷
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == -2 && !first) {   //第一个山谷
                first = true;
                left = i;
            }

            if (heights[i] == -2 && first) {    // 下一个山谷
                res = Math.max(res, i - left + 1);
                left = i;
            }
        }

        return res;
    }

    private void getHeight(int[] heights, int[] arr, int i) {
        if (i == 0) {
            if (arr[i] < arr[i + 1]) {
                heights[i] = 1;
            } else if (arr[i] > arr[i + 1]) {
                heights[i] = -1;
            }
            return;
        }
        if (i == arr.length - 1) {
            if (arr[i - 1] < arr[i]) {
                heights[i] = 1;
            } else if (arr[i - 1] > arr[i]) {
                heights[i] = -1;
            }
            return;
        }

        if (arr[i - 1] < arr[i]) {
            if (arr[i] < arr[i + 1]) {
                heights[i] = 1;
            } else if (arr[i] > arr[i + 1]) {
                heights[i] = 2;  //山峰
            }
        }
        if (arr[i - 1] > arr[i]) {
            if (arr[i] > arr[i + 1]) {
                heights[i] = -1;
            } else if (arr[i] < arr[i + 1]) {
                heights[i] = -2;//山谷
            }
        }
    }

    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     * 如果不含有 “山脉” 则返回 0。
     *  
     * 示例 1：
     * 输入：[2,1,4,7,3,2,5]
     * 输出：5
     * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
     *
     * 示例 2：
     * 输入：[2,2,2]
     * 输出：0
     * 解释：不含 “山脉”。
     *  
     * 提示：
     * 0 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
     */
}
