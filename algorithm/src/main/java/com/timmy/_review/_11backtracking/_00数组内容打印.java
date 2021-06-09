package com.timmy._review._11backtracking;

import java.util.ArrayList;
import java.util.List;

public class _00数组内容打印 {

    public static void main(String[] args) {
        _00数组内容打印 demo = new _00数组内容打印();
        int[] A = {1, 2, 3};
        demo.solve(A);
    }

    /**
     * 使用Box类方法实现打印
     */
    public void solve(int[] A) {
        Box box = new Box();
        solve3(A, 0, box);
    }

    /**
     * 2。回溯算法，使用box保存操作的元素
     * 回溯1个核心：第i个元素处理逻辑
     * 3个条件：
     * -满足条件，添加到结果集中
     * -终止递归
     * -下层回溯 i+1
     */
    private void solve3(int[] A, int i, Box box) {
        box.print();
        if (i >= A.length) {
            return;
        }
        box.push(A[i]);
        solve3(A, i + 1, box);
        box.pop();
    }

    class Box {
        private List<Integer> list = new ArrayList<>();

        public void push(int i) {
            list.add(i);
        }

        public void pop() {
            list.remove(list.size() - 1);
        }

        public void print() {
            // 这里给定的A数组必然是有效的
            System.out.print("{");
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("}");
        }
    }


    /*******************************************************/

    /**
     * 递归调用实现
     */
    public void solve_v2(int[] A) {
        solve2(A, 0);
    }

    private void solve2(int[] A, int i) {
        print(A, i);
        if (i >= A.length) {
            return;
        }
        solve2(A, i + 1);
    }

    /**
     * 1。输入一个数组，打印出要求结果，需要使用print函数
     * 2。遍历数组元素，在遍历数组元素下标时，调用print函数，i的范围是[0,N] --注意边界
     */
    public void solve_v1(int[] A) {
        for (int i = 0; i <= A.length; i++) {
            print(A, i);
        }
    }

    // 打印数组的[0, i)范围里面的数
    void print(int[] A, int i) {
        // 这里给定的A数组必然是有效的
        System.out.print("{");
        for (int j = 0; j < i; j++) {
            System.out.print(A[j] + ", ");
        }
        System.out.println("}");
    }

    /**
     * 输入一个数组，打印如下数组的内容
     * {}
     * {1,}
     * {1, 2}
     * {1, 2, 3,}
     */
}
