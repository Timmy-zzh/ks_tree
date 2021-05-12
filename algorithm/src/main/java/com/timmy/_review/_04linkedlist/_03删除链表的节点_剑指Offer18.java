package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _03删除链表的节点_剑指Offer18 {

    public static void main(String[] args) {
        _03删除链表的节点_剑指Offer18 demo = new _03删除链表的节点_剑指Offer18();
        ListNode node4 = new ListNode(9);
        ListNode node3 = new ListNode(1);
        ListNode node2 = new ListNode(5);
        ListNode head = new ListNode(4);
        node3.next = node4;
        node2.next = node3;
        head.next = node2;

        PrintUtils.print(head);
        ListNode res = demo.deleteNode(head, 5);
        PrintUtils.print(res);
    }

    //在原始链表中进行删除处理
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode node = dummyHead;
        while (node.next != null) {
            //相等需要删除该结点，但是需要使用前继节点
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {  //不相等直接后移
                node = node.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 1.理解题意
     * -输入一个链表的头节点，和一个值val，遍历该链表所有节点，当节点值等于val时，删除该节点，并将删除节点后的链表返回
     * 2。模拟运行
     * -创建新链表，不断遍历原始链表节点，只要遍历节点的值不是val时，则将该节点添加到新链表的尾部，原始链表需要不断遍历
     * 3。边界和细节处理
     * -遍历原始链表的节点，并将符合条件的节点插入到新链表的尾部，原始链表节点不断后移
     * 新链表尾部插入（有条件插入），原始链表需要不断遍历并且后移
     */
    public ListNode deleteNode_v1(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (head != null) {
            ListNode tmp = head.next;
            if (head.val != val) {
                tail.next = head;
                tail = tail.next;
            }
            head = tmp;
        }
        //如果原始链表的最后一个节点的值等于val，他不会添加，但是tail会指向该节点，需要断开链接
        tail.next = null;

        return dummyHead.next;
    }

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * 注意：此题对比原题有改动
     *
     * 示例 1:
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
     * 示例 2:
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *  
     * 说明：
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
     */
}
