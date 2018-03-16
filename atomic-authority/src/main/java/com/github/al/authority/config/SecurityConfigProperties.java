package com.github.al.authority.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 11:02
 * @Modified By:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.oauth2")
public class SecurityConfigProperties {

    private String clientId;
    private String clientSecret;
    private String scope;
}
