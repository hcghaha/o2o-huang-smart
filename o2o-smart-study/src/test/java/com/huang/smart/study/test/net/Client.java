package com.huang.smart.study.test.net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Goal：the function of class
 * User:  huangchaoguang
 * Date: 2016/9/14
 * Time: 18:01
 * Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket=new Socket("localhost", 8888);
        socket.setTcpNoDelay(false);
        OutputStream os=socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Thread.sleep(3000);

        bufferedWriter.write("haha");
//        os.write(new byte[]{1,2});
//
//        os.write(new byte[]{3,4});         os.flush();

        os.close();
        socket.close();

    }
}
