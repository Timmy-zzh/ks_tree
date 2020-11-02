package com.timmy.leetcode._202008;

public class _0808_practice {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxArea(height);
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");


    }

    /**
     * 11. 盛最多水的容器
     * <p>
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 步骤：
     * 1.双for循环查找最大的面积值
     */
    public static int maxArea(int[] height) {
        int length = height.length;
        int max = Integer.MIN_VALUE;
        int area;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                area = (j - i) * Math.min(height[i], height[j]);
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }
}
