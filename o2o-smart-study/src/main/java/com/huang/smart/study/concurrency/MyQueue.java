package com.huang.smart.study.concurrency;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成消费模型 demo
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 上午10:58
 * Version 1.0
 */
public class MyQueue {
    // 1. 创建一个容器
    private LinkedList<Object> list = new LinkedList<Object>();
    // 2. 设置容器的大小
    private AtomicInteger count = new AtomicInteger();
    // 3. 设定容器的上限、下限
    private final int minSize = 0;
    private final int maxSize;

    public MyQueue(int maxSize){
        this.maxSize = maxSize;
    }

    // 4. 放置元素到容器中
    public synchronized void put(Object obj){
        try {
            if (maxSize == count.get())
                this.wait();
            list.add(obj);
            count.incrementAndGet();
            this.notify();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // 5. 从容器中取出元素
    public synchronized Object get(){
        Object obj = null;
        try{
            if (minSize == count.get())
                this.wait();
            obj = list.removeFirst();
            count.decrementAndGet();
            this.notify();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return obj;
    }

    // 6. 获取当前容器的size
    public int getSize(){
        return count.get();
    }

    public static void main(String[] args){
        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("g");
                System.out.println("存放元素g");
                myQueue.put("f");
                System.out.println("存放元素f");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("取出第一个元素：" + myQueue.get());
                System.out.println("取出第二个元素：" + myQueue.get());
            }
        });

        t1.start();
        t2.start();

    }

}
