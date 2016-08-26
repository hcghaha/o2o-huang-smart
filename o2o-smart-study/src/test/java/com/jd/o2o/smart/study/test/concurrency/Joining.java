package com.jd.o2o.smart.study.test.concurrency;

/**
 * 测试线程Join方法
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 下午1:38
 * Version: 1.0
 */

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int duration){
        super(name);
        this.duration = duration;
        this.start();
    }
    @Override
    public void run(){
        try{
            sleep(duration);
        } catch(InterruptedException ex){
            System.out.println(getName() + " Interrupted!");
            return;
        }
        System.out.println(getName() + " waken up!");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        this.start();

    }

    public void run(){
        try{
            sleeper.join();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println(getName() + " join completed!");
    }

}


public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper1 = new Sleeper("sleeper1",1500);
        Sleeper sleeper2 = new Sleeper("sleeper2",1500);
        Joiner joiner1 = new Joiner("joiner1", sleeper1);
        Joiner joiner2 = new Joiner("joiner2", sleeper2);
        sleeper1.interrupt();

    }
}



