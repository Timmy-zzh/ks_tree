package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _06K个一组翻转链表_25 {

    public static void main(String[] args) {
        _06K个一组翻转链表_25 demo = new _06K个一组翻转链表_25();

        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;

        PrintUtils.print(head);
        ListNode res = demo.reverseKGroup(head, 3);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -k个一组反转链表，总数不超过k个保持原油顺序
     * 2。模拟运行
     * -遍历链表中的每个节点，每k个一组进行反转，所以将整个链表进行分段，每段进行处理，并且保存好前继和后继节点的值
     * -pre 是整个段前继节点
     * -curr tail 是每段的前后节点
     * -next 是整个段的后继节点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode pre = dummyHead, curr = head, tail = head, next = null;

        while (curr != null) {
            //curr 当前节点后移k个
            for (int i = 0; i < k - 1; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummyHead.next;
                }
            }

            //保存整个段的后继节点
            next = tail.next;

            tail.next = null;//断开
            //现在存在一个段 [head,tail] 需要进行翻转，并返回新的头尾节点
            ListNode[] nodes = reverse(curr, tail);
            curr = nodes[0];
            tail = nodes[1];
            PrintUtils.print(curr);

            //全部连接起来
            pre.next = curr;
            tail.next = next;

            pre = tail;
            curr = tail = next;
        }
        return dummyHead.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode last = dummyHead;

        while (head != null) {
            ListNode tmp = head.next;

            //head 插入到新链表的头部位置
            head.next = dummyHead.next;
            dummyHead.next = head;

            if (dummyHead == last) {
                last = last.next;
            }

            head = tmp;
        }
        last.next = null;

        return new ListNode[]{dummyHead.next, last};
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
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     */
}
