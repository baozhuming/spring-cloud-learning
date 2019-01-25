package com.cloud.zuul.utis;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtils {
    public static void responseOutJson(HttpServletResponse response, Object o){
        try {
            response.setContentType("application/json;charset=utf8");
            PrintWriter writer = response.getWriter();
            writer.println(JSONObject.toJSONString(o));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
