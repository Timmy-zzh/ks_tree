package com.timmy.practice._07month;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _11两数相加_2 {

    public static void main(String[] args) {
        _11两数相加_2 demo = new _11两数相加_2();

        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;

        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;

        PrintUtils.print(l1);
        PrintUtils.print(l2);

        ListNode res = demo.addTwoNumbers(l1, l2);
        PrintUtils.print(res);
    }

    /**
     * 1.理解题意
     * -输入两个链表，每个链表表示的是一个非负整数，例如整数342，则链表为2->4->3,现在要计算使用链表表示整数的两数之和
     * 2。解题思路：
     * 2。1。暴力解法
     * -将链表转换成数字，然后将两个整数进行相加得到整数结果，最后将整数再转换成链表返回
     * --存在问题：如果链表表示的整数范围很大，如果转换成整数会出现类型益处的错误
     * 2。2。使用链表元素相加
     * -不断从链表中取出元素，然后将链表节点的数字进行相加，相加结果的数字作为链表新节点添加到链表结果中
     * 3。边界与细节问题
     * -链表节点中数字相加结果可能大于10，会出现进位情况，这个时候链表节点保存个位数，进位1保存起来，等待下一次使用
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode dummyNode = new ListNode();
        ListNode currNode = dummyNode;

        int offset = 0;//   进位
        while (p != null || q != null) {
            int pV = p == null ? 0 : p.val;
            int qV = q == null ? 0 : q.val;

            int sum = pV + qV + offset;
            int value = sum % 10;
            offset = sum / 10;

            currNode.next = new ListNode(value);
            currNode = currNode.next;

            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        if (offset > 0) {
            currNode.next = new ListNode(offset);
        }

        return dummyNode.next;
    }
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 示例 2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *  
     * 提示：
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     */
}
