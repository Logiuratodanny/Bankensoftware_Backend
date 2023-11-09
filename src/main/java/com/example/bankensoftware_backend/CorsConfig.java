package com.example.bankensoftware_backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Erlaubt Zugriff auf alle Endpunkte
                .allowedOrigins("*") // Erlaubt Anfragen von allen Ursprüngen. Besser ist, dies auf spezifische Ursprünge zu beschränken.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Erlaubte HTTP-Methoden
                .allowedHeaders("*"); // Erlaubte Header
    }
}

