package com.huang.smart.study.test.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Goal：the function of class
 * User:  huangchaoguang
 * Date: 2016/9/14
 * Time: 17:50
 * Version: 1.0
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888, 1);

        while(true){
            Socket socket = serverSocket.accept();
            InputStream im = socket.getInputStream();
            byte[] b = new byte[10];
            im.read(b);
            System.out.println("1b[0]:" + b[0]);
            System.out.println("1b[1]:" + b[1]);
            Thread.sleep(1000);
            im.read(b);
            System.out.println("2b[0]:" + b[0]);
            System.out.println("2b[1]:" + b[1]);
            im.close();
            socket.close();

        }


    }
}
