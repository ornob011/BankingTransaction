package com.banking.config;

import com.banking.interceptors.AppInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration // This annotation is used to tell the spring that this class is used to configure the application.
@ComponentScan(basePackages = {"com.banking"})
// This annotation is used to tell the spring that this class is used to scan the components.
public class AppConfig extends WebMvcConfigurationSupport {

    // This method is used to configure the static resources.
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "images/**", "js/**", "icon-fonts/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/images/", "classpath:/static/js/", "classpath:/static/icon-fonts/");
    }
    // End Of Resource Handler.

    // This method is used to configure the view resolver.
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver(); // This is the view resolver object.
        jspViewResolver.setPrefix("/WEB-INF/jsp/"); // This is the location of the jsp files.
        jspViewResolver.setSuffix(".jsp"); // This is the extension of the jsp files.
        jspViewResolver.setViewClass(JstlView.class); // This is the view class.

        return jspViewResolver;
    }
    // End Of View Resolver.


    // This method is used to configure the interceptors.
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/app/*"); // This is used to add the interceptor to the registry.

    }
    // End Of Interceptor Registry.
}
