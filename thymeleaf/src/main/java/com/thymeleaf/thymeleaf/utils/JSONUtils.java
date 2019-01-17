package com.thymeleaf.thymeleaf.utils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtils {
    public static JSONObject getResultJSON(int code,String message,String exceptionInfo){
        JSONObject result = new JSONObject();
        result.put("code",code);
        result.put("message",message);
        result.put("exceptionInfo",exceptionInfo);
        return result;
    }
}
