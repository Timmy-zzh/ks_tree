package com.timmy.javalib._2gc;


/**
 * 线程GCRoot场景2:方法区静态变量
 * <p>
 * 日志打印结果：
 * <p>
 * 开始时
 * free is 117M ,total is 119M
 * 对象置为null
 * free is 77M ,total is 119M
 */
public class _2GCRootStaticVariable {

    private static int _10MB = 1024 * 1024 * 10;
    private byte[] memory;
    private static _2GCRootStaticVariable staticVariable;

    public _2GCRootStaticVariable(int size) {
        this.memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println("开始时");
        printMemeory();

        _2GCRootStaticVariable g = new _2GCRootStaticVariable(4 * _10MB);
        g.staticVariable = new _2GCRootStaticVariable(4 * _10MB);

        g = null;
        System.gc();
        System.out.println("对象置为null");
        printMemeory();
    }

    //打印当前jvm剩余空间和总的空间大小
    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
