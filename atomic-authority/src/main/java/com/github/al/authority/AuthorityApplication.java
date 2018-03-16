package com.github.al.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/11 18:29
 * @Modified By:
 */
@EnableFeignClients
@EnableResourceServer
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.github.al.common.web","com.github.al.authority","com.github.al.nosql.redis"
})
public class AuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class,args);
    }

    // security 异常消息国际化
    @Bean
    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:com/github/al/authority/messages_zh_CN");
        return messageSource;
    }
}
