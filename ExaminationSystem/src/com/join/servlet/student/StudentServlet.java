package com.join.servlet.student;

import com.google.gson.Gson;
import com.join.entity.Student;
import com.join.service.student.Impl.StudentServiceImpl;
import com.join.service.student.StudentService;
import com.join.utils.jsonUtil.JSONUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name ="StudentServlet",urlPatterns = "/studentServlet",asyncSupported = true)
public class StudentServlet extends HttpServlet {
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
            case "studentLogin":
                login(req,resp);
                break;
            case "studentRegister":
                register(req,resp);
                break;
        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String jsonString= JSONUtils.getJson(request);
        Gson gson=new Gson();
        Student student=gson.fromJson(jsonString,Student.class);
        HttpSession session=request.getSession();
        StudentService studentService=new StudentServiceImpl();
        Student student1=studentService.queryStudentByInfoService(student.getUsername(),student.getPassword());
        Map map=new HashMap();
        if (student1!=null) {
            Map data=new HashMap();
            data.put("student",student1);
            map.put("code",1);
            map.put("data",data);
            session.setAttribute("student",student1);
        } else {
            map.put("code",0);
            map.put("message","用户不存在，登陆失败");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    // 注册两个问题：
    // 1、检验学号是否唯一
    // 2、上传头像文件
    private void register(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String jsonString=JSONUtils.getJson(request);
        Gson gson=new Gson();
        Map map=new HashMap();
        Student student=gson.fromJson(jsonString,Student.class);
        System.out.println(student);
        // 获取学号
        int sno=student.getSno();
        StudentService studentService=new StudentServiceImpl();
        boolean flag1=false;
        boolean flag2=false;
        try {
            flag1=studentService.queryStudentBySnoService(sno);
            // 查询该学号未被注册
            if (!flag1) {
                flag2=studentService.addStudentByAllInfo(student);
            } else {// 该学号已被注册
                flag1=true;
            }
        } catch (Exception e) {
            map.put("code",0);
            map.put("message","注册失败");
            jsonString=gson.toJson(map);
            PrintWriter out=response.getWriter();
            out.print(jsonString);
            out.flush();
            return;
        }
        if (flag2) {
            map.put("code",1);
            map.put("message","注册成功");
        } else {
            map.put("code",0);
            map.put("message","注册失败");
        }
        if (flag1) {
            map.put("code",0);
            map.put("message","该学号已被注册");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=response.getWriter();
        out.print(jsonString);
        out.flush();
    }
}