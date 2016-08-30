package com.huang.smart.study.test.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-5
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r){
        Thread thread = new Thread(r);
        thread.setDaemon(true);
         return thread;
    }
}
