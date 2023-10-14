package com.example.carrentalproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/dashboard").setViewName("acc-admin/admin-dashboard");
        registry.addViewController("/user/dashboard").setViewName("acc-user/dashboard");
    }
}
