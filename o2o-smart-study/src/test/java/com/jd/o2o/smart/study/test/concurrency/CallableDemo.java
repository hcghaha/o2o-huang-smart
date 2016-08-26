package com.jd.o2o.smart.study.test.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */
public class CallableDemo {
    public static void main(String args[]) {
        List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        try {
            for (Future<String> res : results)
                System.out.println(res.get());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("1");
            exec.shutdown();
        }
    }

}
