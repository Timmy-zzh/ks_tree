package com.timmy.javalib._4concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _05ThreadPool {

    public static void main(String[] args) {
//        singleThreadPool();
        cacheThreadPool();
//        newFixedThreadPool();
//        newScheduledThreadPool();
    }

    private static void newScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Date now = new Date();
                System.out.println("Thread:" + Thread.currentThread().getName() +
                        " ,timer:" + now.toString());
            }
        }, 500, 500, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
    }

    private static void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread:" + Thread.currentThread().getName() +
                            " ,running task:" + taskId);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void cacheThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread:" + Thread.currentThread().getName() +
                            " ,running task:" + taskId);
                    try {
                        // simple io task
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void singleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread:" + Thread.currentThread().getName() +
                            " ,running task:" + taskId);
                }
            });
        }
    }
}
