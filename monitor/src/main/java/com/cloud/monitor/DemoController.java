package com.cloud.monitor;

import com.cloud.monitor.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Random;

@RestController
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Registration registration; // 服务注册
    @RequestMapping(value = "/hello")
    public List<ServiceInstance> index() throws Exception{

        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        //模拟短路，等待几秒钟,有重试机制
        int sleepTime = 2000+new Random().nextInt(3000);
        logger.info("++++++++++++++sleepTime:"+sleepTime);
        Thread.sleep(sleepTime);
        logger.info("/hello,list:"+list.toString());
//        for (ServiceInstance is : list){
//            String host = is.getHost();
//            int port = is.getPort();
//            String id = is.getServiceId();
//            URI uri = is.getUri();
//            logger.debug("/hello,host:"+host+", service_id:"+id+",uri:"+uri);
//            logger.info("/hello,host:"+host+", service_id:"+id+",uri:"+uri);
//            logger.warn("/hello,host:"+host+", service_id:"+id+",uri:"+uri);
//            logger.error("/hello,host:"+host+", service_id:"+id+",uri:"+uri);
//        }
        return list;
    }
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "Hello "+name;
    }
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "Hello "+user.getName()+", " + user.getAge();
    }
}
