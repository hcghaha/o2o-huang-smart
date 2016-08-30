package com.huang.smart.study.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-15
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class AtomicityTest implements Runnable {
    private  int value = 0;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void incrementValue() {
        value++;
        value++;
    }

    public void run() {
        while (true) {
            incrementValue();
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true) {
            int value = at.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
