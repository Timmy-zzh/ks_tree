package com.timmy.lgsf._01basic._4queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 用队列实现栈功能：
 * 队列要求先进先出，栈是先进后出
 * 用两个队列实现：一个队列专门用于出栈（取出队尾元素）
 * 另一个队列用于备份
 * <p>
 * 实现方式二：只用一个队列，判断size出队列，出队列的元素从新入队列到队尾
 */
public class MyStack {

    private Queue<Integer> useQueue;        //使用队列
    private Queue<Integer> backupQueue;     //备份队列

    public MyStack() {
        useQueue = new ArrayDeque<>();
        backupQueue = new ArrayDeque<>();
    }

    /**
     * 入栈
     */
    public void push(int val) {
        useQueue.add(val);
    }

    /**
     * 出栈
     * 1。将操作队列前n-1个元素放入备份队列
     * 2。操作队列最后一个元素出栈
     * 3。将备份队列中所有元素移动到操作队列中
     */
    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Stack is null");
        }
        int size = useQueue.size() - 1;
        while (size > 0) {
            backupQueue.add(useQueue.remove());
            size--;
        }
        Integer popVal = useQueue.remove();

        while (!backupQueue.isEmpty()) {
            useQueue.add(backupQueue.remove());
        }
        return popVal;
    }

    /**
     * 返回栈顶元素
     */
    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("Stack is null");
        }
        int pop = pop();
        push(pop);
        return pop;
    }

    /**
     * 栈是否为空
     */
    public boolean empty() {
        return useQueue.isEmpty() && backupQueue.isEmpty();
    }
}
