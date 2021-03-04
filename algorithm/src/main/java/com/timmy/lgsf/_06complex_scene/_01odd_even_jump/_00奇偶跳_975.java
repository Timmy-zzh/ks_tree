package com.timmy.lgsf._06complex_scene._01odd_even_jump;

import com.timmy.common.PrintUtils;

public class _00奇偶跳_975 {

    public static void main(String[] args) {
        _00奇偶跳_975 demo = new _00奇偶跳_975();
        int[] arr = {10, 13, 12, 14, 15};
//        int[] arr = {5, 1, 3, 4, 2};
//        int[] arr = {2, 3, 1, 1, 4};
        PrintUtils.print(arr);
        int res = demo.oddEvenJump(arr);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2。解题思路：动态规划
     * 2。1。根据上面的暴力解法，可以发现在跳跃的过程中存在很多重复的计算，例如：
     * --从第0个位置跳到第2个位置，然后从第2个位置计算跳转到后面位置，...  ; 然后到了第2个位置，还要重复计算下一个跳转位置
     * 2。2。动态规划解法步骤：
     * -原问题拆分为子问题
     * -状态转移方程
     * 2。3。
     * -dp[i][0/1] 代表元素第i个位置，0/1（奇偶跳）是否可以到达跳转到数组最后位置（也就是是否是好索引）
     * -从后往前遍历
     * 3.
     * 4。时间复杂度： O(n^2)
     *
     * @param arr
     * @return
     */
    public int oddEvenJump(int[] arr) {
        int N = arr.length;
        int[][] nextPosArr = new int[N][2];
        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            //奇数跳
            int nextPos = findNextPos(arr, i, true);
            if (nextPos == N - 1) {
                nextPosArr[i][0] = nextPos;
            } else if (nextPos != -1) { //跳转不了
                // i的奇数跳跳转的下个位置j，是下一个j的位置的偶数跳
                nextPosArr[i][0] = nextPosArr[nextPos][1];
            }
            if (nextPosArr[i][0] == N - 1) {
                count++;
            }
            //偶数跳
            nextPos = findNextPos(arr, i, false);
            if (nextPos == N - 1) {
                nextPosArr[i][1] = nextPos;
            } else if (nextPos != -1) { //跳转不了
                nextPosArr[i][1] = nextPosArr[nextPos][0];
            }
        }
        //判断有几个元素可以跳转到最后
        PrintUtils.print(nextPosArr);
        return count;
    }

//    public int oddEvenJump(int[] arr) {
//        int N = arr.length;
//        int[][] nextPosArr = new int[N][2];
//        int count = 0;
//        for (int i = N - 1; i >= 0; i--) {
//            //奇数跳
//            int nextPos = findNextPos(arr, i, true);
//            System.out.println("i:" + i + " ,odd nextPos:" + nextPos);
//            if (nextPos == N - 1) {
//                nextPosArr[i][0] = nextPos;
//            } else if (nextPos == -1) { //跳转不了
//                nextPosArr[i][0] = -1;
//            } else {
//                // i的奇数跳跳转的下个位置j，是下一个j的位置的偶数跳
//                nextPosArr[i][0] = nextPosArr[nextPos][1];
//            }
//
//            //偶数跳
//            nextPos = findNextPos(arr, i, false);
//            System.out.println("i:" + i + " ,odd false nextPos:" + nextPos);
//            if (nextPos == N - 1) {
//                nextPosArr[i][1] = nextPos;
//            } else if (nextPos == -1) { //跳转不了
//                nextPosArr[i][1] = -1;
//            } else {
//                nextPosArr[i][1] = nextPosArr[nextPos][0];
//            }
//        }
//
//        //判断有几个元素可以跳转到最后
//        for (int i = 0; i < N; i++) {
//            if (nextPosArr[i][0] == N - 1) {
//                count++;
//            }
//        }
//        PrintUtils.print(nextPosArr);
//        return count;
//    }

