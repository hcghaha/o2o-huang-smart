package com.jd.o2o.smart.study.test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: huangchaoguang
 * Date: 16-8-3
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 * 功能：测试数据事务
 */
public class TestMysql {
    public static void main(String args[]){
        Connection conn = null;
        String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&connectTimeout=1000&autoReconnect=true";
        try{
            Class.forName("com.mysql.jdbc.Driver"); //动态加载mysql驱动
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(dbUrl, "root", "123456");

            conn.setAutoCommit(false);

            Statement stmt = conn.createStatement();
            String sql = "select * from tb_testvarchar";

            String sql2 = "update tb_testvarchar set name = 'test2' where id = 1";

            String sql3 = "update tb_testvarchar set name = 'test3' where id = 1";

            int result = stmt.executeUpdate(sql2);
            System.out.println("result2=" + result);

            ResultSet rs = stmt.executeQuery(sql);

            result = stmt.executeUpdate(sql3);
            System.out.println("result3=" + result);

            while(rs.next()){
                System.out.println(rs.getInt(1) + rs.getString(2));
            }

            conn.commit();

        }catch(Exception e){
            System.out.println("happened exception!");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    System.out.println("记录回滚失败的日志！");
                }
            }
        }
     }

}
