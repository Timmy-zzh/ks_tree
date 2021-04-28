package com.timmy._review._01stack;

import com.timmy.common.PrintUtils;

import java.util.Stack;

public class _05字典序最小的k个数的子序列 {

    public static void main(String[] args) {
        _05字典序最小的k个数的子序列 demo = new _05字典序最小的k个数的子序列();
        int[] A = {3, 5, 2, 6};
        PrintUtils.print(A);
        int[] res = demo.findSmallSeq(A, 2);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个正整数数组，依次取出k个数，这k个数组成一个数组，并且数组的字典序最小
     * -字典序最小，表示需要找到排序更小的数字
     * 2。模拟运行
     * 暴力法：遍历获取所有的数组子序列（长度为k），然后比较所有子序列的长度，找到子序列最小的子序列
     * 单调栈解法：
     * -因为要求的子序列在数组中的位置也是有序的，并且所求子序列要最小，所以可以将问题拆解为求右边元素值更小的元素
     * -子序列要求长度为k，所以在出栈的时候要注意栈中元素不能小于k
     * 3。边界与细节问题
     * -单调递增栈，子序列个数为k
     * 4.复杂度分析
     * -时间：每个元素都要遍历O(n)
     * -空间：使用栈保存数组元素O(n)
     * 5.总结：
     * -抓住问题的几个关键点：子序列，子序列长度为k，最小子序列
     * -单调递增栈，小数消除大数
     * -核心是出栈操作
     */
    private int[] findSmallSeq(int[] A, int k) {
        int[] res = new int[k];
        int length = A.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && stack.peek() > A[i] && (stack.size() + length - i >= k)) {
                stack.pop();
            }
            stack.push(A[i]);
        }
        if (stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
    /**
     * 【题目】给定一个正整数数组和 k，要求依次取出 k 个数，输出其中数组的一个子序列，需要满足：1. 长度为 k；2.字典序最小。
     *
     * 输入：nums = [3,5,2,6], k = 2
     * 输出：[2,6]
     *
     * 解释：在所有可能的解：{[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 字典序最小。
     *
     * 所谓字典序就是，给定两个数组：x = [x1,x2,x3,x4]，y = [y1,y2,y3,y4]，
     * 如果 0 ≤ p < i，xp == yp 且 xi < yi，那么我们认为 x 的字典序小于 y。
     */
}
