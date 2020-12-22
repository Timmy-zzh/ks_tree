package com.timmy.javalib._4concurrent;

public class _02_1SynchronizedMethod {

    private int num = 0;

    private void sum() {
        synchronized (this) {
            num += 1;
        }
    }


    public static void main(String[] args) {
//        _02SynchronizedMethod demo1 = new _02SynchronizedMethod();
//        _02SynchronizedMethod demo2 = new _02SynchronizedMethod();
//
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo1.printLog();
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo2.printLog();
//            }
//        });
//
//        thread1.start();
//        thread2.start();

        final _02_1SynchronizedMethod demo1 = new _02_1SynchronizedMethod();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.printLog();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.printLog();
            }
        });

        thread1.start();
        thread2.start();
    }

    private synchronized void printLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread Name:" + Thread.currentThread().getName() + " --- i:" + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
        }
    }
}
