package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _05三角形最小路径和_120 {

    public static void main(String[] args) {
        _05三角形最小路径和_120 demo = new _05三角形最小路径和_120();
        List<List<Integer>> triangle = new ArrayList<>();
//        ArrayList<Integer> level0 = new ArrayList<>();
//        level0.add(2);
//        triangle.add(level0);
//
//        ArrayList<Integer> level1 = new ArrayList<>();
//        level1.add(3);
//        level1.add(4);
//        triangle.add(level1);
//
//        ArrayList<Integer> level2 = new ArrayList<>();
//        level2.add(6);
//        level2.add(5);
//        level2.add(7);
//        triangle.add(level2);
//
//        ArrayList<Integer> level3 = new ArrayList<>();
//        level3.add(4);
//        level3.add(1);
//        level3.add(8);
//        level3.add(3);
//        triangle.add(level3);

        //[[-1],[2,3],[1,-1,-3]]
        ArrayList<Integer> level0 = new ArrayList<>();
        level0.add(-1);
        triangle.add(level0);

        ArrayList<Integer> level1 = new ArrayList<>();
        level1.add(2);
        level1.add(3);
        triangle.add(level1);

        ArrayList<Integer> level2 = new ArrayList<>();
        level2.add(1);
        level2.add(-1);
        level2.add(-3);
        triangle.add(level2);

        for (List<Integer> integers : triangle) {
            PrintUtils.print(integers);
        }
        int res = demo.minimumTotal(triangle);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入双层集合表示三角形，现在需要找出一条最小路径和，路径从最顶部开始往下层寻找，上层到下层的下标位置为[i,i+1]
     * 2.解题思路
     * 深度优先遍历 + 递归实现
     * -从三角形的第一层开始，并从第一层集合的不同位置开始遍历，往下一层，一直到最下层计算最小路径和结束
     */
    int res = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        dfs(triangle, 0, 0, 0);
        return res;
    }

    /**
     * @param triangle
     * @param level    层级
     * @param index    在这一层的起始位置
     * @param path     当前路径上的和
     */
    private void dfs(List<List<Integer>> triangle, int level, int index, int path) {
        System.out.println("level:" + level + " ,index:" + index + " ,path:" + path);
        if (level == triangle.size()) {
            if (path < res) {
                res = path;
            }
            return;
        }

        List<Integer> nums = triangle.get(level);
        for (int i = index; i < nums.size() && i < index + 2; i++) {   //只遍历两个数字
            dfs(triangle, level + 1, i, path + nums.get(i));
        }
    }

    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标
     * 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     * 示例 1：
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     * 示例 2：
     * 输入：triangle = [[-10]]
     * 输出：-10
     *  
     * 提示：
     * 1 <= triangle.length <= 200
     * triangle[0].length == 1
     * triangle[i].length == triangle[i - 1].length + 1
     * -104 <= triangle[i][j] <= 104
     *
     * 进阶：
     * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
     * 链接：https://leetcode-cn.com/problems/triangle
     */
}
