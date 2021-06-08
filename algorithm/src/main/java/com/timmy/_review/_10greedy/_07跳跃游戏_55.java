package com.timmy._review._10greedy;

import com.timmy.common.PrintUtils;

/**
 * TODO 头晕，数据量太多，脑容量还不够
 */
public class _07跳跃游戏_55 {

    public static void main(String[] args) {
        _07跳跃游戏_55 demo = new _07跳跃游戏_55();
        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {3, 2, 1, 0, 4};
        PrintUtils.print(nums);
        boolean res = demo.canJump(nums);
        System.out.println("res:" + res);
    }

    /**
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        return false;
    }

    public boolean canJump_v2(int[] nums) {
        int jumpRange = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= jumpRange) {
                jumpRange = Math.max(jumpRange, i + nums[i]);
                if (jumpRange >= nums.length - 1) {         //经过当前位置跳跃后可以达到数组尾部
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中的元素表示从该位置开始可以跳跃的最大长度，初始位置在数组第一个下标，判断是否可以跳跃到数组的最后一个元素位置
     * 2。解题思路
     * -设置一个变量标示可以条约的最大范围，然后遍历数组元素，判断以当前位置覆盖的跳跃范围是否超过之前的最大跳跃覆盖范围
     * --如果超过，则更新之前的最大跳跃范围
     * -如果经过本次跳跃后的最大位置还是在当前位置，说明不能往后跳跃了，直接返回false
     */
    public boolean canJump_v1(int[] nums) {
        int jumpRange = 0;

        for (int i = 0; i < nums.length; i++) {
            jumpRange = Math.max(jumpRange, i + nums[i]);
            if (jumpRange >= nums.length - 1) {         //经过当前位置跳跃后可以达到数组尾部
                return true;
            } else if (jumpRange == i) {                //经过当前位置跳跃还是没有进步，则不会往后跳跃了
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * 示例 1：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * 示例 2：
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     *
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * 0 <= nums[i] <= 105
     * 链接：https://leetcode-cn.com/problems/jump-game
     */
}
