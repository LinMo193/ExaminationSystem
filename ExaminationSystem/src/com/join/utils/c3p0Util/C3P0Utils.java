package com.join.utils.c3p0Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class C3P0Utils {
    private static ComboPooledDataSource ds=new ComboPooledDataSource();
    public static void configDataSource() {
        try {
            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/join_exam");
            ds.setUser("root");
            ds.setPassword("2019012660Moon");
            ds.setInitialPoolSize(3);
            ds.setMaxPoolSize(10);
            ds.setMinPoolSize(3);
            ds.setAcquireIncrement(3);
        } catch (PropertyVetoException e) {
            System.out.println("连接数据库失败");
        }
    }

    /**
     * 从连接池获取连接
     * @return
     */
    public static Connection getConnection() {
        configDataSource();
        Connection conn=null;
        try {
            conn=ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭资源
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("未成功关闭Connection");
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps!=null) {
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("未成功关闭PreparedStatement");
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("未成功关闭ResultSet");
        }
    }

}
