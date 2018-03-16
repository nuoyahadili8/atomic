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

import com.github.al.common.entity.User;
import com.github.al.common.entity.enums.ResourceType;
import com.github.al.common.exception.DefaultException;
import com.github.al.usercenter.mapper.ResourceMapper;
import com.github.al.usercenter.mapper.UserMapper;
import com.github.al.usercenter.model.UserInfo;
import com.github.al.usercenter.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.al.common.entity.Resource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 用户信息 服务实现类
 * @author by an on 2018-03-10.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public User findByUsername(String username) throws DefaultException {
        User user=userMapper.findByUsername(username);
        if(user == null){
            throw new DefaultException("找不到用户: " + username);
        }
        return user;
    }

    @Override
    public void deleteUserAssociatedRoles(Long userId) {
        userMapper.deleteUserAssociatedRoles(userId);
    }

    @Override
    public UserInfo getInfo(String username) {
        User user=userMapper.findByUsername(username);
        List<Resource> resources= Optional.ofNullable(resourceMapper.getAllMenuByUsername(username)).orElse(Lists.newArrayList());
        UserInfo userInfo=new UserInfo().setUser(user)
                //角色列表
                .setRoles(()->user.getRoleSet().stream().map(role -> role.getCode()).toArray(String[]::new))
                //菜单
                .setMenus(()->filterBy(resources,resource -> StrUtil.isEmpty(resource.getCode()) && resource.getType()== ResourceType.MENU))
                //权限列表（按钮）
                .setPermissions(()->filterBy(resources,resource -> StrUtil.isEmpty(resource.getCode()) && resource.getType() == ResourceType.BUTTON));
        return userInfo;
    }

    private String[] filterBy(List<Resource> resources, Predicate<Resource> predicate){
        if(CollectionUtil.isEmpty(resources)){
            return new String[0];
        }
        return resources.stream().filter(predicate).map(resource -> resource.getCode()).toArray(String[] :: new);
    }
}
