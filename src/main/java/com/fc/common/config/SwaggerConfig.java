package com.fc.common.config;

import com.fasterxml.jackson.core.filter.TokenFilter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Florence
 * @since 2023/05/04
 */


public class SwaggerConfig implements InitializingBean {
    @Autowired
    private Environment environment;
    private boolean enable;
    /*@Bean
    public Docket sysDocket() {
        return groupDocket(
                "01_系统",
                "/sys.*",
                "系统模块文档",
                "用户，角色，资源");
    }*/

    @Bean
    public Docket metadataDocket() {
        return groupDocket(
                "02_元数据",
                "/(dict.*|plate.*)",
                "元数据模块文档",
                "数据字典类型，数据字典条目，省份，城市");
    }

    @Bean
    public Docket examDocket() {
        return groupDocket(
                "03_考试",
                "/exam.*",
                "考试模块文档",
                "考场，科1科4，科2科3");
    }

    private Docket groupDocket(String group,
                               String regex,
                               String title,
                               String description) {
        return basicDocket()
                .groupName(group)
                .apiInfo(apiInfo(title, description))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//只显示带有该类注释的接口
                .paths(PathSelectors.regex(regex))
                .build();
    }

    private Docket basicDocket() {
        /*RequestParameter token = new RequestParameterBuilder()
                .name(TokenFilter.HEADER_TOKEN)
                .description("用户登录令牌")
                .in(ParameterType.HEADER)
                .build();*/
        return new Docket(DocumentationType.SWAGGER_2)
                //.globalRequestParameters(List.of(token))
                .ignoredParameterTypes(
                        HttpSession.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class)
                .enable(enable);
    }

    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("1.0.0")
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        enable = environment.acceptsProfiles(Profiles.of("dev", "test"));
    }
}
