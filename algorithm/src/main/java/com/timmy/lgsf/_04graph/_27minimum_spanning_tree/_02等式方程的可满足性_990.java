package com.timmy.lgsf._04graph._27minimum_spanning_tree;


public class _02等式方程的可满足性_990 {

    public static void main(String[] args) {
        _02等式方程的可满足性_990 demoe = new _02等式方程的可满足性_990();
//        String[] equations = {"a==b", "b==c", "a==c"};
//        String[] equations = {"a==b","b!=c","c==a"};
        String[] equations = {"c==c", "b==d", "x!=z"};
//        String[] equations = {"e==e", "d!=e", "c==d", "d!=e"};
        boolean result = demoe.equationsPossible(equations);
        System.out.println("result:" + result);
    }

    /**
     * 1.理解题意
     * -输入字符串数组，数组元素是变量的方程式，且字符串方程长度固定为4
     * -当将整数赋值给变量时，查看方程是否成立，成立则返回true
     * 2.解题思路
     * -并查集方式解答
     * -遍历字符串数组，判断方程是否是“==”，是的话则将方程中相应的两个字母存放在同一个集合中
     * -当方程是“！=”时，判断两个字母是否在通过一个集合，如果是在同一个集合，说明两个字母相等，
     * --方程式有误，返回false
     *
     * @param equations
     * @return
     */
    int[] parent;

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < equations.length; i++) {
            String equation = equations[i];
            if (equation.contains("==")) {
                int start = equation.substring(0, 1).charAt(0) - 'a';
                int end = equation.substring(3, 4).charAt(0) - 'a';
                System.out.println("start:" + start + " ,end:" + end);
                union(start, end);
            }
        }

        for (int i = 0; i < equations.length; i++) {
            String equation = equations[i];
            if (equation.contains("!=")) {
                int start = equation.substring(0, 1).charAt(0) - 'a';
                int end = equation.substring(3, 4).charAt(0) - 'a';
                if (find(start) == find(end)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    private int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
            parent[x] = parent[parent[x]];
        }
        return parent[x];
    }


    /**给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
     * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），
     * 表示单字母变量名。

     只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

     示例 1：

     输入：["a==b","b!=a"]
     输出：false
     解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     示例 2：

     输入：["b==a","a==b"]
     输出：true
     解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
     示例 3：

     输入：["a==b","b==c","a==c"]
     输出：true
     示例 4：

     输入：["a==b","b!=c","c==a"]
     输出：false
     示例 5：

     输入：["c==c","b==d","x!=z"]
     输出：true

     链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
     */


}
