package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _05合并K个升序链表_23 {

    public static void main(String[] args) {
        _05合并K个升序链表_23 demo = new _05合并K个升序链表_23();
        ListNode[] lists = new ListNode[3];

        ListNode node3 = new ListNode(5);
        ListNode node2 = new ListNode(4);
        ListNode head = new ListNode(1);
        node2.next = node3;
        head.next = node2;
        lists[0] = head;

        ListNode node23 = new ListNode(4);
        ListNode node22 = new ListNode(3);
        ListNode head2 = new ListNode(1);
        node22.next = node23;
        head2.next = node22;
        lists[1] = head2;

        ListNode node32 = new ListNode(6);
        ListNode head3 = new ListNode(2);
        head3.next = node32;
        lists[2] = head3;

        for (ListNode list : lists) {
            PrintUtils.print(list);
        }

        ListNode res = demo.mergeKLists(lists);
        PrintUtils.print(res);
    }

    /**
     * 1。理解题意
     * -合并k个有序链表
     * 2。模拟运行
     * 使用优先级队列，（最小堆实现）
     * -先将链表集合中的首个节点，保存到优先级队列中
     * -然后不断获取优先级队列中的堆顶元素，并且将堆顶元素的后继节点添加到队列中，重复上诉操作，
     * -不断将新获取的堆顶元素添加到新链表的尾部
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        //小顶堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode node, ListNode t1) {
                return node.val - t1.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                queue.offer(lists[i]);
        }


        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            tail.next = poll;
            tail = tail.next;
            if (poll.next != null) {
                queue.offer(poll.next);
            }
        }

        return dummyHead.next;
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 示例 1：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     * 示例 2：
     * 输入：lists = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：lists = [[]]
     * 输出：[]
     *  
     * 提示：
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     */
}
