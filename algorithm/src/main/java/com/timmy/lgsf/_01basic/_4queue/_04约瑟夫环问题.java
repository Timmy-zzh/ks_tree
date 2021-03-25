package com.timmy.lgsf._01basic._4queue;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;

public class _04约瑟夫环问题 {

    public static void main(String[] args) {
        ring(10, 5, 3);
    }

    /**
     * 约瑟夫环问题：
     * 一共有10个人，从第3个人开始数数，从1开始数，数到数字5的时候，此人离开，后面的人又从1开始数数
     * 解题思路：
     * 1。使用LinkedList保持所有的元素，因为LinkedList是循环链表，从队尾到对头顺序是 10 ～ 1
     * 2。从第二个人开始从第1位数字计数，这步可以使用队头出队列，然后该元素再入队列尾部
     * 3。然后开始从1开始计数，每次计数都出队列然后队尾入队列，到计数为5时就不入队列了，该元素从队列中删除了
     */
    private static void ring(int m, int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        PrintUtils.print(list);

        for (int i = 1; i < k; i++) {
            Integer poll = list.pop();
            list.add(poll);
        }
        PrintUtils.print(list);

        int index = 1;
        while (!list.isEmpty()) {
            if (index == 5) {
                Integer poll = list.poll();
                index = 1;
                System.out.println("--出队列：" + poll);
//                PrintUtils.print(list);
            } else {
                Integer poll = list.poll();
                list.add(poll);
                index++;
            }
        }
    }


}
