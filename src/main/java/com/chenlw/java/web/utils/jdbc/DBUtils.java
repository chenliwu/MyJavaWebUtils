package com.chenlw.java.web.utils.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库抽象工厂(提供执行增删改查的静态方法)
 * 在调用其静态方法时,最好用try/catch块包括起来,执行SQL语句的静态方法都进行了抛出异常的处理。
 * 此类为final类。直接调用其静态方法即可。
 *
 * @author charlie
 * @version 1.0.0
 * @日期 2018.6.16
 */

public final class DBUtils {

    public static void main(String[] args) {
        try {
            testGetScalar();

            // testBatchInsert();
            // testInsert();

        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
        }
    }


    public static void testBatchInsert() throws Exception {
        String sql = "insert into student(clno,sno,sname,sex,sbir) values(? ,?, ?, ?,? )";
        int batchSize = 10000 * 30;
        String clno, sno, sname, sbir;
        int successfulCount = 0;

        //初始化数据库连接对象
        Connection connection = getConnection();
        //创建预编译的SQL语句的对象,即SQL命令对象
        PreparedStatement ps = null;
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始插入数据....");
            //初始化命令对象
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < batchSize; i++) {
                clno = "clno" + i;
                sno = "sno" + i;
                sname = "sname" + i;
                sbir = "20191027";
                // 填充参数
                ps.setObject(1, clno);
                ps.setObject(2, sno);
                ps.setObject(3, sname);
                ps.setObject(4, 1);
                ps.setObject(5, sbir);
                //执行命令,返回结果
                int nResult = ps.executeUpdate();
                if (nResult > 0) {
                    successfulCount++;
                }
            }
            System.out.println("成功插入数据量:" + successfulCount);
            System.out.println("插入数据所耗费的时间：" + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (ps != null) {
                //关闭命令对象
                ps.close();
            }
            if (connection != null) {
                //关闭数据库连接
                connection.close();
            }
        }

    }


    /**
     * 插入数据
     */
    public static void testInsert() throws Exception {
        String sql = "insert into user(name,account,password) values( ?, ?,? )";
        int nResult = executeUpdate(sql, new Object[]{"charlie", "charlie", "charlie"});
        if (nResult > 0) {
            System.out.println("插入数据成功");
        } else {
            System.out.println("插入数据失败");
        }
    }


    /**
     * 测试获取第一行第一列
     *
     * @throws Exception
     */
    public static void testGetScalar() throws Exception {
        String sql = "select count(*) from student";
        long startTime = System.currentTimeMillis();
        long count = (long) getScalar(sql, new Object[]{});
        System.out.println("count=" + count);
        System.out.println("执行SQL所耗费的时间：" + (System.currentTimeMillis() - startTime) + " ms");
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
    private static final String connectionString = "jdbc:mysql://localhost:3306/test_jdbc_performance";

    /**
     * 数据库登录账号
     */
    private static final String USER = "root";

    /**
     * 数据库登录密码
     */
    private static final String PASSWORD = "root";

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

    /**
     * 提供往数据库添加、删改和修改的功能
     *
     * @param sql    一条有效的SQL语句
     * @param params 以数组形式提供SQL语句中用到的参数列表
     * @return 受影响行数
     * @throws Exception
     */
    public static int executeUpdate(String sql, Object[] params) throws Exception {
        //初始化数据库连接对象
        Connection connection = getConnection();
        //创建预编译的SQL语句的对象,即SQL命令对象
        PreparedStatement ps = null;
        //方法返回值
        int value = -1;
        try {
            //初始化命令对象
            ps = connection.prepareStatement(sql);
            if (params != null) {
                //绑定参数到命令对象
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            //执行命令,返回结果
            value = ps.executeUpdate();
            //关闭命令对象
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (ps != null) {
                //关闭命令对象
                ps.close();
            }
            if (connection != null) {
                //关闭数据库连接
                connection.close();
            }
        }
        return value;
    }


    /**
     * 获取结果集表,即将结果集的数据存放List对象中
     *
     * @param sql    一条有效的SQL语句
     * @param params 以数组形式提供SQL语句中用到的参数列表
     * @return List对象
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List getList(String sql, Object[] params) throws Exception {
        //初始化数据库连接对象
        Connection connection = getConnection();
        //创建命令对象
        PreparedStatement ps = null;
        //创建结果集对象
        ResultSet rs = null;
        //创建List对象
        List list = new ArrayList();
        try {
            //初始化命令对象
            ps = connection.prepareStatement(sql);
            if (params != null) {
                //绑定参数到命令对象
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            /*
             * 注意:获取结果集之时,不能关闭命令对象和数据库连接,否则结果集就读取不到数据
             * 在使用结果集完毕后再关闭数据库连接即可
             */
            //获取结果集
            rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            while (rs.next()) {
                Map m = new HashMap();
                for (int i = 0; i < columns; i++) {
                    Object obj = null;
                    int type = resultSetMetaData.getColumnType(i + 1);
                    int scale = resultSetMetaData.getScale(i + 1);
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    switch (type) {
                        case -1:
                            obj = rs.getObject(columnName);
                            break;
                        case 1:
                            obj = rs.getCharacterStream(columnName);
                            break;
                        case -5:
                            obj = Long.valueOf(rs.getLong(columnName));
                            break;
                        case 2:
                            switch (scale) {
                                case 0:
                                    obj = Integer.valueOf(rs.getInt(columnName));
                                    break;
                                case -127:
                                    obj = Float.valueOf(rs.getFloat(columnName));
                                    break;
                                default:
                                    obj = Integer.valueOf(rs.getInt(columnName));
                            }
                            break;
                        case 12:
                            obj = rs.getString(columnName);
                            break;
                        case 91:
                            obj = rs.getDate(columnName);
                            break;
                        case 93:
                            obj = rs.getTimestamp(columnName);
                            break;
                        case 2004:
                            obj = rs.getBlob(columnName);
                            break;
                        default:
                            obj = rs.getString(columnName);
                    }
                    m.put(columnName.toUpperCase(), obj);
                }
                list.add(m);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                //关闭结果集
                rs.close();
            }
            if (ps != null) {
                //关闭命令对象
                ps.close();
            }
            if (connection != null) {
                //关闭数据库连接
                connection.close();
            }
        }
        return list;
    }


    /**
     * 获取第一行第一列
     *
     * @param sql    一条有效的SQL语句
     * @param params 以数组形式提供SQL语句中用到的参数列表
     * @return Object对象
     * @throws Exception
     */
    public static Object getScalar(String sql, Object[] params) throws Exception {
        //初始化数据库连接对象
        Connection connection = getConnection();
        //创建预编译的SQL语句的对象,即SQL命令对象
        PreparedStatement ps = null;
        //创建结果集对象
        ResultSet rs = null;
        //创建Object对象
        Object obj = null;
        try {
            //初始化命令对象
            ps = connection.prepareStatement(sql);
            if (params != null) {
                //绑定参数到命令对象
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            //执行命令
            rs = ps.executeQuery();
            while (rs.next()) {
                obj = rs.getObject(1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (rs != null) {
                //关闭结果集
                rs.close();
            }
            if (ps != null) {
                //关闭命令对象
                ps.close();
            }
            if (connection != null) {
                //关闭数据库连接
                connection.close();
            }
        }
        return obj;
    }

    /**
     * 获取数据表的某一行数据
     *
     * @param sql    一条有效的SQL语句
     * @param params 以数组形式提供SQL语句中用到的参数列表
     * @return map键值对对象
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static Map uniqueQuery(String sql, Object[] params) throws Exception {
        Map m = null;
        List list = getList(sql, params);
        if ((list != null) && (list.size() > 0)) {
            m = (Map) list.get(0);
        }
        return m;
    }

}