package com.github.al.gateway.feign.fallback;

import com.github.al.common.Constant;
import com.github.al.common.web.entity.RetEntity;
import com.github.al.gateway.feign.ResourceFeignClient;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @author by an on 2018/3/13.
 */
@Log4j
@Component
public class ResourceFeignClientFallback implements ResourceFeignClient {
    @Override
    public RetEntity findByRoleCode(String roleCode) {

        int retCode = Constant.Code.SC_FEIGN_FALLBACK;
        String retMessage = String.format(
            "Feign调用接口失败(from: %s, Method: findByRoleCode, Params: %s)",
            Constant.Service.ATOMIC_USERCENTER, roleCode
        );
        log.error(retMessage);
        return RetEntity.error(retCode, retMessage);
    }
}
