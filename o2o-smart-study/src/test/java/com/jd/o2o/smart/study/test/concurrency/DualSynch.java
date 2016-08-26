package com.jd.o2o.smart.study.test.concurrency;

/**
 * 在其他对象上同步
 * User: huangchaoguang
 * Date: 16-8-16
 * Time: 下午2:38
 * Version：1.0
 */
public class DualSynch {
    private Object syncObject = new Object();
    public synchronized void f(){
        for (int i=0; i<5; i++){
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g(){
        synchronized(syncObject){
            for (int i=0; i<5; i++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
