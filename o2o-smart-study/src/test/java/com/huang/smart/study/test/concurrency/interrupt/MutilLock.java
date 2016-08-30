package com.huang.smart.study.test.concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 */
public class MutilLock {
    private int count;
    public MutilLock(int count) {
        this.count = count;
    }
    public synchronized void f1(){
        if (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + " f1() call f2() with count="+count);
            f2();
        }
    }

    public synchronized void f2(){
        if (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + " f2() call f1() with count="+count);
            f1();
        }
    }

    public static void main(String[] args){
        final MutilLock lock = new MutilLock(10);
        new Thread(){
            @Override
            public void run() {
                lock.f1();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread run 1");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                lock.f2();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread run 2");
            }
        }.start();

    }

}
