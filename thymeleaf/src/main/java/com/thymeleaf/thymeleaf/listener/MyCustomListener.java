package com.thymeleaf.thymeleaf.listener;

import org.springframework.context.ApplicationListener;

/**
 * 自定义监听器：2 监听主题的消费者
 */
public class MyCustomListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        String msg = myApplicationEvent.getMsg();
        System.out.println("消费者收到MyCustomListener的消息:"+msg);
    }
}
