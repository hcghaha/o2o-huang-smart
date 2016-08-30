package com.huang.smart.study.test.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-10
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();
        System.out.println("thread.isDaemon() = " + thread.isDaemon() + ".");
        TimeUnit.SECONDS.sleep(1);
    }
}

class Daemon implements Runnable {
    private Thread[] threads = new Thread[10];

    public void run() {
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println("DaemonSpawn " + i + " is started");
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("thread[" + i + "].isDaemon() = " + threads[i].isDaemon());
        }
    }
}

class DaemonSpawn implements Runnable {
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}
