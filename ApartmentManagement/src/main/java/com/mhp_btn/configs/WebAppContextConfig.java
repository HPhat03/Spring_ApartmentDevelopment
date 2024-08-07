/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.configs;


import com.mhp_btn.formatters.FloorsFormatter;
import com.mhp_btn.formatters.RentalConstractFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Admin
 */
//RỔ ĐẬU CHỨA CÁC ĐỐI TƯỢNG ÍNSTANCE CỦA WEB 
@Configuration
@EnableWebMvc //Kích hoạt câu lệnh mặc định của webMVC
@EnableTransactionManagement
@ComponentScan(basePackages = { //Chỉ nơi scan các annotation trong các package
    "com.mhp_btn.controllers",
    "com.mhp_btn.repositories",
    "com.mhp_btn.services",
    "com.mhp_btn.components"
})
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //Tạo 1 view Resolver -> zô đường dẫn đây mà lấy tài nguyên lấy file jsp
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setViewClass(JstlView.class);
        r.setPrefix("/WEB-INF/pages/");
        r.setSuffix(".jsp");

        return r;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");
        return m;
    }
    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    @Override
    public Validator getValidator(){
        return validator();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new FloorsFormatter());
        registry.addFormatter(new RentalConstractFormatter());
    }
    //    @Bean
//    public WebAppValidator userValidator() {
//        Set<Validator> springValidators = new HashSet<>();
//        springValidators.add(new PassValidator());
//        WebAppValidator validator = new WebAppValidator();
//        validator.setSpringValidators(springValidators);
//        return validator;
//}

    
}
