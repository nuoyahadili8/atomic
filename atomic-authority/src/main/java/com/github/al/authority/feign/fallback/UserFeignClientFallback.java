package com.github.al.authority.feign.fallback;

import com.github.al.authority.feign.UserFeignClient;
import com.github.al.common.Constant;
import com.github.al.common.entity.User;
import com.github.al.common.web.entity.RetEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/11 18:38
 * @Modified By:
 */
@Log4j
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public RetEntity<User> findByUsername(String username) {
        int retCode = Constant.Code.SC_FEIGN_FALLBACK;
        String retMessage = String.format(
                "Feign调用接口失败(from: %s, Method: findByUsername, Params: %s)",
                Constant.Service.ATOMIC_USERCENTER, username
        );
        log.error(retMessage);
        return RetEntity.error(retCode, retMessage);
    }
}
