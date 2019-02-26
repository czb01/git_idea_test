package com.itheima.dao;

import com.itheima.util.JDBCDemo;

import java.util.List;
import java.util.Map;


public class Dao {


    public List getAllStudent(){
        JDBCDemo jdbcDemo=new JDBCDemo();
        String sql="select * from student";
        List<Map<String, Object>> students = jdbcDemo.query(sql);
        System.out.println(students);
        return students;

    }

//    public static void main(String[] args) {
//        JDBCDemo jdbcDemo=new JDBCDemo();
//        List<Map<String, Object>> list = jdbcDemo.query("select * from student");
//        System.out.println(list);
//    }
public static void main(String[] args) {
    String a="a"+"b";
    a=a+"aaa";
    a+=a;
    System.out.println(a);
    StringBuffer b=new StringBuffer();
    b.append(a);
    b.append(a);

    System.out.println(b);
}
}
