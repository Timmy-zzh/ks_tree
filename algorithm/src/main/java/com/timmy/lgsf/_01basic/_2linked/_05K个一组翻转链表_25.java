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
        System.out.println("--------------");

        ListNode res = demo.reverseKGroup(head, 3);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入一个链表和需要翻转的间隔个数，求翻转后的新链表
     * 2。解题思路
     * -创建虚拟头节点指向链表的真实头节点，然后虚拟头节点不动，
     * --最后返回虚拟头节点的下一个节点为最终结果，新建一个节点指向虚拟头节点
     * -因为原始链表需要按照k个一组进行翻转，所以解题思路重点是将 k个一组链表进行处理
     * --保留k个一组前继节点和他的后继节点，找到K个一组的 头节点和尾节点 然后进行翻转，翻转后再进行链接
     * 3。边界和细节问题
     * -k个一组进行翻转，最后没有到达k个，则不用反转，直接链接上
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode curr = dummyHead.next;
        //k个一组的前继,和后继节点
        ListNode prev = dummyHead;
        ListNode tail;
        //k组中的前后节点
        ListNode start = curr;
        ListNode end = curr;

        while (curr != null) {
            System.out.println("---curr:" + curr.value + " ,start:" + start.value + " ,end:" + end.value);
            // k个一组的开头和结尾: start end
            for (int i = 1; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return dummyHead.next;
                }
            }
            tail = end.next;
            //start 与 end 翻转
            System.out.println("before --- ,start:" + start.value + " ,end:" + end.value);
            ListNode[] res = reverse(start, end);
            start = res[0];
            end = res[1];
            System.out.println("after --- ,start:" + start.value + " ,end:" + end.value);

            prev.next = start;
            end.next = tail;

            prev = end;
            curr = end.next;
            start = curr;
            end = curr;
        }

        return dummyHead.next;
    }

    private ListNode[] reverse(ListNode start, ListNode end) {
        //保留后继节点
        ListNode last = end.next;
        //反转使用到的三个节点 prev，curr，next
        ListNode curr = start;
        ListNode prev = null, next;
        while (curr != last) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        start.next = last;
        return new ListNode[]{prev, start};
    }

    public ListNode reverseKGroup_v1(ListNode head, int k) {
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
