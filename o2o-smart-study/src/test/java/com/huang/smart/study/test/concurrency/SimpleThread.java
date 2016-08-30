package com.huang.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-10
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread(){
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString(){
        return "#" + getName() + "(" + countDown + ").";
    }

    public void run(){
        while(true){
            System.out.println(this);
            countDown--;
            if (countDown<=0){
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }

    }
}
