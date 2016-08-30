package com.huang.smart.study.test.concurrency;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class ResponsiveUI extends Thread{
    private static volatile double d = 1;

    public ResponsiveUI(){
        this.setDaemon(true);
        this.start();
    }

    public void run(){
        while(true){
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }

    /**
     * Created with IntelliJ IDEA.
     * User: huangchaoguang
     * Date: 16-8-16
     * Time: 下午1:56
     * To change this template use File | Settings | File Templates.
     */
    public static class ExplicitPairManager2 {
    }
}
