package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;

/**
 * 删除链表元素
 */
public class _08链表删除 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(4);
        ListNode head21 = new ListNode(1);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(1);

        head.next = head2;
        head2.next = head21;
        head21.next = head3;
        head3.next = head4;
        head4.next = head5;

        ListNode tempNode = head;
        while (tempNode != null) {
            System.out.print(tempNode.value + " ,");
            tempNode = tempNode.next;
        }
        System.out.println();
        System.out.println("------------------------");

        _08链表删除 prac = new _08链表删除();
//        ListNode result = prac.removeEle(head, 1);
        ListNode result = prac.removeEle2(head, 1);
        System.out.println("reslult:" + result.value);

        tempNode = result;
        while (tempNode != null) {
            System.out.print(tempNode.value + " ,");
            tempNode = tempNode.next;
        }
        System.out.println();
    }

    /**
     * 删除链表中等于给定值 val 的所有节点。
     * <p>
     * 这里以链表 1 4 2 4  来举例，移除元素1。
     * <p>
     * 思路：
     * 直接使用原来的链表进行删除操作
     * 要特殊处理头节点的value值等于target值
     */
    private ListNode removeEle(ListNode head, int target) {
        //处理头部
        while (head != null && head.value == target) {
            head = head.next;
        }
        ListNode result = head;
        while (head != null && head.next != null) {
            if (head.next.value == target) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return result;
    }

    /**
     * 设置一个虚拟头节点
     * 注意：返回值和动态删除的节点
     */
    private ListNode removeEle2(ListNode head, int target) {
        ListNode dynamic = new ListNode(0);
        dynamic.next = head;

        ListNode temp = dynamic;
        while (temp.next != null) {
            if (temp.next.value == target) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dynamic.next;
    }
}
