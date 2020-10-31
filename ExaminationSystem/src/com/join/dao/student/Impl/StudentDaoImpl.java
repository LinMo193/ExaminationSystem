package com.join.dao.student.Impl;

import com.join.dao.student.StudentDao;
import com.join.entity.Student;
import com.join.sql.student.StudentSql;
import com.join.utils.c3p0Util.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDaoImpl implements StudentDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    @Override
    public boolean addStudentByAllInfo(Student student) {
        conn=null;
        ps=null;
        boolean flag=false;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentSql.addStudentByAllInfo);
            ps.setInt(1,student.getSno());
            ps.setString(2,student.getUsername());
            ps.setString(3,student.getPassword());
            ps.setString(4,student.getTelenumber());
            ps.setString(5,student.getEmail());
            ps.setString(6,student.getHeadpath());
            if (ps.executeUpdate()>0) {
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public Student queryStudentByInfo(String username, String password) {
        Student student=null;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentSql.queryStudentByInfo);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()) {
                student=new Student();
                student.setSno(rs.getInt("sno"));
                student.setUsername(rs.getString("username"));
                student.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println("未成功查询到"+username+"的相关信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return student;
    }

    @Override
    public boolean queryStudentBySno(int sno) {
        Boolean flag=false;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentSql.queryStudentBySno);
            ps.setInt(1,sno);
            rs=ps.executeQuery();
            while (rs.next()) {
                flag=true;
                break;
            }
        } catch (Exception e) {
            System.out.println("查询学号时出现了问题");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }
}
