package com.github.al.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 17:53
 * @Modified By:
 */
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解
@SpringBootApplication(scanBasePackages = {"com.github.al.common.web","com.github.al.gateway"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
