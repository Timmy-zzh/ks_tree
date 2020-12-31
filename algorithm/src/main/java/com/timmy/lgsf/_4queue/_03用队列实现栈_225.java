package com.timmy.lgsf._4queue;

import java.util.LinkedList;

public class _03用队列实现栈_225 {

    public static void main(String[] args) {

        MyStack stack = new MyStack();

        stack.push(1); // queue is: [1]
        stack.push(2); // queue is: [2,1] (leftmost is front of the queue)
        stack.push(3); // queue is: [2,1] (leftmost is front of the queue)

        System.out.println(stack.top());  // return 2
        System.out.println(stack.pop()); // return 2, queue is [1]
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.empty()); // return false

    }

    /**
     * 使用队列实现栈的下列操作：
     * <p>
     * push(x) -- 元素 x 入栈
     * pop() -- 移除栈顶元素
     * top() -- 获取栈顶元素
     * empty() -- 返回栈是否为空
     * 注意:
     * <p>
     * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
     * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
     * <p>
     * 实现思路：使用队列实现栈
     * 队列使用List
     */
    static class MyStack {

        LinkedList<Integer> list = new LinkedList<>();

        public MyStack() {
        }

        /**
         * 入栈：添加到队列尾部
         *
         * @param x
         */
        public void push(int x) {
            list.addLast(x);
        }

        /**
         * 出栈，需要将队列的队尾元素出队
         * 遍历到队尾元素
         */
        public int pop() {
            int size = list.size();
            while (size > 1) {
                //对头出队 --》 再入队
                Integer integer = list.removeFirst();
                list.addLast(integer);
                size--;
            }
            //最后一个元素，出队列
            return list.removeFirst();
        }

        public int top() {
            int size = list.size();
            while (size > 1) {
                //对头出队 --》 再入队
                Integer integer = list.removeFirst();
                list.addLast(integer);
                size--;
            }
            Integer integer = list.removeFirst();
            list.addLast(integer);
            return integer;
        }

        public boolean empty() {
            return list.isEmpty();
        }
    }
}
