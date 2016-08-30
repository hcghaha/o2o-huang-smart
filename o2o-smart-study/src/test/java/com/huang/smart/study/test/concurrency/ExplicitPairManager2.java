package com.huang.smart.study.test.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-16
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class ExplicitPairManager2 extends PairManager {
    private ReentrantLock lock = new ReentrantLock();

    public synchronized void increment(){
        lock.lock();
        Pair tmp;
        try{
            p.incrementX();
            p.incrementY();
            tmp = getPair();
        }finally {
            lock.unlock();
        }
        store(tmp);
    }
}