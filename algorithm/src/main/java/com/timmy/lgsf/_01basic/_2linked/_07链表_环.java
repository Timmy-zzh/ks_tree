package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;

public class _07链表_环 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;

        boolean hasCycle = hasCycle(listNode1);
        System.out.println("hasCycle:" + hasCycle);

        int cycleNodeValue = getCycleNode(listNode1);
        System.out.println("result:" + cycleNodeValue);
    }

    /**
     * 如果链表存在环，找出环入口元素
     */
    private static int getCycleNode(ListNode header) {
        ListNode fast = header;
        ListNode slow = header;
        ListNode temp = header;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) { //存在环
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp.val;
            }
        }
        return -1;
    }

    /**
     * 判断链表是否有环？
     * 解题思路：快慢指针
     * 快慢指针最后碰撞在一起说明存在环
     */
    private static boolean hasCycle(ListNode header) {
        ListNode fast = header;
        ListNode slow = header;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
