package com.jd.o2o.smart.study.test.concurrency.cooperation;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangchaoguang on 2016/8/19.
 */
public class Car {
    private volatile boolean waxed = false;

    public synchronized void WaxOn() {
        waxed = true;
        notify();
    }

    public synchronized void WaxOff() {
        waxed = false;
        notify();
    }

    public synchronized void WaitingForWax() throws InterruptedException {
        while (waxed == false) {
            wait();
        }
    }

    public synchronized void WaitingForBuffing() throws InterruptedException {
        while (waxed == true) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.WaxOn();
                car.WaitingForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.WaitingForWax();
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.WaxOff();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");

    }


}
