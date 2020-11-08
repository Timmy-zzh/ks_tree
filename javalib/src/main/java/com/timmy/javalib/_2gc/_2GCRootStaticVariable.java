package com.timmy.javalib._2gc;


/**
 * �߳�GCRoot����2:��������̬����
 * <p>
 * ��־��ӡ�����
 * <p>
 * ��ʼʱ
 * free is 117M ,total is 119M
 * ������Ϊnull
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
        System.out.println("��ʼʱ");
        printMemeory();

        _2GCRootStaticVariable g = new _2GCRootStaticVariable(4 * _10MB);
        g.staticVariable = new _2GCRootStaticVariable(4 * _10MB);

        g = null;
        System.gc();
        System.out.println("������Ϊnull");
        printMemeory();
    }

    //��ӡ��ǰjvmʣ��ռ���ܵĿռ��С
    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
