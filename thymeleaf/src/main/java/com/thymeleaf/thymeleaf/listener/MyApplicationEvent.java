package com.thymeleaf.thymeleaf.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义监听器：1，事件
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String msg;

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
