package com.timmy._00review._03month;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _26删除链表的倒数第N个结点_19 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        _26删除链表的倒数第N个结点_19 demo = new _26删除链表的倒数第N个结点_19();
        PrintUtils.print(head);
        ListNode res = demo.removeNthFromEnd(head, 3);
        System.out.println("---");
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个链表，删除该链表的倒数第n个节点
     * 2。解题思路
     * -要删除倒数第n个节点，需要知道删除节点的前继节点才行
     * --所以现在问题转变为查找删除节点的前继节点
     * -双指针解法：快指针先从后节点移动n个位置
     * -满节点从头节点开始移动，快慢节点之间差距为n个节点
     * -当快节点到达末尾节点时，慢节点既是删除节点的前继节点，进行删除节点操作
     * -返回虚拟头节点
     * 3。边界和细节问题
     * -找到删除节点的前继节点
     * -快慢指针
     * -虚拟头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode slow = dummyHead, fast = dummyHead;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //delete
        slow.next = slow.next.next;

        return dummyHead.next;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     *
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *
     * 提示：
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     *
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     */

}
