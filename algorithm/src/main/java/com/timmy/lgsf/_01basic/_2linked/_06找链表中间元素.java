package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _06找链表中间元素 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        PrintUtils.print(listNode1);
        ListNode result = findMidNode(listNode1);
        System.out.println(result.val);
    }

    /**
     * 给定一个奇数的链表，查找出这个链表中间位置的节点的数值
     * 解题思路：快慢指针
     */
    private static ListNode findMidNode(ListNode header) {
        ListNode fast = header;
        ListNode slow = header;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
