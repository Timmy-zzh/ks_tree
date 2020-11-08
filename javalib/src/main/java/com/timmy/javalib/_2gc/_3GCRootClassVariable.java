package com.timmy.javalib._2gc;


/**
 * �߳�GCRoot����3:��֤��Ա�����Ƿ������ΪGCRoot
 * <p>
 * ��־��ӡ�����
 * <p>
 * ��ʼʱ
 * free is 117M ,total is 119M
 * ������Ϊnull
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
        System.out.println("��ʼʱ");
        printMemeory();

        _3GCRootClassVariable g = new _3GCRootClassVariable(4 * _10MB);
        g.staticVariable = new _3GCRootClassVariable(4 * _10MB);

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
