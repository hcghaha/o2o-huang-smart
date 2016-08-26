package com.jd.o2o.smart.study.test.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-5
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDaemons implements Runnable {

     public void run(){
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException ex) {
            System.out.println("TimeUnit sleep() Exception!");
        }
    }

    public static void main(String args[]) throws InterruptedException{
//        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++){
            Thread thread = new Thread(new SimpleDaemons());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All Daemon started!");
        TimeUnit.MILLISECONDS.sleep(175);
    }

}
