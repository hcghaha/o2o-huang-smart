package com.huang.smart.study.test.concurrency;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call(){
        return "result of TaskWithResult " + id;
    }
}
