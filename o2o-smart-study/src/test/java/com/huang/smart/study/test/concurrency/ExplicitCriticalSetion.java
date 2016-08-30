package com.huang.smart.study.test.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-16
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public class ExplicitCriticalSetion {
    public static void main(String[] args) {
        PairManager p1 = new ExplicitPairManager1();
        PairManager p2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(p1, p2);
    }
}
