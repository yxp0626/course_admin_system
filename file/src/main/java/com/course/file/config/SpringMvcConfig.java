package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xiaoping Yu
 * @date 2021/6/7 - 19:22
 */

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer{
//静态资源访问路径配置，封装了底层访问的路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/f/**").addResourceLocations("file:A:/file/imooc/course/");
    }
}
