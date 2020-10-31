package com.join.dao.examPage.Impl;

import com.join.dao.examPage.ExamPageShortDao;
import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageShort;
import com.join.entity.ExamPageSingle;
import com.join.entity.Teacher;
import com.join.sql.examPage.examPageCompletion.ExamPageCompletionSql;
import com.join.sql.examPage.examPageShort.ExamPageShortSql;
import com.join.sql.examPage.examPageSingle.ExamPageSingleSql;
import com.join.utils.c3p0Util.C3P0Utils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamPageShortDaoImpl implements ExamPageShortDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    ExamPageCompletion examPageCompletion=null;

    @Override
    public boolean addShortByInfo(ExamPageShort examPageShort,String teacherName) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageShortSql.addShortByInfo);
            ps.setString(1,examPageShort.getCourseName());
            ps.setString(2,examPageShort.getQuestionContent());
            ps.setFloat(3, examPageShort.getDegree());
            ps.setString(4, teacherName);
//            ps.setString(4,"join");
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
    public boolean deleteShortInfo(String courseName, String questionContent) {
        boolean flag = false;
        HttpSession session = null;
        conn = null;
        ps = null;
        try {
            conn=C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageShortSql.deleteShortByInfo);
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
    public ExamPageShort queryShortByTwoInfo(String courseName, String questionContent) {
        ExamPageShort examPageShort=null;
        conn=null;
        ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageShortSql.queryShortByTwoInfo);
            ps.setString(1,courseName);
            ps.setString(2,questionContent);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageShort=new ExamPageShort();
                examPageShort.setCourseName(rs.getString("courseName"));
                examPageShort.setQuestionContent(rs.getString("questionContent"));
                examPageShort.setDegree(rs.getFloat("degree"));
                examPageShort.setTeacherName(rs.getString("teacherName"));
            }
        } catch (Exception e) {
            System.out.println("查询试题内容时出现了问题");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return examPageShort;
    }

    @Override
    public List<ExamPageShort> listAllExamPageShort() {
        ExamPageShort examPageShort=null;
        List<ExamPageShort> list=new ArrayList<>();
        conn=null;
        PreparedStatement ps=null;
        try {
            conn= C3P0Utils.getConnection();
            ps=conn.prepareStatement(ExamPageShortSql.queryShortAllByInfo);
            rs=ps.executeQuery();
            while (rs.next()) {
                examPageShort=new ExamPageShort();
                examPageShort.setId(rs.getInt("id"));
                examPageShort.setCourseName(rs.getString("courseName"));
                examPageShort.setQuestionContent(rs.getString("questionContent"));
                examPageShort.setDegree(rs.getInt("degree"));
                examPageShort.setTeacherName(rs.getString("teacherName"));
                list.add(examPageShort);
            }
        } catch (Exception e) {
            System.out.println("未能成功获取全部简答题信息");
        } finally {
            C3P0Utils.closeResultSet(rs);
            C3P0Utils.closePreparedStatement(ps);
            C3P0Utils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<ExamPageShort> queryExamPageShortByPage(int currentPage) {
        List<ExamPageShort> list = new ArrayList<>();
        ExamPageShort examPageShort=null;
        conn = null;
        ps = null;
        rs = null;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageShortSql.queryExamPageShortByPage);
            int pageSize = 5;
            int current = (currentPage - 1) * pageSize;
            ps.setInt(1, current);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                examPageShort=new ExamPageShort();
                examPageShort.setId(rs.getInt("id"));
                examPageShort.setCourseName(rs.getString("courseName"));
                examPageShort.setQuestionContent(rs.getString("questionContent"));
                examPageShort.setDegree(rs.getInt("degree"));
                examPageShort.setTeacherName(rs.getString("teacherName"));
                list.add(examPageShort);
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
    public int getExamPageShortTotalCount() {
        conn = null;
        ps = null;
        rs = null;
        int count = -1;
        try {
            conn= C3P0Utils.getConnection();
            ps = conn.prepareStatement(ExamPageShortSql.examPageShortTotalCount);
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
