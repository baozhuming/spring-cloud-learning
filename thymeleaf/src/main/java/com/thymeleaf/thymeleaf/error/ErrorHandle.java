package com.thymeleaf.thymeleaf.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandle  {
    private final static String DEFAULT_ERROR_VIEW = "error";//错误信息页
    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;//错误信息的构建工具
    /**
     * 根据业务规则,统一处理异常。
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Throwable error) {

        //1.若为AJAX请求,则返回异常信息(JSON)
        if (isAjaxRequest(request)) {
            error.printStackTrace();
            return errorInfoBuilder.getErrorInfo(request,error);
        }
        error.printStackTrace();
        //2.其余请求,则返回指定的异常信息页(View).
        return new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo", errorInfoBuilder.getErrorInfo(request, error));
    }

    private boolean isAjaxRequest(HttpServletRequest request) {

        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
