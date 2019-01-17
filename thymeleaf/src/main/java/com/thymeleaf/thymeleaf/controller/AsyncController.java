package com.thymeleaf.thymeleaf.controller;

import com.alibaba.fastjson.JSONObject;
import com.thymeleaf.thymeleaf.service.PushServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 服务器推送消息方式2：跨浏览器的 --1
 */
@Controller
public class AsyncController {
    @Autowired
    PushServiceImpl pushService;//1

    @RequestMapping(value = "/defer")
    @ResponseBody
    public DeferredResult<JSONObject> deferredCall(){//2
        return pushService.getAsyncUpdate();
    }
}
