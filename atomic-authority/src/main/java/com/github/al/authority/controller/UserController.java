package com.github.al.authority.controller;

import com.github.al.common.web.entity.RetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 11:06
 * @Modified By:
 */
@RestController
public class UserController {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    /**
     * 注销登陆
     */
    @PostMapping("/removeToken")
    public RetEntity removeToken(String accessToken, String refreshToken) {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.removeAccessToken(accessToken);
        redisTokenStore.removeRefreshToken(refreshToken);
        return RetEntity.ok();
    }
}
