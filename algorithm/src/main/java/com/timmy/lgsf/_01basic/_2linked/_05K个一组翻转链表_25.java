package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _05K个一组翻转链表_25 {

    public static void main(String[] args) {
        _05K个一组翻转链表_25 demo = new _05K个一组翻转链表_25();
        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        PrintUtils.print(head);
        System.out.println("--------------");

        ListNode res = demo.reverseKGroup(head, 3);
        PrintUtils.print(res);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre, next;
        ListNode curr = head;
        int n;
        boolean tag = false;
        ListNode res = null;
        ListNode preNode = new ListNode(); //  上一个合并节点的最后位置
        while (curr != null) {
            n = k;
            pre = null;
            while (curr != null && n > 0) {
                next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;

                n--;
            }

            //如果n！=0翻转回去
            if (n != 0) {
                n = k;
                curr = pre;
                pre = null;
                while (curr != null && n > 0) {
                    next = curr.next;
                    curr.next = pre;
                    pre = curr;
                    curr = next;
                }
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
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 进阶：
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *  
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     *
     * 示例 2：
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     *
     * 示例 3：
     * 输入：head = [1,2,3,4,5], k = 1
     * 输出：[1,2,3,4,5]
     *
     * 示例 4：
     * 输入：head = [1], k = 1
     * 输出：[1]
     *
     * 提示：
     * 列表中节点的数量在范围 sz 内
     * 1 <= sz <= 5000
     * 0 <= Node.val <= 1000
     * 1 <= k <= sz
     *
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     */
}
