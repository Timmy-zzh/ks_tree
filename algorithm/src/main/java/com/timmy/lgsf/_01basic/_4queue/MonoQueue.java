package com.timmy.lgsf._01basic._4queue;

import java.util.ArrayDeque;

/**
 * 单调队列
 * 1。底层使用Queue实现
 * 2。入队列与出队列操作
 */
public class MonoQueue {

    private ArrayDeque<Integer> queue;

    public MonoQueue() {
        queue = new ArrayDeque<>();
    }

    /**
     * 从队尾开始遍历，和删除
     */
    public void push(int val) {
        while (!queue.isEmpty() && val > queue.peekLast()) {
            queue.removeLast();
        }
        queue.add(val);
    }

    public void pop(int val) {
        if (!queue.isEmpty() && val == queue.peekFirst()) {
            queue.removeFirst();
        }
    }

    public int fornt() {
        return queue.peekFirst();
    }

    @Override
    public String toString() {
        return "MonoQueue{" +
                "queue=" + queue +
                '}';
    }
}
