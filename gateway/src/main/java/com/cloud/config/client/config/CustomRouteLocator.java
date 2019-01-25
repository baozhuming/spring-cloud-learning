package com.cloud.config.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);
    private JdbcTemplate jdbcTemplate;
    private ZuulProperties properties;
    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath : {}",servletPath);

    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(locateRoutesFromDB());
        LinkedHashMap<String,ZuulProperties.ZuulRoute> values = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        for (Map.Entry<String,ZuulProperties.ZuulRoute> entry:routesMap.entrySet()){
            String path = entry.getKey();
            if(!path.startsWith("/")){
                path = "/" + path;
            }
            if(StringUtils.hasText(this.properties.getPrefix())){
                path = this.properties.getPrefix()+path;
                if(!path.startsWith("/")){
                    path = "/" + path;
                }
            }
            values.put(path,entry.getValue());
        }
        return values;

    }

    private Map<? extends String,? extends ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String,ZuulProperties.ZuulRoute> routeMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        List<RoutesBean> resutles = jdbcTemplate.query("SELECT * FROM gateway_api_define where enabled = 1 ",new BeanPropertyRowMapper<>(RoutesBean.class));
        for (RoutesBean re: resutles){
            if(StringUtils.isEmpty(re.getPath())){
                continue;
            }
            if(StringUtils.isEmpty(re.getServiceId()) && StringUtils.isEmpty(re.getUrl())){
                continue;
            }
//            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
//            try{
//                BeanUtils.copyProperties(re,zuulRoute);
//            }catch (Exception e){
//                logger.error("==============================load zuul route info from db with error==============",e);
//            }
            routeMap.put(re.getPath(),re);
        }
        return routeMap;
    }
}
