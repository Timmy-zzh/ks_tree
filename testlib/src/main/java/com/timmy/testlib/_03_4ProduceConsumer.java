package com.timmy.testlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class _03_4ProduceConsumer {

    static class Produce implements Runnable {

        final List<Integer> list;
        Random random = new Random();

        public Produce(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while (list.size() > 0) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    list.add(random.nextInt(50));
                    System.out.println("Thread:" + Thread.currentThread().getName() +
                            " ,produce: " + list.get(0));
                    list.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        final List<Integer> list;

        public Consumer(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while (list.size() <= 0) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread:" + Thread.currentThread().getName() +
                            " ,Consumer: " + list.remove(0));
                    list.notifyAll();
                }
            }
        }

        public static void main(String[] args) {
            List<Integer> list = new ArrayList<>();
            Thread produce = new Thread(new Produce(list));
            Thread consume = new Thread(new Consumer(list));

            produce.start();
            consume.start();

            BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);
            try {
                queue.put(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
