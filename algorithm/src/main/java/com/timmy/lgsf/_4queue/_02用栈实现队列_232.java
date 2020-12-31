package com.timmy.lgsf._4queue;

import java.util.Stack;

public class _02用栈实现队列_232 {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)

        System.out.println(myQueue.peek());  // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
//        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false
    }

    /**
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
     * <p>
     * 实现 MyQueue 类：
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * <p>
     * 输入：
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 1, 1, false]
     * <p>
     * 解释：
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     * <p>
     * 解题思路：
     * 使用两个栈实现队列
     * 1。一个栈用于入队列，一个栈用于出队列
     */
    static class MyQueue {

        Stack<Integer> inputStack = new Stack<>();
        Stack<Integer> outputStack = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            inputStack.add(x);
        }

        public int pop() {
            if (!outputStack.isEmpty()) {
                return outputStack.pop();
            }
            //将 输入栈中的元素，压栈到输出栈
            while (!inputStack.isEmpty()) {
                outputStack.add(inputStack.pop());
            }
            return outputStack.pop();
        }

        public int peek() {
            if (!outputStack.isEmpty()) {
                return outputStack.peek();
            }
            //将 输入栈中的元素，压栈到输出栈
            while (!inputStack.isEmpty()) {
                outputStack.add(inputStack.pop());
            }
            return outputStack.peek();
        }

        public boolean empty() {
            return outputStack.isEmpty() && inputStack.isEmpty();
        }
    }
}
