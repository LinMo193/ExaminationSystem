package com.join.dao.examPage.Impl;

import com.join.dao.examPage.ExamPageCompletionDao;
import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageShort;
import com.join.entity.ExamPageSingle;
import com.join.entity.Teacher;
import com.join.sql.examPage.examPageCompletion.ExamPageCompletionSql;
import com.join.sql.examPage.examPageSingle.ExamPageSingleSql;
import com.join.utils.c3p0Util.C3P0Utils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamPageCompletionDaoImpl implements ExamPageCompletionDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    ExamPageCompletion examPageCompletion=null;


    @Override
    public boolean addCompletionByInfo(ExamPageCompletion examPageCompletion,String teacherName) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageCompletionSql.addCompletionByInfo);
            ps.setString(1,examPageCompletion.getCourseName());
            ps.setString(2,examPageCompletion.getQuestionContent());
            ps.setString(3,examPageCompletion.getAnswerRight());
            ps.setFloat(4,examPageCompletion.getDegree());
            ps.setString(5, teacherName);
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
    public boolean deleteCompletionInfo(String courseName, String questionContent) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageCompletionSql.deleteCompletionByInfo);
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
    public ExamPageCompletion queryCompletionByTwoInfo(String courseName, String questionContent) {
        ExamPageCompletion examPageCompletion=null;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageCompletionSql.queryCompletionByTwoInfo);
            ps.setString(1,courseName);
            ps.setString(2,questionContent);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageCompletion=new ExamPageCompletion();
                examPageCompletion.setCourseName(rs.getString("courseName"));
                examPageCompletion.setQuestionContent(rs.getString("questionContent"));
                examPageCompletion.setAnswerRight(rs.getString("answerRight"));
                examPageCompletion.setDegree(rs.getInt("degree"));
                examPageCompletion.setTeacherName(rs.getString("teacherName"));
            }
        } catch (Exception e) {
            System.out.println("查询试题内容时出现了问题");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return examPageCompletion;
    }

    @Override
    public List<ExamPageCompletion> listAllExamPageCompletion() {
        ExamPageCompletion examPageCompletion=null;
        List<ExamPageCompletion> list=new ArrayList<>();
        conn=null;
        PreparedStatement ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageCompletionSql.queryCompletionAllByInfo);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageCompletion=new ExamPageCompletion();
                examPageCompletion.setId(rs.getInt("id"));
                examPageCompletion.setCourseName(rs.getString("courseName"));
                examPageCompletion.setQuestionContent(rs.getString("questionContent"));
                examPageCompletion.setAnswerRight(rs.getString("answerRight"));
                examPageCompletion.setDegree(rs.getInt("degree"));
                examPageCompletion.setTeacherName(rs.getString("teacherName"));
                list.add(examPageCompletion);
            }
        } catch (Exception e) {
            System.out.println("未能成功获取全部填空题信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<ExamPageCompletion> queryExamPageCompletionByPage(int currentPage) {
        List<ExamPageCompletion> list = new ArrayList<>();
        ExamPageCompletion examPageCompletion=null;
        conn = null;
        ps = null;
        rs = null;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageCompletionSql.queryExamPageCompletionByPage);
            int pageSize = 5;
            int current = (currentPage - 1) * pageSize;
            ps.setInt(1, current);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                examPageCompletion=new ExamPageCompletion();
                examPageCompletion.setId(rs.getInt("id"));
                examPageCompletion.setCourseName(rs.getString("courseName"));
                examPageCompletion.setQuestionContent(rs.getString("questionContent"));
                examPageCompletion.setAnswerRight(rs.getString("answerRight"));
                examPageCompletion.setDegree(rs.getInt("degree"));
                examPageCompletion.setTeacherName(rs.getString("teacherName"));
                list.add(examPageCompletion);
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
    public int getExamPageCompletionTotalCount() {
        conn = null;
        ps = null;
        rs = null;
        int count = -1;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageCompletionSql.examPageCompletionTotalCount);
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
