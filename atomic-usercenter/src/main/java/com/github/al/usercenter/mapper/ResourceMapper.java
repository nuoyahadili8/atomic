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

import com.github.al.common.entity.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源信息(菜单&按钮) Mapper 接口
 * @author by an on 2018-03-10.
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询用户资源
     * @param userId
     * @return
     */
    public List<Resource> findByUserId(@Param("userId") Long userId);


    /**
     * 查询角色资源（用于鉴权,url != null）
     * @param roleCode
     * @return
     */
    public List<Resource> findWithUrlNotNullByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 获取用户的所有菜单(不含按钮、其他资源, 即type==0)
     * @param username
     * @return
     */
    public List<Resource> getAllMenuByUsername(@Param("username") String username);

}
