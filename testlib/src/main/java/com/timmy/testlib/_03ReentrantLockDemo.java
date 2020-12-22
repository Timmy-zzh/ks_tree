package com.timmy.testlib;

import java.util.concurrent.locks.ReentrantLock;

public class _03ReentrantLockDemo {

    ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        _03ReentrantLockDemo demo1 = new _03ReentrantLockDemo();

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

    private void printLog() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread Name:" + Thread.currentThread().getName() + " --- i:" + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
