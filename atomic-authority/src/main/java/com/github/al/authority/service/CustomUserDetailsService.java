package com.github.al.authority.service;

import com.github.al.authority.feign.UserFeignClient;
import com.github.al.common.entity.User;
import com.github.al.common.web.entity.RetEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 11:00
 * @Modified By:
 */
@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RetEntity<User> retEntity = userFeignClient.findByUsername(username);
        if(!retEntity.isStatus()){
            log.error("loadUserByUsername: " + retEntity.getMessage());
            throw new UsernameNotFoundException(retEntity.getMessage());
        }
        return new CustomUserDetails(retEntity.getBody());
    }
}
