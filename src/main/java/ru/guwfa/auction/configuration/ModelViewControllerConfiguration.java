package ru.guwfa.auction.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class ModelViewControllerConfiguration implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    //все обращения по адресу /img/.. будут перенаправляться на file:/uploadPath/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + File.separator);

        //classpath означает, что поиск будет не во всей файловой системе, а только в дереве проекта
        registry.addResourceHandler("static/**")
                .addResourceLocations("classpath:/static" + File.separator);
    }

    @Bean
    public Locale locale(){
        return new Locale("ru");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(locale());
    }

    @Bean
    public ResourceBundle resourceBundle(){
        return ResourceBundle.getBundle("lang.messages", locale());
    }
}

