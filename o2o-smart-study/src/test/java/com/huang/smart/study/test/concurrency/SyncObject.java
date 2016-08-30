package com.huang.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-16
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(){
            public void run(){
                ds.f();
            }
        }.start();
        ds.g();
    }
}
