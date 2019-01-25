package com.cloud.feign.service.impl;

import com.cloud.feign.bean.User;
import com.cloud.feign.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * 1.继承连接远程服务的接口，实现异常熔断后的回调
 * 2.在接口的FeignClient注解中中定义fallback
 */
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("未知",0);
    }

    @Override
    public String hello(User user) {
        return "error";
    }
}
