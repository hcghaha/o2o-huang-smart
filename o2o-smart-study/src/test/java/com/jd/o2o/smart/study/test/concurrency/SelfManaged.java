package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-10
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread thread = new Thread(this);
    public SelfManaged(){
        thread.start();
    }

    public String toString(){
        return Thread.currentThread().getName() + "(" + countDown + ") ";
    }

    public void run(){
        while(true){
            System.out.println(this);
            countDown--;
            if (countDown <= 0){
                break;
            }
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            new SelfManaged();
        }
    }


}
