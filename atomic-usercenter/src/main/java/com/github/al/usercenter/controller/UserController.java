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
package com.github.al.usercenter.controller;


import com.github.al.common.entity.User;
import com.github.al.common.utils.TokenKit;
import com.github.al.common.web.entity.RetEntity;
import com.github.al.usercenter.model.UserInfo;
import com.github.al.usercenter.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息 前端控制器
 * @author by an on 2018-03-10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public RetEntity<UserInfo> info(HttpServletRequest request){
        return RetEntity.ok().setBody(userService.getInfo(TokenKit.getUsername(request)));
    }

    @PutMapping("/updatePwd")
    public RetEntity updatePwd(HttpServletRequest request,String oldPassword,String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = TokenKit.getUsername(request);

        User user = userService.findByUsername(username);
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return RetEntity.error("原密码错误。");
        }
        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        boolean flag = user.updateById();
        return flag ? RetEntity.ok() : RetEntity.error();
    }

    @GetMapping("/findByUsername/{username}")
    public RetEntity<User> findByUsername(@PathVariable String username){
        User user=userService.findByUsername(username);
        try {
            return RetEntity.ok().setBody(user);
        } catch (Exception e) {
            return RetEntity.error(e.getMessage());
        }
    }

}

