package com.timmy.javalib._4concurrent;

public class _01TwoThread {

    public static void main(String[] args) {
        _01TwoThread demo = new _01TwoThread();
        demo.test();
    }

    public int x = 0;
    public int y = 0;

    private void test() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                int a = x;
                y = 1;
                System.out.println("thread1--  x:" + x + " ,y:" + y + " ,a:" + a);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int b = y;
                x = 2;
                System.out.println("thread2===x:" + x + " ,y:" + y + " ,b:" + b);
            }
        };
        thread1.start();
        thread2.start();
    }

//    private volatile int value = 0;
//    private void setValue() {
//        value = 1;
//    }
//
//    public int getValue() {
//        return value;
//    }

    private int value = 0;

    private void setValue() {
        synchronized (this) {
            value = 1;
        }
    }

    public int getValue() {
        synchronized (this) {
            return value;
        }
    }
}