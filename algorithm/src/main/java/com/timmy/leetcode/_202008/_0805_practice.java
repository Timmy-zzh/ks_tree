package com.timmy.leetcode._202008;

import com.timmy.common.ListNode;
import com.timmy.common.PrintUtils;

public class _0805_practice {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        int[] result = twoSum(nums, target);
//        PrintUtils.print(result);
//        System.out.println("-----------------------------------------");

//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(7);

//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);

//        ListNode listNode = addTwoNumbers(l1, l2);
//        PrintUtils.print(listNode);
//        System.out.println("-----------------------------------------");

//        int length = lengthOfLongestSubstring("abcabcbb");
//        int length = lengthOfLongestSubstring("aa");
//        int length = lengthOfLongestSubstring("au");
//        int length = lengthOfLongestSubstring("bbbbb");
        int length = lengthOfLongestSubstring("aab");
        System.out.println("length:" + length);
        System.out.println("-----------------------------------------");

    }

    /**
     * 3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxV = Integer.MIN_VALUE;
        int start = 0, end = 0;
        String tempStr;
        int[] lengths = new int[s.length()];//保存以下标开始无重复字串的长度
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            start = i;
            for (int j = i + 1; j < chars.length; j++) {
                end = j;
                tempStr = s.substring(start, end);
                if (tempStr.contains(String.valueOf(chars[j]))) {
                    lengths[i] = j - i;
                    break;
                }
            }
            if (end == chars.length - 1) {
                if (lengths[i] == 0) {
                    lengths[i] = chars.length - i;
                } else {
                    lengths[i] = end - start;
                }
            }
        }
        PrintUtils.print(lengths);
        for (int i = 0; i < lengths.length; i++) {
            if (maxV < lengths[i]) {
                maxV = lengths[i];
            }
        }
        return maxV;
    }

    /**
     * 2. 两数相加
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 243+567 = 7001
     * -> 342+765 = 1107
     * <p>
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dst = null;
        ListNode head = null;
        int temp = 0;
        int add;
        while (l1 != null && l2 != null) {
            add = l1.value + l2.value + temp;
            if (add >= 10) {
                temp = add / 10;
                add = add % 10;
            } else {
                temp = 0;
            }
            ListNode newNode = new ListNode(add);
            if (dst == null) {
                dst = newNode;
                head = newNode;
            } else {
                dst.next = newNode;
                dst = dst.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 == null && temp > 0) {
            dst.next = new ListNode(temp);
            return head;
        }

        if (l1 != null) {
            while (l1 != null) {
                add = l1.value + temp;
                if (add >= 10) {
                    temp = add / 10;
                    add = add % 10;
                } else {
                    temp = 0;
                }
                dst.next = new ListNode(add);
                dst = dst.next;
                l1 = l1.next;
            }

            if (temp > 0) {
                dst.next = new ListNode(temp);
                return head;
            }
        }

        if (l2 != null) {
            while (l2 != null) {
                add = l2.value + temp;
                if (add >= 10) {
                    temp = add / 10;
                    add = add % 10;
                } else {
                    temp = 0;
                }
                dst.next = new ListNode(add);
                dst = dst.next;
                l2 = l2.next;
            }
            if (temp > 0) {
                dst.next = new ListNode(temp);
                return head;
            }
        }
        return head;
    }


    /**
     * 1.两数之和
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

}
