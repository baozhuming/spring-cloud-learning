package com.thymeleaf.thymeleaf.service;

import com.alibaba.fastjson.JSONObject;
import com.thymeleaf.thymeleaf.utils.JSONUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 服务器推送消息方式2：跨浏览器的 --2
 */
@Service
public class PushServiceImpl {
    private DeferredResult<JSONObject> deferredResult;//在PushService里产生DeferredResult给控制器使用，通过@Scheduled注解的方法定时更新DeferredResult
    public DeferredResult<JSONObject> getAsyncUpdate(){//
        deferredResult = new DeferredResult<JSONObject>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh(){//
        if(deferredResult != null){
            JSONObject result = JSONUtils.getResultJSON(1,new Long(System.currentTimeMillis()).toString(),"");
            deferredResult.setResult(result);
        }
    }
}
