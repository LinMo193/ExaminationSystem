package com.join.servlet.examPage;

import com.google.gson.Gson;
import com.join.entity.ExamAboutQuestion;
import com.join.entity.Teacher;
import com.join.service.examPage.ExamBasicInfoService;
import com.join.service.examPage.Impl.ExamBasicInfoServiceImpl;
import com.join.utils.jsonUtil.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name ="ExamBasicInfoServlet",urlPatterns = "/examBasicInfoServlet",asyncSupported = true)
public class ExamBasicInfoServlet extends HttpServlet {
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
            case "createExamPage":
                createExamPage(req,resp);
                break;
            case "addExamPage":
                addExamPage(req,resp);
                break;
            case "deleteQuestion":
                deleteQuestion(req,resp);
                break;
            case "deleteExamPage":
                deleteExamPage(req,resp);
                break;
            case "storeExamPage":
                storeExamPage(req,resp);
                break;
            case "studentExam":
                studentExam(req,resp);
                break;
        }
    }

    private void createExamPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamAboutQuestion examAboutQuestion =gson.fromJson(jsonString, ExamAboutQuestion.class);
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();

        HttpSession session=req.getSession();
        System.out.println("开始进行session传值");
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        System.out.println(teacher.getUsername());
        examAboutQuestion.setTeacherName(teacher.getUsername());

        boolean flag=examBasicInfoService.queryExamPageAllExitService(examAboutQuestion);
        if (flag) {
            map.put("code",0);
            map.put("message","该试卷已存在，请直接添加试题");
        } else {
            boolean flag1=examBasicInfoService.addExamPageAllByBasicInfoService(examAboutQuestion);
            if (flag1) {
                map.put("code",1);
                map.put("message","添加试卷成功");
            } else {
                map.put("code",0);
                map.put("message","添加试卷失败");
            }
        }
        // 将试卷信息存储到session中
        session.setAttribute("examBasicInfo", examAboutQuestion);
        // 将试卷中的试题数存储到session中
        int count=0;
        session.setAttribute("count",count);

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void addExamPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamAboutQuestion examAboutQuestion =gson.fromJson(jsonString, ExamAboutQuestion.class);
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();

        HttpSession session=req.getSession();
        ExamAboutQuestion examAboutQuestion1 = (ExamAboutQuestion) session.getAttribute("examBasicInfo");
        examAboutQuestion.setAcademy(examAboutQuestion1.getAcademy());
        examAboutQuestion.setMajor(examAboutQuestion1.getMajor());
        examAboutQuestion.setGrade(examAboutQuestion1.getGrade());
        examAboutQuestion.setExamName(examAboutQuestion1.getExamName());

        int count= (int) session.getAttribute("count");
        int count1=count;

        if (count>=6) {
            map.put("code",1);
            map.put("message","添加试题失败，该试卷已饱和");
        } else {
            count1=examBasicInfoService.addQuestionService(examAboutQuestion,count);
            System.out.println("添加试题后，由"+count+"->"+count1);
            if (count1!=count) {
                map.put("code",1);
                map.put("message","添加试题成功");
            } else {
                map.put("code",1);
                map.put("message","添加试题失败");
            }
        }
        session.setAttribute("count",count1);

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void deleteQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamAboutQuestion examAboutQuestion =gson.fromJson(jsonString, ExamAboutQuestion.class);
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();

        HttpSession session=req.getSession();
        int count= (int) session.getAttribute("count");
        int count1=count;

        count1=examBasicInfoService.deleteQuestionService(examAboutQuestion,count);
        if (count1!=count) {
            map.put("code",1);
            map.put("message","删除试题成功");
        } else {
            map.put("code",1);
            map.put("message","删除试题失败");
        }
        session.setAttribute("count",count1);

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void deleteExamPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();

        HttpSession session=req.getSession();
        ExamAboutQuestion examAboutQuestion = (ExamAboutQuestion) session.getAttribute("examBasicInfo");
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();

        boolean flag=examBasicInfoService.deleteExamPageService(examAboutQuestion);
        if (flag) {
            map.put("code",1);
            map.put("message","删除试卷成功");
        } else {
            map.put("code",1);
            map.put("message","删除试卷失败");
        }

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void storeExamPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();

        HttpSession session=req.getSession();
        ExamAboutQuestion examAboutQuestion = (ExamAboutQuestion) session.getAttribute("examBasicInfo");
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();
        // 获取试卷总分
        int sum=examBasicInfoService.sumTotalService(examAboutQuestion);
        // 将总分存储进试卷即添加试卷彻底成功
        boolean flag=examBasicInfoService.storeExamPageService(examAboutQuestion,sum);
        if (flag) {
            map.put("code",1);
            map.put("message","添加试卷+总分成功");
        } else {
            map.put("code",1);
            map.put("message","添加试卷+总分失败");
        }

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void studentExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamAboutQuestion examAboutQuestion =gson.fromJson(jsonString, ExamAboutQuestion.class);
        ExamBasicInfoService examBasicInfoService=new ExamBasicInfoServiceImpl();

        boolean flag=examBasicInfoService.queryExamPageAllExitService(examAboutQuestion);
        if (flag) {
            map.put("code",0);
            map.put("message","该试卷已存在，请直接添加试题");
        } else {
            boolean flag1=examBasicInfoService.addExamPageAllByBasicInfoService(examAboutQuestion);
            if (flag1) {
                map.put("code",1);
                map.put("message","添加试卷成功");
            } else {
                map.put("code",0);
                map.put("message","添加试卷失败");
            }
        }

        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}
