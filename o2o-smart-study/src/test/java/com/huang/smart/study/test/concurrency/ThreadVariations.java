package com.huang.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-11
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        System.out.println("hello world!");
    }
}

//Using a named Inner class
class InnerThread1 {
   private int countDown = 5;
   private Inner inner;

   private class Inner extends Thread {
       public Inner(String name) {
           super(name);
           start();
       }
       public void run() {
           try {
               while (true) {
                   System.out.println(this);
                   countDown--;
                   if (countDown <= 0) {
                       return;
                   }
                   sleep(100);
               }
           } catch (InterruptedException e) {
               System.out.println("interrupted");
           }
       }
       public String toString() {
           return getName() + ":" + countDown;
       }
   }

   public InnerThread1(String name){
       inner = new Inner(name);
   }
}

//Using an anonymous inner  class:
class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    public InnerThread2(String name){
        t = new Thread(name){
            @Override
            public void run(){
                try{
                    while (countDown > 0) {
                        System.out.println(getName() + " : " + countDown);
                        countDown--;
                    }
                    sleep(100);
                }catch(InterruptedException ex){
                    System.out.println("Interrupted!");
                }
            }
        };
        t.start();
    }
}

// Using a named Runnable implementation:
class InnerRunnable1{
    private int countDown = 5;
    private Inner inner;
    public InnerRunnable1(String name){
        inner = new Inner(name);
    }
    class Inner implements Runnable{
        Thread t;
        public Inner(String name){
            t = new Thread(this,name);
            t.start();
        }
        @Override
        public void run(){
           try{
               while(countDown>0){
                   System.out.println(this);
                   countDown--;
               }
               t.sleep(100);
           } catch(InterruptedException ex){
               System.out.println("Interrrupted!");
           }
        }

        public String toString(){
            return t.getName() + " : " + countDown;
         }
    }
}
