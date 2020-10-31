package com.join.dao.examPage.Impl;

import com.join.dao.examPage.ExamBasicInfoDao;
import com.join.entity.ExamAboutQuestion;
import com.join.sql.examPage.examPageAll.ExamBasicInfoSql;
import com.join.utils.c3p0Util.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExamBasicInfoDaoImpl implements ExamBasicInfoDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    @Override
    public boolean addExamPageAllByBasicInfo(ExamAboutQuestion examAboutQuestion) {
        boolean flag = false;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.addExamPageAllByBasicInfo);
            ps.setString(1, examAboutQuestion.getAcademy());
            ps.setString(2, examAboutQuestion.getMajor());
            ps.setInt(3, examAboutQuestion.getGrade());
            ps.setString(4, examAboutQuestion.getExamName());
            ps.setInt(5, examAboutQuestion.getExamTime());
            ps.setString(6, examAboutQuestion.getTeacherName());
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
    public boolean queryExamPageAllExit(ExamAboutQuestion examAboutQuestion) {
        Boolean flag=false;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.queryExamPageExit);
            ps.setString(1, examAboutQuestion.getAcademy());
            ps.setString(2, examAboutQuestion.getMajor());
            ps.setInt(3, examAboutQuestion.getGrade());
            ps.setString(4, examAboutQuestion.getExamName());
            rs=ps.executeQuery();
            while (rs.next()) {
                flag=true;
                break;
            }
        } catch (Exception e) {
            System.out.println("查询试卷时出现了问题");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public int addQuestion(ExamAboutQuestion examAboutQuestion, int count) {
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.addQuestion);
            ps.setString(1, examAboutQuestion.getAcademy());
            ps.setString(2, examAboutQuestion.getMajor());
            ps.setInt(3, examAboutQuestion.getGrade());
            ps.setString(4, examAboutQuestion.getExamName());
            ps.setString(5, examAboutQuestion.getQuestionType());
            ps.setString(6, examAboutQuestion.getQuestionContent());
            ps.setInt(7, examAboutQuestion.getSingleScore());
            System.out.println(ps);
            if(ps.executeUpdate()>0){
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return count;
    }

    @Override
    public int deleteQuestion(ExamAboutQuestion examAboutQuestion, int count) {
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.deleteQuestion);
            ps.setString(1, examAboutQuestion.getQuestionContent());
            System.out.println(ps);
            if(ps.executeUpdate()>0){
                count--;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return count;
    }

    @Override
    public boolean deleteExamPage(ExamAboutQuestion examAboutQuestion) {
        boolean flag=false;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.deleteExamPage);
            ps.setString(1, examAboutQuestion.getExamName());
//            ps.setString(2,examBasicInfo.getTeacherName());
            System.out.println(ps);
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
    public int sumTotal(ExamAboutQuestion examAboutQuestion) {
        conn = null;
        ps = null;
        rs=null;
        int sum=0;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.sumTotal);
            ps.setString(1, examAboutQuestion.getExamName());
            rs=ps.executeQuery();
            while (rs.next()) {
                sum=rs.getInt("sum(SingleScore)");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return sum;
    }

    @Override
    public boolean storeExamPage(ExamAboutQuestion examAboutQuestion, int sum) {
        boolean flag=false;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamBasicInfoSql.storeExamPage);
            ps.setInt(1,sum);
            ps.setString(2, examAboutQuestion.getExamName());
            System.out.println(ps);
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

}
