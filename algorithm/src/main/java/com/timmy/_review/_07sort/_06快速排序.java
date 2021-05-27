package com.timmy._review._07sort;

import com.timmy.common.PrintUtils;

/**
 * 正确的方法 + 大量的练习
 * -清晰的目标
 * -高度专注
 * -及时反馈
 * -在舒适区边缘练习
 * 动态监控系：反思节点
 */
public class _06快速排序 {

    public static void main(String[] args) {
        _06快速排序 demo = new _06快速排序();
        int[] nums = {7, 8, 7, 6, 2, 1, 2, 6, 2};
        PrintUtils.print(nums);
        System.out.println("----sort----");
        demo.quickSort(nums);
        PrintUtils.print(nums);
    }

    /**
     * 1。快速排序
     * 2。前序遍历 + 三路切分
     * -前序遍历的解法是，先处理根节点，这里是找到中间节点，左边的数据都是小于的区域；中间是等于；后面是大于数据的区域
     * --中间节点的值是mid值
     * -然后遍历整个区域，找出左右区间，先排序为三个区域，然后在对左右的两个区域进行递归处理
     */
    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int left, int right) {
        System.out.println("left:" + left + " ,right:" + right);
        if (left >= right) {
            return;
        }
        //找到中间元素作为锚点，然后处理该区域[l,r] 为三个区域
        int mid = left + ((right - left) >> 1);
        int povit = nums[mid];  //锚点
        int index = left;
        int li = left;
        int rj = right;

        /**
         * 规则：
         * [0,li] 小于
         * [li,i] 等于
         * [i,rj] 处理区域
         * [rj,N-1] 大于
         */
        while (index <= rj) {
            // 将index下表元素，不断与中位数mid下表元素比较
            if (nums[index] < povit) {
                //case1：小于，li与i 都往后移动
                swapV(nums, li, index);
                li++;
                index++;
            } else if (nums[index] == povit) {
                index++;
            } else {
                //大于区域
                swapV(nums, index, rj);
                rj--;
            }
        }

        //左右区域
        sort(nums, left, li - 1);
//        sort(nums, rj + 1, right);
        sort(nums, index, right);
//        System.out.println("rj+1:" + (rj + 1) + " ,index:" + index);
    }

    /**
     * 数组中元素交换
     */
    private void swapV(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 7,8,7,6,2,1,2,6,2
     */
}
