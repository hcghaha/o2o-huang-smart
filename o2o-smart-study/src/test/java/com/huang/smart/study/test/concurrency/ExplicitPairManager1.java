package com.huang.smart.study.test.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-16
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 */
public class ExplicitPairManager1 extends PairManager {
    private ReentrantLock lock = new ReentrantLock();

    public synchronized void increment(){
        lock.lock();
        try{
            p.incrementX();
            p.incrementY();
            store(getPair());
        }finally {
            lock.unlock();
        }
    }
}
