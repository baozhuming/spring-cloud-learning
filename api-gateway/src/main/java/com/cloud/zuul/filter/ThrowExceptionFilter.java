package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ThrowExceptionFilter extends ZuulFilter {
    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";
//    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);
    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
        return false;
    }

    @Override
    public Object run() throws ZuulException {
//        log.info("This is a pre filter, it will throw a RuntimeException");

        doSomething();
        return null;

    }

    private Throwable getOriginException(Throwable e){
        e = e.getCause();
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }
    private void doSomething(){
        throw new RuntimeException("Exist some errors...");
    }
}
