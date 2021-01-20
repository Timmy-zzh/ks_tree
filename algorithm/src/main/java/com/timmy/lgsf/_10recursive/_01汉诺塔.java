package com.timmy.lgsf._10recursive;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _01汉诺塔 {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(3);

        _01汉诺塔 demo = new _01汉诺塔();
        demo.hannuota(A.size(), A, B, C);
        PrintUtils.print(C);
    }

    /**
     * 汉诺塔递归实现
     * 题目要求：有n个盘子和三个柱子，刚开始n个盘子全部在A柱子上，并且按照从小到大依次从上到下排列
     * 现在要求将A上的盘子全部移动到C柱子上，并且在移动过程中小号的盘子只能在大号的盘子上
     * <p>
     * 解题思路：
     * 1。如果只有一个盘子，直接从A移动到C
     * 2。如果有n个盘子，先将n-1个盘子从A移动到B，然后将最大那个盘子从A移动到C
     * 3。接着将n-1个盘子，从B移动到C
     */
    public void hannuota(int size, List<Integer> start, List<Integer> betwoon, List<Integer> target) {
        if (size == 1) {
            target.add(start.remove(start.size() - 1));
        } else {
            //1.将n-1个盘子，从A移动到B
            hannuota(size - 1, start, target, betwoon);
            //2。将第n个盘子，移动到C
            target.add(start.remove(start.size() - 1));
            //3。将n-1个盘子，从B移动到C
            hannuota(size - 1, betwoon, start, target);
        }
    }


}