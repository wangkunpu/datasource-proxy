package com.wkp.datasource.proxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("data.source.proxy")
public class DataSourceProxyProperties {
    private String enabled;

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
