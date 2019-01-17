package com.thymeleaf.thymeleaf.interceptor;

import com.thymeleaf.thymeleaf.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 一般是做登陆验证
 * 自定义拦截器：1
 */

public class InterceptorConf implements HandlerInterceptor {
    @Value("${spring.profiles.active}")
    private String env;
    /**
     * 预处理回调方法，实现处理器的预处理(如登陆检测)
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle被调用");
        HttpSession session = request.getSession();
        //判断用户是否已经登陆
//        if(session.getAttribute("session") != null){
//            return true;
//        }else{
//            String url = "/login";
//            response.sendRedirect(url);
//            return false;
//        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
     * 此时可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    /**
     * 整个请求处理完毕回调方法
     * 即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
