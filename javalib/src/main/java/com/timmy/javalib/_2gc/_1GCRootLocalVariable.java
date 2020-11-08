package com.timmy.javalib._2gc;


/**
 * 线程GCRoot场景1：java虚拟机栈中局部变量表中的引用对象
 * <p>
 * 日志打印结果：
 * <p>
 * 开始时
 * free is 117M ,total is 119M
 * 局部变量申请内存后gc
 * free is 37M ,total is 119M
 * 方法执行完，执行gc
 * free is 117M ,total is 119M
 */
public class _1GCRootLocalVariable {

    private int _10MB = 1024 * 1024 * 10;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        System.out.println("开始时");
        printMemeory();
        memthod();
        System.gc();
        System.out.println("方法执行完，执行gc");
        printMemeory();
    }

    //在方法中的局部变量申请内存
    private static void memthod() {
        _1GCRootLocalVariable g = new _1GCRootLocalVariable();
        System.gc();
        System.out.println("局部变量申请内存后gc");
        printMemeory();
    }

    //打印当前jvm剩余空间和总的空间大小
    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }


}
