package com.github.al.gateway.feign;

import com.github.al.common.Constant;
import com.github.al.common.entity.Resource;
import com.github.al.common.web.entity.RetEntity;
import com.github.al.gateway.feign.fallback.ResourceFeignClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author by an on 2018/3/13.
 */
@FeignClient(name = Constant.Service.ATOMIC_USERCENTER, fallback = ResourceFeignClientFallback.class)
public interface ResourceFeignClient {

    @GetMapping(value = "/resource/findByRoleCode/{roleCode}")
    RetEntity<List<Resource>> findByRoleCode(@PathVariable("roleCode") String roleCode);
}
