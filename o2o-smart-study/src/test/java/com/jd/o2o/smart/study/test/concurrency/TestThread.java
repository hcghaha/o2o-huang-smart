package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-5
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public class TestThread extends Thread {

    @Override
    public void run(){
        System.out.println("sub thread running!");
    }

    public static void main(String[] args) {
        TestThread test = new TestThread();
        test.start();
        System.out.println("main thread running!");
    }
}
