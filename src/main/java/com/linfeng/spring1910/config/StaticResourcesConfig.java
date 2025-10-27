package com.linfeng.spring1910.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define the external path where your photos are stored
        String photoPath = "file:///C:/Users/16939/Desktop/Nouveau dossier/blog/server/cover/";

        // IMPORTANT: Replace the placeholder above with your actual photo directory.
        // On Windows, it might look like: "file:///C:/Users/YourUser/Images/uploads/"
        // On Linux/macOS, it might look like: "file:///home/user/app/photos/"

        // Map the URL path (e.g., http://localhost:8080/images/...)
        registry.addResourceHandler("/cover/**")
                // To the physical location of the photos
                .addResourceLocations(photoPath);
    }
}
