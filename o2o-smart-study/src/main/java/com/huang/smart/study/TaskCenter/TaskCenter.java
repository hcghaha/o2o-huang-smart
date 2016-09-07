package com.huang.smart.study.TaskCenter;

import java.util.Properties;

import com.huang.smart.study.TaskCenter.Task.DataSyncABean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Goal：the function of class
 * User:  huangchaoguang
 * Date: 2016/9/2
 * Time: 10:29
 * Version: 1.0
 */
import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;

public class TaskCenter {

    public static void main(String[] args) throws Exception {

        // 初始化Spring
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath*:spring-config.xml");

        // 初始化调度工厂
        TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();

        Properties p = new Properties();
        p.put("zkConnectString", "localhost:2181");
        p.put("rootPath", "/tbschedule/test");
        p.put("zkSessionTimeout", "60000");
        p.put("userName", "zookeeper");
        p.put("password", "zookeeper");
        p.put("isCheckParentPath", "true");

        scheduleManagerFactory.setApplicationContext(ctx);

        scheduleManagerFactory.init(p);
        DataSyncABean dataSyncABean =  (DataSyncABean)ctx.getBean("dataSyncABean") ;
        System.out.println(dataSyncABean.getStr());
        System.out.println(ctx.getBeanDefinitionCount());
        System.out.println("haha");
    }
}