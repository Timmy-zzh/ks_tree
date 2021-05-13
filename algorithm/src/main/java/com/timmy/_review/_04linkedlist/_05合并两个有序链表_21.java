package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _05合并两个有序链表_21 {

    public static void main(String[] args) {
        _05合并两个有序链表_21 demo = new _05合并两个有序链表_21();
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode head = new ListNode(1);
        node2.next = node3;
        head.next = node2;
        PrintUtils.print(head);

        ListNode noder3 = new ListNode(4);
        ListNode noder2 = new ListNode(3);
        ListNode heard = new ListNode(1);
        noder2.next = noder3;
        heard.next = noder2;
        PrintUtils.print(heard);

        ListNode res = demo.mergeTwoLists(head, heard);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -合并两个有序链表
     * 2。模拟运行
     * -新建一个链表，不断遍历两个原始链表，可能存在三种场景
     * --a。两个链表的节点都不为空，则将值更小的节点，添加到新链表的尾部，节点后移
     * --b。链表1的节点为空，则将链表2的节点添加到新链表的尾部，节点后移
     * --c。链表2节点为空
     * 3.总结：
     * -创建新链表，不断将原始链表的节点添加到新链表的尾部，原始链表的节点不断后移
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    ListNode next = l1.next;
                    tail.next = l1;
                    tail = tail.next;
                    l1 = next;
                } else {
                    ListNode next = l2.next;
                    tail.next = l2;
                    tail = tail.next;
                    l2 = next;
                }
            } else if (l1 == null) {
                //l2 不为空
                ListNode next = l2.next;
                tail.next = l2;
                tail = tail.next;
                l2 = next;
            } else {
                //l1不为空
                ListNode next = l1.next;
                tail.next = l1;
                tail = tail.next;
                l1 = next;
            }
        }
        tail.next = null;

        return dummyHead.next;
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * 示例 2：
     * 输入：l1 = [], l2 = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *
     * 提示：
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     */
}
