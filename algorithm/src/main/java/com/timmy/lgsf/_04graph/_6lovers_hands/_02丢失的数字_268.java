package com.timmy.lgsf._04graph._6lovers_hands;

import java.util.HashSet;

public class _02丢失的数字_268 {

    public static void main(String[] args) {
        _02丢失的数字_268 demo = new _02丢失的数字_268();
        int[] nums = {3, 0, 1};
        int res = demo.missingNumber(nums);
        System.out.println("res:" + res);
    }

    /**
     * 解法2：遍历【0，n】的n+1个元素，不断加上其中的元素，并且不断的减少数组中的元素，最后的和就是结果
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            sum -= nums[i - 1];
        }
        return sum;
    }

    /**
     * 1.理解题意
     * -输入一个数组nums，数组的个数为n，则要求数组中的元素为[0,n]中n+1个元素中的一个元素，其中必定有一个元素没有出现，找出来
     * 2。解题思路
     * 哈希表判断
     * -遍历[0-n]，并将元素i保存到hashSet中
     * -遍历给到的数组nums，判断元素i是否在hasnSet中出现，如果没有出现，则是所有结果
     *
     * @param nums
     * @return
     */
    public int missingNumber_v1(int[] nums) {
        int n = nums.length;
        HashSet<Integer> hashSet = new HashSet<>();
        //1.先将数组中的元素保存到哈希表中
        for (int i = 0; i < n; i++) {
            hashSet.add(nums[i]);
        }

        //2.遍历【0，n】的元素，看其中的那个元素在哈希表中没有存在
        for (int i = 0; i <= n; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     *
     * 进阶：
     * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
     *  
     * 示例 1：
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     *
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     *
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     *
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     *  
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/missing-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
