package com.timmy.lgsf._05backtrack_dp._3backtrack_bit;

public class _01火柴棍摆正方形_473 {

    public static void main(String[] args) {
        _01火柴棍摆正方形_473 demo = new _01火柴棍摆正方形_473();
        int[] nums = {1, 1, 2, 2, 2};
        boolean res = demo.makesquare(nums);
        System.out.println("res:" + res);
    }

    /**
     * TODO:解法2 - 位运算
     * 1。理解题意
     * -输入一个大小为n的数组，表示有n根火柴，数组元素表示这根火柴的长度，将所有火柴使用到，可以摆放成一个正方形
     * 2。解题思路-回溯算法
     * -所有火柴摆放成一个正方形，说明所有火柴进行组合可以生成一个数组为4的且每个元素都相等
     * -创建一个大小为4的数组，
     * --遍历每根的火柴，这根火柴可以存放在正方形的各个边上，进行合并，最后要求合并结果四条边都相等
     * -回溯，每条边上添加了这根火柴； 匹配完后，需要减少这根火柴，使他可以回归到未被使用前状态，接着在下一条边中使用
     * 3。边界与细节问题
     * -火柴根数必须大于3
     * -所有火柴的长度可以被4整除
     * -减枝处理：每根火柴长度，是所有火柴长度的1/4
     * -所有火柴进行由长到短进行排序，可以减少遍历次数
     *
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 4 != 0) {
            return false;
        }

        int[] lengths = new int[4];
        return backtrack(nums, lengths, sum / 4, 0);
    }

    /**
     * 遍历每根火柴，存放在不同边上
     *
     * @param nums    原始火柴数组
     * @param lengths 存放4条边，火柴的数组
     * @param preLen  每条边火柴的最长长度
     * @param index   原始火柴数组中当前处理到的第几条火柴
     * @return
     */
    private boolean backtrack(int[] nums, int[] lengths, int preLen, int index) {
        if (index == nums.length) {
            return lengths[0] == lengths[1] && lengths[1] == lengths[2] && lengths[2] == lengths[3];
        }
        int item = nums[index];
        for (int j = 0; j < lengths.length; j++) {
            if (lengths[j] + item <= preLen) {
                lengths[j] += item;
                boolean backtrack = backtrack(nums, lengths, preLen, index + 1);
                if (backtrack) {
                    return true;
                }
                lengths[j] -= item;
            }
        }
        return false;
    }

    /**
     * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
     * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
     * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
     *
     * 示例 1:
     * 输入: [1,1,2,2,2]
     * 输出: true
     * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
     *
     * 示例 2:
     * 输入: [3,3,3,3,4]
     * 输出: false
     *
     * 解释: 不能用所有火柴拼成一个正方形。
     * 注意:
     * 给定的火柴长度和在 0 到 10^9之间。
     * 火柴数组的长度不超过15。
     *
     * 链接：https://leetcode-cn.com/problems/matchsticks-to-square
     */
}
