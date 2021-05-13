package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _04删除排序链表中的重复元素_83 {

    public static void main(String[] args) {
        _04删除排序链表中的重复元素_83 demo = new _04删除排序链表中的重复元素_83();
        ListNode node4 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode head = new ListNode(1);
        node3.next = node4;
        node2.next = node3;
        head.next = node2;
        PrintUtils.print(head);
        ListNode res = demo.deleteDuplicates(head);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入一个升序排列的链表，链表中的节点数据可能相同，现在需要删除重复出现的元素，使得每个元素只出现一次
     * 2。模拟运行
     * 采用创建新链表的方式：
     * -遍历原始链表中的每个节点，第一个节点添加到新链表的尾部，原始链表继续遍历，后面的节点数据和新链表尾部节点值不同时，则添加到尾部节点
     * 3。总结：
     */
    public ListNode deleteDuplicates(ListNode head) {
        //新链表头部
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        //新链表尾部节点
        ListNode tail = dummyHead;

        //不断遍历原始链表
        while (head != null) {
            //临时变量保存 当前节点的后继节点
            ListNode tmp = head.next;

            //判断当前节点与新链表尾部节点的值是否相同
            if (tail == dummyHead || tail.val != head.val) {
                //尾部插入
                tail.next = head;
                tail = tail.next;
            }

            //当前节点后移，用于继续遍历
            head = tmp;
        }
        //尾部节点指向null
        tail.next = null;

        return dummyHead.next;
    }

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素只出现一次 。
     * 返回同样按升序排列的结果链表。
     *
     * 示例 1：
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     *
     * 示例 2：
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     *  
     * 提示：
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序排列
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     */
}
