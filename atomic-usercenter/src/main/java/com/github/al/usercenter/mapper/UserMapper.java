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
package com.github.al.usercenter.mapper;

import com.github.al.common.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户信息 Mapper 接口
 * @author by an on 2018-03-10.
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询(多对多,自动填充roles)
     * @param username
     * @return
     */
    public User findByUsername(@Param("username") String username);

    /**
     * 删除用户关联的角色(tb_user_role)
     * @param userId
     */
    public void deleteUserAssociatedRoles(@Param("userId") Long userId);

}
