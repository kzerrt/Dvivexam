package com.fc.common.config;

import com.fc.common.property.JKProperty;
import com.fc.filter.ErrorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @author Florence
 * @since 2023/04/27
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JKProperty properties;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // /**表示对所有的路径开放全局跨域访问权限
        registry.addMapping("/**")
                // 开放哪些IP、端口、域名的访问权限
                .allowedOrigins(properties.getCfg().getCorsOrigins())
                // 是否允许发送Cookie信息
                .allowCredentials(true)
                // 哪些HTTP方法允许跨域访问
                .allowedMethods("GET", "POST")
                // 允许HTTP请求中的携带哪些Header信息
                .allowedHeaders("*");
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        // .exposedHeaders("*");
    }

    /**
     * 将filter设置为最前
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        // 设置Filter
        bean.setFilter(new ErrorFilter());
        bean.addUrlPatterns("/*");
        // 最高权限
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
