package com.timmy._review._01stack;

import com.timmy.common.PrintUtils;

import java.util.Stack;

public class _04找出数组中右边比我小的元素 {

    public static void main(String[] args) {
        _04找出数组中右边比我小的元素 demo = new _04找出数组中右边比我小的元素();
//        int[] A = {1, 2, 4, 9, 4, 0, 5};
        int[] A = {5, 2};
        PrintUtils.print(A);
        System.out.println("---");
        int[] res = demo.findRightSmall(A);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个整数数组，遍历每个元素，找到右边元素中第一个比当前元素值小的元素位置
     * 2。模拟运行
     * -暴力解法：是遍历每个元素，然后从当前元素位置开始往后遍历，找到第一个比当前元素值小的元素，这样的话时间复杂度为O(n^2)
     * -递增单调栈解法：
     * --遍历元素，栈为空，则入栈
     * --栈不为空，新元素与栈顶元素进行大小比较，新元素比栈顶元素大，入栈
     * ---否则，栈顶元素出栈，并且该出栈元素找到了右边第一个小的元素位置，并用数组进行记录
     * 3。边界与细节
     * -栈中内容保存的是数组下标，出栈时表示找到右边比元素值小的元素，
     * 4。复杂度分析：
     * -时间：遍历数组中所有元素O（n)
     * -空间：使用栈保存，最坏情况保存所有元素-原先就是升序数组 O(n)
     * 5.总结：
     * -找到数组右边第一个比当前元素小的元素位置
     * -单调递增栈
     */
    public int[] findRightSmall(int[] A) {
        int length = A.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
//            if (stack.isEmpty()) {
//                stack.push(i);
//            } else {
//                while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
//                    Integer pop = stack.pop();
//                    res[pop] = i;
//                }
//                stack.push(i);
//            }
            while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                Integer pop = stack.pop();
                res[pop] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    /**
     * 【题目】一个整数数组 A，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。
     * 输入：[5, 2]
     * 输出：[1, -1]
     *
     * 解释：因为元素 5 的右边离我最近且比我小的位置应该是 A[1]，最后一个元素 2 右边没有比 2 小的元素，所以应该输出 -1。
     */
}
