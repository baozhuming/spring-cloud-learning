package com.cloud.feign.service;

import com.cloud.feign.bean.User;
import com.cloud.feign.service.impl.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "hello-service", fallback = HelloServiceFallback.class)//调用服务中心已经注册的服务提供者的名称,不分大小写
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);

}
