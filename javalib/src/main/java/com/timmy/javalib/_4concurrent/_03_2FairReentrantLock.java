package com.timmy.javalib._4concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class _03_2FairReentrantLock implements Runnable {

    private int shareNum = 0;
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (shareNum < 10) {
            try {
                reentrantLock.lock();
                shareNum++;
                System.out.println(Thread.currentThread().getName() + " ，shareNum:" + shareNum);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        _03_2FairReentrantLock reentrantLock = new _03_2FairReentrantLock();
        Thread thread1 = new Thread(reentrantLock);
        Thread thread2 = new Thread(reentrantLock);
        Thread thread3 = new Thread(reentrantLock);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
