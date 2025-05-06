package org.example.review;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // กำหนด path ของ API
                .allowedOrigins("http://localhost:3000") // ที่อยู่ของ React app
                .allowedMethods("GET", "POST", "PUT", "DELETE") // เมธอดที่อนุญาต
                .allowedHeaders("*"); // อนุญาตให้ส่ง header ใดๆ
    }
}
