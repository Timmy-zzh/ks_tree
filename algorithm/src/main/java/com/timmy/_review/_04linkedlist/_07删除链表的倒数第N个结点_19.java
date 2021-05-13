package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _07删除链表的倒数第N个结点_19 {

    public static void main(String[] args) {
        _07删除链表的倒数第N个结点_19 demo = new _07删除链表的倒数第N个结点_19();

        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode head = new ListNode(1);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        head.next = node2;
        PrintUtils.print(head);

        ListNode res = demo.removeNthFromEnd(head, 2);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -输入一个链表，删除链表的倒数din个节点
     * 2。模拟运行
     * -要删除一个节点，需要先找到该节点的前继节点，然后进行删除
     * -要找到链表的倒数第n个节点，可以使用双指针进行实现，快慢指针
     * --快指针先后移k个位置，然后满指针开始后移，当快指针移动到链表末尾时，满指针的位置刚好在倒是第n个节点的前继节点位置，然后进行删除操作
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode front = head, back = dummyHead;
        int i = 0;
        while (front != null) {
            front = front.next;

            if (i >= n) {
                back = back.next;
            }
            i++;
        }

        back.next = back.next.next;

        return dummyHead.next;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 进阶：你能尝试使用一趟扫描实现吗？
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     *
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *
     * 提示：
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     */
}
