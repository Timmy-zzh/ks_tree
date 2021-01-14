package com.timmy.lgsf._12devide_and_conquer;

import java.util.HashMap;
import java.util.Map;

public class _02多数元素_169 {

    public static void main(String[] args) {

    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     * 输入：[3,2,3]
     * 输出：3
     *
     * 示例 2：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 1.理解题意
     * -从数组中找出出现次数大于n/2次的元素
     * 2。解题思路
     * -HashMap 存放每个元素出现的次数，然后比较即可
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }
        }
        int maxNum = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxNum = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return maxNum;
    }
}
