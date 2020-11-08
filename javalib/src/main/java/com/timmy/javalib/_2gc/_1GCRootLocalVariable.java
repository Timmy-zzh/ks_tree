package com.timmy.javalib._2gc;


/**
 * �߳�GCRoot����1��java�����ջ�оֲ��������е����ö���
 * <p>
 * ��־��ӡ�����
 * <p>
 * ��ʼʱ
 * free is 117M ,total is 119M
 * �ֲ����������ڴ��gc
 * free is 37M ,total is 119M
 * ����ִ���ִ꣬��gc
 * free is 117M ,total is 119M
 */
public class _1GCRootLocalVariable {

    private int _10MB = 1024 * 1024 * 10;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        System.out.println("��ʼʱ");
        printMemeory();
        memthod();
        System.gc();
        System.out.println("����ִ���ִ꣬��gc");
        printMemeory();
    }

    //�ڷ����еľֲ����������ڴ�
    private static void memthod() {
        _1GCRootLocalVariable g = new _1GCRootLocalVariable();
        System.gc();
        System.out.println("�ֲ����������ڴ��gc");
        printMemeory();
    }

    //��ӡ��ǰjvmʣ��ռ���ܵĿռ��С
    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }


}
