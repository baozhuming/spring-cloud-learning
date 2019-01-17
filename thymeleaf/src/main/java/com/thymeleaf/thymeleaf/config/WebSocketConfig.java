package com.thymeleaf.thymeleaf.config;

import com.thymeleaf.thymeleaf.log.LoggerMessage;
import com.thymeleaf.thymeleaf.log.LoggerQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebSocket配置：1
 */
@Component
@Configuration
@EnableWebSocketMessageBroker//通过注解开启使用STOMP协议来传输基于代理（message broker）的消息，这时控制器支持使用@MessageMapping，就像@RequestMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点（endpoint），并映射的指定的url
        registry.addEndpoint("/endpointWisely").withSockJS();//注册一个STOPMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointChat").withSockJS();
        registry.addEndpoint("/log")
//                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理（Message Broker）
        registry.enableSimpleBroker("/queue","/topic");//广播式配置一个/topic消息代理
        //点对点式应增加一个/queue消息代理
    }

    /**
     * 推送日志到/topic/pullLogger
     */
    @PostConstruct
    public void pushLogger(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LoggerMessage log = LoggerQueue.getInstance().poll();
                        if(log!=null){
                            if(messagingTemplate!=null)
                                messagingTemplate.convertAndSend("/topic/pullLogger",log);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
    }
}
