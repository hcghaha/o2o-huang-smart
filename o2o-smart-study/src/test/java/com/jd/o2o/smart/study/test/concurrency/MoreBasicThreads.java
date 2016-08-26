package com.jd.o2o.smart.study.test.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public class MoreBasicThreads {
    public static void main(String args[]){
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i<10; i++){
            Thread thread = new Thread(new LightOff());
            list.add(thread);
        }
        for (Thread thread : list){
            thread.start();
        }
    }
}
