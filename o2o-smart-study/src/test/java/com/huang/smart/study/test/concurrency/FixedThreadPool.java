package com.huang.smart.study.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i<5; i++) {
            exec.execute(new LightOff());
        }
        exec.shutdown();



    }
}
