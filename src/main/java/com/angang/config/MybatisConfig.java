package com.angang.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lvga
 * @Description
 * @date 2022/9/19 14:54
 */
@Configuration
@MapperScan("com.angang.mapper")
public class MybatisConfig {
    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
