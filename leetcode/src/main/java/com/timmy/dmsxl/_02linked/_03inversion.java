package com.timmy.dmsxl._02linked;

import com.timmy.leetcode._202008.ListNode;

/**
 * 总结：链表
 * 内存地址随机存储， 节点中保存next节点的指针
 * 可以高效删除和插入操作，不适合存取操作
 *
 * 常用场景：
 * 1。删除节点：通过构造虚拟头节点
 * 2。链表操作，增删改查
 * 3。链表反转，与判断链表是否有环，和查找环入口
 */
public class _03inversion {

    public static void main(String[] args) {
//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;
//
//        PrintUtils.print(listNode1);
//        ListNode result = reversion(listNode1);
//        PrintUtils.print(result);


        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;
        int cycleNodeValue = getCycleNode(listNode1);
        System.out.println("result:" + cycleNodeValue);
    }

    /**
     * 链表反转
     * 双指针法
     */
    private static ListNode reversion(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 1。先确定链表是否有环--》快慢指针法
     * 2。如果有环的话，找出环的入口节点，否则返回-1
     */
    public static int getCycleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {  //遇到环了
                /**
                 * 从相遇点 和 从头节点开始的两个指针相遇点就是环入口点
                 */
                ListNode node1 = fast;
                ListNode node2 = head;
                while (node1 != node2) {
                    node1 = node1.next;
                    node2 = node2.next;
                }
                return node1.value;
            }
        }
        return -1;
    }

}
