package com.timber.config;

import org.springframework.context.annotation.*;

// 标志该类为 Spring 的核心配置类
@Configuration
// <context:component-scan base-package="com.timber"/>
@ComponentScan("com.timber")
// <import resource=""/>
@Import(DataSourceConfiguration.class)

public class SpringConfiguration {

}
