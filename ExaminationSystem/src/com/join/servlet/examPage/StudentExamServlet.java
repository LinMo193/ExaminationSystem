package com.join.servlet.examPage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.join.dao.examPage.StudentExamDao;
import com.join.entity.*;
import com.join.service.examPage.ExamPageSingleService;
import com.join.service.examPage.Impl.ExamPageSingleServiceImpl;
import com.join.service.examPage.Impl.StudentExamServiceImpl;
import com.join.service.examPage.StudentExamService;
import com.join.utils.jsonUtil.JSONUtils;

import javax.security.sasl.SaslServer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="StudentExamServlet",urlPatterns = "/studentExamServlet",asyncSupported = true)
public class StudentExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
//        String state=req.getRequestURI();
        String state=req.getParameter("state");
        switch (state) {
            case "getExamInfo":
                getExamInfo(req,resp);
                break;
            case "studentExamInfo":
                studentExamInfo(req,resp);
                break;
            case "checkStudentExamInfo":
                checkStudentExamInfo(req,resp);
                break;
        }
    }

    private void getExamInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamBasicInfo examBasicInfo=gson.fromJson(jsonString,ExamBasicInfo.class);
        HttpSession session=req.getSession();
        session.setAttribute("courseName",examBasicInfo.getExamName());

        StudentExamService studentExamService=new StudentExamServiceImpl();
        examBasicInfo=studentExamService.getExamInfoService(examBasicInfo);

        // 获取该试卷所有信息后，再将试题存储在examAboutQuestion中并一起返回前端
        List<ExamAboutQuestion> list=new ArrayList<>();
        list=studentExamService.getQuestionInfoService(examBasicInfo);

        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","该试卷中的试题信息未成功查询");
        } else {
            Map data1=new HashMap();
            Map data2=new HashMap();
            data1.put("examBasicInfo",examBasicInfo);
            data2.put("list",list);
            map.put("code",1);
            map.put("data1",data1);
            map.put("data2",data2);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void studentExamInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        List<ExamAboutQuestion> list= gson.fromJson(jsonString, new TypeToken<List<ExamAboutQuestion>>() {}.getType());

        HttpSession session=req.getSession();
        Student student= (Student) session.getAttribute("student");
        System.out.println(student);
        String courseName= (String) session.getAttribute("courseName");

        StudentExamService studentExamService=new StudentExamServiceImpl();
        // 学生提交试卷后将答题信息存储到数据库中
        boolean flag=studentExamService.completedExamService(list,student,courseName);
        boolean flag1=false;
        int score=0;
        Map map=new HashMap();
        // 学生已提交试卷
        if (flag) {
            // 计算学生该试卷得分并存储在数据库中
            flag1=studentExamService.submitExamService(list,courseName,student);
            if (flag1) {
                map.put("code",1);
            map.put("message","学生提交试卷成功^2");
            } else {
                map.put("code",0);
                map.put("message","学生提交试卷失败^2");
            }
        } else {
            map.put("code",0);
            map.put("message","学生提交试卷失败");
        }

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void checkStudentExamInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        StudentExamInfo studentExamInfo=gson.fromJson(jsonString,StudentExamInfo.class);
        HttpSession session=req.getSession();
        Student student= (Student) session.getAttribute("student");
        List<CheckStudentExamInfo> list=new ArrayList<>();
        StudentExamService studentExamService=new StudentExamServiceImpl();
        list=studentExamService.checkStudentExamInfoService(student,studentExamInfo);
        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","查询试卷时出错");
        } else {
            Map data=new HashMap();
            data.put("list",list);
            map.put("code",1);
            map.put("data",data);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}
