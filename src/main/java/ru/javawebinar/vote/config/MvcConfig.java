package ru.javawebinar.vote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("fing bag login");// TEST
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/news").setViewName("news");
    }
}