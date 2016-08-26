package com.jd.o2o.smart.study.test.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程中发生异常的捕获，此外，在main函数中 try/catch是没有效果。
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run(){
        throw new RuntimeException();
    }

    public static void main(String[] args){
        try{
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new ExceptionThread());
        }catch (Exception e){
            System.out.println("异常");
        }
    }
}

