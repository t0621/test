package com.atguigu.juc3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test {
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 6, 3L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    static Ticket ticket = new Ticket();

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            threadPoolExecutor.execute(() -> {
                for (int i1 = 0; i1 < 50; i1++) {
                    ticket.run();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}

class Ticket {
    static Integer ticket = 30;
    Lock lock = new ReentrantLock();

    public void run() {
        while (ticket > 0) {
            synchronized (Ticket.class) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket-- + "张票");
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            System.out.println("222");
            System.out.println("333");
        }
    }
}



