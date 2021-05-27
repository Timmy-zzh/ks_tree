package com.timmy._review._07sort;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _04合并两个有序链表_21 {

    public static void main(String[] args) {
        _04合并两个有序链表_21 demo = new _04合并两个有序链表_21();
        ListNode l12 = new ListNode(2, new ListNode(4));
        ListNode l1 = new ListNode(1, l12);

        ListNode l23 = new ListNode(3, new ListNode(4));
        ListNode l2 = new ListNode(1, l23);
        ListNode res = demo.mergeTwoLists(l1, l2);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入两个有序链表，将两个链表进行合并后返回
     * 2。解题思路
     * -创建新链表，不断将两个链表中的更小的节点添加到新链表的尾部节点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while (l1 != null || l2 != null) {
            if (l2 == null || l1 != null && l1.val <= l2.val) {
                //取l1链表节点添加到新链表中
                tail.next = l1;
                tail = tail.next;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = tail.next;
                l2 = l2.next;
            }
            tail.next = null;
        }
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
