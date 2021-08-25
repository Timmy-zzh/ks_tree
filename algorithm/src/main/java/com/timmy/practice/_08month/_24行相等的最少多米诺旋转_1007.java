package com.timmy.practice._08month;

public class _24行相等的最少多米诺旋转_1007 {

    public static void main(String[] args) {
        _24行相等的最少多米诺旋转_1007 demo = new _24行相等的最少多米诺旋转_1007();
//        int[] tops = {2, 1, 2, 4, 2, 2};
//        int[] bots = {5, 2, 6, 2, 3, 2};
        int[] tops = {3, 5, 1, 2, 3};
        int[] bots = {3, 6, 3, 3, 4};
        int res = demo.minDominoRotations(tops, bots);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两个数组，数组的元素个数相等，两个数组中同样下标位置的元素可以上下位置交换，
     * --交换后其中一个数组中所有元素相等为最终目标，请要等到相同元素的数组需要交换的最少次数
     * 2。解题思路
     * -以A[0]元素为基准旋转A数组与B数组需要交换的最少次数。
     * -然后以B[0]元素值为基准旋转-- 得到最少需要交换的次数
     * --如果以A[0]为基准的交换没有成功
     */
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int rotate1 = rotate(tops[0], tops, bottoms);
        System.out.println("tops[0]:" + rotate1);

        if (rotate1 != -1 || tops[0] == bottoms[0]) {
            return rotate1;
        } else {
            return rotate(bottoms[0], tops, bottoms);
        }

//        if (rotate1 == -1) {
//            return rotate(bottoms[0], tops, bottoms);
//        }
//        int rotate2 = rotate(bottoms[0], tops, bottoms);
//        System.out.println("bottoms[0]:" + rotate2);
//        if (rotate2 == -1) {
//            return rotate1;
//        }
//        return Math.min(rotate1, rotate2);
    }

    private int rotate(int num, int[] tops, int[] bottoms) {
        int rotateA = 0;
        int rotateB = 0;
        int n = tops.length;

        for (int i = 0; i < n; i++) {
            //两个都不等于num，直接返回-1
            if (tops[i] != num && bottoms[i] != num) {
                return -1;
            }
            if (tops[i] != num && bottoms[i] == num) {
                rotateA++;
            } else if (tops[i] == num && bottoms[i] != num) {
                rotateB++;
            }
        }
        return Math.min(rotateA, rotateB);
    }

    /**
     * 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。
     * （一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
     * 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
     * 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
     * 如果无法做到，返回 -1.
     *
     * 示例 1：
     * 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
     * 输出：2
     * 解释：
     * 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
     * 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
     *
     * 示例 2：
     * 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
     * 输出：-1
     * 解释：
     * 在这种情况下，不可能旋转多米诺牌使一行的值相等。
     *
     * 提示：
     * 1 <= A[i], B[i] <= 6
     * 2 <= A.length == B.length <= 20000
     * 链接：https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row
     */
}
