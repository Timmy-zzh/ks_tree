package com.timmy.lgsf._01basic._6skiplist;


import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _01删除排序链表中的重复元素II_82 {

    public static void main(String[] args) {
        _01删除排序链表中的重复元素II_82 demo = new _01删除排序链表中的重复元素II_82();
        // 1->2->3->3->4->4->5
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode listNode = demo.deleteDuplicates(l1);
        PrintUtils.print(listNode);
    }

    /**
     * 2/4 删除排序链表中的重复元素 II
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 示例 1:
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     *
     * 示例 2:
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     */

    /**
     * 解题思路：
     * 1.创建头节点，头节点作为prev前驱节点，遍历链表，
     * 2.如果node与next节点的值相同，则继续遍历
     * 3.如果不相同，则前驱节点指向node，前驱节点后移
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode defNode = new ListNode(-1);
        defNode.next = head;
        ListNode prev = defNode;
        ListNode node = head;

        while (node != null) {
            if (node.next != null && node.val == node.next.val) {
                while (node.next != null && node.val == node.next.val) {
                    node = node.next;
                }
                node = node.next;
                if (node == null){
                    prev.next = null;
                }
            } else {
                prev.next = node;
                prev = node;
                node = node.next;
            }
        }
        return defNode.next;
    }
}
