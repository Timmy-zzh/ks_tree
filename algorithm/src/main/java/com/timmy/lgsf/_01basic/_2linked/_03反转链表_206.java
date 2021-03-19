package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _03反转链表_206 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        PrintUtils.print(listNode1);
        System.out.println("--------------");
        _03反转链表_206 demo = new _03反转链表_206();
        ListNode res = demo.reverseList(listNode1);
        PrintUtils.print(res);
    }

    /**
     * 1。链表反转
     * 2。解题思路：画图
     * - next = curr.next; 指针保存下一次循环需要跳转的位置
     * - curr.next = pre;  指针翻转，指向前继节点
     * - pre = curr;       前继节点后移
     * - curr = next;      赋值，跳转到下一个节点进行比较
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, next = null;
        ListNode curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /***
     * 反转一个单链表。
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     */
}
