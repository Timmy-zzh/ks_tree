package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _03链表翻转 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        PrintUtils.print(listNode1);
//        ListNode result = reversion(listNode1);
//        PrintUtils.print(result);

        System.out.println("--------------");
        ListNode result2 = reversion(listNode1, 3);
        PrintUtils.print(result2);
    }

    /**
     * 链表翻转2：
     * 给定一个含有n个元素的链表，现在要求每k个节点一组进行翻转，打印翻转后的结构，其中k是一个正整数，且可被n整除
     * <p>
     * - 例如链表为：1-2-3-4-5-6，k=3，则打印：321654
     * <p>
     * 解题思路：
     * 1。先正常遍历，i++；当i可以被k整除时
     * 2.则将k个链表节点进行翻转
     * 3。将每k个链表 当作一个子链表，注意保存 pre，begin，指针控制节点
     */
    private static ListNode reversion(ListNode node, int k) {
        //1。创建空的头节点
        ListNode header = new ListNode(-1);
        header.next = node;
        ListNode pre = header;
        ListNode begin = node;
        ListNode end;
        ListNode temp;  //指向下一个子链表的节点，用于保证链表不要断了

        while (node != null) {
            for (int i = 0; i < k - 1 && node.next != null; i++) {
                node = node.next;
            }
            end = node;
            temp = node.next;

            //断开链接
            end.next = null;
            pre.next = reveSubList(begin);

            //再次链接
            begin.next = temp;

            //重置
            pre = begin;
            begin = temp;
            node = temp;
        }
        return header.next;
    }

    /**
     * 翻转单个子链表，返回新的连标头节点
     */
    private static ListNode reveSubList(ListNode node) {
        ListNode pre = null;
        ListNode curr = node;
        ListNode temp;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 链表翻转： 1->2->3->4->5  结果： 5->4->3->2->1
     * 输入链表头节点 1； 最后输出链表节点 5
     * 解题思路：双指针法
     * 每次拿到的只有一个节点，需要使用额外变量保存已翻转的节点
     * <p>
     * 每次移动，pre 与 curr同时移动，且curr的next指针指向pre
     */
    private static ListNode reversion(ListNode node) {
        ListNode pre = null, temp, curr = node;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }
}
