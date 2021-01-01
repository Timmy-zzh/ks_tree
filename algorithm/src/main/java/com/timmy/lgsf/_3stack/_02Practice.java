package com.timmy.lgsf._3stack;

import java.util.Stack;

public class _02Practice {

    public static void main(String[] args) {
        _02Practice demo = new _02Practice();
//        int[] pushed = {1, 2, 3, 4, 5};
////        int[] popped = {4, 5, 3, 2, 1};
//        int[] popped = {4, 3, 5, 1, 2};
//        boolean result = demo.validateStackSequences(pushed, popped);
//        System.out.println("result:" + result);

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();
        System.out.println("min:"+min);

        minStack.pop();
        int top = minStack.top();
        System.out.println("top:"+top);
        System.out.println("min:"+minStack.getMin());
        
        int a = 10;
        String v = String.valueOf(a);

    }

    /**
     * 判断出栈顺序 是否匹配 入栈顺序？
     * pushed = {1,2,3,4,5} --> popped={4,5,3,2,1} 匹配
     * pushed = {1,2,3,4,5} --> popped={4,3,5,1,2} 不匹配
     * <p>
     * -- 不断入栈，当入栈元素 与 出栈第一个元素相等时，说明需要出栈
     * -- 如果入栈元素 与 出栈数组相等，则不断入栈数据结构不断出栈，
     * -- 不匹配，则入栈队列不断入栈
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int popIndex = 0;
        Stack<Integer> pushStack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            int pushV = pushed[i];
            pushStack.push(pushV);

            while (!pushStack.isEmpty() && pushStack.peek() == popped[popIndex]) {
                pushStack.pop();
                popIndex++;
            }
        }
        return pushStack.isEmpty();
    }

}
