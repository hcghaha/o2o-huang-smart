package com.jd.o2o.smart.study.test.concurrency.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java编程思想-中断-ReentrantLock
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 下午5:23
 * Version: 1.0
 */

class BlockMutex{
    private Lock lock = new ReentrantLock();
    public BlockMutex(){
        lock.lock();
    }

    public void f(){
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisiton in f()");
        }
    }
}

class Block2 implements Runnable{
    private BlockMutex blockMutex = new BlockMutex();
    @Override
    public void run() {
        System.out.println("Before BlockMutex f()");
        blockMutex.f();
        System.out.println("after blockMutex f()");
    }
}


public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Block2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }

}
