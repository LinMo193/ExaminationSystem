package com.join.dao.examPage.Impl;

import com.google.gson.internal.$Gson$Preconditions;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.entity.ExamPageSingle;
import com.join.entity.Teacher;
import com.join.service.examPage.ExamPageSingleService;
import com.join.sql.examPage.examPageSingle.ExamPageSingleSql;
import com.join.sql.student.StudentSql;
import com.join.utils.c3p0Util.C3P0Utils;
import javafx.animation.ScaleTransition;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamPageSingleDaoImpl implements ExamPageSingleDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    ExamPageSingle examPageSingle=null;

    @Override
    public boolean addSingleByInfo(ExamPageSingle examPageSingle,String teacherName) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageSingleSql.addSingleByInfo);
            ps.setString(1,examPageSingle.getCourseName());
            ps.setString(2,examPageSingle.getQuestionContent());
            ps.setString(3,examPageSingle.getAnswerA());
            ps.setString(4,examPageSingle.getAnswerB());
            ps.setString(5,examPageSingle.getAnswerC());
            ps.setString(6,examPageSingle.getAnswerD());
            ps.setString(7,examPageSingle.getAnswerRight());
            ps.setFloat(8, examPageSingle.getDegree());
            ps.setString(9, teacherName);
//            ps.setString(9,"join");
            if(ps.executeUpdate()>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public boolean deleteSingleInfo(String courseName, String questionContent) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageSingleSql.deleteSingleByInfo);
            ps.setString(1,courseName);
            ps.setString(2,questionContent);

//            Teacher teacher= (Teacher) session.getAttribute("teacher");
//            ps.setString(9, teacher.getUsername());
//            ps.setString(3,"join");
            if(ps.executeUpdate()>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public ExamPageSingle querySingleByTwoInfo(String courseName, String questionContent) {
        ExamPageSingle examPageSingle=null;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageSingleSql.querySingleByTwoInfo);
            ps.setString(1,courseName);
            ps.setString(2,questionContent);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageSingle=new ExamPageSingle();
                examPageSingle.setCourseName(rs.getString("courseName"));
                examPageSingle.setQuestionContent(rs.getString("questionContent"));
                examPageSingle.setAnswerA(rs.getString("answerA"));
                examPageSingle.setAnswerB(rs.getString("answerB"));
                examPageSingle.setAnswerC(rs.getString("answerC"));
                examPageSingle.setAnswerD(rs.getString("answerD"));
                examPageSingle.setAnswerRight(rs.getString("answerRight"));
                examPageSingle.setDegree(rs.getFloat("degree"));
                examPageSingle.setTeacherName(rs.getString("teacherName"));
            }
        } catch (Exception e) {
            System.out.println("查询试题内容时出现了问题");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return examPageSingle;
    }

    @Override
    public List<ExamPageSingle> listAllExamPageSingle() {
        ExamPageSingle examPageSingle=null;
        List<ExamPageSingle> list=new ArrayList<>();
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageSingleSql.querySingleAllByInfo);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageSingle=new ExamPageSingle();
                examPageSingle.setId(rs.getInt("id"));
                examPageSingle.setCourseName(rs.getString("courseName"));
                examPageSingle.setQuestionContent(rs.getString("questionContent"));
                examPageSingle.setAnswerA(rs.getString("answerA"));
                examPageSingle.setAnswerB(rs.getString("answerB"));
                examPageSingle.setAnswerC(rs.getString("answerC"));
                examPageSingle.setAnswerD(rs.getString("answerD"));
                examPageSingle.setAnswerRight(rs.getString("answerRight"));
                examPageSingle.setDegree(rs.getFloat("degree"));
                examPageSingle.setTeacherName(rs.getString("teacherName"));
                list.add(examPageSingle);
            }
        } catch (Exception e) {
            System.out.println("未能成功获取全部单选题信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<ExamPageSingle> queryExamPageSingleByPage ( int currentPage){
        List<ExamPageSingle> list = new ArrayList<>();
        ExamPageSingle examPageSingle=null;
        conn = null;
        ps = null;
        rs = null;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageSingleSql.queryExamPageSingleByPage);
            int pageSize = 5;
            int current = (currentPage - 1) * pageSize;
            ps.setInt(1, current);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                examPageSingle = new ExamPageSingle();
                examPageSingle.setId(rs.getInt("id"));
                examPageSingle.setCourseName(rs.getString("courseName"));
                examPageSingle.setQuestionContent(rs.getString("questionContent"));
                examPageSingle.setAnswerA(rs.getString("answerA"));
                examPageSingle.setAnswerB(rs.getString("answerB"));
                examPageSingle.setAnswerC(rs.getString("answerC"));
                examPageSingle.setAnswerD(rs.getString("answerD"));
                examPageSingle.setAnswerRight(rs.getString("answerRight"));
                examPageSingle.setDegree(rs.getFloat("degree"));
                examPageSingle.setTeacherName(rs.getString("teacherName"));
                list.add(examPageSingle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public int getExamPageSingleTotalCount () {
        conn = null;
        ps = null;
        rs = null;
        int count = -1;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageSingleSql.examPageSingleTotalCount);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return count;
    }
}
