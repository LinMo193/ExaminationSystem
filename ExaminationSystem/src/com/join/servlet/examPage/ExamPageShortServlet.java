package com.join.servlet.examPage;

import com.google.gson.Gson;
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

@WebServlet(name ="ExamPageShortServlet",urlPatterns = "/examPageShortServlet",asyncSupported = true)
public class ExamPageShortServlet extends HttpServlet {
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
            case "addShortQuestion":
                addShortQuestion(req,resp);
                break;
            case "deleteShortQuestion":
                deleteShortQuestion(req,resp);
                break;
            case "getOnlyShortQuestion":
                getOnlyShortQuestion(req,resp);
                break;
            case "getAllShortQuestion":
                getAllShortQuestion(req,resp);
                break;
            case "getAllShortByPage":
                getAllShortByPage(req,resp);
                break;
        }

    }

    private void addShortQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        Map map=new HashMap();
        ExamPageShort examPageShort=gson.fromJson(jsonString,ExamPageShort.class);
        HttpSession session=req.getSession();
        System.out.println("开始进行session传值");
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        System.out.println(teacher);
        String teacherName=teacher.getUsername();
        System.out.println("ExamPageSingleServlet中的"+teacherName);

        // 获取课程名称和试题内容，判断是否重复
        String courseName=examPageShort.getCourseName();
        String questionContent=examPageShort.getQuestionContent();
        ExamPageShortService examPageShortService=new ExamPageShortServiceImpl();
        ExamPageShort examPageShort1=null;
        boolean flag1=false;
        boolean flag2=false;
        try {
            examPageShort1=examPageShortService.queryShortByTwoInfoService(courseName,questionContent);
            // 未查询到该试题
            if (examPageShort1==null) {
                flag2=examPageShortService.addShortByInfoService(examPageShort,teacherName);
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
        if (examPageShort1!=null) {
            map.put("code",0);
            map.put("message","该试题已在题库中");
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除简答题
     * @param req
     * @param resp
     */
    private void deleteShortQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageShort examPageShort=gson.fromJson(jsonString,ExamPageShort.class);
        ExamPageShortService examPageShortService=new ExamPageShortServiceImpl();
        boolean flag=examPageShortService.deleteShortByInfoService(examPageShort.getCourseName(),examPageShort.getQuestionContent());
        Map map=new HashMap();
        if (flag) {
            Map data=new HashMap();
            map.put("code",1);
            map.put("message","删除简答题成功");
        } else {
            map.put("code",0);
            map.put("message","删除简答题失败");
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
    private void getOnlyShortQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonString= JSONUtils.getJson(req);
        Gson gson=new Gson();
        ExamPageShort examPageShort1=gson.fromJson(jsonString,ExamPageShort.class);
        ExamPageShortService examPageShortService=new ExamPageShortServiceImpl();
        ExamPageShort examPageShort=examPageShortService.queryShortByTwoInfoService(examPageShort1.getCourseName(),examPageShort1.getQuestionContent());
        Map map=new HashMap();
        if (examPageShort==null) {
            map.put("code",0);
            map.put("message","未能成功查询到该简答题");
        } else {
            Map data=new HashMap();
            data.put("examPageShort",examPageShort);
            map.put("code",1);
            map.put("data",data);
        }
        jsonString=gson.toJson(map);
        PrintWriter out=resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void getAllShortQuestion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ExamPageShortService examPageShortService=new ExamPageShortServiceImpl();
        List<ExamPageShort> list=examPageShortService.listAllExamPageShortService();
        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到所有的简答题");
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
    private void getAllShortByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
        ExamPageShortService examPageShortService=new ExamPageShortServiceImpl();
        int totalCount=examPageShortService.getExamPageShortTotalCount();
        page.setTotalCount(totalCount);
        page.setTotalPage(totalCount);

//        获取计算后的数据列表
        List<ExamPageShort> list=examPageShortService.queryExamPageShortByPage(currentPage);
        page.setShortList(list);

        Map map=new HashMap();
        if (list==null) {
            map.put("code",0);
            map.put("message","未能成功查询到简答题");
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
