package com.jd.o2o.smart.study.test.concurrency.executors;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ThreadPoolExecutorsTest {
//    public static void main(String[] args) {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(index*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            exec.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(index);
//                }
//            });
//        }
//    }
//}

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ThreadPoolExecutorsTest {
//    public static void main(String[] args) {
//        ExecutorService exec = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            exec.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(index);
//                    try{
//                        Thread.sleep(2000);
//                    } catch (InterruptedException ex){
//                        ex.printStackTrace();
//                    }
//                }
//            });
//        }
//    }
//}


//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.Executors;
//
//public class ThreadPoolExecutorsTest {
//    public static void main(String[] args) {
//        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
//        exec.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3L, TimeUnit.SECONDS);
//    }
//}

//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.Executors;
//
//public class ThreadPoolExecutorsTest {
//    public static void main(String[] args) {
//        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
//        exec.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 1 second, every 3 seconds execute");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//    }
//}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorsTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(index);
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        try{
           Thread.sleep(500);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}