package com.thymeleaf.thymeleaf.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义监听器：3  发布者
 */
public class MyStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent myApplicationEvent) {
        System.out.println("准备发消息了");
        MyApplicationEvent myAppEvent = new MyApplicationEvent("myEvent");
        myAppEvent.setMsg("自定义了一条消息");
        myApplicationEvent.getApplicationContext().publishEvent(myAppEvent);
    }
}
