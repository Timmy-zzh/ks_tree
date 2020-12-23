package com.timmy.javalib._4concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _03_3ReadWriteLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static String value = "0";

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Reader(), "read lock -1 ");
        Thread thread2 = new Thread(new Reader(), "read lock -2 ");
        Thread thread3 = new Thread(new Writer(), "write lock");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    readWriteLock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " ,i:" + i + " , value:" + value);
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 7; i += 2) {
                try {
                    readWriteLock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + " ,i:" + i);
                    value = value.concat(String.valueOf(i));
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }
        }
    }

}
