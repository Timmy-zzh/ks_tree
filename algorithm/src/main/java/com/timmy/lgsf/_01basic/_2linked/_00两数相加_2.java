package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _00两数相加_2 {

    public static void main(String[] args) {
        ListNode l1_2 = new ListNode(2);
        ListNode l1_4 = new ListNode(4);
        ListNode l1_3 = new ListNode(3);
        l1_2.next = l1_4;
        l1_4.next = l1_3;

        ListNode l2_5 = new ListNode(5);
        ListNode l2_6 = new ListNode(6);
        ListNode l2_4 = new ListNode(4);
        l2_5.next = l2_6;
        l2_6.next = l2_4;

        _00两数相加_2 demo = new _00两数相加_2();
        ListNode listNode = demo.addTwoNumbers(l1_2, l2_5);
        PrintUtils.print(listNode);
    }

    /**
     * TODO 大数相加题目
     *
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     */

    /**
     * 解题思路：最优解 - 数学思维法
     * --遍历两个链表，将链表节点对应位置的数值相加
     * 注意进位处理
     */
    public ListNode addTwoNumbers(ListNode p, ListNode q) {
        ListNode head = new ListNode(-1);
        ListNode curr = head;

        int offset = 0; //进位
        while (p != null || q != null) {
            //链表节点的数值
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;

            //将链表节点的数值相加，并获取到是否有进位
            int sum = x + y + offset;
            int num = sum % 10;
            offset = sum / 10;

            //链表节点移动
            curr.next = new ListNode(num);
            curr = curr.next;

            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }

        if (offset > 0) {
            curr.next = new ListNode(offset);
        }
        return head.next;
    }

    /**
     * 解题思路：暴力解法
     * --将链表转成数字-》数字相加--》数字再转成链表返回
     */
    public ListNode addTwoNumbers_v1(ListNode p, ListNode q) {
        //链表转数字： 2-》4-》3  == 342
        int num1 = 0;
        int pow = 0;
        while (p != null) {
            //10的n次方
            double powValue = Math.pow(10, pow);
            int value = p.val;
            num1 = (int) (num1 + value * powValue);
            pow++;
            p = p.next;
        }
        System.out.println("num1:" + num1);

        //
        int num2 = 0;
        pow = 0;
        while (q != null) {
            //10的n次方
            double powValue = Math.pow(10, pow);
            int value = q.val;
            num2 = (int) (num2 + value * powValue);
            pow++;
            q = q.next;
        }
        System.out.println("num2:" + num2);

        //数字转链表 342 + 465 = 807 --》输出7 -> 0 -> 8
        //不断遍历得到个位数字，然后组成链表
        long resultNum = num1 + num2;
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        if (resultNum == 0) {
            return new ListNode(0);
        }

        while (resultNum != 0) {
            int mode = (int) (resultNum % 10);
            curr.next = new ListNode(mode);
            curr = curr.next;
            resultNum = resultNum / 10;
        }
        return head.next;
    }

}
