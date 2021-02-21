package com.timmy.testlib;

import java.util.HashSet;

public class tSKJLF {

    public static void main(String[] args) {
        tSKJLF demoe = new tSKJLF();
//        String[] equations = {"a==b", "b==c", "a==c"};
//        String[] equations = {"a==b","b!=c","c==a"};
        String[] equations = {"c==c", "b==d", "x!=z"};
//        String[] equations = {"e==e", "d!=e", "c==d", "d!=e"};
        boolean result = demoe.equationsPossible(equations);
        System.out.println("result:" + result);
    }

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
        PrintUtils.print(parent);

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

}
