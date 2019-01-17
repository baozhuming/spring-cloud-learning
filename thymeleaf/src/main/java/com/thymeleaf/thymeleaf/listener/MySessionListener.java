package com.thymeleaf.thymeleaf.listener;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * 统计在线人数
 */
@WebListener
public class MySessionListener implements HttpSessionListener {
    private static Set<String> sessionMap = new HashSet<String>();
    @Autowired
    private ServletContext servletContext;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("获取的session："+se.getSession().getId());
        boolean flag = true;
        for (String key:sessionMap){
            if(key.equals(se.getSession().getId())){
                flag = false;
            }
        }
        if(flag){
            sessionMap.add(se.getSession().getId());
        }
        ServletContext ctx = se.getSession().getServletContext();
        ctx.setAttribute("numSessions",sessionMap.size());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        boolean flag = false;
        for (String key:sessionMap){
            if(key.equals(se.getSession().getId())){
                flag = true;
            }
        }
        if(flag){
            sessionMap.remove(se.getSession().getId());
        }
        ServletContext ctx = se.getSession().getServletContext();
        ctx.setAttribute("numSessions",sessionMap.size());
    }
}
