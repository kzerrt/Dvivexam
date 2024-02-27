package com.fc.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fc.pojo.vo.list.ExamPlaceCourseVo;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Florence
 * @since 2023/04/27
 */

@Configuration
@MapperScan("com.fc.mapper")
public class MybatisPlusConfig implements InitializingBean {
    /**
    * 开启分页插件
    * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor(DbType.H2);
        // 当页数超过总页数时，自动跳回到第1页
        innerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        /*
        首先，拥有lambda cache的实体类Entity，才能使用LambdaQueryWrapper<Entity>，
        默认情况下，只有BaseMapper<Entity>中的Entity类，才拥有lambda cache,
        其他类需要通过TableInfoHelper手动添加lambda cache
        */
        MapperBuilderAssistant assistant = new MapperBuilderAssistant(new MybatisConfiguration(), "");
        TableInfoHelper.initTableInfo(assistant, ExamPlaceCourseVo.class);
    }
}
