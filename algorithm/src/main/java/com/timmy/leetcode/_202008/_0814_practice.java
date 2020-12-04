package com.timmy.leetcode._202008;

import com.timmy.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0814_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0814_practice practice = new _0814_practice();
////        int[] nums = {-1, 2, 1, -4};
////        int[] nums = {0, 1, 2};
////        int[] nums = {0, 0, 0};
//        int[] nums = {-1, 0, 1, 1, 55};
//        int target = 3;
//        int result = practice.threeSumClosest(nums, target);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        _0814_practice practice = new _0814_practice();
//        ListNode head = new ListNode(1);
//        ListNode head2 = new ListNode(2);
//        ListNode head3 = new ListNode(3);
//        ListNode head4 = new ListNode(4);
//        ListNode head5 = new ListNode(5);
//        head.next = head2;
//        head2.next = head3;
//        head3.next = head4;
//        head4.next = head5;
////        while (head != null) {
////            System.out.print(head.val + ", ");
////            head = head.next;
////        }
//
//        ListNode result = practice.removeNthFromEnd(head, 2);
//        while (result != null) {
//            System.out.print(result.val + ", ");
//            result = result.next;
//        }
//        System.out.println();
//        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        _0814_practice practice = new _0814_practice();
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l2.next = l21;
        l21.next = l23;

//        while (l1 != null) {
//            System.out.print(l1.val + ", ");
//            l1 = l1.next;
//        }
//        System.out.println();
//        while (l2 != null) {
//            System.out.print(l2.val + ", ");
//            l2 = l2.next;
//        }
//        System.out.println();

        ListNode result = practice.mergeTwoLists(l1, l2);
        while (result != null) {
            System.out.print(result.value + ", ");
            result = result.next;
        }
        System.out.println();
        System.out.println("-----------------------------------------");
    }

    /**
     * 21. 合并两个有序链表
     * <p>
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * 思路： 分别从两个链表中取节点，然后进行比较，根据大小进行从新排序
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode result = node;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                node.next = new ListNode(l1.value);
                node = node.next;
                l1 = l1.next;
            } else {
                node.next = new ListNode(l2.value);
                node = node.next;
                l2 = l2.next;
            }
        }
        while (l1!=null){
            node.next = new ListNode(l1.value);
            node = node.next;
            l1 = l1.next;
        }
        while (l2!=null){
            node.next = new ListNode(l2.value);
            node = node.next;
            l2 = l2.next;
        }
        return result.next;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * <p>
     * 思路：创建两个节点指针，一前一后，相差n个位置，当前一个节点到达末尾时，后一个节点正好在需要删除的位置，
     * 然后进行链表删除操作
     * 链表操作：找到节点位置-》然后操作
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode temp = head;
        int k = 0;
        while (temp != null) {
            k++;
            temp = temp.next;
        }
        if (k == n) {
            return head.next;
        }
        ListNode preNode = head;
        ListNode lastNode = head;
        for (int i = 0; i < n + 1; i++) {
            preNode = preNode.next;
        }
        while (preNode != null) {
            lastNode = lastNode.next;
            preNode = preNode.next;
        }
        lastNode.next = lastNode.next.next;

        return head;
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * 思路：遍历字符串，找到数字对应的字母，然后多层循环遍历产生多种组合
     * 中途不断替换
     * 思路2：递归，回溯
     */
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, String> map = new HashMap<>(8);
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> result = new ArrayList<>();
        result.add("");
        char[] chars = digits.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String letters = map.get(chars[i]);
            List<String> tempList = new ArrayList<>();
            for (String str : result) {
                for (int j = 0; j < letters.length(); j++) {
                    char c = letters.charAt(j);
                    tempList.add(str + c);
                }
            }
            result = tempList;
        }
        return result;
    }

    /**
     * 16. 最接近的三数之和
     * <p>
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
     * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     *  输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * <p>
     * [0,1,2]
     * 0
     * <p>
     * [-1,0,1,1,55]
     * 3
     * <p>
     * 思路：采用求三数之和的方法（排序+双指针）
     * 最接近的三数之和，为Math.abs()绝对值大小比较
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);

        int best = 10000;
        int sum;
        int start, end;
        for (int i = 0; i < nums.length - 2; i++) {
            //过来相同的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                System.out.println("i:" + i + " ,start:" + start + " ,end:" + end);
                sum = nums[i] + nums[start] + nums[end];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum > target) {
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    end--;
                } else {
                    //左右指针移动
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    start++;
                }
            }
        }
        return best;
    }
}
