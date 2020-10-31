package com.join.dao.examPage.Impl;

import com.join.dao.examPage.StudentExamDao;
import com.join.entity.*;
import com.join.sql.examPage.examPageAll.ExamBasicInfoSql;
import com.join.sql.examPage.examPageAll.StudentExamSql;
import com.join.sql.examPage.examPageSingle.ExamPageSingleSql;
import com.join.utils.c3p0Util.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentExamDaoImpl implements StudentExamDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    @Override
    public ExamBasicInfo getExamInfo(ExamBasicInfo examBasicInfo) {
        conn=null;
        ps=null;
        rs=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentExamSql.getExamInfo);
            ps.setString(1,examBasicInfo.getExamName());
            rs=ps.executeQuery();
            while (rs.next()) {
                examBasicInfo.setAcademy(rs.getString("academy"));
                examBasicInfo.setMajor(rs.getString("major"));
                examBasicInfo.setGrade(rs.getInt("grade"));
                examBasicInfo.setExamName(rs.getString("examName"));
                examBasicInfo.setExamTime(rs.getInt("examTime"));
                examBasicInfo.setTotalScore(rs.getInt("totalScore"));
                examBasicInfo.setTeacherName(rs.getString("teacherName"));
            }
        } catch (Exception e) {
            System.out.println("未能成功获取该试卷信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return examBasicInfo;
    }

    @Override
    public List<ExamAboutQuestion> getQuestionInfo(ExamBasicInfo examBasicInfo) {
        ExamAboutQuestion examAboutQuestion;
        List<ExamAboutQuestion> list=new ArrayList<>();
        conn=null;
        ps=null;
        rs=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentExamSql.getQuestionInfo);
            ps.setString(1,examBasicInfo.getExamName());
            rs=ps.executeQuery();
            while (rs.next()) {
                examAboutQuestion=new ExamAboutQuestion();
                examAboutQuestion.setAcademy(rs.getString("academy"));
                examAboutQuestion.setMajor(rs.getString("major"));
                examAboutQuestion.setGrade(rs.getInt("grade"));
                examAboutQuestion.setExamName(rs.getString("examName"));
                examAboutQuestion.setQuestionType(rs.getString("questionType"));
                examAboutQuestion.setQuestionContent(rs.getString("questionContent"));
                examAboutQuestion.setSingleScore(rs.getInt("singleScore"));
                list.add(examAboutQuestion);
            }
        } catch (Exception e) {
            System.out.println("未能成功获取该试卷中的试题信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean completedExam(List<ExamAboutQuestion> list, Student student,String courseName) {
        boolean flag = false;
        conn = null;
        ps = null;
        try {
            for (ExamAboutQuestion examAboutQuestion:list) {
                conn=C3P0Utils.getConnection();
                ps=conn.prepareStatement(StudentExamSql.completedExam);
                ps.setInt(1, student.getSno());
                ps.setString(2, courseName);
                ps.setString(3, examAboutQuestion.getQuestionContent());
                ps.setString(4, examAboutQuestion.getStudentAnswer());
                ps.setString(5,examAboutQuestion.getQuestionType());
                if(ps.executeUpdate()>0){
                    flag=true;
                } else {
                    return false;
                }
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
    public boolean submitExam(List<ExamAboutQuestion> list,String courseName,Student student) {
        boolean flag=false;
        int score=0;
        conn = null;
        ps = null;
        String studentAnswer=null;
        String questionType=null;
        String answerRight=null;
        int singleScore=0;
        try {
            conn=C3P0Utils.getConnection();
            for (ExamAboutQuestion examAboutQuestion:list) {
                ps=conn.prepareStatement(StudentExamSql.selectThreeInfo);
                ps.setString(1,courseName);
                ps.setString(2,examAboutQuestion.getQuestionContent());
                rs=ps.executeQuery();
                while (rs.next()) {
                    studentAnswer=rs.getString("studentAnswer");
                    questionType=rs.getString("questionType");
                }

                ps=conn.prepareStatement(StudentExamSql.getSingleScore);
                ps.setString(1,examAboutQuestion.getQuestionContent());
                rs=ps.executeQuery();
                while (rs.next()) {
                    singleScore=rs.getInt("singleScore");
                }

                if ("单选题".equals(questionType)) {
                    ps=conn.prepareStatement(StudentExamSql.checkedSingle);
                    ps.setString(1,examAboutQuestion.getQuestionContent());
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        answerRight=rs.getString("answerRight");
                    }
                    if (answerRight.equals(studentAnswer)) {
                        score=score+singleScore;
                    }
                }

                if ("填空题".equals(questionType)) {
                    ps=conn.prepareStatement(StudentExamSql.checkedCompletion);
                    ps.setString(1, examAboutQuestion.getQuestionContent());
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        answerRight=rs.getString("answerRight");
                    }
                    if (answerRight.equals(studentAnswer)) {
                        score=score+singleScore;
                    }
                }
            }

            ps=conn.prepareStatement(StudentExamSql.submitExam);
            ps.setInt(1,student.getSno());
            ps.setString(2,student.getUsername());
            ps.setString(3,courseName);
            ps.setInt(4,score);
            if(ps.executeUpdate()>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public List<CheckStudentExamInfo> checkStudentExamInfo(Student student, StudentExamInfo studentExamInfo) {
        conn = null;
        ps = null;
        CheckStudentExamInfo checkStudentExamInfo=null;
        List<CheckStudentExamInfo> list=new ArrayList<>();
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(StudentExamSql.checkStudentExamInfo);
            ps.setInt(1,student.getSno());
            System.out.println(student.getSno());
            ps.setString(2,studentExamInfo.getExamName());
            rs=ps.executeQuery();
            while (rs.next()) {
                checkStudentExamInfo=new CheckStudentExamInfo();
                checkStudentExamInfo.setQuestionContent(rs.getString("questionContent"));
                checkStudentExamInfo.setStudentAnswer(rs.getString("studentAnswer"));
                checkStudentExamInfo.setQuestionType(rs.getString("questionType"));

                if ("单选题".equals(checkStudentExamInfo.getQuestionType())) {
                    ps=conn.prepareStatement(StudentExamSql.checkedSingle);
                    ps.setString(1,checkStudentExamInfo.getQuestionContent());
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        checkStudentExamInfo.setAnswerRight(rs.getString("answerRight"));
                    }
                }
                if ("填空题".equals(checkStudentExamInfo.getQuestionType())) {
                    ps=conn.prepareStatement(StudentExamSql.checkedCompletion);
                    ps.setString(1, checkStudentExamInfo.getQuestionContent());
                    rs=ps.executeQuery();
                    while (rs.next()) {
                        checkStudentExamInfo.setAnswerRight("answerRight");
                    }
                }
                if ("简答题".equals(checkStudentExamInfo.getQuestionType())) {
                    checkStudentExamInfo.setAnswerRight("待老师批阅");
                }
                list.add(checkStudentExamInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }
}
