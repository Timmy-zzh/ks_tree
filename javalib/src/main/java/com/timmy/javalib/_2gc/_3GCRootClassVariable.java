package com.timmy.javalib._2gc;


/**
 * 线程GCRoot场景3:验证成员变量是否可以作为GCRoot
 * <p>
 * 日志打印结果：
 * <p>
 * 开始时
 * free is 117M ,total is 119M
 * 对象置为null
 * free is 117M ,total is 119M
 */
public class _3GCRootClassVariable {

    private static int _10MB = 1024 * 1024 * 10;
    private byte[] memory;
    private _3GCRootClassVariable staticVariable;

    public _3GCRootClassVariable(int size) {
        this.memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println("开始时");
        printMemeory();

        _3GCRootClassVariable g = new _3GCRootClassVariable(4 * _10MB);
        g.staticVariable = new _3GCRootClassVariable(4 * _10MB);

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
