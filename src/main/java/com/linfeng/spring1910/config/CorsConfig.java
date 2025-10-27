package com.linfeng.spring1910.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:3000") // The 'origins' you specified
                .allowCredentials(true) // The 'allowCredentials' you specified
                .allowedHeaders("*") // The 'allowedHeaders' you specified
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Common methods
    }
}