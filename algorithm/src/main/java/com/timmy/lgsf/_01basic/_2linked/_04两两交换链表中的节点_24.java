package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _04两两交换链表中的节点_24 {

    public static void main(String[] args) {
        _04两两交换链表中的节点_24 demo = new _04两两交换链表中的节点_24();

        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        PrintUtils.print(head);
        System.out.println("--------------");

        ListNode res = demo.swapPairs(head);
        PrintUtils.print(res);
    }

    /**
     * 1.
     * 2.添加虚拟头节点
     * -因为只是两两节点交换，所以可以先拿到
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //虚拟头节点
        ListNode def = new ListNode();
        def.next = head;
        ListNode curr = def;

        while (curr.next != null && curr.next.next != null) {
            ListNode node1 = curr.next;
            ListNode node2 = curr.next.next;
            //node1与node2交换;交换时，先处理后继，再处理前继
            node1.next = node2.next;
            node2.next = node1;
            curr.next = node2;

            curr = node1;
        }
        return def.next;
    }

    /**
     * 1.理解题意
     * -给定一个链表，相邻的节点两两交换，
     * 关键词：连标，节点两两交换
     * 2。解题思路
     * -每两个元素进行交换,所以可以将每两个节点作为一个整体进行处理
     * -每两个节点翻转后，再与前后的节点进行链接
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_v1(ListNode head) {
        ListNode pre, next;
        ListNode curr = head;
        int n;
        boolean tag = false;
        ListNode res = null;
        ListNode preNode = new ListNode(); //  上一个合并节点的最后位置
        while (curr != null) {
            n = 2;
            pre = null;
            while (curr != null && n > 0) {
                next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;

                n--;
            }
            if (!tag) {
                tag = true;
                res = pre;
            }
            preNode.next = pre;

            while (pre != null) {
                preNode = pre;
                pre = pre.next;
            }
        }
        return res;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     *
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：[1]
     *
     * 提示：
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     *
     * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
     *
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     */
}
