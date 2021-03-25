package com.timmy.lgsf._01basic._3stack;

import java.util.Stack;

/**
 * 用栈实现队列：
 * 队列要求先进先出，栈是先进后出
 * 用两个栈实现队列的功能，一个栈入数据，一个栈出数据
 */
public class MyQueue {

    private Stack<Integer> inputStack;
    private Stack<Integer> outputStack;

    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /**
     * 将一个元素放入队列的尾部
     */
    public void add(int val) {
        inputStack.push(val);
    }

    /**
     * 从队列首部移除元素
     */
    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is null");
        }
        if (outputStack.empty()) {
            //将输入栈中元素移动到输出栈中，然后再出队列
            while (!inputStack.empty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    /**
     * 返回队列首部的元素
     */
    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is null");
        }
        if (outputStack.empty()) {
            //将输入栈中元素移动到输出栈中，然后再出队列
            while (!inputStack.empty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    /**
     * 队列是否为空
     */
    public boolean empty() {
        return inputStack.empty() && outputStack.empty();
    }
}
