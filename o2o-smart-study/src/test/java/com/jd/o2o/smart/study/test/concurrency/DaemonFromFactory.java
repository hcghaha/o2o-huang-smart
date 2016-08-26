package com.jd.o2o.smart.study.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-5
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class DaemonFromFactory implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException ex) {
            System.out.println("TimeUnit sleep() Exception!");
        }
    }

    public static void main(String args[]) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("All Daemon Started!");
        TimeUnit.MILLISECONDS.sleep(175);
    }

}
