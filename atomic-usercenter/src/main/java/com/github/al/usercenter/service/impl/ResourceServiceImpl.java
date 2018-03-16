/*
 * Copyright 2018 吴汶泽(wenzewoo@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.al.usercenter.service.impl;

import com.github.al.common.entity.Resource;
import com.github.al.usercenter.mapper.ResourceMapper;
import com.github.al.usercenter.service.ResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 资源信息(菜单&按钮) 服务实现类
 * @author by an on 2018-03-10.
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> findByUserId(Long userId) {
        return Optional.ofNullable(resourceMapper.findByUserId(userId)).orElse(Lists.newArrayList());
    }

    @Override
    public List<Resource> findWithUrlNotNullByRoleCode(String roleCode) {
        return Optional.ofNullable(resourceMapper.findWithUrlNotNullByRoleCode(roleCode)).orElse(Lists.newArrayList());
    }

    @Override
    public List<Resource> getAllMenuByUsername(String username) {
        return Optional.ofNullable(resourceMapper.getAllMenuByUsername(username)).orElse(Lists.newArrayList());
    }
}
