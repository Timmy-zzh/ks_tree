package com.timmy.leetcode._202008;

import java.util.ArrayList;
import java.util.List;

public class _0818_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0818_practice practice = new _0818_practice();
//        List<String> result = practice.generateParenthesis(3);
//        System.out.println("result:" + result.toString());
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        _0818_practice practice = new _0818_practice();
//        ListNode[] lists = new ListNode[3];
//        ListNode node11 = new ListNode(1);
//        ListNode node12 = new ListNode(4);
//        ListNode node13 = new ListNode(5);
//        node11.next = node12;
//        node12.next = node13;
//        lists[0] = node11;
//        ListNode node21 = new ListNode(1);
//        ListNode node22 = new ListNode(3);
//        ListNode node23 = new ListNode(4);
//        node21.next = node22;
//        node22.next = node23;
//        lists[1] = node21;
//        ListNode node31 = new ListNode(2);
//        ListNode node32 = new ListNode(6);
//        node31.next = node32;
//        lists[2] = node31;
//
//        for (int i = 0; i < lists.length; i++) {
//            PrintUtils.print(lists[i]);
//        }
//        ListNode result = practice.mergeKLists(lists);
//        PrintUtils.print(result);
//        System.out.println("-----------------------------------------");

        System.out.println("-----------------------------------------");
        _0818_practice practice = new _0818_practice();
//        int result = practice.strStr("hello", "ll");
//        int result = practice.strStr("aaaaa", "bba");
//        int result = practice.strStr("mississippi", "mississippi");
//        int result = practice.strStr("mississippi", "issipi");
        int result = practice.strStr("ppi", "pi");
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");

    }

    /**
     * 28. 实现 strStr()
     * 实现 strStr() 函数。
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * <p>
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * <p>
     * 思路：遍历，判断
     * 好后缀规则
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        if (haystack.length() == needle.length() && haystack.equals(needle)) {
            return 0;
        }
        char[] hayArr = haystack.toCharArray();
        char[] needArr = needle.toCharArray();
        for (int i = 0; i < (hayArr.length - needArr.length + 1); i++) {
            int j = 0;
            for (; j < needArr.length; j++) {
                if (hayArr[i + j] != needArr[j]) {
                    break;
                }
            }
            if (j == needArr.length) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 23. 合并K个升序链表
     * <p>
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * <p>
     * 思路：每次都从链表数组中取出一个最小值的节点，然后删除头节点，直到数组中所有的链表都为空
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode result = null, head = null;
        int minValue;
        while (!isEmpty(lists)) {
            minValue = getMinValue(lists);
            if (head == null) {
                head = new ListNode(minValue);
                result = head;
            } else {
                head.next = new ListNode(minValue);
                head = head.next;
            }
        }
        return result;
    }

    private int getMinValue(ListNode[] lists) {
        int min = Integer.MAX_VALUE;
        int index = 0;  //表示数组第几个元素 链表
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (lists[i].value < min) {
                    min = lists[i].value;
                    index = i;
                }
            }
        }
        //删除数组中第index 个链表的第一个
        ListNode indexNode = lists[index];
        if (indexNode.next == null) {
            indexNode = null;
        } else {
            indexNode = indexNode.next;
        }
        lists[index] = indexNode;
        return min;
    }

    //判断数组链表是否为空
    private boolean isEmpty(ListNode[] lists) {
        for (ListNode list : lists) {
            if (list != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 22. 括号生成
     * <p>
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 思路：输入n为括号的对数，则有n个左括号，n个右括号，2n个括号组合后进行判断留下有效的括号组合返回
     * 难点：怎么判断组合的括号是有效的？ -> 分别判断左右括号的数量个数
     * -->回溯法递归所有的组合，然后进行判断
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        generatePar(result, "", 0, 0, n);
        return result;
    }

    private void generatePar(List<String> result, String sb, int left, int right, int n) {
        if (left == n && right == n) {
            System.out.println(sb);
            if (valid(sb)) {
                result.add(sb);
            }
        }
        if (left < n) {
            generatePar(result, sb + "(", left + 1, right, n);
        }
        if (right < n) {
            generatePar(result, sb + ")", left, right + 1, n);
        }
    }

    /**
     * 判断括号的有效性
     * 左括号==右括号个数
     * 遍历过程中： 左括号数 >= 右括号数，否则无效
     */
    private boolean valid(String sb) {
        char[] chars = sb.toCharArray();
        int blance = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                blance++;
            } else {
                blance--;
            }
            if (blance < 0) {
                return false;
            }
        }
        return blance == 0;
    }

}
