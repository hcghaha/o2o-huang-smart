package com.huang.smart.study.test.concurrency.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * java编程思想-线程中断
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 下午2:38
 * Version：1.0
 */
class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex){
            System.out.println("SleepBlocked interruptedException");
        }
        System.out.println("Existing SleepBlocked run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;
    public IOBlocked(InputStream in){
        this.in = in;
    }
    @Override
    public void run() {
        try {
            System.out.println("Waiting for read(): ");
                in.read();
        }catch (IOException e){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from I/O blocked");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Existing IOBlocked run()");
    }
}

class SynchronizedBlock implements Runnable{
    public synchronized void func(){
        while(true){
            Thread.yield();
        }
    }

    public SynchronizedBlock(){
        new Thread(){
            @Override
            public void run() {
                func();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("try to call func(): ");
        func();
        System.out.println("Existing SynchronizedBlock run()");
    }
}



public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    public static void test(Runnable r) throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + f.getClass().getName());
        f.cancel(true);
        System.out.println("Interruping sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlock());

        TimeUnit.SECONDS.sleep(10);

        System.out.println("Aborting with System.exit(0).");
        System.exit(0);
    }

}
