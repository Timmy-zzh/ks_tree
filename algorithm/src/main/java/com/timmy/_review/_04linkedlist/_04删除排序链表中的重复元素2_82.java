package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _04删除排序链表中的重复元素2_82 {

    public static void main(String[] args) {
        _04删除排序链表中的重复元素2_82 demo = new _04删除排序链表中的重复元素2_82();
        //1,2,3,3,4,4,5
//        ListNode node7 = new ListNode(5);
//        ListNode node6 = new ListNode(4);
//        ListNode node5 = new ListNode(4);
//        ListNode node4 = new ListNode(3);
//        ListNode node3 = new ListNode(3);
//        ListNode node2 = new ListNode(2);
//        ListNode head = new ListNode(1);
//        node6.next = node7;
//        node5.next = node6;
//        node4.next = node5;
//        node3.next = node4;
//        node2.next = node3;
//        head.next = node2;
        //1,1,1,2,3
        ListNode node5 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode head = new ListNode(1);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        head.next = node2;
        PrintUtils.print(head);
        ListNode res = demo.deleteDuplicates(head);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入一个升序排列的链表，链表中存在节点值相同的元素，要求将存在重复数据的节点删除，只留下没有重复出现的数字
     * 2。模拟运行
     * -因为链表是升序排序，可以通过判断当前节点与后继节点的值是否相同来判断是否需要该节点
     * -采用创建新链表方式求解，不断遍历原始链表的节点，将符合条件的节点添加到新链表的尾部节点
     * -使用一个临时变量保存当前遍历的节点，并判断后继节点的值，是否相同，如果不相同，说明可以添加该节点到新链表中
     * --如果相同，说明该节点和后面节点都不添加到新链表，并且需要while遍历到不想等的节点位置
     * 3。总结
     * -创建新链表，不断将满足条件的节点添加到新链表的尾部
     * --当前节点与后继节点值不相同
     * --如果当前节点与后继节点相同，则不断往后遍历，直到遇到不相等的节点，
     * -时刻记住节点后移
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode tail = dummyHead;

        //1。不断遍历原始链表
        while (head != null) {
            ListNode next = head.next;
            //判断当前链表和后继节点的值是否相同
            if (next == null || head.val != next.val) {
                tail.next = head;
                tail = tail.next;
            } else {
                //如果当前节点和后继节点值相同
                //需要找到后面不想等的节点，且当前节点和相同节点不会添加到新链表中
                while (next != null && head.val == next.val) {
                    next = next.next;
                }
            }
            head = next;
        }
        tail.next = null;

        return dummyHead.next;
    }

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     * 返回同样按升序排列的结果链表。
     *
     * 示例 1：
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     *
     * 示例 2：
     * 输入：head = [1,1,1,2,3]
     * 输出：[2,3]
     *
     * 提示：
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序排列
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     */
}
