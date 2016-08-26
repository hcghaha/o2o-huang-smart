package com.jd.o2o.smart.study.test.exception;

import java.io.IOException;
import java.util.Scanner;

/**
 * 测试异常堆栈信息的打印 printStackTrace
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 下午2:08
 * Version: 1.0
 */
public class TestException {
    public static void main(String[] args) {
        try{
//            int j = 9/0;
            throw new IOException();

        }catch (Exception e){
            e.printStackTrace(System.out);
        }

//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        System.out.println(str);
    }
}
