package com.huang.smart.study.test.mysql;

import java.sql.*;

/**
 * Created by huangchaoguang on 2016/9/1.
 */
public class TestBatchInsert {
    public static void main(String args[]){
        Connection conn = null;
        String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&connectTimeout=1000&autoReconnect=true";
        try{
            Class.forName("com.mysql.jdbc.Driver"); //动态加载mysql驱动
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(dbUrl, "root", "123456");

              conn.setAutoCommit(false);

            String BillNumer_pre="2016090", BillNumer_end="1001", BuildDate="20160901",Customer="A", GoodsName="咖啡";
            float Amount=3.5f, SaleMoney=350;


            String sql = "insert into tbOrder (BillNumber, BuildDate,Customer, GoodsName,Amount, SaleMoney) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            final int batchSize = 1000;
            int count = 0;
            while (count < 8000) {
                ps.setString(1, BillNumer_pre + BillNumer_end);
                ps.setString(2, BuildDate);
                ps.setString(3, Customer);
                ps.setString(4, GoodsName);
                ps.setFloat(5,Amount);
                ps.setFloat(6,SaleMoney);
                ps.addBatch();
                BillNumer_end = String.valueOf(Integer.parseInt(BillNumer_end) +1);
                if(++count % batchSize == 0) {
                    BuildDate = String.valueOf(Integer.parseInt(BuildDate) +1);
                    Amount = Amount + 1;
                    SaleMoney = SaleMoney + 10;
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            conn.commit();
            ps.close();
            conn.close();

        }catch(Exception e){
            System.out.println("happened exception!");
            System.out.println(e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    System.out.println("记录回滚失败的日志！");
                    System.out.println(ex);
                }
            }
        } finally {
         }
    }

}
