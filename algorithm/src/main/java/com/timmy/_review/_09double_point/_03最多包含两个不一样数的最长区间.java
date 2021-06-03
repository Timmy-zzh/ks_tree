package com.timmy._review._09double_point;

import com.timmy.common.PrintUtils;

import java.util.HashMap;

public class _03最多包含两个不一样数的最长区间 {

    public static void main(String[] args) {
        _03最多包含两个不一样数的最长区间 demo = new _03最多包含两个不一样数的最长区间();
//        int[] nums = {1, 2, 1, 2, 3};
//        int[] nums = {0,1,2,2};
        int[] nums = {1, 2, 3, 2, 2};
        PrintUtils.print(nums);
        int res = demo.loggest(nums);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，求一个最长区间，该区间中的元素最多包含两个不一样的数
     * 2。解题思路
     * -和02题一样的考察点，同样使用双指针解法，记录每个元素出现的次数，使用Map(ele,count） 记录，
     * -左侧指针left从0开始，右侧指针固定向后移动，右侧指针移动前后需要保证检索区间中的元素key值数量不能超过2，
     * --如果超过2则需要不断移动左侧指针，直到满足区间中的元素最多包含两个不一样的数
     */
    public int loggest(int[] nums) {
        int left = -1;
        int res = 0;
        Counter counter = new Counter();

        for (int right = 0; right < nums.length; right++) {
            counter.add(nums[right], 1);

            while (counter.size() > 2) {        // 区间内元素种类超过2，不断从左侧指针元素减少
                counter.add(nums[++left], -1);
            }
            res = Math.max(res, right - left);
        }

        return res;
    }

    public class Counter extends HashMap<Integer, Integer> {

        //求该元素对应存在的个数
        public int getCount(int ele) {
            return containsKey(ele) ? get(ele) : 0;
        }

        // 对该元素出现的次数，进行追加
        public void add(int ele, int incre) {
            put(ele, getCount(ele) + incre);
            if (getCount(ele) == 0) {
                remove(ele);
            }
        }
    }

    /**
     * 练习题 3：给定一个数组 A[]，请你找到一个最长区间，这个区间里面最多包含两个不一样的数。
     * 输入：A = [1, 2, 1, 2, 3]
     * 输出：4
     * 解释：区间 [1, 2, 1, 2] 里面只有两个数，并且是最长区间。
     */
}
