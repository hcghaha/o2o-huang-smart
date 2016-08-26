package com.jd.o2o.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-12
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
public class TestTel {
    public static void main(String[] args) {
        int[] arr = new int[]{8,2,1,0,3};
        int[] index = new int[]{2,0,3,2,4,0,1,3,2,3,3};
        String tel = "";
        for (int i : index){
            tel += arr[i];
        }
        System.out.println(tel);
    }
}
