package com.timmy._review._00recursive;

import com.timmy.common.PrintUtils;

import java.util.LinkedList;
import java.util.List;

public class _01汉诺塔问题 {


    public static void main(String[] args) {
        _01汉诺塔问题 demo = new _01汉诺塔问题();
        List<Integer> A = new LinkedList<>();
        List<Integer> B = new LinkedList<>();
        List<Integer> C = new LinkedList<>();
        A.add(1);
        A.add(2);
        A.add(3);

        PrintUtils.print(A);
        demo.hanota(A, B, C);
        PrintUtils.print(C);
    }

    /**
     * 1.理解题意
     * -汉诺塔经典问题，有三根柱子（A，B，C），和N个不同大小的圆盘（使用数字进行表示），
     * --刚开始左右圆盘都在A柱子上，要求将A柱子上的所有圆盘移动到C柱子上
     * -圆盘移动规则限制：
     * --每次只能移动一个盘子
     * --盘子只能从柱子顶端滑出，移动到下一根柱子
     * --盘子只能叠在比他大的盘子上
     * 2。解题思路：递归
     * -要将A柱子上n个圆盘移动到C，可以先将上面n-1个盘子先移动到B，然后将A中最后的那个第n个盘子移动到C
     * -再将B上面的n-1个盘子，移动到柱子C上
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }

    /**
     * 柱子A上有n个盘子，需要将A柱子上的n个盘子移动到柱子C上，借柱子B，
     * 递归三要素：
     * -入参与返回值
     * -终止条件：只有一个盘子
     * -单层递归
     */
    private void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n > 0) {
            //1。先将A柱子上的n-1个盘子，从A柱子上的移动到B柱子上，借助于柱子C
            hanota(n - 1, A, C, B);
            //2.将A柱子的最大那个盘子移动到C柱子上
            C.add(A.remove(A.size() - 1));
            //3.再将柱子B上的n-1个盘中移动到柱子C上，借助于珠子A
            hanota(n - 1, B, A, C);
        }
    }

    /**
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
     * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     *
     * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     * 你需要原地修改栈。
     *
     * 示例1:
     *  输入：A = [2, 1, 0], B = [], C = []
     *  输出：C = [2, 1, 0]
     *
     * 示例2:
     *  输入：A = [1, 0], B = [], C = []
     *  输出：C = [1, 0]
     * 提示:
     *
     * A中盘子的数目不大于14个。
     *
     * 链接：https://leetcode-cn.com/problems/hanota-lcci
     */
}
