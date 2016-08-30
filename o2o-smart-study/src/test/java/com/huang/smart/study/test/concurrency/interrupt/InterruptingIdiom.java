package com.huang.smart.study.test.concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Java编程思想-检查中断
 * User: huangchaoguang
 * Date: 16-8-18
 * Time: 上午10:06
 * Version：1.0
 */

class NeedCleanup{
    private final int id;

    public NeedCleanup(int id){
        this.id =  id;
        System.out.println("NeedCleanup id: "+ id);
    }

    public void cleanup(){
        System.out.println("clean up id：" + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                NeedCleanup n1 = new NeedCleanup(1);
                try {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedCleanup n2 = new NeedCleanup(2);
                    try{
                        System.out.println("Caculating");
                        for (int i = 1; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("finish time-consuming operation");
                    }finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test!");
        }catch (Exception e){
            System.out.println("Exiting via InterruptedException");
        }

    }
}


public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        if (args == null || args.length <= 0) {
            System.out.println("usage: java InterruptingIdiom delay-in-ms");
            System.exit(-1);
        }
        Thread t1 = new Thread(new Blocked3());
        t1.start();
        TimeUnit.SECONDS.sleep(Integer.valueOf(args[0]));
        t1.interrupt();
    }
}
