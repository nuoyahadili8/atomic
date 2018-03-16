package com.github.al.authority.feign;

import com.github.al.authority.feign.fallback.UserFeignClientFallback;
import com.github.al.common.Constant;
import com.github.al.common.entity.User;
import com.github.al.common.web.entity.RetEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/11 18:34
 * @Modified By:
 */
@FeignClient(name = Constant.Service.ATOMIC_USERCENTER, fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/user/findByUsername/{username}")
    RetEntity<User> findByUsername(@PathVariable("username") String username);
}
