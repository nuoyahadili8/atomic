package com.github.al.gateway.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/13 13:59
 * @Modified By:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityConfigProperties {

    private List<String> excludeUrls = Lists.newArrayList();
}
