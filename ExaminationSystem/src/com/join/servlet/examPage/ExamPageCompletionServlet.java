package com.join.servlet.examPage;

import com.google.gson.Gson;
import com.join.dao.examPage.ExamPageCompletionDao;
import com.join.entity.*;
import com.join.service.examPage.ExamPageCompletionService;
import com.join.service.examPage.ExamPageShortService;
import com.join.service.examPage.ExamPageSingleService;
import com.join.service.examPage.Impl.ExamPageCompletionServiceImpl;
import com.join.service.examPage.Impl.ExamPageShortServiceImpl;
import com.join.service.examPage.Impl.ExamPageSingleServiceImpl;
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

@WebServlet(name ="ExamPageCompletionServlet",urlPatterns = "/examPageCompletionServlet",asyncSupported = true)
public class ExamPageCompletionServlet extends HttpServlet {
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
            case "addCompletionQuestion":
                addCompletionQuestion(req,resp);
                break;
            case "deleteCompletionQuestion":
                deleteCompletionQuestion(req,resp);
                break;
            case "getOnlyCompletionQuestion":
                getOnlyCompletionQuestion(req,resp);
                break;
            case "getAllCompletionQuestion":
                getAllCompletionQuestion(req,resp);
                break;
            case "getAllCompletionByPage":
                getAllCompletionByPage(req,resp);
                break;
        }

    }

    /**
     * 删除填空题
     * @param req
     * @param resp
     */
    private void deleteCompletionQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageCompletion examPageCompletion=gson.fromJson(jsonString,ExamPageCompletion.class);
        ExamPageCompletionService examPageCompletionService=new ExamPageCompletionServiceImpl();
        boolean flag=examPageCompletionService.deleteSingleByInfoService(examPageCompletion.getCourseName(),examPageCompletion.getQuestionContent());
        Map map=new HashMap();
        if (flag) {
            Map data=new HashMap();
            map.put("code",1);
            map.put("message","删除填空题成功");
        } else {
            map.put("code",0);
            map.put("message","删除填空题失败");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 添加填空题信息
     * @param req
     * @param resp
     */
    private void addCompletionQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamPageCompletion examPageCompletion=gson.fromJson(jsonString,ExamPageCompletion.class);
        HttpSession session=req.getSession();
        System.out.println("开始进行session传值");
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        System.out.println(teacher);
        String teacherName=teacher.getUsername();
        System.out.println("ExamPageSingleServlet中的"+teacherName);

        // 获取课程名称和试题内容，判断是否重复
        String courseName=examPageCompletion.getCourseName();
        String questionContent=examPageCompletion.getQuestionContent();
        ExamPageCompletionService examPageCompletionService=new ExamPageCompletionServiceImpl();
        ExamPageCompletion examPageCompletion1=null;
        boolean flag1=false;
        boolean flag2=false;
        try {
            examPageCompletion1=examPageCompletionService.queryCompletionByTwoInfoService(courseName,questionContent);
            // 未查询到该试题
            if (examPageCompletion1==null) {
                flag2=examPageCompletionService.addCompletionByInfoService(examPageCompletion,teacherName);
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
        if (examPageCompletion1!=null) {
            map.put("code",0);
            map.put("message","该试题已在题库中");
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
    private void getOnlyCompletionQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageCompletion examPageCompletion1=gson.fromJson(jsonString,ExamPageCompletion.class);
        ExamPageCompletionService examPageCompletionService=new ExamPageCompletionServiceImpl();
        ExamPageCompletion examPageCompletion=examPageCompletionService.queryCompletionByTwoInfoService(examPageCompletion1.getCourseName(),examPageCompletion1.getQuestionContent());
        Map map=new HashMap();
        if (examPageCompletion==null) {
            map.put("code",0);
            map.put("message","未能成功查询到该填空题");
        } else {
            Map data=new HashMap();
            data.put("examPageCompletion",examPageCompletion);
            map.put("code",1);
            map.put("data",data);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 查询所有填空题信息
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getAllCompletionQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ExamPageCompletionService examPageCompletionService=new ExamPageCompletionServiceImpl();
        List<ExamPageCompletion> list=examPageCompletionService.listAllExamPageCompletionService();
        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到所有的填空题");
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
    private void getAllCompletionByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
        ExamPageCompletionService examPageCompletionService=new ExamPageCompletionServiceImpl();
        int totalCount=examPageCompletionService.getExamPageCompletionTotalCount();
        page.setTotalCount(totalCount);
        page.setTotalPage(totalCount);

//        获取计算后的数据列表
        List<ExamPageCompletion> list=examPageCompletionService.queryExamPageCompletionByPage(currentPage);
        page.setCompletionList(list);

        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到填空题");
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
