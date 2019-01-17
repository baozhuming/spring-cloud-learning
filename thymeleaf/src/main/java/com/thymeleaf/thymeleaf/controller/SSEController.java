package com.thymeleaf.thymeleaf.controller;

import com.alibaba.fastjson.JSONObject;
import com.thymeleaf.thymeleaf.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * 演示服务消息推送服务：第一种：SSE技术
 */
@Controller
public class SSEController {
    @RequestMapping(value = "/push", produces = "text/event-stream")
    public @ResponseBody String push(){
        Random r = new Random();
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
    }
}
