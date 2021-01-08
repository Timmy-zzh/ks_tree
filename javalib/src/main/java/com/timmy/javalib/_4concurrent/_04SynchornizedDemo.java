package com.timmy.javalib._4concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class _04SynchornizedDemo {

    private Object lock = new Object();
    private int value = 0;

    public void setValue() {
        synchronized (lock) {
            value++;
        }
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        atomicInteger.get();
        atomicInteger.compareAndSet(0,1);
        atomicInteger.getAndAdd(1);
        atomicInteger.decrementAndGet();
        atomicInteger.incrementAndGet();

    }
}