package com.github.al.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 22:47
 * @Modified By:
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.github.al.common.web","com.github.al.usercenter","com.github.al.db.mybatis"
})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class,args);
    }
}
