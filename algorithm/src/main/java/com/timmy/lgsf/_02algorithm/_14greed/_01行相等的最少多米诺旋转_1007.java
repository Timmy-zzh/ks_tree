package com.timmy.lgsf._02algorithm._14greed;

public class _01行相等的最少多米诺旋转_1007 {

    public static void main(String[] args) {
        _01行相等的最少多米诺旋转_1007 demo = new _01行相等的最少多米诺旋转_1007();
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {2, 2, 6, 2, 3, 2};
        int result = demo.minDominoRotations(A, B);
        System.out.println("result:" + result);
    }

    /**
     * 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。
     * （一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
     * 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
     * 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
     * 如果无法做到，返回 -1.
     * <p>
     * 示例 1：
     * 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
     * 输出：2
     * 解释：
     * 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
     * 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
     * <p>
     * 示例 2：
     * 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
     * 输出：-1
     * 解释：
     * 在这种情况下，不可能旋转多米诺牌使一行的值相等。
     * <p>
     * 链接：https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row
     */


    /**
     * 贪心算法
     * 解题思路：
     * 最后要的结果是数组A中的元素全部变成A[0],或者数组B中的元素全部变成A[0];
     * 或者A，B数组的所有元素全部变成B[0]
     */
    public int minDominoRotations(int[] A, int[] B) {
        //1.先判断A，B数组都变成A[0]数值，需要旋转的次数
        int n = A.length;
        int check = check(A[0], A, B, n);
        if (check != -1) {
            return check;
        } else {
            // 将A，B数组全部变成A[0]失败，则判断将A，B数组全部变成B[0]元素旋转的次数
            return check(B[0], A, B, n);
        }
    }

    /**
     * 传入x为A[0] 或 B[0]的元素数值，
     * 如果A，B中都与x不想等，则不存在满足条件的元素
     * 如果数组A与x不想等，说明要旋转A
     * 如果数组B与x不相等，要旋转B
     */
    public int check(int x, int[] A, int[] B, int n) {
        int rotateA = 0;
        int rotateB = 0;
        for (int i = 0; i < n; i++) {
            if (x != A[i] && x != B[i]) {
                return -1;
            } else if (x != A[i]) {
                rotateA++;
            } else {
                rotateB++;
            }
        }
        return rotateA > rotateB ? rotateB : rotateA;
    }

    /**
     * 1.理解题意
     * -两个数组，数组中的元素个数相同，通过旋转两个数组中的上下元素，使得最后数组的元素都相同，求选择的最小次数
     * A-[2,1,2,4,2,2]
     * B-[5,2,6,2,3,2]
     * 2。解题思路
     * -因为多米诺的数值是1-6的，所以定义一个数组保存每个数值在数组A和B中出现的次数
     * -并保存两个数组在相同位置数值出现的次数，如果有数值出现次数等于6，说明该数值符合题意，进一步计算旋转次数
     * 3。边界与细节问题
     * -数值是从1开始的，数组下标从0开始保存
     * -上下元素数组等于6的是满足条件的
     */
    public int minDominoRotations_v1(int[] A, int[] B) {
        int[] countA = new int[6];  //A数组中各数值出现的次数
        int[] countB = new int[6];  //B中各数值出现的次数
        int[] countAB = new int[6]; //AB数组中相同下标数值出现的次数，数值相同，计数一次

        int n = A.length;
        for (int i = 0; i < n; i++) {
            countA[A[i] - 1]++;
            countB[B[i] - 1]++;
            countAB[A[i] - 1]++;
            if (A[i] != B[i]) {
                countAB[B[i] - 1]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (countAB[i] == n) {
                return n - Math.max(countA[i], countB[i]);
            }
        }
        return -1;
    }
}
