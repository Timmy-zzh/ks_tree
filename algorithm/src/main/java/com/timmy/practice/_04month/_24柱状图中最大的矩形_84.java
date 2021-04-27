package com.timmy.practice._04month;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.Stack;

public class _24柱状图中最大的矩形_84 {

    public static void main(String[] args) {
        _24柱状图中最大的矩形_84 demo = new _24柱状图中最大的矩形_84();
        int[] heights = {2, 1, 5, 6, 2, 3};
        int res = demo.largestRectangleArea(heights);
        System.out.println("res:" + res);
    }

    /**
     * 使用一个for循环，进行左右区间的查找
     */
    public int largestRectangleArea(int[] heights) {
        PrintUtils.print(heights);
        int N = heights.length;
        int[] left = new int[N];
        int[] right = new int[N];
        Arrays.fill(right, N);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            //单调递增栈，新入栈元素比栈顶元素小的，就需要连续出栈
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            //因为是递增栈，每一个新添加的元素，栈顶左侧元素比新入栈元素小
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
            System.out.println("i:" + i + " ,res:" + (right[i] - left[i] - 1));
        }
        return res;
    }

    /**
     * 1。
     * 2.解题思路：单调栈
     * -遍历数组中每根柱子的高度，求以当前柱子的高度为矩形的高度，进行向左向右延展，直到比当前高度低的柱子为止
     * -使用数组int[] right 保存每个元素的右边比当前元素高度低的第一个元素位置，同样左边同理
     * --通过递增栈数据结构保存柱子的高度
     */
    public int largestRectangleArea_v2(int[] heights) {
        PrintUtils.print(heights);
        int N = heights.length;
        int[] left = new int[N];
        int[] right = new int[N];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            //判断当前遍历高度，与栈顶元素比较大小，如果比栈顶大的-入栈（单调递增），如果小-出栈（while）
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                Integer pop = stack.pop();
                right[pop] = i;
            }
            stack.push(i);
        }
        // 在栈中留下来的是递增的，说明该位置右边的元素都比当前元素值大
        while (!stack.isEmpty()) {
            right[stack.pop()] = -1;
        }
        PrintUtils.print(right);

        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            //找左边元素比当前值小的元素
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        PrintUtils.print(left);

        int res = 0;
        int rightIndex;
        for (int i = 0; i < N; i++) {
            rightIndex = right[i] == -1 ? N : right[i];
            res = Math.max(res, heights[i] * (rightIndex - left[i] - 1));
            System.out.println("i:" + i + " ,res:" + heights[i] * (rightIndex - left[i] - 1));
        }

        return res;
    }

    /**
     * 1.理解题意
     * -输入一个整数数组，数组中的元素表示柱子的高度，这些柱子可以组成矩形，求矩形的最大面积
     * 2。解题思路
     * 暴力解法：遍历数组元素，从i开始，到j（j>=i） 每层循环计算矩形的面积
     * -矩形的大小是区域内柱子最低高度 * 乘以区域大小
     */
    public int largestRectangleArea_v1(int[] heights) {
        PrintUtils.print(heights);
        int res = 0;
        int minHeight = 0;
        for (int i = 0; i < heights.length; i++) {
            minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                //求柱子的最低高度，乘以区域(j-i+1)
                if (minHeight > heights[j]) {
                    minHeight = heights[j];
                }
                int area = minHeight * (j - i + 1);
                System.out.println("i:" + i + " ,j:" + j + " ,area:" + area);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     * 示例:
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     *
     * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
     */
}
