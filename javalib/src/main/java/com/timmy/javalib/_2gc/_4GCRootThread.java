package com.timmy.javalib._2gc;


/**
 * �߳�GCRoot����4:��֤��Ծ���߳��Ƿ������ΪGCRoot
 * ��֤��ִ֤�й����У�����GC�������ڴ��Ƿ񱻻��գ�
 * <p>
 * ��־��ӡ�����
 * <p>
 * ��ʼʱ
 * free is 117M ,total is 119M
 * �߳�ִ�й�����GC
 * free is 37M ,total is 119M
 * �߳�ִ�н�������GC
 * free is 117M ,total is 119M
 */
public class _4GCRootThread {

    private static int _10MB = 1024 * 1024 * 10;
    private byte[] memory;
    private _4GCRootThread staticVariable;

    public _4GCRootThread(int size) {
        this.memory = new byte[size];
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("��ʼʱ");
        printMemeory();

        TestRunnable testRunnable = new TestRunnable(new _4GCRootThread(8 * _10MB));
        Thread thread = new Thread(testRunnable);
        thread.start();

        System.gc();
        System.out.println("�߳�ִ�й�����GC");
        printMemeory();

        //��֤��ǰ�߳̽������ٵ��ú���Ĵ���
        thread.join();
        testRunnable = null;
        System.gc();
        System.out.println("�߳�ִ�н�������GC");
        printMemeory();
    }

    //��ӡ��ǰjvmʣ��ռ���ܵĿռ��С
    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }

    private static class TestRunnable implements Runnable {

        private _4GCRootThread gcRootThread;

        public TestRunnable(_4GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
