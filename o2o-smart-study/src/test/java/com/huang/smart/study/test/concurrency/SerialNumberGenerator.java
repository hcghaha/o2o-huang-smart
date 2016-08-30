package com.huang.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-15
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static synchronized int nextSerialNumber(){
        return serialNumber++;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    int value = SerialNumberGenerator.nextSerialNumber();
                    System.out.println("t1:"+value);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++)  {
                    int value = SerialNumberGenerator.nextSerialNumber();
                    System.out.println("t2:"+value);
                }
            }
        });
        t2.start();
    }

}
