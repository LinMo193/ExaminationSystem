package com.join.servlet.examPage;

import com.google.gson.Gson;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageSingle;
import com.join.entity.Page;
import com.join.entity.Student;
import com.join.entity.Teacher;
import com.join.service.examPage.ExamPageSingleService;
import com.join.service.examPage.Impl.ExamPageSingleServiceImpl;
import com.join.service.student.Impl.StudentServiceImpl;
import com.join.service.student.StudentService;
import com.join.utils.jsonUtil.JSONUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="ExamPageSingleServlet",urlPatterns = "/examPageSingleServlet",asyncSupported = true)
public class ExamPageSingleServlet extends HttpServlet {
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
            case "addSingleQuestion":
                addSingleQuestion(req,resp);
                break;
            case "deleteSingleQuestion":
                deleteSingleQuestion(req,resp);
                break;
            case "getOnlySingleQuestion":
                getOnlySingleQuestion(req,resp);
                break;
            case "getAllSingleQuestion":
                getAllSingleQuestion(req,resp);
                break;
            case "getAllSingleByPage":
                getAllSingleByPage(req,resp);
                break;
        }
    }

    /**
     * 增加单选题信息
     * @param req
     * @param resp
     */
    private void addSingleQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamPageSingle examPageSingle=gson.fromJson(jsonString,ExamPageSingle.class);
        HttpSession session=req.getSession();
        System.out.println("开始进行session传值");
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        System.out.println(teacher);
        String teacherName=teacher.getUsername();
        System.out.println("ExamPageSingleServlet中的"+teacherName);

        // 获取课程名称和试题内容，判断是否重复
        String courseName=examPageSingle.getCourseName();
        String questionContent=examPageSingle.getQuestionContent();
        ExamPageSingleService examPageSingleService=new ExamPageSingleServiceImpl();
        ExamPageSingle examPageSingle1=null;
        boolean flag1=false;
        boolean flag2=false;
        try {
            examPageSingle1=examPageSingleService.querySingleByTwoInfoService(courseName,questionContent);
            // 未查询到该试题
            if (examPageSingle1==null) {
                flag2=examPageSingleService.addSingleByInfoService(examPageSingle,teacherName);
            } else {// 试题已被添加过
//                flag1=true;
            }
        } catch (Exception e) {
            map.put("code",0);
            map.put("message","添加试题失败");
            jsonString=gson.toJson(map);
            PrintWriter out=resp.getWriter();
            out.print(jsonString);
            out.flush();
            return;
        }
        if (flag2) {
            map.put("code",1);
            map.put("message","添加试题成功");
        } else {
            map.put("code",0);
            map.put("message","添加试题失败");
        }
        if (examPageSingle1!=null) {
            map.put("code",0);
            map.put("message","该试题已在题库中");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除单选题
     * @param req
     * @param resp
     */
    private void deleteSingleQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageSingle examPageSingle=gson.fromJson(jsonString,ExamPageSingle.class);
        ExamPageSingleService examPageSingleService=new ExamPageSingleServiceImpl();
        boolean flag=examPageSingleService.deleteSingleByInfoService(examPageSingle.getCourseName(),examPageSingle.getQuestionContent());
        Map map=new HashMap();
        if (flag) {
            Map data=new HashMap();
            map.put("code",1);
            map.put("message","删除单选题成功");
        } else {
            map.put("code",0);
            map.put("message","删除单选题失败");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 查询某一试题的内容
     * @param req
     * @param resp
     */
    private void getOnlySingleQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageSingle examPageSingle1=gson.fromJson(jsonString,ExamPageSingle.class);
        ExamPageSingleService examPageSingleService=new ExamPageSingleServiceImpl();
        ExamPageSingle examPageSingle=examPageSingleService.querySingleByTwoInfoService(examPageSingle1.getCourseName(),examPageSingle1.getQuestionContent());
        Map map=new HashMap();
        if (examPageSingle==null) {
            map.put("code",0);
            map.put("message","未能成功查询到该单选题");
        } else {
            Map data=new HashMap();
            data.put("examPageSingle",examPageSingle);
            map.put("code",1);
            map.put("data",data);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 查询所有单选题信息
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getAllSingleQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ExamPageSingleService examPageSingleService=new ExamPageSingleServiceImpl();
        List<ExamPageSingle> list=examPageSingleService.listAllExamPageSingleService();
        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到所有的单选题");
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

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    private void getAllSingleByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Page page=gson.fromJson(jsonString,Page.class);
//        如果第一次进行分页，就定位到第一页
        int currentPage=page.getCurrentPage();
        if (currentPage==0) {
            currentPage=1;
        }
        page.setCurrentPage(currentPage);

//        获取总数据条数
        ExamPageSingleService examPageSingleService=new ExamPageSingleServiceImpl();
        int totalCount=examPageSingleService.getExamPageSingleTotalCount();
        page.setTotalCount(totalCount);
        page.setTotalPage(totalCount);

//        获取计算后的数据列表
        List<ExamPageSingle> list=examPageSingleService.queryExamPageSingleByPage(currentPage);
        page.setSingleList(list);

        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到单选题");
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
