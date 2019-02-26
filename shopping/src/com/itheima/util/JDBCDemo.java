package com.itheima.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.HashAttributeSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCDemo {

        static {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        private String username = "c##scott";
        private String password = "tiger";
        private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

        // localhost = 127.0.0.1 代表本机ip地址
        /*
         * 1.java链接数据库 前期准备,用户名,密码,数据库地址 2.导入数据库驱动jar包 3.加载数据库驱动jar包
         * 4.创建与数据库链接对象connection 5.写增删改的方法
         *
         */
        public Connection getConn() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 通过drivermanager驱动管理器, 数据路径,用户名,密码
            // 得到与数据库的链接
            return conn;
        }

        public int update(String sql) {
            Connection conn = getConn();
            // 获取与数据库的链接
            PreparedStatement ps = null;
            int a=0;
            try {
                ps = conn.prepareStatement(sql);
                // 预编译sql语句
                a=ps.executeUpdate();
                // 执行sql语句

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return a;
        }
        public boolean delete(String sql){
            boolean flag=false;
            Connection conn = getConn();
            // 获取与数据库的链接
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(sql);
                // 预编译sql语句
                ps.executeUpdate();
                // 执行sql语句
                flag=true;
                return flag;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return flag;

        }

        public List<Map<String, Object>> query(String sql) {
            List<Map<String, Object>> list = new ArrayList();
            Connection conn = getConn();
            PreparedStatement ps = null;
            ResultSet rs = null;// 存储数据的仓库
            ResultSetMetaData rsmd = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();// 列的总个数
                Map map = null;
                while (rs.next()) {
                    map = new HashMap();
                    for (int i = 1; i <= count; i++) {
                        String cName = rsmd.getColumnLabel(i);
                        String cValue = rs.getString(cName);
                        map.put(cName, cValue);
                    }
                    list.add(map);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }


        public boolean login(String username,String password){//登录账号密码验证, t_user表
            boolean flag=false;
            String sql="select * from t_user where username='"+username+"' and password='"+password+"' ";
            List list=query(sql);
            if(list.size()>0){
                flag=true;
            }
            return flag;
        }
        public int count(String sql){
            List<Map<String, Object>> list = new ArrayList();
            Connection conn = getConn();
            PreparedStatement ps=null;
            ResultSet rs=null;
            ResultSetMetaData rsmd=null;
            try {
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                rsmd=rs.getMetaData();
                int count=rsmd.getColumnCount();
                Map map=null;
                while(rs.next()){
                    map=new HashMap();
                    for (int i = 1; i < count; i++) {
                        String cName=rsmd.getColumnLabel(i);
                        String cValue=rs.getString(cName);
                        map.put(cName, cValue);
                    }
                    list.add(map);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int count=list.size();

            return count;

        }
        public boolean zhuce(String username,String password){
            boolean flag=false;
            String sql1="select * from t_user ";
            int a=count(sql1);
            String sql="insert into t_user values('"+username+"' ,'"+password+"') ";
            update(sql);
            String sql2="select * from t_user ";
            int b=count(sql2);
            System.out.println(a);System.out.println(b);
            if (b>a) {
                flag=true;
            }
            return flag;

        }
        public int reg(String username,String password){
            String sql="insert into t_user values('"+username+"' ,'"+password+"') ";
            int a=update(sql);
            return a;

        }
        public int chaming(String username){
            String sql="select * from t_user where username='"+username+"'";
            List list=query(sql);
            int a=list.size();
            return a;
        }
}
