package com.timmy.lgsf._04graph._28lovers_hands;

public class _01情侣牵手_765 {

    public static void main(String[] args) {
        _01情侣牵手_765 demo = new _01情侣牵手_765();
//        int[] row = {0, 2, 1, 3};
//        int[] row={3, 2, 0, 1};
        int[] row = {6, 3, 0, 2, 1, 4, 5, 7};
//        int[] row = {6, 1, 0, 3, 2, 7, 5, 4};
        int result = demo.minSwapsCouples(row);
        System.out.println("result:" + result);

//        int result = demo.minSwapsCouplesc_v1(row);
//        System.out.println("---result:" + result);
    }

    /**
     * 1.
     * 2.解题思路2：
     * -还是先找到第i位元素，查找第i+1对应的元素，之前的方式是遍历后面所有的元素然后判断
     * --现在可以先将元素的坐标先进行保存，然后直接获取即可
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int swapCount = 0;

        int[] parent = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            //保存每个元素的下标值
            parent[row[i]] = i;
        }

        for (int i = 0; i < row.length; i += 2) {
            //1。根据第i个值，求第i+1的值是否满足情侣关系
            int value = row[i];
            int nextV = value % 2 == 0 ? value + 1 : value - 1;
            if (nextV == row[i + 1]) {//满足情侣关系
                continue;
            }

            //2.因为知道要求的i+1元素值是nextV，根据parent已保存的值，找到nextV元素的下表，然后进行交换
            int index = parent[nextV];
            row[index] = row[i + 1];
            row[i + 1] = nextV;

            //3.更新parent保存的下表
            parent[row[index]] = index;

            swapCount++;
        }
        return swapCount;
    }

    /**
     * 1.理解题意
     * -输入二维数组，表示有N对情侣，坐在2N个座位上，现需要调整座位使得每对情侣并肩坐在一起，求最小交换座位次数
     * 2。解题思路-暴力解法
     * -遍历2N个座位上的情侣编号，第i个和第i+1元素值，需要是情侣关系如（6，7）或（7，6）否则第i+1个元素的值需要交换
     * --假如第i个元素是6，则需要从i+2后面的元素中查找出元素值为7的元素，然后进行交换，交换次数既是所求
     * 3。边界与细节问题
     * -因为情侣是成对出现的，且情侣的元素值是相邻的，
     * -所以根据第i个元素值的奇偶性，求出第i+1位置的元素值是多少，然后从后面的元素中查找到该值
     * -每次遍历步长为2
     *
     * @param row
     * @return
     */
    public int minSwapsCouplesc_v1(int[] row) {
        int swapCount = 0;

        for (int i = 0; i < row.length; i += 2) {
            //1。根据第i个值，求第i+1的值是否满足情侣关系
            int value = row[i];
            int nextV = value % 2 == 0 ? value + 1 : value - 1;
            if (nextV == row[i + 1]) {//满足情况关系
                continue;
            }

            //2.不满足情况关系，从后面的元素中查找并交换
            for (int j = i + 2; j < row.length; j++) {
                if (row[j] == nextV) {
                    row[j] = row[i + 1];
                    row[i + 1] = nextV;
                    break;
                }
            }
            swapCount++;
        }
        return swapCount;
    }

    /**
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，
     * 以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
     *
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，
     * 以此类推，最后一对是 (2N-2, 2N-1)。
     *
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     *
     * 示例 1:
     *
     * 输入: row = [0, 2, 1, 3]
     * 输出: 1
     * 解释: 我们只需要交换row[1]和row[2]的位置即可。
     * 示例 2:
     *
     * 输入: row = [3, 2, 0, 1]
     * 输出: 0
     * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
     *
     * 说明:
     * len(row) 是偶数且数值在 [4, 60]范围内。
     * 可以保证row 是序列 0...len(row)-1 的一个全排列。
     *
     * 链接：https://leetcode-cn.com/problems/couples-holding-hands
     */
}
