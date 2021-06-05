package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

public class _02盛最多水的容器_11 {

    public static void main(String[] args) {
        _02盛最多水的容器_11 demo = new _02盛最多水的容器_11();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        PrintUtils.print(height);
        int res = demo.maxArea(height);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，表示柱子的高度，找出两根柱子，往里面注水，求注水最多的容量大小
     * 2。解题思路：双指针解法
     * -左右指针分别从数组头尾开始移动，获取当前区间范围的容水量，然后移动柱子高度较小的柱子，直至柱子相交
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int resArea = 0;
        int itemArea;
        while (l < r) {
            int minH = Math.min(height[l], height[r]);
            itemArea = minH * (r - l);
            resArea = Math.max(itemArea, resArea);
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return resArea;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器。
     *
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     *
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     *
     * 示例 3：
     * 输入：height = [4,3,2,1,4]
     * 输出：16
     *
     * 示例 4：
     * 输入：height = [1,2,1]
     * 输出：2
     *
     * 提示：
     * n = height.length
     * 2 <= n <= 3 * 104
     * 0 <= height[i] <= 3 * 104
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     */
}
