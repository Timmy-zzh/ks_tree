package com.timmy.lgsf._01basic._2linked;

import com.timmy.common.ListNode;

/**
 * 自己设计链表实现，功能如下：
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。
 * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，
 * 则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * 实现：
 * 1。动态链表头
 * 2。size表示链表数量
 */
public class _MyLinkedList {

    public static void main(String[] args) {
        _MyLinkedList linkedList = new _MyLinkedList();
        System.out.println("--------addAtHead----------");
        linkedList.addAtHead(1);
        linkedList.printL();
        System.out.println("----------addAtTail--------");
        linkedList.addAtTail(3);
        linkedList.printL();
        System.out.println("----------addAtIndex--------");
        linkedList.addAtIndex(1, 2);
        linkedList.printL();
        System.out.println("----------get--------");
        int result = linkedList.get(1);
        System.out.println("result:" + result);
        System.out.println("----------deleteAtIndex--------");
        linkedList.deleteAtIndex(1);
        linkedList.printL();
        System.out.println("----------get--------");
        result = linkedList.get(1);
        System.out.println("result:" + result);
    }

    private ListNode dynamicHead;
    private int size = 0;

    public _MyLinkedList() {
        dynamicHead = new ListNode(0);
    }

    /**
     * 获取第index个节点值
     */
    public int get(int index) {
        if (index < 0 || index > size) {
            return -1;
        }
        //从真正的头节点开始遍历，找到第index位置，并返回
        ListNode cur = dynamicHead.next;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dynamicHead.next;
        dynamicHead.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode cur = dynamicHead;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        size++;
    }

    /**
     * 在链表的第index个插入节点val
     * 实现：index大于size不插入，index小于0插入到头部
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        //遍历到第index个节点
        ListNode cur = dynamicHead;
        while (index-- > 0) {
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * 删除第index个节点
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return;
        }
        //遍历到第index个节点
        ListNode cur = dynamicHead;
        while (index-- > 0) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size++;
    }

    public void printL() {
        ListNode node = dynamicHead.next;
        while (node != null) {
            System.out.print(node.val + " ,");
            node = node.next;
        }
        System.out.println();
    }

}
