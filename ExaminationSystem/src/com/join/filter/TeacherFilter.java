package com.join.filter;

import com.google.gson.Gson;
import com.join.entity.Student;
import com.join.entity.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "TeacherFilter")
public class TeacherFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Teacher teacher=(Teacher) session.getAttribute("teacher");
        Gson gson = new Gson();
        Map map = new HashMap();
        String jsonString;
        if (teacher == null) {
            map.put("code", 0);
            map.put("message","filter拦截成功");
            jsonString = gson.toJson(map);
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            out.flush();
        } else {
            chain.doFilter(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
