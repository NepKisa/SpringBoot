package com.neptune.springboot02webadmin.config;

import com.neptune.springboot02webadmin.servlet.MyFilter;
import com.neptune.springboot02webadmin.servlet.MyServletContextListener;
import com.neptune.springboot02webadmin.servlet.MyWebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
//(proxyBeanMethods = true) 保证依赖的组件始终是单实例的
@Configuration
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet() {
        MyWebServlet myWebServlet = new MyWebServlet();

        return new ServletRegistrationBean(myWebServlet, "/my", "/my02");
    }


    @Bean
    public FilterRegistrationBean myFilter() {

        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my", "/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }
}