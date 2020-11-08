package com.timmy.javalib._2gc;


/**
 * 线程GCRoot场景4:验证活跃的线程是否可以作为GCRoot
 * 验证验证执行过程中，进行GC操作，内存是否被回收？
 * <p>
 * 日志打印结果：
 * <p>
 * 开始时
 * free is 117M ,total is 119M
 * 线程执行过程中GC
 * free is 37M ,total is 119M
 * 线程执行结束后再GC
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
        System.out.println("开始时");
        printMemeory();

        TestRunnable testRunnable = new TestRunnable(new _4GCRootThread(8 * _10MB));
        Thread thread = new Thread(testRunnable);
        thread.start();

        System.gc();
        System.out.println("线程执行过程中GC");
        printMemeory();

        //保证当前线程结束后再调用后面的代码
        thread.join();
        testRunnable = null;
        System.gc();
        System.out.println("线程执行结束后再GC");
        printMemeory();
    }

    //打印当前jvm剩余空间和总的空间大小
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
