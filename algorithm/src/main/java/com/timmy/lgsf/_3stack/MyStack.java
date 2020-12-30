package com.timmy.lgsf._3stack;

/**
 * 栈实现
 * 使用数组作为底层数据结构
 */
public class MyStack<E> {

    Object[] elements = new Object[1000];
    int count = 0;   //栈元素个数
    int index = -1; //栈顶索引

    /**
     * 入栈
     */
    public void push(E e) {
        elements[++index] = e;
        count++;
    }

    /**
     * 栈顶元素出栈，元素移除
     */
    public E pop() {
        E p = peek();
        if (p != null) {   //移除
            elements[index] = null;
            index--;
            count--;
        }
        return p;
    }

    /**
     * 获取栈顶元素，不移除
     *
     * @return
     */
    public E peek() {
        if (index > 0) {
            return (E) elements[index];
        }
        return null;
    }
}
