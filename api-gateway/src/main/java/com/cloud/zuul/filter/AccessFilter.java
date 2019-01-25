package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 在路由器端做拦截和校验
 */
@Component
public class AccessFilter extends ZuulFilter {
//    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * pre：可以在请求被路由之前调用。
     * routing：在路由请求时候被调用。
     * post：在routing和error过滤器之后被调用。
     * error：处理请求时发生错误时被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null){
//            log.warn("access token is empty!--------------------------");
            ctx.setSendZuulResponse(false);//设置false直接拦截返回到上游服务器，不再往下继续请求
            ctx.setResponseStatusCode(401);
            return null;
        }
//        log.info("access token ok!");
        return null;
    }
}
