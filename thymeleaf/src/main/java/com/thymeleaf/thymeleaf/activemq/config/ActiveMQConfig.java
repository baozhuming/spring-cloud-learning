package com.thymeleaf.thymeleaf.activemq.config;

//import com.thymeleaf.thymeleaf.activemq.Msg;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.core.JmsTemplate;

/**
 * 1.需要实现CommandLineRunner
 * 2.重写run方法
 */
@Configuration
public class ActiveMQConfig  {
    /**@Autowired
    JmsTemplate jmsTemplate;

     * ActiveMQ队列的创建
     * 1.引入依赖
     * 2.实现CommandLineRunner接口
     * 3.注入JmsTemplate的Bean
     * 4.通过JmsTemplate的send方法向my-destination目的地发送Msg的消息，也等于在消息代理上定义了一个目的地叫my-destination
     * @param args
     * @throws Exception

    @Override
    public void run(String... args) throws Exception {//消息提供者
        jmsTemplate.send("my-destination",new Msg());
    }*/
}
