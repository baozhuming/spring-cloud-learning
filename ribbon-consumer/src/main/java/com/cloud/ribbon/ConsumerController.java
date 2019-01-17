package com.cloud.ribbon;

import com.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    HelloService service;
    @RequestMapping(value = "/ribbon-consumer")
    public String helloConsumer(){
        return service.helloService();
    }
}
