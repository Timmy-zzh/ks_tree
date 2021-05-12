package com.timmy.common;

import java.util.List;

public class PrintUtils {


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void print(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void print(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(String.valueOf(arr[i][j]) + ", \t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }

    public static void printCh(List<Character> list) {
        for (Character integer : list) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }

    public static void printStr(List<String> lists) {
        for (String integer : lists) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }

    /**
     * 前序遍历
     */
    public static void printPre(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + ", ");
        printPre(root.left);
        printPre(root.right);
    }

    //中序便利
    public static void printMid(TreeNode root) {
        if (root == null) {
            return;
        }
        printMid(root.left);
        System.out.print(root.val + ", ");
        printMid(root.right);
    }

    //后序遍历
    public static void printNex(TreeNode root) {
        if (root == null) {
            return;
        }
        printNex(root.left);
        printNex(root.right);
        System.out.print(root.val + ", ");
    }

    public static void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void print(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
