package com.timmy.leetcode._202008;

import com.timmy.common.PrintUtils;

import java.util.Arrays;

public class _0828_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0828_practice practice = new _0828_practice();
////        int[] nums = {1, 2, 0};
////        int[] nums = {0, -1, 3, 1};
////        int[] nums = {3, 4, -1, 1};
////        int[] nums = {7, 8, 9, 11, 12};
//        int[] nums = {0, 2, 2, 1, 1};
//        int result = practice.firstMissingPositive(nums);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");

        System.out.println("-----------------------------------------");
        _0828_practice practice = new _0828_practice();
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = {2, 0, 2};
        int result = practice.trap(height);
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，
     * 可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     * <p>
     * 思路：
     * 遍历，双指针，查找快指针比满指针 大于等于的元素，然后减去区间的和
     */
    public int trap(int[] height) {
        int result = 0;
        if (height == null || height.length < 3) {
            return 0;
        }
        int start = 0, end = 1;
        while (start < height.length && end < height.length) {
            if (height[start] == 0) {
                start++;
                end = start + 1;
            } else {
                //查找 end元素大于等于start元素的
                while (end < height.length) {
                    if (end == height.length - 1) {
                        if (height[start] <= height[end]) {
                            if (end - start > 1) {
                                for (int j = start + 1; j < end; j++) {
                                    result += height[start] - height[j];
                                }
                            }
                            start = end;
                            end++;
                            break;
                        } else {
                            if (end - start > 1) {
                                //找到start后最大的元素
                                int maxIndex = start + 1;
                                int maxVaule = height[start + 1];
                                for (int i = start + 2; i <= end; i++) {
                                    if (height[i] > maxVaule) {
                                        maxVaule = height[i];
                                        maxIndex = i;
                                    }
                                }
                                if (maxIndex - start > 1) {
                                    for (int j = start + 1; j < maxIndex; j++) {
                                        result += height[maxIndex] - height[j];
                                    }
                                    start = maxIndex;
                                    end = start + 1;
                                } else {
                                    start++;
                                    end = start + 1;
                                }
                            } else {
                                start++;
                                end++;
                                break;
                            }
                        }
                    } else if (height[start] > height[end]) {
                        end++;
                    } else {
                        //找到凹槽装水了,计算出来
                        if (end - start > 1) {
                            for (int j = start + 1; j < end; j++) {
                                result += height[start] - height[j];
                            }
                        }
                        start = end;
                        end++;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 41. 缺失的第一个正数
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     * <p>
     * 输入: [1,2,0]
     * 输出: 3
     * <p>
     * 输入: [3,4,-1,1]
     * 输出: 2
     * <p>
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * <p>
     * 思路：
     * 1。先排序
     * 2。遍历，--（双指针方）
     * 快慢指针
     * 考察三种情况： 数组左边区域，数组区域判断，数组右边区域 （还需要判断与元素与0的关系）
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        Arrays.sort(nums);
        PrintUtils.print(nums);
        if (nums[0] > 1 || nums[nums.length - 1] <= 0) {
            return 1;
        }
        if (nums.length == 1) {
            if (nums[0] == 1) {
                return 2;
            } else {
                return 1;
            }
        }

        //数组数量>=2
        int start = 0, end = 1;
        while (end < nums.length) {
            if (nums[end] <= 0) {
                start++;
                end++;
            } else if (nums[start] < 0 && nums[end] >= 0) { //一个大于0，一个小于0
                if (nums[end] > 1) {
                    return 1;
                } else {
                    start++;
                    end++;
                }
            } else {       //都大于0
                if (nums[start] == nums[end] || nums[start] + 1 == nums[end]) {
                    start++;
                    end++;
                } else {
                    return nums[start] + 1;
                }
            }
        }
        return nums[nums.length - 1] + 1;
    }

}
