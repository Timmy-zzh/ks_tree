package com.timmy.lgsf._01basic._4queue;

import com.timmy.common.ListNode;

/**
 * 使用链表实现队列
 * 头尾节点：head tail
 * 从队尾 入队列
 * 从队头 出队列
 */
public class MyQueue {

    private ListNode head;
    private ListNode tail;
    private int size;

    /**
     * 入队列
     * 新增的节点为队尾最后一个节点
     */
    public void add(int x) {
        ListNode newNode = new ListNode(x);
        if (tail == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * 队头出队
     */
    public int poll() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int value = head.value;
        ListNode next = head.next;
        head.next = null;// 断开链接
        head = next;

        //队列中只有元素，出队列后，尾指针处理
        if (next == null) {
            tail = null;
        }

        size--;
        return value;
    }

    public int peek() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        return head.value;
    }

    public int size() {
        return size;
    }

}
