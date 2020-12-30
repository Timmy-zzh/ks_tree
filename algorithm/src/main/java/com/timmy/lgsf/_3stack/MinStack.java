package com.timmy.lgsf._3stack;


import com.timmy.common.ListNode;

/**
 * 栈使用链表作为底层数据结构实现
 * 栈顶元素 一直是链表头（空链表节点）
 */
public class MinStack {

    ListNode head;   //栈顶元素 -null
    int count;

    public MinStack() {
        head = new ListNode(0);
        count = 0;
    }

    public void push(int x) {
        ListNode newNode = new ListNode(x);
        if (head.next != null) {
            newNode.next = head.next;
        }
        head.next = newNode;
    }

    public int pop() {
        int e = top();
        if (e != Integer.MIN_VALUE) {//移除
            head.next = head.next.next;
        }
        return e;
    }

    public int top() {
        if (head.next == null) {
            return Integer.MIN_VALUE;
        }
        return head.next.value;
    }

    //遍历，找到最小的数值
    public int getMin() {
        int min = Integer.MAX_VALUE;
        ListNode node = head.next;
        while (node != null) {
            if (node.value < min) {
                min = node.value;
            }
            node = node.next;
        }
        return min;
    }

}
