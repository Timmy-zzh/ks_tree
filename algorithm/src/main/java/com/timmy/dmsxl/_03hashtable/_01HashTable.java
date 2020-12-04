package com.timmy.dmsxl._03hashtable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * HashTable 哈希表（也叫散列表）
 */
public class _01HashTable {

    public static void main(String[] args) {
        _01HashTable hashTable = new _01HashTable();
//        boolean anagram = hashTable.isAnagram("anagram", "nagaram");
//        boolean anagram = hashTable.isAnagram("rat", "car");
//        boolean anagram = hashTable.isAnagram("rat", "tarr");

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] intersection = hashTable.intersection(nums1, nums2);

//        boolean anagram = hashTable.isHappy(19);
//        System.out.println("-----result:" + anagram);
    }

    /**
     * 第242题. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * --两个字符串中所包含的字符相同，所处位置可以不同
     * <p>
     * 思路：
     * 分别记录字符串s和t中，每个字符出现的次数，然后进行比较
     * 固定大小可以使用数组作为容器
     * （数组就是简单的哈希表，但是数组的大小是受限的）
     */
    private boolean isAnagram(String s, String t) {
        //26个字母
        int[] nums = new int[26];
        char[] chars1 = s.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            nums[chars1[i] - 'a']++;
        }
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            nums[chars2[i] - 'a']--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第349题. 两个数组的交集
     * 题意：给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 思路：求两个数组的交集，遍历数组1，判断遍历的元素在数组2中是否存在
     * 存在的话放到集合中，集合使用set容器，保存值不会重复
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> result_set = new HashSet<>();
        Set<Integer> nums1_set = new HashSet<>();
        for (int i : nums1) {
            nums1_set.add(i);
        }
//        System.out.println("nums1_set:" + nums1_set.toString());
        for (int j : nums2) {
            if (nums1_set.contains(j) && !result_set.contains(j)) {
                result_set.add(j);
            }
        }
        int[] result_arr = new int[result_set.size()];
        int i = 0;
        Iterator<Integer> iterator = result_set.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            result_arr[i++] = next;
        }
//        PrintUtils.print(result_arr);
        return result_arr;
    }


    /**
     * 第202题. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，
     * 那么这个数就是快乐数。
     * <p>
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * <p>
     * 思路：不断对原始数据进行处理，如果处理后出现重复数据，
     * 则这个数组回进入循环出不来，因此也可以判断该数值不是快乐数
     */
    private boolean isHappy(int n) {
        int nums = getNums(n);
        HashSet<Integer> hashSet = new HashSet<>();
        while (true) {
            System.out.println("nums:" + nums);
            if (nums == 1) {
                return true;
            }
            if (hashSet.contains(nums)) {
                return false;
            } else {
                hashSet.add(nums);
            }
            nums = getNums(nums);
        }
    }

    //不断获取个位数
    private int getNums(int n) {
        int result = 0;
        while (n > 0) {
            result += (n % 10) * (n % 10);
            n = n / 10;
        }
        return result;
    }


}
