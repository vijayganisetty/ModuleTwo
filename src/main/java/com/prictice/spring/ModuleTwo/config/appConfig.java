package com.prictice.spring.ModuleTwo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {

    @Bean
    public ModelMapper getModuleMapper(){
        return new ModelMapper();
    }

}
