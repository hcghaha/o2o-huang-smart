package com.jd.o2o.smart.study.test.concurrency.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-15
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger value = new AtomicInteger(0);

    public int getValue(){
        return value.get();
    }

    public void incrementValue(){
        value.addAndGet(2);
    }
    public void run(){
        while(true){
            incrementValue();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);
        while(true){
            if (ait.getValue()%2!=0){
                System.out.println("error!");
                System.exit(0);
            }
        }
    }
}
