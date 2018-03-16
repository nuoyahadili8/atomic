package com.github.al.authority.service;

import com.github.al.common.entity.Role;
import com.github.al.common.entity.User;
import com.github.al.common.entity.enums.UserStatus;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.util.CollectionUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 10:48
 * @Modified By:
 */
@Log4j
public class CustomUserDetails implements UserDetails,Serializable {

    private User userEntity;

    public CustomUserDetails(User userEntity){
        this.userEntity=userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 构建角色集合: Role -> SimpleGrantedAuthority
        Set<Role> roleSet=userEntity.getRoleSet();
        List<GrantedAuthority> authorities = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(roleSet)){
            roleSet.stream().map(role -> new SimpleGrantedAuthority(role.getCode()))
                    .forEach(authority -> authorities.add(authority));
            log.debug("buildAuthority:" + authorities.toString());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账户是否没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账户是否没有锁定
        return userEntity.getStatus() != UserStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 凭证是否没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 账户是否启用
        return userEntity.getStatus() == UserStatus.ENABLED;
    }
}
