package com.timmy.javalib._4concurrent;

public class _02_3Synchronized {

    private Object lock = new Object();

//    public static _02_3Synchronized getInstance(){
//        synchronized (_02_3Synchronized.class){
//
//        }
//    }


    public static void main(String[] args) {
        final _02_3Synchronized demo1 = new _02_3Synchronized();

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
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread Name:" + Thread.currentThread().getName() + " --- i:" + i);
            }
        }
    }
}
