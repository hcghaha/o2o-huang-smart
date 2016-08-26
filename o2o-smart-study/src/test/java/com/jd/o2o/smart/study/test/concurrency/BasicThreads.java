package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class BasicThreads {
    public static void main(String[] args) {
        LightOff launch = new LightOff();
        Thread thread = new Thread(launch);

        thread.start();
        System.out.println("Waiting for LightOff!");
    }
}
