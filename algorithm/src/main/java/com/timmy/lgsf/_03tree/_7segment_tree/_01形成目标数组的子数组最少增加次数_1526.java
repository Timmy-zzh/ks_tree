package com.timmy.lgsf._03tree._7segment_tree;

public class _01形成目标数组的子数组最少增加次数_1526 {

    public static void main(String[] args) {
        _01形成目标数组的子数组最少增加次数_1526 demo = new _01形成目标数组的子数组最少增加次数_1526();
        int[] target = {3, 1, 5, 4, 2};
        int result = demo.min(target);
        System.out.println("result:" + result);
    }

    /**
     * 1.理解题意
     * -求从原始数组变化到目标数组需要操作的次数，每次操作只能在区间范围内增加1
     * 2.解题思路
     * -数组元素中前面一个值大于后面的值，其中的差值就是 变化的次数，通过反方向求解
     *
     * @param target
     * @return
     */
    public int min(int[] target) {
        int res = target[0];
        for (int i = 1; i < target.length; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }
        return res;
    }

    /**
     * 给你一个整数数组 target 和一个数组 initial ，initial 数组与 target 
     * 数组有同样的维度，且一开始全部为 0 。
     * 请你返回从 initial 得到  target 的最少操作次数，每次操作需遵循以下规则：
     * 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。
     * 答案保证在 32 位有符号整数以内。
     *
     * 示例 1：
     *
     * 输入：target = [1,2,3,2,1]
     * 输出：3
     * 解释：我们需要至少 3 次操作从 intial 数组得到 target 数组。
     * [0,0,0,0,0] 将下标为 0 到 4 的元素（包含二者）加 1 。
     * [1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。
     * [1,2,2,2,1] 将下表为 2 的元素增加 1 。
     * [1,2,3,2,1] 得到了目标数组。
     * 示例 2：
     *
     * 输入：target = [3,1,1,2]
     * 输出：4
     * 解释：(initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2] (target) 。
     * 示例 3：
     *
     * 输入：target = [3,1,5,4,2]
     * 输出：7
     * 解释：(initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1]
     *                                   -> [3,1,2,2,2] -> [3,1,3,3,2] ->
     *                                   [3,1,4,4,2] -> [3,1,5,4,2] (target)。
     * 示例 4：
     *
     * 输入：target = [1,1,1,1]
     * 输出：1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
