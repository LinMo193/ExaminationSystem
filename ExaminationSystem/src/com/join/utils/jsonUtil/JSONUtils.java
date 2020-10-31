package com.join.utils.jsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONUtils {
    public static String getJson(HttpServletRequest request) throws IOException {
        // 建立缓存流，存储字符串
        StringBuilder stringBuilder=null;
        String jsonString=null;
        // 获取字符流
        BufferedReader reader=new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        stringBuilder=new StringBuilder();
        String str="";
        // 获取字符串
        while ((str=reader.readLine())!=null) {
            stringBuilder.append(str);
        }
        // 获取到json
        jsonString=stringBuilder.toString();
        reader.close();
        return jsonString;
    }
}
