package com.timmy.practice._05month;

class _xx接雨水_42 {

    public static void main(String[] args) {
        System.out.println("---_04接雨水_42--");
    }

    public int trap(int[] height) {
        return 0;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * <p>
     * 示例 2：
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *  
     * 提示：
     * n == height.length
     * 0 <= n <= 3 * 104
     * 0 <= height[i] <= 105
     * <p>
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     */


    public int trap_old(int[] height) {
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
}
