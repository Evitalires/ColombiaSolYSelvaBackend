package com.ColombiaSolySelva.ColombiaSolYSelva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeamos la URL /IMG/imgProductos/** a la carpeta física dentro del proyecto
        // Esto permite que el backend sirva las imágenes desde la carpeta que
        // solicitaste
        Path uploadDir = Paths.get("src/main/resources/static/IMG/imgProductos");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/IMG/imgProductos/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}
