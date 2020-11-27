package com.atguigu.juc3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Window {
    static int tickets = 100;

    static String string = "";

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (tickets > 0) {
                    synchronized (string) {
                        try {
                            if (tickets > 0) {
                                System.out.println(Thread.currentThread().getName()
                                        + "卖出了第" + (tickets--) + "张票");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        //关闭线程池
        service.shutdown();
    }

}
