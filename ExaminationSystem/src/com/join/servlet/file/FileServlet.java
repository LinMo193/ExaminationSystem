package com.join.servlet.file;

import com.google.gson.Gson;
import com.join.entity.Teacher;
import com.join.utils.jsonUtil.JSONUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@MultipartConfig
@WebServlet(name ="FileServlet",urlPatterns = "/fileServlet",asyncSupported = true)
public class FileServlet extends HttpServlet {
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
            case "addPicture":
                addPicture(req,resp);
                break;
        }
    }

    /**
     * 上传头像
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void addPicture(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        Part part=request.getPart("img");
        // 获取上传的文件名拓展名
        String disposition=part.getSubmittedFileName();
        String suffix=disposition.substring(disposition.lastIndexOf("."));
        // 随机生成的uuid
        String filename= UUID.randomUUID()+suffix;
        String serverpath=request.getServletContext().getRealPath("pic");
        // 如果文件夹不存在则新建
        File file=new File(serverpath);
        if (!file.exists()) {
            file.mkdir();
        }
        String filepart=serverpath+"/"+filename;
        // 给前端返回文件路径
        part.write(filepart);
        String projectServerPath=request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+request.getContextPath()+"/pic/"+filename;
        PrintWriter out=response.getWriter();
        Gson gson=new Gson();
        Map map=new HashMap();
        map.put("code","1");
        map.put("img",projectServerPath);
        String jsonString=gson.toJson(map);
        out.print(jsonString);
        out.flush();
    }
}
