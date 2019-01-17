package com.cloud.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "helloFallback")//断路回调，当集成的服务提供者有其中一个down掉的被轮询到了，就会回调方法，等到服务注册中心
    public String helloService(){
        long startTime = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://HELLO-SERVICE/demo",String.class).getBody();
        long end = System.currentTimeMillis();
        System.out.println("Spend Time:"+(end-startTime));
        return result;
    }
    public String helloFallback(){
        return "error";
    }
}
