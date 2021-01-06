package com.timmy.lgsf._6skiplist;

import java.util.Random;

public class _01跳表设计_1206 {

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.printVal();
        skiplist.add(2);
        skiplist.printVal();
        skiplist.add(3);
        skiplist.printVal();
        boolean result = skiplist.search(0);// 返回 false
        System.out.println("search(0)--- " + result);
        skiplist.add(4);
        skiplist.printVal();
        result = skiplist.search(1);   // 返回 true
        System.out.println("search(1)--- " + result);
        result = skiplist.erase(0);    // 返回 false，0 不在跳表中
        System.out.println("erase(0)--- " + result);
        skiplist.printVal();
        result = skiplist.erase(1);    // 返回 true
        System.out.println("erase(1)--- " + result);
        skiplist.printVal();
        result = skiplist.search(1);   // 返回 false，1 已被擦除
        System.out.println("search(1)--- " + result);
    }

    /**
     * 不使用任何库函数，设计一个跳表。
     * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，
     * 并且跳表的代码长度相较下更短，其设计思想与链表相似。
     * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
     * <p>
     * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
     * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。
     * 跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
     * <p>
     * 在本题中，你的设计应该要包含这些函数：
     * bool search(int target) : 返回target是否存在于跳表中。
     * void add(int num): 插入一个元素到跳表。
     * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
     * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
     * <p>
     * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
     * <p>
     * 样例:
     * Skiplist skiplist = new Skiplist();
     * skiplist.add(1);
     * skiplist.add(2);
     * skiplist.add(3);
     * skiplist.search(0);   // 返回 false
     * skiplist.add(4);
     * skiplist.search(1);   // 返回 true
     * skiplist.erase(0);    // 返回 false，0 不在跳表中
     * skiplist.erase(1);    // 返回 true
     * skiplist.search(1);   // 返回 false，1 已被擦除
     * 约束条件:
     * <p>
     * 0 <= num, target <= 20000
     * 最多调用 50000 次 search, add, 以及 erase操作。
     * <p>
     * 链接：https://leetcode-cn.com/problems/design-skiplist
     */

    /**
     * 跳表包含
     * 头节点：head
     * 跳表的层级：levels
     */
    public static class Skiplist {

        private final int HEAD_VALUE = -1;
        private Node head;
        private int levels;

        public Skiplist() {
            head = new Node(HEAD_VALUE);
            levels = 1;
        }

        /**
         * 查询
         * <p>
         * 解题思路：
         * 从头节点开始，从左到右遍历，直到大于target的值，接着往下遍历
         */
        public boolean search(int target) {
            Node node = head;
            while (node != null) {
                //1.不断往右遍历
                while (node.right != null && node.right.val < target) {
                    node = node.right;
                }
                //2。判断右节点的值是否等于target
                if (node.right != null && node.right.val == target) {
                    return true;
                }
                //3.否则往下遍历查找
                node = node.down;
            }
            return false;
        }

        /**
         * 插入
         * 1。先找到插入的位置，先插入到原链表
         * 2。要插入，首先要知道插入节点前一个节点的位置
         * 3。索引层是否需要插入，通过抛硬币概率控制
         */
        public void add(int num) {
            // 使用数据保存插入前节点 的节点标记
            Node[] nodes = new Node[levels];
            int i = 0;
            Node node = head;
            while (node != null) {
                //1.不断往右遍历
                while (node.right != null && node.right.val <= num) {
                    node = node.right;
                }
                //2。node的right节点为 新节点插入位置，保存锚点
                nodes[i++] = node;

                //遍历下一层
                node = node.down;
            }

            //3.便利到原链表，进行新节点插入
            node = nodes[--i];
            Node target = new Node(num);
            target.right = node.right;
            node.right = target;

            //索引层新增锚点
            insertAnchorByIcons(target, nodes, i);
        }

        /**
         * 根据随机数，判断索引层是否需要新节点
         * index 是上一层的节点
         */
        private void insertAnchorByIcons(Node target, Node[] nodes, int index) {
            Node downNode = target;
            Random random = new Random();
            int icon = random.nextInt(2);// 0 or 1
            while (icon == 0 && index > 0) {
                //上一层的节点
                Node prev = nodes[--index];
                Node newNode = new Node(target.val, prev.right, downNode);
                prev.right = newNode;
                downNode = newNode;

                icon = random.nextInt(2);
            }

            //最上层，是否需要新增一层
            icon = random.nextInt(2);
            if (icon == 0) {
                Node newNode = new Node(target.val, null, downNode);
                Node newHead = new Node(HEAD_VALUE, newNode, head);
                head = newHead;
                levels++;
            }

        }

        /**
         * 删除
         * 先查找到要删除的节点
         * 然后逐层进行删除
         */
        public boolean erase(int num) {
            boolean isExist = false;
            Node node = head;
            while (node != null) {
                //1.不断往右遍历
                while (node.right != null && node.right.val < num) {
                    node = node.right;
                }
                //2。判断右节点的值是否等于target
                Node right = node.right;
                if (right != null && right.val == num) {
                    right.down = null;//help GC
                    node.right = right.right;
                    isExist = true;
                }
                //3.否则往下遍历查找
                node = node.down;
            }
            return isExist;
        }

        //打印
        public void printVal() {
            Node node = head;
            Node down = head;
            while (node != null) {
                //从左往右
                while (node != null) {
                    System.out.print(node.val + " * ");
                    node = node.right;
                }
                //从上往下
                down = down.down;
                node = down;
                System.out.println();
            }
            System.out.println("--------level--------" + levels);
        }

        //跳表的每个节点包含数据域，和指针域：right，down
        class Node {
            int val;
            Node right, down;

            public Node(int val) {
                this.val = val;
            }

            public Node(int val, Node right, Node down) {
                this.val = val;
                this.right = right;
                this.down = down;
            }
        }
    }

}
