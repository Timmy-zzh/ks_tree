package com.timmy.practice.sort;

import com.timmy.common.PrintUtils;

/**
 * 计数排序：
 * -遍历数组，找到数组中最大最小的元素，相减得到值为元素值的范围区间，新建一个数组大小为元素值的区间
 * -再次遍历数组，记录每个元素出现的次数，存放在新数组中
 * -遍历新数组找出出现次数大于0的元素，存放在原始数组中
 */
public class 计数排序 {

    public static void main(String[] args) {
        计数排序 demo = new 计数排序();
        int[] nums = {14, 18, 25, 18, 25, 17, 14};
        demo.countSort(nums);
    }

    public void countSort(int[] nums) {
        PrintUtils.print(nums);
        int minV = nums[0];
        int maxV = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxV) {
                maxV = nums[i];
            } else if (nums[i] < minV) {
                minV = nums[i];
            }
        }

        int newLen = maxV - minV + 1;
        int[] newArr = new int[newLen];
        for (int i = 0; i < nums.length; i++) {
            newArr[nums[i] - minV]++;
        }
        PrintUtils.print(newArr);

        int index = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] > 0) {
                for (int j = 0; j < newArr[i]; j++) {
                    nums[index++] = i + minV;
                }
            }
        }
        PrintUtils.print(nums);
    }


}
