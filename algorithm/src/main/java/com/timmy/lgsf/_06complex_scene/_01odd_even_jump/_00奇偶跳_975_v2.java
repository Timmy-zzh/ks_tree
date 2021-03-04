package com.timmy.lgsf._06complex_scene._01odd_even_jump;

import com.timmy.common.PrintUtils;

import java.util.Arrays;
import java.util.Stack;

public class _00奇偶跳_975_v2 {

    public static void main(String[] args) {
        _00奇偶跳_975_v2 demo = new _00奇偶跳_975_v2();
        int[] arr = {10, 13, 12, 14, 15};
//        int[] arr = {5, 1, 3, 4, 2};
//        int[] arr = {2, 3, 1, 1, 4};
        PrintUtils.print(arr);
        int res = demo.oddEvenJump(arr);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2。解题思路：动态规划+单调栈
     * 2.1.动态规划思路：
     * -第一层for循环决定当前遍历到了那个位置的元素
     * -第二层循环查找当前位置跳跃到的下一个位置j的值
     * -动态规划根据从后往前遍历，根据下一个跳转位置是否可以到达末尾，从而判断当前位置是否是好索引
     * 2.2.
     * 思考最优解：
     * -上面动态规划的思路时间复杂度为O(n^2),第二层循环主要工作是查找位置i的下一个跳跃的位置j（都需要遍历后面的元素），
     * -如果我们在前期就计算好位置i的跳跃位置j（或者没有跳转目标位置），并且使用数据结构保存好，--》使用排序好的数组，则下一个元素就是略大于当前元素的下一个元素
     * -则获取下一跳跃位置的复杂度降低为O(1)
     * 2。3。动态规划+单调栈+排序（要求稳定排序）
     * 单调递增栈：栈中数据出栈的顺序序列为单调递增序列
     * -元素入栈的时候，要小于栈顶元素
     * -如果大于栈顶元素，则需要先出栈
     * 快速排序（稳定版）
     * -在原始快速排序的基础上，增加索引数组位置的判断
     *
     * @param arr
     * @return
     */
    public int oddEvenJump(int[] arr) {
        int N = arr.length;
        int count = 1;

        //TODO 目标：采用单调栈和升序数组，先期确定元素i的下一个跳转目标位置
        //计算后的结果值保存在nextPosArr数组中，保存元素i奇偶跳下一个目标的位置
        int[][] nextPosArr = new int[N][2];

        //奇数跳处理，先对原始数组进行升序排序，并使用一个index数组保存下表的位置
        //因为数组已经是升序了，所以对应的下表数组可以用于确定下一跳的位置
        //然后使用单调栈保存，如果新入斩的下标数组大于栈顶元素，则需要出栈，出栈元素的下一个跳跃位置，就是新入栈的数组下标

        //原数组保持不变，复制一个一样的新数组进行排序操作
        int[] newArr = Arrays.copyOf(arr, N);
        //保存元素下标的数组
        int[] indexArr = new int[N];
        for (int i = 0; i < N; i++) {
            indexArr[i] = i;
        }
        //快速排序（稳定版）
        quickSort(newArr, indexArr, 0, N - 1);
//        PrintUtils.print(newArr);
//        PrintUtils.print(indexArr);
        //根据升序后的数组和下表数组，使用单调栈，保存状态转移位置i的下一个跳跃位置
        Stack<Integer> stack = new Stack<>();   //单调递增序列
        for (int i = 0; i < N; i++) {
            int index = indexArr[i];
            while (!stack.isEmpty() && index > stack.peek()) {
                Integer pop = stack.pop();
                //出栈的元素下标，他的下一个跳转位置就是index位置，因为index位置的值是大于i的最小值啊
                nextPosArr[pop][0] = index;
            }
            stack.push(index);
        }

        //偶数跳也是用同样的方式，求的跳转状态方程
        indexArr = new int[N];
        for (int i = 0; i < N; i++) {
            newArr[i] = -1 * arr[i];
            indexArr[i] = i;
        }
        //快速排序（稳定版）
        quickSort(newArr, indexArr, 0, N - 1);
        PrintUtils.print(newArr);
        PrintUtils.print(indexArr);
        //根据升序后的数组和下表数组，使用单调栈，保存状态转移位置i的下一个跳跃位置
        stack = new Stack<>();   //单调递增序列
        for (int i = 0; i < N; i++) {
            int index = indexArr[i];
            while (!stack.isEmpty() && index > stack.peek()) {
                Integer pop = stack.pop();
                //出栈的元素下标，他的下一个跳转位置就是index位置，因为index位置的值是大于i的最小值啊
                nextPosArr[pop][1] = index;
            }
            stack.push(index);
        }
        nextPosArr[N - 1][0] = N - 1;
        nextPosArr[N - 1][1] = N - 1;
        System.out.println("------");
        PrintUtils.print(nextPosArr);
        System.out.println("------");

        //从倒数第二个元素开始判断
        for (int i = N - 2; i >= 0; i--) {
            //奇数跳
            int nextPos = nextPosArr[i][0];//findNextPos(arr, i, true);
            if (nextPos == N - 1) {
                nextPosArr[i][0] = nextPos;
            } else if (nextPos != 0) { //跳转不了
                // i的奇数跳跳转的下个位置j，是下一个j的位置的偶数跳
                nextPosArr[i][0] = nextPosArr[nextPos][1];
            }
            if (nextPosArr[i][0] == N - 1) {
                count++;
            }
            //偶数跳
            nextPos = nextPosArr[i][1];// findNextPos(arr, i, false);
            if (nextPos == N - 1) {
                nextPosArr[i][1] = nextPos;
            } else if (nextPos != 0) { //跳转不了
                nextPosArr[i][1] = nextPosArr[nextPos][0];
            }
        }
        //判断有几个元素可以跳转到最后
        PrintUtils.print(nextPosArr);
        return count;
    }

    /**
     * @param arr      需要最后升序的数组
     * @param indexArr 升序排序后对应的元素下标
     * @param start    开始下标
     * @param end      结束下标
     */
    private void quickSort(int[] arr, int[] indexArr, int start, int end) {
        int i = start;
        int j = end;
        int temp = arr[start];
        int tempIndex = indexArr[start];

        //以排序位置第一个元素start为基准，不断进行交换，最后结果是：比temp小的元素都在前面，比temp值大的都在后面
        while (i < j) {
            //先从后面查找，比temp值小的元素，然后与位置i进行交换
            while (i < j && arr[j] >= temp) {
                //如果遇到值相同的两个元素，则比较下标值，需要下标值也是升序
                if (arr[j] == temp && indexArr[j] < tempIndex) {
                    break;
                }
                j--;
            }
            //退出上面的循环，说明找到 小于基准值temp的元素--》与位置i交换
            if (i < j) {
                indexArr[i] = indexArr[j];
                arr[i++] = arr[j];
            }

            //上面已经i++了，现在从i的位置查找比temp大的元素，并与位置j交换
            while (i < j && arr[i] <= temp) {
                if (arr[i] == temp && indexArr[i] > tempIndex) {
                    break;
                }
                i++;
            }
            if (i < j) {
                indexArr[j] = indexArr[i];
                arr[j--] = arr[i];
            }
        }
        arr[i] = temp;
        indexArr[i] = tempIndex;
        if (start < i - 1) {
            quickSort(arr, indexArr, start, i - 1);
        }
        if (i + 1 < end) {
            quickSort(arr, indexArr, i + 1, end);
        }
    }
}
