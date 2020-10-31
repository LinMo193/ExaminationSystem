package com.join.sql.student;

public class StudentSql {
    // 增
    public static String addStudentByAllInfo="insert into studentinfo (sno,username,password,telenumber,email,headpath) value (?,?,?,?,?,?)";
    // 查student全部信息
    public static String queryStudentByInfo="select * from studentinfo where username=? and password=?";
    // 查student的sno是否唯一
    public static String queryStudentBySno="select * from studentinfo where sno=?";
}
