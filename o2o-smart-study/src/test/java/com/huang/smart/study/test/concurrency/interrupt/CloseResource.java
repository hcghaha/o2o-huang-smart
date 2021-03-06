package com.huang.smart.study.test.concurrency.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class CloseResource {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing socketInput: " + socketInput.getClass().getName());
        socketInput.close();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing System.in: " + System.in.getClass().getName());
        System.in.close();
    }
}
