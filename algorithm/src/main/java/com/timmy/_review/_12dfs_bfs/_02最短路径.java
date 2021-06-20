package com.timmy._review._12dfs_bfs;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _02最短路径 {

    public static void main(String[] args) {
        _02最短路径 demo = new _02最短路径();
        int[][] A = {
                {0, 1},
                {0, 0}};
        PrintUtils.print(A);
        List<int[]> res = demo.findMinPath(A);
        for (int[] re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个二维数组表示迷宫，数组元素由0和1组成，其中1表示墙壁，0表示通道，求从左上角走到右下角的最短路径，并输出行走的点坐标
     * 2。解题思路：dfs 深度优先遍历 + 回溯算法
     * -要从左上角走到右下角，当前行走位置可以从四周扩散，并且四周不是墙壁，使用路径集合保存走过的路径
     * --当最后行走到目标位置时，比较当前路径比之前走过的最短路径是否还要短，是的话最短路径赋值为当前更短的路径
     * -因为要寻找最短的路径，而且第i个位置的节点有四个方向可以选择，所以需要使用回溯法返回到之前的状态从新选择路径节点
     */
    List<int[]> shortPath;
    private int[][] dir = {
            {-1, 0},    // 上
            {1, 0},     // 下
            {0, -1},    // 左
            {0, 1}      // 右
    };

    public List<int[]> findMinPath(int[][] A) {
        if (A == null || A.length == 0) {
            return shortPath;
        }

        int row = A.length;
        int col = A[0].length;
        List<int[]> path = new ArrayList<>();
        boolean[][] visited = new boolean[row][col];
        path.add(new int[]{0, 0});
        visited[0][0] = true;
        dfs(A, path, visited, 0, 0);
        path.remove(path.size() - 1);
        visited[0][0] = false;

        return shortPath;
    }

    private void dfs(int[][] A, List<int[]> path, boolean[][] visited, int x, int y) {
//        System.out.println("x:" + x + " ,y:" + y);
        //递归，判断当前节点是否满足条件
        int row = A.length;
        int col = A[0].length;
        if (x == row - 1 && y == col - 1) {
            if (shortPath == null) {
                shortPath = new ArrayList<>(path);
            } else if (path.size() < shortPath.size()) {
                shortPath = new ArrayList<>(path);
            }
        }

        //剪枝1：如果当前路径的长度，已经比shortPath还要长了，就没有比较继续往下走了
        if (shortPath != null && path.size() > shortPath.size()) {
            return;
        }

        //当前节点[x,y]的四个方向的选项
        for (int d = 0; d < dir.length; d++) {
            int[] ints = dir[d];
            int newX = x + ints[0];
            int newY = y + ints[1];
            if (checkRange(newX, newY, A) && !visited[newX][newY] && A[newX][newY] == 0) {
                path.add(new int[]{newX, newY});
                visited[newX][newY] = true;
                dfs(A, path, visited, newX, newY);
                path.remove(path.size() - 1);
                visited[newX][newY] = false;
            }
        }
    }

    private boolean checkRange(int newX, int newY, int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        return 0 <= newX && newX < row && 0 <= newY && newY < col;
    }

    /**
     * 【题目】给定一个迷宫，其中 0 表示可能通过的地方，而 1 表示墙壁。请问，
     * 从左上角走到右下角的最短路径是什么样的？请依次输出行走的点坐标。
     * 输入：A = [[0, 1], [0, 0]]
     * 输出：ans = [[0, 0], [1, 0], [1,1]]
     * 解释：
     *
     */
}
