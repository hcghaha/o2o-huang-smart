package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class MainThread {
    public static void main(String[] args) {
        LightOff launch = new LightOff();
        launch.run();
    }
}
