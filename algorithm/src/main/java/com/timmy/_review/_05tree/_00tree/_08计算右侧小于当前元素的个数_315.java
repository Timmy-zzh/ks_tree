package com.timmy._review._05tree._00tree;

import com.timmy.common.PrintUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 线段树复习：
 */
public class _08计算右侧小于当前元素的个数_315 {

    public static void main(String[] args) {
        _08计算右侧小于当前元素的个数_315 demo = new _08计算右侧小于当前元素的个数_315();
        int[] nums ={5,2,6,1};
        List<Integer> res = demo.countSmaller(nums);
        PrintUtils.print(res);
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        _08SegmentTree segmentTree = new _08SegmentTree(nums);
        segmentTree.print();
        int query = segmentTree.query(1, 2);
        System.out.println("query:"+query);
        segmentTree.update(3,10);
        segmentTree.print();

        return res;
    }

    /**
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质：
     * counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     *
     * 示例：
     * 输入：nums = [5,2,6,1]
     * 输出：[2,1,1,0]
     * 解释：
     * 5 的右侧有 2 个更小的元素 (2 和 1)
     * 2 的右侧仅有 1 个更小的元素 (1)
     * 6 的右侧有 1 个更小的元素 (1)
     * 1 的右侧有 0 个更小的元素
     *  
     * 提示：
     * 0 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     *
     * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
     */
}
