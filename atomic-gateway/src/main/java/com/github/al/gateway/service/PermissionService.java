package com.github.al.gateway.service;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author An
 * @Description: 授权信息判断接口
 * @see com.github.al.gateway.config.ResourceServerConfig#configure(HttpSecurity)
 * @Date: create in 2018/3/13 14:08
 * @Modified By:
 */
public interface PermissionService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
