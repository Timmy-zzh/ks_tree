package com.timmy.lgsf._04graph._26union_find_set;

public class _01岛屿数量_200 {

    public static void main(String[] args) {
        _01岛屿数量_200 demo = new _01岛屿数量_200();
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int result = demo.numIslands(grid);
        System.out.println("result:" + result);
    }

    /**
     * 1。理解题意
     * -遍历二维数组，当找到陆地为1的元素时，需要将附近所有的陆地链接起来组成岛屿，并求出最后岛屿的个数
     * 2。使用并查集方式解题
     * -将相邻的陆地保存在一个集合中，有几个陆地区域，就存在几个集合
     * --问题：如何将相邻的陆地保存在同一个集合中？
     * -2。1。遍历，首先将所有单个的陆地元素当作一个集合，该集合只有一个元素，就是他本身自己
     * -2。2。再次遍历，当遇到陆地元素时，查找该陆地上下左右四周是否是陆地，如果是陆地，则判读是否进行合并
     * --如果都是陆地，且没有在同一个集合，则将当前单陆地集合合并
     * ---判断是否在同一集合，主要通过判断元素的根节点是否相同，（刚开始不同，跟节点都自己，当合并和跟节点一样）
     * -2。3。并查集的处理逻辑只有两步：查找与合并
     * --刚开始每个陆地元素都是一个集合，并且集合中只有自己一个元素，（跟节点为自己，集合的层次为1）
     * --当遇到相邻的陆地节点元素时，判断是否在同一个集合，不在的话则将新的陆地元素集合 与原先的陆地区域集合进行合并
     * ---合并的主要动作是将新集合元素的跟节点设置为一样（按秩合并）
     *
     * @param grid
     * @return
     */
    private int numIslands(char[][] grid) {
        UnionFindSet uf = new UnionFindSet(grid);
        System.out.println("count:" + uf.getCount());
        //遍历，找到陆地元素，并判断四周节点是否为陆地，也是陆地的话则进行合并
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    //先将陆地节点设置为水（0），并判断四周节点是否为陆地
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {  //上节点
                        // 当前节点 - 与上节点 进行合并
                        uf.union(uf.hash(i, j), uf.hash(i - 1, j));
                    }
                    if (i + 1 < row && grid[i + 1][j] == '1') {  //下节点
                        uf.union(uf.hash(i, j), uf.hash(i + 1, j));
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {  //左节点
                        uf.union(uf.hash(i, j), uf.hash(i, j - 1));
                    }
                    if (j + 1 < col && grid[i][j + 1] == '1') {  //右节点
                        uf.union(uf.hash(i, j), uf.hash(i, j + 1));
                    }
                }
            }
        }

        System.out.println("--count:" + uf.getCount());
        return uf.getCount();
    }

    public class UnionFindSet {
        /**
         * 保存每个陆地节点的跟节点
         * key为[x,y]的hash值，value也是自身
         * 数量为m*n
         */
        int[] parent;

        /**
         * 层次
         * 默认刚开始陆地节点的层次为1
         * key为[x,y]的hash值，value为层次
         */
        int[] rank;

        /**
         * 陆地集合的个数
         * 刚开始count为陆地的个数，当合并和count不断减少
         */
        int count;

        char[][] grid;
        private int constant;

        public UnionFindSet(char[][] grid) {
            this.grid = grid;
            int row = grid.length;
            int col = grid[0].length;
            constant = col;

            parent = new int[row * col];
            rank = new int[row * col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int value = hash(i, j);
                        parent[value] = value;
                        rank[value] = 1;
                        count++;
                    }
                }
            }
        }

        /**
         * 合并两个元素
         * -先判断两个元素的根节点是否相同，相同表示在同一个集合中，不做处理
         * -不相同，则需要将两个集合进行合并，合并按照集合的层次进行合并，合并后元素的根节点一样
         *
         * @param x 为元素坐标[x,y]的封装值
         * @param y
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                //根节点不相同，-按秩合并
                if (rank[rootX] > rank[rootY]) {
                    parent[y] = rootX;          //x集合的层次更多，将y集合合并到x集合中去--y的根节点设置为x的根节点即可
                } else if (rank[rootX] < rank[rootY]) {
                    parent[x] = rootY;
                } else {
                    //秩相等,y集合合并到x集合中去，且x集合的层次加1
                    parent[y] = rootX;
                    rank[rootX] = rank[rootX] + 1;
                }
                count--;
            }
        }

        /**
         * 查找数据x的根节点
         * -不断往上遍历查找，直到根节点的key值和value值相同
         *
         * @param x x为二维坐标[i,j]的封装hash值
         * @return
         */
        private int find(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return parent[x];

            //压缩路径算法
        }

        public int getCount() {
            return count;
        }

        /**
         * 通过二维数组的 x,y 坐标，封装成一个值，且该值可以代表 [x,y]坐标
         * x * constant + y
         *
         * @param x
         * @param y constant 参数为列
         * @return
         */
        public int hash(int x, int y) {
            return x * constant + y;
        }

        public int hash_v1(int x, int y, int constant) {
            return x * constant + y;
        }
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     */
}
