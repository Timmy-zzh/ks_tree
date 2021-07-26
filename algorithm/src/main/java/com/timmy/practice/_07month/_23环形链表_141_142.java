package com.timmy.practice._07month;

import com.timmy.common.ListNode;

public class _23环形链表_141_142 {

    public static void main(String[] args) {
        _23环形链表_141_142 demo = new _23环形链表_141_142();
        ListNode head = new ListNode(3);
        ListNode head2 = new ListNode(2);
        ListNode head0 = new ListNode(0);
        ListNode head4 = new ListNode(-4);
        head.next = head2;
        head2.next = head0;
        head0.next = head4;
        head4.next = head2;

        boolean res = demo.hasCycle(head);
        System.out.println("res:" + res);

        ListNode node = demo.detectCycle(head);
        System.out.println("enter:" + (node == null ? "null" : node.val));
    }

    /**
     * 142题：
     * 1。先判断链表是否存在环，如果存在环，求出链表入环的第一个节点
     * 2。先判断是否存在环，快慢指针
     * -当存在环时，快慢指针碰撞的位置，
     * --从该节点开始往后遍历到达环入口节点的长度，和从头节点到环入口节点的长度一样
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode tempNode = fast;
                ListNode tempHead = head;
                while (tempHead != tempNode) {
                    tempHead = tempHead.next;
                    tempNode = tempNode.next;
                }
                return tempHead;
            }
        }
        return null;
    }

    /**
     * 141题：
     * 1.理解题意
     * -输入一个链表，判断链表中是否存在环，存在则返回true，否则false
     * 2。解题思路：快慢指针解法
     * -定义两个指针都从头节点开始往后移动，快指针每次走两步，慢指针每次走一步
     * -只要快指针没有走到空节点则一直往后遍历，
     * --如果当快慢指针移动到相同位置时，说明存在环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    /**
     * 给定一个链表，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
     * 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
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
