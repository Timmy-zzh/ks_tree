package com.timmy.testlib;

public class _04SynchornizedDemo {

    private Object lock = new Object();
    private int value = 0;

    public void setValue() {
        synchronized (lock) {
            value++;
        }
    }

    public static void main(String[] args) {

    }
}