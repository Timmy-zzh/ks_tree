package com.timmy._review._04linkedlist;

import com.timmy.common.ListNode;

/**
 * 1.理解题意
 * 设计链表结构：包括如下功能
 * -将节点数据插入到链表的头部
 * -将节点数据插入到链表的尾部
 * -将数据插入到链表的的某个位置
 * -获取某一个位置节点的数据
 * -删除某一位置的节点
 * 2。解题思路
 * 使用假头
 * -不管是插入，删除，还是获取；读需要先获取到所求节点的前继节点
 * 2。1。只是用一个假头节点
 * 2。2。使用假头节点变量，还有一个尾部节点变量
 */
public class _01MyLinkedList {

    ListNode dummyHead;
    ListNode tail;
    int size;

    /**
     * Initialize your data structure here.
     */
    public _01MyLinkedList() {
        dummyHead = new ListNode();
        tail = dummyHead;
        size = 0;
    }

    /**
     * 获取链表index位置的节点数据
     * -先判断index值是否在链表大小范围内？
     * -获取index位置节点的前继节点
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode preNode = getPreNode(index);
        return preNode.next.value;
    }

    /**
     * 这样处理可以保证当前节点不为null
     */
    private ListNode getPreNode(int index) {
        //后面一个节点
        ListNode back = dummyHead;
        //前面一个节点
        ListNode fornt = dummyHead.next;
        for (int i = 0; i < index && fornt != null; i++) {
            back = fornt;
            fornt = fornt.next;
        }
        return back;
    }

    /**
     * 将节点数据val插入到头节点位置
     * -新建节点
     * -新节点的后继节点为假头的后继节点
     * -假头的后继节点 为 新节点
     * -如果当前是空链表，则尾部节点等于假头，尾部节点需要后移
     */
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        if (dummyHead == tail) {
            tail = newNode;
        }
        size++;
    }

    /**
     * 将节点数据val，添加到链表的尾部节点后面
     * 1。新建节点
     * 2。尾部节点的后继节点指向新节点
     * 3。尾部节点后移
     */
    public void addAtTail(int val) {
        tail.next = new ListNode(val);
        tail = tail.next;
        size++;
    }

    /**
     * 在链表的index位置，插入新节点val
     * 1。如果index <= 0 ,插入到头节点
     * 2。如果index = size，插入到尾部节点， >size 实现不了
     * 3。插入到链表的中部位置，则先获取链表位置的前继节点，然后进行插入
     * -新建节点
     * -新节点的后继节点，为前继节点的后继
     * -前继节点的后继节点为新节点
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        } else if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode preNode = getPreNode(index);
            ListNode newNode = new ListNode(val);
            newNode.next = preNode.next;
            preNode.next = newNode;
            size++;
        }
    }

    /**
     * 删除index位置的节点
     * -先判断删除节点位置范围
     * -查找删除位置的前继节点，然后进行删除
     * -特殊处理：删除的是尾部节点
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode preNode = getPreNode(index);
        preNode.next = preNode.next.next;
        //如果是删除的是最后一个
        if (index == size - 1) {
            tail = preNode;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public void print() {
        System.out.println("-------");
        ListNode node = dummyHead.next;
        while (node != null) {
            System.out.print(node.value + " ,");
            node = node.next;
        }
        System.out.println();
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
