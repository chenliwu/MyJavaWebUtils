package com.chenlw.java.web.utils.jdbc;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chenlw
 * @date 2019/10/26
 */
public class JdbcPerformanceTester {


    public static void main(String[] args) {
        try {
            testSelect();
        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
        }

    }

    public static void testSelect() throws Exception {

        String sql = "SELECT \n" +
                "CLNO AS \"班级编号\",\n" +
                "SNO AS \"学号\",\n" +
                "SNAME AS \"姓名\",\n" +
                "SEX AS \"性别\",\n" +
                "SBIR AS \"生日\"\n" +
                "FROM student";

        List<String[]> list = new LinkedList<>();
        long sqlExecuteTime = System.currentTimeMillis();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            // 一次检索的批量值
            statement.setFetchSize(20);

            System.out.println("=================  SQL开始执行  ================");
            rs = statement.executeQuery(sql);
            System.out.println("SQL执行所耗费时间: " + (System.currentTimeMillis() - sqlExecuteTime) + " ms");
            System.out.println("=================  SQL执行完毕  ================");
            System.out.println();

            long programExecuteTime = System.currentTimeMillis();
            System.out.println("=================  程序开始处理从数据库查回来的数据  ================");
            // 优化程序处理性能，当数据量大，setFetchSize可以提升程序处理的效率
            // rs.setFetchSize(10000);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String valueTemp;
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    valueTemp = rs.getString(j + 1);
                    if (valueTemp != null) {
                        row[j] = row[j] = valueTemp;
                    } else {
                        row[j] = "#NULL";
                    }
                }
                list.add(row);
            }
            System.out.println("后台程序处理数据所耗费时间: " + (System.currentTimeMillis() - programExecuteTime) + " ms");
            System.out.println("=================  程序处理数据完毕 ================");
            System.out.println("查询数据量：" + list.size());
        } catch (Exception e) {
            throw new Exception("ERROR:" + e.getMessage(), e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    /**
     * 加载数据库驱动程序字符串
     * SQL Server:com.microsoft.sqlserver.jdbc.SQLServerDriver
     * MySQL:com.mysql.jdbc.Driver
     */
    private static final String JDriver = "com.mysql.jdbc.Driver";
    /**
     * 数据库连接字符串
     * SQL Server连接字符串:jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DB_MyShare
     * MySQL连接字符串:jdbc:mysql://localhost:3306/testmybatis
     */
    private static final String connectionString = "jdbc:mysql://localhost:3307/test_jdbc_performance";

    /**
     * 数据库登录账号
     */
    private static final String USER = "root";

    /**
     * 数据库登录密码
     */
    private static final String PASSWORD = "123456";

    /**
     * 获取数据库连接对象
     *
     * @return 数据库连接对象
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Connection connection;
        try {
            try {
                //加载数据库驱动程序
                Class.forName(JDriver);
            } catch (ClassNotFoundException e) {
                // e.printStackTrace();
                throw new Exception("加载数据库驱动程序失败...");
            }
            //获取数据库连接
            connection = DriverManager.getConnection(connectionString, USER, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("建立数据库连接失败...");
        }
        return connection;
    }

}
