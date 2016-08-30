package com.huang.smart.study.test.infclass.TestInter;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-17
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        MyInterface inter = new MyClass();
        testInter(inter, "hello world");
    }

    public static void testInter(MyInterface myInterface, String words){
        myInterface.task(words);
    }


}
