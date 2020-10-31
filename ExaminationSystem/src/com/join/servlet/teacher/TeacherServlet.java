package com.join.servlet.teacher;

import com.google.gson.Gson;
import com.join.entity.ExamPageSingle;
import com.join.entity.StudentExamInfo;
import com.join.entity.Teacher;
import com.join.service.examPage.ExamPageSingleService;
import com.join.service.examPage.Impl.ExamPageSingleServiceImpl;
import com.join.service.teacher.Impl.TeacherServiceImpl;
import com.join.service.teacher.TeacherService;
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
import java.util.List;
import java.util.Map;

import static com.join.sql.teacher.TeacherSql.searchOneStudent;

@WebServlet(name ="TeacherServlet",urlPatterns = "/teacherServlet",asyncSupported = true)
public class TeacherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
//        String state=req.getRequestURI();
        String state=req.getParameter("state");
        switch (state) {
            // 教师登陆
            case "teacherLogin":
                login(req,resp);
                break;
            // 教师个人中心
            case "searchStudentExamInfo":
                searchStudentExamInfo(req,resp);
                break;
            case "searchOneStudent":
                searchOneStudent(req,resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString= JSONUtils.getJson(request);
        Gson gson=new Gson();
        Teacher teacher=gson.fromJson(jsonString,Teacher.class);
        HttpSession session=request.getSession();
        Map map=new HashMap();
        if ("join".equals(teacher.getUsername())&&"join666".equals(teacher.getPassword())) {
            // 返回值构建
            Map data=new HashMap();
            data.put("teacher",teacher);

            map.put("code",1);
            map.put("data",data);
            session.setAttribute("teacher",teacher);

            Teacher teacher1= (Teacher) session.getAttribute("teacher");
            System.out.println(teacher1.getUsername());
        } else {
            map.put("code",0);
            map.put("message","用户不存在，登陆失败");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void searchStudentExamInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TeacherService teacherService=new TeacherServiceImpl();
        List<StudentExamInfo> list=teacherService.searchStudentExamInfoService();
        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到所有学生的考试信息");
        } else {
            Map data=new HashMap();
            data.put("list",list);
            map.put("code",1);
            map.put("data",data);
        }
        String jsonString=null;
        Gson gson=new Gson();
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void searchOneStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        StudentExamInfo studentExamInfo=gson.fromJson(jsonString,StudentExamInfo.class);
        TeacherService teacherService=new TeacherServiceImpl();
        StudentExamInfo studentExamInfo1=new StudentExamInfo();
        studentExamInfo1=teacherService.searchOneStudentService(studentExamInfo);
        Map map=new HashMap();
        if (studentExamInfo1==null) {
            map.put("code",0);
            map.put("message","未能成功查询到该学生的考试信息");
        } else {
            map.put("code",1);
            map.put("studentExamInfo",studentExamInfo1);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}
