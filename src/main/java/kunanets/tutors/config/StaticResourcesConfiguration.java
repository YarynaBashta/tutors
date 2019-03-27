package kunanets.tutors.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static kunanets.tutors.service.FileService.IMG_DIR;


@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class StaticResourcesConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(IMG_DIR);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + IMG_DIR);
    }

}
