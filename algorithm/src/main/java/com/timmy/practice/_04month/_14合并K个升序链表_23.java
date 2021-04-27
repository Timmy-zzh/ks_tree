package com.timmy.practice._04month;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _14合并K个升序链表_23 {

    public static void main(String[] args) {
        _14合并K个升序链表_23 demo = new _14合并K个升序链表_23();
        ListNode[] lists = new ListNode[3];

        ListNode _1l1 = new ListNode(5);
        ListNode _1l2 = new ListNode(4, _1l1);
        ListNode _1Head = new ListNode(1, _1l2);
        lists[0] = _1Head;

        ListNode _2l1 = new ListNode(4);
        ListNode _2l2 = new ListNode(3, _2l1);
        ListNode _2Head = new ListNode(1, _2l2);
        lists[1] = _2Head;

        ListNode _3l1 = new ListNode(6);
        ListNode _3Head = new ListNode(2, _3l1);
        lists[2] = _3Head;

        ListNode res = demo.mergeKLists(lists);
        PrintUtils.print(res);
    }

    /**
     * 2。优先队列解法
     * -因为链表数组中的每个链表都是升序的，所以使用优先级队列（小顶堆）保存每个链表的第一个节点
     * -然后从优先队列中取出值，这个值就是最小值，然后不断从取出元素的链表下一个节点
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode();
        ListNode node = dummyHead;
        int K = lists.length;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(K, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode node, ListNode t1) {
                return node.value - t1.value;
            }
        });
        for (ListNode list : lists) {
            queue.add(list);
        }

        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            node.next = poll;

            if (poll.next != null) {
                queue.add(poll.next);
            }
            node = node.next;
        }
        PrintUtils.print(dummyHead);
        return dummyHead.next;
    }

    /**
     * 1.
     * 2.v1版本可用归并思想求解
     * -将数组中每个链表进行拆分，最后拆分成两个链表进行合并
     */
    public ListNode mergeKLists_v2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode leftNode = mergeKLists(lists, left, mid);
        ListNode rightNode = mergeKLists(lists, mid + 1, right);
        return merge(leftNode, rightNode);
    }

    /**
     * 1.理解题意
     * -输入一个链表数组，而且数组中每个链表都已按升序排列
     * -要求将链表数组中所有的链表进行合并，生成一个升序的新链表
     * 2。解题思路
     * -将数组中的两个链表进行合并成一个新的，然后再与数组中其他链表进行合并，直到数组中所有链表都合并完成
     * 3。细节
     * -假头节点
     * -节点转移
     */
    public ListNode mergeKLists_v1(ListNode[] lists) {
        ListNode dummyHead = new ListNode();
        ListNode node = dummyHead;

        for (ListNode listNode : lists) {
            //合并两个链表，并返回头节点
            node = merge(node, listNode);
        }
        System.out.println("dummyHead--");
        PrintUtils.print(dummyHead);
        return dummyHead.next;
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        System.out.println("merge-- node1:" + node1.value + " ,node2:" + node2.value);
        ListNode header = new ListNode();
        ListNode node = header;
        while (node1 != null || node2 != null) {
            if (node1 == null) {
                node.next = node2;
                node2 = node2.next;
            } else if (node2 == null) {
                node.next = node1;
                node1 = node1.next;
            } else {
                if (node1.value < node2.value) {
                    node.next = node1;
                    node1 = node1.next;
                } else {
                    node.next = node2;
                    node2 = node2.next;
                }
            }
            node = node.next;
        }
        System.out.println("header---");
        PrintUtils.print(header.next);
        return header.next;
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
     *
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     */
}
