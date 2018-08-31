package com.wkp.datasource.proxy.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataSourceProxyProperties.class)
public class DataSourceProxyConfigure {
    @Bean
    @ConditionalOnProperty(prefix = "data.source.proxy", value = "enabled", havingValue = "true")
    BeanPostProcessor dataSourceProxyBeanProcessor() {
        return new DataSourceProxyBeanPostProcessor();
    }
}
