package com.github.al.authority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author An
 * @Description: 资源服务器配置
 * OAuth2授权模块会提供用户信息, 所以本身也是资源服务器
 * @Date: create in 2018/3/12 11:04
 * @Modified By:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 自定义权限保护规则
         */
        http
                // 禁用csrf保护
                .csrf().disable()
                // 授权请求
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
