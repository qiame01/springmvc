package com.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private Interceptor1 Interceptor1;
    @Autowired
    private Interceptor2 Interceptor2;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Interceptor1).addPathPatterns("/**"); // todo 更多链式方法，自行查看
        registry.addInterceptor(Interceptor2);
    }
}
