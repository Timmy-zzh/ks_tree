package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

public class _03接雨水_42 {

    public static void main(String[] args) {
        _03接雨水_42 demo = new _03接雨水_42();
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = {4, 2, 3};
        PrintUtils.print(height);
        int res = demo.trap(height);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组表示柱子的高度，现在下雨，雨水会落入柱子的凹槽之中，求能接住多少雨水，也就是求凹槽的面积
     * 2。解题思路
     * 双指针解法：
     * -不断遍历数组，遍历元素为区间右指针，左指针默认在数组左侧，当区间右指针大于等于左指针柱子高度时
     * --说明找到可以装水的凹槽，先求左右指针的柱子最小高度，然后求整个区间的雨水面积，
     * -当右指针遍历到数组最右侧时，则不断往右移动左指针，并求移动区域的凹槽
     */
    public int trap(int[] height) {
        int n = height.length;
        int l = 0, r = 1;
        int sumArea = 0;

        while (l < n || r < n) {
            if (r < n) {
                //右指针不断右移，直到大于等于左指针
                if (height[r] >= height[l]) {
                    System.out.println("l:" + l + " ,r:" + r);
                    //求区域内凹槽雨水面积
                    int minH = Math.min(height[l], height[r]);
                    for (int i = l + 1; i < r; i++) {
                        sumArea += minH - height[i];
                    }
                    l = r;
                }
                r++;
            } else {
                //遇到右指针移动到数组最右侧，都没找到比区间左指针柱子高的元素，需要从右往左移动
                int tempL = l;
                r--;
                l = r - 1;
                while (l >= tempL) {
                    if (height[l] >= height[r]) {
                        System.out.println("---l:" + l + " ,r:" + r);
                        //求区域内凹槽雨水面积
                        int minH = Math.min(height[l], height[r]);
                        for (int i = l + 1; i < r; i++) {
                            sumArea += minH - height[i];
                        }
                        r = l;
                    }
                    l--;
                }
                break;
            }
        }

        return sumArea;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     *
     * 示例 2：
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     * 提示：
     * n == height.length
     * 0 <= n <= 3 * 104
     * 0 <= height[i] <= 105
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     */
}
