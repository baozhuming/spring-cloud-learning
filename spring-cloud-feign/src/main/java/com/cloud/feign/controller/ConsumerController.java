package com.cloud.feign.controller;

import com.cloud.feign.bean.User;
import com.cloud.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(){
        return helloService.hello("包竹明");
    }
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello2(){
        User user = helloService.hello("包竹明",26);
        return user;
    }
    @RequestMapping(value = "/hello3")
    public String hello3(){

        return helloService.hello(new User("乘车",11));
    }

}
