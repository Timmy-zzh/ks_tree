package com.timmy.javalib._4concurrent;

public class _02_2SynchronizedMethod {

    public static void main(String[] args) {
        final _02_2SynchronizedMethod demo1 = new _02_2SynchronizedMethod();
        final _02_2SynchronizedMethod demo2 = new _02_2SynchronizedMethod();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.printLog();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.printLog();
            }
        });

        thread1.start();
        thread2.start();
    }

    private static synchronized void printLog() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread Name:" + Thread.currentThread().getName() + " --- i:" + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
        }
    }
}
