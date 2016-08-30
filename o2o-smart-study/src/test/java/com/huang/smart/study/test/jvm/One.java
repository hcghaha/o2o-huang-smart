package com.huang.smart.study.test.jvm;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-4
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class One {
    public static void main(String[] args) {
        System.out.println("One.main");

        Two.main(args);
    }
}

class Two {
   public static void main(String[] args) {
       System.out.println("Two.main");
   }
}
