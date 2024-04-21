/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Admin
 */
//Nơi nhận request
@Configuration
public class DispatcherServletInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
    
    //
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
          HibernateConfig.class  
        };
    }
    
    //Đăng kí các rổ đậu (IOC Container)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
            WebAppContextConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
}
