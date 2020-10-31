package com.join.dao.teacher.Impl;

import com.join.dao.teacher.TeacherDao;
import com.join.entity.ExamPageSingle;
import com.join.entity.StudentExamInfo;
import com.join.sql.examPage.examPageSingle.ExamPageSingleSql;
import com.join.sql.teacher.TeacherSql;
import com.join.utils.c3p0Util.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    @Override
    public List<StudentExamInfo> searchStudentExamInfo() {
        StudentExamInfo studentExamInfo=null;
        List<StudentExamInfo> list=new ArrayList<>();
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(TeacherSql.searchStudentExamInfo);
            rs=ps.executeQuery();
            while (rs.next()) {
                studentExamInfo=new StudentExamInfo();
                studentExamInfo.setSno(rs.getInt("sno"));
                studentExamInfo.setUsername(rs.getString("username"));
                studentExamInfo.setExamName(rs.getString("examName"));
                studentExamInfo.setTotalScore(rs.getInt("actualScore"));
                list.add(studentExamInfo);
            }
        } catch (Exception e) {
            System.out.println("未能成功获取全部学生考试信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public StudentExamInfo searchOneStudent(StudentExamInfo studentExamInfo) {
        conn=null;
        ps=null;
        rs=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(TeacherSql.searchOneStudent);
            ps.setString(1,studentExamInfo.getUsername());
            rs=ps.executeQuery();
            while (rs.next()) {
                studentExamInfo.setSno(rs.getInt("sno"));
                studentExamInfo.setExamName(rs.getString("examName"));
                studentExamInfo.setTotalScore(rs.getInt("actualScore"));
            }
        } catch (Exception e) {
            System.out.println("未能成功获取"+studentExamInfo.getUsername()+"的考试信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return studentExamInfo;
    }
}