    /**
     * 1.理解题意
     * -输入一个数组，从头开始遍历数组元素，从i元素作为起始位置，然后进行奇偶跳，能够跳到数组最后一个元素，
     * --则称该起始元素是好的起始索引，求好的起始索引的数量
     * -奇偶跳：第一次跳跃是奇数跳，第二次跳跃是偶数跳，。。。。
     * --奇数跳跃：要求跳到下一个索引的位置为j，且A[i]<=A[j]; j的值是比i大的最小值
     * --偶数跳跃：要求跳到下一个索引的位置为j，且A[i]>=A[j]; j的值是比i小的最大值
     * 2。解题思路：暴力解法
     * -第一层for循环i从0开始遍历
     * --然后找到i需要跳转到的下一个位置j：根据奇偶跳的要求查找（奇数跳要求）
     * ---只要能跳转到下一个j位置，则以j位置继续查找（偶数跳要求）
     * ---直到跳转到最后元素位置，说明当前起始索引是好索引；否则中断，回到第一层for循环查找下一个起始索引
     * 3。边界和细节处理
     * 4。时间复杂度： O(n^3)
     *
     * @param arr
     * @return
     */
    public int oddEvenJump_v1(int[] arr) {
        int N = arr.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            int nextPos = i;
            boolean isOdd = true;
            while (nextPos < N - 1) {
                nextPos = findNextPos(arr, nextPos, isOdd);
                isOdd = !isOdd;
                System.out.println("i:" + i + " ,nexPos:" + nextPos);
                if (nextPos == -1) {
                    break;
                }
            }
            if (nextPos == N - 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 查到当前位置index的下一个跳跃位置
     *
     * @param arr
     * @param index 当前位置
     * @param isOdd 是否是奇数跳
     * @return
     */
    private int findNextPos(int[] arr, int index, boolean isOdd) {
        int N = arr.length;
        if (index == N - 1) {
            return index;
        }
        int nextPos = -1;
        int curr = isOdd ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (int i = index + 1; i < N; i++) {
            if (isOdd && arr[i] >= arr[index] && curr > arr[i]) {   //奇数跳，查找大于index值的最小值
                curr = arr[i];
                nextPos = i;
            } else if (!isOdd && arr[i] <= arr[index] && curr < arr[i]) {   //偶数跳，查找小雨index值的最大值
                curr = arr[i];
                nextPos = i;
            }
        }
        return nextPos;
    }

    /**
     * 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，
     * 而第 2、4、6... 次跳跃称为偶数跳跃。
     *
     * 你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：
     *
     * 在进行奇数跳跃时（如，第 1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值。
     * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
     * 在进行偶数跳跃时（如，第 2，4，6... 次跳跃），你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值。
     * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
     * （对于某些索引 i，可能无法进行合乎要求的跳跃。）
     * 如果从某一索引开始跳跃一定次数（可能是0次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。
     *
     * 返回好的起始索引的数量。
     *
     * 示例 1：
     * 输入：[10,13,12,14,15]
     * 输出：2
     * 解释：
     * 从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
     * 从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
     * 从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
     * 从起始索引 i = 4 出发，我们已经到达数组末尾。
     * 总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     *
     * 示例 2：
     * 输入：[2,3,1,1,4]
     * 输出：3
     * 解释：
     * 从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：
     * 在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。
     * 在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。
     * 在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。
     * 我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。
     *
     * 类似地，我们可以推断：
     * 从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     * 从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
     * 从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     * 从起始索引 i = 4 出发，我们已经到达数组末尾。
     * 总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     *
     * 示例 3：
     * 输入：[5,1,3,4,2]
     * 输出：3
     * 解释：
     * 我们可以从起始索引 1，2，4 出发到达数组末尾。
     *  
     * 提示：
     * 1 <= A.length <= 20000
     * 0 <= A[i] < 100000
     * 链接：https://leetcode-cn.com/problems/odd-even-jump
     */
}
