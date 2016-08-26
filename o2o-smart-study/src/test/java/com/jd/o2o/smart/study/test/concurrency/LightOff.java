package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public class LightOff implements Runnable{
    private int countDown = 10;
    private static int status = 0;

    private final int id = status++;

    public String getStatus(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "lightOff!") + ").";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(getStatus());
            Thread.yield();
        }

    }
}
