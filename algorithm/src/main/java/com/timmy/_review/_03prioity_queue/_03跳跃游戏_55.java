package com.timmy._review._03prioity_queue;

public class _03跳跃游戏_55 {

    public static void main(String[] args) {
        _03跳跃游戏_55 demo = new _03跳跃游戏_55();
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {3, 2, 1, 0, 4};
        boolean res = demo.canJump(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组元素表示当前位置可以跳跃的最大长度，判断从第一个元素开始跳跃是否能条约到最后一个元素
     * 2。模拟运行
     * 贪心算法
     * -从第一个元素开始跳跃，因为下一个跳跃位置有多个，可以选择多个跳跃方式，并判断每一个跳跃位置是否可以到达最后一个元素
     */
    public boolean canJump(int[] nums) {
        return canJump(0, nums);
    }

    private boolean canJump(int i, int[] nums) {
        if (i >= nums.length) {
            return false;
        }
        if (i == nums.length - 1) {
            return true;
        }
        //判断当前位置i，可以条约到的下一个位置
        int maxJump = nums[i];
        for (int jump = maxJump; jump >= 1; jump--) {
            if (canJump(i + jump, nums)) {
                return true;
            }
        }
        return false;
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
     *
     * 链接：https://leetcode-cn.com/problems/jump-game
     */
}
