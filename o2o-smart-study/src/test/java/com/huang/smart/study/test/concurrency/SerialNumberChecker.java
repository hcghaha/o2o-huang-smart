package com.huang.smart.study.test.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 原子性试验
 * User: huangchaoguang
 * Date: 16-8-15
 * Time: 上午11:46
 * version：1.1
 */

class CircularSet{
    private int[] arr;
    private int len;
    private  int index;

    public CircularSet(int len){
        this.len = len;
        index = 0;
        arr = new int[len];
        for (int i=0; i<len; i++){
            arr[i] = -1;
        }
    }

    public synchronized void add(int val) {
        arr[index] = val;
        index = ++index % len;
    }

     public synchronized boolean contain(int val){
         for (int i = 0; i < len; i++) {
             if (val == arr[i])
                 return true;
         }
         return false;
     }
}


public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialCheckor implements Runnable {
        public void run(){
            while(true){
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contain(serial)){
                    System.out.println("Duplicate "+ serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
             exec.execute(new SerialCheckor());
        }

    }
}
