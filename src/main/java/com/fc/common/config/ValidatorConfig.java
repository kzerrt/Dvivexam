package com.fc.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author Florence
 * @since 2023/05/04
 */

@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        /**
         * 设置快速检查，在校验到第一个错误时，不在向下进行
         * */
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory().getValidator();
    }
}
