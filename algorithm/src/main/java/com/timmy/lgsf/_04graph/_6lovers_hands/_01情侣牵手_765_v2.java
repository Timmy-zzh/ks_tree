package com.timmy.lgsf._04graph._6lovers_hands;

public class _01情侣牵手_765_v2 {

    public static void main(String[] args) {
        _01情侣牵手_765_v2 demo = new _01情侣牵手_765_v2();
//        int[] row = {0, 2, 1, 3};
//        int[] row={3, 2, 0, 1};
        int[] row = {6, 3, 0, 2, 1, 4, 5, 7};
//        int[] row = {6, 1, 0, 3, 2, 7, 5, 4};
        int result = demo.minSwapsCouples(row);
        System.out.println("result:" + result);
    }

    /**
     * 1.理解题意
     * -求情侣交换位置的最少次数
     * 2。解题思路：贪心算法+并查集
     * 2.1.规律
     * -每交换一次，必有一对情侣完成配对
     * -如果有N对情侣，其中有x对情侣的位置是正确不用交换的，则只需要交换N-x-1次，
     * --为什么还要 -1呢？因为交换了前面n-1对情侣位置后，最后一对肯定已经是正确的了
     * -->所以我们只需要查找到有n几组情侣的位置不对，即可以求出位置交换的个数为n-1
     * 2.2.分组思想
     * -2N情侣，有N对，一对情侣作为一组（一个集合），遍历所有情侣元素，初始化所有情侣的跟节点
     * -便利情侣元素，每次遍历2个元素，如果这两个元素根节点相同，则说明这两个情侣位置正确，不用调整
     * -如果跟节点元素不同，则说明相邻的两个元素不是情侣关系
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        UnSet unSet = new UnSet(row.length / 2);
        for (int i = 0; i < row.length; i += 2) {
            unSet.union(row[i] / 2, row[i + 1] / 2);
        }
        return unSet.getCount();
    }

    class UnSet {
        //并查集个数
        int count;
        //情侣对的跟节点
        int[] parent;

        public UnSet(int n) {
            parent = new int[n];
            count = 0;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootY] = rootX;
            count++;
        }

        private int find(int x) {
            while (parent[x] != x) {
                x = parent[x];
                parent[x] = parent[parent[x]];
            }
            return parent[x];
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，
     * 以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
     *
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，
     * 以此类推，最后一对是 (2N-2, 2N-1)。
     *
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     *
     * 示例 1:
     *
     * 输入: row = [0, 2, 1, 3]
     * 输出: 1
     * 解释: 我们只需要交换row[1]和row[2]的位置即可。
     * 示例 2:
     *
     * 输入: row = [3, 2, 0, 1]
     * 输出: 0
     * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
     *
     * 说明:
     * len(row) 是偶数且数值在 [4, 60]范围内。
     * 可以保证row 是序列 0...len(row)-1 的一个全排列。
     *
     * 链接：https://leetcode-cn.com/problems/couples-holding-hands
     */
}
