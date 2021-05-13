package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;

public class _08环形链表_141 {

    public static void main(String[] args) {
        _08环形链表_141 demo = new _08环形链表_141();

        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(0);
        ListNode node2 = new ListNode(2);
        ListNode head = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

//        PrintUtils.print(head);
        boolean res = demo.hasCycle(head);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个链表，判断链表中是否存在环？
     * 2。模拟运行
     * 快满指针解法
     * -如果快慢指针最后相等，说明存在环，否则不存在
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     * 进阶：
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     *
     * 提示：
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引 。
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle
     */
}
