package com.github.al.gateway.service.impl;

import com.github.al.common.entity.Resource;
import com.github.al.common.web.entity.RetEntity;
import com.github.al.gateway.feign.ResourceFeignClient;
import com.github.al.gateway.service.PermissionService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/13 14:09
 * @Modified By:
 */
@Log4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ResourceFeignClient resourceFeignClient;

    static AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 用户信息是否存在
        if(authentication.getPrincipal() != null) {
            // 权限合集 (角色)
            List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

            if(CollectionUtil.isNotEmpty(authorities)) {
                for (SimpleGrantedAuthority authority : authorities) {
                    // 取当前角色关联的资源
                    RetEntity<List<Resource>> retEntity = resourceFeignClient.findByRoleCode(authority.getAuthority());

                    if(retEntity.isStatus() && CollectionUtil.isNotEmpty(retEntity.getBody())) {
                        // 当前请求是否包含在菜单合集内 && 请求方式是否一致
                        boolean hasPermission = retEntity.getBody().stream()
                                .anyMatch((resource) -> {
                                    String url = resource.getUrl();
                                    String method = resource.getMethod() != null ? resource.getMethod().toString() : null;
                                    return StrUtil.isNotEmpty(url)
                                            && StrUtil.isNotEmpty(method)
                                            && pathMatcher.match(url, request.getRequestURI())
                                            && method.equalsIgnoreCase(request.getMethod());
                                });
                        if (hasPermission) return true;
                    }
                }
            }
        }
        return false;
    }
}
