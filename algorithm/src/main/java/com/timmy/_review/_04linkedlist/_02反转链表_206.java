package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _02反转链表_206 {

    public static void main(String[] args) {
        _02反转链表_206 demo = new _02反转链表_206();
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode head = new ListNode(1);
        node4.next = new ListNode(5);
        node3.next = node4;
        node2.next = node3;
        head.next = node2;

        PrintUtils.print(head);
        ListNode res = demo.reverseList(head);
        PrintUtils.print(res);
    }

    /**
     * 在原始链表中进行反转
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, curr = head, next = null;

        while (curr != null) {
            //保存后继节点
            next = curr.next;
            //当前节点与前继节点交换，并使前继节点后移
            curr.next = pre;
            pre = curr;

            curr = next;
        }
        return pre;
    }

    /**
     * 1.理解题意
     * -输入一个链表，将该链表进行反转，将反转后的链表进行返回
     * 2.模拟运行
     * -创建一个新的链表，遍历老链表，不断将老链表中的节点插入到新链表的头部位置
     * -注意老链表的元素位置不断后移
     * 3.边界与细节问题
     * -老链表不断遍历，遍历节点不断后移，直到链表尾部
     * -遍历到的老链表节点不断插入到新链表的头部，实现链表的反转
     * 4。复杂度分析
     * -时间：遍历了所有链表的节点一遍-O(n)
     * -空间：当前节点的后继节点保存-O(1)
     * 5.总结
     * -要保证老链表的不断遍历，和不断插入到新链表的头部；在遍历过程中需要保存后继节点用于节点后移
     */
    public ListNode reverseList_v1(ListNode head) {
        ListNode dummyHead = new ListNode();
        while (head != null) {
            //先后后继节点保存，用于当前节点后移
            ListNode next = head.next;

            //将当前遍历到的节点，插入到新链表的头部
            head.next = dummyHead.next;
            dummyHead.next = head;

            head = next;
        }
        return dummyHead.next;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     *
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：[2,1]
     *
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     *
     * 提示：
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     *
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     */
}
