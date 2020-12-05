package com.timmy.dmsxl._05stack;

import com.timmy.common.PrintUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class _02InversePolish {

    public static void main(String[] args) {
        _02InversePolish demo = new _02InversePolish();
//        int result = demo.evalRPN(new String[]{"2", "1", "+", "3", "*"});
//        int result = demo.evalRPN(new String[]{"4", "13", "5", "/", "+"});
//        System.out.println("result:" + result);

//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] result = demo.maxSlidingWindow(nums, 3);
//        int[] nums = {1, -1};
//        int[] result = demo.maxSlidingWindow(nums, 1);
//        int[] nums = {1, 3, 1, 2, 0, 5};
//        int[] result = demo.maxSlidingWindow(nums, 3);
//        PrintUtils.print(result);

        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] result = demo.topKFrequent(nums, 2);
        PrintUtils.print(result);
    }

    /**
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的运算符包括 + ,  - ,  *  ,  / 。每个运算对象可以是整数，
     * 也可以是另一个逆波兰表达式。
     * 说明：
     * <p>
     * 整数除法只保留整数部分。给定逆波兰表达式总是有效的。换句话说，
     * 表达式总会得出有效数值且不存在除数为 0 的情况。
     * <p>
     * 示例 1：
     * 输入: ["2", "1", "+", "3", " * "]
     * 输出: 9
     * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * <p>
     * 解题思路：
     * 1。遍历字符数组，使用栈保存
     * 2。如果是数字则入栈，如果遇到运算符则从栈中取栈顶两个元素，计算后，结果再入栈
     * 3。全部遍历完取出栈内的元素就是计算机计算的结果
     */
//    public int evalRPN(String[] tokens) {
//        Stack<String> stack = new Stack<>();
//        for (int i = 0; i < tokens.length; i++) {
//            String token = tokens[i];
//            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
//                String pop1 = stack.pop();
//                String pop2 = stack.pop();
//                if ("+".equals(token)) {
//                    int result = Integer.valueOf(pop1) + Integer.valueOf(pop2);
//                    stack.push(String.valueOf(result));
//                } else if ("-".equals(token)) {
//                    int result = Integer.valueOf(pop2) - Integer.valueOf(pop1);
//                    stack.push(String.valueOf(result));
//                } else if ("*".equals(token)) {
//                    int result = Integer.valueOf(pop1) * Integer.valueOf(pop2);
//                    stack.push(String.valueOf(result));
//                } else {
//                    int result = Integer.valueOf(pop2) / Integer.valueOf(pop1);
//                    stack.push(String.valueOf(result));
//                }
//            } else {
//                stack.push(token);
//            }
//        }
//        String pop = stack.pop();
//        return Integer.valueOf(pop);
//    }

    /**
     * 239. 滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * <p>
     * 进阶：
     * 你能在线性时间复杂度内解决此题吗？
     * <p>
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7      3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * 解题思路：
     * 1。创建单调队列，元素大小从大到小，实现入队列与出队列的逻辑
     * 2。每次取队列都是取队头的最大值元素
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonoQueue monoQueue = new MonoQueue();
        int[] result = new int[nums.length - k + 1];
        int h = 0;

        //1.先将前k个元素入单调队列
        for (int i = 0; i < k; i++) {
            monoQueue.push(nums[i]);
        }
        result[h++] = monoQueue.fornt();

        //2.遍历k后面的元素，出前面的队列,入单调队列，取数据，
        for (int i = k; i < nums.length; i++) {
            monoQueue.pop(nums[i - k]);
            monoQueue.push(nums[i]);
            result[h++] = monoQueue.fornt();
        }
        return result;
    }

    /**
     * 347. 前 K 个高频元素
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * <p>
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * <p>
     * 解题思路：
     * 1。找出每个数字出现的频率：使用map保存
     * 2。使用数字的频率，map.getValue()进行比较，取前k高的值，使用小顶堆保存
     * 3。根据vlaue找到key值然后返回
     *
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        //小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> t1, Map.Entry<Integer, Integer> t2) {
                return  t1.getValue() > t2.getValue() ? 1 : -1;
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            System.out.println("key:" + entry.getKey() + " ,value:" + value);
            priorityQueue.add(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int size = priorityQueue.size();
        int h = 0;
        for (int i = 0; i < size; i++) {
            Map.Entry<Integer, Integer> entry = priorityQueue.poll();
            System.out.println("priorityQueue key:" + entry.getKey() + " ,value:" + entry.getValue());
            result[h++] = entry.getKey();
        }
        return result;
    }
}
