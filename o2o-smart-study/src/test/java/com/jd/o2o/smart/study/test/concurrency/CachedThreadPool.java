package com.jd.o2o.smart.study.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class CachedThreadPool {
    public static void main(String args[]){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i < 10; i++){
            exec.execute(new LightOff());
        }
       exec.shutdown();

    }

}
