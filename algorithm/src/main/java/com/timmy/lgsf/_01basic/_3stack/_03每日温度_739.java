package com.timmy.lgsf._01basic._3stack;

import com.timmy.common.PrintUtils;

import java.util.Stack;

public class _03每日温度_739 {

    public static void main(String[] args) {
        _03每日温度_739 demo = new _03每日温度_739();
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = demo.dailyTemperatures(temps);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个每日气温数组，每个元素表示当前的气温，输出一个数组，输出数组的元素为以当天气温为基准，需要过几天才有更高温度
     * 2。解题思路
     * -遍历温度数组，使用栈数据结构保存前面元素的**下标(核心)**，遍历过程中判断当前遍历的元素与栈顶元素比较
     * --如果大于栈顶元素，说明观测到更高温度，计算经过了几天时间，出栈，并赋值
     * --如果小于栈顶元素，则入栈
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            while (!stack.isEmpty() && temp > T[stack.peek()]) {
                Integer topIndex = stack.peek();
                res[topIndex] = i - topIndex;
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * 链接：https://leetcode-cn.com/problems/daily-temperatures
     */
}
