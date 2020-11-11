package com.timmy.javalib._2gc;

/**
 * VM agrs: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 *
 * 参数解析：
 * -Xms20M ：初始化堆大小为20M
 * -Xmx20M ：设置堆最大分配内存20M
 * -Xmn10M ：设置新生代内存大小为10M
 * -XX:+PrintGCDetails ：打印GC的详细log日志
 * -XX:SurvivorRatio=8 ：eden：from：to=8：1:1
 */
public class _5MinorGCTest {

    private static final int _1M = 1024 * 1024;

    private static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1M];
        a2 = new byte[2 * _1M];
        a3 = new byte[2 * _1M];
        a4 = new byte[1 * _1M];
        printMemeory();
    }

    public static void main(String[] args) {
        testAllocation();
    }

    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}


