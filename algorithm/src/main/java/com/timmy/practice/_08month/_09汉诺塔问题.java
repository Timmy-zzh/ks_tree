package com.timmy.practice._08month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _09汉诺塔问题 {

    public static void main(String[] args) {
        _09汉诺塔问题 demo = new _09汉诺塔问题();
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        PrintUtils.print(A);
        demo.hanota(A, B, C);
        PrintUtils.print(C);
    }

    /**
     * 1.理解题意
     * -有三根柱子和n个圆盘，刚开始n个盘子都存放在A柱子上，并且从上倒下按升序保存，现在要将A柱子上的所有盘子移动到C柱子上
     * -移动规则如下：
     * --每次只能移动一个盘子，且要从柱子顶端移动
     * --移动的盘子只能放在更大盘子的上面
     * 2。解题思路：递归解法
     * -将A柱子上的n个盘子，分成两部分，上面部分为n-1个，还有最底部的一个大的
     * -先将n-1个盘子看作一个整体从A柱子移动到B柱子上（借助C柱子）
     * -然后将第n个盘子从柱子A移动到C柱子上，
     * -最后将B柱子上的n-1个盘子移动到柱子C上
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        movePlate(A.size(), A, B, C);
    }

    private void movePlate(int size, List<Integer> start, List<Integer> auxiliary, List<Integer> target) {
        if (size == 1) {
            target.add(start.remove(start.size() - 1));
            return;
        }
        movePlate(size - 1, start, target, auxiliary);
        target.add(start.remove(start.size() - 1));
        movePlate(size - 1, auxiliary, start, target);
    }

    /**
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，
     * 所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
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
     *
     * 提示:
     * A中盘子的数目不大于14个。
     * 链接：https://leetcode-cn.com/problems/hanota-lcci
     */
}
